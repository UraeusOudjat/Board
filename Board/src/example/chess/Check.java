package example.chess;

import board.Cell;
import board.Index;

public class Check {

	public static boolean isKingCheck(PlayerColor playerColor, Cell[][] board) {
		Index kingPosition;
		PlayerColor opponentColor = (playerColor.equals(PlayerColor.WHITE)) ? PlayerColor.BLACK : PlayerColor.WHITE;
		ChessType kingType = (playerColor.equals(PlayerColor.WHITE)) ? ChessType.KING_W : ChessType.KING_B;

		kingPosition = ChessGlobal.piecePositions.getKingPosition(playerColor);

		System.out.println("Pawn check :" + pawnCheck(playerColor, opponentColor, kingPosition));

		System.out.println("Rook check :" + rookCheck(opponentColor, kingType, board, kingPosition));

		System.out.println("Knight check :" + knightCheck(opponentColor, kingType, board, kingPosition));

		System.out.println("Bishop check :" + bishopCheck(opponentColor, kingType, board, kingPosition));

		System.out.println("Queen check :" + queenCheck(opponentColor, kingType, board, kingPosition));

		return false;
	}

	/**
	 * That method verify if a Pawn checked the opponent king
	 * 
	 * @param playerColor
	 * @param opponentColor
	 * @param kingPosition
	 * @return
	 */
	private static boolean pawnCheck(PlayerColor playerColor, PlayerColor opponentColor, Index kingPosition) {

		int kingPosX = kingPosition.getX();
		int kingPosY = kingPosition.getY();

		if (playerColor.equals(PlayerColor.WHITE)) {
			for (Index pawn : ChessGlobal.piecePositions.getPawnPosition(opponentColor)) {
				if (pawn.getX() == kingPosX - 1) {
					if (pawn.getY() == kingPosY - 1 || pawn.getY() == kingPosY - 1) {
						return true;
					}
				}
			}
		} else {
			for (Index pawn : ChessGlobal.piecePositions.getPawnPosition(opponentColor)) {
				if (pawn.getX() == kingPosX + 1) {
					if (pawn.getY() == kingPosY - 1 || pawn.getY() == kingPosY - 1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * That method verify if a Rook checked the opponent king
	 * 
	 * To know if a rook checked the opponent king we have two part : First we
	 * check if the rook is on the same row or column as the king Second we
	 * check if we doesn't have any piece between the rook and the king
	 * 
	 * @param opponentColor
	 * @param kingType
	 * @param board
	 * @param kingPosition
	 * @return True if one of the rook can take the king
	 */
	private static boolean rookCheck(PlayerColor opponentColor, ChessType kingType, Cell[][] board,
			Index kingPosition) {
		int tempX, tempY;
		for (Index rook : ChessGlobal.piecePositions.getRookPosition(opponentColor)) {
			if (rook.isOnTheTopAndSameColumn(kingPosition)) {
				tempX = rook.getX();
				tempY = rook.getY();
				while (tempX + 1 < 8 && board[tempX + 1][tempY].getAgentType().equals(ChessType.EMPTY_CELL)) {
					tempX++;
				}
				if (board[tempX + 1][tempY].getAgentType().equals(kingType)) {
					return true;
				}
			} else if (rook.isOnTheBottomAndsameColumn(kingPosition)) {
				tempX = rook.getX();
				tempY = rook.getY();
				while (tempX - 1 >= 0 && board[tempX - 1][tempY].getAgentType().equals(ChessType.EMPTY_CELL)) {
					tempX--;
				}
				if (board[tempX - 1][tempY].getAgentType().equals(kingType)) {
					return true;
				}
			} else if (rook.isOnTheLeftAndSameRow(kingPosition)) {
				tempX = rook.getX();
				tempY = rook.getY();
				while (tempY + 1 < 8 && board[tempX][tempY + 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
					tempY++;
				}
				if (board[tempX][tempY + 1].getAgentType().equals(kingType)) {
					return true;
				}
			} else if (rook.isOnTheRightAndSameRow(kingPosition)) {
				tempX = rook.getX();
				tempY = rook.getY();
				while (tempY - 1 >= 0 && board[tempX][tempY - 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
					tempY--;
				}
				if (board[tempX][tempY - 1].getAgentType().equals(kingType)) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * That method verify if a Knight checked the opponent king
	 * 
	 * @param opponentColor
	 * @param kingType
	 * @param board
	 * @param kingPosition
	 * @return
	 */
	private static boolean knightCheck(PlayerColor opponentColor, ChessType kingType, Cell[][] board,
			Index kingPosition) {

		for (Index knight : ChessGlobal.piecePositions.getKnightPosition(opponentColor)) {
			if (knight.getX() - 1 >= 0 && knight.getY() - 2 >= 0
					&& (board[knight.getX() - 1][knight.getY() - 2].getAgentType().equals(kingType))) {
				return true;
			}

			// Check if cell in left-2 bottom-1 contain opponent king
			if (knight.getX() + 1 < 8 && knight.getY() - 2 >= 0
					&& (board[knight.getX() + 1][knight.getY() - 2].getAgentType().equals(kingType))) {
				return true;
			}

			// Check if cell in left-1 top-2 contain opponent king
			if (knight.getX() - 2 >= 0 && knight.getY() - 1 >= 0
					&& (board[knight.getX() - 2][knight.getY() - 1].getAgentType().equals(kingType))) {
				return true;
			}

			// Check if cell in right-1 top-2 contain opponent king
			if (knight.getX() - 2 >= 0 && knight.getY() + 1 < 8
					&& (board[knight.getX() - 2][knight.getY() + 1].getAgentType().equals(kingType))) {
				return true;
			}

			// Check if cell in right-2 top-1 contain opponent king
			if (knight.getX() - 1 >= 0 && knight.getY() + 2 < 8
					&& (board[knight.getX() - 1][knight.getY() + 2].getAgentType().equals(kingType))) {
				return true;
			}

			// Check if cell in right-2 bottom-1 contain opponent king
			if (knight.getX() + 1 < 8 && knight.getY() + 2 < 8
					&& (board[knight.getX() + 1][knight.getY() + 2].getAgentType().equals(kingType))) {
				return true;
			}

			// Check if cell in left-1 bottom-2 contain opponent king
			if (knight.getX() + 2 < 8 && knight.getY() - 1 >= 0
					&& (board[knight.getX() + 2][knight.getY() - 1].getAgentType().equals(kingType))) {
				return true;
			}

			// Check if cell in right-1 bottom-2 contain opponent king
			if (knight.getX() + 2 < 8 && knight.getY() + 1 < 8
					&& (board[knight.getX() + 2][knight.getY() + 1].getAgentType().equals(kingType))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * That method verify if a Bishop checked the opponent king
	 * 
	 * @param opponentColor
	 * @param kingType
	 * @param board
	 * @param kingPosition
	 * @return
	 */
	private static boolean bishopCheck(PlayerColor opponentColor, ChessType kingType, Cell[][] board,
			Index kingPosition) {
		int tempX, tempY;

		for (Index bishop : ChessGlobal.piecePositions.getBishopPosition(opponentColor)) {

			/*
			 * To know if a cell is on the same Top-Left to Bottom-Right
			 * diagonal on an other cell we have to check if the difference
			 * between the x coordinates are equal to the difference between the
			 * y coordinates of the two cells
			 */
			if (bishop.getX() - kingPosition.getX() == bishop.getY() - kingPosition.getY()) {
				tempX = bishop.getX();
				tempY = bishop.getY();
				if (bishop.isOnTheTop(kingPosition)) {
					while (tempX + 1 < 8 && tempY + 1 < 8
							&& board[tempX + 1][tempY + 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
						tempX++;
						tempY++;
					}
					if (board[tempX + 1][tempY + 1].getAgentType().equals(kingType)) {
						return true;
					}
				} else {
					while (tempX - 1 >= 0 && tempY - 1 >= 0
							&& board[tempX - 1][tempY - 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
						tempX--;
						tempY--;
					}
					if (board[tempX - 1][tempY - 1].getAgentType().equals(kingType)) {
						return true;
					}
				}
			} else if (bishop.getX() + bishop.getY() == kingPosition.getX() + kingPosition.getY()) {
				/*
				 * To know if a cell is on the same Bottom-Left to Top-Right
				 * diagonal on an other cell we have to check if the sum of the
				 * two coordinates of the first cell is equal to the sum of the
				 * coordinates to the second cell
				 */
				tempX = bishop.getX();
				tempY = bishop.getY();
				if (bishop.isOnTheTop(kingPosition)) {
					while (tempX + 1 < 8 && tempY - 1 >= 0
							&& board[tempX + 1][tempY - 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
						tempX++;
						tempY--;
					}
					if (board[tempX + 1][tempY - 1].getAgentType().equals(kingType)) {
						return true;
					}
				} else {
					while (tempX - 1 >= 0 && tempY + 1 < 8
							&& board[tempX - 1][tempY + 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
						tempX--;
						tempY++;
					}
					if (board[tempX - 1][tempY + 1].getAgentType().equals(kingType)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean queenCheck(PlayerColor opponentColor, ChessType kingType, Cell[][] board,
			Index kingPosition) {
		int tempX, tempY;
		Index queen = ChessGlobal.piecePositions.getQueenPosition(opponentColor);

		if (queen.isOnTheTopAndSameColumn(kingPosition)) {
			tempX = queen.getX();
			tempY = queen.getY();
			while (tempX + 1 < 8 && board[tempX + 1][tempY].getAgentType().equals(ChessType.EMPTY_CELL)) {
				tempX++;
			}
			if (board[tempX + 1][tempY].getAgentType().equals(kingType)) {
				return true;
			}
		} else if (queen.isOnTheBottomAndsameColumn(kingPosition)) {
			tempX = queen.getX();
			tempY = queen.getY();
			while (tempX - 1 >= 0 && board[tempX - 1][tempY].getAgentType().equals(ChessType.EMPTY_CELL)) {
				tempX--;
			}
			if (board[tempX - 1][tempY].getAgentType().equals(kingType)) {
				return true;
			}
		} else if (queen.isOnTheLeftAndSameRow(kingPosition)) {
			tempX = queen.getX();
			tempY = queen.getY();
			while (tempY + 1 < 8 && board[tempX][tempY + 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
				tempY++;
			}
			if (board[tempX][tempY + 1].getAgentType().equals(kingType)) {
				return true;
			}
		} else if (queen.isOnTheRightAndSameRow(kingPosition)) {
			tempX = queen.getX();
			tempY = queen.getY();
			while (tempY - 1 >= 0 && board[tempX][tempY - 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
				tempY--;
			}
			if (board[tempX][tempY - 1].getAgentType().equals(kingType)) {
				return true;
			}
		}

		// Bishop Check
		tempX = queen.getX();
		tempY = queen.getY();
		if (queen.getX() - kingPosition.getX() == queen.getY() - kingPosition.getY()) {
			tempX = queen.getX();
			tempY = queen.getY();
			if (queen.isOnTheTop(kingPosition)) {
				while (tempX + 1 < 8 && tempY + 1 < 8
						&& board[tempX + 1][tempY + 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
					tempX++;
					tempY++;
				}
				if (board[tempX + 1][tempY + 1].getAgentType().equals(kingType)) {
					return true;
				}
			} else {
				while (tempX - 1 >= 0 && tempY - 1 >= 0
						&& board[tempX - 1][tempY - 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
					tempX--;
					tempY--;
				}
				if (board[tempX - 1][tempY - 1].getAgentType().equals(kingType)) {
					return true;
				}
			}
		} else if (queen.getX() + queen.getY() == kingPosition.getX() + kingPosition.getY()) {
			/*
			 * To know if a cell is on the same Bottom-Left to Top-Right
			 * diagonal on an other cell we have to check if the sum of the two
			 * coordinates of the first cell is equal to the sum of the
			 * coordinates to the second cell
			 */
			tempX = queen.getX();
			tempY = queen.getY();
			if (queen.isOnTheTop(kingPosition)) {
				while (tempX + 1 < 8 && tempY - 1 >= 0
						&& board[tempX + 1][tempY - 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
					tempX++;
					tempY--;
				}
				if (board[tempX + 1][tempY - 1].getAgentType().equals(kingType)) {
					return true;
				}
			} else {
				while (tempX - 1 >= 0 && tempY + 1 < 8
						&& board[tempX - 1][tempY + 1].getAgentType().equals(ChessType.EMPTY_CELL)) {
					tempX--;
					tempY++;
				}
				if (board[tempX - 1][tempY + 1].getAgentType().equals(kingType)) {
					return true;
				}
			}
		}
		return false;

	}
}
