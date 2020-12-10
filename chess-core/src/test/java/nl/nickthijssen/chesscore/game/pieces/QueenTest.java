package nl.nickthijssen.chesscore.game.pieces;

import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QueenTest {

	@Test
	void getLegalQueenMovesOnEmptyBoardTest() {
		Board board = new ChessBoard();
		Piece queen = new Queen(PlayerColor.WHITE);
		board.placePiece(queen, Location.d4);
		assertEquals(queen, board.getSquare(Location.d4).getPiece());

		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.c5, Location.b6, Location.a7, Location.c3, Location.b2, Location.a1,
							  Location.e3,
							  Location.f2, Location.g1, Location.e5, Location.f6, Location.g7, Location.h8,
							  Location.c4,
							  Location.b4, Location.a4, Location.d3, Location.d2, Location.d1, Location.d5,
							  Location.d6,
							  Location.d7, Location.d8, Location.e4, Location.f4, Location.g4, Location.h4));
		List<Location> actualValidMovements = queen.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		board.removePiece(Location.d4);
		assertTrue(board.getSquare(Location.d4).isEmpty());
		board.placePiece(queen, Location.h8);
		assertEquals(queen, board.getSquare(Location.h8).getPiece());

		expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.g7, Location.f6, Location.e5, Location.d4, Location.c3, Location.b2,
							  Location.a1,
							  Location.a8, Location.b8, Location.c8, Location.d8, Location.e8, Location.f8,
							  Location.g8,
							  Location.h7, Location.h6, Location.h5, Location.h4, Location.h3, Location.h2,
							  Location.h1));
		actualValidMovements = queen.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		board.removePiece(Location.h8);
		assertTrue(board.getSquare(Location.h8).isEmpty());
	}

	@Test
	void getLegalQueenMovesOnBoardWithOwnPiecesTest() {
		Board board = new ChessBoard();
		Piece queen = new Queen(PlayerColor.WHITE);
		board.placePiece(queen, Location.d4);
		assertEquals(queen, board.getSquare(Location.d4).getPiece());

		Piece pawn1 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn1, Location.b2);
		Piece pawn2 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn2, Location.g1);
		Piece pawn3 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn3, Location.a7);
		Piece pawn4 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn4, Location.h4);
		Piece pawn5 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn5, Location.d2);

		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.c5, Location.b6, Location.c3, Location.e3, Location.f2, Location.e5,
							  Location.f6,
							  Location.g7, Location.h8, Location.c4, Location.b4, Location.a4, Location.d3,
							  Location.d5,
							  Location.d6, Location.d7, Location.d8, Location.e4, Location.f4, Location.g4));
		List<Location> actualValidMovements = queen.getLegalMoves(board);

		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void getLegalQueenMovesOnBoardWithEnemyPiecesTest() {
		Board board = new ChessBoard();
		Piece queen = new Queen(PlayerColor.WHITE);
		board.placePiece(queen, Location.d4);
		assertEquals(queen, board.getSquare(Location.d4).getPiece());

		Piece pawn1 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn1, Location.b2);
		Piece pawn2 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn2, Location.g1);
		Piece pawn3 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn3, Location.a7);
		Piece pawn4 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn4, Location.h4);
		Piece pawn5 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn5, Location.d2);

		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.c5, Location.b6, Location.c3, Location.e3, Location.f2, Location.e5,
							  Location.f6,
							  Location.g7, Location.h8, Location.c4, Location.b4, Location.a4, Location.d3,
							  Location.d5,
							  Location.d6, Location.d7, Location.d8, Location.e4, Location.f4, Location.g4,
							  Location.b2,
							  Location.g1, Location.a7, Location.h4, Location.d2));
		List<Location> actualValidMovements = queen.getLegalMoves(board);

		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}
}
