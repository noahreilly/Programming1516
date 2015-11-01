package Mazes;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MazeFrame extends JFrame{
	
	private static final long serialVersionUID = -4763320574165957054L;

	public MazeFrame( int width, int height ){
		super();
		this.setSize( new Dimension( width, height ) );
	}
	
}
