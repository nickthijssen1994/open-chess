package nl.nickthijssen.chesscore.game.board;

import nl.nickthijssen.chesscore.PlayerColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

	@Test
	void setupBoardTest() {
		Board board = new ChessBoard();
		board.setPiecesToStartPosition();

		assertEquals(8, board.getLines());
		assertEquals(8, board.getRows());
		assertEquals(64, board.getSquares().size());
		assertEquals(32, board.getActivePieces().size());
		assertEquals(16, board.getActivePlayerPieces(PlayerColor.WHITE).size());
		assertEquals(16, board.getActivePlayerPieces(PlayerColor.BLACK).size());
		assertEquals(0, board.getCapturedPlayerPieces(PlayerColor.WHITE).size());
		assertEquals(0, board.getCapturedPlayerPieces(PlayerColor.BLACK).size());
		assertEquals(0, board.getCapturedPieces().size());
		assertEquals(32, board.getEmptySquares().size());
		assertEquals(PlayerColor.WHITE, board.getPlayerWhoHasTurn());
		assertEquals(PlayerColor.BLACK, board.getPlayerWhoNotHasTurn());
		assertNotEquals(PlayerColor.WHITE, board.getPlayerWhoNotHasTurn());
		assertNotEquals(PlayerColor.BLACK, board.getPlayerWhoHasTurn());
		assertEquals(20, board.getLegalPlayerMoves(PlayerColor.WHITE).size());
		assertEquals(20, board.getLegalPlayerMoves(PlayerColor.BLACK).size());
	}

	@Test
	void movePieceTest() {
		Board board = new ChessBoard();
		board.setPiecesToStartPosition();

		assertEquals(PlayerColor.WHITE, board.getPlayerWhoHasTurn());
		assertNotNull(board.movePiece(PlayerColor.WHITE, Location.b2, Location.b3));
		assertTrue(board.getSquare(Location.b2).isEmpty());
		assertFalse(board.getSquare(Location.b3).isEmpty());
		assertEquals(PlayerColor.BLACK, board.getPlayerWhoHasTurn());

		assertNull(board.movePiece(PlayerColor.WHITE, Location.b3, Location.b4));
		assertEquals(PlayerColor.BLACK, board.getPlayerWhoHasTurn());
		assertNotNull(board.movePiece(PlayerColor.BLACK, Location.b7, Location.b6));
		assertEquals(PlayerColor.WHITE, board.getPlayerWhoHasTurn());
		assertNull(board.movePiece(PlayerColor.BLACK, Location.b6, Location.b5));
		assertEquals(PlayerColor.WHITE, board.getPlayerWhoHasTurn());
	}
}
