package CodeQuest1415;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Prob09 {

	private static final String INPUT_FILE_NAME = "./src/CodeQuest1415/Prob09.txt";
	
	private static ArrayList<ArrayList<String>> image;

	public static void main( String[] args ) {
		Scanner scan = null;
		image = new ArrayList<ArrayList<String>>();
		try {
			File in = new File( INPUT_FILE_NAME );
			scan = new Scanner( in );
			int cases = scan.nextInt();
			String temp = null;
			for( int i = 0; i < cases; i++ ) {
				int x = scan.nextInt();
				for( int index = 0; index < x; index++ ) {
					temp = scan.nextLine();
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
	
	public static void flipX(){
		
	}
	
	public static void flipY(){
		
	}
	
	public static void inverse(){
		
	}
	
}
