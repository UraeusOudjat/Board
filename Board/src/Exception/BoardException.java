package Exception;

public class BoardException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3740984409669191594L;

	public BoardException(TypeException typeException) {
		switch (typeException) {
		case TYPE_BORD_SIZE:
				System.err.println("The Type Board has not the same size as the Cell Board !");
			break;

		case BOARD_INCORECT_VALUES:
				System.err.println("Row or Column can't be null or negative !");
			break;
		case TYPE_BOARD_GRID:
			System.err.println("The type board must have always the same row and column size !");
			break;
		case DESIGN_WAS_NOT_SPECIFIED :
			System.err.println("You must specify ColorMap or ImageMap");
			break;
		
		case COLOR_MAP_TYPE :
			System.err.println("The color map contain unknow type");
			break;
		
		case IMAGE_MAP_TYPE :
			System.err.println("The image map contain unknow type");
			break;
		default:
			break;
		}
	}

}
