package units;

public class Unit {
	public final static Unit BASE = new Unit(1,"");
	
	public double baseUnits;
	public String unitName;
	
	protected Unit(double base,String name) {
		this.baseUnits = base;
		this.unitName = name;
	}
	
	public static double recalculate(double value,Unit oldUnit,Unit newUnit) {
		return value*oldUnit.baseUnits/newUnit.baseUnits;
	}
	
	public static double toUnit(double value,Unit newUnit) {
		return value/newUnit.baseUnits;
	}
	
	public static double fromUnit(double value,Unit oldUnit) {
		return value*oldUnit.baseUnits;
	}
	
	public Unit[] values() {
		Unit[] values =  {BASE};
		return values;
	}
	
	public static Unit getBaseUnit() {
		return BASE;
	}
	
	public static Unit parseUnit(String unit) {
		return BASE;
	}
	
	@Override
	public String toString() {
		return this.unitName;
	}
}
