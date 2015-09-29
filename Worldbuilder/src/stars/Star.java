package stars;

import data.DoubleUnitValue;
import data.DoubleValue;
import data.LimitedDoubleUnitValue;
import data.Value;
import data.ValueInformation;
import units.AreaUnit;
import units.DensityUnit;
import units.LenghtUnit;
import units.MassUnit;
import units.VolumeUnit;

public abstract class Star {

	public DoubleUnitValue mass;
	public final LimitedDoubleUnitValue radius = new LimitedDoubleUnitValue(0, "Radius", LenghtUnit.getBaseUnit(), false,null,null, this::notifyRadiusChange);
	public Value<String> name = new Value<>("","Name",true);
	
	private final DoubleUnitValue circumference = new DoubleUnitValue(0,"Circumference",this.radius.getUnit());
	private final DoubleUnitValue surfaceArea	= new DoubleUnitValue(0,"Surface Area",AreaUnit.getUnitFromLenght((LenghtUnit)this.radius.getUnit()));
	private final DoubleUnitValue volume		= new DoubleUnitValue(0,"Volume",VolumeUnit.getUnitFromLenght((LenghtUnit)this.radius.getUnit()));
	private final DoubleUnitValue density		= new DoubleUnitValue(0,"Density",null);

	//Save Load Stuff
	
	public String dataSheet() {
		String out = "";
		out+=this.name+"\n";
		out+=this.mass+"\n";
		out+=this.radius+"\n";
		out+=getCircumference()+"\n";
		out+=getSurfaceArea()+"\n";
		out+=getVolume()+"\n";
		out+=getDensity();
		return out;
	}

	public abstract String toInfobox();

	//TODO Add getValue in Encodes, add Units in decoding
	protected static String encodeValue(DoubleUnitValue value) {
		return value.getBaseValue()+","+value.getUnit();
	}
	
	protected static String encodeValue(Value<?> value) {
		return value.getValue().toString();
	}
	
	public String encode() {
		return starType()+","+encodeValue(this.mass)+","+encodeValue(this.radius)+","+encodeValue(this.name);
	}

	public static Star decode(String str) {
		String[] values = str.split(",");
		switch(values[0]) {
		case "Black Hole":
			return new BlackHole(str);
		case "Giant Star":
			return new GiantStar(str);
		case "Main Class Star":
			return new MainClassStar(str);
		case "Neutron Star":
			return new NeutronStar(str);
		case "Super Massive Black Hole":
			return new SuperMassiveBlackHole(str);
		case "White Dwarf":
			return new WhiteDwarf(str);
		default:
			return null;
		}

	}
	
	//Getter
	
	public String getNameString() {
		return this.name.getValue();
	}
	
	public Value<String> getName() {
		return this.name;
	}
	
	public void setName(String newName) {
		this.name.setValue(newName);
	}
	
	public DoubleUnitValue getRadius() {
		return this.radius;
	}
	
	public DoubleUnitValue getMass() {
		return this.mass;
	}
	
	public DoubleUnitValue getCircumference() {
		return this.circumference;
	}

	public DoubleUnitValue getSurfaceArea() {
		return this.surfaceArea;
	}

	public DoubleValue getVolume() {
		return this.volume;
	}

	public ValueInformation getDensity() {
		return this.density;
	}
	
	public String starType() {
		return "Star";
	}

	@Override
	public String toString() {
		String out = starType();
		if(!getNameString().trim().equals("")) {
			out+=" - \""+getNameString()+"\"";
		}
		return out;
	}
	
	protected void setSphereUnits() {
		this.circumference.setUnit(this.radius.getUnit());
		this.surfaceArea.setUnit(AreaUnit.getUnitFromLenght((LenghtUnit)this.radius.getUnit()));
		this.volume.setUnit(VolumeUnit.getUnitFromLenght((LenghtUnit)this.radius.getUnit()));
		this.density.setUnit(DensityUnit.getUnitFromMassAndVolume((MassUnit)this.mass.getUnit(), (VolumeUnit)this.volume.getUnit()));
	}
	
	private void notifyRadiusChange() {
		double r = this.radius.getBaseValue();
		this.circumference.setBaseValue(r*2*Math.PI);
		this.surfaceArea.setBaseValue(r*r*4*Math.PI);
		this.volume.setBaseValue(r*r*r*4/3*Math.PI);
		if(r!=0) {
		this.density.setBaseValue(this.mass.getBaseValue()/this.volume.getBaseValue());
		} else {
			this.density.setBaseValue(0);
		}
	}

}
