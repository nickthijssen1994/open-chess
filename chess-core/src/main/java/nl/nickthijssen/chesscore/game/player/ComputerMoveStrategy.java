package nl.nickthijssen.chesscore.game.player;

import nl.nickthijssen.chesscore.PlayerColor;
import nl.nickthijssen.chesscore.game.board.Board;
import nl.nickthijssen.chesscore.game.move.Move;

import java.util.List;
import java.util.Random;

public class ComputerMoveStrategy {

	private static Random random = new Random();

	private ComputerMoveStrategy() {
		throw new IllegalStateException("Utility Class");
	}

	// TODO Implement Computer AI Logic
	public static Move makeComputerMove(int id, Board board, PlayerColor color) {
		List<Move> possibleMoves = board.getPossibleMoves(color);
		int randomMovesAmount = possibleMoves.size();
		Move randomMove = possibleMoves.get(random.nextInt(randomMovesAmount));
		randomMove.setId(id);
		return randomMove;
		//        int originColumn = random.nextInt(8);
		//        int originRow = random.nextInt(8);
		//        int targetColumn = random.nextInt(8);
		//        int targetRow = random.nextInt(8);
		//
		//        boolean foundValidOrigin = false;
		//        boolean foundValidTarget = false;
		//
		//        while (!foundValidOrigin) {
		//            if (board.getSquare(originColumn, originRow).hasPlayerPiece(color) && !board.getSquare
		//            (originColumn, originRow).getPiece().getValidMoves(board).isEmpty()) {
		//                foundValidOrigin = true;
		//                while (!foundValidTarget) {
		//                    if (board.getSquare(originColumn, originRow).getPiece().getValidMoves(board).contains
		//                    (board.getSquare(targetColumn, targetRow))) {
		//                        foundValidTarget = true;
		//                    } else {
		//                        targetColumn = random.nextInt(8);
		//                        targetRow = random.nextInt(8);
		//                    }
		//                }
		//            }
		//            else {
		//                originColumn = random.nextInt(8);
		//                originRow = random.nextInt(8);
		//            }
		//        }
		//
		//        Move move = new Move(color, board.getSquare(originColumn, originRow), board.getSquare(targetColumn,
		//        targetRow));
		//        return move;
	}
}
