package stargenerator;

import java.util.Random;
import java.util.Vector;

import stars.BlackHole;
import stars.GiantStar;
import stars.MainClassStar;
import stars.NeutronStar;
import stars.MainClassStar.StarClass;
import stars.Star;
import stars.SuperMassiveBlackHole;
import stars.WhiteDwarf;

public class Stargenerator {
	private static final float starsPerKubicLightjear = 0.004f;
	private static final double probMainClassStar = 0.9;
	private static final double probWhiteDwraf = 0.096;
	
	public static Vector<Star> generateFromSize(double volume) {
		int count = (int) Math.round(volume * starsPerKubicLightjear);
		return generateFromNumber(count);
	}
	
	public static Vector<Star> generateFromNumber(int numberOfStars) {
		Vector<Star> out = new Vector<>();
		for(int i=0;i<numberOfStars;i++) {
			out.add(generateStar());
		}
		
		return out;
	}
	
	private static Star generateStar() {
		Random r = new Random();
		double rnd = r.nextDouble();
		
		if(rnd <=0.9) {
			return generateMainClassStar();
		} else if(rnd<=probMainClassStar+probWhiteDwraf) {
			return generateWhiteDwraf();
		} else {
			return generateGiantStar(); 
		}
	}

	public static GiantStar generateGiantStar() {
		return new GiantStar();
	}

	public static WhiteDwarf generateWhiteDwraf() {
		return new WhiteDwarf();
	}
	
	public static MainClassStar generateMainClassStar(StarClass sClass) {
		return new MainClassStar(sClass);
	}

	public static MainClassStar generateMainClassStar() {
		return new MainClassStar();
	}

	public static BlackHole generateBlackHole() {
		return new BlackHole();
	}
	
	public static SuperMassiveBlackHole generateSuperMassiveBlackHole() {
		return new SuperMassiveBlackHole();
	}
	
	public static NeutronStar generateNeutronStar() {
		return new NeutronStar();
	}
}
