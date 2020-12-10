package nl.nickthijssen.chesscore.game.pieces;

import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BishopTest {

	@Test
	void getLegalBishopMovesOnEmptyBoardTest() {
		Board board = new ChessBoard();
		Piece bishop = new Bishop(PlayerColor.WHITE);
		board.placePiece(bishop, Location.d4);
		assertEquals(bishop, board.getSquare(Location.d4).getPiece());

		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.c5, Location.b6, Location.a7, Location.c3, Location.b2, Location.a1,
							  Location.e3,
							  Location.f2, Location.g1, Location.e5, Location.f6, Location.g7, Location.h8));
		List<Location> actualValidMovements = bishop.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		board.removePiece(Location.d4);
		assertTrue(board.getSquare(Location.d4).isEmpty());
		board.placePiece(bishop, Location.h8);
		assertEquals(bishop, board.getSquare(Location.h8).getPiece());

		expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.g7, Location.f6, Location.e5, Location.d4, Location.c3, Location.b2,
							  Location.a1));
		actualValidMovements = bishop.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		board.removePiece(Location.h8);
		assertTrue(board.getSquare(Location.h8).isEmpty());
	}

	@Test
	void getLegalBishopMovesOnBoardWithOwnPiecesTest() {
		Board board = new ChessBoard();
		Piece bishop = new Bishop(PlayerColor.WHITE);
		board.placePiece(bishop, Location.d4);
		assertEquals(bishop, board.getSquare(Location.d4).getPiece());

		Piece pawn1 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn1, Location.b2);
		Piece pawn2 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn2, Location.g1);
		Piece pawn3 = new Pawn(PlayerColor.WHITE);
		board.placePiece(pawn3, Location.a7);

		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.c3, Location.e3, Location.f2, Location.c5, Location.b6, Location.e5,
							  Location.f6,
							  Location.g7, Location.h8));
		List<Location> actualValidMovements = bishop.getLegalMoves(board);

		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void getLegalBishopMovesOnBoardWithEnemyPiecesTest() {
		Board board = new ChessBoard();
		Piece bishop = new Bishop(PlayerColor.WHITE);
		board.placePiece(bishop, Location.d4);
		assertEquals(bishop, board.getSquare(Location.d4).getPiece());

		Piece pawn1 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn1, Location.b2);
		Piece pawn2 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn2, Location.g1);
		Piece pawn3 = new Pawn(PlayerColor.BLACK);
		board.placePiece(pawn3, Location.a7);

		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.b2, Location.c3, Location.e3, Location.f2, Location.g1, Location.c5,
							  Location.b6,
							  Location.a7, Location.e5, Location.f6, Location.g7, Location.h8));
		List<Location> actualValidMovements = bishop.getLegalMoves(board);

		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}
}
