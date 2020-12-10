package nl.nickthijssen.chesscore.game;

import nl.nickthijssen.chesscore.*;
import nl.nickthijssen.chesscore.game.board.*;
import nl.nickthijssen.chesscore.game.move.Move;
import nl.nickthijssen.chesscore.game.pieces.Piece;
import nl.nickthijssen.chesscore.game.player.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class ChessGame implements Game {

	private final Map<PlayerColor, Player> players = new HashMap<>();
	private final List<Move> doneMoves = new ArrayList<>();
	private GameManager gameManager;
	private Board board;
	private Player playerWhoHasWon;
	private Player playerWhoHasLost;
	private boolean singlePlayer = false;
	private boolean hasStarted = false;
	private boolean hasEnded = false;
	private Date startTime;
	private Date endTime;

	public ChessGame(GameManager gameManager) {
		this.gameManager = gameManager;
		board = new ChessBoard();
		board.setPiecesToStartPosition();
	}

	@Override
	public void addPlayer(String sessionId, boolean singlePlayer, String username) {
		this.singlePlayer = singlePlayer;
		if (players.size() == 0) {
			Player humanPlayer;
			if (username.isBlank()) {
				humanPlayer = new HumanPlayer(sessionId, PlayerColor.WHITE, "Player One");
			}
			else {
				humanPlayer = new HumanPlayer(sessionId, PlayerColor.WHITE, username);
			}
			players.put(PlayerColor.WHITE, humanPlayer);
			gameManager.processPlayerAdded(humanPlayer);
			gameManager.processPlayerTurn(getPlayers(), getPlayerWhoHasTurn());
			gameManager.processPieceLocations(humanPlayer, board.getPieceLocations());

			if (singlePlayer) {
				Player computerPlayer = new ComputerPlayer(PlayerColor.BLACK, "Computer");
				computerPlayer.setReady(true);
				players.put(computerPlayer.getColor(), computerPlayer);
				gameManager.processOpponentAdded(humanPlayer, computerPlayer);
			}
			else {
				gameManager.processShowMessageToPlayer(humanPlayer, "Waiting For Other Player To Join");
			}
		}
		else if (players.size() == 1) {
			Player humanPlayer;
			if (username.isBlank()) {
				humanPlayer = new HumanPlayer(sessionId, PlayerColor.BLACK, "Player Two");
			}
			else {
				humanPlayer = new HumanPlayer(sessionId, PlayerColor.BLACK, username);
			}
			players.put(PlayerColor.BLACK, humanPlayer);
			gameManager.processPlayerAdded(humanPlayer);
			gameManager.processOpponentAdded(humanPlayer, getOtherPlayer(humanPlayer));
			gameManager.processOpponentAdded(getOtherPlayer(humanPlayer), humanPlayer);
			gameManager.processPlayerTurn(getPlayers(), getPlayerWhoHasTurn());
			gameManager.processPieceLocations(humanPlayer, board.getPieceLocations());
			gameManager.processShowMessageToPlayers(getPlayers(), "Waiting For Players To Be Ready");
		}
		else {
			// Show max number of players message
		}
	}

	@Override
	public void setPlayerReady(PlayerColor color) {
		if (hasStarted) {
			gameManager.processShowMessageToPlayer(players.get(color), "Game Has Already Started");
		}
		else if (players.size() != 2) {
			gameManager.processShowMessageToPlayer(players.get(color), "Waiting For Other Player To Join");
		}
		else {
			players.get(color).setReady(true);
			if (!singlePlayer) {
				gameManager.processShowMessageToPlayer(players.get(getOtherPlayerColor(color)),
													   players.get(color).getUsername() + " Is " + "Ready To Start");
			}

			boolean allPlayersReady = true;
			for (Player player : players.values()) {
				if (!player.isReady()) {
					allPlayersReady = false;
					break;
				}
			}

			if (allPlayersReady) {
				startGame();
			}
		}
	}

	@Override
	public void selectPiece(PlayerColor color, int originColumn, int originRow) {
		// Check if game has ended
		if (hasEnded) {
			gameManager.processShowMessageToPlayer(players.get(color), "Game Has Ended");
		}
		// Check if game is started
		else if (!hasStarted) {
			gameManager.processShowMessageToPlayer(players.get(color), "Game Hasn't Started Yet");
		}
		// Check if player has turn
		else if (board.getPlayerWhoHasTurn() != color) {
			gameManager.processShowMessageToPlayer(players.get(color), "Not Your Turn");
		}
		else {
			Square selectedOriginSquare = board.getSquare(Location.fromCoordinates(originColumn, originRow));

			// Check if there is a piece on the selected square
			if (selectedOriginSquare.isEmpty()) {
				gameManager.processShowMessageToPlayer(players.get(color), "No Piece Selected");
			}
			else {
				Piece piece = selectedOriginSquare.getPiece();

				// Check if the selected piece belongs to player
				if (piece.getPlayerColor() != color) {
					gameManager.processShowMessageToPlayer(players.get(color), "Not Your Piece");
				}
				else {
					gameManager.processSquareLocations(players.get(color), piece.getPossibleMoves(board));
				}
			}
		}
	}

	@Override
	public void selectTarget(PlayerColor color, int targetColumn, int targetRow) {

	}

	@Override
	public void makeMove(PlayerColor color, int originColumn, int originRow, int targetColumn, int targetRow) {
		// Check if game has ended
		if (hasEnded) {
			gameManager.processShowMessageToPlayer(players.get(color), "Game Has Ended");
		}
		// Check if game is started
		else if (!hasStarted) {
			gameManager.processShowMessageToPlayer(players.get(color), "Game Hasn't Started Yet");
		}
		// Check if player has turn
		else if (board.getPlayerWhoHasTurn() != color) {
			gameManager.processShowMessageToPlayer(players.get(color), "Not Your Turn");
		}
		// Check if there is a piece on the selected square
		else if (board.getSquare(Location.fromCoordinates(originColumn, originRow)).isEmpty()) {
			gameManager.processShowMessageToPlayer(players.get(color), "No Piece Selected");
		}
		// Check if piece belongs to player
		else if (board.getSquare(Location.fromCoordinates(originColumn, originRow)).hasOpponentPiece(color)) {
			gameManager.processShowMessageToPlayer(players.get(color), "Not Your Piece");
		}
		// Check if target is a valid move
		else if (!board.getSquare(Location.fromCoordinates(originColumn, originRow)).getPiece().getLegalMoves(
				board).contains(Location.fromCoordinates(targetColumn, targetRow))) {
			gameManager.processShowMessageToPlayer(players.get(color), "Piece Cannot Be Moved Here");
		}
		else {
			Move playerMove = board.movePiece(color, Location.fromCoordinates(originColumn, originRow),
											  Location.fromCoordinates(targetColumn, targetRow));
			if (playerMove != null) {
				playerMove.setId(doneMoves.size() + 1);
				doneMoves.add(playerMove);
				gameManager.processMoveDone(getPlayers(), playerMove);
				switchTurns();
			}
			else {
				gameManager.processShowMessageToPlayer(players.get(color), "Piece Cannot Be Moved Here");
			}
		}
	}

	@Override
	public void undoLastMove(PlayerColor color) {

		if (hasStarted) {
			if (!doneMoves.isEmpty()) {
				Move undoneMove = doneMoves.get(doneMoves.size() - 1);
				if (undoneMove.getPlayerColor() == color && board.getPlayerWhoHasTurn() != color) {
					undoneMove.undo();
					gameManager.processMoveUndone(getPlayers(), undoneMove);
					doneMoves.remove(undoneMove);
					switchTurns();
				}
				else {
					gameManager.processShowMessageToPlayer(players.get(color), "Move can not be undone");
				}
			}
			else {
				gameManager.processShowMessageToPlayer(players.get(color), "No moves done yet");
			}
		}
		else {
			gameManager.processShowMessageToPlayer(players.get(color), "Game Hasn't Started Yet");
		}
	}

	@Override
	public void pauseGame(PlayerColor color) {

	}

	@Override
	public void restartGame(PlayerColor color) {
		doneMoves.clear();
		board.setPiecesToStartPosition();
	}

	@Override
	public void resign(PlayerColor color) {
		hasEnded = true;
		endTime = new Date();
		players.get(color).resign();
		playerWhoHasLost = players.get(color);
		playerWhoHasWon = players.get(getOtherPlayerColor(color));
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String date = formatter.format(endTime);
		gameManager.processShowMessageToPlayers(getPlayers(),
												playerWhoHasLost.getUsername() + " Has Resigned. Game " + "Ended At " + date);
	}

	@Override
	public int getNumberOfPlayers() {
		return players.size();
	}

	@Override
	public boolean getHasStarted() {
		return hasStarted;
	}

	@Override
	public boolean getHasEnded() {
		return hasEnded;
	}

	@Override
	public Player getOtherPlayer(Player player) {
		for (Player addedPlayer : players.values()) {
			if (!addedPlayer.getColor().equals(player.getColor())) {
				return addedPlayer;
			}
		}
		return null;
	}

	@Override
	public List<Player> getPlayers() {
		List<Player> playerList = new ArrayList<>();
		for (Player value : players.values()) {
			if (!value.isComputer()) {
				playerList.add(value);
			}
		}
		return playerList;
	}

	@Override
	public List<Move> getDoneMoves() {
		return doneMoves;
	}

	@Override
	public List<PieceLocation> getPieceLocations() {
		return board.getPieceLocations();
	}

	private void startGame() {
		hasStarted = true;
		startTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String date = formatter.format(startTime);
		gameManager.processShowMessageToPlayers(getPlayers(), "Game Started At " + date);
		gameManager.processStartGame(this);
	}

	public PlayerColor getOtherPlayerColor(PlayerColor color) {
		if (color == PlayerColor.WHITE) {
			return PlayerColor.BLACK;
		}
		else {
			return PlayerColor.WHITE;
		}
	}

	private Player getPlayerWhoHasTurn() {
		return players.get(board.getPlayerWhoHasTurn());
	}

	private void switchTurns() {
		gameManager.processPlayerTurn(getPlayers(), getPlayerWhoHasTurn());
		checkIfCheckmate();
		// Let Computer make move when in single player
		if (hasStarted && !hasEnded && singlePlayer && board.getPlayerWhoHasTurn() == PlayerColor.BLACK) {
			Move computerMove = ComputerMoveStrategy.makeComputerMove(doneMoves.size() + 1, board, PlayerColor.BLACK);
			board.movePiece(computerMove.getPlayerColor(), computerMove.getOriginSquare().getLocation(),
							computerMove.getTargetSquare().getLocation());
			doneMoves.add(computerMove);
			gameManager.processMoveDone(getPlayers(), computerMove);
			checkIfCheckmate();
		}
	}

	private void checkIfCheckmate() {
		if (board.isKingInCheckmate(board.getPlayerWhoHasTurn())) {
			hasEnded = true;
			endTime = new Date();
			playerWhoHasLost = players.get(board.getPlayerWhoNotHasTurn());
			playerWhoHasWon = getOtherPlayer(playerWhoHasLost);
			gameManager.processShowMessageToPlayers(getPlayers(), playerWhoHasWon.getUsername() + " Has Won The Game");
		}
	}
}
