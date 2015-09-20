package stars;

import data.DoubleUnitValue;
import data.SolarMass;
import data.ValueInformation;
import tools.HelperFunctions;
import units.DensityUnit;
import units.LenghtUnit;
import units.MassUnit;
import units.Unit;

public class NeutronStar extends Star {
	public static final double MIN_MASS_NEUTRON_STAR = Unit.fromUnit(1.4, MassUnit.SOLAR_MASS);
	public static final double MAX_MASS_NEUTRON_STAR = Unit.fromUnit(3, MassUnit.SOLAR_MASS);

	public static final double MIN_RADIUS_NEUTRON_STAR = Unit.fromUnit(10, LenghtUnit.KILOMETER);
	public static final double MAX_RADIUS_NEUTRON_STAR = Unit.fromUnit(13, LenghtUnit.KILOMETER);

	public NeutronStar() {
		setMass();
		setRadius();
	}

	public NeutronStar(String str) {
		String[] val = str.split(",");
		double baseMass = Double.parseDouble(val[1]);
		this.mass = new SolarMass(Unit.toUnit(baseMass, MassUnit.SOLAR_MASS));
		double baseRadius = Double.parseDouble(val[2]);
		this.radius = new DoubleUnitValue( Unit.toUnit(baseRadius, LenghtUnit.KILOMETER) ,"Radius",LenghtUnit.KILOMETER);
	}

	private void setRadius() {
		double baseRadius = HelperFunctions.getRandomRange(MIN_RADIUS_NEUTRON_STAR, MAX_RADIUS_NEUTRON_STAR);
		this.radius =new DoubleUnitValue( Unit.toUnit(baseRadius,LenghtUnit.KILOMETER),"Radius",LenghtUnit.KILOMETER);
	}

	private void setMass() {
		double baseMass = HelperFunctions.getRandomRange(MIN_MASS_NEUTRON_STAR, MAX_MASS_NEUTRON_STAR);
		this.mass = new SolarMass(Unit.toUnit(baseMass, MassUnit.SOLAR_MASS));

	}

	@Override
	public String starType() {
		return "Neutron Star";
	}

	@Override
	public void update(ValueInformation valInfo, String val) {
		if(valInfo.equals(this.mass)) {
			this.mass.value = HelperFunctions.parseDefaultClapToUnit(val, this.mass.value, MIN_MASS_NEUTRON_STAR, MAX_MASS_NEUTRON_STAR, MassUnit.SOLAR_MASS);
		} else if(valInfo.equals(this.radius)) {
			this.radius.value = HelperFunctions.parseDefaultClapToUnit(val, this.radius.value, MIN_RADIUS_NEUTRON_STAR, MAX_RADIUS_NEUTRON_STAR, LenghtUnit.KILOMETER);
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
