package homework1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStar {
	private static final int SIZE = 101;
	private static final int INF = Integer.MAX_VALUE - 100000;
	private Cell[][] maze;
	private PriorityQueue<Cell> open;
	private LinkedList<Cell> closed;
	private int counter = 0;
	private ArrayList<Cell> path;
	private Cell start;
	private Cell goal;
	private Direction[] actions = {new Direction(-1, 0), new Direction(1, 0),
								new Direction(0, -1), new Direction(0, 1) };
	private int numExpanded = 0;	// number of expanded cells
	
	// comparator to compare different cells in a maze
	private Comparator<Cell> cellComparator = new Comparator<Cell>() {
		@Override
		public int compare(Cell c1, Cell c2) {
			return c1.getF() - c2.getF();
		}
	};
	
	public AStar(Cell[][] maze, int sx, int sy, int gx, int gy) {
		//this.startX = sx;
		//this.startY = sy;
		//gen.generate(startX, startY);
		//maze = gen.getMaze();
		this.maze = maze;
		open = new PriorityQueue<Cell>(cellComparator);
		start = maze[sx][sy];
		goal = maze[gx][gy];
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				maze[i][j].setH(goal);
			}
		}
		closed = new LinkedList<Cell>();
		path = new ArrayList<>();
			
	}
	
	public void aStarMain() {
		while(!start.equalTo(goal)) {
			counter++;
			start.setG(0);
			start.setH(goal);
			start.setSearch(counter);
			goal.setG(INF);
			goal.setSearch(counter);
			open.clear();
			closed.clear();
			open.offer(start);
			//detect(start);
			if(!open.isEmpty()) {
				computePath();
			}
			if(open.isEmpty()) {
				System.out.println("I cannot reach the target");
				return;
			}
			Cell next = move();
			path.add(start);
			start = next;
			numExpanded += closed.size();
		}
		System.out.println("I reached the target.");
	}
	
	private void computePath() {
		while(goal.getG() > open.peek().getG()) {
			Cell curState = open.poll();
			closed.add(curState);
			for(Direction act : actions) {
				int nx = curState.getX() + act.dx;
				int ny = curState.getY() + act.dy;
				if((0 <= nx && nx < SIZE) && (0 <= ny && ny < SIZE) && !maze[nx][ny].isBlocked()) {
					// successor has been searched before
					// maze[nx][ny] is the successor
					if(maze[nx][ny].getSearch() < counter) {
						maze[nx][ny].setG(INF);
						maze[nx][ny].setSearch(counter);
					}
					// update g-value
					if(maze[nx][ny].getG() > curState.getG() + 1) {
						maze[nx][ny].setG(curState.getG() + 1);
						maze[nx][ny].tree = curState;
						if(open.contains(maze[nx][ny])) {
							open.remove(maze[nx][ny]);
						}
						open.offer(maze[nx][ny]);
					}
				}
			}
			if(open.isEmpty()) {
				return;
			}
		}
	}
	
	// move the position of agent
	private Cell move() {
		Cell pos = goal;
		while(pos.tree != start) {
			pos = pos.tree;
		}
		return pos;
	}
	
	/**
	// detect if there are blocked cells around the agent
	// if there are blocked cells, set the f-value of blocked cells to infinity and remove it out of the open list
	private void detect(Cell agent) {
		for(Direction dir : actions) {
			int nx = agent.getX() + dir.dx;
			int ny = agent.getY() + dir.dy;
			if((nx < 0 || nx >= SIZE) || (ny < 0 || ny >= SIZE)) {
				continue;
			}
			if(maze[nx][ny].isBlocked()) {
				maze[nx][ny].setF(INF);
				if(open.contains(maze[nx][ny])) {
					open.remove(maze[nx][ny]);
				}
			}
		}
	}
	*/
	
	public ArrayList<Cell> getPath() {
		return path;
	}
	
	public Cell[][] getMaze() {
		return maze;
	}
	
	public int getNumExpandedCells() {
		return numExpanded;
	}
	
	// in MB
	public long getMemory() {
		Runtime runtime = Runtime.getRuntime();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		memory = memory / (1024L * 1024L);
		return memory;
	}
	
	private class Direction {
		int dx;
		int dy;
		
		public Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
	}
	
	
}
