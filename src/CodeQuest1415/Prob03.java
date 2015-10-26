package CodeQuest1415;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Prob03 {

	private static final String INPUT_FILE_NAME = "./src/CodeQuest1415/Prob03.txt";

	public static void main(String[] args) {
		Scanner scan = null;
		try {
			File in = new File( INPUT_FILE_NAME );
			scan = new Scanner( in );
			int cases = scan.nextInt();
			int temp = 0;
			for( int i = 0; i < cases; i++ ) {
				int x = scan.nextInt();
				for( int index = 0; index < x; index++ ) {
					temp = scan.nextInt();
					if( temp < 1582 ) {
						System.out.println( "No" );
					} else if( temp % 4 != 0 ) {
						System.out.println( "No" );
					} else if( temp % 100 != 0 ) {
						System.out.println( "Yes" );
					} else if( temp % 400 != 0 ) {
						System.out.println( "No" );
					} else {
						System.out.println( "Yes" );
					}
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
