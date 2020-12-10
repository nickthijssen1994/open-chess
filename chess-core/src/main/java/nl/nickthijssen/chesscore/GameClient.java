package nl.nickthijssen.chesscore;

public interface GameClient {

	void registerGameClientUI(GameClientUI gameClientUI);

	void notifyStartGame();

	void notifyPieceSelected(int originColumn, int originRow);

	void notifyTargetSelected(int targetColumn, int targetRow);

	void notifyMakeMove(int originColumn, int originRow, int targetColumn, int targetRow);

	void notifyPlayerReady();

	void notifyPauseGame();

	void notifyUndoMove();

	void notifyResign();

	/*
	 * Methods Regarding Game
	 */
	void processGeneralMessage(String message);

	void processStartGame(String playerUsername, PlayerColor playerColor, String opponentUsername);

	void processLoadValidMovementLocation(SquareLocation squareLocation);

	void processLoadPieceLocation(PieceLocation pieceLocation);

	/*
	 * Methods Regarding Player
	 */
	void processPlayerAdded(String playerUsername, PlayerColor playerColor);

	void processOpponentAdded(String opponentUsername, PlayerColor opponentColor);

	void processTurnSwitched(String username, PlayerColor playerColor);

	/*
	 * Methods Regarding Moves
	 */
	void processMoveDoneMessage(String message);

	void processMoveDone(String time, String player, String piece, String capture, String origin, String target);

	void processMoveUndoneMessage(String message);

	void processUndoPieceMoved(int originColumn, int originRow, int targetColumn, int targetRow);

	void processPieceMoved(int originColumn, int originRow, int targetColumn, int targetRow);
}
