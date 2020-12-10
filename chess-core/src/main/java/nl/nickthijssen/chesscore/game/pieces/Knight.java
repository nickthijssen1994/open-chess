package nl.nickthijssen.chesscore.game.pieces;

import nl.nickthijssen.chesscore.PieceType;
import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.*;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

	public Knight(PlayerColor playerColor) {
		super(PieceType.KNIGHT, playerColor);
	}

	@Override
	public List<Location> getLegalMoves(Board board) {
		List<Location> legalMoves = new ArrayList<>();

		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() + 2, getRowPosition() + 1));

		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() + 2, getRowPosition() - 1));

		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() + 1, getRowPosition() - 2));

		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() - 1, getRowPosition() - 2));

		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() - 2, getRowPosition() - 1));

		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() - 2, getRowPosition() + 1));

		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() + 1, getRowPosition() + 2));

		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() - 1, getRowPosition() + 2));

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
