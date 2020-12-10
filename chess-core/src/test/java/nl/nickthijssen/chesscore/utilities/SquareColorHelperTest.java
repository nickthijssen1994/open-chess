package nl.nickthijssen.chesscore.utilities;

import nl.nickthijssen.chesscore.SquareColor;
import nl.nickthijssen.chesscore.game.utilities.SquareColorHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SquareColorHelperTest {

	@Test
	void getSquareColor() {
		assertEquals(SquareColor.BLACK, SquareColorHelper.getSquareColor(0, 0));
		assertNotEquals(SquareColor.WHITE, SquareColorHelper.getSquareColor(0, 0));
	}
}
