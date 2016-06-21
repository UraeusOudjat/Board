package board;

import java.util.ArrayList;
import java.util.HashMap;

import Exception.BoardException;
import Exception.TypeException;
import design.Area;
import global.GlobalValues;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board {
	private int row;
	private int column;
	private ArrayList<String> cellType;
	private Cell[][] board;
	private BorderPane globalPane;
	private GridPane boardPane;

	private Area areaTop;
	private Area areaBot;
	private Area areaLeft;
	private Area areaRight;

	public Board(int row, int column, int cellWidth, int cellHeight,
			ArrayList<String> cellType, Area areaTop, Area areaBot,
			Area areaLeft, Area areaRight, String[][] typeBoard,
			HashMap<String, Color> colorMap, HashMap<String, Image> imageMap)
			throws BoardException {

		if (row <= 0 || column <= 0) {
			throw new BoardException(TypeException.BOARD_INCORECT_VALUES);
		} else if (!typeBoardIsGrid(typeBoard)) {
			throw new BoardException(TypeException.TYPE_BOARD_GRID);
		} else if (typeBoard.length != row || typeBoard[0].length != column) {
			throw new BoardException(TypeException.TYPE_BORD_SIZE);
		} else if (colorMap == null && imageMap == null) {
			throw new BoardException(TypeException.DESIGN_WAS_NOT_SPECIFIED);
		} else if (!mapTypeMatch(colorMap, cellType)) {
			throw new BoardException(TypeException.COLOR_MAP_TYPE);
		} else if (!mapTypeMatch(imageMap, cellType)) {
			throw new BoardException(TypeException.COLOR_MAP_TYPE);
		} else if (!typeBoardMatch(typeBoard, cellType)) {
			throw new BoardException(TypeException.TYPE_BOARD_TYPE);
		}

		this.globalPane = new BorderPane();
		board = new Cell[row][column];
		this.boardPane = new GridPane();
		GlobalValues.CELL_HEIGHT = cellHeight;
		GlobalValues.CELL_WIDTH = cellWidth;

		GlobalValues.DESIGN_COLOR = colorMap;
		GlobalValues.DESIGN_IMAGE = imageMap;

		boardPane.setPrefSize(GlobalValues.CELL_WIDTH * column,
				GlobalValues.CELL_HEIGHT * row);

		this.row = row;
		this.column = column;
		this.cellType = cellType;

		this.areaTop = areaTop;
		this.areaBot = areaBot;
		this.areaLeft = areaLeft;
		this.areaRight = areaRight;

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				board[i][j] = new Cell(typeBoard[i][j]);
				boardPane.add(board[i][j].getDesign(), j, i);
			}
		}

		globalPane.setCenter(boardPane);

		globalPane.setPrefSize(boardPane.getPrefWidth() + getAreaWidth(),
				boardPane.getPrefHeight() + getAreaHeight());

	}

	public BorderPane getGlobalPane() {
		return globalPane;
	}

	public double getWidth() {
		return globalPane.getPrefWidth();
	}

	public double getHeight() {
		return globalPane.getPrefHeight();
	}

	public void setCellStroke(double width, Color color) throws BoardException {
		if (width > 0) {
			if (color != null) {
				GlobalValues.CELL_STROKE = true;
				GlobalValues.CELL_STROKE_COLOR = color;
				GlobalValues.CELL_STROKE_WIDTH = width;

				boardPane
						.setPrefSize(
								(GlobalValues.CELL_WIDTH + (GlobalValues.CELL_STROKE_WIDTH))
										* column,
								(GlobalValues.CELL_HEIGHT + (GlobalValues.CELL_STROKE_WIDTH))
										* row);
				globalPane.setPrefSize(boardPane.getPrefWidth()
						+ getAreaWidth(), boardPane.getPrefHeight()
						+ getAreaHeight());
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < column; j++) {
						board[i][j].setStroke();
					}
				}
			} else {
				throw new BoardException(TypeException.STROKE_COLOR);
			}
		} else {
			throw new BoardException(TypeException.STROKE_WIDTH);
		}

	}

	private boolean typeBoardIsGrid(String[][] typeBoard) {
		int line = typeBoard.length;

		for (int i = 0; i < typeBoard.length; i++) {
			if (typeBoard[i].length != line) {
				return false;
			}
		}
		return true;
	}

	private boolean mapTypeMatch(HashMap<String, ?> map,
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

	private boolean typeBoardMatch(String[][] typeBoard,
			ArrayList<String> cellType) {
		for (int i = 0; i < typeBoard.length; i++) {
			for (int j = 0; j < typeBoard[0].length; j++) {
				if (!cellType.contains(typeBoard[i][j])) {
					return false;
				}
			}
		}
		return true;
	}

	private double getAreaWidth() {
		double areaWidth = 0;

		if (areaLeft != null) {
			globalPane.setTop(areaLeft.getArea());
			areaWidth += areaLeft.getWidth();
		}
		if (areaRight != null) {
			globalPane.setTop(areaRight.getArea());
			areaWidth += areaRight.getWidth();
		}
		return areaWidth;
	}

	private double getAreaHeight() {
		double areaHeight = 0;

		if (areaTop != null) {
			globalPane.setTop(areaTop.getArea());
			areaHeight += areaTop.getHeight();
		}
		if (areaBot != null) {
			globalPane.setTop(areaBot.getArea());
			areaHeight += areaBot.getHeight();
		}
		return areaHeight;
	}

}
