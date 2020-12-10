package nl.nickthijssen.websocketshared.messaging;

import nl.nickthijssen.commons.serialization.SerializationProvider;
import nl.nickthijssen.commons.serialization.Serializer;

public class EncapsulatingMessageGenerator implements MessageGenerator {

	Serializer<String> serializer = SerializationProvider.getSerializer();

	public EncapsulatingMessage generateMessage(Object content) {
		String messageForServerJson = serializer.serialize(content);
		String type = content.getClass().getSimpleName();
		return new EncapsulatingMessage(type, messageForServerJson);
	}

	public String generateMessageString(Object content) {
		EncapsulatingMessage message = generateMessage(content);
		return serializer.serialize(message);
	}
}
