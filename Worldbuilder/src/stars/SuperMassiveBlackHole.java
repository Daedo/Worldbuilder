package stars;

import data.SolarMass;
import data.ValueInformation;
import tools.HelperFunctions;
import units.MassUnit;
import units.Unit;

public class SuperMassiveBlackHole extends BlackHole {
	public static final double MIN_MASS_SUPER_MASSIVE_BLACK_HOLE = Unit.fromUnit(100_000,MassUnit.SOLAR_MASS);
	public static final double MAX_MASS_SUPER_MASSIVE_BLACK_HOLE = Unit.fromUnit(10_000_000_000d,MassUnit.SOLAR_MASS);
	
	public SuperMassiveBlackHole() {
		setMass();
		setRadius();
		setPhotosphere();
	}
	
	public SuperMassiveBlackHole(String str) {
		super(str);
	}

	private void setMass() {
		double baseMass = HelperFunctions.getRandomRange(MIN_MASS_SUPER_MASSIVE_BLACK_HOLE, MAX_MASS_SUPER_MASSIVE_BLACK_HOLE);
		this.mass = new SolarMass(Unit.toUnit(baseMass, MassUnit.SOLAR_MASS));
	}
	
	@Override
	public String starType() {
		return "Super Massive Black Hole";
	}
	
	@Override
	public void update(ValueInformation valInfo, String val) {
		if(valInfo.equals(this.mass)) {
			this.mass.value = HelperFunctions.parseDefaultClapToUnit(val, this.mass.value, MIN_MASS_SUPER_MASSIVE_BLACK_HOLE, MAX_MASS_SUPER_MASSIVE_BLACK_HOLE, MassUnit.SOLAR_MASS);
			setRadius();
			setPhotosphere();
		} else {
			super.update(valInfo, val);
		}
	}
}
