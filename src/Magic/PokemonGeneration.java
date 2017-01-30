package Magic;

import java.util.*;

public class PokemonGeneration {

	public static void main(String [] args){
	    List<Integer> list = new ArrayList<Integer>();
	    for(int i = 0; i < 20; i++){
	    	list.add( (int)(Math.random() * 90) + 10 );
	    }
	    System.out.println( list );
	    bubbleSort(list);
	}
	public static void bubbleSort(List<Integer> list){
		
	}
	public static void pokeStats(){
		int fg = (int)(Math.random() * 65) + 5;
		int fw = (int)(Math.random() * 65) + 5;
		int ff = (int)(Math.random() * 65) + 5;
		int mg = (int)(Math.random() * 65) + 5;
		int mw = (int)(Math.random() * 65) + 5;
		int mf = (int)(Math.random() * 65) + 5;
		System.out.printf("\tGRASS\tWATER\tFIRE%nF:\t%d\t%d\t%d%nM:\t%d\t%d\t%d", fg, fw, ff, mg, mw, mf);
	}
	
}
