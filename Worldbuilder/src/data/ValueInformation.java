package data;

public class ValueInformation {
	public String description;
	public boolean isEditable;
	
	public ValueInformation(String descrip) {
		this.description = descrip;
		this.isEditable = false;
	}
	
	public ValueInformation(String descrip, boolean editable) {
		this.description = descrip;
		this.isEditable = editable;
	}
	
	@Override
	public String toString() {
		return this.description+": ";
	}
}
