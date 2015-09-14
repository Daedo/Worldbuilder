package stars;

import data.DoubleUnitValue;
import data.Value;
import data.ValueInformation;

public abstract class Star {
	
	public DoubleUnitValue mass;
	public DoubleUnitValue radius;
	public Value<String> name = new Value<>("","Name",true);
	
	public DoubleUnitValue getCircumference() {
		return new DoubleUnitValue(2*Math.PI*this.radius.value,"Circumference",this.radius.unit);
	}
	
	public DoubleUnitValue getSurfaceArea() {
		double r = this.radius.value;
		return new DoubleUnitValue(4*Math.PI*r*r,"Surface Area",this.radius.unit+"^2");
	}
	
	public DoubleUnitValue getVolume() {
		double r = this.radius.value;
		return new DoubleUnitValue(4/3*Math.PI*r*r*r,"Volume",this.radius.unit+"^3");
	}
	
	public DoubleUnitValue getDensity() {
		DoubleUnitValue vol = getVolume();
		double dens = this.mass.value/vol.value;
		return new DoubleUnitValue(dens,"Density",this.mass.unit+"/"+vol.unit);
	}

	public String dataSheet() {
		String out = this.mass+"\n"+
				this.radius+"\n"+
				getCircumference()+"\n"+
				getSurfaceArea()+"\n"+
				getVolume()+"\n"+
				getDensity();
		return out;
	}
	
	public abstract String toInfobox();
	
	public abstract String encode();
	
	public static Star decode(String str) {
		String[] values = str.split(",");
		switch(values[0]) {
		case "BlackHole":
			return new BlackHole(str);
		case "GiantStar":
			return new GiantStar(str);
		case "MainClassStar":
			return new MainClassStar(str);
		case "NeutronStar":
			return new NeutronStar(str);
		case "SuperMassiveBlackHole":
			return new SuperMassiveBlackHole(str);
		case "WhiteDwarf":
			return new WhiteDwarf(str);
		default:
			return null;
		}
		
	}

	public void update(ValueInformation valInfo, String val) {
		if(valInfo.equals(this.name)) {
			this.name.value = val;
		}
	}
}
