package stars;

import data.DoubleUnitValue;
import data.Value;
import data.ValueInformation;
import units.AreaUnit;
import units.DensityUnit;
import units.LenghtUnit;
import units.MassUnit;
import units.Unit;
import units.VolumeUnit;

public abstract class Star {

	public DoubleUnitValue mass;
	public DoubleUnitValue radius;
	public Value<String> name = new Value<>("","Name",true);

	public DoubleUnitValue getCircumference() {
		return new DoubleUnitValue(2*Math.PI*this.radius.value,"Circumference",this.radius.unit);
	}

	public DoubleUnitValue getSurfaceArea() {
		double r = this.radius.value;
		AreaUnit unit = AreaUnit.getUnitFromLenght((LenghtUnit)this.radius.unit);
		return new DoubleUnitValue(4*Math.PI*r*r,"Surface Area",unit);
	}

	public DoubleUnitValue getVolume() {
		double r = this.radius.value;
		VolumeUnit unit = VolumeUnit.getUnitFromLenght((LenghtUnit)this.radius.unit);
		return new DoubleUnitValue(4/3*Math.PI*r*r*r,"Volume",unit);
	}

	public ValueInformation getDensity() {
		DoubleUnitValue vol = getVolume();
		double dens = this.mass.value/vol.value;
		DensityUnit unit = DensityUnit.getUnitFromMassAndVolume((MassUnit)this.mass.unit,(VolumeUnit)vol.unit);
		return new DoubleUnitValue(dens,"Density",unit);
	}

	public String dataSheet() {
		return this.mass+"\n"+
				this.radius+"\n"+
				getCircumference()+"\n"+
				getSurfaceArea()+"\n"+
				getVolume()+"\n"+
				getDensity();
	}

	public abstract String toInfobox();

	//TODO Add getValue in Encodes, add Units in decoding
	public String encode() {
		return starType()+","+this.mass.getBaseValue()+","+this.radius.getBaseValue()+","+this.name.value;
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

	public void update(ValueInformation valInfo, String val) {
		if(valInfo.equals(this.name)) {
			this.name.value = val.replaceAll(",", "");
		}
	}

	public String getName() {
		if(this.name.value!=null && !this.name.value.equals("")) {
			return this.name.value;
		}
		return "";
	}

	public String starType() {
		return "Star";
	}

	@Override
	public String toString() {
		String out = starType();
		if(getName()!="") {
			out+=" - \""+getName()+"\"";
		}
		return out;
	}

}
