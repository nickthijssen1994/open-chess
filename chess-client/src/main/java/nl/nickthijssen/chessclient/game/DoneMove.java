package nl.nickthijssen.chessclient.game;

import javafx.beans.property.SimpleStringProperty;

public class DoneMove {

	private final SimpleStringProperty time;
	private final SimpleStringProperty player;
	private final SimpleStringProperty piece;
	private final SimpleStringProperty capture;
	private final SimpleStringProperty origin;
	private final SimpleStringProperty target;

	public DoneMove(String time, String player, String piece, String capture, String origin, String target) {
		this.time = new SimpleStringProperty(time);
		this.player = new SimpleStringProperty(player);
		this.piece = new SimpleStringProperty(piece);
		this.capture = new SimpleStringProperty(capture);
		this.origin = new SimpleStringProperty(origin);
		this.target = new SimpleStringProperty(target);
	}

	public String getTime() {
		return time.get();
	}

	public void setTime(String time) {
		this.time.set(time);
	}

	public String getPlayer() {
		return player.get();
	}

	public void setPlayer(String player) {
		this.player.set(player);
	}

	public String getPiece() {
		return piece.get();
	}

	public void setPiece(String piece) {
		this.piece.set(piece);
	}

	public String getCapture() {
		return capture.get();
	}

	public void setCapture(String capture) {
		this.capture.set(capture);
	}

	public String getOrigin() {
		return origin.get();
	}

	public void setOrigin(String origin) {
		this.origin.set(origin);
	}

	public String getTarget() {
		return target.get();
	}

	public void setTarget(String target) {
		this.target.set(target);
	}
}
