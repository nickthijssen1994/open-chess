package nl.nickthijssen.chessserver.messaging.handlers;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.requests.ResignRequestMessage;

public class ResignMessageHandler extends MessageHandlerBase<ResignRequestMessage> {

	private GameManager gameManager;

	public ResignMessageHandler(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void handleMessageInternal(ResignRequestMessage message, String sessionId) {
		gameManager.handleResignRequest(sessionId);
	}
}
