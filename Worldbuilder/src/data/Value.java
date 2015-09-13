package data;

public class Value<T> {
	public T value;
	public String description;
	
	public Value(T val, String descrip) {
		this.value = val;
		this.description = descrip;
	}
	
	public Value(T val) {
		this(val,"");
	}
	
	@Override
	public String toString() {
		return this.description+": "+this.value;
	}
}
