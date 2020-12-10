package nl.nickthijssen.chessserver.messaging.handlers;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.requests.PieceSelectedRequestMessage;

public class PieceSelectedMessageHandler extends MessageHandlerBase<PieceSelectedRequestMessage> {

	private GameManager gameManager;

	public PieceSelectedMessageHandler(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void handleMessageInternal(PieceSelectedRequestMessage message, String sessionId) {
		gameManager.handlePieceSelectedRequest(sessionId, message.getOriginColumn(), message.getOriginRow());
	}
}
