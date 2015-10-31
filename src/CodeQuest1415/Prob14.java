package CodeQuest1415;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Prob14 {

	private static final String INPUT_FILE_NAME = "./src/CodeQuest1415/Prob14.txt";

	public static void main( String[] args ) {
		Scanner scan = null;
		try {
			File in = new File( INPUT_FILE_NAME );
			scan = new Scanner( in );
			int cases = scan.nextInt();
			String originalStrand = null;
			ArrayList<String> suspects = new ArrayList<String>();
			for( int i = 0; i < cases; i++ ) {
				int x = scan.nextInt();
				for( int index = 0; index < x; index++ ) {
					originalStrand = scan.nextLine();
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
