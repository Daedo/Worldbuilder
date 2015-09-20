package units;

public class TemperatureUnit extends Unit {
	public static final TemperatureUnit KELVIN = new TemperatureUnit(1, "K");
	public static final TemperatureUnit SOLAR_TEMPERATURE = new TemperatureUnit(5_778, "T\u2299");
	//TODO Celsius
	
	private TemperatureUnit(double base, String name) {
		super(base, name);
	}

	@Override
	public TemperatureUnit[] values() {
		TemperatureUnit[] values = {KELVIN,SOLAR_TEMPERATURE};
		return values;
	}
	
	public static TemperatureUnit getBaseUnit() {
		return KELVIN;
	}
}
