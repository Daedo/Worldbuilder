package stars;

import data.DoubleUnitValue;
import data.LimitedDoubleUnitValue;
import data.SolarMass;
import data.Value;
import data.ValueInformation;
import tools.HelperFunctions;
import units.LenghtUnit;
import units.MassUnit;
import units.Unit;
import units.VolumeUnit;

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
		getRadius().setUnit(LenghtUnit.parseUnit(val[6]));
		
		setPhotosphere();
		getPhotosphere().setUnit(LenghtUnit.parseUnit(val[8]));
	}

	protected void setPhotosphere() {
		Unit pUnit = null;
		if(this.photosphere!=null) {
			pUnit = this.photosphere.getUnit();
		}
		
		this.photosphere = DoubleUnitValue.createFromUnitValue(1.5*this.schwarzschildRadius.getUnitValue(),"Photosphere",this.schwarzschildRadius.getUnit());
		
		if(pUnit!=null) {
			this.photosphere.setUnit(pUnit);
		}
	}

	protected void setSchwarzschildRadius() {
		Unit sUnit = null;
		if(this.schwarzschildRadius!=null) {
			sUnit = this.schwarzschildRadius.getUnit();
		}
		
		this.schwarzschildRadius = DoubleUnitValue.createFromUnitValue(2.95*this.mass.getUnitValue(),"Schwarzschild Radius",LenghtUnit.KILOMETER);
		
		if(sUnit!=null) {
			this.schwarzschildRadius.setUnit(sUnit);
		}
	}
	
	public DoubleUnitValue getSchwarzschildRadius() {
		return this.photosphere;
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
	public DoubleUnitValue getVolume() {
		DoubleUnitValue vol = super.getVolume();
		vol.setBaseValue(0);
		vol.setUnit(VolumeUnit.METER_CUBED);
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
