package nl.nickthijssen.chesscore.game.pieces;

import nl.nickthijssen.chesscore.PieceType;
import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.*;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

	public Bishop(PlayerColor playerColor) {
		super(PieceType.BISHOP, playerColor);
	}

	@Override
	public List<Location> getLegalMoves(Board board) {
		List<Location> legalMoves = new ArrayList<>();

		// Diagonal To Top Right
		for (int diagonal = 1; diagonal < 8; diagonal++) {
			if (!addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() + diagonal,
																				getRowPosition() + diagonal))) {
				break;
			}
		}

		// Diagonal To Top Left
		for (int diagonal = 1; diagonal < 8; diagonal++) {
			if (!addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() - diagonal,
																				getRowPosition() + diagonal))) {
				break;
			}
		}

		// Diagonal To Bottom Right
		for (int diagonal = 1; diagonal < 8; diagonal++) {
			if (!addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() + diagonal,
																				getRowPosition() - diagonal))) {
				break;
			}
		}

		// Diagonal To Bottom Left
		for (int diagonal = 1; diagonal < 8; diagonal++) {
			if (!addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() - diagonal,
																				getRowPosition() - diagonal))) {
				break;
			}
		}

		return legalMoves;
	}

	private boolean addLocationIfLegal(Board board, List<Location> legalMoves, Location location) {
		if (location != null) {
			Square targetSquare = board.getSquare(location);
			if (targetSquare.isEmpty()) {
				legalMoves.add(location);
				return true;
			}
			else if (targetSquare.hasOpponentPiece(super.getPlayerColor())) {
				legalMoves.add(location);
				return false;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
}
