package Exception;

public class BoardException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3740984409669191594L;

	public BoardException(TypeException typeException) {
		switch (typeException) {
		case UNMATCH_VALUE:
				System.err.println("The Type Board has not the same size as the Cell Board !");
			break;

		case INCORECT_VALUE:
				System.err.println("Row or Column can't be null or negative !");
			break;
		case TYPE_BOARD_GRID:
			System.err.println("The type board must have always the same row and column size !");
			break;
		
		default:
			break;
		}
	}

}
