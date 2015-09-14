package gui;

import javax.swing.JPanel;

import data.ValueInformation;
import stars.MainClassStar;
import stars.Star;
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
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);


		//Star Infos
		JLabel lblStartype = new JLabel("Startype: "+star.toString());
		GridBagConstraints gbc_lblStartype = new GridBagConstraints();
		gbc_lblStartype.fill = GridBagConstraints.BOTH;
		gbc_lblStartype.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartype.gridx = 1;
		gbc_lblStartype.gridy = 1;
		add(lblStartype, gbc_lblStartype);
		
		JLabel lblName = new JLabel(star.name.toString());
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 1;
		add(lblName, gbc_lblName);

		JLabel lblMass = new JLabel(star.mass.toString());
		GridBagConstraints gbc_lblMass = new GridBagConstraints();
		gbc_lblMass.fill = GridBagConstraints.BOTH;
		gbc_lblMass.insets = new Insets(0, 0, 5, 5);
		gbc_lblMass.gridx = 1;
		gbc_lblMass.gridy = 2;
		add(lblMass, gbc_lblMass);
		
		JLabel lblRadius = new JLabel(star.radius.toString());
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.fill = GridBagConstraints.BOTH;
		gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
		gbc_lblRadius.gridx = 1;
		gbc_lblRadius.gridy = 3;
		add(lblRadius, gbc_lblRadius);

		JLabel lblCircumference = new JLabel(star.getCircumference().toString());
		GridBagConstraints gbc_lblCircumference = new GridBagConstraints();
		gbc_lblCircumference.fill = GridBagConstraints.BOTH;
		gbc_lblCircumference.insets = new Insets(0, 0, 5, 5);
		gbc_lblCircumference.gridx = 1;
		gbc_lblCircumference.gridy = 4;
		add(lblCircumference, gbc_lblCircumference);

		JLabel lblSurfaceArea = new JLabel(star.getSurfaceArea().toString());
		GridBagConstraints gbc_lblSurfaceArea = new GridBagConstraints();
		gbc_lblSurfaceArea.fill = GridBagConstraints.BOTH;
		gbc_lblSurfaceArea.insets = new Insets(0, 0, 5, 5);
		gbc_lblSurfaceArea.gridx = 1;
		gbc_lblSurfaceArea.gridy = 5;
		add(lblSurfaceArea, gbc_lblSurfaceArea);

		JLabel lblVolume = new JLabel(star.getVolume().toString());
		GridBagConstraints gbc_lblVolume = new GridBagConstraints();
		gbc_lblVolume.fill = GridBagConstraints.BOTH;
		gbc_lblVolume.insets = new Insets(0, 0, 5, 5);
		gbc_lblVolume.gridx = 1;
		gbc_lblVolume.gridy = 6;
		add(lblVolume, gbc_lblVolume);

		JLabel lblDensity = new JLabel(star.getDensity().toString());
		GridBagConstraints gbc_lblDensity = new GridBagConstraints();
		gbc_lblDensity.fill = GridBagConstraints.BOTH;
		gbc_lblDensity.insets = new Insets(0, 0, 5, 5);
		gbc_lblDensity.gridx = 1;
		gbc_lblDensity.gridy = 7;
		add(lblDensity, gbc_lblDensity);

		if(star instanceof MainClassStar) {
			MainClassStar mainClassStar = (MainClassStar) star;
			//Main Class Infos
			
			String color = String.format("#%06x", new Integer(mainClassStar.sColor.value.getRGB() & 0x00FFFFFF)).toUpperCase();
			JLabel lblColor = new JLabel("Color: "+color);
			lblColor.setBackground(mainClassStar.sColor.value);
			lblColor.setOpaque(true);
			GridBagConstraints gbc_lblColor = new GridBagConstraints();
			gbc_lblColor.fill = GridBagConstraints.BOTH;
			gbc_lblColor.insets = new Insets(0, 0, 5, 5);
			gbc_lblColor.gridx = 1;
			gbc_lblColor.gridy = 8;
			add(lblColor, gbc_lblColor);
			
			JLabel lblLuminosity = new JLabel(mainClassStar.luminosityInSuns.toString());
			GridBagConstraints gbc_lblLuminosity = new GridBagConstraints();
			gbc_lblLuminosity.fill = GridBagConstraints.BOTH;
			gbc_lblLuminosity.insets = new Insets(0, 0, 5, 5);
			gbc_lblLuminosity.gridx = 1;
			gbc_lblLuminosity.gridy = 9;
			add(lblLuminosity, gbc_lblLuminosity);

			JLabel lblLifetime = new JLabel(mainClassStar.lifetimeInYears.toString());
			GridBagConstraints gbc_lblLifetime = new GridBagConstraints();
			gbc_lblLifetime.fill = GridBagConstraints.BOTH;
			gbc_lblLifetime.insets = new Insets(0, 0, 5, 5);
			gbc_lblLifetime.gridx = 1;
			gbc_lblLifetime.gridy = 10;
			add(lblLifetime, gbc_lblLifetime);

			JLabel lblTemperature = new JLabel(mainClassStar.temperatureInKelvin.toString());
			GridBagConstraints gbc_lblTemperature = new GridBagConstraints();
			gbc_lblTemperature.fill = GridBagConstraints.BOTH;
			gbc_lblTemperature.insets = new Insets(0, 0, 5, 5);
			gbc_lblTemperature.gridx = 1;
			gbc_lblTemperature.gridy = 11;
			add(lblTemperature, gbc_lblTemperature);

			JLabel lblHabitableZoneOuter = new JLabel(mainClassStar.habitableZoneInnerInAU.toString());
			GridBagConstraints gbc_lblHabitableZoneOuter = new GridBagConstraints();
			gbc_lblHabitableZoneOuter.fill = GridBagConstraints.BOTH;
			gbc_lblHabitableZoneOuter.insets = new Insets(0, 0, 5, 5);
			gbc_lblHabitableZoneOuter.gridx = 1;
			gbc_lblHabitableZoneOuter.gridy = 12;
			add(lblHabitableZoneOuter, gbc_lblHabitableZoneOuter);

			JLabel lblHabitableZoneInner = new JLabel(mainClassStar.habitableZoneOuterInAU.toString());
			GridBagConstraints gbc_lblHabitableZoneInner = new GridBagConstraints();
			gbc_lblHabitableZoneInner.fill = GridBagConstraints.BOTH;
			gbc_lblHabitableZoneInner.insets = new Insets(0, 0, 5, 5);
			gbc_lblHabitableZoneInner.gridx = 1;
			gbc_lblHabitableZoneInner.gridy = 13;
			add(lblHabitableZoneInner, gbc_lblHabitableZoneInner);
		}

		validate();
		setVisible(true);
	}
	
	public void addNonEditableInfo(int gridX,int gridY, String data) {
		JLabel label = new JLabel(data);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = gridX;
		gbc_label.gridy = gridY;
		add(label, gbc_label);
	}

}
