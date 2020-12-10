package nl.nickthijssen.restshared.messages.results;

import nl.nickthijssen.restshared.messages.BaseResult;

public class LoginResult extends BaseResult {

	private String username;
	private String token;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
