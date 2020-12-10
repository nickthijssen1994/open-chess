package nl.nickthijssen.chessclient.game.websocket.handlers;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.results.PlayerAddedResultMessage;

public class PlayerAddedResultMessageHandler extends MessageHandlerBase<PlayerAddedResultMessage> {

	private GameClient gameClient;

	public PlayerAddedResultMessageHandler(GameClient client) {
		this.gameClient = client;
	}

	@Override
	public void handleMessageInternal(PlayerAddedResultMessage message, String sessionId) {
		gameClient.processPlayerAdded(message.getPlayerUsername(), message.getPlayerColor());
	}
}
