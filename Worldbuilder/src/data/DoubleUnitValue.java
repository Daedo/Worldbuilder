package data;

import tools.HelperFunctions;
import units.Unit;

public class DoubleUnitValue extends DoubleValue {
	private Unit unit;

	public DoubleUnitValue(double val) {
		this(val,"",Unit.BASE);
	}
	
	public DoubleUnitValue(double val, String descrip, Unit valUnit) {
		super(val,descrip);
		this.unit = valUnit;
	}
	
	public DoubleUnitValue(double val, String descrip, Unit valUnit,boolean editable) {
		super(val,descrip,editable);
		this.unit = valUnit;
	}
	
	public DoubleUnitValue(double val, String descrip, Unit valUnit,boolean editable,Runnable valueChangeCallback) {
		super(val,descrip,editable,valueChangeCallback);
		this.unit = valUnit;
	}

	public double getUnitValue() {
		return getBaseValue()/this.unit.baseUnits;
	}
	
	public double getUnitValue(Unit valueUnit) {
		return getBaseValue()/valueUnit.baseUnits;
	}
	
	public void setUnitValue(double newUnitValue) {
		setBaseValue(newUnitValue*this.unit.baseUnits);
	}
	
	public Unit getUnit() {
		return this.unit;
	}
	
	public void setUnit(Unit newUnit) {
		this.unit = newUnit;
	}
	
	public static DoubleUnitValue createFromUnitValue(double unitValue,String descrip,Unit unit) {
		return new DoubleUnitValue(Unit.fromUnit(unitValue, unit),descrip,unit);
	}
	
	public static DoubleUnitValue createFromUnitValue(double unitValue,Unit unit) {
		return createFromUnitValue(unitValue,"",unit);
	}
	
	@Override
	public String toString() {
		return (this.description+": "+HelperFunctions.round(getUnitValue(), 4)+" "+this.unit).trim();
	}
	
	public String toStringWithUnit(Unit returnUnit) {
		return (this.description+": "+HelperFunctions.round(getUnitValue(returnUnit), 4)+" "+returnUnit).trim();
	}
}
