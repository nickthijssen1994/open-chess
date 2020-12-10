package nl.nickthijssen.chesscore.game.pieces;

import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {

	@Test
	void getLegalKnightMovesOnEmptyBoardTest() {
		Board board = new ChessBoard();
		Piece knight = new Knight(PlayerColor.WHITE);
		board.placePiece(knight, Location.d4);
		assertEquals(knight, board.getSquare(Location.d4).getPiece());

		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.b5, Location.b3, Location.c2, Location.e2, Location.f3, Location.f5,
							  Location.e6,
							  Location.c6));
		List<Location> actualValidMovements = knight.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		board.removePiece(Location.d4);
		assertTrue(board.getSquare(Location.d4).isEmpty());
		board.placePiece(knight, Location.h8);
		assertEquals(knight, board.getSquare(Location.h8).getPiece());

		expectedValidMovements = new HashSet<>(Arrays.asList(Location.f7, Location.g6));
		actualValidMovements = knight.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		board.removePiece(Location.h8);
		assertTrue(board.getSquare(Location.h8).isEmpty());
	}

	@Test
	void getLegalKnightMovesOnBoardWithOwnPiecesTest() {
		Board board = new ChessBoard();
		Piece knight = new Knight(PlayerColor.WHITE);
		board.placePiece(knight, Location.d4);
		assertEquals(knight, board.getSquare(Location.d4).getPiece());

		Piece pawn1 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn1, Location.b5);
		Piece pawn2 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn2, Location.f3);
		Piece pawn3 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn3, Location.e6);

		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.b3, Location.c2, Location.e2, Location.f5, Location.c6));
		List<Location> actualValidMovements = knight.getLegalMoves(board);

		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void getLegalKnightMovesOnBoardWithEnemyPiecesTest() {
		Board board = new ChessBoard();
		Piece knight = new Knight(PlayerColor.WHITE);
		board.placePiece(knight, Location.d4);
		assertEquals(knight, board.getSquare(Location.d4).getPiece());

		Piece pawn1 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn1, Location.b5);
		Piece pawn2 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn2, Location.f3);
		Piece pawn3 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn3, Location.e6);

		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.b5, Location.b3, Location.c2, Location.e2, Location.f3, Location.f5,
							  Location.e6,
							  Location.c6));
		List<Location> actualValidMovements = knight.getLegalMoves(board);

		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}
}
