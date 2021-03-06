package units;

public class LenghtUnit extends Unit{
	public static final LenghtUnit METER = new LenghtUnit(1, "m");
	public static final LenghtUnit KILOMETER = new LenghtUnit(1_000, "km");
	public static final LenghtUnit AU = new LenghtUnit(149_597_870_700d, "AU");
	public static final LenghtUnit EARTH_RADIUS = new LenghtUnit(6_378_137, "R\u2295");
	public static final LenghtUnit SOLAR_RADIUS = new LenghtUnit(695_600_000, "R\u2299");
	
	protected LenghtUnit(double base,String name) {
		super(base,name);
	}
	
	public LenghtUnit[] values() {
		LenghtUnit[] units = {METER,KILOMETER,EARTH_RADIUS,SOLAR_RADIUS,AU};
		return units;
	}
	
	public static LenghtUnit getBaseUnit() {
		return METER;
	}
	
	public static LenghtUnit parseUnit(String unit) {
		switch (unit) {
		case "R\u2299":
			return SOLAR_RADIUS;
		case "R\u2295":
			return EARTH_RADIUS;
		case "AU":
			return AU;
		case "km":
			return KILOMETER;
		case "m":
		default:
			return METER;
		}
	}
}
