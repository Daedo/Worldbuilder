package stars;

import data.DoubleUnitValue;
import data.SolarMass;
import data.Value;
import data.ValueInformation;
import tools.HelperFunctions;
import units.LenghtUnit;
import units.MassUnit;
import units.Unit;
import units.VolumeUnit;

public class BlackHole extends Star {
	
	public static final double MIN_MASS_BLACK_HOLE = Unit.fromUnit(5, MassUnit.SOLAR_MASS);
	public static final double MAX_MASS_BLACK_HOLE = Unit.fromUnit(500, MassUnit.SOLAR_MASS);
	
	public DoubleUnitValue photosphere;
	public BlackHole() {
		setMass();
		setRadius();
		setPhotosphere();
	}

	public BlackHole(String str) {
		String[] val = str.split(",");
		double baseMass = Double.parseDouble(val[1]);
		this.mass = new SolarMass(Unit.toUnit(baseMass, MassUnit.SOLAR_MASS));
		setRadius();
		setPhotosphere();
	}

	protected void setPhotosphere() {
		this.photosphere = new DoubleUnitValue(1.5*this.radius.value,"Photosphere",this.radius.unit);
	}

	protected void setRadius() {
		this.radius = new DoubleUnitValue(2.95*this.mass.value,"Schwarzschild Radius",LenghtUnit.SOLAR_RADIUS);
	}

	private void setMass() {
		double baseMass = HelperFunctions.getRandomRange(MIN_MASS_BLACK_HOLE, MAX_MASS_BLACK_HOLE);
		this.mass= new SolarMass(Unit.toUnit(baseMass, MassUnit.SOLAR_MASS));
	}
	
	@Override
	public String starType() {
		return "Black Hole";
	}
	
	@Override
	public ValueInformation getDensity() {
		return new Value<>("Infinity","Density");
	}
	
	@Override
	public DoubleUnitValue getVolume() {
		DoubleUnitValue vol = super.getVolume();
		vol.value = 0;
		vol.unit = VolumeUnit.METER_CUBED;
		return vol;
	}
	
	@Override
	public String encode() {
		return super.encode()+","+this.photosphere.getBaseValue();
	}

	@Override
	public String dataSheet() {
		return super.dataSheet()+"\n"+this.photosphere;
	}
	
	@Override
	public void update(ValueInformation valInfo, String val) {
		if(valInfo.equals(this.mass)) {
			this.mass.value = HelperFunctions.parseDefaultClapToUnit(val, this.mass.getBaseValue(), MIN_MASS_BLACK_HOLE, MAX_MASS_BLACK_HOLE, this.mass.unit);
			setRadius();
			setPhotosphere();
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
