package nl.nickthijssen.websocketshared.messaging.messages.results;

import nl.nickthijssen.chesscore.PlayerColor;

public class StartGameResultMessage {

	private String playerUsername;
	private PlayerColor playerColor;
	private String opponentUsername;

	public StartGameResultMessage(String playerUsername, PlayerColor playerColor, String opponentUsername) {
		this.playerUsername = playerUsername;
		this.playerColor = playerColor;
		this.opponentUsername = opponentUsername;
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

	public String getOpponentUsername() {
		return opponentUsername;
	}

	public void setOpponentUsername(String opponentUsername) {
		this.opponentUsername = opponentUsername;
	}
}
