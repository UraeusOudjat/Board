package Exception;

import global.GlobalValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javafx.scene.image.Image;

public class ExceptionCheck {

	public static void checkError(int row, int column,
			String[][] backgroundBoard, String[][] agentBoard,
			ArrayList<String> cellType) throws BoardException {
		if (row <= 0 || column <= 0) {
			throw new BoardException(TypeException.BOARD_INCORECT_VALUES);
		} else if (cellType == null) {
			throw new BoardException(TypeException.CELL_TYPE_UNDEFINED);

		} else if (!typeBoardIsGrid(backgroundBoard)) {
			throw new BoardException(TypeException.BACKGROUND_BOARD_GRID);

		} else if (backgroundBoard.length != row
				|| backgroundBoard[0].length != column) {
			throw new BoardException(TypeException.BACKGROUND_BORD_SIZE);

		} else if (boardContainNullValues(backgroundBoard)) {
			throw new BoardException(TypeException.BACKGROUND_BOARD_EMPTY);

		} else if (!boardMatch(backgroundBoard, cellType)) {
			throw new BoardException(TypeException.BACKGROUND_BOARD_TYPE);

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

			} else if (!boardMatch(agentBoard, cellType)) {
				throw new BoardException(TypeException.AGENT_BOARD_TYPE);
			}
		}
		if (GlobalValues.DESIGN_COLOR != null) {
			if (!mapTypeMatch(GlobalValues.DESIGN_COLOR, cellType)) {
				throw new BoardException(TypeException.COLOR_MAP_TYPE);

			} else if (mapContainNullValues(GlobalValues.DESIGN_COLOR)) {
				throw new BoardException(TypeException.COLOR_MAP_EMPTY);

			}
		}

		if (GlobalValues.DESIGN_IMAGE != null) {
			if (!mapTypeMatch(GlobalValues.DESIGN_IMAGE, cellType)) {
				throw new BoardException(TypeException.IMAGE_MAP_TYPE);

			} else if (mapContainNullValues(GlobalValues.DESIGN_IMAGE)) {
				throw new BoardException(TypeException.IMAGE_MAP_EMPTY);
			}
		}

	}

	private static boolean typeBoardIsGrid(String[][] typeBoard) {
		int line = typeBoard.length;

		for (int i = 0; i < typeBoard.length; i++) {
			if (typeBoard[i].length != line) {
				return false;
			}
		}
		return true;
	}

	private static boolean mapTypeMatch(HashMap<String, ?> map,
			ArrayList<String> cellType) {
		if (map != null) {
			for (String type : map.keySet()) {
				if (!cellType.contains(type)) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean boardMatch(String[][] board,
			ArrayList<String> cellType) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!cellType.contains(board[i][j])) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean boardContainNullValues(String[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == null) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean mapContainNullValues(HashMap<String, ?> map) {
		Iterator<?> it = map.entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry pair = (Map.Entry) it.next();
			if (pair.getValue() == null) {
				return true;
			}
		}
		return false;
	}

}
