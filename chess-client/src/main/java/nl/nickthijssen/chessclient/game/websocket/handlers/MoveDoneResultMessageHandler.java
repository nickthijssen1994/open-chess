package nl.nickthijssen.chessclient.game.websocket.handlers;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.results.MoveDoneResultMessage;

public class MoveDoneResultMessageHandler extends MessageHandlerBase<MoveDoneResultMessage> {

	private GameClient gameClient;

	public MoveDoneResultMessageHandler(GameClient client) {
		this.gameClient = client;
	}

	@Override
	public void handleMessageInternal(MoveDoneResultMessage message, String sessionId) {
		gameClient.processMoveDoneMessage(message.getMessage());
		gameClient.processMoveDone(message.getTime(), message.getPlayer(), message.getPiece(), message.getCapture(),
								   message.getOrigin(), message.getTarget());
		gameClient.processPieceMoved(message.getOriginColumn(), message.getOriginRow(), message.getTargetColumn(),
									 message.getTargetRow());
	}
}
