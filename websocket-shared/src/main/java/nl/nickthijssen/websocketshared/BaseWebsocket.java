package nl.nickthijssen.websocketshared;

import nl.nickthijssen.commons.serialization.SerializationProvider;
import nl.nickthijssen.commons.serialization.Serializer;
import nl.nickthijssen.websocketshared.messaging.EncapsulatingMessageGenerator;
import nl.nickthijssen.websocketshared.messaging.MessageGenerator;

public abstract class BaseWebsocket {

	private MessageGenerator encapsulatingMessageGenerator;

	public BaseWebsocket() {
		encapsulatingMessageGenerator = new EncapsulatingMessageGenerator();
	}

	public abstract void start();

	public abstract void stop();

	public MessageGenerator getEncapsulatingMessageGenerator() {
		return encapsulatingMessageGenerator;
	}

	public Serializer<String> getSerializer() {
		return SerializationProvider.getSerializer();
	}
}
