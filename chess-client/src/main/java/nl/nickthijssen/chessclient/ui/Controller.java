package nl.nickthijssen.chessclient.ui;

import javafx.scene.control.Alert;
import nl.nickthijssen.chessclient.utilities.ClientScene;
import nl.nickthijssen.chesscore.GameClient;

/**
 * Interface with methods that all controllers can use
 */
public interface Controller {

	GameClient getGameClient();

	/**
	 * @param clientScene
	 */
	void loadScene(ClientScene clientScene);

	/**
	 * @param alertHeaderText
	 * @param alertContentText
	 */
	void showAlert(String alertHeaderText, String alertContentText);

	/**
	 * @param alertType
	 * @param alertHeaderText
	 * @param alertContentText
	 */
	void showAlert(Alert.AlertType alertType, String alertHeaderText, String alertContentText);
}
