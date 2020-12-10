package nl.nickthijssen.chessserver;

import nl.nickthijssen.chesscore.*;
import nl.nickthijssen.chesscore.game.ChessGame;
import nl.nickthijssen.chesscore.game.move.Move;
import nl.nickthijssen.chessserver.messaging.ServerMessageGenerator;
import nl.nickthijssen.restclient.AuthenticationRestClient;

import java.util.ArrayList;
import java.util.List;

public class ChessServerGameManager implements GameManager {

	private final AuthenticationRestClient authenticationRestClient;
	private final ServerMessageGenerator messageGenerator;

	private final List<Game> games = new ArrayList<>();

	public ChessServerGameManager(AuthenticationRestClient authenticationRestClient,
								  ServerMessageGenerator messageGenerator) {
		this.authenticationRestClient = authenticationRestClient;
		this.messageGenerator = messageGenerator;
		games.add(new ChessGame(this));
	}

	@Override
	public void handleStartGameRequest(String sessionId, boolean singlePlayer, String username) {
		boolean allGamesFull = true;
		for (Game game : games) {
			if (game.getNumberOfPlayers() < 2) {
				allGamesFull = false;
				game.addPlayer(sessionId, singlePlayer, username);
				break;
			}
		}
		if (allGamesFull) {
			Game newGame = new ChessGame(this);
			games.add(newGame);
			newGame.addPlayer(sessionId, singlePlayer, username);
		}
	}

	@Override
	public void handlePlayerReadyRequest(String sessionId) {
		getGameFromSessionId(sessionId).setPlayerReady(getPlayerFromSessionId(sessionId).getColor());
	}

	@Override
	public void handlePieceLocationsRequest(String sessionId) {
		for (PieceLocation pieceLocation : getGameFromSessionId(sessionId).getPieceLocations()) {
			messageGenerator.sendPieceLocation(sessionId, pieceLocation);
		}
	}

	@Override
	public void handlePieceSelectedRequest(String sessionId, int originColumn, int originRow) {
		getGameFromSessionId(sessionId).selectPiece(getPlayerFromSessionId(sessionId).getColor(), originColumn,
													originRow);
	}

	@Override
	public void handleTargetSelectedRequest(String sessionId, int targetColumn, int targetRow) {
		getGameFromSessionId(sessionId).selectTarget(getPlayerFromSessionId(sessionId).getColor(), targetColumn,
													 targetRow);
	}

	@Override
	public void handleMakeMoveRequest(String sessionId, int originColumn, int originRow, int targetColumn,
									  int targetRow) {
		getGameFromSessionId(sessionId).makeMove(getPlayerFromSessionId(sessionId).getColor(), originColumn, originRow,
												 targetColumn, targetRow);
	}

	@Override
	public void handleUndoLastMoveRequest(String sessionId) {
		getGameFromSessionId(sessionId).undoLastMove(getPlayerFromSessionId(sessionId).getColor());
	}

	@Override
	public void handlePauseGameRequest(String sessionId) {
		getGameFromSessionId(sessionId).pauseGame(getPlayerFromSessionId(sessionId).getColor());
	}

	@Override
	public void handleResignRequest(String sessionId) {
		getGameFromSessionId(sessionId).resign(getPlayerFromSessionId(sessionId).getColor());
	}

	@Override
	public void processStartGame(Game game) {
		for (Player player : game.getPlayers()) {
			messageGenerator.sendStartGameResult(player.getSessionId(), player.getUsername(), player.getColor(),
												 game.getOtherPlayer(player).getUsername());
		}
	}

	@Override
	public void processPlayerAdded(Player player) {
		messageGenerator.sendPlayerAddedResult(player.getSessionId(), player.getUsername(), player.getColor());
	}

	@Override
	public void processOpponentAdded(Player player, Player opponent) {
		messageGenerator.sendOpponentAddedResult(player.getSessionId(), opponent.getUsername(), opponent.getColor());
	}

	@Override
	public void processPlayerTurn(List<Player> players, Player playerWhoHasTurn) {
		for (Player player : players) {
			messageGenerator.sendTurnSwitchedMessage(player.getSessionId(), playerWhoHasTurn.getUsername(),
													 playerWhoHasTurn.getColor());
		}
	}

	@Override
	public void processSquareLocations(Player player, List<SquareLocation> squareLocations) {
		for (SquareLocation squareLocation : squareLocations) {
			messageGenerator.sendValidMovementLocation(player.getSessionId(), squareLocation);
		}
	}

	@Override
	public void processPieceLocations(Player player, List<PieceLocation> pieceLocations) {
		for (PieceLocation pieceLocation : pieceLocations) {
			messageGenerator.sendPieceLocation(player.getSessionId(), pieceLocation);
		}
	}

	@Override
	public void processMoveDone(List<Player> players, Move doneMove) {
		for (Player player : players) {
			messageGenerator.sendMoveDoneResult(player.getSessionId(), doneMove.executedMessage(),
												doneMove.getTimeString(), doneMove.getPlayerString(),
												doneMove.getPieceString(), doneMove.getCaptureString(),
												doneMove.getOriginString(), doneMove.getTargetString(),
												doneMove.getOriginSquare().getColumn(),
												doneMove.getOriginSquare().getRow(),
												doneMove.getTargetSquare().getColumn(),
												doneMove.getTargetSquare().getRow());
		}
	}

	@Override
	public void processMoveUndone(List<Player> players, Move undoneMove) {
		for (Player player : players) {
			messageGenerator.sendMoveUndoneResult(player.getSessionId(), undoneMove.undoneMessage(),
												  undoneMove.getTimeString(), undoneMove.getPlayerString(),
												  undoneMove.getPieceString(), undoneMove.getCaptureString(),
												  undoneMove.getOriginString(), undoneMove.getTargetString(),
												  undoneMove.getOriginSquare().getColumn(),
												  undoneMove.getOriginSquare().getRow(),
												  undoneMove.getTargetSquare().getColumn(),
												  undoneMove.getTargetSquare().getRow());
		}
	}

	@Override
	public void processShowMessageToPlayers(List<Player> players, String message) {
		for (Player player : players) {
			messageGenerator.sendGeneralMessage(player.getSessionId(), message);
		}
	}

	@Override
	public void processShowMessageToPlayer(Player player, String message) {
		messageGenerator.sendGeneralMessage(player.getSessionId(), message);
	}

	@Override
	public void processClientDisconnect(String sessionId) {

	}

	private Player getPlayerFromSessionId(String sessionId) {
		for (Game game : games) {
			for (Player player : game.getPlayers()) {
				if (player.getSessionId().equals(sessionId)) {
					return player;
				}
			}
		}
		return null;
	}

	private Game getGameFromSessionId(String sessionId) {
		for (Game game : games) {
			for (Player player : game.getPlayers()) {
				if (player.getSessionId().equals(sessionId)) {
					return game;
				}
			}
		}
		return null;
	}
}
