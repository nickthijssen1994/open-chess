package nl.nickthijssen.chessserver.messaging.handlers;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.requests.DoMoveRequestMessage;

public class DoMoveMessageHandler extends MessageHandlerBase<DoMoveRequestMessage> {

	private GameManager gameManager;

	public DoMoveMessageHandler(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void handleMessageInternal(DoMoveRequestMessage message, String sessionId) {
		gameManager.handleMakeMoveRequest(sessionId, message.getOriginColumn(), message.getOriginRow(),
										  message.getTargetColumn(), message.getTargetRow());
	}
}
