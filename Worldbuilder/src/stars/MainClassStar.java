package stars;

import java.awt.Color;
import java.util.Random;

import data.DoubleUnitValue;
import data.LimitedDoubleUnitValue;
import data.SolarMass;
import data.Value;
import tools.HelperFunctions;
import tools.MultipointInterpolator;
import units.LenghtUnit;
import units.LuminosityUnit;
import units.MassUnit;
import units.TemperatureUnit;
import units.TimeUnit;
import units.Unit;

public class MainClassStar  extends Star{
	public enum StarClass {
		O(16,50),B(2.1,16),A(1.4,2.1),F(1.04,1.4),G(0.8,1.04),K(0.45,0.8),M(0.08,0.45);

		public double lowerMassLimit,upperMassLimit;

		private StarClass(double lowerMass,double upperMass) {
			this.lowerMassLimit = lowerMass;
			this.upperMassLimit = upperMass;
		}
	}

	private static final double probClassO = 0.0000003;
	private static final double probClassB = 0.0013;
	private static final double probClassA = 0.006;
	private static final double probClassF = 0.03;
	private static final double probClassG = 0.076;
	private static final double probClassK = 0.121;

	private static final double EXPONENT_POINT_ONE_SOLAR_MASSES = 2.7;
	private static final double EXPONENT_ONE_SOLAR_MASS = 4.7;
	private static final double EXPONENT_TEN_SOLAR_MASSES = 3;
	private static final double EXPONENT_HUNDRED_SOLAR_MASSES = 1.6;

	private static final Color COLOR_THIRTY_THOUSAND_KELVIN = Color.decode("#9BB0FF");
	private static final Color COLOR_TEN_THOUSAND_KELVIN = Color.decode("#AABFFF");
	private static final Color COLOR_SEVEN_POINT_FIVE_THOUSAND_KELVIN = Color.decode("#CAD7FF");
	private static final Color COLOR_SIX_THOUSAND_KELVIN = Color.decode("#F8F7FF");
	private static final Color COLOR_FIVE_POINT_TWO_THOUSAND_KELVIN = Color.decode("#FFF4EA");
	private static final Color COLOR_THREE_POINT_SEVEN_THOUSAND_KELVIN = Color.decode("#FFD2A1");
	private static final Color COLOR_TWO_POINT_FOUR_THOUSAND_KELVIN = Color.decode("#FFCC6F");

	public static final DoubleUnitValue MIN_MASS_STAR = DoubleUnitValue.createFromUnitValue(0.08, MassUnit.SOLAR_MASS);
	public static final DoubleUnitValue MAX_MASS_STAR = DoubleUnitValue.createFromUnitValue(50, MassUnit.SOLAR_MASS);

	private StarClass sClass;
	private DoubleUnitValue luminosity,lifetime,temperature;
	private DoubleUnitValue habitableZoneInner,habitableZoneOuter;
	private Value<Color> sColor;

	public MainClassStar() {
		setClass();
		setMass();
		setRadius();
		setMainStarData();
		setColor();
		this.radius.setUnit(LenghtUnit.SOLAR_RADIUS);
		setSphereUnits();
	}

	public MainClassStar(StarClass starClass) {
		this.sClass=starClass;
		setMass();
		setRadius();
		setMainStarData();
		setColor();
		this.radius.setUnit(LenghtUnit.SOLAR_RADIUS);
		setSphereUnits();
	}

	public MainClassStar(String str) {
		String[] val = str.split(",");
		
		
		double baseMass   =  Double.parseDouble(val[1]);
		MassUnit massUnit = MassUnit.parseUnit(val[2]);
		this.mass= new LimitedDoubleUnitValue(baseMass,"Mass", massUnit,true,MIN_MASS_STAR, MAX_MASS_STAR, this::notifyMassChange);
		
		this.sClass = StarClass.valueOf(val[5]);
		
		setRadius();
		getRadius().setUnit(LenghtUnit.parseUnit(val[4]));
		//TODO Add Unit Parsing
		setMainStarData();
		setColor();
	}

	private void setClass() {
		Random rnd = new Random();
		double rVal = rnd.nextDouble();

		double prob = probClassO;
		if(rVal<=prob) {
			this.sClass= StarClass.O;
		} else {
			prob+=probClassB;
			if(rVal<=prob) {
				this.sClass= StarClass.B;
			} else {
				prob+=probClassA;
				if(rVal<=prob) {
					this.sClass= StarClass.A;
				} else {
					prob+=probClassF;
					if(rVal<=prob) {
						this.sClass= StarClass.F;
					} else {
						prob+=probClassG;
						if(rVal<=prob) {
							this.sClass= StarClass.G;
						} else {
							prob+=probClassK;
							if(rVal<=prob) {
								this.sClass= StarClass.K;
							} else {
								this.sClass = StarClass.M;
							}
						}
					}
				}
			}
		}
	}

	private void setMass() {
		double solarMass = HelperFunctions.getRandomRange(this.sClass.lowerMassLimit, this.sClass.upperMassLimit);
		double baseMass = Unit.fromUnit(solarMass, MassUnit.SOLAR_MASS);
		this.mass= new SolarMass(baseMass,MIN_MASS_STAR, MAX_MASS_STAR,this::notifyMassChange);
	}

	private void setRadius() {
		double r;
		if(getMass().getUnitValue(MassUnit.SOLAR_MASS)>1) {
			r = Math.pow(getMass().getUnitValue(MassUnit.SOLAR_MASS), 0.5);
		} else {
			r = Math.pow(getMass().getUnitValue(MassUnit.SOLAR_MASS), 0.8);
		}
		r = Unit.fromUnit(r, LenghtUnit.SOLAR_RADIUS);
		this.radius.setBaseValue(r);
	}

	private void setMainStarData() {

		double exponent;
		double massUnitValue = getMass().getUnitValue(MassUnit.SOLAR_MASS);
		
		if(massUnitValue<=0.1) {
			exponent = EXPONENT_POINT_ONE_SOLAR_MASSES;
		} else {
			if(massUnitValue<=1) {
				//0.1-1
				double interpolator = Math.log10(massUnitValue)-Math.log10(0.1);
				exponent = HelperFunctions.lerp(EXPONENT_POINT_ONE_SOLAR_MASSES,EXPONENT_ONE_SOLAR_MASS,interpolator);
			} else {
				if(massUnitValue<=10) {
					//1-10
					double interpolator = Math.log10(massUnitValue)-Math.log10(1);
					exponent = HelperFunctions.lerp(EXPONENT_ONE_SOLAR_MASS,EXPONENT_TEN_SOLAR_MASSES,interpolator);
				} else {
					if(massUnitValue<=100) {
						//10-100
						double interpolator = Math.log10(massUnitValue)-Math.log10(10);
						exponent = HelperFunctions.lerp(EXPONENT_TEN_SOLAR_MASSES,EXPONENT_HUNDRED_SOLAR_MASSES,interpolator);
					} else {
						exponent = EXPONENT_HUNDRED_SOLAR_MASSES;
					}
				}
			}
		}
		
		
		if(this.luminosity==null) {
			this.luminosity = new DoubleUnitValue(0, "Luminosity", LuminosityUnit.SOLAR_LUMINOSITY);
		}
		double lum = Unit.fromUnit(Math.pow(massUnitValue, exponent),LuminosityUnit.SOLAR_LUMINOSITY);
		this.luminosity.setBaseValue(lum);
		
		if(this.lifetime==null) {
			this.lifetime = new DoubleUnitValue(0,"Lifetime on the Main Sequence",TimeUnit.YEAR);
		}
		
		double life = Unit.fromUnit(massUnitValue/this.luminosity.getUnitValue(LuminosityUnit.SOLAR_LUMINOSITY),TimeUnit.SOLAR_LIFETIME);
		this.lifetime.setBaseValue(life);
		
		
		if(this.temperature==null) {
			this.temperature = new DoubleUnitValue(0, "Temperature", TemperatureUnit.SOLAR_TEMPERATURE);
		}
		double r = getRadius().getUnitValue(LenghtUnit.SOLAR_RADIUS);
		double temp =  Unit.fromUnit(Math.pow(this.luminosity.getUnitValue(LuminosityUnit.SOLAR_LUMINOSITY)/(r*r), 0.25),TemperatureUnit.SOLAR_TEMPERATURE);
		this.temperature.setBaseValue(temp);
		
		
		if(this.habitableZoneInner==null) {
			this.habitableZoneInner = new DoubleUnitValue(0, "Habitable Zone Inner", LenghtUnit.AU);
		}
		double hInner				= Unit.fromUnit(Math.sqrt(this.luminosity.getUnitValue(LuminosityUnit.SOLAR_LUMINOSITY)/1.1),LenghtUnit.AU);
		this.habitableZoneInner.setBaseValue(hInner);
		
		if(this.habitableZoneOuter==null) {
			this.habitableZoneOuter = new DoubleUnitValue(0, "Habitable Zone Outer", LenghtUnit.AU);
		}
		double hOuter				= Unit.fromUnit(Math.sqrt(this.luminosity.getUnitValue(LuminosityUnit.SOLAR_LUMINOSITY)/0.53),LenghtUnit.AU);
		this.habitableZoneOuter.setBaseValue(hOuter);
	}

	private void setColor() {
		MultipointInterpolator<Color> colorInterp = new MultipointInterpolator<>(HelperFunctions::rgbLerp);
		colorInterp.addDatapoint(30_000, COLOR_THIRTY_THOUSAND_KELVIN);
		colorInterp.addDatapoint(10_000, COLOR_TEN_THOUSAND_KELVIN);
		colorInterp.addDatapoint(7_500, COLOR_SEVEN_POINT_FIVE_THOUSAND_KELVIN);
		colorInterp.addDatapoint(6_000, COLOR_SIX_THOUSAND_KELVIN);
		colorInterp.addDatapoint(5_200, COLOR_FIVE_POINT_TWO_THOUSAND_KELVIN);
		colorInterp.addDatapoint(3_700, COLOR_THREE_POINT_SEVEN_THOUSAND_KELVIN);
		colorInterp.addDatapoint(2_400, COLOR_TWO_POINT_FOUR_THOUSAND_KELVIN);
		this.sColor = new Value<>(colorInterp.getInterpolation(this.temperature.getUnitValue(TemperatureUnit.KELVIN)));
	}

	@Override
	public String starType() {
		return "Main Class Star";
	}
	
	public StarClass getsClass() {
		return this.sClass;
	}

	public DoubleUnitValue getLuminosity() {
		return this.luminosity;
	}

	public DoubleUnitValue getLifetime() {
		return this.lifetime;
	}

	public DoubleUnitValue getTemperature() {
		return this.temperature;
	}

	public DoubleUnitValue getHabitableZoneInner() {
		return this.habitableZoneInner;
	}

	public DoubleUnitValue getHabitableZoneOuter() {
		return this.habitableZoneOuter;
	}

	public Value<Color> getsColor() {
		return this.sColor;
	}

	@Override
	public String toString() {
		String out = "Class "+this.sClass.name()+" Star";
		if(!getNameString().trim().equals("")) { //FIXME add trim/Equals to all toStrings
			out+=" - \""+getNameString()+"\"";
		}
		return out;
	}

	@Override
	public String dataSheet() {
		String out = super.dataSheet()+"\n"+
					this.luminosity+"\n"+
					this.lifetime+"\n"+
					this.temperature+"\n"+
					this.habitableZoneInner+"\n"+
					this.habitableZoneOuter;
		return out;
	}

	@Override
	public String toInfobox() {
		return null;
	}

	@Override
	public String encode() {
		//TODO add units to encoding
		return super.encode()+this.sClass.name()+","+encodeValue(this.luminosity)
				+","+encodeValue(this.lifetime)+","+encodeValue(this.temperature)+","+
				encodeValue(this.habitableZoneInner)+","+encodeValue(this.habitableZoneOuter)
				+","+this.sColor.getValue().getRGB();
	}

	private void findClass() {
		double massUnitValue = getMass().getUnitValue(MassUnit.SOLAR_MASS);
		for(StarClass cl:StarClass.values()) {
			if(cl.lowerMassLimit<=massUnitValue && cl.upperMassLimit>massUnitValue) {
				this.sClass = cl;
				break;
			}
		}
	}
	
	public void notifyMassChange() {
		findClass();
		setRadius();
		setMainStarData();
		setColor();
	}
}
