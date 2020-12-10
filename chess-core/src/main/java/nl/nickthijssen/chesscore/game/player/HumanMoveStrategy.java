package nl.nickthijssen.chesscore.game.player;

import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.Board;
import nl.nickthijssen.chesscore.game.board.Location;
import nl.nickthijssen.chesscore.game.move.Move;

public class HumanMoveStrategy {

	private HumanMoveStrategy() {
		throw new IllegalStateException("Utility Class");
	}

	public static Move makeHumanMove(int id, Board board, PlayerColor color, int originColumn, int originRow,
									 int targetColumn, int targetRow) {
		Move move = new Move(color, board.getSquare(Location.fromCoordinates(originColumn, originRow)),
							 board.getSquare(Location.fromCoordinates(targetColumn, targetRow)));
		move.setId(id);
		return move;
	}
}
