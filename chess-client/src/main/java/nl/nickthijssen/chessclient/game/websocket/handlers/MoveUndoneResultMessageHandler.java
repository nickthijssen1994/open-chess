package nl.nickthijssen.chessclient.game.websocket.handlers;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.results.MoveUndoneResultMessage;

public class MoveUndoneResultMessageHandler extends MessageHandlerBase<MoveUndoneResultMessage> {

	private GameClient gameClient;

	public MoveUndoneResultMessageHandler(GameClient client) {
		this.gameClient = client;
	}

	@Override
	public void handleMessageInternal(MoveUndoneResultMessage message, String sessionId) {
		gameClient.processMoveUndoneMessage(message.getMessage());
		gameClient.processMoveDone(message.getTime(), message.getPlayer(), message.getPiece(), message.getCapture(),
								   message.getOrigin(), message.getTarget());
		gameClient.processUndoPieceMoved(message.getOriginColumn(), message.getOriginRow(), message.getTargetColumn(),
										 message.getTargetRow());
	}
}
