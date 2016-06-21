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
	private StackPane imgPane = null;
	private ImageView imageView = null;

	public DesignCell(String type) {
		if (GlobalValues.DESIGN_IMAGE != null) {
			if (GlobalValues.DESIGN_IMAGE.get(type) != null) {
				imgPane = new StackPane();
				imgPane.setPrefSize(GlobalValues.CELL_WIDTH, GlobalValues.CELL_HEIGHT);
				imageView = new ImageView();
				imageView.setImage(GlobalValues.DESIGN_IMAGE.get(type));
				imageView.setPreserveRatio(false);
				imageView.setFitWidth(GlobalValues.CELL_WIDTH);
				imageView.setFitHeight(GlobalValues.CELL_HEIGHT);
				imgPane.getChildren().add(imageView);
			} else {
				cell = new Rectangle(GlobalValues.CELL_WIDTH,
						GlobalValues.CELL_HEIGHT);
				cell.setFill(GlobalValues.DESIGN_COLOR.get(type));
			}

		} else {
			cell = new Rectangle(GlobalValues.CELL_WIDTH,
					GlobalValues.CELL_HEIGHT);
			cell.setFill(GlobalValues.DESIGN_COLOR.get(type));
		}
	}

	public void setStroke() {
		if (imageView != null) {
			if (GlobalValues.CELL_STROKE) {
				String hexColor = String
						.format("#%02X%02X%02X",
								(int) (GlobalValues.CELL_STROKE_COLOR.getRed() * 255),
								(int) (GlobalValues.CELL_STROKE_COLOR
										.getGreen() * 255),
								(int) (GlobalValues.CELL_STROKE_COLOR.getBlue() * 255));
				imgPane.setPrefSize(GlobalValues.CELL_WIDTH+GlobalValues.CELL_STROKE_WIDTH, GlobalValues.CELL_HEIGHT+GlobalValues.CELL_STROKE_WIDTH);
				imgPane.setStyle("-fx-background-color: " + hexColor + ";");
				imageView.setFitWidth(GlobalValues.CELL_WIDTH);
				imageView.setFitHeight(GlobalValues.CELL_HEIGHT);
				System.out.println("img w:"+imgPane.getPrefWidth()+" img h :"+imgPane.getHeight());
			}

		} else {
			if (GlobalValues.CELL_STROKE) {
				cell.setStroke(GlobalValues.CELL_STROKE_COLOR);
				cell.setStrokeWidth(GlobalValues.CELL_STROKE_WIDTH);
			}
		}
	}

	public Node getDesign() {
		if (imgPane != null) {
			return imgPane;
		} else {
			return cell;
		}
	}
}
