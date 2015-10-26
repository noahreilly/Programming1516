package Mazes;

import java.awt.Color;

import javax.swing.JLabel;

public class MazeLocation extends JLabel {

	public MazeLocation(int color) {
		super();
		setColor( color );
		this.setOpaque( true );
	}
	
	public void setColor( int color ){
		switch ( color ){
			case 0:
				this.setBackground( Color.BLACK );
				break;
			case 1:
				this.setBackground( Color.GRAY );
				break;
			case 2:
				this.setBackground( Color.BLUE );
				break;
			case 3:
				this.setBackground( Color.WHITE );
				break;
			case 4:
				this.setBackground( Color.GREEN );
				break;
			case 5 : 
				this.setBackground( Color.RED );
				break;
			case 6 :
				this.setBackground( Color.YELLOW );
				break;
		}
	}
}
