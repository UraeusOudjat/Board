package design;

import global.GlobalValues;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class DesignCell {

	private Rectangle cell = null;
	private ImageView imageView = null;
	
	public DesignCell(String type) {
				
		if(GlobalValues.DESIGN_IMAGE != null){
			imageView = new ImageView(GlobalValues.DESIGN_IMAGE.get(type));
			imageView.setFitWidth(GlobalValues.CELL_WIDTH);
			imageView.setFitHeight(GlobalValues.CELL_HEIGHT);
		}else{
			cell = new Rectangle(GlobalValues.CELL_WIDTH, GlobalValues.CELL_HEIGHT);
			cell.setFill(GlobalValues.DESIGN_COLOR.get(type));
		}
	}
	
	public Node getDesign(){
		if(imageView != null){
			return imageView;
		}else{
			return cell;
		}
	}
}
