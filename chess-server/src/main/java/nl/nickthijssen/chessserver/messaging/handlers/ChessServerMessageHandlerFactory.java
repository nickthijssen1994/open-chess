package nl.nickthijssen.chessserver.messaging.handlers;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.MessageHandler;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerFactory;

public class ChessServerMessageHandlerFactory implements MessageHandlerFactory {

	public MessageHandler getHandler(String simpleType, Object gameManagerObject) {
		GameManager gameManager = (GameManager) gameManagerObject;
		switch (simpleType) {
			case "PauseGameRequestMessage":
				return new PauseGameMessageHandler(gameManager);
			case "PieceLocationsRequestMessage":
				return new PieceLocationsMessageHandler(gameManager);
			case "PlayerReadyRequestMessage":
				return new PlayerReadyMessageHandler(gameManager);
			case "ResignRequestMessage":
				return new ResignMessageHandler(gameManager);
			case "StartGameRequestMessage":
				return new StartGameMessageHandler(gameManager);
			case "PieceSelectedRequestMessage":
				return new PieceSelectedMessageHandler(gameManager);
			case "TargetSelectedRequestMessage":
				return new TargetSelectedMessageHandler(gameManager);
			case "DoMoveRequestMessage":
				return new DoMoveMessageHandler(gameManager);
			case "UndoMoveRequestMessage":
				return new UndoMoveMessageHandler(gameManager);
			default:
				return null;
		}
	}
}
