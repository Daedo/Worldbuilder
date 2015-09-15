package units;

public class AreaUnit extends LenghtUnit {
	public static final AreaUnit METER_SQUARED = new AreaUnit(1, "m²");
	public static final AreaUnit KILOMETER_SQUARED = new AreaUnit(KILOMETER.baseUnits*KILOMETER.baseUnits, "km²");
	public static final AreaUnit AU_SQUARED = new AreaUnit(AU.baseUnits*AU.baseUnits, "AU²");
	public static final AreaUnit EARTH_RADIUS_SQUARED = new AreaUnit(EARTH_RADIUS.baseUnits*EARTH_RADIUS.baseUnits, "R\u2295²");
	public static final AreaUnit SOLAR_RADIUS_SQUARED = new AreaUnit(SOLAR_RADIUS.baseUnits*SOLAR_RADIUS.baseUnits, "R\u2299²");
	
	protected AreaUnit(double base, String name) {
		super(base, name);
	}
	
	@Override
	public AreaUnit[] values() {
		AreaUnit[] values = {METER_SQUARED,KILOMETER_SQUARED,AU_SQUARED,EARTH_RADIUS_SQUARED,SOLAR_RADIUS_SQUARED};
		return values;
	}

	public static AreaUnit getUnitFromLenght(LenghtUnit unit) {
		if(unit.equals(METER)) {
			return METER_SQUARED;
		} else if(unit.equals(KILOMETER)) {
			return KILOMETER_SQUARED;
		} else if(unit.equals(AU)) {
			return AU_SQUARED;
		} else if(unit.equals(EARTH_RADIUS)) {
			return EARTH_RADIUS_SQUARED;
		} else if(unit.equals(SOLAR_RADIUS)) {
			return SOLAR_RADIUS_SQUARED;
		} 
		return null;
	}

}
