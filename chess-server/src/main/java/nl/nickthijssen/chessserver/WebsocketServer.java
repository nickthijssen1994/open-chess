package nl.nickthijssen.chessserver;

import nl.nickthijssen.websocketshared.BaseWebsocket;
import nl.nickthijssen.websocketshared.messaging.EncapsulatingMessage;
import nl.nickthijssen.websocketshared.messaging.MessageProcessor;

import javax.inject.Singleton;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Singleton
@ServerEndpoint(value = "/chess/")
public class WebsocketServer extends BaseWebsocket implements WebsocketServerEndpoint {

	public static final String SESSION_ID = "[WebSocket Session ID] : ";
	private static final List<Session> SESSIONS = new ArrayList<>();
	private MessageProcessor messageProcessor;

	public MessageProcessor getMessageProcessor() {
		return messageProcessor;
	}

	@Override
	public void setMessageProcessor(MessageProcessor processor) {
		this.messageProcessor = processor;
	}

	@Override
	public void start() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void stop() {
		throw new UnsupportedOperationException();
	}

	@OnOpen
	public void onConnect(Session session) {
		System.out.println(SESSION_ID + session.getId() + " [Socket Connected]");
		SESSIONS.add(session);
	}

	@OnMessage
	public void onMessage(Session session, String messageString) {
		System.out.println(SESSION_ID + session.getId() + " [Message Received From Client] : " + messageString);
		EncapsulatingMessage message = getSerializer().deserialize(messageString, EncapsulatingMessage.class);
		getMessageProcessor().processMessage(session.getId(), message.getType(), message.getContent());
	}

	@OnError
	public void onError(Session session, Throwable cause) {
		System.out.println(cause.getMessage());
	}

	@OnClose
	public void onClose(Session session, CloseReason reason) {
		System.out.println(SESSION_ID + session.getId() + " [Socket Closed] : " + reason);
		getMessageProcessor().handleDisconnect(session.getId());
		SESSIONS.remove(session);
	}

	@Override
	public void broadcast(Object messageObject) {
		for (Session session : SESSIONS) {
			sendToClient(session.getId(), messageObject);
		}
	}

	@Override
	public void sendToClient(String sessionID, Object messageObject) {
		String messageString = getEncapsulatingMessageGenerator().generateMessageString(messageObject);
		try {
			getSessionFromId(sessionID).getBasicRemote().sendText(messageString);
			System.out.println(SESSION_ID + sessionID + " [Message Send To Client] : " + messageString);
		}
		catch (NullPointerException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendToClients(List<String> sessionIds, Object messageObject) {
		for (String sessionId : sessionIds) {
			Session session = getSessionFromId(sessionId);
			sendToClient(session.getId(), messageObject);
		}
	}

	private Session getSessionFromId(String sessionId) {
		for (Session session : SESSIONS) {
			if (session.getId().equals(sessionId)) {
				return session;
			}
		}
		return null;
	}
}
