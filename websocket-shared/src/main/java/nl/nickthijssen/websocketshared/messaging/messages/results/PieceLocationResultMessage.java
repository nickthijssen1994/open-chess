package nl.nickthijssen.websocketshared.messaging.messages.results;

import nl.nickthijssen.chesscore.PieceType;
import nl.nickthijssen.chesscore.PlayerColor;

public class PieceLocationResultMessage {

	private int column;
	private int row;
	private PieceType type;
	private PlayerColor color;

	public PieceLocationResultMessage(int column, int row, PieceType type, PlayerColor color) {
		this.column = column;
		this.row = row;
		this.type = type;
		this.color = color;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public PieceType getType() {
		return type;
	}

	public void setType(PieceType type) {
		this.type = type;
	}

	public PlayerColor getColor() {
		return color;
	}

	public void setColor(PlayerColor color) {
		this.color = color;
	}
}
