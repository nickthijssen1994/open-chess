package nl.nickthijssen.chesscore.game.board;

import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.pieces.Piece;

public class Square {

	private Location location;
	private Piece piece;

	public Square(int column, int row) {
		this.location = Location.fromCoordinates(column, row);
		this.piece = null;
	}

	public int getColumn() {
		return location.getColumn();
	}

	public int getRow() {
		return location.getRow();
	}

	public Piece getPiece() {
		return piece;
	}

	public void placePiece(Piece piece) {
		if (piece != null) {
			piece.setPosition(this);
		}
		this.piece = piece;
	}

	public void removePiece() {
		if (piece != null) {
			piece.setPosition(null);
		}
		this.piece = null;
	}

	public boolean isEmpty() {
		return piece == null;
	}

	public boolean hasPlayerPiece(PlayerColor playerColor) {
		if (piece == null) {
			return false;
		}
		else {
			return piece.getPlayerColor() == playerColor;
		}
	}

	public boolean hasOpponentPiece(PlayerColor playerColor) {

		if (piece == null) {
			return false;
		}
		else {
			return piece.getPlayerColor() != playerColor;
		}

	}

	public Location getLocation() {
		return location;
	}
}
