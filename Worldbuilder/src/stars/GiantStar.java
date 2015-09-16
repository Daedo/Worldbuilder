package stars;

import data.SolarMass;
import data.SolarRadius;
import data.ValueInformation;
import tools.HelperFunctions;
import units.LenghtUnit;
import units.MassUnit;
import units.Unit;

public class GiantStar extends Star {
	public static final double MIN_MASS_GIANT_STAR = 50;
	public static final double MAX_MASS_GIANT_STAR = 150;
	
	public GiantStar() {
		this.mass = new SolarMass(HelperFunctions.getRandomRange(MIN_MASS_GIANT_STAR, MAX_MASS_GIANT_STAR));
		setRadius();
	}
	
	private void setRadius() {
		this.radius = new SolarRadius(Math.pow(this.mass.value,0.5));
		
	}

	public GiantStar(String str) {
		String[] val = str.split(",");
		double baseMass = Double.parseDouble(val[1]);
		this.mass = new SolarMass(Unit.toUnit(baseMass, MassUnit.SOLAR_MASS));
		double baseRadius = Double.parseDouble(val[2]);
		this.radius = new SolarRadius(Unit.toUnit(baseRadius, LenghtUnit.SOLAR_RADIUS));
	}

	@Override
	public String starType() {
		return "Giant Star";
	}
	
	@Override
	public void update(ValueInformation valInfo, String val) {
		if(valInfo.equals(this.mass)) {
			double newMass = HelperFunctions.parseDefault(val,this.mass.value);
			this.mass.value = HelperFunctions.linearClamp(newMass,MIN_MASS_GIANT_STAR,MAX_MASS_GIANT_STAR);
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
