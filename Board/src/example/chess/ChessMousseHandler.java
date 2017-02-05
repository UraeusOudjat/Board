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
	private PlayerColor playerColor = PlayerColor.WHITE;
	private String playerColorString;
	
	public ChessMousseHandler(Cell[][] board, int row, int column) {
		this.board = board;
		this.row = row;
		this.column = column;
	}

	@Override
	public void handle(MouseEvent event) {

		Cell cell = (Cell) event.getSource();
		((Node) (event.getSource())).toFront();

		playerColorString = (playerColor.equals(PlayerColor.WHITE)) ? "_W" : "_B";

		if (cell.getAgentType().equals(ChessType.valueOf("PAWN" + playerColorString))) {
			selectedPieceCell = Pawn.setCanMoveCell(playerColor, row, column, cell, backgroundMoveCell, board,
					indexMoveCell, lastPieceMovedCell);

		} else if (cell.getAgentType().equals(ChessType.ROOK_W) && playerColor.equals(PlayerColor.WHITE)) {
			// We display the cell where the white rook can move only if it's
			// the white turn
			selectedPieceCell = Rook.setCanMoveCell(playerColor, row, column, cell, backgroundMoveCell, board,
					indexMoveCell, lastPieceMovedCell);

		} else if (cell.getAgentType().equals(ChessType.ROOK_B) && playerColor.equals(PlayerColor.BLACK)) {
			// We display the cell where the black rook can move only if it's
			// the black turn
			selectedPieceCell = Rook.setCanMoveCell(playerColor, row, column, cell, backgroundMoveCell, board,
					indexMoveCell, lastPieceMovedCell);

		} else if (cell.getAgentType().equals(ChessType.KNIGHT_W) && playerColor.equals(PlayerColor.WHITE)) {
			// We display the cell where the white knight can move only if it's
			// the white turn
			selectedPieceCell = Knight.setCanMoveCell(playerColor, row, column, cell, backgroundMoveCell, board,
					indexMoveCell, lastPieceMovedCell);
			
		} else if (cell.getAgentType().equals(ChessType.KNIGHT_B) && playerColor.equals(PlayerColor.BLACK)) {
			// We display the cell where the black knight can move only if it's
			// the black turn
			selectedPieceCell = Knight.setCanMoveCell(playerColor, row, column, cell, backgroundMoveCell, board,
					indexMoveCell, lastPieceMovedCell);

		} else if (cell.getAgentType().equals(ChessType.BISHOP_W) && playerColor.equals(PlayerColor.WHITE)) {
			// We display the cell where the white bishop can move only if it's
			// the white turn
			selectedPieceCell = Bishop.setCanMoveCell(playerColor, row, column, cell, backgroundMoveCell, board,
					indexMoveCell, lastPieceMovedCell);

		} else if (cell.getAgentType().equals(ChessType.BISHOP_B) && playerColor.equals(PlayerColor.BLACK)) {
			// We display the cell where the black bishop can move only if it's
			// the black turn
			selectedPieceCell = Bishop.setCanMoveCell(playerColor, row, column, cell, backgroundMoveCell, board,
					indexMoveCell, lastPieceMovedCell);

		}else if (cell.getAgentType().equals(ChessType.QUEEN_W) && playerColor.equals(PlayerColor.WHITE)) {
			// We display the cell where the white queen can move only if it's
			// the white turn
			selectedPieceCell = Queen.setCanMoveCell(playerColor, row, column, cell, backgroundMoveCell, board,
					indexMoveCell, lastPieceMovedCell);

		} else if (cell.getAgentType().equals(ChessType.QUEEN_B) && playerColor.equals(PlayerColor.BLACK)) {
			// We display the cell where the black queen can move only if it's
			// the black turn
			selectedPieceCell = Queen.setCanMoveCell(playerColor, row, column, cell, backgroundMoveCell, board,
					indexMoveCell, lastPieceMovedCell);

		}else if (cell.getAgentType().equals(ChessType.KING_W) && playerColor.equals(PlayerColor.WHITE)) {
			System.out.println(ChessGlobal.piecePositions.toString());

		} else if (cell.getBackgroundType().equals(ChessType.MOVE_CELL)) {

			if (selectedPieceCell.getAgentType().toString().contains("PAWN")) {
				lastPieceMovedCell = Pawn.move(playerColor, board, cell, column, backgroundMoveCell, indexMoveCell);
			} else if (selectedPieceCell.getAgentType().toString().contains("ROOK")) {
				lastPieceMovedCell = Rook.move(playerColor, board, cell, column, backgroundMoveCell, indexMoveCell);
			} else if (selectedPieceCell.getAgentType().toString().contains("KNIGHT")) {
				lastPieceMovedCell = Knight.move(playerColor, board, cell, column, backgroundMoveCell, indexMoveCell);
			} else if (selectedPieceCell.getAgentType().toString().contains("BISHOP")) {
				lastPieceMovedCell = Bishop.move(playerColor, board, cell, column, backgroundMoveCell, indexMoveCell);
			}else if (selectedPieceCell.getAgentType().toString().contains("QUEEN")) {
				lastPieceMovedCell = Queen.move(playerColor, board, cell, column, backgroundMoveCell, indexMoveCell);
			}

			// We swap the color of the player turn
			playerColor = (playerColor.equals(PlayerColor.WHITE)) ? PlayerColor.BLACK : PlayerColor.WHITE;
			
			System.out.println("King "+playerColor.toString());
			Check.isKingCheck(playerColor, board);
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
