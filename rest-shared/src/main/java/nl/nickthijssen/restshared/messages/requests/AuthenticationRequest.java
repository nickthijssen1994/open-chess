package nl.nickthijssen.restshared.messages.requests;

import nl.nickthijssen.restshared.messages.BaseRequest;

public class AuthenticationRequest extends BaseRequest {

	private String token;

	public AuthenticationRequest() {
	}

	public AuthenticationRequest(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
