package nl.nickthijssen.chessserver.messaging.handlers;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.requests.PauseGameRequestMessage;

public class PauseGameMessageHandler extends MessageHandlerBase<PauseGameRequestMessage> {

	private GameManager gameManager;

	public PauseGameMessageHandler(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void handleMessageInternal(PauseGameRequestMessage message, String sessionId) {
		gameManager.handlePauseGameRequest(sessionId);
	}
}
