package homework1;

import java.util.ArrayList;
import java.util.Random;

public class Main {
	private static final int SIZE = 101;

	public static void main(String[] args) {
		int startX, startY;
		int goalX, goalY;
		MazeGenerator mazeGen= new MazeGenerator(SIZE, SIZE);
		Cell[][] maze = mazeGen.getMaze();
		AStar myAStar;
		AStarLargerG myAStarLargerG;
		AStarSmallerG myAStarSmallerG;
		AStarBackward myAStarBackward;
		AStarAdaptive myAStarAdaptive;
		long timeStart, timeEnd;
		
		Random rand = new Random();
		do {
			startX = rand.nextInt(SIZE);
			startY = rand.nextInt(SIZE);
			goalX = rand.nextInt(SIZE);
			goalY = rand.nextInt(SIZE);
			myAStar = new AStar(maze, startX, startY, goalX, goalY);
			myAStarLargerG = new AStarLargerG(mazeGen.getCopy(), startX, startY, goalX, goalY);
			myAStarSmallerG = new AStarSmallerG(mazeGen.getCopy(), startX, startY, goalX, goalY);
			myAStarBackward = new AStarBackward(mazeGen.getCopy(), startX, startY, goalX, goalY);
			myAStarAdaptive = new AStarAdaptive(mazeGen.getCopy(), startX, startY, goalX, goalY);
		} while((startX == goalX && startY == goalY) &&
				(maze[startX][startY].isBlocked() || maze[goalX][goalY].isBlocked()));
		System.out.println("Start: x= " + startX + " y= " + startY);
		System.out.println("Goal x= " + goalX + " y= " + goalY);
		System.out.println("Maze:");
		// display maze
		mazeGen.display();
		
		// display result after A* search
		timeStart = System.currentTimeMillis();
		myAStar.aStarMain();
		timeEnd = System.currentTimeMillis();
		ArrayList<Cell> path = myAStar.getPath();
		System.out.println("\nMaze after A* search:");
		Visualizer.displayMaze(maze, path);
		System.out.println("Number of expanded cells in Repeated Forward A* search: " + myAStar.getNumExpandedCells());
		System.out.println("Run time of Repeated Forward A*: " + (timeEnd - timeStart) + "ms");
		System.out.println("Memory: " + myAStar.getMemory() +"MB");
		
		// A* with larger g-value
		timeStart = System.currentTimeMillis();
		myAStarLargerG.aStarMain();
		timeEnd = System.currentTimeMillis();
		ArrayList<Cell> pathLargerG = myAStarLargerG.getPath();
		System.out.println("\nMaze after A* search with larger g-value:");
		Visualizer.displayMaze(maze, pathLargerG);
		System.out.println("Number of expanded cells in Repeated Forward A* search with larger g-value: "
				+ myAStarLargerG.getNumExpandedCells());
		System.out.println("Run time of Repeated Forward A* with larger g-value: "
				+ (timeEnd - timeStart) + "ms");
		
		// A* with smaller g-value
		timeStart = System.currentTimeMillis();
		myAStarSmallerG.aStarMain();
		timeEnd = System.currentTimeMillis();
		ArrayList<Cell> pathSmallerG = myAStarSmallerG.getPath();
		System.out.println("\n Maze after A* search with smaller g-value:");
		Visualizer.displayMaze(maze, pathSmallerG);
		System.out.println("Number of expanded cells in Repeated Forward A* search with smaller g-value: "
				+ myAStarSmallerG.getNumExpandedCells());
		System.out.println("Run time of Repeated Forward A* with smaller g-value: "
				+ (timeEnd - timeStart) + "ms");
		
		// Repeated backward A* 
		timeStart = System.currentTimeMillis();
		myAStarBackward.aStarMain();
		timeEnd = System.currentTimeMillis();
		ArrayList<Cell> pathBackward = myAStarBackward.getPath();
		System.out.println("\nMaze after Repeated Backward A* search:");
		Visualizer.displayMaze(maze, pathBackward);
		System.out.println("Number of expanded cells in Repeated Backward A* search with larger g-value: "
				+ myAStarBackward.getNumExpandedCells());
		System.out.println("Run time of Repeated Backward A* with larger g-value: "
				+ (timeEnd - timeStart) + "ms");
		
		// Adaptive A*
		timeStart = System.currentTimeMillis();
		myAStarAdaptive.aStarMain();
		timeEnd = System.currentTimeMillis();
		ArrayList<Cell> pathAdaptive = myAStarAdaptive.getPath();
		System.out.println("\nMaze after Adaptive Forward A* search:");
		Visualizer.displayMaze(maze, pathAdaptive);
		System.out.println("Number of expanded cells in Adaptive Forward A* search: "
				+ myAStarAdaptive.getNumExpandedCells());
		System.out.println("Run time of Adaptive Forward A*:" + (timeEnd - timeStart) + "ms");
	}

}
