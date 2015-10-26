package CodeQuest1415;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prob01 {

	private static final String INPUT_FILE_NAME = "./src/CodeQuest1415/Prob01.txt";

	public static void main(String[] args) {
		try {
			File in = new File( INPUT_FILE_NAME );
			FileReader fr = new FileReader( in );
			BufferedReader br = new BufferedReader( fr );
			int cases = Integer.parseInt( br.readLine() );
			String line = null;
			String temp = null;
			Pattern p = Pattern.compile( "(\\w).*\\s(\\w).*\\s(\\w).*" );
			for( int i = 0; i < cases; i++ ) {
				int x = Integer.parseInt( br.readLine() );
				for( int index = 0; index < x; index++ ) {
					line = br.readLine();
					Matcher m = p.matcher( line );
					if( m.matches() )
						temp =  m.group(1) + m.group(3) + m.group(2);
						System.out.println( temp.toUpperCase() );
				}
			}
			br.close();
			fr.close();
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}

}
