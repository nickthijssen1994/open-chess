package nl.nickthijssen.websocketshared.messaging;

public interface MessageProcessor {

	void processMessage(String sessionId, String type, String data);

	void handleDisconnect(String sessionId);
}
