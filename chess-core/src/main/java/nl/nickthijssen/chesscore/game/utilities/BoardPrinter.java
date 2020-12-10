package nl.nickthijssen.chesscore.game.utilities;

import nl.nickthijssen.chesscore.game.board.*;

public class BoardPrinter {

	private BoardPrinter() {
		throw new IllegalStateException("Utility Class");
	}

	public static void printBoard(Board board) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int row = 7; row >= 0; row--) {
			for (int column = 0; column < 8; column++) {
				Square square = board.getSquare(Location.fromCoordinates(column, row));
				stringBuilder.append("[");
				stringBuilder.append(SquareColorHelper.getSquareColor(column,
																	  row).getAbbreviation() + ChessNotationHelper.arrayCoordinateToAlgebraicNotation(
						column, row));
				stringBuilder.append("-");
				if (square.isEmpty()) {
					stringBuilder.append("  ");
				}
				else {
					stringBuilder.append(
							square.getPiece().getPlayerColor().getAbbreviation() + square.getPiece().getPieceType().getAbbreviation());
				}
				stringBuilder.append("]");
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder.toString());
	}

	public static void printSquares(Board board) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int row = 7; row >= 0; row--) {
			for (int column = 0; column < 8; column++) {
				Square square = board.getSquare(Location.fromCoordinates(column, row));
				stringBuilder.append("[" + SquareColorHelper.getSquareColor(square.getColumn(),
																			square.getRow()).getAbbreviation() + ChessNotationHelper.arrayCoordinateToAlgebraicNotation(
						square.getColumn(), square.getRow()) + "]");
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder.toString());
	}

	public static void printPieces(Board board) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int row = 7; row >= 0; row--) {
			for (int column = 0; column < 8; column++) {
				Square square = board.getSquare(Location.fromCoordinates(column, row));
				stringBuilder.append("[");
				if (square.isEmpty()) {
					stringBuilder.append("  ");
				}
				else {
					stringBuilder.append(
							square.getPiece().getPlayerColor().getAbbreviation() + square.getPiece().getPieceType().getAbbreviation());
				}
				stringBuilder.append("]");
			}
			stringBuilder.append("\n");
		}
		System.out.println(stringBuilder.toString());
	}
}
