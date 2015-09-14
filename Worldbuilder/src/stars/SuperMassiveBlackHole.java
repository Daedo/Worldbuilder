package stars;

import data.SolarMass;
import tools.HelperFunctions;

public class SuperMassiveBlackHole extends BlackHole {
	public SuperMassiveBlackHole() {
		setMass();
		setRadius();
		setPhotosphere();
	}
	
	public SuperMassiveBlackHole(String str) {
		super(str);
	}

	private void setMass() {
		this.mass = new SolarMass(HelperFunctions.getRandomRange(100_000, 10_000_000_000d));
	}
	
	@Override
	public String toString() {
		return "Super Massive Black Hole";
	}
	
	@Override
	public String encode() {
		return "SuperMassiveBlackHole,"+this.mass+","+this.radius;
	}
}
