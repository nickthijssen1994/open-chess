package nl.nickthijssen.chessserver.messaging.handlers;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.requests.UndoMoveRequestMessage;

public class UndoMoveMessageHandler extends MessageHandlerBase<UndoMoveRequestMessage> {

	private GameManager gameManager;

	public UndoMoveMessageHandler(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void handleMessageInternal(UndoMoveRequestMessage message, String sessionId) {
		gameManager.handleUndoLastMoveRequest(sessionId);
	}
}
