package stars;

import data.DoubleUnitValue;
import data.LimitedDoubleUnitValue;
import data.SolarMass;
import data.ValueInformation;
import tools.HelperFunctions;
import units.DensityUnit;
import units.LenghtUnit;
import units.MassUnit;
import units.Unit;

public class NeutronStar extends Star {
	public static final DoubleUnitValue MIN_MASS_NEUTRON_STAR = DoubleUnitValue.createFromUnitValue(1.4, MassUnit.SOLAR_MASS);
	public static final DoubleUnitValue MAX_MASS_NEUTRON_STAR = DoubleUnitValue.createFromUnitValue(3, MassUnit.SOLAR_MASS);

	public static final DoubleUnitValue MIN_RADIUS_NEUTRON_STAR = DoubleUnitValue.createFromUnitValue(10, LenghtUnit.KILOMETER);
	public static final DoubleUnitValue MAX_RADIUS_NEUTRON_STAR = DoubleUnitValue.createFromUnitValue(13, LenghtUnit.KILOMETER);

	public NeutronStar() {
		setMass();
		setRadius();
	}

	public NeutronStar(String str) {
		String[] val = str.split(",");
		double baseMass = Double.parseDouble(val[1]);
		MassUnit massUnit = MassUnit.parseUnit(val[2]);
		this.mass = new LimitedDoubleUnitValue(baseMass, "Mass", massUnit, true, MIN_MASS_NEUTRON_STAR, MAX_MASS_NEUTRON_STAR);
		
		double baseRadius = Double.parseDouble(val[3]);
		LenghtUnit radiusUnit = LenghtUnit.parseUnit(val[2]);
		this.radius = new LimitedDoubleUnitValue(baseRadius, "Radius", radiusUnit, true, MIN_RADIUS_NEUTRON_STAR, MAX_RADIUS_NEUTRON_STAR);
	}

	private void setRadius() {
		Unit rUnit = null;
		if(this.radius!=null) {
			rUnit = this.radius.getUnit();
		}
		
		double baseRadius = HelperFunctions.getRandomRange(MIN_RADIUS_NEUTRON_STAR, MAX_RADIUS_NEUTRON_STAR);
		this.radius= new LimitedDoubleUnitValue(baseRadius,"Radius",LenghtUnit.KILOMETER, true, MIN_RADIUS_NEUTRON_STAR, MAX_RADIUS_NEUTRON_STAR);
		
		if(rUnit!=null) {
			this.radius.setUnit(rUnit);
		}
	}

	private void setMass() {
		Unit mUnit = null;
		if(this.mass!=null) {
			mUnit = this.radius.getUnit();
		}
		
		double baseMass = HelperFunctions.getRandomRange(MIN_MASS_NEUTRON_STAR, MAX_MASS_NEUTRON_STAR);
		this.mass= new SolarMass(baseMass,MIN_MASS_NEUTRON_STAR, MAX_MASS_NEUTRON_STAR);
		
		if(mUnit!=null) {
			this.mass.setUnit(mUnit);
		}
	}

	@Override
	public String starType() {
		return "Neutron Star";
	}

	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueInformation getDensity() {
		DoubleUnitValue dens = (DoubleUnitValue) super.getDensity();
		dens.setUnit(DensityUnit.KILOGRAM_PER_CUBIC_METER);
		
		return dens;
	}
}
