package homework1;

import java.util.ArrayList;

public class Visualizer {
	
	public static void displayMaze(Cell[][] maze, ArrayList<Cell> path) {
		int row = maze.length;
		int col = maze[0].length;
		char[][] display = new char[row][col];
		// open cells and closed cells
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				Cell c = maze[i][j];
				if(!c.isBlocked()) {
					display[i][j] = '0';
				} else {
					display[i][j] = '1';
				}
			}
		}
		
		// display path
		for(Cell p : path) {
			int x = p.getX();
			int y = p.getY();
			display[x][y] = '*';
		}
		
		// print
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				System.out.print(display[i][j]);
				if((j + 1) % col == 0) {
					System.out.print("\n");
				}
			}
		}
	}

}
