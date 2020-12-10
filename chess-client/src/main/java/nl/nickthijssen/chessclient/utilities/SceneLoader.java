package nl.nickthijssen.chessclient.utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import nl.nickthijssen.chesscore.GameClient;

import java.io.IOException;
import java.lang.reflect.Constructor;

public class SceneLoader {

	public static final String DARK_THEME_CSS = "/css/modena-dark.css";
	private static SceneLoader instance = null;
	private GameClient gameClient;
	private Stage currentStage;

	public static SceneLoader getInstance() {
		if (instance == null) {
			instance = new SceneLoader();
		}
		return instance;
	}

	public void loadScene(ClientScene clientScene, Stage primaryStage, GameClient gameClient) {
		if (this.gameClient == null) {
			this.gameClient = gameClient;
		}
		if (this.currentStage == null) {
			this.currentStage = primaryStage;
		}
		loadScene(clientScene);
	}

	public void loadScene(ClientScene clientScene) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + clientScene.getFxmlFile() + ".fxml"));

		loader.setControllerFactory((Class<?> type) -> {
			try {
				for (Constructor<?> c : type.getConstructors()) {
					if (c.getParameterCount() == 1 && c.getParameterTypes()[0] == GameClient.class) {
						return c.newInstance(gameClient);
					}
				}
				return type.getDeclaredConstructor().newInstance();
			}
			catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		});

		Parent root = null;
		try {
			root = loader.load();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		currentStage.setTitle(clientScene.getTitle());
		currentStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/ui/chess-icon.png")));
		if (GlobalClientSettings.getInstance().getDarkThemeEnabled()) {
			scene.getStylesheets().add(this.getClass().getResource(DARK_THEME_CSS).toExternalForm());
		}
		currentStage.setScene(scene);
		currentStage.show();
		currentStage.setResizable(false);
		currentStage.centerOnScreen();
	}

	public void selectTheme(boolean darkThemeEnabled) {
		if (darkThemeEnabled) {
			currentStage.getScene().getStylesheets().add(this.getClass().getResource(DARK_THEME_CSS).toExternalForm());
		}
		else {
			currentStage.getScene().getStylesheets().remove(
					this.getClass().getResource(DARK_THEME_CSS).toExternalForm());
		}
	}
}
