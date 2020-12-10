package nl.nickthijssen.chessserver.messaging.handlers;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.requests.PlayerReadyRequestMessage;

public class PlayerReadyMessageHandler extends MessageHandlerBase<PlayerReadyRequestMessage> {

	private GameManager gameManager;

	public PlayerReadyMessageHandler(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void handleMessageInternal(PlayerReadyRequestMessage message, String sessionId) {
		gameManager.handlePlayerReadyRequest(sessionId);
	}
}
