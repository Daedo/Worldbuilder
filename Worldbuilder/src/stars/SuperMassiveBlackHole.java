package stars;

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
		this.massInSolarMasses = HelperFunctions.getRandomRange(100_000, 10_000_000_000d);
	}
	
	@Override
	public String toString() {
		return "Super Massive Black Hole";
	}
	
	@Override
	public String encode() {
		return "SuperMassiveBlackHole,"+this.massInSolarMasses+","+this.radius;
	}
}
