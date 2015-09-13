package data;

public class UnitValue<T> extends Value<T> {
	public String unit;

	public UnitValue(T val) {
		this(val,"","");
	}

	public UnitValue(T val, String descrip, String valUnit) {
		super(val,descrip);
		this.unit = valUnit;
	}

	@Override
	public String toString() {
		return super.toString()+" "+this.unit;
	}	
}
