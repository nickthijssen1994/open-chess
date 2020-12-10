package nl.nickthijssen.chesscore.game.board;

import nl.nickthijssen.chesscore.*;
import nl.nickthijssen.chesscore.game.move.Move;
import nl.nickthijssen.chesscore.game.pieces.Piece;

import java.util.List;

public interface Board {

	void setPiecesToStartPosition();

	Square getSquare(Location location);

	List<PieceLocation> getPieceLocations();

	List<Move> getPossibleMoves(PlayerColor playerColor);

	// Returns a list of squares that can by targeted by opponent at this moment
	List<Square> getPossibleTargets(PlayerColor color);

	boolean isKingInCheck(PlayerColor color);

	boolean isKingInCheckmate(PlayerColor color);

	int getLines();

	int getRows();

	List<Square> getEmptySquares();

	List<Piece> getActivePieces();

	List<Piece> getActivePlayerPieces(PlayerColor player);

	List<Piece> getCapturedPieces();

	List<Piece> getCapturedPlayerPieces(PlayerColor player);

	PlayerColor getPlayerWhoHasTurn();

	PlayerColor getPlayerWhoNotHasTurn();

	List<Move> getLegalPlayerMoves(PlayerColor player);

	List<Square> getSquares();

	Move movePiece(PlayerColor player, Location origin, Location target);

	void placePiece(Piece piece, Location location);

	void removePiece(Location location);

	boolean promotePawn(PlayerColor playerColor, PieceType pieceType);
}
