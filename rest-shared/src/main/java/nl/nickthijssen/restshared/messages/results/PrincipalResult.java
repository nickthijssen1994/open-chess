package nl.nickthijssen.restshared.messages.results;

import nl.nickthijssen.restshared.messages.BaseResult;

public class PrincipalResult extends BaseResult {

	private String principal;

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
}
