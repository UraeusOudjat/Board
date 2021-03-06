package example.chess;

import java.util.ArrayList;

import board.Cell;
import board.Index;

public class Knight {

	private static String opponentColor;
	private static boolean canMove = false;
	private static Cell selectedPieceCell;

	public static Cell setCanMoveCell(PlayerColor playerColor, int row, int column, Cell cell,
			ArrayList<ChessType> backgroundMoveCell, Cell[][] board, ArrayList<Index> indexMoveCell,
			Cell lastPieceMovedCell) {

		opponentColor = (playerColor.equals(PlayerColor.WHITE)) ? "_B" : "_W";

		int x = cell.getIndex().getX();
		int y = cell.getIndex().getY();

		// We removed the other move case
		ChessGlobal.removeMoveCase(backgroundMoveCell, board, indexMoveCell);

		// Move to left-2 top-1
		if (x - 1 >= 0 && y - 2 >= 0 && (board[x - 1][y - 2].getAgentType().equals(ChessType.EMPTY_CELL)
				|| board[x - 1][y - 2].getAgentType().toString().contains(opponentColor))) {
			indexMoveCell.add(new Index(x - 1, y - 2));
			backgroundMoveCell.add((ChessType) board[x - 1][y - 2].getBackgroundType());
			board[x - 1][y - 2].setBackgroundType(ChessType.MOVE_CELL);
			canMove = true;
		}

		// Move to left-2 bottom-1
		if (x + 1 < row && y - 2 >= 0 && (board[x + 1][y - 2].getAgentType().equals(ChessType.EMPTY_CELL)
				|| board[x + 1][y - 2].getAgentType().toString().contains(opponentColor))) {
			indexMoveCell.add(new Index(x + 1, y - 2));
			backgroundMoveCell.add((ChessType) board[x + 1][y - 2].getBackgroundType());
			board[x + 1][y - 2].setBackgroundType(ChessType.MOVE_CELL);
			canMove = true;
		}

		// Move to left-1 top-2
		if (x - 2 >= 0 && y - 1 >= 0 && (board[x - 2][y - 1].getAgentType().equals(ChessType.EMPTY_CELL)
				|| board[x - 2][y - 1].getAgentType().toString().contains(opponentColor))) {
			indexMoveCell.add(new Index(x - 2, y - 1));
			backgroundMoveCell.add((ChessType) board[x - 2][y - 1].getBackgroundType());
			board[x - 2][y - 1].setBackgroundType(ChessType.MOVE_CELL);
			canMove = true;
		}

		// Move to right-1 top-2
		if (x - 2 >= 0 && y + 1 < column && (board[x - 2][y + 1].getAgentType().equals(ChessType.EMPTY_CELL)
				|| board[x - 2][y + 1].getAgentType().toString().contains(opponentColor))) {
			indexMoveCell.add(new Index(x - 2, y + 1));
			backgroundMoveCell.add((ChessType) board[x - 2][y + 1].getBackgroundType());
			board[x - 2][y + 1].setBackgroundType(ChessType.MOVE_CELL);
			canMove = true;
		}

		// Move to right-2 top-1
		if (x - 1 >= 0 && y + 2 < column && (board[x - 1][y + 2].getAgentType().equals(ChessType.EMPTY_CELL)
				|| board[x - 1][y + 2].getAgentType().toString().contains(opponentColor))) {
			indexMoveCell.add(new Index(x - 1, y + 2));
			backgroundMoveCell.add((ChessType) board[x - 1][y + 2].getBackgroundType());
			board[x - 1][y + 2].setBackgroundType(ChessType.MOVE_CELL);
			canMove = true;
		}

		// Move to right-2 bottom-1
		if (x + 1 < row && y + 2 < column && (board[x + 1][y + 2].getAgentType().equals(ChessType.EMPTY_CELL)
				|| board[x + 1][y + 2].getAgentType().toString().contains(opponentColor))) {
			indexMoveCell.add(new Index(x + 1, y + 2));
			backgroundMoveCell.add((ChessType) board[x + 1][y + 2].getBackgroundType());
			board[x + 1][y + 2].setBackgroundType(ChessType.MOVE_CELL);
			canMove = true;
		}

		// Move to left-1 bottom-2
				if (x + 2 < row && y - 1 >= 0 && (board[x + 2][y - 1].getAgentType().equals(ChessType.EMPTY_CELL)
						|| board[x + 2][y - 1].getAgentType().toString().contains(opponentColor))) {
					indexMoveCell.add(new Index(x + 2, y - 1));
					backgroundMoveCell.add((ChessType) board[x + 2][y - 1].getBackgroundType());
					board[x + 2][y - 1].setBackgroundType(ChessType.MOVE_CELL);
					canMove = true;
				}

				// Move to right-1 bottom-2
				if (x + 2 < row && y + 1 < column && (board[x + 2][y + 1].getAgentType().equals(ChessType.EMPTY_CELL)
						|| board[x + 2][y + 1].getAgentType().toString().contains(opponentColor))) {
					indexMoveCell.add(new Index(x + 2, y + 1));
					backgroundMoveCell.add((ChessType) board[x + 2][y + 1].getBackgroundType());
					board[x + 2][y + 1].setBackgroundType(ChessType.MOVE_CELL);
					canMove = true;
				}
		
		if (canMove) {
			selectedPieceCell = cell;
		}

		return selectedPieceCell;
	}

	public static Cell move(PlayerColor playerColor, Cell[][] board, Cell cell, int column,
			ArrayList<ChessType> backgroundMoveCell, ArrayList<Index> indexMoveCell) {

		int x = cell.getIndex().getX();
		int y = cell.getIndex().getY();

		ChessGlobal.piecePositions.setNewKnightPosition(playerColor, new Index(x, y), selectedPieceCell.getIndex());
		if(!board[x][y].getAgentType().equals(ChessType.EMPTY_CELL)){
			ChessGlobal.removePiece(x, y, playerColor, board);
		}
		
		return ChessGlobal.move(x, y, selectedPieceCell, board, cell, backgroundMoveCell, indexMoveCell);
	}

}
