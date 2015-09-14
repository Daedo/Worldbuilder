package stars;

import data.DoubleUnitValue;
import data.SolarMass;
import tools.HelperFunctions;

public class WhiteDwarf extends Star {
	public WhiteDwarf() {
		this.mass= new SolarMass( HelperFunctions.getRandomRange(0.17, 1.7));
		setRadius();
	}

	private void setRadius() {
		this.radius = new DoubleUnitValue(Math.pow(this.mass.value, -1.0/3.0),"Radius","Earth Radii");
	}
	
	public WhiteDwarf(String str) {
		String[] val = str.split(",");
		this.mass = new SolarMass( Double.parseDouble(val[1]));
		setRadius();
	}

	@Override
	public String toString() {
		return "White Dwarf";
	}

	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encode() {
		return "WhiteDwarf,"+this.mass.value+","+this.radius.value;
	}
}
