package nl.nickthijssen.websocketshared.messaging.messages.requests;

public class TargetSelectedRequestMessage {

	private int targetColumn;
	private int targetRow;

	public TargetSelectedRequestMessage(int targetColumn, int targetRow) {

		this.targetColumn = targetColumn;
		this.targetRow = targetRow;
	}

	public int getTargetColumn() {
		return targetColumn;
	}

	public int getTargetRow() {
		return targetRow;
	}
}
