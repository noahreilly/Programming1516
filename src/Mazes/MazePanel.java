package Mazes;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MazePanel extends JPanel{

	private int [][] maze;
	private MazeLocation[][] mazeLabels;
	
	public MazePanel( int [][] maze ){
		super( new GridLayout( maze.length, maze[0].length ) );
		this.setSize( new Dimension( 100, 750 ) );
		this.maze = maze;
		mazeLabels = new MazeLocation[maze.length][maze[0].length];
		this.setVisible(true);
	}
	
	public void fillMaze(){
		for( int y = 0; y < maze.length; y++ ){
			for( int x = 0; x < maze[0].length; x++){
				mazeLabels[y][x] = new MazeLocation( maze[y][x] );
				this.add( mazeLabels[y][x] );
			}
		}
	}
	
	public void modifyLocation( int x, int y, int val ){
		mazeLabels[y][x].setColor( val );
	}
	
}
