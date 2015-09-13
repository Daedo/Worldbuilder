package stars;

import tools.HelperFunctions;

public class NeutronStar extends Star {
	public NeutronStar() {
		setMass();
		setRadius();
	}
	
	public NeutronStar(String str) {
		String[] val = str.split(",");
		this.massInSolarMasses = Double.parseDouble(val[1]);
		this.radius = Double.parseDouble(val[2]);
	}

	private void setRadius() {
		this.radius = HelperFunctions.getRandomRange(10, 13);
	}

	private void setMass() {
		this.massInSolarMasses = HelperFunctions.getRandomRange(1.4, 3);
		
	}

	@Override
	public String toString() {
		return "Neutron Star";
	}

	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encode() {
		return "NeutronStar,"+this.massInSolarMasses+","+this.radius;
	}
}
