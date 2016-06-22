package board;

import java.util.ArrayList;
import java.util.HashMap;

import Exception.BoardException;
import Exception.ExceptionCheck;
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

	private String[][] backgroundBoard;
	private String[][] agentBoard;
	
	private Area areaTop;
	private Area areaBot;
	private Area areaLeft;
	private Area areaRight;

	public Board() {
		row = 5;
		column = 5;

		this.globalPane = new BorderPane();
		board = new Cell[row][column];
		this.boardPane = new GridPane();
		GlobalValues.CELL_HEIGHT = 100;
		GlobalValues.CELL_WIDTH = 100;

		HashMap<String, Color> colorMap = new HashMap<>();
		colorMap.put("empty", Color.WHITE);
		colorMap.put("wall", Color.BROWN);
		GlobalValues.DESIGN_COLOR = colorMap;

		boardPane.setMinSize(GlobalValues.CELL_WIDTH * column,
				GlobalValues.CELL_HEIGHT * row);

		this.areaTop = null;
		this.areaBot = null;
		this.areaLeft = null;
		this.areaRight = null;

		String[][] backgroundBoard = new String[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (i == 0 || j == 0 || i == row - 1 || j == column - 1) {
					backgroundBoard[i][j] = "wall";
				} else {
					backgroundBoard[i][j] = "empty";
				}
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				board[i][j] = new Cell(backgroundBoard[i][j], "");
				boardPane.add(board[i][j].getDesign(), j, i);
			}
		}

		globalPane.setCenter(boardPane);

		globalPane.setMinSize(boardPane.getMinWidth() + getAreaWidth(),
				boardPane.getMinHeight() + getAreaHeight());

	}

	public Board(int row, int column, int cellWidth, int cellHeight,
			ArrayList<String> cellType, String[][] backgroundBoard,
			String[][] agentBoard, HashMap<String, Color> colorMap,
			HashMap<String, Image> imageMap, Area areaTop, Area areaBot,
			Area areaLeft, Area areaRight) throws BoardException {

		
		this.row = row;
		this.column = column;
		this.cellType = cellType;

		this.backgroundBoard = backgroundBoard;
		this.agentBoard = agentBoard;
		
		this.areaTop = areaTop;
		this.areaBot = areaBot;
		this.areaLeft = areaLeft;
		this.areaRight = areaRight;
		
		GlobalValues.CELL_HEIGHT = cellHeight;
		GlobalValues.CELL_WIDTH = cellWidth;

		GlobalValues.DESIGN_COLOR = colorMap;
		GlobalValues.DESIGN_IMAGE = imageMap;
		
		ExceptionCheck.checkError(row, column, backgroundBoard, agentBoard, cellType);

		this.globalPane = new BorderPane();
		board = new Cell[row][column];
		this.boardPane = new GridPane();
	

		boardPane.setMinSize(GlobalValues.CELL_WIDTH * column,
				GlobalValues.CELL_HEIGHT * row);

		

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if(agentBoard!= null){
					board[i][j] = new Cell(backgroundBoard[i][j], agentBoard[i][j]);
				}else{
					board[i][j] = new Cell(backgroundBoard[i][j], null);
				}
				boardPane.add(board[i][j].getDesign(), j, i);
			}
		}

		globalPane.setCenter(boardPane);

		globalPane.setMinSize(boardPane.getMinWidth() + getAreaWidth(),
				boardPane.getMinHeight() + getAreaHeight());

	}

	public BorderPane getGlobalPane() {
		return globalPane;
	}

	public double getWidth() {
		return globalPane.getMinWidth();
	}

	public double getHeight() {
		return globalPane.getMinHeight();
	}

	public void setCellStroke(double width, Color color) throws BoardException {
		if (width > 0) {
			if (color != null) {
				GlobalValues.CELL_STROKE = true;
				GlobalValues.CELL_STROKE_COLOR = color;
				GlobalValues.CELL_STROKE_WIDTH = width;

				boardPane
						.setMinSize(
								(GlobalValues.CELL_WIDTH + (GlobalValues.CELL_STROKE_WIDTH))
										* column,
								(GlobalValues.CELL_HEIGHT + (GlobalValues.CELL_STROKE_WIDTH))
										* row);
				globalPane.setMinSize(boardPane.getMinWidth() + getAreaWidth(),
						boardPane.getMinHeight() + getAreaHeight());
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
