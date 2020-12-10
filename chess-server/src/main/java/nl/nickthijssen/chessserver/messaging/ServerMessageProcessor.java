package nl.nickthijssen.chessserver.messaging;

import nl.nickthijssen.chesscore.GameManager;
import nl.nickthijssen.websocketshared.messaging.MessageProcessor;

public interface ServerMessageProcessor extends MessageProcessor {

	void registerGameManager(GameManager gameManager);
}
