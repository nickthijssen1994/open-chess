package nl.nickthijssen.chesscore;

public enum PlayerColor {
	WHITE("w", "White"),
	BLACK("b", "Black");

	private String abbreviation;
	private String name;

	PlayerColor(String abbreviation, String name) {
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
