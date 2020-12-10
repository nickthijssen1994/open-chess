package nl.nickthijssen.chesscore.game.pieces;

import nl.nickthijssen.chesscore.PieceType;
import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

	public Pawn(PlayerColor playerColor) {
		super(PieceType.PAWN, playerColor);
	}

	@Override
	public List<Location> getLegalMoves(Board board) {
		List<Location> legalMoves = new ArrayList<>();

		// For White Pawns
		if (super.getPlayerColor() == PlayerColor.WHITE) {
			if (addLocationIfLegal(board, legalMoves,
								   Location.fromCoordinates(getColumnPosition(), getRowPosition() + 1),
								   true) && !super.hasMoved()) {
				addLocationIfLegal(board, legalMoves,
								   Location.fromCoordinates(getColumnPosition(), getRowPosition() + 2), true);
			}
			addLocationIfLegal(board, legalMoves,
							   Location.fromCoordinates(getColumnPosition() + 1, getRowPosition() + 1), false);
			addLocationIfLegal(board, legalMoves,
							   Location.fromCoordinates(getColumnPosition() - 1, getRowPosition() + 1), false);
		}

		// For Black Pawns
		if (super.getPlayerColor() == PlayerColor.BLACK) {
			if (addLocationIfLegal(board, legalMoves,
								   Location.fromCoordinates(getColumnPosition(), getRowPosition() - 1),
								   true) && !super.hasMoved()) {
				addLocationIfLegal(board, legalMoves,
								   Location.fromCoordinates(getColumnPosition(), getRowPosition() - 2), true);
			}
			addLocationIfLegal(board, legalMoves,
							   Location.fromCoordinates(getColumnPosition() + 1, getRowPosition() - 1), false);
			addLocationIfLegal(board, legalMoves,
							   Location.fromCoordinates(getColumnPosition() - 1, getRowPosition() - 1), false);
		}

		return legalMoves;
	}

	private boolean addLocationIfLegal(Board board, List<Location> legalMoves, Location location, boolean forward) {
		if (location != null) {
			Square targetSquare = board.getSquare(location);
			if (forward && targetSquare.isEmpty()) {
				legalMoves.add(location);
				return true;
			}
			else if (!forward && !targetSquare.isEmpty()) {
				if (targetSquare.hasOpponentPiece(super.getPlayerColor())) {
					legalMoves.add(location);
					return true;
				}
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
