package stars;

import data.DoubleUnitValue;
import data.DoubleValue;
import data.LimitedDoubleUnitValue;
import data.SolarMass;
import data.Value;
import data.ValueInformation;
import tools.HelperFunctions;
import units.LenghtUnit;
import units.MassUnit;
import units.Unit;

public class BlackHole extends Star {
	
	public static final DoubleUnitValue MIN_MASS_BLACK_HOLE = DoubleUnitValue.createFromUnitValue(5, MassUnit.SOLAR_MASS);
	public static final DoubleUnitValue MAX_MASS_BLACK_HOLE = DoubleUnitValue.createFromUnitValue(500, MassUnit.SOLAR_MASS);
	
	private DoubleUnitValue photosphere,schwarzschildRadius;
	
	public BlackHole() {
		setMass();
		setSchwarzschildRadius();
		setPhotosphere();
	}

	public BlackHole(String str) {
		String[] val = str.split(",");
		double baseMass = Double.parseDouble(val[1]);
		Unit massUnit = MassUnit.parseUnit(val[2]);
		this.mass = new LimitedDoubleUnitValue(baseMass, "Mass", massUnit, true, MIN_MASS_BLACK_HOLE, MAX_MASS_BLACK_HOLE, this::notifyMassChange);
		
		setSchwarzschildRadius();
		this.schwarzschildRadius.setUnit(LenghtUnit.parseUnit(val[6]));
		
		setPhotosphere();
		this.photosphere.setUnit(LenghtUnit.parseUnit(val[8]));
	}

	protected void setPhotosphere() {
		double photosphereSizeInBaseUnits = 1.5*this.schwarzschildRadius.getBaseValue();
		//Old Calculation: Units cancel each other out
		//Unit.fromUnit(1.5*this.schwarzschildRadius.getUnitValue(LenghtUnit.KILOMETER),LenghtUnit.KILOMETER);
		
		if(this.photosphere==null) {
			//Create
			this.photosphere = new DoubleUnitValue(photosphereSizeInBaseUnits, "Photosphere", this.schwarzschildRadius.getUnit());
		} else {
			//Update
			this.photosphere.setBaseValue(photosphereSizeInBaseUnits);
		}
	}

	protected void setSchwarzschildRadius() {
		double schwarzschildRadiusInBaseUnits = Unit.fromUnit(2.95*this.mass.getUnitValue(MassUnit.SOLAR_MASS), LenghtUnit.KILOMETER);
		
		if(this.schwarzschildRadius==null) {
			this.schwarzschildRadius = new DoubleUnitValue(schwarzschildRadiusInBaseUnits,"Schwarzschild Radius",LenghtUnit.KILOMETER);
		} else {
			this.schwarzschildRadius.setBaseValue(schwarzschildRadiusInBaseUnits);
		}
	}
	
	public DoubleUnitValue getSchwarzschildRadius() {
		return this.schwarzschildRadius;
	}
	
	public DoubleUnitValue getPhotosphere() {
		return this.photosphere;
	}

	private void setMass() {
		double baseMass = HelperFunctions.getRandomRange(MIN_MASS_BLACK_HOLE, MAX_MASS_BLACK_HOLE);
		this.mass= new SolarMass(baseMass,MIN_MASS_BLACK_HOLE,MAX_MASS_BLACK_HOLE,this::notifyMassChange);
	}
	
	public void notifyMassChange() {
		setSchwarzschildRadius();
		setPhotosphere();
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
	public DoubleValue getVolume() {
		DoubleValue vol = new DoubleValue(0, "Volume");
		return vol;
	}
	
	@Override
	public String encode() {
		return super.encode()+","+encodeValue(this.schwarzschildRadius)+","+encodeValue(this.photosphere);
	}

	@Override
	public String dataSheet() {
		return super.dataSheet()+"\n"+
				this.schwarzschildRadius+"\n"+
				this.photosphere;
	}
	
	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}
}
