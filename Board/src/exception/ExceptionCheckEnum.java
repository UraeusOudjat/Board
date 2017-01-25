package exception;

import global.GlobalValues;

public class ExceptionCheckEnum {

	//FIXME :  It not raise error if they are enum type who are not attribute in color map AND image map
	
	public static void checkError(int row, int column,
			Enum<?>[][] backgroundBoard, Enum<?>[][] agentBoard) throws BoardException {
		if (row <= 0 || column <= 0) {
			throw new BoardException(TypeException.BOARD_INCORECT_VALUES);
		} else if (!typeBoardIsGrid(backgroundBoard)) {
			throw new BoardException(TypeException.BACKGROUND_BOARD_GRID);

		} else if (backgroundBoard.length != row
				|| backgroundBoard[0].length != column) {
			throw new BoardException(TypeException.BACKGROUND_BORD_SIZE);

		} else if (boardContainNullValues(backgroundBoard)) {
			throw new BoardException(TypeException.BACKGROUND_BOARD_EMPTY);

		} else if (GlobalValues.DESIGN_COLOR == null
				&& GlobalValues.DESIGN_IMAGE == null) {
			throw new BoardException(TypeException.DESIGN_WAS_NOT_SPECIFIED);

		}

		if (agentBoard != null) {
			if (!typeBoardIsGrid(agentBoard)) {
				throw new BoardException(TypeException.AGENT_BOARD_GRID);

			} else if (agentBoard.length != row
					|| agentBoard[0].length != column) {
				throw new BoardException(TypeException.AGENT_BORD_SIZE);

			} else if (boardContainNullValues(agentBoard)) {
				throw new BoardException(TypeException.AGENT_BOARD_EMPTY);

			} 
		}
		
		if(!agentBoard.getClass().equals(backgroundBoard.getClass())){
			throw new BoardException(TypeException.AGENT_BOARD_BACKGROUND_BOARD_TYPE_UNMATCH);
		}
		
		
		if (GlobalValues.DESIGN_COLOR != null) {
			if(!agentBoard[0][0].getClass().equals(GlobalValues.DESIGN_COLOR.keySet().iterator().next().getClass())){
				throw new BoardException(TypeException.AGENT_BOARD_DESIGN_COLOR_TYPE_UNMATCH);
			}
		}
		
		if (GlobalValues.DESIGN_IMAGE != null) {
			if(!agentBoard[0][0].getClass().equals(GlobalValues.DESIGN_IMAGE.keySet().iterator().next().getClass())){
				throw new BoardException(TypeException.AGENT_BOARD_DESIGN_IMAGE_TYPE_UNMATCH);
			}
		}
		
		if(GlobalValues.DESIGN_COLOR != null && GlobalValues.DESIGN_IMAGE != null){
			if(!GlobalValues.DESIGN_COLOR.keySet().iterator().next().getClass().equals(GlobalValues.DESIGN_IMAGE.keySet().iterator().next().getClass())){
				throw new BoardException(TypeException.DESIGN_COLOR_DESIGN_IMAGE_TYPE_UNMATCH);
			}
		}
		
	}

	private static boolean typeBoardIsGrid(Enum<?>[][] typeBoard) {
		int line = typeBoard.length;

		for (int i = 0; i < typeBoard.length; i++) {
			if (typeBoard[i].length != line) {
				return false;
			}
		}
		return true;
	}

	

	private static boolean boardContainNullValues(Enum<?>[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == null) {
					return true;
				}
			}
		}
		return false;
	}

	

}
