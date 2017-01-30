package CodeQuest1415;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Prob00 {
	
	private static final String INPUT_FILE_NAME = "./src/CodeQuest1415/Prob00.txt";
	private static Map<Integer, Integer> results = new TreeMap<Integer, Integer>();
	
	public static void main( String [] args ){
		Scanner scan = null;
		try{
			File in = new File( INPUT_FILE_NAME );
			scan = new Scanner( in );
			while(scan.hasNextInt()){
				int key = scan.nextInt();
				if(results.containsKey( key )){
					results.put(key, results.get( key ) + 1);
				} else{
					results.put( key, 1 );
				}
			}
			for(int i = 1; i <= 30; i++){
				if(!results.containsKey( i )){
					results.put(i, 0);
				}
			}
			for(int x : results.keySet()){
				System.out.println( "Question " + x + ": " + results.get( x ) );
			}
		} catch( Exception e ){
			e.printStackTrace();
		} finally {
			if( scan != null ){
				scan.close();
			}
		}
	}
	
}
