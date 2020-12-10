package nl.nickthijssen.chessclient.utilities;

public class GlobalClientSettings {

	/**
	 * Thread safe class holding global setting for client
	 */
	private static GlobalClientSettings instance = null;

	private String playerName = "";
	private boolean singlePlayerMode = true;
	private String authenticationToken = "";
	private String authenticationServerURL = "http://localhost:8090";
	private String gameServerURL = "ws://localhost:8095/chess/";
	private boolean darkThemeEnabled = true;

	private GlobalClientSettings() {
	}

	public static synchronized GlobalClientSettings getInstance() {
		if (instance == null) {
			instance = new GlobalClientSettings();
		}
		return instance;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getAuthenticationToken() {
		return authenticationToken;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	public String getAuthenticationServerURL() {
		return authenticationServerURL;
	}

	public void setAuthenticationServerURL(String authenticationServerURL) {
		this.authenticationServerURL = authenticationServerURL;
	}

	public String getGameServerURL() {
		return gameServerURL;
	}

	public void setGameServerURL(String gameServerURL) {
		this.gameServerURL = gameServerURL;
	}

	public boolean getDarkThemeEnabled() {
		return darkThemeEnabled;
	}

	public void setDarkThemeEnabled(boolean darkThemeEnabled) {
		this.darkThemeEnabled = darkThemeEnabled;
	}

	public boolean getSinglePlayerModeEnabled() {
		return singlePlayerMode;
	}

	public void setSinglePlayerModeEnabled(boolean singlePlayerMode) {
		this.singlePlayerMode = singlePlayerMode;
	}
}
