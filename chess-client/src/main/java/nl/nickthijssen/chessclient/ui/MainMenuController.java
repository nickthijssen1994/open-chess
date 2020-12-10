package nl.nickthijssen.chessclient.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import nl.nickthijssen.chessclient.utilities.ClientScene;
import nl.nickthijssen.chessclient.utilities.GlobalClientSettings;
import nl.nickthijssen.chesscore.GameClient;

public class MainMenuController extends BaseController {

	@FXML
	private Button btnSingleplayer;
	@FXML
	private Button btnMultiplayer;
	@FXML
	private Button buttonAccount;
	@FXML
	private Button buttonSettings;
	@FXML
	private Button buttonQuit;

	public MainMenuController(GameClient gameClient) {
		super(gameClient);
	}

	@FXML
	public void btnSingleplayerClicked(MouseEvent mouseEvent) {
		GlobalClientSettings.getInstance().setSinglePlayerModeEnabled(true);
		loadScene(ClientScene.IN_GAME);
	}

	@FXML
	public void btnMultiplayerClicked(MouseEvent mouseEvent) {
		GlobalClientSettings.getInstance().setSinglePlayerModeEnabled(false);
		loadScene(ClientScene.IN_GAME);
	}

	@FXML
	public void buttonAccountClicked(MouseEvent mouseEvent) {
		loadScene(ClientScene.ACCOUNT);
	}

	@FXML
	public void buttonSettingsClicked(MouseEvent mouseEvent) {
		loadScene(ClientScene.SETTINGS);
	}

	@FXML
	public void buttonQuitClicked(MouseEvent mouseEvent) {
		Platform.exit();
		System.exit(0);
	}
}
