package nl.nickthijssen.restshared.messages.results;

import nl.nickthijssen.restshared.messages.BaseResult;

public class RegistrationResult extends BaseResult {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
