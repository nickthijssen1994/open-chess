package nl.nickthijssen.chessclient.game.websocket.handlers;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.chesscore.PieceLocation;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.results.PieceLocationResultMessage;

public class PieceLocationResultMessageHandler extends MessageHandlerBase<PieceLocationResultMessage> {

	private GameClient gameClient;

	public PieceLocationResultMessageHandler(GameClient client) {
		this.gameClient = client;
	}

	@Override
	public void handleMessageInternal(PieceLocationResultMessage message, String sessionId) {
		gameClient.processLoadPieceLocation(
				new PieceLocation(message.getColumn(), message.getRow(), message.getType(), message.getColor()));
	}
}
