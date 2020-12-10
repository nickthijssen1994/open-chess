package nl.nickthijssen.chessclient.game.websocket.handlers;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.results.OpponentAddedResultMessage;

public class OpponentAddedResultMessageHandler extends MessageHandlerBase<OpponentAddedResultMessage> {

	private GameClient gameClient;

	public OpponentAddedResultMessageHandler(GameClient client) {
		this.gameClient = client;
	}

	@Override
	public void handleMessageInternal(OpponentAddedResultMessage message, String sessionId) {
		gameClient.processOpponentAdded(message.getOpponentUsername(), message.getOpponentColor());
	}
}
