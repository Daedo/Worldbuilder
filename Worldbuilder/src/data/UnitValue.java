package data;

import units.Unit;

public class UnitValue<T> extends Value<T> {
	public Unit unit;

	public UnitValue(T val) {
		this(val,"",Unit.BASE);
	}

	public UnitValue(T val, String descrip, Unit valUnit) {
		super(val,descrip);
		this.unit = valUnit;
	}
	
	public UnitValue(T val, String descrip, Unit valUnit,boolean editable) {
		super(val,descrip,editable);
		this.unit = valUnit;
	}

	@Override
	public String toString() {
		return (super.toString()+" "+this.unit).trim();
	}	
}
