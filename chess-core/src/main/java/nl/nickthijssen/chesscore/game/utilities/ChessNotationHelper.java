package nl.nickthijssen.chesscore.game.utilities;

import nl.nickthijssen.chesscore.game.board.Square;

/**
 * This class contains helper functions to convert coordinates from and to Algebraic notations
 */
public class ChessNotationHelper {

	private ChessNotationHelper() {
		throw new IllegalStateException("Utility Class");
	}

	/**
	 * Convert array coordinate to algebraic notation.
	 *
	 * @param column the column
	 * @param row    the row
	 * @return the coordinate in Algebraic notation
	 */
	public static String arrayCoordinateToAlgebraicNotation(int column, int row) {
		return arrayColumnToAlgebraicNotation(column) + arrayRowToAlgebraicNotation(row);
	}

	/**
	 * Convert array coordinate to Algebraic notation.
	 * On a chessboard, columns are notated with letters from a to h.
	 * To convert a column coordinate to a letter, the ASCII table is used.
	 * * In the ASCII table 'a' is 97 in decimal and 'h' is 104.
	 *
	 * @param column the column
	 * @return the string
	 * @throws IllegalArgumentException if the coordinate is outside of the chessboard
	 */
	public static String arrayColumnToAlgebraicNotation(int column) {
		if (column < 0 || column > 7) {
			throw new IllegalArgumentException("Coordinate is outside chess board");
		}
		else {
			String notation = "";
			return notation + (char) (column + 97);
		}
	}

	/**
	 * Array row to algebraic notation string.
	 *
	 * @param row the row
	 * @return the string
	 * @throws IllegalArgumentException if the coordinate is outside of the chessboard
	 */
	public static String arrayRowToAlgebraicNotation(int row) {
		if (row < 0 || row > 7) {
			throw new IllegalArgumentException("Coordinate is outside chess board");
		}
		else {
			return Integer.toString(row + 1);
		}
	}

	/**
	 * Same method as arrayCoordinateToAlgebraicNotation but uses square as argument
	 *
	 * @param square the square
	 * @return the coordinate in Algebraic notation
	 */
	public static String squareToAlgebraicNotation(Square square) {
		return arrayCoordinateToAlgebraicNotation(square.getColumn(), square.getRow());
	}
}
