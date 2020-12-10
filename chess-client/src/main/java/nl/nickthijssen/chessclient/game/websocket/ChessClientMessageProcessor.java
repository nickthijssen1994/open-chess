package nl.nickthijssen.chessclient.game.websocket;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.websocketshared.messaging.*;

public class ChessClientMessageProcessor extends MessageProcessorBase implements ClientMessageProcessor {

	private GameClient gameClient;

	public ChessClientMessageProcessor(MessageHandlerFactory messageHandlerFactory) {
		super(messageHandlerFactory);
	}

	@Override
	public void registerGameClient(GameClient gameClient) {
		this.gameClient = gameClient;
	}

	@Override
	public void processMessage(String sessionId, String type, String data) {
		String simpleType = type.split("\\.")[type.split("\\.").length - 1];

		MessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, gameClient);
		handler.handleMessage(data, sessionId);
	}

	@Override
	public void handleDisconnect(String sessionId) {
		// Needs implementation
	}
}
