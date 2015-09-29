package stars;

import data.DoubleUnitValue;
import data.LimitedDoubleUnitValue;
import data.SolarMass;
import tools.HelperFunctions;
import units.LenghtUnit;
import units.MassUnit;
import units.Unit;

public class GiantStar extends Star {
	public static final DoubleUnitValue MIN_MASS_GIANT_STAR = DoubleUnitValue.createFromUnitValue(50, MassUnit.SOLAR_MASS);
	public static final DoubleUnitValue MAX_MASS_GIANT_STAR = DoubleUnitValue.createFromUnitValue(150, MassUnit.SOLAR_MASS);
	
	public GiantStar() {
		double baseMass = HelperFunctions.getRandomRange(MIN_MASS_GIANT_STAR, MAX_MASS_GIANT_STAR);
		this.mass= new SolarMass(baseMass,MIN_MASS_GIANT_STAR,MAX_MASS_GIANT_STAR,this::notifyMassChange);
		this.radius.setUnit(LenghtUnit.SOLAR_RADIUS);
		setRadius();
		setSphereUnits();
	}
	
	private void setRadius() {
		double r = Unit.fromUnit(Math.pow(getMass().getUnitValue(), 0.5), LenghtUnit.SOLAR_RADIUS);
		this.radius.setBaseValue(r);
	}

	public GiantStar(String str) {
		String[] val = str.split(",");
		double baseMass = Double.parseDouble(val[1]);
		MassUnit massUnit = MassUnit.parseUnit(val[2]);
		this.mass = new LimitedDoubleUnitValue(baseMass, "Mass", massUnit, true, MIN_MASS_GIANT_STAR, MAX_MASS_GIANT_STAR, this::notifyMassChange);
		
		setRadius();
		getRadius().setUnit(LenghtUnit.parseUnit(val[4]));
		setSphereUnits();
	}

	@Override
	public String starType() {
		return "Giant Star";
	}

	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void notifyMassChange() {
		setRadius();
	}
}
