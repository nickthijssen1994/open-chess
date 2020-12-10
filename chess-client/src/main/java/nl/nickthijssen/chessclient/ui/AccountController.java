package nl.nickthijssen.chessclient.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import nl.nickthijssen.chessclient.utilities.ClientScene;
import nl.nickthijssen.chessclient.utilities.GlobalClientSettings;
import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.restclient.AuthenticationRestClient;

public class AccountController extends BaseController {

	private AuthenticationRestClient authenticationRestClient;

	@FXML
	private TextField txtRegisterUsername;
	@FXML
	private TextField txtRegisterEmail;
	@FXML
	private PasswordField txtRegisterPassword;
	@FXML
	private PasswordField txtRegisterConfirmPassword;
	@FXML
	private TextField txtLoginUsername;
	@FXML
	private PasswordField txtLoginPassword;
	@FXML
	private Button btnRegister;
	@FXML
	private Button btnLogin;
	@FXML
	private Button btnLoginWithGitHub;
	@FXML
	private Button btnCheckAuthentication;
	@FXML
	private Button btnBackToMenu;

	public AccountController(GameClient gameClient) {
		super(gameClient);
	}

	@FXML
	public void initialize() {
		authenticationRestClient = new AuthenticationRestClient(
				GlobalClientSettings.getInstance().getAuthenticationServerURL());
	}

	@FXML
	public void btnRegisterClicked(MouseEvent event) {
		String username = txtRegisterUsername.getText();
		String email = txtRegisterEmail.getText();
		String password = txtRegisterPassword.getText();
		String confirmPassword = txtRegisterConfirmPassword.getText();
		if (username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
			showAlert("Registration", "Missing Info");
		}
		else if (!password.equals(confirmPassword)) {
			showAlert("Registration", "Passwords are not the same");
		}
		else {
			authenticationRestClient.register(username, email, password, confirmPassword);
		}
	}

	@FXML
	public void btnLoginClicked(MouseEvent event) {
		String username = txtLoginUsername.getText();
		String password = txtLoginPassword.getText();
		if (username.isBlank() || password.isBlank()) {
			showAlert("Login", "Missing Info");
		}
		else {
			authenticationRestClient.login(username, password);
		}
	}

	@FXML
	public void btnLoginWithGitHubClicked(MouseEvent event) {
		loadScene(ClientScene.GITHUB);
	}

	@FXML
	public void btnCheckAuthenticationClicked(MouseEvent mouseEvent) {
		System.out.println(authenticationRestClient.oauth());
	}

	@FXML
	public void btnBackToMenuClicked(MouseEvent mouseEvent) {
		loadScene(ClientScene.MAIN_MENU);
	}
}
