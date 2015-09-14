package stars;

import data.DoubleUnitValue;
import data.SolarMass;
import data.SolarRadius;
import data.ValueInformation;
import tools.HelperFunctions;

public class BlackHole extends Star {
	
	public static final double MIN_MASS_BLACK_HOLE = 5;
	public static final double MAX_MASS_BLACK_HOLE = 500;
	
	public DoubleUnitValue photosphere;
	public BlackHole() {
		setMass();
		setRadius();
		setPhotosphere();
	}

	public BlackHole(String str) {
		String[] val = str.split(",");
		this.mass = new SolarMass(Double.parseDouble(val[1]));
		setRadius();
		setPhotosphere();
	}

	protected void setPhotosphere() {
		this.photosphere = new DoubleUnitValue(1.5*this.radius.value,"Photosphere",this.radius.unit);
	}

	protected void setRadius() {
		this.radius = new SolarRadius(2.95*this.mass.value);
	}

	private void setMass() {
		this.mass= new SolarMass(HelperFunctions.getRandomRange(MIN_MASS_BLACK_HOLE, MAX_MASS_BLACK_HOLE));
	}
	
	@Override
	public String toString() {
		return "Black Hole";
	}
	
	@Override
	public DoubleUnitValue getDensity() {
		return new DoubleUnitValue(0,"Density","A Lot");
	}
	
	@Override
	public String encode() {
		return "BlackHole,"+this.mass.value+","+this.radius.value+","+this.photosphere.value;
	}

	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(ValueInformation valInfo, String val) {
		if(valInfo.equals(this.mass)) {
			double newMass = HelperFunctions.parseDefault(val,this.mass.value);
			this.mass.value = HelperFunctions.linearClamp(newMass,MIN_MASS_BLACK_HOLE,MAX_MASS_BLACK_HOLE);
			setRadius();
			setPhotosphere();
		} else {
			super.update(valInfo, val);
		}
	}
}
