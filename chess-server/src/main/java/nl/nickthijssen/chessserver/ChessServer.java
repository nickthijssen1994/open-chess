package nl.nickthijssen.chessserver;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.chessserver.messaging.*;
import nl.nickthijssen.chessserver.messaging.handlers.ChessServerMessageHandlerFactory;
import nl.nickthijssen.restclient.AuthenticationRestClient;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.websocket.server.*;

public class ChessServer {

	private static final int PORT = 8095;

	public static void main(String[] args) {
		startChessServer();
	}

	private static void startChessServer() {

		final MessageHandlerFactory messageHandlerFactory = new ChessServerMessageHandlerFactory();
		final ServerMessageProcessor messageProcessor = new ChessServerMessageProcessor(messageHandlerFactory);
		final WebsocketServer websocketServer = new WebsocketServer();
		websocketServer.setMessageProcessor(messageProcessor);
		final ServerMessageGenerator messageGenerator = new ChessServerMessageGenerator(websocketServer);

		final AuthenticationRestClient authenticationRestClient = new AuthenticationRestClient(
				"http://localhost:8090");

		final GameManager gameManager = new ChessServerGameManager(authenticationRestClient, messageGenerator);
		messageProcessor.registerGameManager(gameManager);

		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		connector.setPort(PORT);
		connector.setIdleTimeout(0);
		server.addConnector(connector);

		ServletContextHandler webSocketContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
		webSocketContext.setContextPath("/");
		server.setHandler(webSocketContext);

		try {
			ServerContainer serverContainer = WebSocketServerContainerInitializer.configureContext(webSocketContext);
			ServerEndpointConfig config = ServerEndpointConfig.Builder.create(websocketServer.getClass(),
																			  websocketServer.getClass().getAnnotation(
																					  ServerEndpoint.class).value()).configurator(
					new ServerEndpointConfig.Configurator() {
						@Override
						public <T> T getEndpointInstance(Class<T> endpointClass) {
							return (T) websocketServer;
						}
					}).build();
			serverContainer.addEndpoint(config);
			server.start();
			server.join();
		}
		catch (Exception t) {
			t.printStackTrace();
		}
	}
}
