package nl.nickthijssen.chessclient.game.websocket.handlers;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.results.StartGameResultMessage;

public class StartGameResultMessageHandler extends MessageHandlerBase<StartGameResultMessage> {

	private GameClient gameClient;

	public StartGameResultMessageHandler(GameClient client) {
		this.gameClient = client;
	}

	@Override
	public void handleMessageInternal(StartGameResultMessage message, String sessionId) {
		gameClient.processStartGame(message.getPlayerUsername(), message.getPlayerColor(),
									message.getOpponentUsername());
	}
}
