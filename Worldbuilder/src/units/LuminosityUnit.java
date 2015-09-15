package units;

public class LuminosityUnit extends Unit {
	public static final LuminosityUnit WATT = new LuminosityUnit(1, "W");
	public static final LuminosityUnit SOLAR_LUMINOSITY = new LuminosityUnit(383_000_000_000_000_000_000_000_000d, "L\u2299");
	
	protected LuminosityUnit(double base, String name) {
		super(base, name);
	}

	@Override
	public LuminosityUnit[] values() {
		LuminosityUnit[] values = {WATT,SOLAR_LUMINOSITY};
		return values;
	}
}
