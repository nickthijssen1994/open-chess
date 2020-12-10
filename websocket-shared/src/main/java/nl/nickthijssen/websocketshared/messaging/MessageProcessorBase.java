package nl.nickthijssen.websocketshared.messaging;

public abstract class MessageProcessorBase {

	private MessageHandlerFactory messageHandlerFactory;

	public MessageProcessorBase(MessageHandlerFactory messageHandlerFactory) {
		this.messageHandlerFactory = messageHandlerFactory;
	}

	public MessageHandlerFactory getMessageHandlerFactory() {
		return messageHandlerFactory;
	}
}
