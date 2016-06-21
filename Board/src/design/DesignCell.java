package design;

import global.GlobalValues;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DesignCell {

	private Rectangle cell;
	
	public DesignCell() {
		cell = new Rectangle(GlobalValues.CELL_WIDTH, GlobalValues.CELL_HEIGHT);
	}
	
	public Rectangle getRectangle(){
		return cell;
	}
}
