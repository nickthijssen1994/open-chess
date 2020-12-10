package nl.nickthijssen.chesscore.game.pieces;

import nl.nickthijssen.chesscore.*;
import nl.nickthijssen.chesscore.game.board.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

	private PieceType pieceType;
	private PlayerColor playerColor;
	private Square position;
	private int movesDone = 0;
	private boolean isCaptured;
	private boolean canBePromoted;

	public Piece(PieceType pieceType, PlayerColor playerColor) {
		this.pieceType = pieceType;
		this.playerColor = playerColor;
		this.isCaptured = false;
		this.canBePromoted = false;
	}

	public PieceType getPieceType() {
		return this.pieceType;
	}

	public PlayerColor getPlayerColor() {
		return this.playerColor;
	}

	public boolean hasMoved() {
		return movesDone > 0;
	}

	public boolean isCaptured() {
		return isCaptured;
	}

	public void setCaptured(boolean captured) {
		isCaptured = captured;
	}

	public void incrementMovesDone() {
		movesDone++;
	}

	public void decrementMovesDone() {
		if (movesDone > 0) {
			movesDone--;
		}
	}

	public int getMovesDone() {
		return movesDone;
	}

	public int getColumnPosition() {
		return position.getColumn();
	}

	public int getRowPosition() {
		return position.getRow();
	}

	public void setPosition(Square square) {
		this.position = square;
	}

	public boolean canBeCaptured(Board board) {
		List<Square> possibleOpponentMoves = new ArrayList<>();
		for (Piece opponentPiece : board.getActivePlayerPieces(board.getPlayerWhoNotHasTurn())) {
			List<Square> possibleOpponentPieceMoves = new ArrayList<>();
			for (Location legalMove : opponentPiece.getLegalMoves(board)) {
				possibleOpponentPieceMoves.add(board.getSquare(legalMove));
			}
			possibleOpponentMoves.addAll(possibleOpponentPieceMoves);
		}
		return possibleOpponentMoves.contains(position);
	}

	public List<SquareLocation> getPossibleMoves(Board board) {
		List<SquareLocation> squareLocations = new ArrayList<>();
		for (Location square : getLegalMoves(board)) {
			SquareLocation pieceLocation = new SquareLocation(square.getColumn(), square.getRow());
			squareLocations.add(pieceLocation);
		}
		return squareLocations;
	}

	public abstract List<Location> getLegalMoves(Board board);

	public boolean canBePromoted() {
		if (pieceType == PieceType.PAWN) {
			if (playerColor == PlayerColor.WHITE && getRowPosition() == 7) {
				canBePromoted = true;
			}
			if (playerColor == PlayerColor.BLACK && getRowPosition() == 0) {
				canBePromoted = true;
			}
		}
		return canBePromoted;
	}
}
