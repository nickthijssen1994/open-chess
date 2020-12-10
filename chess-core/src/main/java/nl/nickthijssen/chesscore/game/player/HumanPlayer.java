package nl.nickthijssen.chesscore.game.player;

import nl.nickthijssen.chesscore.PlayerColor;

public class HumanPlayer extends BasePlayer {

	private String sessionId;

	public HumanPlayer(String sessionId, PlayerColor color, String username) {
		super(color, username);
		this.sessionId = sessionId;
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public boolean isComputer() {
		return false;
	}
}
