package nl.nickthijssen.chessclient.game.websocket.handlers;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.results.TurnSwitchedResultMessage;

public class TurnSwitchedResultMessageHandler extends MessageHandlerBase<TurnSwitchedResultMessage> {

	private GameClient gameClient;

	public TurnSwitchedResultMessageHandler(GameClient client) {
		this.gameClient = client;
	}

	@Override
	public void handleMessageInternal(TurnSwitchedResultMessage message, String sessionId) {
		gameClient.processTurnSwitched(message.getUsername(), message.getPlayerColor());
	}
}
