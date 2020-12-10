package nl.nickthijssen.chessclient.utilities;

public enum ClientScene {
	MAIN_MENU("Main Menu", "main-menu"),
	SETTINGS("Settings", "settings"),
	ACCOUNT("Account", "account"),
	IN_GAME("Game", "in-game"),
	GITHUB("GitHub", "github"),
	START_GAME("Start Game", "start-game");

	private String title;

	private String fxmlFile;

	ClientScene(String title, String fxmlFile) {
		this.title = title;
		this.fxmlFile = fxmlFile;
	}

	public String getTitle() {
		return this.title;
	}

	public String getFxmlFile() {
		return fxmlFile;
	}
}
