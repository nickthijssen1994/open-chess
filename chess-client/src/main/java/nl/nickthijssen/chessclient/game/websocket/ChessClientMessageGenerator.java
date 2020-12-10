package nl.nickthijssen.chessclient.game.websocket;

import nl.nickthijssen.websocketshared.messaging.messages.requests.*;

public class ChessClientMessageGenerator implements ClientMessageGenerator {

	private WebsocketClientEndpoint websocketClientEndpoint;

	public ChessClientMessageGenerator(WebsocketClientEndpoint websocketClientEndpoint) {
		this.websocketClientEndpoint = websocketClientEndpoint;
	}

	@Override
	public void sendStartGameRequest(boolean singlePlayer, String username) {
		websocketClientEndpoint.sendToServer(new StartGameRequestMessage(singlePlayer, username));
	}

	@Override
	public void sendPlayerReadyRequest() {
		websocketClientEndpoint.sendToServer(new PlayerReadyRequestMessage());
	}

	@Override
	public void sendPieceSelectedRequest(int originColumn, int originRow) {
		websocketClientEndpoint.sendToServer(new PieceSelectedRequestMessage(originColumn, originRow));
	}

	@Override
	public void sendTargetSelectedRequest(int targetColumn, int targetRow) {
		websocketClientEndpoint.sendToServer(new TargetSelectedRequestMessage(targetColumn, targetRow));
	}

	@Override
	public void sendMakeMoveRequest(int originColumn, int originRow, int targetColumn, int targetRow) {
		websocketClientEndpoint.sendToServer(
				new DoMoveRequestMessage(originColumn, originRow, targetColumn, targetRow));
	}

	@Override
	public void sendUndoLastMoveRequest() {
		websocketClientEndpoint.sendToServer(new UndoMoveRequestMessage());
	}

	@Override
	public void sendPauseGameRequest() {
		websocketClientEndpoint.sendToServer(new PauseGameRequestMessage());
	}

	@Override
	public void sendResignRequest() {
		websocketClientEndpoint.sendToServer(new ResignRequestMessage());
	}
}
