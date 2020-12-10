package nl.nickthijssen.chessserver.messaging.handlers;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.requests.StartGameRequestMessage;

public class StartGameMessageHandler extends MessageHandlerBase<StartGameRequestMessage> {

	private GameManager gameManager;

	public StartGameMessageHandler(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void handleMessageInternal(StartGameRequestMessage message, String sessionId) {
		gameManager.handleStartGameRequest(sessionId, message.isSinglePlayer(), message.getUsername());
	}
}
