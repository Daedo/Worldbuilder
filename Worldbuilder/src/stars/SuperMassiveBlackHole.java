package stars;

import data.SolarMass;
import data.ValueInformation;
import tools.HelperFunctions;

public class SuperMassiveBlackHole extends BlackHole {
	public static final double MIN_MASS_SUPER_MASSIVE_BLACK_HOLE = 100_000;
	public static final double MAX_MASS_SUPER_MASSIVE_BLACK_HOLE = 10_000_000_000d;
	
	public SuperMassiveBlackHole() {
		setMass();
		setRadius();
		setPhotosphere();
	}
	
	public SuperMassiveBlackHole(String str) {
		super(str);
	}

	private void setMass() {
		this.mass = new SolarMass(HelperFunctions.getRandomRange(MIN_MASS_SUPER_MASSIVE_BLACK_HOLE, MAX_MASS_SUPER_MASSIVE_BLACK_HOLE));
	}
	
	@Override
	public String starType() {
		return "Super Massive Black Hole";
	}
	
	@Override
	public void update(ValueInformation valInfo, String val) {
		if(valInfo.equals(this.mass)) {
			double newMass = HelperFunctions.parseDefault(val,this.mass.value);
			this.mass.value = HelperFunctions.linearClamp(newMass,MIN_MASS_SUPER_MASSIVE_BLACK_HOLE,MAX_MASS_SUPER_MASSIVE_BLACK_HOLE);
			setRadius();
			setPhotosphere();
		} else {
			super.update(valInfo, val);
		}
	}
}
