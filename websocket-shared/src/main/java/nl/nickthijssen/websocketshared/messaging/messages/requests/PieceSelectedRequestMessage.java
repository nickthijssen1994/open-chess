package nl.nickthijssen.websocketshared.messaging.messages.requests;

public class PieceSelectedRequestMessage {

	private int originColumn;
	private int originRow;

	public PieceSelectedRequestMessage(int originColumn, int originRow) {
		this.originColumn = originColumn;
		this.originRow = originRow;
	}

	public int getOriginColumn() {
		return originColumn;
	}

	public int getOriginRow() {
		return originRow;
	}
}
