package nl.nickthijssen.chesscore;

import nl.nickthijssen.chesscore.game.move.Move;

import java.util.List;

public interface Game {

	void addPlayer(String sessionId, boolean singlePlayer, String username);

	void setPlayerReady(PlayerColor color);

	void selectPiece(PlayerColor color, int originColumn, int originRow);

	void selectTarget(PlayerColor color, int targetColumn, int targetRow);

	void makeMove(PlayerColor color, int originColumn, int originRow, int targetColumn, int targetRow);

	void undoLastMove(PlayerColor color);

	void pauseGame(PlayerColor color);

	void restartGame(PlayerColor color);

	void resign(PlayerColor color);

	int getNumberOfPlayers();

	boolean getHasStarted();

	boolean getHasEnded();

	Player getOtherPlayer(Player player);

	List<Player> getPlayers();

	List<Move> getDoneMoves();

	List<PieceLocation> getPieceLocations();
}
