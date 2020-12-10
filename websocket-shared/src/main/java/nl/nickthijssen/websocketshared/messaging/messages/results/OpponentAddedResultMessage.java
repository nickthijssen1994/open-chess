package nl.nickthijssen.websocketshared.messaging.messages.results;

import nl.nickthijssen.chesscore.PlayerColor;

public class OpponentAddedResultMessage {

	private String opponentUsername;
	private PlayerColor opponentColor;

	public OpponentAddedResultMessage(String opponentUsername, PlayerColor opponentColor) {
		this.opponentUsername = opponentUsername;
		this.opponentColor = opponentColor;
	}

	public String getOpponentUsername() {
		return opponentUsername;
	}

	public void setOpponentUsername(String opponentUsername) {
		this.opponentUsername = opponentUsername;
	}

	public PlayerColor getOpponentColor() {
		return opponentColor;
	}

	public void setOpponentColor(PlayerColor opponentColor) {
		this.opponentColor = opponentColor;
	}
}
