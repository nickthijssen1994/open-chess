package nl.nickthijssen.chesscore.game.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChessNotationHelperTest {

	@Test
	void coordinateToAlgebraicNotationTest() {
		assertEquals("a", ChessNotationHelper.arrayColumnToAlgebraicNotation(0));
		assertEquals("b", ChessNotationHelper.arrayColumnToAlgebraicNotation(1));
		assertEquals("c", ChessNotationHelper.arrayColumnToAlgebraicNotation(2));
		assertEquals("d", ChessNotationHelper.arrayColumnToAlgebraicNotation(3));
		assertEquals("e", ChessNotationHelper.arrayColumnToAlgebraicNotation(4));
		assertEquals("f", ChessNotationHelper.arrayColumnToAlgebraicNotation(5));
		assertEquals("g", ChessNotationHelper.arrayColumnToAlgebraicNotation(6));
		assertEquals("h", ChessNotationHelper.arrayColumnToAlgebraicNotation(7));

		assertEquals("1", ChessNotationHelper.arrayRowToAlgebraicNotation(0));
		assertEquals("2", ChessNotationHelper.arrayRowToAlgebraicNotation(1));
		assertEquals("3", ChessNotationHelper.arrayRowToAlgebraicNotation(2));
		assertEquals("4", ChessNotationHelper.arrayRowToAlgebraicNotation(3));
		assertEquals("5", ChessNotationHelper.arrayRowToAlgebraicNotation(4));
		assertEquals("6", ChessNotationHelper.arrayRowToAlgebraicNotation(5));
		assertEquals("7", ChessNotationHelper.arrayRowToAlgebraicNotation(6));
		assertEquals("8", ChessNotationHelper.arrayRowToAlgebraicNotation(7));
	}
}
