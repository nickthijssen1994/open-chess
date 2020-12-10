package nl.nickthijssen.websocketshared.messaging.messages.results;

public class ValidMovementLocationResultMessage {

	private int column;
	private int row;

	public ValidMovementLocationResultMessage(int column, int row) {
		this.column = column;
		this.row = row;
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
}
