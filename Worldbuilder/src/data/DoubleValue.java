package data;

import tools.HelperFunctions;

public class DoubleValue extends ValueInformation{
	private double value;
	private Runnable changeCallback;
	
	public DoubleValue(double val, String descrip,boolean editable,Runnable valueChangeCallback) {
		super(descrip,editable);
		this.value = val;
		this.changeCallback = valueChangeCallback;
	}
	
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
	
	public double getBaseValue() {
		return this.value;
	}
	
	public void setBaseValue(double val) {
		this.value = val;
		if(this.changeCallback!=null) {
			this.changeCallback.run();
		}
	}
}
