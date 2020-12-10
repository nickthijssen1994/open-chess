package nl.nickthijssen.chessclient.game.websocket.handlers;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerBase;
import nl.nickthijssen.websocketshared.messaging.messages.results.GeneralResultMessage;

public class GeneralResultMessageHandler extends MessageHandlerBase<GeneralResultMessage> {

	private GameClient gameClient;

	public GeneralResultMessageHandler(GameClient client) {
		this.gameClient = client;
	}

	@Override
	public void handleMessageInternal(GeneralResultMessage message, String sessionId) {
		gameClient.processGeneralMessage(message.getMessage());
	}
}
