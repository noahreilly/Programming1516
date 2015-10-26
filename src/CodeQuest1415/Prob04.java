package CodeQuest1415;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prob04 {

	private static final String INPUT_FILE_NAME = "./src/CodeQuest1415/Prob04.txt";

	public static void main(String[] args) {
		Scanner scan = null;
		try {
			File in = new File( INPUT_FILE_NAME );
			scan = new Scanner( in );
			int cases = scan.nextInt();
			String tempI = null;
			double val = 0;
			String tempS = null;
			double converted = 0;
			Pattern p = Pattern.compile( ".*(\\d+)\\s.*" );
			for( int i = 0; i < cases; i++ ) {
				int x = scan.nextInt();
				for( int index = 0; index < x; index++ ) {
					tempI = scan.nextLine();
					Matcher m = p.matcher( tempI );
					if( m.matches() ){
						val = Integer.parseInt( m.group( 1 ) ) * 1.0;
						System.out.println( val );
					}
					tempS = scan.next();
					System.out.print( tempI + " " + tempS + " = " );
					if( tempS.equals( "F" ) ) {
						converted = ( 5 / 9.0 ) * ( val - 32 );
						System.out.println( converted + " C" );
					} else {
						converted = ( ( 9 * val ) / 5 ) + 32;
						System.out.println( converted + " F" );
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
