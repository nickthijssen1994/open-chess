package nl.nickthijssen.websocketshared.messaging.messages.requests;

public class StartGameRequestMessage {

	private boolean singlePlayer;
	private String username;

	public StartGameRequestMessage(boolean singlePlayer, String username) {
		this.singlePlayer = singlePlayer;
		this.username = username;
	}

	public boolean isSinglePlayer() {
		return singlePlayer;
	}

	public String getUsername() {
		return username;
	}
}
