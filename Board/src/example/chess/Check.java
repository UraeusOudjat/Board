package example.chess;

import board.Cell;
import board.Index;

public class Check {

	private static boolean kingWhiteCheck = false;
	private static boolean kingBlackCheck = false;
	
	
	//TODO : Add 2 method isWhiteKingCheck and isBlackKingCheck to remove the confusion and to reduce the computing of opponent color
	public static boolean isKingCheck(PlayerColor playerColor){
		if(playerColor.equals(PlayerColor.WHITE))
			return kingWhiteCheck;
		else
			return kingBlackCheck;
	}
	
	
	
	//TODO : Add 2 method verifyWhiteKingCheck and verifyBlackKingCheck to remove the confusion and to reduce the computing of opponent color
	/**
	 * This method verify if the king of the player are check
	 * @param playerColor
	 * @param board
	 * @return
	 */
	public static void verifyKingCheck(PlayerColor playerColor, Cell[][] board) {
		Index kingPosition;
		PlayerColor opponentColor = (playerColor.equals(PlayerColor.WHITE)) ? PlayerColor.BLACK : PlayerColor.WHITE;
		ChessType kingType = (playerColor.equals(PlayerColor.WHITE)) ? ChessType.KING_W : ChessType.KING_B;

		
		System.out.println("on verifie si les pièces "+ opponentColor.toString()+ " mettent en echec les pieces "+ playerColor.toString());
		
		boolean check = false;
		
		kingPosition = ChessGlobal.piecePositions.getKingPosition(playerColor);

		if(pawnCheck(playerColor, opponentColor, kingPosition) || 
				rookCheck(opponentColor, kingType, board, kingPosition) || 
				knightCheck(opponentColor, kingType, board, kingPosition) || 
				bishopCheck(opponentColor, kingType, board, kingPosition) || 
				queenCheck(opponentColor, kingType, board, kingPosition))
			check = true;
		else
			check = false;

		if(playerColor.equals(PlayerColor.WHITE)){
			kingWhiteCheck = check;
		}else{
			kingBlackCheck = check;
		}
	}

	/**
	 * That method verify if an opponent Pawn checked the king
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
	 * That method verify if an opponent Rook checked the king
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
	 * That method verify if an opponent Knight checked the king
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
	 * That method verify if an opponent Bishop checked the king
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

	
	/**
	 * That method verify if an opponent Queen checked the king
	 * 
	 * @param opponentColor
	 * @param kingType
	 * @param board
	 * @param kingPosition
	 * @return
	 */
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
	
	
	/**
	 * This method verify if the move of a piece cancel the actual check of the king
	 * 
	 * @param playerColor
	 * @param tempBoard
	 * @return
	 */
	public static boolean moveCancelCheck(PlayerColor playerColor, Cell[][] board, Index actualIndex, Index futureIndex){

		// We save the agent type of the future cell because we simulate a move who put another piece on this cell
		ChessType typeOfFutureIndexCell = (ChessType) board[futureIndex.getX()][futureIndex.getY()].getAgentType();
		
		board[futureIndex.getX()][futureIndex.getY()].setAgentType(board[actualIndex.getX()][actualIndex.getY()].getAgentType());
		board[actualIndex.getX()][actualIndex.getY()].setAgentType(ChessType.EMPTY_CELL);		
	
	
		// FIXME : The problem occur when a pawn need to kill an opponent piece
		// so we need to remove the piece into the chessglobal but after we need
		// to read after the simulation and the "prise en passant" is a real
		// problem
		
		verifyKingCheck(playerColor, board);
		
		boolean kingCheckAfterSimulateMove = (playerColor.equals(PlayerColor.WHITE))? kingWhiteCheck : kingBlackCheck;
		
		board[actualIndex.getX()][actualIndex.getY()].setAgentType(board[futureIndex.getX()][futureIndex.getY()].getAgentType());
		board[futureIndex.getX()][futureIndex.getY()].setAgentType(typeOfFutureIndexCell);
			
		
		/* if the move can cancel the check we reinitialize the check to true
		 * because we simulate the move of a piece who cancel the check so the
		 * actual state is kingcheck = false but the move doesn't append yet so
		 * after verify if the check can be cancel we reset the state before the
		 * simulate move.
		 */
		if(playerColor.equals(PlayerColor.WHITE))
			kingWhiteCheck = true;
		else
			kingBlackCheck = true;
		
		// If the king is checked after the simulate move we return false because the simulate move doesn't cancel the check
		return (kingCheckAfterSimulateMove) ? false : true;
	}
	
	
}
