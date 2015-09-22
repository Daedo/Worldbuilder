package stars;

import data.DoubleUnitValue;
import data.LimitedDoubleUnitValue;
import data.SolarMass;
import tools.HelperFunctions;
import units.LenghtUnit;
import units.MassUnit;
import units.Unit;

public class WhiteDwarf extends Star {
	public static final DoubleUnitValue MIN_MASS_WHITE_DWARF = DoubleUnitValue.createFromUnitValue(0.17, MassUnit.SOLAR_MASS);
	public static final DoubleUnitValue MAX_MASS_WHITE_DWARF = DoubleUnitValue.createFromUnitValue(1.7, MassUnit.SOLAR_MASS);
	
	public WhiteDwarf() {
		double baseMass =  HelperFunctions.getRandomRange(MIN_MASS_WHITE_DWARF, MAX_MASS_WHITE_DWARF);
		this.mass= new SolarMass(baseMass, MIN_MASS_WHITE_DWARF,MAX_MASS_WHITE_DWARF,this::notifyMassChange);
		setRadius();
	}

	private void setRadius() {
		Unit rUnit = null;
		if(this.radius!=null) {
			rUnit = this.radius.getUnit();
		}
		
		this.radius = new DoubleUnitValue(Math.pow(getMass().getUnitValue(), -1.0/3.0),"Radius",LenghtUnit.EARTH_RADIUS);
		
		if(rUnit!=null) {
			this.radius.setUnit(rUnit);
		}
	}
	
	public WhiteDwarf(String str) {		
		String[] val = str.split(",");
		double baseMass = Double.parseDouble(val[1]);
		MassUnit massUnit = MassUnit.parseUnit(val[2]);
		this.mass = new LimitedDoubleUnitValue(baseMass, "Mass", massUnit, true, MIN_MASS_WHITE_DWARF, MAX_MASS_WHITE_DWARF, this::notifyMassChange);
		
		setRadius();
		getRadius().setUnit(LenghtUnit.parseUnit(val[4]));
	}

	@Override
	public String starType() {
		return "White Dwarf";
	}

	public void notifyMassChange() {
		setRadius();
	}
	
	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}
}
