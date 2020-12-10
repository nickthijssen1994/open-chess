package nl.nickthijssen.chessclient;

import javafx.application.Application;
import javafx.stage.Stage;
import nl.nickthijssen.chessclient.game.ChessGameClient;
import nl.nickthijssen.chessclient.utilities.ClientScene;
import nl.nickthijssen.chessclient.utilities.SceneLoader;

public class ChessClient extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		SceneLoader.getInstance().loadScene(ClientScene.MAIN_MENU, primaryStage, new ChessGameClient());
	}
}
