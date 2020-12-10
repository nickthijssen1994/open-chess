package nl.nickthijssen.chesscore.game.player;

import nl.nickthijssen.chesscore.PlayerColor;

public class ComputerPlayer extends BasePlayer {

	public ComputerPlayer(PlayerColor color, String username) {
		super(color, username);
	}

	@Override
	public String getSessionId() {
		return null;
	}

	@Override
	public void setSessionId(String sessionId) {

	}

	@Override
	public boolean isComputer() {
		return true;
	}
}
