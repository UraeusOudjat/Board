package board;

public class Index {

	private int x;
	private int y;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Index(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isOnTheLeft(Index i){
		if(y < i.getY())
			return true;
		else
			return false;
		
	}
	
	public boolean isOnTheRight(Index i){
		if(y > i.getY())
			return true;
		else
			return false;
	}
	
	public boolean isOnTheTop(Index i){
		if(x < i.getX())
			return true;
		else
			return false;
	}
	
	public boolean isOnTheBottom(Index i){
		if(x > i.getX())
			return true;
		else
			return false;
	}
	
	public boolean isOnTheLeftAndSameRow(Index i){
		if(y < i.getY() && x == i.getX())
			return true;
		else
			return false;
		
	}
	
	public boolean isOnTheRightAndSameRow(Index i){
		if(y > i.getY() && x == i.getX())
			return true;
		else
			return false;
	}
	
	public boolean isOnTheTopAndSameColumn(Index i){
		if(x < i.getX() && y == i.getY())
			return true;
		else
			return false;
	}
	
	public boolean isOnTheBottomAndsameColumn(Index i){
		if(x > i.getX() && y == i.getY())
			return true;
		else
			return false;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj.getClass().equals(Index.class)){
			if(this.x == ((Index)obj).getX() && this.y == ((Index)obj).getY())
				return true;
			else
				return false;
		}else{
			return false;
		}
	}
	
	
	
	
}
