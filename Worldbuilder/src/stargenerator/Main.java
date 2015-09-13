package stargenerator;

import java.util.Vector;

import stars.Star;

public class Main {

	public static void main(String[] args) {
		Vector<Star> stars = Stargenerator.generateFromNumber(10);
		for(Star s:stars) {
			System.out.println(s.toString()+"\n"+s.dataSheet()+"\n");
		}
		
	}

}
