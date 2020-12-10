package nl.nickthijssen.websocketshared;

import nl.nickthijssen.websocketshared.messaging.MessageProcessor;

public interface Websocket {

	void start();

	void stop();

	void setMessageProcessor(MessageProcessor processor);
}
