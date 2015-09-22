package data;

import units.MassUnit;

public class SolarMass extends LimitedDoubleUnitValue {
	public SolarMass(double baseUnits) {
		super(baseUnits,"Mass",MassUnit.SOLAR_MASS,true);
	}
	
	public SolarMass(double baseUnits,DoubleUnitValue min,DoubleUnitValue max) {
		super(baseUnits,"Mass",MassUnit.SOLAR_MASS,true,min,max);
	}
	
	public SolarMass(double baseUnits,DoubleUnitValue min,DoubleUnitValue max,Runnable valueChangeCallback) {
		super(baseUnits,"Mass",MassUnit.SOLAR_MASS,true,min,max,valueChangeCallback);
	}
}
