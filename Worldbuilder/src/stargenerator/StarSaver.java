package stargenerator;

import javax.management.Descriptor;

import data.DoubleUnitValue;
import data.DoubleValue;
import data.ValueInformation;
import stars.BlackHole;
import stars.MainClassStar;
import stars.NeutronStar;
import stars.Star;
import units.AreaUnit;
import units.DensityUnit;
import units.LenghtUnit;
import units.LuminosityUnit;
import units.MassUnit;
import units.TemperatureUnit;
import units.TimeUnit;
import units.Unit;
import units.VolumeUnit;

public class StarSaver {
	public static String getSaveString(Star star) {
		String out = "";
		//Standard Star Information
		out+= star.starType()+",";
		out+= star.getNameString().trim().replaceAll(",", "")+",";
		out+= star.getMass().getBaseValue()+","+star.getMass().getUnit().getUnitId()+",";
		out+= star.getRadius().getBaseValue()+","+star.getRadius().getUnit().getUnitId()+",";
		
		out+= star.getCircumference().getUnit().getUnitId()+",";
		out+= star.getSurfaceArea().getUnit().getUnitId()+",";
		
		DoubleValue volume = star.getVolume();
		if(volume instanceof DoubleUnitValue) {
			DoubleUnitValue unitVol = (DoubleUnitValue) volume;
			out+= unitVol.getUnit().getUnitId()+",";
		} else {
			out+="-1,";
		}
		
		ValueInformation density = star.getDensity();
		if (density instanceof DoubleUnitValue) {
			DoubleUnitValue unitDensity = (DoubleUnitValue) density;
			out+= unitDensity.getUnit().getUnitId()+",";
		} else {
			out+="-1,";
		}
		
		// (Super Massive) Black Hole Data
		if (star instanceof BlackHole) {
			BlackHole blackHole = (BlackHole) star;
			out+=blackHole.getSchwarzschildRadius().getUnit().getUnitId()+",";
			out+=blackHole.getPhotosphere().getUnit().getUnitId();
		} else if (star instanceof MainClassStar) {
			MainClassStar mStar = (MainClassStar) star;
			
			out+= mStar.getLuminosity().getUnit().getUnitId()+",";
			out+= mStar.getLifetime().getUnit().getUnitId()+",";
			out+= mStar.getTemperature().getUnit().getUnitId()+",";
			out+= mStar.getHabitableZoneInner().getUnit().getUnitId()+",";
			out+= mStar.getHabitableZoneOuter().getUnit().getUnitId();
		} else {
			//GiantStar,NeutronStar,WhiteDwarf have no extra data
			//Remove last Comma
			out = out.substring(0, out.length()-1);
		}
		
		
		return out;
	}

	public static Star getStarFromString(String saveString) {
		String[] data = saveString.split(",");
		
		
		Star out = Stargenerator.generateFromStarClassName(data[0]);
		out.setName(data[1]);
		
		//Mass
		double mass = Double.parseDouble(data[2]);
		out.getMass().setBaseValue(mass);
		int massUnitID = Integer.parseInt(data[3]);
		Unit massUnit = MassUnit.getBaseUnit().getUnitFromID(massUnitID);
		out.getMass().setUnit(massUnit);
		
		//Radius
		if (out instanceof NeutronStar) {
			NeutronStar nOut = (NeutronStar) out;
			double radius = Double.parseDouble(data[4]);
			nOut.getRadius().setBaseValue(radius);
		}
		int radiusUnitID = Integer.parseInt(data[5]);
		Unit radiusUnit = LenghtUnit.getBaseUnit().getUnitFromID(radiusUnitID);
		out.getRadius().setUnit(radiusUnit);
		
		//Circumference
		int circumUnitID = Integer.parseInt(data[6]);
		Unit circumUnit  = LenghtUnit.getBaseUnit().getUnitFromID(circumUnitID);
		out.getCircumference().setUnit(circumUnit);
		
		//Surface Area
		int surfUnitID 	= Integer.parseInt(data[7]);
		Unit surfUnit	= AreaUnit.getBaseUnit().getUnitFromID(surfUnitID);
		out.getSurfaceArea().setUnit(surfUnit);
		
		//Volume
		int volUnitID	= Integer.parseInt(data[8]);
		Unit volUnit	= VolumeUnit.getBaseUnit().getUnitFromID(volUnitID);
		DoubleValue vol = out.getVolume();
		if (vol instanceof DoubleUnitValue) {
			DoubleUnitValue unitVol = (DoubleUnitValue) vol;
			unitVol.setUnit(volUnit);
		}
		
		//Density
		Unit denUnit	= parseUnitID(data[9], DensityUnit.getBaseUnit());
		ValueInformation dens = out.getDensity();
		if (dens instanceof DoubleUnitValue) {
			DoubleUnitValue unitDens = (DoubleUnitValue) dens;
			unitDens.setUnit(denUnit);
		}
		
		//(Super Massive) Black Hole
		if (out instanceof BlackHole) {
			BlackHole blackHole = (BlackHole) out;
			//Schwarzschild Radius
			int schwUnitID	= Integer.parseInt(data[10]);
			Unit schwUnit	= LenghtUnit.getBaseUnit().getUnitFromID(schwUnitID);
			blackHole.getSchwarzschildRadius().setUnit(schwUnit);
			
			//Photosphere
			int photUnitID	= Integer.parseInt(data[11]);
			Unit photUnit	= LenghtUnit.getBaseUnit().getUnitFromID(photUnitID);
			blackHole.getPhotosphere().setUnit(photUnit);
			
		} else if (out instanceof MainClassStar) {
			//Main Class Star
			MainClassStar mOut = (MainClassStar) out;
			
			//Luminosity
			Unit lumUnit	= parseUnitID(data[10], LuminosityUnit.getBaseUnit());
			mOut.getLuminosity().setUnit(lumUnit);
			
			//Lifetime
			Unit lifeUnit	= parseUnitID(data[11], TimeUnit.getBaseUnit());
			mOut.getLifetime().setUnit(lifeUnit);
			
			//Temperature
			Unit tempUnit	= parseUnitID(data[12], TemperatureUnit.getBaseUnit());
			mOut.getTemperature().setUnit(tempUnit);
			
			//Habitable Zone Inner
			Unit hInUnit	= parseUnitID(data[13], LenghtUnit.getBaseUnit());
			mOut.getHabitableZoneInner().setUnit(hInUnit);
			
			//Habitable Zone Outer
			Unit hOutUnit	= parseUnitID(data[14], LenghtUnit.getBaseUnit());
			mOut.getHabitableZoneOuter().setUnit(hOutUnit);
		}
		
		return out;
	}
	
	private static Unit parseUnitID(String unitID,Unit unitClass) {
		int id = Integer.parseInt(unitID);
		return unitClass.getUnitFromID(id);
	}
}
