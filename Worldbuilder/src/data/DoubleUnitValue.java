package data;

public class DoubleUnitValue extends DoubleValue {
	public String unit;

	public DoubleUnitValue(double val) {
		this(val,"","");
	}

	public DoubleUnitValue(double val, String descrip, String valUnit) {
		super(val,descrip);
		this.unit = valUnit;
	}
	
	public DoubleUnitValue(double val, String descrip, String valUnit,boolean editable) {
		super(val,descrip,editable);
		this.unit = valUnit;
	}

	@Override
	public String toString() {
		return super.toString()+" "+this.unit;
	}
}
