package nl.nickthijssen.websocketshared.messaging.messages.results;

public class MoveUndoneResultMessage {

	private String message;
	private String time;
	private String player;
	private String piece;
	private String capture;
	private String origin;
	private String target;
	private int originColumn;
	private int originRow;
	private int targetColumn;
	private int targetRow;

	public MoveUndoneResultMessage(String message, String time, String player, String piece, String capture,
								   String origin, String target, int originColumn, int originRow, int targetColumn,
								   int targetRow) {
		this.message = message;
		this.time = time;
		this.player = player;
		this.piece = piece;
		this.capture = capture;
		this.origin = origin;
		this.target = target;
		this.originColumn = originColumn;
		this.originRow = originRow;
		this.targetColumn = targetColumn;
		this.targetRow = targetRow;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public String getCapture() {
		return capture;
	}

	public void setCapture(String capture) {
		this.capture = capture;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public int getOriginColumn() {
		return originColumn;
	}

	public void setOriginColumn(int originColumn) {
		this.originColumn = originColumn;
	}

	public int getOriginRow() {
		return originRow;
	}

	public void setOriginRow(int originRow) {
		this.originRow = originRow;
	}

	public int getTargetColumn() {
		return targetColumn;
	}

	public void setTargetColumn(int targetColumn) {
		this.targetColumn = targetColumn;
	}

	public int getTargetRow() {
		return targetRow;
	}

	public void setTargetRow(int targetRow) {
		this.targetRow = targetRow;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
