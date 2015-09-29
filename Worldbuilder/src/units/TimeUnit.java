package units;

public class TimeUnit extends Unit{
	public static final TimeUnit SECOND = new TimeUnit(1, "s");
	public static final TimeUnit MINUTE = new TimeUnit(60, "min");
	public static final TimeUnit HOUR = new TimeUnit(3_600, "h");
	public static final TimeUnit DAY = new TimeUnit(86_400, "d");
	public static final TimeUnit YEAR = new TimeUnit(31_540_000, "yr");
	public static final TimeUnit SOLAR_LIFETIME = new TimeUnit(31_540_000_000_000_000d, "Tms\u2299");
	
	protected TimeUnit(double base, String name) {
		super(base, name);
	}

	@Override
	public TimeUnit[] values() {
		TimeUnit[] values = {SECOND,MINUTE,HOUR,DAY,YEAR,SOLAR_LIFETIME};
		return values;
	}
	
	public static TimeUnit getBaseUnit() {
		return SECOND;
	}
}
