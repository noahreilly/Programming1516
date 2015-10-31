package CodeQuest1415;

import java.io.File;
import java.util.Scanner;

public class Prob12 {

	private static final String INPUT_FILE_NAME = "./src/CodeQuest1415/Prob12.txt";

	public static void main( String[] args ) {
		Scanner scan = null;
		try {
			File in = new File( INPUT_FILE_NAME );
			scan = new Scanner( in );
			int cases = scan.nextInt();
			for( int i = 0; i < cases; i++ ) {
				int x = scan.nextInt();
				for( int index = 0; index < x; index++ ) {
					
				}
			}
		} catch( Exception e ) {
			e.printStackTrace();
		} finally {
			if( scan != null ) {
				scan.close();
			}
		}
	}
	
}
