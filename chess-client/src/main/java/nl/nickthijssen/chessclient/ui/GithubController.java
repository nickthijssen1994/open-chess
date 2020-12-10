package nl.nickthijssen.chessclient.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import nl.nickthijssen.chessclient.utilities.ClientScene;
import nl.nickthijssen.chessclient.utilities.GlobalClientSettings;
import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.restclient.AuthenticationRestClient;

public class GithubController extends BaseController {

	private AuthenticationRestClient authenticationRestClient;

	@FXML
	private WebView wvGitHub;
	@FXML
	private Button btnLoginWithGitHub;
	@FXML
	private Button btnBackToMenu;

	public GithubController(GameClient gameClient) {
		super(gameClient);
	}

	@FXML
	public void initialize() {
		authenticationRestClient = new AuthenticationRestClient(
				GlobalClientSettings.getInstance().getAuthenticationServerURL());
		WebEngine webEngine = wvGitHub.getEngine();
		webEngine.setJavaScriptEnabled(true);
		webEngine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87" +
									   ".0.4280.88 Safari/537.36");
		webEngine.setUserStyleSheetLocation(getClass().getResource("/css/primer.css").toExternalForm());
		webEngine.load("http://localhost:8090");
	}

	@FXML
	public void btnLoginWithGitHubClicked(MouseEvent event) {
		authenticationRestClient.github();
	}

	@FXML
	public void btnBackToMenuClicked(MouseEvent mouseEvent) {
		WebEngine webEngine = wvGitHub.getEngine();
		webEngine.setJavaScriptEnabled(true);
		webEngine.setUserAgent(
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87" + ".0.4280.88 Safari/537.36");
		webEngine.setUserStyleSheetLocation(getClass().getResource("/css/primer.css").toExternalForm());
		webEngine.load("http://localhost:8090/user/oauth");
		String principle = webEngine.getDocument().toString();
		System.out.println(principle);
		loadScene(ClientScene.MAIN_MENU);
	}
}
