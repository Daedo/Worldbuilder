package data;

import tools.HelperFunctions;

public class DoubleValue extends ValueInformation{
	public double value;
	
	public DoubleValue(double val, String descrip,boolean editable) {
		super(descrip,editable);
		this.value = val;
	}
	
	public DoubleValue(double val, String descrip) {
		super(descrip);
		this.value = val;
	}
	
	public DoubleValue(double val) {
		this(val,"");
	}
	
	@Override
	public String toString() {
		return super.toString()+HelperFunctions.round(this.value,4);
	}
}
