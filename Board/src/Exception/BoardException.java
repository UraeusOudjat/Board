package Exception;

public class BoardException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3740984409669191594L;

	public BoardException(TypeException typeException) {
		switch (typeException) {

		case BOARD_INCORECT_VALUES:
			System.err.println("Row or Column can't be null or negative.");
			break;
		case BACKGROUND_BOARD_EMPTY :
			System.err.println("The background board contain null values");
			break;
		case BACKGROUND_BORD_SIZE:
			System.err
					.println("The background board has not the same size as the Cell Board.");
			break;
		case BACKGROUND_BOARD_TYPE:
			System.err
					.println("The background board contain unkonw type, check the ArrayList Type.");
			break;

		case BACKGROUND_BOARD_GRID:
			System.err
					.println("The background board must have always the same row and column size.");
			break;
		case AGENT_BOARD_EMPTY :
			System.err.println("The agent board contain null values");
			break;
		case AGENT_BORD_SIZE:
			System.err
					.println("The agent board has not the same size as the Cell Board.");
			break;
		case AGENT_BOARD_TYPE:
			System.err
					.println("The agent board contain unkonw type, check the ArrayList Type.");
			break;

		case AGENT_BOARD_GRID:
			System.err
					.println("The agent board must have always the same row and column size.");
			break;

		case CELL_TYPE_UNDEFINED :
			System.err.println("Cell type is null");
			break;
			
		case DESIGN_WAS_NOT_SPECIFIED:
			System.err.println("You must specify ColorMap or ImageMap.");
			break;

		case COLOR_MAP_EMPTY:
			System.err.println("The color map contain null value");
			break;
			
		case COLOR_MAP_TYPE:
			System.err
					.println("The color map contain unknow type, check the ArrayList Type.");
			break;
			
		case IMAGE_MAP_EMPTY:
			System.err.println("The image map contain null value");
			break;
			
		case IMAGE_MAP_TYPE:
			System.err
					.println("The image map contain unknow type, check the ArrayList Type.");
			break;

		case STROKE_COLOR:
			System.err.println("Stroke color are null.");
			break;

		case STROKE_WIDTH:
			System.err.println("Stroke width are null or negative.");
			break;
		default:
			System.err.println("Board unknow error.");
			break;
		}
	}

}
