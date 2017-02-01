package example.chess;

import java.util.ArrayList;

import board.Cell;
import board.Index;

public class Pawn {

	private static String opponentColor;
	private static boolean canMove = false;
	private static boolean enPassant = false;
	private static Cell selectedPieceCell;

	public static Cell setCanMoveCell(PlayerTurn turn, int row, int column, Cell cell, ArrayList<ChessType> backgroundMoveCell,
			Cell[][] board, ArrayList<Index> indexMoveCell, Cell lastPieceMovedCell) {

		opponentColor = (turn.equals(PlayerTurn.WHITE)) ? "_B" : "_W";


		int x = cell.getIndex().getX();
		int y = cell.getIndex().getY();

		// We removed the other move case
		ChessGlobal.removeMoveCase(backgroundMoveCell, board, indexMoveCell);

		if(turn.equals(PlayerTurn.WHITE)){
		if (x - 1 >= 0) {

			// We check if the cell on the top of the pawn is empty
			if (board[x - 1][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
				indexMoveCell.add(new Index(x - 1, y));
				backgroundMoveCell.add((ChessType) board[x - 1][y].getBackgroundType());
				board[x - 1][y].setBackgroundType(ChessType.MOVE_CELL);
				canMove = true;
			}

			// For a "prise en passant" the pawn have to be in the third row and the previous move
			//must be a pawn on the left or the rigth who move two cell
			
			if (y - 1 >= 0) {

				// We check if the cell on top left contain an opponent piece or if we can do a "prise en passant"
				if (board[x - 1][y - 1].getAgentType().toString().contains(opponentColor)
						|| (x == 3 && lastPieceMovedCell.getIndex().getX() == x
								&& lastPieceMovedCell.getIndex().getY() == y - 1 && lastPieceMovedCell.getAgentType().equals(ChessType.PAWN_B))) {
					indexMoveCell.add(new Index(x - 1, y - 1));
					backgroundMoveCell.add((ChessType) board[x - 1][y - 1].getBackgroundType());
					board[x - 1][y - 1].setBackgroundType(ChessType.MOVE_CELL);
					canMove = true;
				}

			}

			if (y + 1 < column) {

				// We check if the cell on top right contain opponent piece or if
				// the pawn can do a "prise en passant"
				if (board[x - 1][y + 1].getAgentType().toString().contains(opponentColor)
						|| (x == 3 && lastPieceMovedCell.getIndex().getX() == x
								&& lastPieceMovedCell.getIndex().getY() == y + 1 && lastPieceMovedCell.getAgentType().equals(ChessType.PAWN_B))) {
					indexMoveCell.add(new Index(x - 1, y + 1));
					backgroundMoveCell.add((ChessType) board[x - 1][y + 1].getBackgroundType());
					board[x - 1][y + 1].setBackgroundType(ChessType.MOVE_CELL);
					canMove = true;
				}
			}
		}

		// If the pawn doesn't move we check if he can move on the second top
		// cell
		if (x == row - 2) {
			if (board[x - 2][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
				indexMoveCell.add(new Index(x - 2, y));
				backgroundMoveCell.add((ChessType) board[x - 2][y].getBackgroundType());
				board[x - 2][y].setBackgroundType(ChessType.MOVE_CELL);
				canMove = true;
			}
		}
		
		}else{
			if (x + 1 < row) {
				if (board[x + 1][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
					indexMoveCell.add(new Index(x + 1, y));
					backgroundMoveCell.add((ChessType) board[x + 1][y].getBackgroundType());
					board[x + 1][y].setBackgroundType(ChessType.MOVE_CELL);
					canMove = true;
				}

				if (y - 1 >= 0) {
					if (board[x + 1][y - 1].getAgentType().toString().contains("_W") || (x == 4
							&& lastPieceMovedCell.getIndex().getX() == x && lastPieceMovedCell.getIndex().getY() == y - 1 && lastPieceMovedCell.getAgentType().equals(ChessType.PAWN_W))) {
						indexMoveCell.add(new Index(x + 1, y - 1));
						backgroundMoveCell.add((ChessType) board[x + 1][y - 1].getBackgroundType());
						board[x + 1][y - 1].setBackgroundType(ChessType.MOVE_CELL);
						canMove = true;
					}
				}

				if (y + 1 < column) {
					if (board[x + 1][y + 1].getAgentType().toString().contains("_W") || (x == 4
							&& lastPieceMovedCell.getIndex().getX() == x && lastPieceMovedCell.getIndex().getY() == y + 1 && lastPieceMovedCell.getAgentType().equals(ChessType.PAWN_W))) {
						indexMoveCell.add(new Index(x + 1, y + 1));
						backgroundMoveCell.add((ChessType) board[x - 1][y + 1].getBackgroundType());
						board[x + 1][y + 1].setBackgroundType(ChessType.MOVE_CELL);
						canMove = true;
					}
				}

			}

			if (x == 1) {
				if (board[x + 2][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
					indexMoveCell.add(new Index(x + 2, y));
					backgroundMoveCell.add((ChessType) board[x + 2][y].getBackgroundType());
					board[x + 2][y].setBackgroundType(ChessType.MOVE_CELL);
					canMove = true;
				}
			}
		}
		
		if (canMove) {
			selectedPieceCell = cell;
		}
		
		return selectedPieceCell;
	}
	
	/**
	 * 
	 * @param board
	 * @param cell
	 * @param column
	 * @param backgroundMoveCell
	 * @param indexMoveCell
	 * @return The cell who contain the last piece who moved
	 */
	public static Cell move(PlayerTurn turn,Cell[][] board, Cell cell, int column, ArrayList<ChessType> backgroundMoveCell, ArrayList<Index> indexMoveCell){
		
		

		int x = cell.getIndex().getX();
		int y = cell.getIndex().getY();
		
		if(turn.equals(PlayerTurn.WHITE)){
		
			//If the pawn go to an empty cell but change column he has doing an "en passant" so we remove the bottom opponent pawn
			if(enPassant && board[x][y].getAgentType().equals(ChessType.EMPTY_CELL) && selectedPieceCell.getIndex().getY() != y){
				board[x+1][y].setAgentType(ChessType.EMPTY_CELL);
				enPassant = false;
			}else if(Math.abs(selectedPieceCell.getIndex().getX()-x) == 2){
				if (y - 1 >= 0) {
					if(board[x][y-1].getAgentType().equals(ChessType.valueOf("PAWN"+opponentColor)))
						enPassant= true;
				}

				if (y + 1 < column) {
					if(board[x][y+1].getAgentType().equals(ChessType.valueOf("PAWN"+opponentColor)))
						enPassant= true;
				}
			}else{
				enPassant = false;
			}
		
		
		
		}else{
			
				//If the pawn go to an empty cell but change column he has doing an "en passant" so we remove the top opponent pawn
				if(enPassant && board[x][y].getAgentType().equals(ChessType.EMPTY_CELL) && selectedPieceCell.getIndex().getY() != y){
					board[x-1][y].setAgentType(ChessType.EMPTY_CELL);
					enPassant = false;
				}else if(x-selectedPieceCell.getIndex().getX() == 2){
					if (y - 1 >= 0) {
						if(board[x][y-1].getAgentType().equals(ChessType.PAWN_W))
							enPassant= true;
					}

					if (y + 1 < column) {
						if(board[x][y+1].getAgentType().equals(ChessType.PAWN_W))
							enPassant= true;
					}
				}else{
					enPassant = false;
				}
			
		}
	
		return ChessGlobal.move(x, y, selectedPieceCell, turn, board, cell, backgroundMoveCell, indexMoveCell); 
	}


}
