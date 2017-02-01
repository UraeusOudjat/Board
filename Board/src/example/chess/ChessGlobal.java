package example.chess;

import java.util.ArrayList;

import board.Cell;
import board.Index;

public class ChessGlobal {

	public static boolean whiteKingNeverMove = true;
	public static boolean blackKingNeverMove = true;
	
	/**
	 * This method is global method for move a piece on the chess, she must call by specific method move contain in the Pawn, Rook, Knight, Bishop, Queen and King classes
	 * @param x
	 * @param y
	 * @param selectedPieceCell
	 * @param turn
	 * @param board
	 * @param cell
	 * @param backgroundMoveCell
	 * @param indexMoveCell
	 * @return
	 */
	public static Cell move(int x, int y,Cell selectedPieceCell,PlayerTurn turn,Cell[][] board, Cell cell, ArrayList<ChessType> backgroundMoveCell, ArrayList<Index> indexMoveCell){
		//The current cell agent  is now set with the moving piece agent
		board[x][y].setAgentType(selectedPieceCell.getAgentType());

		//The cell who contained the moving piece is set to empty agent
		board[selectedPieceCell.getIndex().getX()][selectedPieceCell.getIndex().getY()]
				.setAgentType(ChessType.EMPTY_CELL);
		
		
		ChessGlobal.removeMoveCase(backgroundMoveCell,board,indexMoveCell);
		selectedPieceCell = board[x][y];

		return cell; 
	}
	
	public static void removeMoveCase(ArrayList<ChessType> backgroundMoveCell, Cell[][] board,
			ArrayList<Index> indexMoveCell) {
		if (backgroundMoveCell.size() > 0) {
			for (int i = 0; i < backgroundMoveCell.size(); i++) {
				board[indexMoveCell.get(i).getX()][indexMoveCell.get(i).getY()]
						.setBackgroundType(backgroundMoveCell.get(i));
			}
		}
		indexMoveCell.clear();
		backgroundMoveCell.clear();
	}
}
