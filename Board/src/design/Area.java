package design;

import javafx.scene.layout.Pane;

public class Area {

	private int width;
	private int height;
	private Pane area;
	
	public Area(int width, int height, Pane area) {
		this.width = width;
		this.height = height;
		this.area = area;
		area.setPrefSize(width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Pane getArea() {
		return area;
	}
	
	
	
}
