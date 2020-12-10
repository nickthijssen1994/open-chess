package nl.nickthijssen.chesscore.game.pieces;

import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RookTest {

	@Test
	void getLegalRookMovesOnEmptyBoardTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece rook = new Rook(PlayerColor.WHITE);
		// Place rook on d4
		board.placePiece(rook, Location.d4);
		assertEquals(rook, board.getSquare(Location.d4).getPiece());

		// List of legal moves for a rook on d4 on an empty board
		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.c4, Location.b4, Location.a4, Location.d3, Location.d2, Location.d1,
							  Location.e4,
							  Location.f4, Location.g4, Location.h4, Location.d5, Location.d6, Location.d7,
							  Location.d8));
		List<Location> actualValidMovements = rook.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		// Move rook to h8
		board.removePiece(Location.d4);
		assertTrue(board.getSquare(Location.d4).isEmpty());
		board.placePiece(rook, Location.h8);
		assertEquals(rook, board.getSquare(Location.h8).getPiece());

		// List of legal moves for a rook on h8 on an empty board
		expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.h1, Location.h2, Location.h3, Location.h4, Location.h5, Location.h6,
							  Location.h7,
							  Location.g8, Location.f8, Location.e8, Location.d8, Location.c8, Location.b8,
							  Location.a8));
		actualValidMovements = rook.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		board.removePiece(Location.h8);
		assertTrue(board.getSquare(Location.h8).isEmpty());
	}

	@Test
	void getLegalRookMovesOnBoardWithOwnPiecesTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece rook = new Rook(PlayerColor.WHITE);
		// Place rook on d4
		board.placePiece(rook, Location.d4);
		assertEquals(rook, board.getSquare(Location.d4).getPiece());

		// Place some white pawns on movement lines
		// Rook should not be able to move to the location of these pawns and squares behind them
		Piece pawn1 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn1, Location.b4);
		Piece pawn2 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn2, Location.g4);
		Piece pawn3 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn3, Location.d1);
		Piece pawn4 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn4, Location.d7);

		// List of legal moves for a rook on d4 on a board with some pawns
		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.c4, Location.d3, Location.d2, Location.e4, Location.f4, Location.d5,
							  Location.d6));
		List<Location> actualValidMovements = rook.getLegalMoves(board);

		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void getLegalRookMovesOnBoardWithEnemyPiecesTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece rook = new Rook(PlayerColor.WHITE);
		// Place rook on d4
		board.placePiece(rook, Location.d4);
		assertEquals(rook, board.getSquare(Location.d4).getPiece());

		// Place some black pawns on movement lines
		// Rook should be able to move to the location of these pawns to capture them but not squares behind them
		Piece pawn1 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn1, Location.b4);
		Piece pawn2 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn2, Location.g4);
		Piece pawn3 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn3, Location.d1);
		Piece pawn4 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn4, Location.d7);

		// List of legal moves for a rook on d4 on a board with enemy pawns
		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.c4, Location.b4, Location.d3, Location.d2, Location.d1, Location.e4,
							  Location.f4,
							  Location.g4, Location.d5, Location.d6, Location.d7));
		List<Location> actualValidMovements = rook.getLegalMoves(board);

		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}
}
