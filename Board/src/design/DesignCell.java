package design;

import global.GlobalValues;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class DesignCell extends StackPane {

	private Rectangle backgroundRectangle = null;
	private StackPane cellPane = null;
	private ImageView backgroundImgView = null;
	private ImageView agentImgView = null;
	private Circle agentCircle = null;

	/**
	 * This class is class who make the design of the cell
	 * 
	 * @param backgroundType
	 * @param agentType
	 */
	public DesignCell(Enum<?> backgroundType, Enum<?> agentType) {

		// XXX : Maybe it can be usefull to make another map for the shape :
		// circle , square, rectangle etc...

		// We create a stack pane because agent frame must be display under the
		// background frame
		cellPane = new StackPane();
		cellPane.setMinSize(GlobalValues.CELL_WIDTH, GlobalValues.CELL_HEIGHT);
		cellPane.setAlignment(Pos.CENTER);

		drawBackground(backgroundType);
		drawAgent(agentType);

	}

	public void drawBackground(Enum<?> backgroundType) {
		// We check if the background have an image frame , if he have we
		// display it if he doesn"t have image frame we display a color square
		if (GlobalValues.DESIGN_IMAGE != null) {
			if (GlobalValues.DESIGN_IMAGE.get(backgroundType) != null) {
				backgroundImgView = new ImageView();
				backgroundImgView.setImage(GlobalValues.DESIGN_IMAGE.get(backgroundType));
				backgroundImgView.setPreserveRatio(false);
				backgroundImgView.setFitWidth(GlobalValues.CELL_WIDTH);
				backgroundImgView.setFitHeight(GlobalValues.CELL_HEIGHT);
				cellPane.getChildren().add(backgroundImgView);
			} else {
				backgroundRectangle = new Rectangle(GlobalValues.CELL_WIDTH, GlobalValues.CELL_HEIGHT);
				backgroundRectangle.setFill(GlobalValues.DESIGN_COLOR.get(backgroundType));
				cellPane.getChildren().add(backgroundRectangle);
			}

		} else {
			backgroundRectangle = new Rectangle(GlobalValues.CELL_WIDTH, GlobalValues.CELL_HEIGHT);
			backgroundRectangle.setFill(GlobalValues.DESIGN_COLOR.get(backgroundType));
			cellPane.getChildren().add(backgroundRectangle);
		}
	}

	protected void drawAgent(Enum<?> agentType) {
		// After initialize the background frame we add the agent frame
		// We make the same test as for the background
		if (GlobalValues.DESIGN_IMAGE != null && agentType != null) {
			if (GlobalValues.DESIGN_IMAGE.containsKey(agentType)) {
				agentImgView = new ImageView();
				agentImgView.setImage(GlobalValues.DESIGN_IMAGE.get(agentType));
				agentImgView.setPreserveRatio(false);
				agentImgView.setFitWidth(GlobalValues.CELL_WIDTH);
				agentImgView.setFitHeight(GlobalValues.CELL_HEIGHT);
				cellPane.getChildren().add(agentImgView);
			} else if (GlobalValues.DESIGN_COLOR.containsKey(agentType)) {
				agentCircle = new Circle(GlobalValues.CELL_WIDTH / 2);
				agentCircle.setFill(GlobalValues.DESIGN_COLOR.get(agentType));
				cellPane.getChildren().add(agentCircle);
			}
		}
	}

	/***
	 * This function is used to set stroke to this cells
	 */
	public void setStroke() {
		if (backgroundImgView != null) {
			if (GlobalValues.CELL_STROKE) {
				// If the cell have background frame, we apply a CSS style on
				// the cell
				// It's why we need the hexColor
				String hexColor = String.format("#%02X%02X%02X", (int) (GlobalValues.CELL_STROKE_COLOR.getRed() * 255),
						(int) (GlobalValues.CELL_STROKE_COLOR.getGreen() * 255),
						(int) (GlobalValues.CELL_STROKE_COLOR.getBlue() * 255));
				cellPane.setMinSize(GlobalValues.CELL_WIDTH + GlobalValues.CELL_STROKE_WIDTH,
						GlobalValues.CELL_HEIGHT + GlobalValues.CELL_STROKE_WIDTH);
				cellPane.setStyle("-fx-background-color: " + hexColor + ";");
				backgroundImgView.setFitWidth(GlobalValues.CELL_WIDTH - GlobalValues.CELL_STROKE_WIDTH);
				backgroundImgView.setFitHeight(GlobalValues.CELL_HEIGHT - GlobalValues.CELL_STROKE_WIDTH);
			}

		} else {
			if (GlobalValues.CELL_STROKE) {
				backgroundRectangle.setStroke(GlobalValues.CELL_STROKE_COLOR);
				backgroundRectangle.setStrokeWidth(GlobalValues.CELL_STROKE_WIDTH);

			}
		}
	}

	public Node getDesign() {
		return cellPane;
	}

	public Node getAgentDesign() {
		if (agentImgView != null)
			return agentImgView;
		else
			return agentCircle;
	}

	public Node getBackgroundDesign() {
		if (backgroundImgView != null)
			return backgroundImgView;
		else
			return backgroundRectangle;
	}
}
