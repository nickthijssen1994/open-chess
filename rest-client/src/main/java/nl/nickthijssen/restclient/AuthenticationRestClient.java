package nl.nickthijssen.restclient;

import nl.nickthijssen.restshared.messages.requests.*;
import nl.nickthijssen.restshared.messages.results.*;

public class AuthenticationRestClient extends BaseRestClient {

	private String serverURL;

	public AuthenticationRestClient() {
	}

	public AuthenticationRestClient(String serverURL) {
		this.serverURL = serverURL;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public String github() {
		String query = "/";
		return executeQueryGetHTML(query);
	}

	public PrincipalResult oauth() {
		String query = "/user/oauth";
		return executeQueryGet(query, PrincipalResult.class);
	}

	public LoginResult login(String username, String password) {
		LoginRequest loginRequest = new LoginRequest(username, password);
		String query = "/user/login";
		return executeQueryPost(loginRequest, query, LoginResult.class);
	}

	public RegistrationResult register(String username, String email, String password, String confirmPassword) {
		RegistrationRequest registrationRequest = new RegistrationRequest(username, email, password, confirmPassword);
		String query = "/user/register";
		return executeQueryPost(registrationRequest, query, RegistrationResult.class);
	}

	public AuthenticationResult authenticate(String token) {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest(token);
		String query = "/user/authenticate";
		return executeQueryPost(authenticationRequest, query, AuthenticationResult.class);
	}
}
