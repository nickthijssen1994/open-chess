package nl.nickthijssen.chessclient.game.websocket;

import nl.nickthijssen.websocketshared.Websocket;

public interface WebsocketClientEndpoint extends Websocket {

	void sendToServer(Object message);
}
