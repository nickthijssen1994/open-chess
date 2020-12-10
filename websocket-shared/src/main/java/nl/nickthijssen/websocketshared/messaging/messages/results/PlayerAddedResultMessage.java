package nl.nickthijssen.websocketshared.messaging.messages.results;

import nl.nickthijssen.chesscore.PlayerColor;

public class PlayerAddedResultMessage {

	private String playerUsername;
	private PlayerColor playerColor;

	public PlayerAddedResultMessage(String playerUsername, PlayerColor playerColor) {
		this.playerUsername = playerUsername;
		this.playerColor = playerColor;
	}

	public String getPlayerUsername() {
		return playerUsername;
	}

	public void setPlayerUsername(String playerUsername) {
		this.playerUsername = playerUsername;
	}

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(PlayerColor playerColor) {
		this.playerColor = playerColor;
	}
}
