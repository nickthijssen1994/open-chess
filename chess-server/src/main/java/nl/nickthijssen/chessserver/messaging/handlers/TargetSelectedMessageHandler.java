package nl.nickthijssen.chessserver.messaging.handlers;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.requests.TargetSelectedRequestMessage;

public class TargetSelectedMessageHandler extends MessageHandlerBase<TargetSelectedRequestMessage> {

	private GameManager gameManager;

	public TargetSelectedMessageHandler(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void handleMessageInternal(TargetSelectedRequestMessage message, String sessionId) {
		gameManager.handleTargetSelectedRequest(sessionId, message.getTargetColumn(), message.getTargetRow());
	}
}
