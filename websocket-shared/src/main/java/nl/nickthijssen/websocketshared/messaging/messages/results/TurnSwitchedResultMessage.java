package nl.nickthijssen.websocketshared.messaging.messages.results;

import nl.nickthijssen.chesscore.PlayerColor;

public class TurnSwitchedResultMessage {

	private PlayerColor playerColor;
	private String username;

	public TurnSwitchedResultMessage(PlayerColor playerColor, String username) {
		this.playerColor = playerColor;
		this.username = username;
	}

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(PlayerColor playerColor) {
		this.playerColor = playerColor;
	}

	public String getUsername() {
		return username;
	}
}
