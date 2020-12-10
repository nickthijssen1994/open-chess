package nl.nickthijssen.chessclient.game.websocket;

public interface ClientMessageGenerator {

	void sendStartGameRequest(boolean singlePlayer, String username);

	void sendPlayerReadyRequest();

	void sendPieceSelectedRequest(int originColumn, int originRow);

	void sendTargetSelectedRequest(int targetColumn, int targetRow);

	void sendMakeMoveRequest(int originColumn, int originRow, int targetColumn, int targetRow);

	void sendUndoLastMoveRequest();

	void sendPauseGameRequest();

	void sendResignRequest();
}
