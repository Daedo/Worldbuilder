package stargenerator;

import stars.BlackHole;
import stars.MainClassStar;
import stars.Star;

public class StarDataSheetGenerator {
	public static String generateDataSheet(Star star) {
		String out = "";

		out+=star.starType();
		if (star instanceof MainClassStar) {
			MainClassStar main = (MainClassStar) star;
			out+= " - Class "+main.getsClass().name();
		}
		out+="\n\n";

		if(star.getNameString()!=null && !star.getNameString().equals("")) {
			out+=star.getName()+"\n";
		}
		out+=star.getMass()+"\n";
		out+=star.getRadius()+"\n";
		out+=star.getCircumference()+"\n";
		out+=star.getSurfaceArea()+"\n";
		out+=star.getVolume()+"\n";
		out+=star.getDensity()+"\n";

		if (star instanceof BlackHole) {
			BlackHole blackHole = (BlackHole) star;
			out+=blackHole.getSchwarzschildRadius()+"\n";
			out+=blackHole.getPhotosphere()+"\n";
		} else if (star instanceof MainClassStar) {
			MainClassStar main = (MainClassStar) star;
			out+=main.getLuminosity()+"\n";
			out+=main.getLifetime()+"\n";
			out+=main.getTemperature()+"\n";
			out+=main.getHabitableZoneInner()+"\n";
			out+=main.getHabitableZoneOuter()+"\n";
		}
		out = out.trim();

		return out;
	}
}
