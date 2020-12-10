package nl.nickthijssen.chessclient.game.websocket.handlers;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.websocketshared.messaging.MessageHandler;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerFactory;

public class ChessClientMessageHandlerFactory implements MessageHandlerFactory {

	@Override
	public MessageHandler getHandler(String simpleType, Object gameClientObject) {
		GameClient gameClient = (GameClient) gameClientObject;

		switch (simpleType) {
			case "GeneralResultMessage":
				return new GeneralResultMessageHandler(gameClient);
			case "StartGameResultMessage":
				return new StartGameResultMessageHandler(gameClient);
			case "PlayerAddedResultMessage":
				return new PlayerAddedResultMessageHandler(gameClient);
			case "OpponentAddedResultMessage":
				return new OpponentAddedResultMessageHandler(gameClient);
			case "ValidMovementLocationResultMessage":
				return new ValidMovementLocationResultMessageHandler(gameClient);
			case "PieceLocationResultMessage":
				return new PieceLocationResultMessageHandler(gameClient);
			case "MoveDoneResultMessage":
				return new MoveDoneResultMessageHandler(gameClient);
			case "MoveUndoneResultMessage":
				return new MoveUndoneResultMessageHandler(gameClient);
			case "TurnSwitchedResultMessage":
				return new TurnSwitchedResultMessageHandler(gameClient);
			default:
				return null;
		}
	}
}
