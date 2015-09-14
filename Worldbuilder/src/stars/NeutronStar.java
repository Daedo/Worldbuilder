package stars;

import data.DoubleUnitValue;
import data.SolarMass;
import tools.HelperFunctions;

public class NeutronStar extends Star {
	public NeutronStar() {
		setMass();
		setRadius();
	}
	
	public NeutronStar(String str) {
		String[] val = str.split(",");
		this.mass = new SolarMass(Double.parseDouble(val[1]));
		this.radius = new DoubleUnitValue( Double.parseDouble(val[2]),"Radius","KM");
	}

	private void setRadius() {
		this.radius =new DoubleUnitValue( HelperFunctions.getRandomRange(10, 13),"Radius","KM");
	}

	private void setMass() {
		this.mass = new SolarMass(HelperFunctions.getRandomRange(1.4, 3));
		
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
		return "NeutronStar,"+this.mass.value+","+this.radius.value;
	}
}
