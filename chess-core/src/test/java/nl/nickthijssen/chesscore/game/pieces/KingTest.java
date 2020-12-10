package nl.nickthijssen.chesscore.game.pieces;

import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

	@Test
	void kingMovesOnEmptyBoardTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece king = new King(PlayerColor.WHITE);
		// Place king on e1
		board.placePiece(king, Location.e1);
		assertEquals(king, board.getSquare(Location.e1).getPiece());

		// List of legal moves for a king on e1 on an empty board
		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.d1, Location.d2, Location.e2, Location.f2, Location.f1));
		List<Location> actualValidMovements = king.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		// Move king to d4
		board.removePiece(Location.e1);
		assertTrue(board.getSquare(Location.e1).isEmpty());
		board.placePiece(king, Location.d4);
		assertEquals(king, board.getSquare(Location.d4).getPiece());

		// List of legal moves for a king on d4 on an empty board
		expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.c4, Location.c5, Location.d5, Location.e5, Location.e4, Location.e3,
							  Location.d3,
							  Location.c3));
		actualValidMovements = king.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void whiteCastlingAllowedTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece king = new King(PlayerColor.WHITE);
		Piece rightRook = new Rook(PlayerColor.WHITE);
		Piece leftRook = new Rook(PlayerColor.WHITE);

		// Place king on e1 and rook on h1
		board.placePiece(king, Location.e1);
		board.placePiece(rightRook, Location.h1);
		board.placePiece(leftRook, Location.a1);
		assertEquals(king, board.getSquare(Location.e1).getPiece());
		assertEquals(rightRook, board.getSquare(Location.h1).getPiece());
		assertEquals(leftRook, board.getSquare(Location.a1).getPiece());

		assertFalse(king.hasMoved());
		assertFalse(rightRook.hasMoved());
		assertFalse(leftRook.hasMoved());
		assertTrue(board.getSquare(Location.b1).isEmpty());
		assertTrue(board.getSquare(Location.c1).isEmpty());
		assertTrue(board.getSquare(Location.d1).isEmpty());
		assertTrue(board.getSquare(Location.g1).isEmpty());
		assertTrue(board.getSquare(Location.f1).isEmpty());

		// List of legal moves for a king when castling is allowed
		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.d1, Location.d2, Location.e2, Location.f2, Location.f1, Location.g1,
							  Location.c1));
		List<Location> actualValidMovements = king.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void blackCastlingAllowedTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece king = new King(PlayerColor.BLACK);
		Piece rightRook = new Rook(PlayerColor.BLACK);
		Piece leftRook = new Rook(PlayerColor.BLACK);

		// Place king on e1 and rook on h1
		board.placePiece(king, Location.e8);
		board.placePiece(rightRook, Location.h8);
		board.placePiece(leftRook, Location.a8);
		assertEquals(king, board.getSquare(Location.e8).getPiece());
		assertEquals(rightRook, board.getSquare(Location.h8).getPiece());
		assertEquals(leftRook, board.getSquare(Location.a8).getPiece());

		assertFalse(king.hasMoved());
		assertFalse(rightRook.hasMoved());
		assertFalse(leftRook.hasMoved());
		assertTrue(board.getSquare(Location.b8).isEmpty());
		assertTrue(board.getSquare(Location.c8).isEmpty());
		assertTrue(board.getSquare(Location.d8).isEmpty());
		assertTrue(board.getSquare(Location.g8).isEmpty());
		assertTrue(board.getSquare(Location.f8).isEmpty());

		// List of legal moves for a king when castling is allowed
		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.d8, Location.d7, Location.e7, Location.f7, Location.f8, Location.g8,
							  Location.c8));
		List<Location> actualValidMovements = king.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void whiteCastlingNotAllowedWithOwnPieceBetweenKingAndRookTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece king = new King(PlayerColor.WHITE);
		Piece rightRook = new Rook(PlayerColor.WHITE);
		Piece leftRook = new Rook(PlayerColor.WHITE);
		Piece knight = new Knight(PlayerColor.WHITE);

		// Place king on e1, rook on h1 and a1 and knight on b1
		board.placePiece(king, Location.e1);
		board.placePiece(rightRook, Location.h1);
		board.placePiece(leftRook, Location.a1);
		board.placePiece(knight, Location.b1);
		assertEquals(king, board.getSquare(Location.e1).getPiece());
		assertEquals(rightRook, board.getSquare(Location.h1).getPiece());
		assertEquals(leftRook, board.getSquare(Location.a1).getPiece());
		assertEquals(knight, board.getSquare(Location.b1).getPiece());

		assertFalse(king.hasMoved());
		assertFalse(rightRook.hasMoved());
		assertFalse(leftRook.hasMoved());
		assertFalse(board.getSquare(Location.b1).isEmpty());
		assertTrue(board.getSquare(Location.c1).isEmpty());
		assertTrue(board.getSquare(Location.d1).isEmpty());
		assertTrue(board.getSquare(Location.g1).isEmpty());
		assertTrue(board.getSquare(Location.f1).isEmpty());

		// List of legal moves for a king when castling is allowed with the right rook
		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.d1, Location.d2, Location.e2, Location.f2, Location.f1, Location.g1));
		List<Location> actualValidMovements = king.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void whiteCastlingNotAllowedWithEnemyPieceBetweenKingAndRookTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece king = new King(PlayerColor.WHITE);
		Piece rightRook = new Rook(PlayerColor.WHITE);
		Piece leftRook = new Rook(PlayerColor.WHITE);
		Piece bishop = new Bishop(PlayerColor.BLACK);

		// Place king on e1, rook on h1 and a1 and knight on b1
		board.placePiece(king, Location.e1);
		board.placePiece(rightRook, Location.h1);
		board.placePiece(leftRook, Location.a1);
		board.placePiece(bishop, Location.b1);
		assertEquals(king, board.getSquare(Location.e1).getPiece());
		assertEquals(rightRook, board.getSquare(Location.h1).getPiece());
		assertEquals(leftRook, board.getSquare(Location.a1).getPiece());
		assertEquals(bishop, board.getSquare(Location.b1).getPiece());

		assertFalse(king.hasMoved());
		assertFalse(rightRook.hasMoved());
		assertFalse(leftRook.hasMoved());
		assertFalse(board.getSquare(Location.b1).isEmpty());
		assertTrue(board.getSquare(Location.c1).isEmpty());
		assertTrue(board.getSquare(Location.d1).isEmpty());
		assertTrue(board.getSquare(Location.g1).isEmpty());
		assertTrue(board.getSquare(Location.f1).isEmpty());

		// List of legal moves for a king when castling is allowed with the right rook
		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.d1, Location.d2, Location.e2, Location.f2, Location.f1, Location.g1));
		List<Location> actualValidMovements = king.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void whiteCastlingNotAllowedWhenRookHasMoved() {
		// Create empty board
		Board board = new ChessBoard();
		Piece king = new King(PlayerColor.WHITE);
		Piece rightRook = new Rook(PlayerColor.WHITE);

		// Place king on e1 and rook on h1
		board.placePiece(king, Location.e1);
		board.placePiece(rightRook, Location.h1);
		assertEquals(king, board.getSquare(Location.e1).getPiece());
		assertEquals(rightRook, board.getSquare(Location.h1).getPiece());

		assertFalse(king.hasMoved());
		assertFalse(rightRook.hasMoved());
		assertTrue(board.getSquare(Location.g1).isEmpty());
		assertTrue(board.getSquare(Location.f1).isEmpty());

		rightRook.incrementMovesDone();
		assertTrue(rightRook.hasMoved());

		// List of legal moves for a king when castling is allowed with the right rook
		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.d1, Location.d2, Location.e2, Location.f2, Location.f1));
		List<Location> actualValidMovements = king.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void whiteCastlingNotAllowedWhenKingHasMoved() {
		// Create empty board
		Board board = new ChessBoard();
		Piece king = new King(PlayerColor.WHITE);
		Piece rightRook = new Rook(PlayerColor.WHITE);

		// Place king on e1 and rook on h1
		board.placePiece(king, Location.e1);
		board.placePiece(rightRook, Location.h1);
		assertEquals(king, board.getSquare(Location.e1).getPiece());
		assertEquals(rightRook, board.getSquare(Location.h1).getPiece());

		assertFalse(king.hasMoved());
		assertFalse(rightRook.hasMoved());
		assertTrue(board.getSquare(Location.g1).isEmpty());
		assertTrue(board.getSquare(Location.f1).isEmpty());

		king.incrementMovesDone();
		assertTrue(king.hasMoved());

		// List of legal moves for a king when castling is allowed with the right rook
		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.d1, Location.d2, Location.e2, Location.f2, Location.f1));
		List<Location> actualValidMovements = king.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void whiteCastlingNotAllowedWhenKingInCheck() {
		// Create empty board
		Board board = new ChessBoard();
		Piece king = new King(PlayerColor.WHITE);
		Piece rightRook = new Rook(PlayerColor.WHITE);
		Piece blackBishop = new Bishop(PlayerColor.BLACK);

		// Place king on e1 and rook on h1
		board.placePiece(king, Location.e1);
		board.placePiece(rightRook, Location.h1);
		board.placePiece(blackBishop, Location.c3);
		assertEquals(king, board.getSquare(Location.e1).getPiece());
		assertEquals(rightRook, board.getSquare(Location.h1).getPiece());
		assertEquals(blackBishop, board.getSquare(Location.c3).getPiece());

		assertFalse(king.hasMoved());
		assertFalse(rightRook.hasMoved());
		assertTrue(board.getSquare(Location.g1).isEmpty());
		assertTrue(board.getSquare(Location.f1).isEmpty());

		// List of legal moves for a king when castling is allowed with the right rook
		Set<Location> expectedValidMovements = new HashSet<>(
				Arrays.asList(Location.d1, Location.e2, Location.f2, Location.f1));
		List<Location> actualValidMovements = king.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void whiteCastlingNotAllowedWhenInBetweenSquaresNotSafe() {
		// Create empty board
		Board board = new ChessBoard();
		Piece king = new King(PlayerColor.WHITE);
		Piece rightRook = new Rook(PlayerColor.WHITE);
		Piece blackBishop = new Bishop(PlayerColor.BLACK);

		// Place king on e1 and rook on h1
		board.placePiece(king, Location.e1);
		board.placePiece(rightRook, Location.h1);
		board.placePiece(blackBishop, Location.e3);
		assertEquals(king, board.getSquare(Location.e1).getPiece());
		assertEquals(rightRook, board.getSquare(Location.h1).getPiece());
		assertEquals(blackBishop, board.getSquare(Location.e3).getPiece());

		assertFalse(king.hasMoved());
		assertFalse(rightRook.hasMoved());
		assertTrue(board.getSquare(Location.g1).isEmpty());
		assertTrue(board.getSquare(Location.f1).isEmpty());

		// List of legal moves for a king when castling is allowed with the right rook
		Set<Location> expectedValidMovements = new HashSet<>(Arrays.asList(Location.d1, Location.e2, Location.f1));
		List<Location> actualValidMovements = king.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}
}
