package board;

import java.util.HashMap;

import design.Area;
import exception.BoardException;
import exception.ExceptionCheckEnum;
import exception.TypeException;
import global.GlobalValues;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board {
	private int row;
	private int column;
	
	private BorderPane globalPane;
	private GridPane boardPane;
	private Cell[][] board = null;
	private Area areaTop;
	private Area areaBot;
	private Area areaLeft;
	private Area areaRight;

	/**
	 * This class is designed to create easily a custom board. You can customize
	 * the number of row and column and the size of cells. You can specify a
	 * list of cells type. Each cell type can have an image or color and
	 * background image or color.
	 * 
	 */

	/**
	 * 
	 * @param cellWidth
	 *            width of each cells
	 * @param cellHeight
	 *            height of each cell
	 * @param emptyAgentType The default agent type who represent empty cell
	 * @param backgroundBoard
	 *            array who represent the background board, each cell can have a
	 *            type, who must be contain in the cellType list
	 * @param agentBoard
	 *            array who represent the board, each cell can have a type, who
	 *            must be contain in the cellType list
	 * @param colorMap
	 *            map who associate a cellType to a color
	 * @param imageMap
	 *            map who associate a cellType to an image
	 * @param areaTop
	 * @param areaBot
	 * @param areaLeft
	 * @param areaRight
	 * @throws BoardException
	 */
	@SuppressWarnings("unchecked")
	public Board(int cellWidth, int cellHeight,Enum<?>[][] backgroundBoard, Enum<?>[][] agentBoard,
			HashMap<?, Color> colorMap, HashMap<?, Image> imageMap,Area areaTop, Area areaBot, Area areaLeft,
			Area areaRight) throws BoardException {

		// XXX : is it really useful to send area ?

		this.row = backgroundBoard.length;
		this.column = backgroundBoard[0].length;

		this.areaTop = areaTop;
		this.areaBot = areaBot;
		this.areaLeft = areaLeft;
		this.areaRight = areaRight;

		GlobalValues.CELL_HEIGHT = cellHeight;
		GlobalValues.CELL_WIDTH = cellWidth;

		GlobalValues.DESIGN_COLOR = (HashMap<Enum<?>, Color>) colorMap;
		GlobalValues.DESIGN_IMAGE = (HashMap<Enum<?>, Image>) imageMap;
		
		/// XXX : currently agent board,background board, color map and image
		/// map must have the same enum type but it's not really developer
		/// friendly i have to make change for check each type in each board and
		/// map

		ExceptionCheckEnum.checkError(row, column, backgroundBoard, agentBoard);

		this.globalPane = new BorderPane();
		board = new Cell[row][column];
		this.boardPane = new GridPane();

		boardPane.setMinSize(GlobalValues.CELL_WIDTH * column, GlobalValues.CELL_HEIGHT * row);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				if (agentBoard != null) {
					board[i][j] = new Cell(backgroundBoard[i][j], agentBoard[i][j],i,j);
				} else {
					board[i][j] = new Cell(backgroundBoard[i][j], null,i,j);
				}
				boardPane.add(board[i][j], j, i);
			}
		}

		globalPane.setCenter(boardPane);

		globalPane.setMinSize(boardPane.getMinWidth() + getAreaWidth(), boardPane.getMinHeight() + getAreaHeight());

	}
	
	public Cell[][] getBoard(){
		return board;
	}

	/**
	 * This function return a border pane who contain the Board in center
	 * 
	 * @return
	 */
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

				boardPane.setMinSize((GlobalValues.CELL_WIDTH + (GlobalValues.CELL_STROKE_WIDTH)) * column,
						(GlobalValues.CELL_HEIGHT + (GlobalValues.CELL_STROKE_WIDTH)) * row);
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
