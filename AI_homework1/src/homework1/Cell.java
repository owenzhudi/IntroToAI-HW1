package homework1;

public class Cell {
	private int x;
	private int y;
	private int gValue;
	private int hValue;
	private int fValue;
	private int search;
	Cell tree;
	private boolean blocked;
	private boolean visited;
	private static final int C = 20000;
	
	public Cell(int x, int y) {
		search = 0;
		this.x = x;
		this.y = y;
	}
	
	public Cell() {
		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isBlocked() {
		return blocked;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public int getF() {
		return fValue;
	}
	
	// for larger g-value
	public int getFLargerG() {
		return C * fValue - gValue;
	}
	
	// for smaller g-value
	public int getFSmallerG() {
		return C * fValue + gValue;
	}
	
	public int getG() {
		return gValue;
	}
	
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	public void visit() {
		visited = true;
	}
	
	public void setH(Cell goal) {
		hValue = Math.abs(this.x - goal.x) + Math.abs(this.y - goal.y); // Manhattan distance
		fValue = gValue + hValue;
	}
	
	public void setHnew(Cell goal) {
		gValue = goal.getG() - gValue;
	}
	
	
	
	public void setHVal(int h) {
		hValue = h;
	}
	
	public void setG(int g) {
		gValue = g;
		fValue = gValue + hValue;
	}
	
	public void setF(int f) {
		fValue = f;
	}
	
	public void setSearch(int s) {
		search = s;
	}
	
	public int getSearch() {
		return search;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equalTo(Cell c) {
		return (x == c.x) && (y == c.y);
	}
}
