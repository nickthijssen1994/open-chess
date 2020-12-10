package nl.nickthijssen.chessclient.utilities;

public class PieceImageLocation {

	private int column;
	private int row;
	private PieceImage abbreviation;

	public PieceImageLocation(int column, int row, PieceImage abbreviation) {
		this.column = column;
		this.row = row;
		this.abbreviation = abbreviation;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public PieceImage getAbbreviation() {
		return abbreviation;
	}
}
