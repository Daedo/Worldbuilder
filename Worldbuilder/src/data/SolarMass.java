package data;

import units.MassUnit;

public class SolarMass extends DoubleUnitValue {
	public SolarMass(double val) {
		super(val,"Mass",MassUnit.SOLAR_MASS,true);
	}
}
