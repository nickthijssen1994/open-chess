package nl.nickthijssen.chessserver;

import nl.nickthijssen.websocketshared.Websocket;

import java.util.List;

public interface WebsocketServerEndpoint extends Websocket {

	void broadcast(Object messageObject);

	void sendToClient(String sessionID, Object message);

	void sendToClients(List<String> sessionIds, Object messageObject);
}
