package example.chess;

import java.util.ArrayList;

import board.Cell;
import board.Index;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class ChessMousseHandler implements EventHandler<MouseEvent> {

	private Cell[][] board = null;
	private int row = 0;
	private int column = 0;
	private Cell lastPieceMovedCell;
	private Cell selectedPieceCell;
	private ArrayList<Index> indexMoveCell = new ArrayList<>();
	private ArrayList<ChessType> backgroundMoveCell = new ArrayList<>();
	private PlayerTurn playerTurn = PlayerTurn.WHITE;
	private String playerColor;
	private String opponentColor;
	

	public ChessMousseHandler(Cell[][] board, int row, int column) {
		this.board = board;
		this.row = row;
		this.column = column;
	}

	@Override
	public void handle(MouseEvent event) {

		Cell cell = (Cell) event.getSource();
		((Node) (event.getSource())).toFront();


		playerColor = (playerTurn.equals(PlayerTurn.WHITE)) ? "_W" : "_B";
		opponentColor = (playerTurn.equals(PlayerTurn.WHITE)) ? "_B" : "_W";

			if (cell.getAgentType().equals(ChessType.valueOf("PAWN"+playerColor))) {
				selectedPieceCell = Pawn.setCanMoveCell(playerTurn, row, column, cell, backgroundMoveCell, board, indexMoveCell, lastPieceMovedCell);
					
			} else if (cell.getAgentType().equals(ChessType.ROOK_W) && playerTurn.equals(PlayerTurn.WHITE)) {
				//We display the cell where the white rook can moove only if it's the white turn
				selectedPieceCell = Rook.setCanMoveCell(playerTurn, row, column, cell, backgroundMoveCell, board, indexMoveCell, lastPieceMovedCell);

			}else if (cell.getAgentType().equals(ChessType.ROOK_B) && playerTurn.equals(PlayerTurn.BLACK)) {
				//We display the cell where the black rook can moove only if it's the black turn
				selectedPieceCell = Rook.setCanMoveCell(playerTurn, row, column, cell, backgroundMoveCell, board, indexMoveCell, lastPieceMovedCell);

			} else if (cell.getBackgroundType().equals(ChessType.MOVE_CELL)) {
			
				if(selectedPieceCell.getAgentType().toString().contains("PAWN")){
					lastPieceMovedCell = Pawn.move(playerTurn,board, cell, column, backgroundMoveCell, indexMoveCell);
				}else if(selectedPieceCell.getAgentType().toString().contains("ROOK")){
					lastPieceMovedCell = Rook.move(playerTurn, board, cell, column, backgroundMoveCell, indexMoveCell);
				}
				
				//We swap the color of the player turn
				playerTurn = (playerTurn.equals(PlayerTurn.WHITE))? PlayerTurn.BLACK : PlayerTurn.WHITE;


			} else {

			}
	}


	public void moveTo(Cell cell, Index goalIndex) {

		// TranslateTransition translateAnimation = new
		// TranslateTransition(Duration.seconds(2), this.getAgent());
		//
		//
		// double strokeMoveX = 0 , strokeMoveY = 0 ;
		// if(GlobalValues.CELL_STROKE){
		// strokeMoveX =
		// (goalIndex.getX()-index.getX())*GlobalValues.CELL_STROKE_WIDTH;
		// strokeMoveY =
		// (goalIndex.getY()-index.getY())*GlobalValues.CELL_STROKE_WIDTH;
		//
		// }
		//
		// //We make the difference between the goal index and the current index
		// because during an animation the current position is 0,0
		// // so if we want to go left or top it must be a negative number
		// translateAnimation.setByX((goalIndex.getX()-index.getX())*GlobalValues.CELL_WIDTH
		// + strokeMoveX);
		// translateAnimation.setByY((goalIndex.getY()-index.getY())*GlobalValues.CELL_HEIGHT
		// + strokeMoveY);
		// translateAnimation.setInterpolator(Interpolator.LINEAR);
		// translateAnimation.play();
		//
		// GlobalValues.board[index.getY()][index.getX()].setAgentType(agentType);
		// this.agentType = GlobalValues.EMPTY_AGENT_TYPE;

	}
}
