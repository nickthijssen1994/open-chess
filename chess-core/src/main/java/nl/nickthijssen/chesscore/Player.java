package nl.nickthijssen.chesscore;

public interface Player {

	String getSessionId();

	void setSessionId(String sessionId);

	boolean isComputer();

	String getUsername();

	PlayerColor getColor();

	boolean isReady();

	void setReady(boolean ready);

	boolean hasResigned();

	void resign();
}
