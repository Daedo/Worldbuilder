package tools;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class HelperFunctions {

	public static double getRandomRange(double inlusiveLower,double exclusiveUpper) {
		double div = exclusiveUpper-inlusiveLower;
		Random rnd = new Random();
		return inlusiveLower+rnd.nextDouble()*div;
	}

	public static double lerp(double v0, double v1, double t) {
		  return (1-t)*v0 + t*v1;
	}
	
	public static Color rgbLerp(Color c0,Color c1,double t) {
		double r = (1-t)*c0.getRed()+t*c1.getRed();
		double g = (1-t)*c0.getGreen()+t*c1.getGreen();
		double b = (1-t)*c0.getBlue()+t*c1.getBlue();
		Color out = new Color((float)r/255f, (float)g/255f, (float)b/255f);
		return out;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
}