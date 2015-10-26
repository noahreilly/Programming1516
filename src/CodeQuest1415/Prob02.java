package CodeQuest1415;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;

public class Prob02 {
	
	private static final String INPUT_FILE_NAME = "./src/CodeQuest1415/Prob02.txt";
	
	public static void main( String [] args ){
		try{
			File in = new File( INPUT_FILE_NAME );
			FileReader fr = new FileReader( in );
			BufferedReader br = new BufferedReader( fr );
			int cases = Integer.parseInt( br.readLine() );
			String temp = null;
			BigInteger finalAns = new BigInteger("0");
			for( int i = 0; i < cases; i++ ){
				int x = Integer.parseInt( br.readLine() );
				for( int index = 0; index < x; index++ ){
					temp = br.readLine();
					finalAns = finalAns.add( new BigInteger( temp ) );
				}
				System.out.println( finalAns );
			}
			br.close();
			fr.close();
		} catch( Exception e ){
			e.printStackTrace();
		}
	}
	
}
