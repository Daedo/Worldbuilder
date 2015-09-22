package data;

public class Value<T> extends ValueInformation{
	public T value;
	
	public Value(T val, String descrip,boolean editable) {
		super(descrip,editable);
		this.value = val;
	}
	
	public Value(T val, String descrip) {
		super(descrip);
		this.value = val;
	}
	
	public Value(T val) {
		this(val,"");
	}
	
	@Override
	public String toString() {
		return super.toString()+this.value;
	}

	public T getValue() {
		return this.value;
	}
	
	public void setValue(T newValue) {
		this.value = newValue;
	}
}
