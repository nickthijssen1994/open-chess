package nl.nickthijssen.chessclient.game.websocket;

import nl.nickthijssen.chesscore.GameClient;
import nl.nickthijssen.websocketshared.messaging.MessageProcessor;

public interface ClientMessageProcessor extends MessageProcessor {

	void registerGameClient(GameClient gameClient);
}
