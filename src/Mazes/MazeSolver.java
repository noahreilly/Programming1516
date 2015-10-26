package Mazes;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.TimeUnit;

public class MazeSolver {

	/**
	 * Location assignment locVal = ( x * length ) + y 
	 * y = locVal % length 
	 * x = locVal / length
	 */
	// MAZE
	private int[][] maze;
	private LinkedHashSet<Integer> openLocations;
	private LinkedHashSet<Integer> filledLocations;

	// LOCATION VALUES
	private final int OPEN_LOCATION = 3;
	private final int DEAD_END_FILL = 1;
	private final int CLOSED_LOCATION = 2;
	private final int FINAL_PATH = 6;

	// DEAD END ALGORITH
	private LinkedHashSet<Integer> deadEnds;
	private boolean flag;
	
	// A*
	private HashMap<Integer/*location */, Integer/*H value*/> locationValues;
	private LinkedHashSet<Integer> checkedLocations;
	private LinkedHashSet<Integer> uncheckedLocations;

	// SIZE OF THE FRAME
	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 750;
	private MazeFrame mazeFrame;
	private MazePanel mazePanel;

	public MazeSolver(int[][] maze) {
		this.maze = maze;
		openLocations = new LinkedHashSet<Integer>();
		filledLocations = new LinkedHashSet<Integer>();
		deadEnds = new LinkedHashSet<Integer>();
		flag = true;
		printMaze();
	}

	// Solve using A*
	public void solve(int startingLocation) {
		initialize();
		uncheckedLocations = openLocations;
		findPath( startingLocation );
	}

	public void findPath(int location) {
		LinkedHashSet<Integer> surroundingLocations = openLocations( location );
		for( int x : surroundingLocations ) {
			findPath( x );
		} 
	}

	public LinkedHashSet<Integer> openLocations(int loc) {
		LinkedHashSet<Integer> temp = new LinkedHashSet<Integer>();
		int x = loc / maze.length;
		int y = loc % maze.length;
		// TODO check open surrounding locations
		if( true ) {

		}
		return temp;
	}

	// Solve using the fill dead end method
	public void solveDeadEnd() {
		initialize();
		while( flag ) {
			findDeadEnd();
		}
	}

	public void findDeadEnd() {
		deadEnds = new LinkedHashSet<Integer>();
		for( int x : openLocations ) {
			if( isDeadEnd( x ) ) {
				deadEnds.add( x );
			}
		}
		fillDeadEnd();
	}

	public void fillDeadEnd() {
		if( deadEnds.size() == 0 ) {
			flag = false;
			fillPath();
			return;
		}
		for( int i : deadEnds ) {
			fillDeadEnd( i / maze.length, i % maze.length );
		}
	}

	public void fillDeadEnd(int x, int y) {
		try {
			TimeUnit.MILLISECONDS.sleep( 5 );
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		openLocations.remove( ( x * maze.length ) + y );
		maze[y][x] = DEAD_END_FILL;
		modifyLocation( x, y, DEAD_END_FILL );
	}

	public void fillPath() {
		for( int i : openLocations ) {
			modifyLocation( i / maze.length, i % maze.length, FINAL_PATH );
		}
	}

	public boolean isDeadEnd(int l) {
		int x = l / maze.length;
		int y = l % maze.length;
		int surroundingWalls = 0;
		if( maze[y][x + 1] <= CLOSED_LOCATION ) {
			surroundingWalls++;
		}
		if( maze[y][x - 1] <= CLOSED_LOCATION ) {
			surroundingWalls++;
		}
		if( maze[y + 1][x] <= CLOSED_LOCATION ) {
			surroundingWalls++;
		}
		if( maze[y - 1][x] <= CLOSED_LOCATION ) {
			surroundingWalls++;
		}
		if( surroundingWalls == 3 ) {
			return true;
		}
		return false;
	}

	public boolean isDeadEnd(int x, int y) {
		int surroundingWalls = 0;
		if( maze[y][x + 1] <= CLOSED_LOCATION ) {
			surroundingWalls++;
		}
		if( maze[y][x - 1] <= CLOSED_LOCATION ) {
			surroundingWalls++;
		}
		if( maze[y + 1][x] <= CLOSED_LOCATION ) {
			surroundingWalls++;
		}
		if( maze[y - 1][x] <= CLOSED_LOCATION ) {
			surroundingWalls++;
		}
		if( surroundingWalls == 3 ) {
			return true;
		}
		return false;
	}

	// OTHER METHODS
	public void initialize() {
		for( int y = 0; y < maze.length; y++ ) {
			for( int x = 0; x < maze[0].length; x++ ) {
				if( isWall( x, y ) ) {
					filledLocations.add( ( x * maze.length ) + y );
				} else {
					openLocations.add( ( x * maze.length ) + y );
				}
			}
		}
	}

	public boolean isWall(int x, int y) {
		return maze[y][x] != OPEN_LOCATION;
	}

	public void printMaze() {
		mazeFrame = new MazeFrame( FRAME_WIDTH, FRAME_HEIGHT );
		mazePanel = new MazePanel( maze );
		mazeFrame.add( mazePanel );
		mazePanel.fillMaze();
		mazePanel.setVisible( true );
		mazeFrame.setVisible( true );
	}

	public void modifyLocation(int x, int y, int val) {
		mazePanel.modifyLocation( x, y, val );
	}

}
