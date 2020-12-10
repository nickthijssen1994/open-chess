package nl.nickthijssen.chesscore.game.pieces;

import nl.nickthijssen.chesscore.PieceType;
import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PawnTest {

	@Test
	void getLegalWhitePawnMovesOnEmptyBoardTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece pawn = new Pawn(PlayerColor.WHITE);
		// Place white pawn on c2
		board.placePiece(pawn, Location.c2);
		assertEquals(pawn, board.getSquare(Location.c2).getPiece());

		// List of legal moves for white a pawn on c2 who hasn't moved
		Set<Location> expectedValidMovements = new HashSet<>(Arrays.asList(Location.c3, Location.c4));
		List<Location> actualValidMovements = pawn.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		// Move pawn
		pawn.incrementMovesDone();
		assertTrue(pawn.hasMoved());

		// List of legal moves for a white pawn on c2 on an empty board who has moved
		expectedValidMovements = new HashSet<>(Arrays.asList(Location.c3));
		actualValidMovements = pawn.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void getLegalBlackPawnMovesOnEmptyBoardTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece pawn = new Pawn(PlayerColor.BLACK);
		// Place black pawn on c7
		board.placePiece(pawn, Location.c7);
		assertEquals(pawn, board.getSquare(Location.c7).getPiece());

		// List of legal moves for a black pawn on c7 who hasn't moved
		Set<Location> expectedValidMovements = new HashSet<>(Arrays.asList(Location.c6, Location.c5));
		List<Location> actualValidMovements = pawn.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		// Move pawn
		pawn.incrementMovesDone();
		assertTrue(pawn.hasMoved());

		// List of legal moves for a black pawn on c7 on an empty board who has moved
		expectedValidMovements = new HashSet<>(Arrays.asList(Location.c6));
		actualValidMovements = pawn.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void whitePawnMovesWithOwnPiecesInFrontTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece pawn = new Pawn(PlayerColor.WHITE);
		Piece pawn1 = new Pawn(PlayerColor.WHITE);
		Piece pawn2 = new Pawn(PlayerColor.WHITE);

		// Place white pawns on c2, b3 and c4
		board.placePiece(pawn, Location.c2);
		board.placePiece(pawn1, Location.b3);
		board.placePiece(pawn2, Location.c4);
		assertEquals(pawn, board.getSquare(Location.c2).getPiece());
		assertEquals(pawn1, board.getSquare(Location.b3).getPiece());
		assertEquals(pawn2, board.getSquare(Location.c4).getPiece());

		// List of legal moves for a pawn on c2 who hasn't moved with own pieces in front
		Set<Location> expectedValidMovements = new HashSet<>(Arrays.asList(Location.c3));
		List<Location> actualValidMovements = pawn.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void whitePawnMovesWithOwnPiecesInFrontFirstMoveTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece pawn = new Pawn(PlayerColor.WHITE);
		Piece pawn1 = new Pawn(PlayerColor.WHITE);
		Piece pawn2 = new Pawn(PlayerColor.WHITE);

		// Place white pawns on c2, b3 and c3
		board.placePiece(pawn, Location.c2);
		board.placePiece(pawn1, Location.b3);
		board.placePiece(pawn2, Location.c3);
		assertEquals(pawn, board.getSquare(Location.c2).getPiece());
		assertEquals(pawn1, board.getSquare(Location.b3).getPiece());
		assertEquals(pawn2, board.getSquare(Location.c3).getPiece());

		// List of legal moves for a pawn on c2 who hasn't moved with own pieces in front
		List<Location> actualValidMovements = pawn.getLegalMoves(board);
		assertEquals(0, actualValidMovements.size());
	}

	@Test
	void whitePawnMovesWithEnemyPiecesInFrontFirstMoveTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece pawn = new Pawn(PlayerColor.WHITE);
		Piece pawn1 = new Pawn(PlayerColor.BLACK);
		Piece pawn2 = new Pawn(PlayerColor.BLACK);
		Piece pawn3 = new Pawn(PlayerColor.BLACK);

		// Place white pawn on c2 and black pawns on b3, c3 and c4
		board.placePiece(pawn, Location.c2);
		board.placePiece(pawn1, Location.b3);
		board.placePiece(pawn2, Location.c3);
		board.placePiece(pawn3, Location.c4);
		assertEquals(pawn, board.getSquare(Location.c2).getPiece());
		assertEquals(pawn1, board.getSquare(Location.b3).getPiece());
		assertEquals(pawn2, board.getSquare(Location.c3).getPiece());
		assertEquals(pawn3, board.getSquare(Location.c4).getPiece());

		// List of legal moves for a white pawn on c2 who hasn't moved with enemy pieces in front
		Set<Location> expectedValidMovements = new HashSet<>(Arrays.asList(Location.b3));
		List<Location> actualValidMovements = pawn.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));
	}

	@Test
	void whitePawnPromotionTest() {
		// Create empty board
		Board board = new ChessBoard();
		Piece whitePawn = new Pawn(PlayerColor.WHITE);
		Piece blackPawn = new Pawn(PlayerColor.BLACK);
		// Place white pawn on c7 and black pawn on f7
		board.placePiece(whitePawn, Location.c7);
		assertEquals(whitePawn, board.getSquare(Location.c7).getPiece());
		board.placePiece(blackPawn, Location.f7);
		assertEquals(blackPawn, board.getSquare(Location.f7).getPiece());

		// List of legal moves for white a pawn on c7
		Set<Location> expectedValidMovements = new HashSet<>(Arrays.asList(Location.c8));
		List<Location> actualValidMovements = whitePawn.getLegalMoves(board);
		assertEquals(expectedValidMovements.size(), actualValidMovements.size());
		assertTrue(actualValidMovements.containsAll(expectedValidMovements));

		assertFalse(whitePawn.canBePromoted());
		assertFalse(blackPawn.canBePromoted());

		assertEquals(PlayerColor.WHITE, board.getPlayerWhoHasTurn());
		board.movePiece(PlayerColor.WHITE, Location.c7, Location.c8);
		assertEquals(whitePawn, board.getSquare(Location.c8).getPiece());
		assertTrue(board.getSquare(Location.c7).isEmpty());

		assertNull(board.movePiece(PlayerColor.BLACK, Location.f7, Location.f6));

		assertTrue(whitePawn.canBePromoted());
		assertEquals(PlayerColor.WHITE, board.getPlayerWhoHasTurn());

		assertFalse(board.promotePawn(PlayerColor.WHITE, PieceType.KING));
		assertFalse(board.promotePawn(PlayerColor.WHITE, PieceType.PAWN));
		assertFalse(board.promotePawn(PlayerColor.BLACK, PieceType.ROOK));
		assertFalse(board.promotePawn(PlayerColor.BLACK, PieceType.KING));
		assertFalse(board.promotePawn(PlayerColor.BLACK, PieceType.PAWN));

		assertTrue(board.promotePawn(PlayerColor.WHITE, PieceType.QUEEN));
		assertFalse(board.getSquare(Location.c8).isEmpty());
		assertTrue(board.getSquare(Location.c8).hasPlayerPiece(PlayerColor.WHITE));
		assertEquals(PieceType.QUEEN, board.getSquare(Location.c8).getPiece().getPieceType());
		assertFalse(board.getSquare(Location.c8).getPiece().canBePromoted());
		assertEquals(PlayerColor.BLACK, board.getPlayerWhoHasTurn());

		assertNotNull(board.movePiece(PlayerColor.BLACK, Location.f7, Location.f6));
		assertEquals(PlayerColor.WHITE, board.getPlayerWhoHasTurn());
	}
}
