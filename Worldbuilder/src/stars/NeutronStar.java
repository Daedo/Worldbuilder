package stars;

import data.DoubleUnitValue;
import data.SolarMass;
import data.ValueInformation;
import tools.HelperFunctions;
import units.DensityUnit;
import units.LenghtUnit;
import units.Unit;
import units.Units;

public class NeutronStar extends Star {
	public static final double MIN_MASS_NEUTRON_STAR = 1.4;
	public static final double MAX_MASS_NEUTRON_STAR = 3;

	public static final double MIN_RADIUS_NEUTRON_STAR = 10;
	public static final double MAX_RADIUS_NEUTRON_STAR = 13;

	public NeutronStar() {
		setMass();
		setRadius();
	}

	public NeutronStar(String str) {
		String[] val = str.split(",");
		this.mass = new SolarMass(Double.parseDouble(val[1]));
		this.radius = new DoubleUnitValue( Double.parseDouble(val[2]),"Radius",LenghtUnit.KILOMETER);
	}

	private void setRadius() {
		this.radius =new DoubleUnitValue( HelperFunctions.getRandomRange(MIN_RADIUS_NEUTRON_STAR, MAX_RADIUS_NEUTRON_STAR),"Radius",LenghtUnit.KILOMETER);
	}

	private void setMass() {
		this.mass = new SolarMass(HelperFunctions.getRandomRange(MIN_MASS_NEUTRON_STAR, MAX_MASS_NEUTRON_STAR));

	}

	@Override
	public String starType() {
		return "Neutron Star";
	}

	@Override
	public void update(ValueInformation valInfo, String val) {
		if(valInfo.equals(this.mass)) {
			double newMass = HelperFunctions.parseDefault(val,this.mass.value);
			this.mass.value = HelperFunctions.linearClamp(newMass,MIN_MASS_NEUTRON_STAR,MAX_MASS_NEUTRON_STAR);
		} else if(valInfo.equals(this.radius)) {
			double newRadius = HelperFunctions.parseDefault(val,this.radius.value);
			this.mass.value = HelperFunctions.linearClamp(newRadius,MIN_RADIUS_NEUTRON_STAR,MAX_RADIUS_NEUTRON_STAR);
		} else {
			super.update(valInfo, val);
		}
	}

	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueInformation getDensity() {
		DoubleUnitValue dens = (DoubleUnitValue) super.getDensity();
		dens.value = Unit.recalculate(dens.value, dens.unit, DensityUnit.KILOGRAM_PER_CUBIC_METER);
		dens.unit = DensityUnit.KILOGRAM_PER_CUBIC_METER;
		
		return dens;
	}
}
