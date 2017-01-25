package board;

import design.DesignCell;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Cell extends DesignCell implements EventHandler<MouseEvent>{
	
	private Enum<?> backgroundType;
	private Enum<?> agentType;
	private Index index;
	
	public Cell(Enum<?> backgroundType, Enum<?> agentType, int i , int j){
		super(backgroundType,agentType);
		this.backgroundType = backgroundType;
		this.agentType = agentType;
		this.index = new Index(i, j);
		
		this.getDesign().setOnMouseClicked(this);
		
		
	}

	@Override
	public void handle(MouseEvent event) {
		System.out.println("----------------------------------------");
		System.out.println("Cell "+index.getX()+ "-"+ index.getY()+"\nBackground type : "+backgroundType+"\nAgent Type : "+agentType);
		System.out.println("----------------------------------------");

		
	}

	public void moveTo(Index goalIndex){
		
	}
}
