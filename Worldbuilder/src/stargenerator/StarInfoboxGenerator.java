package stargenerator;

import java.awt.Color;

import org.apache.commons.lang.StringEscapeUtils;

import data.DoubleUnitValue;
import data.DoubleValue;
import data.Value;
import data.ValueInformation;
import stars.BlackHole;
import stars.MainClassStar;
import stars.Star;
import tools.HelperFunctions;

public class StarInfoboxGenerator {
	public static String generateWikiCode(Star star) {
		//TODO
		return "";
	}

	public static String generateHTMLCode(Star star) {
		String title = star.toString();

		String out = HTMLTagOpen("html")+"\n";
		out+=generateHTMLHeader(title)+"\n\n";
		out+=HTMLTagOpen("body")+"\n";
		out+=HTMLTagOpen("table")+"\n";
		
		if(star.getNameString()!=null && !star.getNameString().equals("")) {
			out+= HTMLTableHeadline(star.getNameString())+"\n";
		}
		out+= HTMLNameValuePair("Type",star.starType())+"\n";
		
		out+= HTMLTableHeadline("Phyical Data")+"\n";
		
		out+= HTMLNameValuePair(star.getMass())+"\n";
		out+= HTMLNameValuePair(star.getRadius())+"\n";
		out+= HTMLNameValuePair(star.getCircumference())+"\n";
		out+= HTMLNameValuePair(star.getSurfaceArea())+"\n";
		
		DoubleValue vol = star.getVolume();
		if (vol instanceof DoubleUnitValue) {
			DoubleUnitValue dVol = (DoubleUnitValue) vol;
			out+= HTMLNameValuePair(dVol)+"\n";
		} else {
			out+= HTMLNameValuePair(vol.description, vol.getBaseValue()+"")+"\n";
		}
		
		ValueInformation dens = star.getDensity();
		if (dens instanceof DoubleUnitValue) {
			DoubleUnitValue dDens = (DoubleUnitValue) dens;
			out+=HTMLNameValuePair(dDens)+"\n";
		} else if (dens instanceof Value<?>) {
			Value<?> vDens = (Value<?>) dens;
			String val = (String) vDens.getValue();
			out+=HTMLNameValuePair(vDens.description, val);
		}
		
		//Obj Special Data
		if (star instanceof BlackHole) {
			BlackHole blackHole = (BlackHole) star;
			
			out+= HTMLTableHeadline("Black Hole Data")+"\n";
			out+= HTMLNameValuePair(blackHole.getSchwarzschildRadius())+"\n";
			out+= HTMLNameValuePair(blackHole.getPhotosphere())+"\n";
			
		} else if (star instanceof MainClassStar) {
			MainClassStar mainClassStar = (MainClassStar) star;
			
			out+= HTMLTableHeadline("Main Class Star Data")+"\n";
			
			out+= HTMLNameValuePair(mainClassStar.getLuminosity())+"\n";
			out+= HTMLNameValuePair(mainClassStar.getLifetime())+"\n";
			out+= HTMLNameValuePair(mainClassStar.getTemperature())+"\n";
			
			Value<Color> col = mainClassStar.getsColor();
			String color = String.format("#%06x", new Integer(col.value.getRGB() & 0x00FFFFFF)).toUpperCase();
			out+= HTMLNameValuePair("Color", color)+"\n";
			
			out+= HTMLNameValuePair(mainClassStar.getHabitableZoneInner())+"\n";
			out+= HTMLNameValuePair(mainClassStar.getHabitableZoneOuter())+"\n";
		}
		out+=HTMLTagClose("table")+"\n";
		out+=HTMLTagClose("body")+"\n";
		out+=HTMLTagClose("html");
		return out;
	}

	private static String HTMLTableHeadline(String name) {
		String out = "";
		out+= HTMLRowOpen()+"\n";
		out+= HTMLTagOpen("th style=\"background-color:#F0E68C;\" colspan=\"2\"");
		out+= name;
		out+= HTMLTagClose("th")+"\n";
		out+= HTMLRowClose();
		return out;
	}
	
	private static String HTMLNameValuePair(DoubleUnitValue unitValue) {
		double roundedValue = HelperFunctions.round(unitValue.getUnitValue(), 4);
		return HTMLNameValuePair(unitValue.description, roundedValue+" "+unitValue.getUnit());
	}
	
	private static String HTMLNameValuePair(String name,String value) {
		String out = "";
		out+= HTMLRowOpen()+"\n";
		out+= HTMLTagOpen("td");
		out+= name;
		out+= HTMLTagClose("td")+"\n";
		out+= HTMLTagOpen("td nowrap=\"nowrap\"");
		out+= StringEscapeUtils.escapeHtml(value);
		out+= HTMLTagClose("td");
		out+= HTMLRowClose();
		return out;
	}
	
	private static String HTMLRowOpen() {
		return HTMLTagOpen("tr");
	}
	
	private static String HTMLRowClose() {
		return HTMLTagClose("tr");
	}

	private static String generateHTMLHeader(String title) {
		String out = HTMLTagOpen("head")+"\n";
		out+=HTMLTagOpen("title")+title+HTMLTagClose("title")+"\n";
		
		out+=HTMLTagOpen("style")+"\n";
		out+="body {\n"+
			"font-family: sans-serif;\n"+
			"}\n"+
			"table { \n"+
			"width:30%;\n"+
			"min-width:250px;\n"+
			"max-width:400px;\n"+
			"clear:right;\n"+
			"float: right;\n"+
			"margin: 1em 0px 1em 1em;\n"+
			"background-color: #F9F9F9;\n"+
			"border: 1px solid #AAA;\n"+
			"border-collapse: collapse;\n"+
			"color: #000;\n"+
			"line-height: 1.6;\n"+
			"}\n\n";
		out+="table td, table th {\n"+
			"border: 1px solid #AAA;\n"+
			"}\n";
		out+=HTMLTagClose("style")+"\n";
		out+= HTMLTagClose("head");
		return out;
	}

	private static String HTMLTagOpen(String contend) {
		return "<"+contend+">";
	}

	private static String HTMLTagClose(String contend) {
		return "</"+contend+">";
	}
}
