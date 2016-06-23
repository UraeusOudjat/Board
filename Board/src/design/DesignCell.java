package design;

import sun.security.action.GetLongAction;
import global.GlobalValues;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DesignCell {

	private Rectangle cell = null;
	private StackPane cellPane = null;
	private ImageView backgroundImgView = null;
	private ImageView agentImgView = null;

	public DesignCell(String backgroundType, String agentType) {
		cellPane = new StackPane();
		cellPane.setMinSize(GlobalValues.CELL_WIDTH, GlobalValues.CELL_HEIGHT);
		cellPane.setAlignment(Pos.CENTER);
		if (GlobalValues.DESIGN_IMAGE != null) {
			if (GlobalValues.DESIGN_IMAGE.get(backgroundType) != null) {
				backgroundImgView = new ImageView();
				backgroundImgView.setImage(GlobalValues.DESIGN_IMAGE.get(backgroundType));
				backgroundImgView.setPreserveRatio(false);
				backgroundImgView.setFitWidth(GlobalValues.CELL_WIDTH);
				backgroundImgView.setFitHeight(GlobalValues.CELL_HEIGHT);
				cellPane.getChildren().add(backgroundImgView);
			} else {
				cell = new Rectangle(GlobalValues.CELL_WIDTH,
						GlobalValues.CELL_HEIGHT);
				cell.setFill(GlobalValues.DESIGN_COLOR.get(backgroundType));
				cellPane.getChildren().add(cell);
			}

		} else {
			cell = new Rectangle(GlobalValues.CELL_WIDTH,
					GlobalValues.CELL_HEIGHT);
			cell.setFill(GlobalValues.DESIGN_COLOR.get(backgroundType));
			cellPane.getChildren().add(cell);
		}
		
		
		// After initialize the background design we add the agent design
		if (GlobalValues.DESIGN_IMAGE != null && agentType != null) {
			if(GlobalValues.DESIGN_IMAGE.containsKey(agentType)){
				agentImgView = new ImageView();
				agentImgView.setImage(GlobalValues.DESIGN_IMAGE.get(agentType));
				agentImgView.setPreserveRatio(false);
				agentImgView.setFitWidth(GlobalValues.CELL_WIDTH);
				agentImgView.setFitHeight(GlobalValues.CELL_HEIGHT);				
				cellPane.getChildren().add(agentImgView);
			}
		}	
	}

	public void setStroke() {
		if (backgroundImgView != null) {
			if (GlobalValues.CELL_STROKE) {
				String hexColor = String
						.format("#%02X%02X%02X",
								(int) (GlobalValues.CELL_STROKE_COLOR.getRed() * 255),
								(int) (GlobalValues.CELL_STROKE_COLOR
										.getGreen() * 255),
								(int) (GlobalValues.CELL_STROKE_COLOR.getBlue() * 255));
				cellPane.setMinSize(GlobalValues.CELL_WIDTH+GlobalValues.CELL_STROKE_WIDTH, GlobalValues.CELL_HEIGHT+GlobalValues.CELL_STROKE_WIDTH);
				cellPane.setStyle("-fx-background-color: " + hexColor + ";");
				backgroundImgView.setFitWidth(GlobalValues.CELL_WIDTH-GlobalValues.CELL_STROKE_WIDTH);
				backgroundImgView.setFitHeight(GlobalValues.CELL_HEIGHT-GlobalValues.CELL_STROKE_WIDTH);
			}

		} else {
			if (GlobalValues.CELL_STROKE) {
				cell.setStroke(GlobalValues.CELL_STROKE_COLOR);
				cell.setStrokeWidth(GlobalValues.CELL_STROKE_WIDTH);
			
			}
		}
	}

	public Node getDesign() {
			return cellPane;
	}
}
