package stars;

public abstract class Star {
	
	public double massInSolarMasses;
	public double radius;
	
	public double getCircumference() {
		return 2*Math.PI*this.radius;
	}
	
	public double getSurfaceArea() {
		return 4*Math.PI*this.radius*this.radius;
	}
	
	public double getVolume() {
		return 4/3*Math.PI*this.radius*this.radius*this.radius;
	}
	
	public double getDensity() {
		return this.massInSolarMasses/getVolume();
	}

	public String dataSheet() {
		String out = "Mass in Solar Masses: "+this.massInSolarMasses+"\n"+
				"Radius: "+this.radius+"\n"+
				"Circumference: "+getCircumference()+"\n"+
				"Surface Area: "+getSurfaceArea()+"\n"+
				"Volume: "+getVolume()+"\n"+
				"Density: "+getDensity();
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
}
