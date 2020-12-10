package nl.nickthijssen.chesscore;

public enum PromotionType {
	BISHOP("B", "Bishop"),
	KNIGHT("N", "Knight"),
	QUEEN("Q", "Queen"),
	ROOK("R", "Rook");

	private String abbreviation;
	private String name;

	PromotionType(String abbreviation, String name) {
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
