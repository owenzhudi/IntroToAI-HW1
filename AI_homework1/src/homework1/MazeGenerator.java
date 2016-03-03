package homework1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * This class generates a maze
 * @author Di Zhu
 *
 */
public class MazeGenerator {
	private Cell[][] maze;
	private Direction[] dir = {new Direction(-1, 0), new Direction(1, 0),
			                   new Direction(0, -1), new Direction(0, 1)};
	Random rnd = new Random();
	
	public MazeGenerator(int row, int col) {
		maze = new Cell[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				maze[i][j] = new Cell(i, j);
			}
		}
		generate(0, 0);
	}
	
	private class Direction {
		int dx;
		int dy;
		
		public Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}
	}
	
	/**
	 * recursive DFS
	 * this may cause java.lang.stackoverflow exception
	private void generate(int x, int y) {
		int row = maze.length;
		int col = maze[0].length;
		if(x < 0 || x >= row) {
			return;
		}
		if(y < 0 || y >= col) {
			return;
		}
		//maze[x][y] = new Cell(x, y);
		//maze[x][y].setXY(x, y);
		if(maze[x][y].isVisited()) {
			return;
		}
		maze[x][y].visit();

		double pos = rnd.nextDouble();
		if(pos < 0.3) {
			maze[x][y].setBlocked(true);
		} else {
			maze[x][y].setBlocked(false);
		}
		Collections.shuffle(Arrays.asList(dir));
		for(Direction d : dir) {
			int nx = x + d.dx;
			int ny = y + d.dy;
			if((nx < 0 || nx >= row) || (ny < 0 || ny >= col)) {
				continue;
			}
			if(!maze[nx][ny].isVisited()) {
				generate(nx, ny);
			}
		}
	}
	*/
	
	// non-recursive version of DFS to generate maze
	// in order to avoid stack overflow exception
	private void generate(int x, int y) {
		int row = maze.length;
		int col = maze[0].length;
		Stack<Cell> stack = new Stack<Cell>();
		if((0 <= x && x < row) && (0 <= y && y < col)) {
			stack.push(maze[x][y]);
		}
		while(!stack.isEmpty()) {
			Cell current = stack.pop();
			int sx = current.getX();
			int sy = current.getY();
			if(current.isVisited()) {
				continue;
			}
			current.visit();
			double pos = rnd.nextDouble();
			if(pos < 0.3) {
				current.setBlocked(true);
			} else {
				current.setBlocked(false);
			}
			Collections.shuffle(Arrays.asList(dir));
			for(Direction d : dir) {
				int nx = sx + d.dx;
				int ny = sy + d.dy;
				if((nx < 0 || nx >= row) || (ny < 0 || ny >= col)) {
					continue;
				}
				if(!maze[nx][ny].isVisited()) {
					stack.push(maze[nx][ny]);
				}
			}
		}
	}
	
	public Cell[][] getMaze() {
		return maze;
	}
	
	// get the copy of maze
	public Cell[][] getCopy() {
		int row = maze.length;
		int col = maze[0].length;
		Cell[][] newMaze = new Cell[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				newMaze[i][j] = new Cell(i, j);
				newMaze[i][j].setBlocked(maze[i][j].isBlocked());
			}
		}
		return newMaze;
		
	}
	
	public void display() {
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[0].length; j++) {
				if(maze[i][j].isBlocked()) {
					System.out.print("1");
				} else {
					System.out.print("0");
				}
				if((j + 1) % maze[0].length == 0) {
					System.out.print("\n");
				}
			}
		}
	}
}
