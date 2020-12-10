package nl.nickthijssen.websocketshared.messaging;

public interface MessageHandlerFactory {

	MessageHandler getHandler(String simpleType, Object game);
}
