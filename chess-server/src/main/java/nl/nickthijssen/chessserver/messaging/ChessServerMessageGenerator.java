package nl.nickthijssen.chessserver.messaging;

import nl.nickthijssen.chesscore.*;
import nl.nickthijssen.chessserver.WebsocketServerEndpoint;
import nl.nickthijssen.websocketshared.messaging.messages.results.*;

public class ChessServerMessageGenerator implements ServerMessageGenerator {

	private final WebsocketServerEndpoint websocketServerEndpoint;

	public ChessServerMessageGenerator(WebsocketServerEndpoint websocketServerEndpoint) {
		this.websocketServerEndpoint = websocketServerEndpoint;
	}

	@Override
	public void broadcastMessage(String message) {
		GeneralResultMessage resultMessage = new GeneralResultMessage(message);
		websocketServerEndpoint.broadcast(resultMessage);
	}

	@Override
	public void sendGeneralMessage(String sessionId, String message) {
		GeneralResultMessage resultMessage = new GeneralResultMessage(message);
		websocketServerEndpoint.sendToClient(sessionId, resultMessage);
	}

	@Override
	public void sendStartGameResult(String sessionId, String playerUsername, PlayerColor playerColor,
									String opponentUsername) {
		StartGameResultMessage resultMessage = new StartGameResultMessage(playerUsername, playerColor,
																		  opponentUsername);
		websocketServerEndpoint.sendToClient(sessionId, resultMessage);
	}

	@Override
	public void sendPlayerAddedResult(String sessionId, String playerUsername, PlayerColor playerColor) {
		PlayerAddedResultMessage resultMessage = new PlayerAddedResultMessage(playerUsername, playerColor);
		websocketServerEndpoint.sendToClient(sessionId, resultMessage);
	}

	@Override
	public void sendOpponentAddedResult(String sessionId, String opponentUsername, PlayerColor opponentColor) {
		OpponentAddedResultMessage resultMessage = new OpponentAddedResultMessage(opponentUsername, opponentColor);
		websocketServerEndpoint.sendToClient(sessionId, resultMessage);
	}

	@Override
	public void sendPieceLocation(String sessionId, PieceLocation pieceLocation) {
		PieceLocationResultMessage resultMessage = new PieceLocationResultMessage(pieceLocation.getColumn(),
																				  pieceLocation.getRow(),
																				  pieceLocation.getType(),
																				  pieceLocation.getColor());
		websocketServerEndpoint.sendToClient(sessionId, resultMessage);
	}

	@Override
	public void sendValidMovementLocation(String sessionId, SquareLocation squareLocation) {
		ValidMovementLocationResultMessage resultMessage = new ValidMovementLocationResultMessage(
				squareLocation.getColumn(), squareLocation.getRow());
		websocketServerEndpoint.sendToClient(sessionId, resultMessage);
	}

	@Override
	public void sendMoveDoneResult(String sessionId, String message, String time, String player, String piece,
								   String capture, String origin, String target, int originColumn, int originRow,
								   int targetColumn, int targetRow) {
		MoveDoneResultMessage resultMessage = new MoveDoneResultMessage(message, time, player, piece, capture, origin,
																		target, originColumn, originRow, targetColumn,
																		targetRow);
		websocketServerEndpoint.sendToClient(sessionId, resultMessage);
	}

	@Override
	public void sendMoveUndoneResult(String sessionId, String message, String time, String player, String piece,
									 String capture, String origin, String target, int originColumn, int originRow,
									 int targetColumn, int targetRow) {
		MoveUndoneResultMessage resultMessage = new MoveUndoneResultMessage(message, time, player, piece, capture,
																			origin, target, originColumn, originRow,
																			targetColumn, targetRow);
		websocketServerEndpoint.sendToClient(sessionId, resultMessage);
	}

	@Override
	public void sendTurnSwitchedMessage(String sessionId, String username, PlayerColor playerColor) {
		TurnSwitchedResultMessage resultMessage = new TurnSwitchedResultMessage(playerColor, username);
		websocketServerEndpoint.sendToClient(sessionId, resultMessage);
	}
}
