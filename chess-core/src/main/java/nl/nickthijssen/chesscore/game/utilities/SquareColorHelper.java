package nl.nickthijssen.chesscore.game.utilities;

import nl.nickthijssen.chesscore.SquareColor;

public class SquareColorHelper {

	private SquareColorHelper() {
		throw new IllegalStateException("Utility Class");
	}

	/*
		Get the square color based on its position.
		If column + row is even, the square should be black.
		If column + row is odd, the square should be white.
	 */

	public static SquareColor getSquareColor(int column, int row) {
		if (column < 0 || column > 7 || row < 0 || row > 7) {
			throw new IllegalArgumentException("Coordinate is outside chess board");
		}
		else if ((column + row) % 2 == 0) {
			return SquareColor.BLACK;
		}
		else {
			return SquareColor.WHITE;
		}
	}
}
