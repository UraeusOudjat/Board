package example.chess;

import java.util.HashMap;

import board.Board;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChessExampleMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {

			int row = 8, column = 8;
			int cellWidth = 75, cellHeight = 75;
			ChessType[][] backgroundBoard = new ChessType[row][column];

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					if (j % 2 == 0) {
						backgroundBoard[i][j] = (i % 2 == 0) ? ChessType.WHITE_CELL : ChessType.BLACK_CELL;
					} else {
						backgroundBoard[i][j] = (i % 2 == 0) ? ChessType.BLACK_CELL : ChessType.WHITE_CELL;
					}
				}
			}

			ChessType[][] agentBoard = new ChessType[row][column];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					
					if(i==0 || i == row-1){
						
						if(j==0 || j== column-1){
							agentBoard[i][j] = (i == 0)?ChessType.ROOK_B : ChessType.ROOK_W;
							
						}else if(j == 1 || j == column-2 ){
							agentBoard[i][j] = (i == 0)?ChessType.KNIGHT_B : ChessType.KNIGHT_W;
							
						}else if(j == 2 || j == column-3){
							agentBoard[i][j] = (i == 0)?ChessType.BISHOP_B : ChessType.BISHOP_W;
							
						}else if(j == 3){
							agentBoard[i][j] = (i == 0)?ChessType.QUEEN_B : ChessType.QUEEN_W;
							
						}else if(j== 4){
							agentBoard[i][j] = (i == 0)?ChessType.KING_B : ChessType.KING_W;
							
						}
					}else if(i == 1 || i == row-2){
						agentBoard[i][j] = (i == 1)?ChessType.PAWN_B : ChessType.PAWN_W;
					}else{
						agentBoard[i][j] = ChessType.EMPTY_CELL;
					}
				}
			}

			HashMap<ChessType, Color> colorMap = new HashMap<>();
			colorMap.put(ChessType.BLACK_CELL, Color.DIMGRAY);
			colorMap.put(ChessType.WHITE_CELL, Color.BEIGE);
			colorMap.put(ChessType.MOVE_CELL, Color.ORANGERED);
			colorMap.put(ChessType.EMPTY_CELL, Color.TRANSPARENT);

			// It will not display but if they have a modification or an error
			// in the image loading the color will be display intead of the
			// image so it's more safe to chose color for each agent in case of
			// problem
			colorMap.put(ChessType.PAWN_B, Color.DARKORANGE);
			colorMap.put(ChessType.ROOK_B, Color.DARKGOLDENROD);
			colorMap.put(ChessType.KNIGHT_B, Color.DARKCYAN);
			colorMap.put(ChessType.BISHOP_B, Color.DARKMAGENTA);
			colorMap.put(ChessType.QUEEN_B, Color.DARKGREEN);
			colorMap.put(ChessType.KING_B, Color.DARKRED);
			
			colorMap.put(ChessType.PAWN_W, Color.ORANGE);
			colorMap.put(ChessType.ROOK_W, Color.GOLDENROD);
			colorMap.put(ChessType.KNIGHT_W, Color.CYAN);
			colorMap.put(ChessType.BISHOP_W, Color.MAGENTA);
			colorMap.put(ChessType.QUEEN_W, Color.GREEN);
			colorMap.put(ChessType.KING_W, Color.RED);

			HashMap<ChessType, Image> imageMap = new HashMap<>();

			Image pawnBlack = new Image("/example/img/pawn_black.png");
			Image rookBlack = new Image("/example/img/rook_black.png");
			Image knightBlack = new Image("/example/img/knight_black.png");
			Image bishopBlack = new Image("/example/img/bishop_black.png");
			Image queenBlack = new Image("/example/img/queen_black.png");
			Image kingBlack = new Image("/example/img/king_black.png");

			Image pawnWhite = new Image("/example/img/pawn_white.png");
			Image rookWhite = new Image("/example/img/rook_white.png");
			Image knightWhite = new Image("/example/img/knight_white.png");
			Image bishopWhite = new Image("/example/img/bishop_white.png");
			Image queenWhite = new Image("/example/img/queen_white.png");
			Image kingWhite = new Image("/example/img/king_white.png");

			imageMap.put(ChessType.PAWN_B, pawnBlack);
			imageMap.put(ChessType.ROOK_B, rookBlack);
			imageMap.put(ChessType.KNIGHT_B, knightBlack);
			imageMap.put(ChessType.BISHOP_B, bishopBlack);
			imageMap.put(ChessType.QUEEN_B, queenBlack);
			imageMap.put(ChessType.KING_B, kingBlack);

			imageMap.put(ChessType.PAWN_W, pawnWhite);
			imageMap.put(ChessType.ROOK_W, rookWhite);
			imageMap.put(ChessType.KNIGHT_W, knightWhite);
			imageMap.put(ChessType.BISHOP_W, bishopWhite);
			imageMap.put(ChessType.QUEEN_W, queenWhite);
			imageMap.put(ChessType.KING_W, kingWhite);

			Board board = new Board(cellWidth, cellHeight, backgroundBoard, agentBoard, colorMap, imageMap, null, null,
					null, null);
			
			board.setCellStroke(1, Color.BLACK);
						
			Scene scene = new Scene(board.getGlobalPane(), board.getWidth(), board.getHeight());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			ChessMousseHandler handler = new ChessMousseHandler(board.getBoard(), row, column);
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					board.getBoard()[i][j].setOnMouseClicked(handler);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
