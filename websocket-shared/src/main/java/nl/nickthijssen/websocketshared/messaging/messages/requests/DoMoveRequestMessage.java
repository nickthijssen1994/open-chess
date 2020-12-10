package nl.nickthijssen.websocketshared.messaging.messages.requests;

public class DoMoveRequestMessage {

	private int originColumn;
	private int originRow;
	private int targetColumn;
	private int targetRow;

	public DoMoveRequestMessage(int originColumn, int originRow, int targetColumn, int targetRow) {
		this.originColumn = originColumn;
		this.originRow = originRow;
		this.targetColumn = targetColumn;
		this.targetRow = targetRow;
	}

	public int getOriginColumn() {
		return originColumn;
	}

	public int getOriginRow() {
		return originRow;
	}

	public int getTargetColumn() {
		return targetColumn;
	}

	public int getTargetRow() {
		return targetRow;
	}
}
