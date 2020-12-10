package nl.nickthijssen.chessserver.messaging.handlers;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.requests.PieceLocationsRequestMessage;

public class PieceLocationsMessageHandler extends MessageHandlerBase<PieceLocationsRequestMessage> {

	private GameManager gameManager;

	public PieceLocationsMessageHandler(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void handleMessageInternal(PieceLocationsRequestMessage message, String sessionId) {
		gameManager.handlePieceLocationsRequest(sessionId);
	}
}
