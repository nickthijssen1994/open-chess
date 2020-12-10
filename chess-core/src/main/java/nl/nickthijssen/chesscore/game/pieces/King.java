package nl.nickthijssen.chesscore.game.pieces;

import nl.nickthijssen.chesscore.PieceType;
import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.*;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

	public King(PlayerColor playerColor) {
		super(PieceType.KING, playerColor);
	}

	@Override
	public List<Location> getLegalMoves(Board board) {
		List<Location> legalMoves = new ArrayList<>();

		// Horizontal Right
		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() + 1, getRowPosition()));
		// Horizontal Left
		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() - 1, getRowPosition()));
		// Vertical Up
		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition(), getRowPosition() + 1));
		// Vertical Down
		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition(), getRowPosition() - 1));
		// Diagonal Left And Down
		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() - 1, getRowPosition() - 1));
		// Diagonal Left And Up
		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() - 1, getRowPosition() + 1));
		// Diagonal Right And Down
		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() + 1, getRowPosition() - 1));
		// Diagonal Right And Up
		addLocationIfLegal(board, legalMoves, Location.fromCoordinates(getColumnPosition() + 1, getRowPosition() + 1));

		// Check if castling is possible
		// First check if king has moved
		if (!this.hasMoved()) {
			if (this.getPlayerColor() == PlayerColor.WHITE && !board.isKingInCheck(PlayerColor.WHITE)) {
				// Check if rook has moved
				if (board.getSquare(Location.h1).hasPlayerPiece(PlayerColor.WHITE) && !board.getSquare(
						Location.h1).getPiece().hasMoved()) {
					// Check if squares between king and rook are empty
					if (board.getSquare(Location.g1).isEmpty() && board.getSquare(Location.f1).isEmpty()) {
						addLocationIfLegal(board, legalMoves, Location.g1);
					}
				}
				if (board.getSquare(Location.a1).hasPlayerPiece(PlayerColor.WHITE) && !board.getSquare(
						Location.a1).getPiece().hasMoved()) {
					// Check if squares between king and rook are empty
					if (board.getSquare(Location.b1).isEmpty() && board.getSquare(
							Location.c1).isEmpty() && board.getSquare(Location.d1).isEmpty()) {
						addLocationIfLegal(board, legalMoves, Location.c1);
					}
				}
			}

			if (this.getPlayerColor() == PlayerColor.BLACK && !board.isKingInCheck(PlayerColor.BLACK)) {
				// Check if rook has moved
				if (board.getSquare(Location.h8).hasPlayerPiece(PlayerColor.BLACK) && !board.getSquare(
						Location.h8).getPiece().hasMoved()) {
					// Check if squares between king and rook are empty
					if (board.getSquare(Location.g8).isEmpty() && board.getSquare(Location.f8).isEmpty()) {
						addLocationIfLegal(board, legalMoves, Location.g8);
					}
				}
				if (board.getSquare(Location.a8).hasPlayerPiece(PlayerColor.BLACK) && !board.getSquare(
						Location.a8).getPiece().hasMoved()) {
					// Check if squares between king and rook are empty
					if (board.getSquare(Location.b8).isEmpty() && board.getSquare(
							Location.c8).isEmpty() && board.getSquare(Location.d8).isEmpty()) {
						addLocationIfLegal(board, legalMoves, Location.c8);
					}
				}
			}
		}

		return legalMoves;
	}

	private boolean addLocationIfLegal(Board board, List<Location> legalMoves, Location location) {
		if (location != null) {
			Square targetSquare = board.getSquare(location);
			if (targetSquare.isEmpty() || targetSquare.hasOpponentPiece(getPlayerColor())) {
				legalMoves.add(location);
				return true;
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
