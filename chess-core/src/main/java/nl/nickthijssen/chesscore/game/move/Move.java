package nl.nickthijssen.chesscore.game.move;

import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.Square;
import nl.nickthijssen.chesscore.game.pieces.Piece;
import nl.nickthijssen.chesscore.game.utilities.ChessNotationHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Move {

	private int id;
	private PlayerColor playerColor;
	private Square originSquare;
	private Square targetSquare;
	private Piece movedPiece;
	private Piece capturedPiece;
	private Date timeStamp;

	public Move(PlayerColor playerColor, Square originSquare, Square targetSquare) {
		this.playerColor = playerColor;
		this.originSquare = originSquare;
		this.targetSquare = targetSquare;
		movedPiece = originSquare.getPiece();
		timeStamp = new Date();
	}

	public Piece getCapturedPiece() {
		return capturedPiece;
	}

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public Square getOriginSquare() {
		return originSquare;
	}

	public Square getTargetSquare() {
		return targetSquare;
	}

	public Piece getMovedPiece() {
		return movedPiece;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getTimeString() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String date = formatter.format(timeStamp);
		return date;
	}

	public String getPlayerString() {
		return playerColor.getName();
	}

	public String getPieceString() {
		return movedPiece.getPieceType().getName();
	}

	public String getCaptureString() {
		if (capturedPiece != null) {
			return capturedPiece.getPieceType().getName();
		}
		return "";
	}

	public String getOriginString() {
		return ChessNotationHelper.squareToAlgebraicNotation(originSquare);
	}

	public String getTargetString() {
		return ChessNotationHelper.squareToAlgebraicNotation(targetSquare);
	}

	/*
	 * Executes this move
	 * If there is a opponent piece on the target square, it is set to be captured
	 * Then the selected square is emptied and the piece is moved to the target square
	 * The piece is set to have moved and a timestamp is added to the move
	 */
	public void execute() {
		if (!targetSquare.isEmpty()) {
			targetSquare.getPiece().setCaptured(true);
			capturedPiece = targetSquare.getPiece();
		}

		movedPiece.incrementMovesDone();
		targetSquare.placePiece(movedPiece);
		originSquare.placePiece(null);
		timeStamp = new Date();
	}

	public void undo() {
		originSquare.placePiece(movedPiece);
		movedPiece.decrementMovesDone();
		if (capturedPiece != null) {
			capturedPiece.setCaptured(false);
			targetSquare.placePiece(capturedPiece);
		}
		else {
			targetSquare.placePiece(null);
		}
	}

	public String executedMessage() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(playerColor.getName());
		stringBuilder.append(" moved " + movedPiece.getPlayerColor() + " " + movedPiece.getPieceType().getName());
		stringBuilder.append(" from " + ChessNotationHelper.squareToAlgebraicNotation(originSquare));
		stringBuilder.append(" to " + ChessNotationHelper.squareToAlgebraicNotation(targetSquare));
		if (capturedPiece != null) {
			stringBuilder.append(" and captured " + capturedPiece.getPieceType().getName());
		}
		return stringBuilder.toString();
	}

	public String undoneMessage() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(playerColor.getName());
		stringBuilder.append(" undid " + movedPiece.getPlayerColor() + " " + movedPiece.getPieceType().getName());
		stringBuilder.append(" from " + ChessNotationHelper.squareToAlgebraicNotation(originSquare));
		stringBuilder.append(" to " + ChessNotationHelper.squareToAlgebraicNotation(targetSquare));
		return stringBuilder.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdString() {
		return String.valueOf(id);
	}
}
