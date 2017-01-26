package example;

import java.util.ArrayList;

import board.Cell;
import board.Index;
import global.GlobalValues;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class ChessMousseHandler implements EventHandler<MouseEvent> {

	private Cell[][] board = null;
	private boolean whiteTurn;
	private boolean selected = false;
	private boolean canMove = false;
	private boolean enPassant = false;
	private int row = 0;
	private int column = 0;
	private Cell lastPieceCell;
	private ArrayList<Index> indexMoveCell = new ArrayList<>();
	private ArrayList<ChessType> backgroundMoveCell = new ArrayList<>();

	public ChessMousseHandler(Cell[][] board, int row, int column) {
		this.board = board;
		this.row = row;
		this.column = column;
		whiteTurn = true;
	}

	@Override
	public void handle(MouseEvent event) {

		Cell cell = (Cell) event.getSource();
		canMove = false;

		System.out.println("----------------------------------------");
		System.out.println("Cell " + cell.getIndex().getX() + "-" + cell.getIndex().getY() + "\nBackground type : "
				+ cell.getBackgroundType() + "\nAgent Type : " + cell.getAgentType());
		System.out.println("----------------------------------------");
		((Node) (event.getSource())).toFront();

		int x = cell.getIndex().getX();
		int y = cell.getIndex().getY();

		if (whiteTurn) {

			if (cell.getAgentType().equals(ChessType.PAWN_W)) {

				// We removed the other move case
				removeMoveCase();

				if (x - 1 > 0) {
					if (board[x - 1][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
						indexMoveCell.add(new Index(x - 1, y));
						backgroundMoveCell.add((ChessType) board[x - 1][y].getBackgroundType());
						board[x - 1][y].setBackgroundType(ChessType.MOVE_CELL);
						canMove = true;
					}

					if (y - 1 > 0) {
						if (board[x - 1][y - 1].getAgentType().toString().contains("_B") || (x == 3
								&& lastPieceCell.getIndex().getX() == x && lastPieceCell.getIndex().getY() == y - 1)) {
							indexMoveCell.add(new Index(x - 1, y - 1));
							backgroundMoveCell.add((ChessType) board[x - 1][y - 1].getBackgroundType());
							board[x - 1][y - 1].setBackgroundType(ChessType.MOVE_CELL);
							canMove = true;
						}

					}

					if (y + 1 < column - 1) {
						if (board[x - 1][y + 1].getAgentType().toString().contains("_B") || (x == 3
								&& lastPieceCell.getIndex().getX() == x && lastPieceCell.getIndex().getY() == y + 1)) {
							indexMoveCell.add(new Index(x - 1, y + 1));
							backgroundMoveCell.add((ChessType) board[x - 1][y + 1].getBackgroundType());
							board[x - 1][y + 1].setBackgroundType(ChessType.MOVE_CELL);
							canMove = true;
						}
					}
				}

				if (x == row - 2) {
					if (board[x - 2][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
						indexMoveCell.add(new Index(x - 2, y));
						backgroundMoveCell.add((ChessType) board[x - 2][y].getBackgroundType());
						board[x - 2][y].setBackgroundType(ChessType.MOVE_CELL);
						canMove = true;
					}
				}

			} else if (cell.getAgentType().equals(ChessType.ROOK_W)) {

				if (!selected) {
					x--;
					while (board[x][y].equals(ChessType.EMPTY_CELL)) {
						board[x][y].setBackgroundType(ChessType.MOVE_CELL);
						x--;
					}

					if (board[x][y].getAgentType().toString().contains("_B")) {
						board[x][y].setBackgroundType(ChessType.MOVE_CELL);
					}
				}

			} else if (cell.getBackgroundType().equals(ChessType.MOVE_CELL)) {

				board[x][y].setAgentType(lastPieceCell.getAgentType());

				board[lastPieceCell.getIndex().getX()][lastPieceCell.getIndex().getY()]
						.setAgentType(GlobalValues.EMPTY_AGENT_TYPE);

				removeMoveCase();
				lastPieceCell = board[x][y];

				selected = false;
				whiteTurn = false;
			} else {

			}

		} else {
			if (cell.getAgentType().equals(ChessType.PAWN_B)) {

				removeMoveCase();

				if (x + 1 < row - 1) {
					if (board[x + 1][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
						indexMoveCell.add(new Index(x + 1, y));
						backgroundMoveCell.add((ChessType) board[x + 1][y].getBackgroundType());
						board[x + 1][y].setBackgroundType(ChessType.MOVE_CELL);
						canMove = true;
					}

					if (y - 1 > 0) {
						if (board[x + 1][y - 1].getAgentType().toString().contains("_W")) {
							indexMoveCell.add(new Index(x + 1, y - 1));
							backgroundMoveCell.add((ChessType) board[x + 1][y - 1].getBackgroundType());
							board[x + 1][y - 1].setBackgroundType(ChessType.MOVE_CELL);
							canMove = true;
						}
					}

					if (y + 1 < column - 1) {
						if (board[x + 1][y + 1].getAgentType().toString().contains("_W")) {
							indexMoveCell.add(new Index(x + 1, y + 1));
							backgroundMoveCell.add((ChessType) board[x - 1][y + 1].getBackgroundType());
							board[x + 1][y + 1].setBackgroundType(ChessType.MOVE_CELL);
							canMove = true;
						}
					}

				}

				if (x == 1) {
					if (board[x + 2][y].getAgentType().equals(ChessType.EMPTY_CELL)) {
						indexMoveCell.add(new Index(x + 2, y));
						backgroundMoveCell.add((ChessType) board[x + 2][y].getBackgroundType());
						board[x + 2][y].setBackgroundType(ChessType.MOVE_CELL);
						canMove = true;
					}
				}

			} else if (cell.getBackgroundType().equals(ChessType.MOVE_CELL)) {

				board[x][y].setAgentType(lastPieceCell.getAgentType());

				board[lastPieceCell.getIndex().getX()][lastPieceCell.getIndex().getY()]
						.setAgentType(GlobalValues.EMPTY_AGENT_TYPE);

				for (int i = 0; i < backgroundMoveCell.size(); i++) {
					board[indexMoveCell.get(i).getX()][indexMoveCell.get(i).getY()]
							.setBackgroundType(backgroundMoveCell.get(i));
				}

				removeMoveCase();
				lastPieceCell = board[x][y];

				selected = false;
				whiteTurn = true;
			} else {

			}
		}

		if (canMove) {
			lastPieceCell = cell;
		}

		// Index goalIndex = new Index(3, 3);
		//
		// moveTo(cell,goalIndex);
		//
		// goalIndex.setX(goalIndex.getX()+1);
		//
	}

	private void removeMoveCase() {
		if (backgroundMoveCell.size() > 0) {
			System.out.println("size : " + backgroundMoveCell.size());
			for (int i = 0; i < backgroundMoveCell.size(); i++) {
				board[indexMoveCell.get(i).getX()][indexMoveCell.get(i).getY()]
						.setBackgroundType(backgroundMoveCell.get(i));
			}
		}
		indexMoveCell.clear();
		backgroundMoveCell.clear();
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
