package nl.nickthijssen.chessserver.messaging;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.*;

public class ChessServerMessageProcessor extends MessageProcessorBase implements ServerMessageProcessor {

	private GameManager gameManager;

	public ChessServerMessageProcessor(MessageHandlerFactory messageHandlerFactory) {
		super(messageHandlerFactory);
	}

	public void registerGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	@Override
	public void processMessage(String sessionId, String type, String data) {
		//Get the last part from the full package and type name
		String simpleType = type.split("\\.")[type.split("\\.").length - 1];

		MessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, getGameManager());
		handler.handleMessage(data, sessionId);
	}

	public void handleDisconnect(String sessionId) {
		getGameManager().processClientDisconnect(sessionId);
	}
}
