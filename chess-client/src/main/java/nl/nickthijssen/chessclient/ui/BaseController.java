package nl.nickthijssen.chessclient.ui;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import nl.nickthijssen.chessclient.utilities.ClientScene;
import nl.nickthijssen.chessclient.utilities.SceneLoader;
import nl.nickthijssen.chesscore.GameClient;

public abstract class BaseController implements Controller {

	private GameClient gameClient;

	public BaseController(GameClient gameClient) {
		this.gameClient = gameClient;
	}

	public GameClient getGameClient() {
		return gameClient;
	}

	public void loadScene(ClientScene clientScene) {
		SceneLoader.getInstance().loadScene(clientScene);
	}

	public void showAlert(final String alertHeaderText, final String alertContentText) {
		showAlert(Alert.AlertType.INFORMATION, alertHeaderText, alertContentText);
	}

	public void showAlert(final Alert.AlertType alertType, final String alertHeaderText,
						  final String alertContentText) {
		Platform.runLater(() -> {
			Alert alert = new Alert(alertType);
			alert.setTitle("Chess");
			alert.setHeaderText(alertHeaderText);
			alert.setContentText(alertContentText);
			alert.showAndWait();
		});
	}
}
