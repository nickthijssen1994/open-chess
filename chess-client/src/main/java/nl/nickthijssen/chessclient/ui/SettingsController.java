package nl.nickthijssen.chessclient.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import nl.nickthijssen.chessclient.utilities.*;
import nl.nickthijssen.chesscore.GameClient;

public class SettingsController extends BaseController {

	@FXML
	private ToggleGroup theme;
	@FXML
	private ToggleButton btnDarkTheme;
	@FXML
	private ToggleButton btnLightTheme;
	@FXML
	private TextField txtPlayerName;
	@FXML
	private TextField txtAuthServerURL;
	@FXML
	private TextField txtGameServerURL;
	@FXML
	private Button btnConfirmSettings;

	public SettingsController(GameClient gameClient) {
		super(gameClient);
	}

	@FXML
	public void initialize() {
		txtPlayerName.setText(GlobalClientSettings.getInstance().getPlayerName());
		txtAuthServerURL.setText(GlobalClientSettings.getInstance().getAuthenticationServerURL());
		txtGameServerURL.setText(GlobalClientSettings.getInstance().getGameServerURL());

		btnDarkTheme.setToggleGroup(theme);
		btnLightTheme.setToggleGroup(theme);
		btnDarkTheme.setSelected(GlobalClientSettings.getInstance().getDarkThemeEnabled());
		btnLightTheme.setSelected(!GlobalClientSettings.getInstance().getDarkThemeEnabled());
	}

	@FXML
	public void btnConfirmSettingsClicked(MouseEvent mouseEvent) {
		Platform.runLater(() -> {
			GlobalClientSettings.getInstance().setPlayerName(txtPlayerName.getText());
			GlobalClientSettings.getInstance().setAuthenticationServerURL(txtAuthServerURL.getText());
			GlobalClientSettings.getInstance().setGameServerURL(txtGameServerURL.getText());
		});
		loadScene(ClientScene.MAIN_MENU);
	}

	@FXML
	public void btnDarkThemeClicked(MouseEvent mouseEvent) {
		GlobalClientSettings.getInstance().setDarkThemeEnabled(true);
		btnDarkTheme.setSelected(GlobalClientSettings.getInstance().getDarkThemeEnabled());
		btnLightTheme.setSelected(!GlobalClientSettings.getInstance().getDarkThemeEnabled());
		SceneLoader.getInstance().selectTheme(GlobalClientSettings.getInstance().getDarkThemeEnabled());
	}

	@FXML
	public void btnLightThemeClicked(MouseEvent mouseEvent) {
		GlobalClientSettings.getInstance().setDarkThemeEnabled(false);
		btnDarkTheme.setSelected(GlobalClientSettings.getInstance().getDarkThemeEnabled());
		btnLightTheme.setSelected(!GlobalClientSettings.getInstance().getDarkThemeEnabled());
		SceneLoader.getInstance().selectTheme(GlobalClientSettings.getInstance().getDarkThemeEnabled());
	}
}
