package stars;

import data.SolarMass;
import data.SolarRadius;

public class GiantStar extends Star {
	public GiantStar() {
		this.mass = new SolarMass(100d);
		this.radius = new SolarRadius(100d);
	}
	
	public GiantStar(String str) {
		String[] val = str.split(",");
		this.mass = new SolarMass(Double.parseDouble(val[1]));
		this.radius = new SolarRadius( Double.parseDouble(val[2]));
	}

	@Override
	public String toString() {
		return "Giant Star";
	}

	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encode() {
		return "GiantStar,"+this.mass.value+","+this.radius.value;
	}
}
