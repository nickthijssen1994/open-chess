package nl.nickthijssen.restshared.messages.requests;

import nl.nickthijssen.restshared.messages.BaseRequest;

public class PrincipalRequest extends BaseRequest {

	private String principal;

	public PrincipalRequest() {
	}

	public PrincipalRequest(String principal) {
		this.principal = principal;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
}
