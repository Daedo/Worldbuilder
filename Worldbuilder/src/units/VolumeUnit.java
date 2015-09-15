package units;

public class VolumeUnit extends AreaUnit {
	public static final VolumeUnit METER_CUBED = new VolumeUnit(1, "m³");
	public static final VolumeUnit KILOMETER_CUBED = new VolumeUnit(KILOMETER_SQUARED.baseUnits*KILOMETER.baseUnits, "km³");
	public static final VolumeUnit AU_CUBED = new VolumeUnit(AU_SQUARED.baseUnits*AU.baseUnits, "AU³");
	public static final VolumeUnit EARTH_RADIUS_CUBED = new VolumeUnit(EARTH_RADIUS_SQUARED.baseUnits*EARTH_RADIUS.baseUnits, "R\u2295³");
	public static final VolumeUnit SOLAR_RADIUS_CUBED = new VolumeUnit(SOLAR_RADIUS_SQUARED.baseUnits*SOLAR_RADIUS.baseUnits, "R\u2299³");
	
	
	protected VolumeUnit(double base, String name) {
		super(base, name);
	}

	@Override
	public VolumeUnit[] values() {
		VolumeUnit[] values = {METER_CUBED,KILOMETER_CUBED,AU_CUBED,EARTH_RADIUS_CUBED,SOLAR_RADIUS_CUBED};
		return values;
	}
	
	public static VolumeUnit getUnitFromLenght(LenghtUnit unit) {
		if(unit.equals(METER)) {
			return METER_CUBED;
		} else if(unit.equals(KILOMETER)) {
			return KILOMETER_CUBED;
		} else if(unit.equals(AU)) {
			return AU_CUBED;
		} else if(unit.equals(EARTH_RADIUS)) {
			return EARTH_RADIUS_CUBED;
		} else if(unit.equals(SOLAR_RADIUS)) {
			return SOLAR_RADIUS_CUBED;
		} 
		return null;
	}
}
