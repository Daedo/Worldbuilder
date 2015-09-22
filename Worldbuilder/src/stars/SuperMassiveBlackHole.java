package stars;

import data.DoubleUnitValue;
import data.LimitedDoubleUnitValue;
import data.SolarMass;
import tools.HelperFunctions;
import units.LenghtUnit;
import units.MassUnit;
import units.Unit;

public class SuperMassiveBlackHole extends BlackHole {
	public static final DoubleUnitValue MIN_MASS_SUPER_MASSIVE_BLACK_HOLE = DoubleUnitValue.createFromUnitValue(100_000,MassUnit.SOLAR_MASS);
	public static final DoubleUnitValue MAX_MASS_SUPER_MASSIVE_BLACK_HOLE = DoubleUnitValue.createFromUnitValue(10_000_000_000d,MassUnit.SOLAR_MASS);
	
	public SuperMassiveBlackHole() {
		setMass();
		setSchwarzschildRadius();
		setPhotosphere();
	}
	
	public SuperMassiveBlackHole(String str) {
		String[] val = str.split(",");
		double baseMass = Double.parseDouble(val[1]);
		Unit massUnit = MassUnit.parseUnit(val[2]);
		this.mass = new LimitedDoubleUnitValue(baseMass, "Mass", massUnit, true, MIN_MASS_SUPER_MASSIVE_BLACK_HOLE, MAX_MASS_SUPER_MASSIVE_BLACK_HOLE, this::notifyMassChange);
		
		setSchwarzschildRadius();
		getRadius().setUnit(LenghtUnit.parseUnit(val[6]));
		
		setPhotosphere();
		getPhotosphere().setUnit(LenghtUnit.parseUnit(val[8]));
	}

	private void setMass() {
		double baseMass = HelperFunctions.getRandomRange(MIN_MASS_SUPER_MASSIVE_BLACK_HOLE, MAX_MASS_SUPER_MASSIVE_BLACK_HOLE);
		this.mass= new SolarMass(baseMass,MIN_MASS_SUPER_MASSIVE_BLACK_HOLE,MAX_MASS_SUPER_MASSIVE_BLACK_HOLE,this::notifyMassChange);
	}
	
	@Override
	public String starType() {
		return "Super Massive Black Hole";
	}
}
