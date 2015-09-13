package stars;

public class GiantStar extends Star {
	public GiantStar() {
		this.massInSolarMasses = 100;
		this.radius = 100;
	}
	
	public GiantStar(String str) {
		String[] val = str.split(",");
		this.massInSolarMasses = Double.parseDouble(val[1]);
		this.radius = Double.parseDouble(val[2]);
	}

	@Override
	public String toString() {
		return "Giant Star";
	}

	@Override
	public String toInfobox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encode() {
		return "GiantStar,"+this.massInSolarMasses+","+this.radius;
	}
}
