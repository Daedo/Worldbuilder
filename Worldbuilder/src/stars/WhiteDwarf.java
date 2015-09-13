package stars;

import tools.HelperFunctions;

public class WhiteDwarf extends Star {
	public WhiteDwarf() {
		this.massInSolarMasses=HelperFunctions.getRandomRange(0.17, 1.7);
		setRadius();
	}

	private void setRadius() {
		this.radius = Math.pow(this.massInSolarMasses, -1.0/3.0);
	}
	
	public WhiteDwarf(String str) {
		String[] val = str.split(",");
		this.massInSolarMasses = Double.parseDouble(val[1]);
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
		return "WhiteDwarf,"+this.massInSolarMasses+","+this.radius;
	}
}
