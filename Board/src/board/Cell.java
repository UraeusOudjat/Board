package board;

import design.DesignCell;
import javafx.event.EventDispatchChain;
import javafx.event.EventTarget;


/**
 * 
 * @author Uraeus
 *
 */
public class Cell extends DesignCell{
	
	private Enum<?> backgroundType;
	private Enum<?> agentType;
	private Index index;
	
	public Cell(Enum<?> backgroundType, Enum<?> agentType, int i , int j){
		super(backgroundType,agentType);
		
		/**
		 * This class is a Stack Pane because she extend design cell.
		 * It was done to allow the management of the mouse event.
		 * If the event are only set on the design we can't get the index and the background and agent type
		 */
		this.getChildren().add(getDesign());
		this.backgroundType = backgroundType;
		this.agentType = agentType;
		this.index = new Index(i, j);		
		
	}
	
	public void setAgentType(Enum<?> agentType){
		this.agentType = agentType;
		this.drawBackground(backgroundType);
		this.drawAgent(agentType);
	}
	
	public void setBackgroundType(Enum<?> backgroundType){
		this.backgroundType = backgroundType;
		this.drawBackground(backgroundType);
		this.drawAgent(agentType);

	}

	public Index getIndex() {
		return index;
	}

	public void setIndex(Index index) {
		this.index = index;
	}

	public Enum<?> getBackgroundType() {
		return backgroundType;
	}

	public Enum<?> getAgentType() {
		return agentType;
	}
	
}
