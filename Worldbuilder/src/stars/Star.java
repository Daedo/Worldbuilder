package stars;

import data.DoubleUnitValue;
import data.Value;
import data.ValueInformation;
import units.AreaUnit;
import units.DensityUnit;
import units.LenghtUnit;
import units.MassUnit;
import units.VolumeUnit;

public abstract class Star {

	public DoubleUnitValue mass;
	public DoubleUnitValue radius;
	public Value<String> name = new Value<>("","Name",true);

	public DoubleUnitValue getCircumference() {
		DoubleUnitValue out =  new DoubleUnitValue(this.radius.getUnitValue()*2*Math.PI,"Circumference",this.radius.getUnit());

		return out;
	}

	public DoubleUnitValue getSurfaceArea() {
		double r = this.radius.getUnitValue();
		DoubleUnitValue out =  new DoubleUnitValue(r*r*4*Math.PI,"Surface Area",AreaUnit.getUnitFromLenght((LenghtUnit)this.radius.getUnit()));
		return out;
	}

	public DoubleUnitValue getVolume() {
		double r = this.radius.getUnitValue();
		DoubleUnitValue out =  new DoubleUnitValue(r*r*r*4/3*Math.PI,"Volume",VolumeUnit.getUnitFromLenght((LenghtUnit)this.radius.getUnit()));

		return out;
	}

	public ValueInformation getDensity() {
		DoubleUnitValue vol = getVolume();
		DoubleUnitValue out =  new DoubleUnitValue(this.mass.getUnitValue()/vol.getUnitValue(),"Density",DensityUnit.getUnitFromMassAndVolume((MassUnit)this.mass.getUnit(), (VolumeUnit)vol.getUnit()));

		return out;
	}

		
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

	
	//Sets new Values from the GUI
	
	
	
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
	
	public String starType() {
		return "Star";
	}

	@Override
	public String toString() {
		String out = starType();
		if(getNameString()!="") {
			out+=" - \""+getNameString()+"\"";
		}
		return out;
	}

}
