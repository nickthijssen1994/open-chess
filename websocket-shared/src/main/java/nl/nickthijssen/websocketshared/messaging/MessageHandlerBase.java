package nl.nickthijssen.websocketshared.messaging;

import nl.nickthijssen.commons.serialization.SerializationProvider;
import nl.nickthijssen.commons.serialization.Serializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MessageHandlerBase<T> implements MessageHandler {

	public void handleMessage(String data, String sessionId) {
		Serializer<String> serializer = SerializationProvider.getSerializer();
		Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		T message = serializer.deserialize(data, type);
		handleMessageInternal(message, sessionId);
	}

	public abstract void handleMessageInternal(T message, String sessionId);
}
