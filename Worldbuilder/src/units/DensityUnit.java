package units;


public class DensityUnit extends MassUnit {
	public static final DensityUnit GRAM_PER_CUBIC_METER = new  DensityUnit(1, "g/m³");
	public static final DensityUnit KILOGRAM_PER_CUBIC_METER = new  DensityUnit(KILOGRAM.baseUnits, "kg/m³");
	public static final DensityUnit SOLAR_MASS_PER_CUBIC_SOLAR_RADIUS = new  DensityUnit(SOLAR_MASS.baseUnits/VolumeUnit.SOLAR_RADIUS_CUBED.baseUnits, "M\u2299/R\u2299³");
	public static final DensityUnit SOLAR_MASS_PER_CUBIC_EARTH_RADIUS = new DensityUnit(SOLAR_MASS.baseUnits/VolumeUnit.EARTH_RADIUS_CUBED.baseUnits,"M\u2299/R\u2295³");
	
	protected DensityUnit(double base, String name) {
		super(base, name);
	}

	@Override
	public DensityUnit[] values() {
		DensityUnit[] values = {GRAM_PER_CUBIC_METER,KILOGRAM_PER_CUBIC_METER,SOLAR_MASS_PER_CUBIC_SOLAR_RADIUS};
		return values;
	}

	public static DensityUnit getUnitFromMassAndVolume(MassUnit unit, VolumeUnit unit2) {
		if(unit.equals(GRAM) && unit2.equals(VolumeUnit.METER_CUBED)) {
			return GRAM_PER_CUBIC_METER;
		} else if(unit.equals(KILOGRAM) && unit2.equals(VolumeUnit.METER_CUBED)) {
			return KILOGRAM_PER_CUBIC_METER;
		} else if(unit.equals(SOLAR_MASS) && unit2.equals(VolumeUnit.SOLAR_RADIUS_CUBED)) {
			return SOLAR_MASS_PER_CUBIC_SOLAR_RADIUS;
		} else if(unit.equals(SOLAR_MASS) && unit2.equals(VolumeUnit.EARTH_RADIUS_CUBED)) {
			return SOLAR_MASS_PER_CUBIC_EARTH_RADIUS;
		}
		return null;
	}
}
