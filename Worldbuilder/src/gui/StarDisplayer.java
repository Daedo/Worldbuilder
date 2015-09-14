package gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data.DoubleUnitValue;
import data.DoubleValue;
import data.UnitValue;
import data.Value;
import data.ValueInformation;
import stars.MainClassStar;
import stars.Star;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class StarDisplayer extends JPanel {

	private class textValuePair {
		public JTextField text;
		public ValueInformation data;
		
		public textValuePair(JTextField t,ValueInformation val) {
			this.text = t;
			this.data = val;
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btnApply;
	private Vector<textValuePair> editables;
	private Star userStar;
	private StarGui starGui;
	/**
	 * Create the panel.
	 */
	public StarDisplayer(Star star,StarGui gui) {
		this.userStar = star;
		this.editables = new Vector<>();
		this.starGui = gui;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		/*
		 * TODO
		 * Send change map to the Star Map contains: (ValueInformation Object, new Value)
		 */


		//Star Infos

		addNonEditableInfo(1, 1,3, "Startype: "+star.starType());

		addValueInfo(2, 1, star.name);
		addValueInfo(1, 2, star.mass);
		addValueInfo(1, 3, star.radius);
		addValueInfo(1, 4,  star.getCircumference());
		addValueInfo(1, 5,  star.getSurfaceArea());
		addValueInfo(1, 6,  star.getVolume());
		addValueInfo(1, 7,  star.getDensity());

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
			gbc_lblColor.gridwidth = 3;
			add(lblColor, gbc_lblColor);

			addValueInfo(1, 9,  mainClassStar.luminosityInSuns);
			addValueInfo(1, 10, mainClassStar.lifetimeInYears);
			addValueInfo(1, 11, mainClassStar.temperatureInKelvin);
			addValueInfo(1, 12, mainClassStar.habitableZoneInnerInAU);
			addValueInfo(1, 13, mainClassStar.habitableZoneOuterInAU);
		}
		
		this.btnApply = new JButton("Apply");
		this.btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				applyChanges();
			}
		});
		this.btnApply.setEnabled(false);
		GridBagConstraints gbc_btnApply = new GridBagConstraints();
		gbc_btnApply.insets = new Insets(0, 0, 5, 5);
		gbc_btnApply.gridx = 2;
		gbc_btnApply.gridy = 15;
		add(this.btnApply, gbc_btnApply);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetChanges();
			}
		});
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 5, 5);
		gbc_btnReset.gridx = 4;
		gbc_btnReset.gridy = 15;
		add(btnReset, gbc_btnReset);

		validate();
		setVisible(true);
	}

	protected void resetChanges() {
		for(textValuePair pair:this.editables) {
			//Reset
			String t = "";
			
			if (pair.data instanceof Value) {
				Value<?> datValue = (Value<?>) pair.data;
				t = datValue.value.toString();
			}
			if (pair.data instanceof DoubleValue) {
				DoubleValue datDoubleValue = (DoubleValue) pair.data;
				t =  datDoubleValue.value+"";
			}
			pair.text.setText(t);
			pair.text.setCaretPosition(0);
		}
		this.btnApply.setEnabled(false);
	}

	protected void applyChanges() {
		for(textValuePair pair:this.editables) {
			String val = pair.text.getText();
			ValueInformation valInfo = pair.data;
			//Apply
			this.userStar.update(valInfo,val);
		}
		
		resetChanges();
		
		//Resort
		this.starGui.updateStarList();
	}

	private void addNonEditableInfo(int column,int row,int width, String data) {
		JLabel label = new JLabel(data);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = column*3-2;
		gbc_label.gridy = row;
		gbc_label.gridwidth = width;
		add(label, gbc_label);
	}

	private void addNonEditableInfo(int x,int y, String data) {
		JLabel label = new JLabel(data);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = x;
		gbc_label.gridy = y;
		add(label, gbc_label);
	}

	private JTextField addEditField(int column,int row,String data) {
		
		JTextField txtField= new JTextField(10);
		txtField.setText(data);
		txtField.setCaretPosition(0);
		txtField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				noteChange();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				noteChange();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				noteChange();
			}
		});
		GridBagConstraints gbc_txtField = new GridBagConstraints();
		gbc_txtField.insets = new Insets(0, 0, 5, 5);
		gbc_txtField.gridx = column*3-1;
		gbc_txtField.gridy = row;
		add(txtField, gbc_txtField);
		return txtField;
	}

	private void addValueInfo(int column, int row,ValueInformation data) {
		if(!data.isEditable) {
			addNonEditableInfo(column,row,3,data.toString());
		} else {
			//Add editable
			addNonEditableInfo(column,row,1,data.description+": ");

			//Value
			if (data instanceof Value) {
				Value<?> datValue = (Value<?>) data;
				JTextField field = addEditField(column, row, datValue.value.toString());
				
				this.editables.addElement(new textValuePair(field, datValue));
			}
			if (data instanceof DoubleValue) {
				DoubleValue datDoubleValue = (DoubleValue) data;
				JTextField field = addEditField(column, row, datDoubleValue.value+"");
				
				this.editables.addElement(new textValuePair(field, datDoubleValue));
			}
			//Unit
			if (data instanceof UnitValue) {
				UnitValue<?> datValue = (UnitValue<?>) data;
				addNonEditableInfo(column*3, row, datValue.unit);
			}
			if (data instanceof DoubleUnitValue) {
				DoubleUnitValue datDoubleValue = (DoubleUnitValue) data;
				addNonEditableInfo(column*3, row, datDoubleValue.unit);
			}

		}
	}

	void noteChange() {
		this.btnApply.setEnabled(true);
	}
}
