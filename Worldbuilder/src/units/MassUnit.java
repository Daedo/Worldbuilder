package units;

public class MassUnit extends Unit {
	public static final MassUnit GRAM = new MassUnit(1, "g");
	public static final MassUnit KILOGRAM = new MassUnit(1_000, "kg");
	public static final MassUnit METRIC_TON = new MassUnit(1_000_000, "t");
	public static final MassUnit EARTH_MASS = new MassUnit(5_972_198_600_000_000_000_000_000_000d, "M\u2295");
	public static final MassUnit SOLAR_MASS = new MassUnit(1_988_435_000_000_000_000_000_000_000_000_000d, "M\u2299");
	
	protected MassUnit(double base,String name) {
		super(base,name);
	}
	
	@Override
	public MassUnit[] values() {
		MassUnit[] values = {GRAM,KILOGRAM,METRIC_TON,EARTH_MASS,SOLAR_MASS};
		return values;
	}

	public static MassUnit getBaseUnit() {
		return GRAM;
	}
	
	public static MassUnit parseUnit(String unit) {
		switch (unit) {
		case "M\u2299":
			return SOLAR_MASS;
		case "M\u2295":
			return EARTH_MASS;
		case "t":
			return METRIC_TON;
		case "kg":
			return KILOGRAM;
		case "g":
		default:
			return GRAM;
		}
	}
}
