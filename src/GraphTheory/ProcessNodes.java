package GraphTheory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProcessNodes {

	// ASSUMING EACH LETTER IS USED A-x
	
	private final String INPUT_FILE_NAME = "./src/GraphTheory/Input.txt";
	private final String OUTPUT_FILE_NAME = "./src/GraphTheory/Output.txt";
	
	private Map<String, Map<String, Integer>> paths;
	private Map<String, Integer> processedPaths;

	public ProcessNodes() {
		Scanner scan = null;
		try {
			paths = new HashMap<String, Map<String, Integer>>();
			File in = new File( INPUT_FILE_NAME );
			scan = new Scanner( in );
			int cases = scan.nextInt();
			for( int i = 0; i < cases; i++ ) {
				String source = scan.next();
				String destination = scan.next();
				int cost = scan.nextInt();
				if( !paths.keySet().contains( source ) ){
					paths.put( source, new HashMap<String, Integer>() );
				}
				if( !paths.get( source ).keySet().contains( destination ) ){
					paths.get( source ).put( destination, cost );
				}
			}
			for( String source : paths.keySet() ){
				process( source );
			}
		} catch( Exception e ) {
			e.printStackTrace();
		} finally {
			if( scan != null ) {
				scan.close();
			}
		}
	}
	
	public void process( String source ){
		for( String destination : paths.get( source ).keySet() ){
			String pathKey = source + destination;
			int pathCost = paths.get( source ).get( destination );
			if( !processedPaths.keySet().contains( pathKey ) ){
				processedPaths.put( pathKey, pathCost );
			} else{
				int previousCost = processedPaths.get( pathKey );
				if( pathCost < previousCost ){
					processedPaths.put( pathKey, pathCost );
				}
			}
		}
	}
	
}
