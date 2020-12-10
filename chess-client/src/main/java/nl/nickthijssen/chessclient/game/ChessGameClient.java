package nl.nickthijssen.chessclient.game;

import nl.nickthijssen.chessclient.game.websocket.*;
import nl.nickthijssen.chessclient.game.websocket.handlers.ChessClientMessageHandlerFactory;
import nl.nickthijssen.chessclient.utilities.GlobalClientSettings;
import nl.nickthijssen.chesscore.*;
import nl.nickthijssen.websocketshared.messaging.MessageHandlerFactory;

public class ChessGameClient implements GameClient {

	private PlayerColor playerColor;
	private ClientMessageGenerator messageGenerator;
	private GameClientUI gameClientUI;

	public ChessGameClient() {
		WebsocketClientEndpoint socket = WebsocketClient.getInstance();
		messageGenerator = new ChessClientMessageGenerator(socket);
		MessageHandlerFactory factory = new ChessClientMessageHandlerFactory();
		ClientMessageProcessor processor = new ChessClientMessageProcessor(factory);
		socket.setMessageProcessor(processor);
		processor.registerGameClient(this);
		socket.start();
	}

	@Override
	public void registerGameClientUI(GameClientUI gameClientUI) {
		this.gameClientUI = gameClientUI;
	}

	@Override
	public void notifyStartGame() {
		messageGenerator.sendStartGameRequest(GlobalClientSettings.getInstance().getSinglePlayerModeEnabled(),
											  GlobalClientSettings.getInstance().getPlayerName());
	}

	@Override
	public void notifyPieceSelected(int originColumn, int originRow) {
		messageGenerator.sendPieceSelectedRequest(originColumn, originRow);
	}

	@Override
	public void notifyTargetSelected(int targetColumn, int targetRow) {
		messageGenerator.sendTargetSelectedRequest(targetColumn, targetRow);
	}

	@Override
	public void notifyMakeMove(int originColumn, int originRow, int targetColumn, int targetRow) {
		messageGenerator.sendMakeMoveRequest(originColumn, originRow, targetColumn, targetRow);
	}

	@Override
	public void notifyPlayerReady() {
		messageGenerator.sendPlayerReadyRequest();
	}

	@Override
	public void notifyPauseGame() {
		messageGenerator.sendPauseGameRequest();
	}

	@Override
	public void notifyUndoMove() {
		messageGenerator.sendUndoLastMoveRequest();
	}

	@Override
	public void notifyResign() {
		messageGenerator.sendResignRequest();
	}

	@Override
	public void processGeneralMessage(String message) {
		gameClientUI.processGeneralMessage(message);
	}

	@Override
	public void processStartGame(String playerUsername, PlayerColor playerColor, String opponentUsername) {
		this.playerColor = playerColor;
		if (playerColor == PlayerColor.WHITE) {
			gameClientUI.processWhitePlayerName(playerUsername);
			gameClientUI.processBlackPlayerName(opponentUsername);
		}
		else {
			gameClientUI.processWhitePlayerName(opponentUsername);
			gameClientUI.processBlackPlayerName(playerUsername);
		}
	}

	@Override
	public void processLoadValidMovementLocation(SquareLocation squareLocation) {
		gameClientUI.processLoadValidMovementLocation(squareLocation);
	}

	@Override
	public void processLoadPieceLocation(PieceLocation pieceLocation) {
		gameClientUI.processLoadPieceLocation(pieceLocation);
	}

	@Override
	public void processPlayerAdded(String playerUsername, PlayerColor playerColor) {
		this.playerColor = playerColor;
		if (playerColor == PlayerColor.WHITE) {
			gameClientUI.processWhitePlayerName(playerUsername);
		}
		else {
			gameClientUI.processBlackPlayerName(playerUsername);
		}
	}

	@Override
	public void processOpponentAdded(String opponentUsername, PlayerColor opponentColor) {
		if (opponentColor == PlayerColor.WHITE) {
			gameClientUI.processWhitePlayerName(opponentUsername);
		}
		else {
			gameClientUI.processBlackPlayerName(opponentUsername);
		}
	}

	@Override
	public void processTurnSwitched(String username, PlayerColor playerColor) {
		if (playerColor == this.playerColor) {
			gameClientUI.processPlayerWhoHasTurnMessage("Your Turn");
		}
		else {
			gameClientUI.processPlayerWhoHasTurnMessage(username + "'s Turn");
		}
	}

	@Override
	public void processMoveDoneMessage(String message) {
		gameClientUI.processMoveDoneMessage(message);
	}

	@Override
	public void processMoveDone(String time, String player, String piece, String capture, String origin,
								String target) {
		gameClientUI.processMoveDone(time, player, piece, capture, origin, target);
	}

	@Override
	public void processMoveUndoneMessage(String message) {
		gameClientUI.processMoveUndoneMessage(message);
	}

	@Override
	public void processUndoPieceMoved(int originColumn, int originRow, int targetColumn, int targetRow) {
		gameClientUI.processPieceMoved(targetColumn, targetRow, originColumn, originRow);
		gameClientUI.processMoveUndone();
	}

	@Override
	public void processPieceMoved(int originColumn, int originRow, int targetColumn, int targetRow) {
		gameClientUI.processPieceMoved(originColumn, originRow, targetColumn, targetRow);
	}
}
