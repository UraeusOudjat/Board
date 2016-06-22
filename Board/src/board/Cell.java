package board;

import design.DesignCell;
import javafx.scene.paint.Color;

public class Cell extends DesignCell{
	
	private String backgroundType;
	private String agentType;
	
	public Cell(String backgroundType, String agentType){
		super(backgroundType,agentType);
		this.backgroundType = backgroundType;
		this.agentType = agentType;
	}

}
