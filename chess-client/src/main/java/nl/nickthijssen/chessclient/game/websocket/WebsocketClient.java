package nl.nickthijssen.chessclient.game.websocket;

import nl.nickthijssen.commons.serialization.SerializationProvider;
import nl.nickthijssen.commons.serialization.Serializer;
import nl.nickthijssen.websocketshared.BaseWebsocket;
import nl.nickthijssen.websocketshared.messaging.EncapsulatingMessage;
import nl.nickthijssen.websocketshared.messaging.MessageProcessor;

import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public class WebsocketClient extends BaseWebsocket implements WebsocketClientEndpoint {

	public static final String SESSION_ID = "[WebSocket Session ID] : ";
	private static WebsocketClient instance = null;
	MessageProcessor messageProcessor;
	private String serverURL = "ws://localhost:8095/chess/";
	private Session session;

	public static WebsocketClient getInstance() {
		if (instance == null) {
			instance = new WebsocketClient();
		}
		return instance;
	}

	@Override
	public void start() {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, new URI(serverURL));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void stop() {
		try {
			if (session != null) {
				session.close();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@OnOpen
	public void onConnect(Session session) {
		System.out.println("[Socket Connected] " + SESSION_ID + session.getId());
		this.session = session;
	}

	@OnMessage
	public void onMessage(Session session, String messageString) {
		System.out.println("[Message Received From Server] : " + messageString);
		Serializer<String> serializer = SerializationProvider.getSerializer();
		EncapsulatingMessage message = serializer.deserialize(messageString, EncapsulatingMessage.class);
		messageProcessor.processMessage(session.getId(), message.getType(), message.getContent());
	}

	@OnError
	public void onError(Session session, Throwable cause) {
		System.out.println(cause.getMessage());
	}

	@OnClose
	public void onClose(CloseReason reason) {
		System.out.println("[Socket Closed] : " + reason);
		session = null;
	}

	@Override
	public void sendToServer(Object objectMessage) {
		String messageString = getEncapsulatingMessageGenerator().generateMessageString(objectMessage);
		session.getAsyncRemote().sendText(messageString);
		System.out.println("[Message Send To Server] : " + messageString);
	}

	@Override
	public void setMessageProcessor(MessageProcessor processor) {
		this.messageProcessor = processor;
	}
}
