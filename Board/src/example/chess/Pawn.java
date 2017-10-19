package example.chess;

import java.util.ArrayList;

import board.Cell;
import board.Index;

public class Pawn {

	private static String opponentColor;
	private static boolean canMove = false;
	private static boolean enPassant = false;
	private static Cell selectedPieceCell;

	public static Cell setCanMoveCell(PlayerColor playerColor, int row, int column, Cell cell,
			ArrayList<ChessType> backgroundMoveCell, Cell[][] board, ArrayList<Index> indexMoveCells,
			Cell lastPieceMovedCell) {

		opponentColor = (playerColor.equals(PlayerColor.WHITE)) ? "_B" : "_W";

		int x = cell.getIndex().getX();
		int y = cell.getIndex().getY();

		// We removed the move case of the previous selected piece
		ChessGlobal.removeMoveCase(backgroundMoveCell, board, indexMoveCells);

		if (playerColor.equals(PlayerColor.WHITE)) {
			if (x - 1 >= 0) {

				// We check if the cell on the top of the pawn is empty
				if (board[x - 1][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
					indexMoveCells.add(new Index(x - 1, y));
				}

				// For a "prise en passant" the pawn have to be in the third row
				// and the previous move
				// must be a pawn on the left or the right who move two cell

				if (y - 1 >= 0) {

					// We check if the cell on top left contain an opponent
					// piece or if we can do a "prise en passant"
					if (board[x - 1][y - 1].getAgentType().toString().contains(opponentColor)
							|| (x == 3 && lastPieceMovedCell.getIndex().getX() == x
									&& lastPieceMovedCell.getIndex().getY() == y - 1
									&& lastPieceMovedCell.getAgentType().equals(ChessType.PAWN_B))) {
						indexMoveCells.add(new Index(x - 1, y - 1));
					}

				}

				if (y + 1 < column) {

					// We check if the cell on top right contain opponent piece
					// or if
					// the pawn can do a "prise en passant"
					if (board[x - 1][y + 1].getAgentType().toString().contains(opponentColor)
							|| (x == 3 && lastPieceMovedCell.getIndex().getX() == x
									&& lastPieceMovedCell.getIndex().getY() == y + 1
									&& lastPieceMovedCell.getAgentType().equals(ChessType.PAWN_B))) {
						indexMoveCells.add(new Index(x - 1, y + 1));
					}
				}
			}

			// If the pawn doesn't move we check if he can move on the second
			// top
			// cell
			if (x == row - 2) {
				if (board[x - 2][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
					indexMoveCells.add(new Index(x - 2, y));
				}
			}

		} else {
			if (x + 1 < row) {
				if (board[x + 1][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
					indexMoveCells.add(new Index(x + 1, y));
				}

				if (y - 1 >= 0) {
					if (board[x + 1][y - 1].getAgentType().toString().contains("_W")
							|| (x == 4 && lastPieceMovedCell.getIndex().getX() == x
									&& lastPieceMovedCell.getIndex().getY() == y - 1
									&& lastPieceMovedCell.getAgentType().equals(ChessType.PAWN_W))) {
						indexMoveCells.add(new Index(x + 1, y - 1));
					}
				}

				if (y + 1 < column) {
					if (board[x + 1][y + 1].getAgentType().toString().contains("_W")
							|| (x == 4 && lastPieceMovedCell.getIndex().getX() == x
									&& lastPieceMovedCell.getIndex().getY() == y + 1
									&& lastPieceMovedCell.getAgentType().equals(ChessType.PAWN_W))) {
						indexMoveCells.add(new Index(x + 1, y + 1));
					}
				}

			}

			if (x == 1) {
				if (board[x + 2][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
					indexMoveCells.add(new Index(x + 2, y));			
				}
			}
		}
		
		if(Check.isKingCheck(playerColor)){
			for (Index indexMove : indexMoveCells) {
				
				System.out.println("Le roi est en echec");
				if(Check.moveCancelCheck(playerColor, board, cell.getIndex(), indexMove)){
					
					System.out.println("Ce mouvement annulera l'échec");
					backgroundMoveCell.add((ChessType) board[indexMove.getX()][indexMove.getY()].getBackgroundType());
					board[indexMove.getX()][indexMove.getY()].setBackgroundType(ChessType.MOVE_CELL);
					canMove = true;
				}	
			}
		}else{
			for (Index index : indexMoveCells) {
				backgroundMoveCell.add((ChessType) board[index.getX()][index.getY()].getBackgroundType());
				board[index.getX()][index.getY()].setBackgroundType(ChessType.MOVE_CELL);
				canMove = true;
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
	public static Cell move(PlayerColor playerColor, Cell[][] board, Cell cell, int column,
			ArrayList<ChessType> backgroundMoveCell, ArrayList<Index> indexMoveCell) {

		int x = cell.getIndex().getX();
		int y = cell.getIndex().getY();

		if (playerColor.equals(PlayerColor.WHITE)) {

			// If the pawn go to an empty cell but change column he has doing an
			// "en passant" move so we remove the bottom opponent pawn
			if (enPassant && board[x][y].getAgentType().equals(ChessType.EMPTY_CELL)
					&& selectedPieceCell.getIndex().getY() != y) {
				board[x + 1][y].setAgentType(ChessType.EMPTY_CELL);
				ChessGlobal.removePiece(x + 1, y, playerColor, board);
				enPassant = false;
			} else if (Math.abs(selectedPieceCell.getIndex().getX() - x) == 2) {
				if (y - 1 >= 0) {
					if (board[x][y - 1].getAgentType().equals(ChessType.valueOf("PAWN" + opponentColor)))
						enPassant = true;
				}

				if (y + 1 < column) {
					if (board[x][y + 1].getAgentType().equals(ChessType.valueOf("PAWN" + opponentColor)))
						enPassant = true;
				}
			} else {
				enPassant = false;
			}

		} else {

			// If the pawn go to an empty cell but change column he has doing an
			// "en passant" move so we remove the top opponent pawn
			if (enPassant && board[x][y].getAgentType().equals(ChessType.EMPTY_CELL)
					&& selectedPieceCell.getIndex().getY() != y) {
				board[x - 1][y].setAgentType(ChessType.EMPTY_CELL);
				ChessGlobal.removePiece(x - 1, y, playerColor, board);
				enPassant = false;
			} else if (x - selectedPieceCell.getIndex().getX() == 2) {
				if (y - 1 >= 0) {
					if (board[x][y - 1].getAgentType().equals(ChessType.PAWN_W))
						enPassant = true;
				}

				if (y + 1 < column) {
					if (board[x][y + 1].getAgentType().equals(ChessType.PAWN_W))
						enPassant = true;
				}
			} else {
				enPassant = false;
			}

		}

		ChessGlobal.piecePositions.setNewPawnPosition(playerColor, new Index(x, y), selectedPieceCell.getIndex());
		if(!board[x][y].getAgentType().equals(ChessType.EMPTY_CELL)){
			ChessGlobal.removePiece(x, y, playerColor, board);
		}
		
		return ChessGlobal.move(x, y, selectedPieceCell, playerColor, board, cell, backgroundMoveCell, indexMoveCell);
	}

}
