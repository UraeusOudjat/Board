package board;

public class Index {

	private int x;
	private int y;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Index(int i, int j) {
		this.x = j;
		this.y = i;
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
		if(x < i.getX())
			return true;
		else
			return false;
		
	}
	
	public boolean isOnTheRight(Index i){
		if(x > i.getX())
			return true;
		else
			return false;
	}
	
	public boolean isOnTheTop(Index i){
		if(y < i.getY())
			return true;
		else
			return false;
	}
	
	public boolean isOnTheBottom(Index i){
		if(y > i.getY())
			return true;
		else
			return false;
	}
	
	
}
