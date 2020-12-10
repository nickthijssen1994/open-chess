package nl.nickthijssen.websocketshared.messaging;

public interface MessageHandler {

	void handleMessage(String message, String sessionId);
}
