package nl.nickthijssen.chessserver.messaging;

import nl.nickthijssen.chesscore.*;

public interface ServerMessageGenerator {

	void broadcastMessage(String message);

	void sendGeneralMessage(String sessionId, String message);

	void sendStartGameResult(String sessionId, String playerUsername, PlayerColor playerColor,
							 String opponentUsername);

	void sendPlayerAddedResult(String sessionId, String playerUsername, PlayerColor playerColor);

	void sendOpponentAddedResult(String sessionId, String opponentUsername, PlayerColor opponentColor);

	void sendPieceLocation(String sessionId, PieceLocation pieceLocation);

	void sendValidMovementLocation(String sessionId, SquareLocation squareLocation);

	void sendMoveDoneResult(String sessionId, String message, String time, String player, String piece, String capture
			, String origin, String target, int originColumn, int originRow, int targetColumn, int targetRow);

	void sendMoveUndoneResult(String sessionId, String message, String time, String player, String piece,
							  String capture, String origin, String target, int originColumn, int originRow,
							  int targetColumn, int targetRow);

	void sendTurnSwitchedMessage(String sessionId, String username, PlayerColor playerColor);
}
