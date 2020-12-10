package nl.nickthijssen.chesscore.game.board;

import nl.nickthijssen.chesscore.*;
import nl.nickthijssen.chesscore.game.move.Move;
import nl.nickthijssen.chesscore.game.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard implements Board {

	private final Square[][] squares = new Square[8][8]; // [column][rows]

	private final List<Piece> pieces = new ArrayList<>();
	private PlayerColor playerWhoHasTurn;
	private boolean waitingForPawnPromotion = false;
	private Piece pawnToBePromoted;

	public ChessBoard() {
		playerWhoHasTurn = PlayerColor.WHITE;
		for (int column = 0; column < 8; column++) {
			for (int row = 0; row < 8; row++) {
				this.squares[column][row] = new Square(column, row);
			}
		}
	}

	@Override
	public void setPiecesToStartPosition() {
		Piece piece;

		// Place White Pawns
		for (int column = 0; column < 8; column++) {
			piece = new Pawn(PlayerColor.WHITE);
			pieces.add(piece);
			squares[column][1].placePiece(piece);
		}

		// Place White Pieces
		piece = new Rook(PlayerColor.WHITE);
		pieces.add(piece);
		squares[0][0].placePiece(piece);
		piece = new Knight(PlayerColor.WHITE);
		pieces.add(piece);
		squares[1][0].placePiece(piece);
		piece = new Bishop(PlayerColor.WHITE);
		pieces.add(piece);
		squares[2][0].placePiece(piece);
		piece = new Queen(PlayerColor.WHITE);
		pieces.add(piece);
		squares[3][0].placePiece(piece);
		piece = new King(PlayerColor.WHITE);
		pieces.add(piece);
		squares[4][0].placePiece(piece);
		piece = new Bishop(PlayerColor.WHITE);
		pieces.add(piece);
		squares[5][0].placePiece(piece);
		piece = new Knight(PlayerColor.WHITE);
		pieces.add(piece);
		squares[6][0].placePiece(piece);
		piece = new Rook(PlayerColor.WHITE);
		pieces.add(piece);
		squares[7][0].placePiece(piece);

		// Place Black Pawns
		for (int column = 0; column < 8; column++) {
			piece = new Pawn(PlayerColor.BLACK);
			pieces.add(piece);
			squares[column][6].placePiece(piece);
		}

		// Place Black Pieces
		piece = new Rook(PlayerColor.BLACK);
		pieces.add(piece);
		squares[0][7].placePiece(piece);
		piece = new Knight(PlayerColor.BLACK);
		pieces.add(piece);
		squares[1][7].placePiece(piece);
		piece = new Bishop(PlayerColor.BLACK);
		pieces.add(piece);
		squares[2][7].placePiece(piece);
		piece = new Queen(PlayerColor.BLACK);
		pieces.add(piece);
		squares[3][7].placePiece(piece);
		piece = new King(PlayerColor.BLACK);
		pieces.add(piece);
		squares[4][7].placePiece(piece);
		piece = new Bishop(PlayerColor.BLACK);
		pieces.add(piece);
		squares[5][7].placePiece(piece);
		piece = new Knight(PlayerColor.BLACK);
		pieces.add(piece);
		squares[6][7].placePiece(piece);
		piece = new Rook(PlayerColor.BLACK);
		pieces.add(piece);
		squares[7][7].placePiece(piece);
	}

	@Override
	public Square getSquare(Location location) {
		return squares[location.getColumn()][location.getRow()];
	}

	@Override
	public List<PieceLocation> getPieceLocations() {
		List<PieceLocation> pieceLocations = new ArrayList<>();
		for (int column = 0; column < 8; column++) {
			for (int row = 0; row < 8; row++) {
				Square square = squares[column][row];
				if (!square.isEmpty()) {
					PieceLocation pieceLocation = new PieceLocation(column, row, square.getPiece().getPieceType(),
																	square.getPiece().getPlayerColor());
					pieceLocations.add(pieceLocation);
				}
			}
		}
		return pieceLocations;
	}

	@Override
	public List<Move> getPossibleMoves(PlayerColor playerColor) {
		List<Move> possiblePlayerMoves = new ArrayList<>();
		for (Piece playerPiece : getActivePlayerPieces(playerColor)) {
			for (Location validTarget : playerPiece.getLegalMoves(this)) {
				Move move = new Move(playerColor,
									 squares[playerPiece.getColumnPosition()][playerPiece.getRowPosition()],
									 getSquare(validTarget));
				if (move.getMovedPiece().getPlayerColor() == playerColor) {
					move.execute();
					if (!isKingInCheck(playerColor)) {
						move.undo();
						possiblePlayerMoves.add(move);
					}
					else {
						move.undo();
					}
				}
			}
		}
		return possiblePlayerMoves;
	}

	// Returns a list of squares that can by targeted by opponent at this moment
	@Override
	public List<Square> getPossibleTargets(PlayerColor color) {
		List<Square> possibleTargets = new ArrayList<>();
		for (Piece playerPiece : getActivePlayerPieces(getPlayerWhoNotHasTurn())) {
			if (playerPiece.getPieceType() != PieceType.KING) {
				for (Location legalMove : playerPiece.getLegalMoves(this)) {
					possibleTargets.add(getSquare(legalMove));
				}
			}
		}
		return possibleTargets;
	}

	@Override
	public boolean isKingInCheck(PlayerColor color) {
		for (Piece piece : getActivePlayerPieces(color)) {
			if (piece.getPieceType() == PieceType.KING) {
				return getPossibleTargets(color).contains(
						getSquare(Location.fromCoordinates(piece.getColumnPosition(), piece.getRowPosition())));
			}
		}
		return false;
	}

	@Override
	public boolean isKingInCheckmate(PlayerColor color) {
		return getPossibleMoves(color).isEmpty();
	}

	@Override
	public int getLines() {
		return squares.length;
	}

	@Override
	public int getRows() {
		return squares[0].length;
	}

	@Override
	public List<Square> getEmptySquares() {
		List<Square> emptySquares = new ArrayList<>();
		for (int column = 0; column < 8; column++) {
			for (int row = 0; row < 8; row++) {
				if (squares[column][row].isEmpty()) {
					emptySquares.add(squares[column][row]);
				}
			}
		}
		return emptySquares;
	}

	@Override
	public List<Piece> getActivePieces() {
		List<Piece> activePieces = new ArrayList<>();
		for (Piece piece : pieces) {
			if (!piece.isCaptured()) {
				activePieces.add(piece);
			}
		}
		return activePieces;
	}

	@Override
	public List<Piece> getActivePlayerPieces(PlayerColor player) {
		List<Piece> activePieces = new ArrayList<>();
		for (Piece piece : pieces) {
			if (piece.getPlayerColor() == player && !piece.isCaptured()) {
				activePieces.add(piece);
			}
		}
		return activePieces;
	}

	@Override
	public List<Piece> getCapturedPieces() {
		List<Piece> capturedPieces = new ArrayList<>();
		for (Piece piece : pieces) {
			if (piece.isCaptured()) {
				capturedPieces.add(piece);
			}
		}
		return capturedPieces;
	}

	@Override
	public List<Piece> getCapturedPlayerPieces(PlayerColor player) {
		List<Piece> capturedPieces = new ArrayList<>();
		for (Piece piece : pieces) {
			if (piece.getPlayerColor() == player && piece.isCaptured()) {
				capturedPieces.add(piece);
			}
		}
		return capturedPieces;
	}

	@Override
	public PlayerColor getPlayerWhoHasTurn() {
		return playerWhoHasTurn;
	}

	@Override
	public PlayerColor getPlayerWhoNotHasTurn() {
		if (playerWhoHasTurn == PlayerColor.WHITE) {
			return PlayerColor.BLACK;
		}
		else {
			return PlayerColor.WHITE;
		}
	}

	@Override
	public List<Move> getLegalPlayerMoves(PlayerColor player) {
		List<Move> legalMoves = new ArrayList<>();
		for (Piece piece : getActivePlayerPieces(player)) {
			for (Location validTarget : piece.getLegalMoves(this)) {
				Move move = new Move(player, getSquare(
						Location.fromCoordinates(piece.getColumnPosition(), piece.getRowPosition())),
									 getSquare(validTarget));
				legalMoves.add(move);
			}
		}
		return legalMoves;
	}

	@Override
	public List<Square> getSquares() {
		List<Square> squareList = new ArrayList<>();
		for (int column = 0; column < 8; column++) {
			for (int row = 0; row < 8; row++) {
				squareList.add(squares[column][row]);
			}
		}
		return squareList;
	}

	@Override
	public Move movePiece(PlayerColor player, Location origin, Location target) {
		if (playerWhoHasTurn != player) {
			return null;
		}
		else if (!getSquare(origin).hasPlayerPiece(player)) {
			return null;
		}
		else {
			for (Location square : getSquare(origin).getPiece().getLegalMoves(this)) {
				if (square.getColumn() == target.getColumn() && square.getRow() == target.getRow()) {
					Move move = new Move(player, getSquare(origin), getSquare(target));
					move.execute();
					if (!move.getMovedPiece().canBePromoted()) {
						switchTurns();
					}
					else {
						pawnToBePromoted = move.getMovedPiece();
						waitingForPawnPromotion = true;
					}
					return move;
				}
			}
		}
		return null;
	}

	@Override
	public void placePiece(Piece piece, Location location) {
		Square square = squares[location.getColumn()][location.getRow()];
		if (square.isEmpty()) {
			pieces.add(piece);
			square.placePiece(piece);
		}
	}

	@Override
	public void removePiece(Location location) {
		Square square = squares[location.getColumn()][location.getRow()];
		if (!square.isEmpty()) {
			pieces.remove(square.getPiece());
			square.removePiece();
		}
	}

	@Override
	public boolean promotePawn(PlayerColor playerColor, PieceType pieceType) {
		if (playerWhoHasTurn == playerColor && pieceType != PieceType.KING && pieceType != PieceType.PAWN) {
			Location pawnLocation = Location.fromCoordinates(pawnToBePromoted.getColumnPosition(),
															 pawnToBePromoted.getRowPosition());
			removePiece(pawnLocation);
			pawnToBePromoted = null;
			switch (pieceType) {
				case BISHOP:
					placePiece(new Bishop(playerColor), pawnLocation);
					break;
				case KNIGHT:
					placePiece(new Knight(playerColor), pawnLocation);
					break;
				case QUEEN:
					placePiece(new Queen(playerColor), pawnLocation);
					break;
				case ROOK:
					placePiece(new Rook(playerColor), pawnLocation);
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + pieceType);
			}
			waitingForPawnPromotion = false;
			switchTurns();
			return true;
		}
		return false;
	}

	private void switchTurns() {
		if (!waitingForPawnPromotion) {
			if (playerWhoHasTurn == PlayerColor.WHITE) {
				playerWhoHasTurn = PlayerColor.BLACK;
			}
			else {
				playerWhoHasTurn = PlayerColor.WHITE;
			}
		}
	}
}
