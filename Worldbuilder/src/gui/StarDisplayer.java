package gui;

import javax.swing.JPanel;

import stars.MainClassStar;
import stars.Star;
import tools.HelperFunctions;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class StarDisplayer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public StarDisplayer(Star star) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);


		//Star Infos
		JLabel lblStartype = new JLabel("Startype: "+star.toString());
		GridBagConstraints gbc_lblStartype = new GridBagConstraints();
		gbc_lblStartype.fill = GridBagConstraints.BOTH;
		gbc_lblStartype.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartype.gridx = 1;
		gbc_lblStartype.gridy = 1;
		add(lblStartype, gbc_lblStartype);

		
		double mass = HelperFunctions.round(star.massInSolarMasses, 4);
		JLabel lblMass = new JLabel("Mass: "+mass);
		GridBagConstraints gbc_lblMass = new GridBagConstraints();
		gbc_lblMass.fill = GridBagConstraints.BOTH;
		gbc_lblMass.insets = new Insets(0, 0, 5, 5);
		gbc_lblMass.gridx = 1;
		gbc_lblMass.gridy = 2;
		add(lblMass, gbc_lblMass);
		
		double radius = HelperFunctions.round(star.radius, 4);
		JLabel lblRadius = new JLabel("Radius: "+radius);
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.fill = GridBagConstraints.BOTH;
		gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
		gbc_lblRadius.gridx = 1;
		gbc_lblRadius.gridy = 3;
		add(lblRadius, gbc_lblRadius);

		double circumference = HelperFunctions.round(star.getCircumference(), 4);
		JLabel lblCircumference = new JLabel("Circumference: "+circumference);
		GridBagConstraints gbc_lblCircumference = new GridBagConstraints();
		gbc_lblCircumference.fill = GridBagConstraints.BOTH;
		gbc_lblCircumference.insets = new Insets(0, 0, 5, 5);
		gbc_lblCircumference.gridx = 1;
		gbc_lblCircumference.gridy = 4;
		add(lblCircumference, gbc_lblCircumference);

		double surfaceArea = HelperFunctions.round(star.getSurfaceArea(), 4);
		JLabel lblSurfaceArea = new JLabel("Surface Area: "+surfaceArea);
		GridBagConstraints gbc_lblSurfaceArea = new GridBagConstraints();
		gbc_lblSurfaceArea.fill = GridBagConstraints.BOTH;
		gbc_lblSurfaceArea.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurfaceArea.gridx = 1;
		gbc_lblSurfaceArea.gridy = 5;
		add(lblSurfaceArea, gbc_lblSurfaceArea);

		double volume = HelperFunctions.round(star.getVolume(), 4);
		JLabel lblVolume = new JLabel("Volume: "+volume);
		GridBagConstraints gbc_lblVolume = new GridBagConstraints();
		gbc_lblVolume.fill = GridBagConstraints.BOTH;
		gbc_lblVolume.insets = new Insets(0, 0, 5, 5);
		gbc_lblVolume.gridx = 1;
		gbc_lblVolume.gridy = 6;
		add(lblVolume, gbc_lblVolume);

		double density = HelperFunctions.round(star.getDensity(), 4);
		JLabel lblDensity = new JLabel("Density: "+density);
		GridBagConstraints gbc_lblDensity = new GridBagConstraints();
		gbc_lblDensity.fill = GridBagConstraints.BOTH;
		gbc_lblDensity.insets = new Insets(0, 0, 5, 5);
		gbc_lblDensity.gridx = 1;
		gbc_lblDensity.gridy = 7;
		add(lblDensity, gbc_lblDensity);

		if(star instanceof MainClassStar) {
			MainClassStar mainClassStar = (MainClassStar) star;
			//Main Class Infos
			
			String color = String.format("#%06x", new Integer(mainClassStar.sColor.getRGB() & 0x00FFFFFF)).toUpperCase();
			JLabel lblColor = new JLabel("Color: "+color);
			lblColor.setBackground(mainClassStar.sColor);
			lblColor.setOpaque(true);
			GridBagConstraints gbc_lblColor = new GridBagConstraints();
			gbc_lblColor.fill = GridBagConstraints.BOTH;
			gbc_lblColor.insets = new Insets(0, 0, 5, 5);
			gbc_lblColor.gridx = 1;
			gbc_lblColor.gridy = 8;
			add(lblColor, gbc_lblColor);
			
			double luminosity = HelperFunctions.round(mainClassStar.luminosityInSuns, 4);
			JLabel lblLuminosity = new JLabel("Luminosity: "+luminosity+" Suns");
			GridBagConstraints gbc_lblLuminosity = new GridBagConstraints();
			gbc_lblLuminosity.fill = GridBagConstraints.BOTH;
			gbc_lblLuminosity.insets = new Insets(0, 0, 5, 5);
			gbc_lblLuminosity.gridx = 1;
			gbc_lblLuminosity.gridy = 9;
			add(lblLuminosity, gbc_lblLuminosity);

			double lifetime = HelperFunctions.round(mainClassStar.lifetimeInYears, 4);
			JLabel lblLifetime = new JLabel("Lifetime: "+lifetime+" Years");
			GridBagConstraints gbc_lblLifetime = new GridBagConstraints();
			gbc_lblLifetime.fill = GridBagConstraints.BOTH;
			gbc_lblLifetime.insets = new Insets(0, 0, 5, 5);
			gbc_lblLifetime.gridx = 1;
			gbc_lblLifetime.gridy = 10;
			add(lblLifetime, gbc_lblLifetime);

			double temperature = HelperFunctions.round(mainClassStar.temperatureInKelvin, 4);
			JLabel lblTemperature = new JLabel("Temperature: "+temperature+" Kelvin");
			GridBagConstraints gbc_lblTemperature = new GridBagConstraints();
			gbc_lblTemperature.fill = GridBagConstraints.BOTH;
			gbc_lblTemperature.insets = new Insets(0, 0, 5, 5);
			gbc_lblTemperature.gridx = 1;
			gbc_lblTemperature.gridy = 11;
			add(lblTemperature, gbc_lblTemperature);

			double habitableZoneInner = HelperFunctions.round(mainClassStar.habitableZoneInnerInAU, 4);
			double habitableZoneOuter = HelperFunctions.round(mainClassStar.habitableZoneOuterInAU, 4);
			JLabel lblHabitableZone = new JLabel("Habitable Zone: "+habitableZoneInner+" AU - "+habitableZoneOuter+" AU");
			GridBagConstraints gbc_lblHabitableZone = new GridBagConstraints();
			gbc_lblHabitableZone.fill = GridBagConstraints.BOTH;
			gbc_lblHabitableZone.insets = new Insets(0, 0, 0, 5);
			gbc_lblHabitableZone.gridx = 1;
			gbc_lblHabitableZone.gridy = 12;
			add(lblHabitableZone, gbc_lblHabitableZone);

		}

		validate();
		setVisible(true);
	}

}
