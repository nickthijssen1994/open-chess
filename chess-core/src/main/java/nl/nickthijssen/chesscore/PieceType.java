package nl.nickthijssen.chesscore;

public enum PieceType {
	BISHOP("B", "Bishop"),
	KING("K", "King"),
	KNIGHT("N", "Knight"),
	PAWN("P", "Pawn"),
	QUEEN("Q", "Queen"),
	ROOK("R", "Rook");

	private String abbreviation;
	private String name;

	PieceType(String abbreviation, String name) {
		this.abbreviation = abbreviation;
		this.name = name;
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public String getName() {
		return this.name;
	}
}
