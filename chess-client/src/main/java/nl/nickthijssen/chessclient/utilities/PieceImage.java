package nl.nickthijssen.chessclient.utilities;

import java.util.HashMap;
import java.util.Map;

public enum PieceImage {
	BLACK_BISHOP("bB", "black-bishop"),
	BLACK_KING("bK", "black-king"),
	BLACK_KNIGHT("bN", "black-knight"),
	BLACK_PAWN("bP", "black-pawn"),
	BLACK_QUEEN("bQ", "black-queen"),
	BLACK_ROOK("bR", "black-rook"),
	WHITE_BISHOP("wB", "white-bishop"),
	WHITE_KING("wK", "white-king"),
	WHITE_KNIGHT("wN", "white-knight"),
	WHITE_PAWN("wP", "white-pawn"),
	WHITE_QUEEN("wQ", "white-queen"),
	WHITE_ROOK("wR", "white-rook");

	private static final Map<String, PieceImage> lookup = new HashMap<>();

	static {
		for (PieceImage d : PieceImage.values()) {
			lookup.put(d.getAbbreviation(), d);
		}
	}

	private String abbreviation;
	private String imageFileName;

	PieceImage(String abbreviation, String imageFileName) {
		this.abbreviation = abbreviation;
		this.imageFileName = imageFileName;
	}

	public static PieceImage get(String abbreviation) {
		return lookup.get(abbreviation);
	}

	public String getAbbreviation() {
		return this.abbreviation;
	}

	public String getImageFileName() {
		return this.imageFileName;
	}
}
