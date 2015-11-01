package Mazes;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MazeSolver {

	/**
	 * Location assignment locVal = ( x * length ) + y y = locVal % length x =
	 * locVal / length
	 */
	// MAZE
	private int[][] maze;
	private Set<Integer> openLocations;
	private Set<Integer> filledLocations;

	// PAUSE TIME
	private final int TIME = 3;

	// LOCATION VALUES
	private final int OPEN_LOCATION = 3;
	private final int DEAD_END_FILL = 1;
	private final int CLOSED_LOCATION = 2;
	private final int FINAL_PATH = 6;
	private final int ENTRANCE = 4;
	
	// SIZE OF THE FRAME
	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 750;
	private MazeFrame mazeFrame;

	// DEAD END ALGORITHM
	private LinkedHashSet<Integer> deadEnds;
	private boolean flag;

	// NOT OMNIPOTENT
	private Set<Integer> finalPath;
	private Set<Integer> checkedLocations;
	private Set<Integer> uncheckedLocations;
	private MazePanel mazePanel;
	private int start;
	private int end;
	// MORE EFFICIENT
	private List<Integer/* locations */> currentlyChecking;
	int[][] parents;

	public MazeSolver( int[][] maze ) {
		this.maze = maze;
		openLocations = new LinkedHashSet<Integer>();
		filledLocations = new LinkedHashSet<Integer>();
		flag = true;
		printMaze();
	}

	// Solve using right hand rule
	public void solve( int startingLocation, int endingLocation ) {
		initialize();
		uncheckedLocations = openLocations;
		checkedLocations = new LinkedHashSet<Integer>();
		currentlyChecking = new ArrayList<Integer>();
		finalPath = new LinkedHashSet<Integer>();
		start = startingLocation;
		end = endingLocation;
		// findPath( startingLocation );
		parents = new int[maze.length][maze[0].length];
		startSearch( startingLocation );
		printPath();
	}

	public boolean findPath( int location ) {
		pause();
		List<Integer> surroundingLocations = openLocations( location );
		uncheckedLocations.remove( location );
		checkedLocations.add( location );
		if( location != start ) {
			modifyLocation( location / maze.length, location % maze.length,
			        DEAD_END_FILL );
		}
		for( int x : surroundingLocations ) {
			if( x == end ) {
				return true;
			}
			if( findPath( x ) ) {
				if( x != start )
					finalPath.add( x );
				return true;
			}
		}
		return false;
	}

	public void startSearch( int location ) {
		findPathA( location );
		while( flag ) {
			pause();
			findPathA( currentlyChecking.remove( 0 ) );
		}
	}

	public void findPathA( int location ) {
		List<Integer> surroundingLocations = openLocations( location );
		uncheckedLocations.remove( location );
		checkedLocations.add( location );
		if( location != start ) {
			modifyLocation( location / maze.length, location % maze.length,
			        DEAD_END_FILL );
		}
		for( int i : surroundingLocations ) {
			parents[i % maze.length][i / maze[0].length] = location;
			currentlyChecking.add( i );
			if( i == end ) {
				flag = false;
				finishPath( i );
				return;
			}
		}
	}

	private void finishPath( int location ) {
		if( location != start ) {
			if( location != end )
				finalPath.add( location );
			finishPath( parents[location % maze.length][location
			        / maze[0].length] );
		}
	}

	public ArrayList<Integer> openLocations( int loc ) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int x = loc / maze.length;
		int y = loc % maze.length;
		int down = ( ( x + 1 ) * maze.length ) + y;
		if( uncheckedLocations.contains( down ) && !isWall( x + 1, y ) ) {
			temp.add( down );
		} else if( isEnd( down ) ) {
			temp.clear();
			temp.add( down );
			return temp;
		}
		int up = ( ( x - 1 ) * maze.length ) + y;
		if( uncheckedLocations.contains( up ) && !isWall( x - 1, y ) ) {
			temp.add( up );
		} else if( isEnd( up ) ) {
			temp.clear();
			temp.add( up );
			return temp;
		}
		int right = ( x * maze.length ) + ( y + 1 );
		if( uncheckedLocations.contains( right ) && !isWall( x, y + 1 ) ) {
			temp.add( right );
		} else if( isEnd( right ) ) {
			temp.clear();
			temp.add( right );
			return temp;
		}
		int left = ( x * maze.length ) + ( y - 1 );
		if( uncheckedLocations.contains( left ) && !isWall( x, y - 1 ) ) {
			temp.add( left );
		} else if( isEnd( left ) ) {
			temp.clear();
			temp.add( left );
			return temp;
		}
		return temp;
	}

	public boolean isEnd( int location ) {
		if( location == end ) {
			return true;
		}
		return false;
	}

	// Solve using the fill dead end method
	public void solveDeadEnd() {
		initialize();
		deadEnds = new LinkedHashSet<Integer>();
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

	public void fillDeadEnd( int x, int y ) {
		pause();
		openLocations.remove( ( x * maze.length ) + y );
		maze[y][x] = DEAD_END_FILL;
		modifyLocation( x, y, DEAD_END_FILL );
	}

	public void fillPath() {
		for( int i : openLocations ) {
			modifyLocation( i / maze.length, i % maze.length, FINAL_PATH );
		}
	}

	public boolean isDeadEnd( int location ) {
		try {
			int x = location / maze.length;
			int y = location % maze.length;
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
		} catch( Exception e ) {
			return false;
		}
	}

	public boolean isDeadEnd( int x, int y ) {
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

	public boolean isWall( int x, int y ) {
		return maze[y][x] != OPEN_LOCATION && maze[y][x] != ENTRANCE;
	}

	public void printMaze() {
		mazeFrame = new MazeFrame( FRAME_WIDTH, FRAME_HEIGHT );
		mazePanel = new MazePanel( maze );
		mazeFrame.add( mazePanel );
		mazePanel.fillMaze();
		mazePanel.setVisible( true );
		mazeFrame.setVisible( true );
	}

	public void printPath() {
		for( int i : finalPath ) {
			pause();
			modifyLocation( i / maze.length, i % maze.length, FINAL_PATH );
		}
	}

	public void modifyLocation( int x, int y, int val ) {
		mazePanel.modifyLocation( x, y, val );
	}

	public void pause() {
		try {
			TimeUnit.MILLISECONDS.sleep( TIME );
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
	}

}
