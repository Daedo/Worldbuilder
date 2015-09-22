package data;

import units.LenghtUnit;

public class SolarRadius extends  LimitedDoubleUnitValue{
	public SolarRadius(double val,String descrip) {
		super(val,descrip,LenghtUnit.SOLAR_RADIUS);
	}
	
	public SolarRadius(double val,String descrip,DoubleUnitValue min,DoubleUnitValue max) {
		super(val,descrip,LenghtUnit.SOLAR_RADIUS,false,min,max);
	}
}
