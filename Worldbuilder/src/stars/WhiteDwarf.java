package stars;

import data.DoubleUnitValue;
import data.SolarMass;
import data.ValueInformation;
import tools.HelperFunctions;
import units.LenghtUnit;
import units.MassUnit;
import units.Unit;

public class WhiteDwarf extends Star {
	public static final double MIN_MASS_WHITE_DWARF = 0.17;
	public static final double MAX_MASS_WHITE_DWARF = 1.7;
	
	public WhiteDwarf() {
		this.mass= new SolarMass( HelperFunctions.getRandomRange(MIN_MASS_WHITE_DWARF, MAX_MASS_WHITE_DWARF));
		setRadius();
	}

	private void setRadius() {
		this.radius = new DoubleUnitValue(Math.pow(this.mass.value, -1.0/3.0),"Radius",LenghtUnit.EARTH_RADIUS,false);
	}
	
	public WhiteDwarf(String str) {
		String[] val = str.split(",");
		double baseMass = Double.parseDouble(val[1]);
		this.mass = new SolarMass(Unit.toUnit(baseMass, MassUnit.SOLAR_MASS));
		setRadius();
	}

	@Override
	public String starType() {
		return "White Dwarf";
	}
	
	@Override
	public void update(ValueInformation valInfo, String val) {
		if(valInfo.equals(this.mass)) {
			double newMass  = HelperFunctions.parseDefault(val, this.mass.value);
			this.mass.value = HelperFunctions.linearClamp(newMass, MIN_MASS_WHITE_DWARF, MAX_MASS_WHITE_DWARF);
			setRadius();
		} else {
			super.update(valInfo, val);
		}
	}

	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}
}
