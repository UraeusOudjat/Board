package example.chess;

import java.util.ArrayList;

import board.Index;

public class PiecePositions {

	private ArrayList<Index> PawnWhitePositions = new ArrayList<>();
	private ArrayList<Index> PawnBlackPositions = new ArrayList<>();
	private ArrayList<Index> RookWhitePositions = new ArrayList<>();
	private ArrayList<Index> RookBlackPositions = new ArrayList<>();
	private ArrayList<Index> KnightWhitePositions = new ArrayList<>();
	private ArrayList<Index> KnightBlackPositions = new ArrayList<>();
	private ArrayList<Index> BishopWhitePositions = new ArrayList<>();
	private ArrayList<Index> BishopBlackPositions = new ArrayList<>();
	private Index QueenWhitePosition;
	private Index QueenBlackPosition;
	private Index KingWhitePosition;
	private Index KingBlackPosition;

	public PiecePositions() {
		for (int j = 0; j < 8; j++) {
			PawnWhitePositions.add(new Index(6, j));
			PawnBlackPositions.add(new Index(1, j));
		}

		RookWhitePositions.add(new Index(7, 1));
		RookWhitePositions.add(new Index(7, 7));
		RookBlackPositions.add(new Index(0, 0));
		RookBlackPositions.add(new Index(0, 7));

		KnightWhitePositions.add(new Index(7, 1));
		KnightWhitePositions.add(new Index(7, 6));
		KnightBlackPositions.add(new Index(0, 1));
		KnightBlackPositions.add(new Index(0, 6));

		BishopWhitePositions.add(new Index(7, 2));
		BishopWhitePositions.add(new Index(7, 5));
		BishopBlackPositions.add(new Index(0, 2));
		BishopBlackPositions.add(new Index(0, 5));

		QueenWhitePosition = new Index(7, 3);
		QueenBlackPosition = new Index(0, 3);

		KingWhitePosition = new Index(7, 4);
		KingBlackPosition = new Index(0, 4);

	}

	private ArrayList<Index> replacePosition(ArrayList<Index> positions, Index newPosition, Index oldPosition) {
		for (int i = 0; i < positions.size(); i++) {
			if (positions.get(i).equals(oldPosition)) {
				positions.remove(i);
				positions.add(i, newPosition);
				break;
			}
		}

		return positions;
	}

	private ArrayList<Index> removePosition(ArrayList<Index> positions, Index oldPosition) {
		for (int i = 0; i < positions.size(); i++) {
			if (positions.get(i).equals(oldPosition)) {
				positions.remove(i);
				break;
			}
		}

		return positions;
	}

	public ArrayList<Index> getPawnPosition(PlayerColor playerColor) {
		if (playerColor.equals(PlayerColor.WHITE))
			return PawnWhitePositions;
		else
			return PawnBlackPositions;

	}

	public void setNewPawnPosition(PlayerColor playerColor, Index newPosition, Index oldPosition) {
		if (playerColor.equals(PlayerColor.WHITE)) {
			PawnWhitePositions = replacePosition(PawnWhitePositions, newPosition, oldPosition);
		} else {
			PawnBlackPositions = replacePosition(PawnBlackPositions, newPosition, oldPosition);
		}
	}

	public void removePawnPosition(PlayerColor playerColor, Index oldPosition) {
		if (playerColor.equals(PlayerColor.WHITE)) {
			PawnBlackPositions = removePosition(PawnBlackPositions, oldPosition);
		} else {
			PawnWhitePositions = removePosition(PawnWhitePositions, oldPosition);
		}
	}

	public ArrayList<Index> getRookPosition(PlayerColor playerColor) {
		if (playerColor.equals(PlayerColor.WHITE))
			return RookWhitePositions;
		else
			return RookBlackPositions;

	}

	public void setNewRookPosition(PlayerColor playerColor, Index newPosition, Index oldPosition) {
		if (playerColor.equals(PlayerColor.WHITE)) {
			RookWhitePositions = replacePosition(RookWhitePositions, newPosition, oldPosition);
		} else {
			RookBlackPositions = replacePosition(RookBlackPositions, newPosition, oldPosition);
		}
	}

	public void removeRookPosition(PlayerColor playerColor, Index oldPosition) {
		if (playerColor.equals(PlayerColor.WHITE)) {
			RookBlackPositions = removePosition(RookBlackPositions, oldPosition);
		} else {
			RookWhitePositions = removePosition(RookWhitePositions, oldPosition);
		}
	}

	public ArrayList<Index> getKnightPosition(PlayerColor playerColor) {
		if (playerColor.equals(PlayerColor.WHITE))
			return KnightWhitePositions;
		else
			return KnightBlackPositions;

	}

	public void setNewKnightPosition(PlayerColor playerColor, Index newPosition, Index oldPosition) {
		if (playerColor.equals(PlayerColor.WHITE)) {
			KnightWhitePositions = replacePosition(KnightWhitePositions, newPosition, oldPosition);
		} else {
			KnightBlackPositions = replacePosition(KnightBlackPositions, newPosition, oldPosition);
		}
	}

	public void removeKnightPosition(PlayerColor playerColor, Index oldPosition) {
		if (playerColor.equals(PlayerColor.WHITE)) {
			KnightBlackPositions = removePosition(KnightBlackPositions, oldPosition);
		} else {
			KnightWhitePositions = removePosition(KnightWhitePositions, oldPosition);
		}
	}

	public ArrayList<Index> getBishopPosition(PlayerColor playerColor) {
		if (playerColor.equals(PlayerColor.WHITE))
			return BishopWhitePositions;
		else
			return BishopBlackPositions;

	}

	public void setNewBishopPosition(PlayerColor playerColor, Index newPosition, Index oldPosition) {
		if (playerColor.equals(PlayerColor.WHITE)) {
			BishopWhitePositions = replacePosition(BishopWhitePositions, newPosition, oldPosition);
		} else {
			BishopBlackPositions = replacePosition(BishopBlackPositions, newPosition, oldPosition);
		}
	}

	public void removeBishopPosition(PlayerColor playerColor, Index oldPosition) {
		if (playerColor.equals(PlayerColor.WHITE)) {
			BishopBlackPositions = removePosition(BishopBlackPositions, oldPosition);
		} else {
			BishopWhitePositions = removePosition(BishopWhitePositions, oldPosition);
		}
	}

	public Index getQueenPosition(PlayerColor playerColor) {
		if (playerColor.equals(PlayerColor.WHITE))
			return QueenWhitePosition;
		else
			return QueenBlackPosition;

	}

	public void setNewQueenPosition(PlayerColor playerColor, Index newPosition) {
		if (playerColor.equals(PlayerColor.WHITE)) {
			QueenWhitePosition = newPosition;
		} else {
			QueenBlackPosition = newPosition;
		}
	}

	public void removeQueenPosition(PlayerColor playerColor) {
		if (playerColor.equals(PlayerColor.WHITE)) {
			QueenBlackPosition = new Index(-1, -1);
		} else {
			QueenWhitePosition = new Index(-1, -1);
		}
	}

	public Index getKingPosition(PlayerColor playerColor) {
		if (playerColor.equals(PlayerColor.WHITE))
			return KingWhitePosition;
		else
			return KingBlackPosition;

	}

	public void setNewKingPosition(PlayerColor playerColor, Index newPosition) {
		if (playerColor.equals(PlayerColor.WHITE)) {
			KingWhitePosition = newPosition;
		} else {
			KingBlackPosition = newPosition;
		}
	}

	public String toString() {

		String returnString = "\n---------------- Piece Positions----------------\nBlack Positions :\n\tPawn :";

		for (Index pawn : PawnBlackPositions) {
			returnString = returnString.concat(" " + (pawn.getY() + 1) + "-" + (8 - pawn.getX()) + " ");
		}

		returnString = returnString.concat("\n\tRook :");
		for (Index rook : RookBlackPositions) {
			returnString = returnString.concat(" " + (rook.getY() + 1) + "-" + (8 - rook.getX()) + " ");
		}

		returnString = returnString.concat("\n\tKnight :");
		for (Index knight : KnightBlackPositions) {
			returnString = returnString.concat(" " + (knight.getY() + 1) + "-" + (8 - knight.getX()) + " ");
		}

		returnString = returnString.concat("\n\tBishop :");
		for (Index bishop : BishopBlackPositions) {
			returnString = returnString.concat(" " + (bishop.getY() + 1) + "-" + (8 - bishop.getX()) + " ");
		}

		if (QueenBlackPosition.getX() != -1 && QueenBlackPosition.getY() != -1) {
			returnString = returnString
					.concat("\n\tQueen : " + (QueenBlackPosition.getY() + 1) + "-" + (8 - QueenBlackPosition.getX()));
		} else {
			returnString = returnString.concat("\n\tQueen : ");
		}
		
		returnString = returnString
				.concat("\n\tKing : " + (KingBlackPosition.getY() + 1) + "-" + (8 - KingBlackPosition.getX()));

		returnString = returnString
				.concat("\n\n---------------- Piece Positions----------------\nWhite Positions :\n\tPawn :");

		for (Index pawn : PawnWhitePositions) {
			returnString = returnString.concat(" " + (pawn.getY() + 1) + "-" + (8 - pawn.getX()) + " ");
		}

		returnString = returnString.concat("\n\tRook :");
		for (Index rook : RookWhitePositions) {
			returnString = returnString.concat(" " + (rook.getY() + 1) + "-" + (8 - rook.getX()) + " ");
		}

		returnString = returnString.concat("\n\tKnight :");
		for (Index knight : KnightWhitePositions) {
			returnString = returnString.concat(" " + (knight.getY() + 1) + "-" + (8 - knight.getX()) + " ");
		}

		returnString = returnString.concat("\n\tBishop :");
		for (Index bishop : BishopWhitePositions) {
			returnString = returnString.concat(" " + (bishop.getY() + 1) + "-" + (8 - bishop.getX()) + " ");
		}

		if (QueenWhitePosition.getX() != -1 && QueenWhitePosition.getY() != -1) {
			returnString = returnString
					.concat("\n\tQueen : " + (QueenWhitePosition.getY() + 1) + "-" + (8 - QueenWhitePosition.getX()));
		} else {
			returnString = returnString.concat("\n\tQueen : ");
		}

		returnString = returnString
				.concat("\n\tKing : " + (KingWhitePosition.getY() + 1) + "-" + (8 - KingWhitePosition.getX()));

		returnString = returnString.concat("\n\n------------------------------------------------\n");
		return returnString;
	}

}
