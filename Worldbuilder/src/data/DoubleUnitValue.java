package data;

import units.Unit;

public class DoubleUnitValue extends DoubleValue {
	public Unit unit;

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

	@Override
	public String toString() {
		return (super.toString()+" "+this.unit).trim();
	}
	
	@Override
	public double getBaseValue() {
		return super.getBaseValue()*this.unit.baseUnits;
	}
}
