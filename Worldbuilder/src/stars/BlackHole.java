package stars;

import tools.HelperFunctions;

public class BlackHole extends Star {
	public double photosphere;
	public BlackHole() {
		setMass();
		setRadius();
		setPhotosphere();
	}

	public BlackHole(String str) {
		String[] val = str.split(",");
		this.massInSolarMasses = Double.parseDouble(val[1]);
		setRadius();
		setPhotosphere();
	}

	protected void setPhotosphere() {
		this.photosphere = 1.5*this.radius;
	}

	protected void setRadius() {
		this.radius = 2.95*this.massInSolarMasses;
	}

	private void setMass() {
		this.massInSolarMasses= HelperFunctions.getRandomRange(5, 500);
	}
	
	@Override
	public String toString() {
		return "Black Hole";
	}
	
	@Override
	public double getDensity() {
		return 42;
	}
	
	@Override
	public String encode() {
		return "BlackHole,"+this.massInSolarMasses+","+this.radius+","+this.photosphere;
	}

	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}
}
