package nl.nickthijssen.websocketshared.messaging.messages.results;

public class GeneralResultMessage {

	private String message;

	public GeneralResultMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
