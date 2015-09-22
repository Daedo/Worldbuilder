package data;

import units.Unit;

public class LimitedDoubleUnitValue extends DoubleUnitValue {
	private DoubleUnitValue upperLimit,lowerLimit;
	
	public LimitedDoubleUnitValue(double baseUnits,String descrip, Unit valUnit, boolean editable,DoubleUnitValue lower,DoubleUnitValue upper,Runnable valueChangeCallback) {
		super(baseUnits, descrip, valUnit, editable,valueChangeCallback);
		this.upperLimit = upper;
		this.lowerLimit = lower;
		setBaseValue(getBaseValue());
	}
	
	
	public LimitedDoubleUnitValue(double baseUnits,String descrip, Unit valUnit, boolean editable,DoubleUnitValue lower,DoubleUnitValue upper) {
		super(baseUnits, descrip, valUnit, editable);
		this.upperLimit = upper;
		this.lowerLimit = lower;
		setBaseValue(getBaseValue());
	}
	
	public LimitedDoubleUnitValue(double baseUnits,String descrip, Unit valUnit, boolean editable) {
		super(baseUnits, descrip, valUnit, editable);	
	}
	
	public LimitedDoubleUnitValue(double baseUnits, String descrip, Unit valUnit) {
		super(baseUnits, descrip, valUnit);	
	}
	
	
	
	public LimitedDoubleUnitValue(double baseUnits) {
		super(baseUnits);	
	}

	@Override
	public void setBaseValue(double newValue) {
		if(this.upperLimit!=null && this.upperLimit.getBaseValue()>newValue) {
			super.setBaseValue(this.upperLimit.getBaseValue());
			return;
		}
		
		if(this.lowerLimit!=null && this.lowerLimit.getBaseValue()<newValue) {
			super.setBaseValue(this.lowerLimit.getBaseValue());
			return;
		}
		
		super.setBaseValue(newValue);
	}
}
