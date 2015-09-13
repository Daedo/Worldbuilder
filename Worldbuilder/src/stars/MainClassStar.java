package stars;

import java.awt.Color;
import java.util.Random;

import tools.HelperFunctions;
import tools.MultipointInterpolator;

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
	
	private static final double TEMPERATURE_OF_THE_SUN_IN_KELVIN = 5778;
	
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
	
	
	public StarClass sClass;
	public double luminosityInSuns,lifetimeInYears,temperatureInKelvin;
	public double habitableZoneInnerInAU,habitableZoneOuterInAU;
	public Color sColor;
	
	public MainClassStar() {
		setClass();
		setMass();
		setRadius();
		setMainStarData();
		setColor();
	}

	public MainClassStar(StarClass starClass) {
		this.sClass=starClass;
		setMass();
		setRadius();
		setMainStarData();
		setColor();
	}

	public MainClassStar(String str) {
		String[] val = str.split(",");
		this.massInSolarMasses = Double.parseDouble(val[1]);
		this.sClass = StarClass.valueOf(val[3]);
		setRadius();
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
		this.massInSolarMasses = HelperFunctions.getRandomRange(this.sClass.lowerMassLimit, this.sClass.upperMassLimit);
	}
	
	private void setRadius() {
		if(this.massInSolarMasses>1) {
			this.radius = Math.pow(this.massInSolarMasses, 0.5);
		} else {
			this.radius = Math.pow(this.massInSolarMasses, 0.8);
		}
	}
	
	private void setMainStarData() {
		
		double exponent;
		if(this.massInSolarMasses<=0.1) {
			exponent = EXPONENT_POINT_ONE_SOLAR_MASSES;
		} else {
			if(this.massInSolarMasses<=1) {
				//0.1-1
				double interpolator = Math.log10(this.massInSolarMasses)-Math.log10(0.1);
				exponent = HelperFunctions.lerp(EXPONENT_POINT_ONE_SOLAR_MASSES,EXPONENT_ONE_SOLAR_MASS,interpolator);
			} else {
				if(this.massInSolarMasses<=10) {
					//1-10
					double interpolator = Math.log10(this.massInSolarMasses)-Math.log10(1);
					exponent = HelperFunctions.lerp(EXPONENT_ONE_SOLAR_MASS,EXPONENT_TEN_SOLAR_MASSES,interpolator);
				} else {
					if(this.massInSolarMasses<=100) {
						//10-100
						double interpolator = Math.log10(this.massInSolarMasses)-Math.log10(10);
						exponent = HelperFunctions.lerp(EXPONENT_TEN_SOLAR_MASSES,EXPONENT_HUNDRED_SOLAR_MASSES,interpolator);
					} else {
						exponent = EXPONENT_HUNDRED_SOLAR_MASSES;
					}
				}
			}
		}
		
		this.luminosityInSuns = Math.pow(this.massInSolarMasses, exponent);
		
		this.lifetimeInYears = this.massInSolarMasses/this.luminosityInSuns;
		this.temperatureInKelvin = Math.pow(this.luminosityInSuns/(this.radius*this.radius), 0.25)*TEMPERATURE_OF_THE_SUN_IN_KELVIN;
		
		this.habitableZoneInnerInAU = Math.sqrt(this.luminosityInSuns/1.1);
		this.habitableZoneOuterInAU = Math.sqrt(this.luminosityInSuns/0.53);
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
		this.sColor = colorInterp.getLerp(this.temperatureInKelvin);
	}
	
	@Override
	public String toString() {
		String out = "Class "+this.sClass.name()+"Star";
		return out;
	}
	
	@Override
	public String dataSheet() {
		String out = toString()+"\n"+super.dataSheet()+"\n"+
				"Luminosity In Suns: "+this.luminosityInSuns+"\n"+
				"Lifetime In Jears: "+this.lifetimeInYears+"\n"+
				"Temperature In Kelvin: "+this.temperatureInKelvin+"\n"+
				"Habitable Zone Inner In AU: "+this.habitableZoneInnerInAU+"\n"+
				"Habitable Zone Outer In AU: "+this.habitableZoneOuterInAU;
		return out;
	}

	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encode() {
		return "MainClassStar,"+this.massInSolarMasses+","+this.radius+","+this.sClass.name()+","+this.luminosityInSuns+","+this.lifetimeInYears+","+this.temperatureInKelvin+
				","+this.habitableZoneInnerInAU+","+this.habitableZoneOuterInAU+","+this.sColor.getRGB();
	}
}
