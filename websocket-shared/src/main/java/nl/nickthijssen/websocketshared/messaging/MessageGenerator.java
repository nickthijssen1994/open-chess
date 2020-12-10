package nl.nickthijssen.websocketshared.messaging;

public interface MessageGenerator {

	EncapsulatingMessage generateMessage(Object content);

	String generateMessageString(Object content);
}
