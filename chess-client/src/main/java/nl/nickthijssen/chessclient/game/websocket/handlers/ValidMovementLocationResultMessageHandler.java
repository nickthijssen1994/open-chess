package nl.nickthijssen.chessclient.game.websocket.handlers;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.chesscore.SquareLocation;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.results.ValidMovementLocationResultMessage;

public class ValidMovementLocationResultMessageHandler extends MessageHandlerBase<ValidMovementLocationResultMessage> {

	private GameClient gameClient;

	public ValidMovementLocationResultMessageHandler(GameClient client) {
		this.gameClient = client;
	}

	@Override
	public void handleMessageInternal(ValidMovementLocationResultMessage message, String sessionId) {
		gameClient.processLoadValidMovementLocation(new SquareLocation(message.getColumn(), message.getRow()));
	}
}
