package nl.nickthijssen.chesscore;

public enum SquareColor {
	WHITE("W", "White"),
	BLACK("B", "Black");

	private String abbreviation;
	private String name;

	SquareColor(String abbreviation, String name) {
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
