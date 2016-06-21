package board;

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
	private String[] cellType;
	private Cell[][] board;
	private BorderPane globalPane;
	private GridPane boardPane;
	
	private Area areaTop;
	private Area areaBot;
	private Area areaLeft;
	private Area areaRight;
	
	
	public Board(int row, int column, int cellWidth, int cellHeight,String[] cellType, Area areaTop,Area areaBot, Area areaLeft, Area areaRight, String[][] typeBoard, HashMap<String, Color> colorMap, HashMap<String, Image> imageMap) throws BoardException {
		
		if(row <= 0 || column <= 0){
			throw new BoardException(TypeException.INCORECT_VALUE);
		}else if(!typeBoardIsGrid(typeBoard)){
			throw new BoardException(TypeException.TYPE_BOARD_GRID);
		}else if(typeBoard.length != row || typeBoard[0].length != column){
			throw new BoardException(TypeException.UNMATCH_VALUE);
		}
		
		this.globalPane = new BorderPane();
		board = new Cell[row][column];
		this.boardPane = new GridPane();
		GlobalValues.CELL_HEIGHT = cellHeight;
		GlobalValues.CELL_WIDTH = cellWidth;
		
		boardPane.setPrefSize(GlobalValues.CELL_WIDTH*column, GlobalValues.CELL_HEIGHT*row);
		boardPane.setGridLinesVisible(true);
		
		this.row = row;
		this.column = column;
		this.cellType = cellType;

		this.areaTop = areaTop;
		this.areaBot = areaBot;
		this.areaLeft = areaLeft;
		this.areaRight = areaRight;
		
		
		
		
		
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				
				Color c = Color.BLUE;
				if(i==0 || j ==0 || i ==row-1 || j==column-1){
					c = Color.RED;
				}
				
				board[i][j]= new Cell(typeBoard[i][j]);
				boardPane.add(board[i][j].getRectangle(), j, i);
			}
		}
		double areaWidth = 0;
		double areaHeight = 0;
		
		if(areaTop != null){
			globalPane.setTop(areaTop.getArea());
			areaHeight+= areaTop.getHeight();
		}
		if(areaBot != null){
			globalPane.setTop(areaBot.getArea());
			areaHeight+= areaBot.getHeight();
		}
		if(areaLeft != null){
			globalPane.setTop(areaLeft.getArea());
			areaWidth+= areaLeft.getWidth();
		}
		if(areaRight != null){
			globalPane.setTop(areaRight.getArea());
			areaWidth+= areaRight.getWidth();
		}
		
		globalPane.setCenter(boardPane);
		
		globalPane.setPrefSize(boardPane.getPrefWidth()+areaWidth, boardPane.getPrefHeight()+areaHeight);
		
	}

	public BorderPane getGlobalPane() {
		return globalPane;
	}

	//TODO : a supprimer , utile pour le test
	public GridPane getBoardPane() {
		return boardPane;
	}
	
	private boolean typeBoardIsGrid(String[][] typeBoard){
		int line = typeBoard.length;
		
		for(int i=0;i<typeBoard.length;i++){
			if(typeBoard[i].length != line){
				return false;
			}
		}
		return true;
	}

	
}