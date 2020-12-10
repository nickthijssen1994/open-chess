package nl.nickthijssen.websocketshared.messaging;

public class EncapsulatingMessage {

	private String type;

	private String content;

	public EncapsulatingMessage(String type, String content) {
		this.type = type;
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public String getContent() {
		return content;
	}
}
