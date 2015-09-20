package gui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data.DoubleUnitValue;
import data.DoubleValue;
import data.Value;
import data.ValueInformation;
import stars.BlackHole;
import stars.MainClassStar;
import stars.Star;
import units.Unit;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class StarDisplayer extends JPanel {
	private static final long serialVersionUID = 1L;
	// TODO Add Unit changer calculation  

	private class ValueField {
		//TODO Move Unit Selection/ Creation here
		public ValueInformation data;
		public Star star;

		public JLabel descrip;
		public JTextField editField;
		public JComboBox<Unit> unitSelector;

		public ValueField(int row, int column, ValueInformation val, Star dataStar) {
			this.data = val;
			this.star = dataStar;

			setupField(row,column);
		}

		private void setupField(int row,int column) {
			if(!this.data.isEditable) {
				if( this.data instanceof DoubleUnitValue) {
					this.descrip = addNonEditableInfo(column,row,2,this.data.toString());
					DoubleUnitValue datDoubleValue = (DoubleUnitValue) this.data;
					this.unitSelector = addUnitSelector(column*3, row, datDoubleValue.unit);
				} else {
					this.descrip = addNonEditableInfo(column,row,3,this.data.toString());
				}
			} else {
				//Add editable
				this.descrip = addNonEditableInfo(column,row,1,this.data.description+": ");

				//Value
				if (this.data instanceof Value) {
					Value<?> datValue = (Value<?>) this.data;
					this.editField = addEditField(column, row, datValue.value.toString());
				}
				if (this.data instanceof DoubleValue) {
					DoubleValue datDoubleValue = (DoubleValue) this.data;
					this.editField = addEditField(column, row, datDoubleValue.value+"");
				}
				//Unit
				if (this.data instanceof DoubleUnitValue) {
					DoubleUnitValue datDoubleValue = (DoubleUnitValue) this.data;
					this.unitSelector = addUnitSelector(column*3, row, datDoubleValue.unit);
				}
			}
		}

		private JTextField addEditField(int column,int row,String textData) {
			JTextField txtField= new JTextField(10);
			txtField.setText(textData);
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

		private JComboBox<Unit> addUnitSelector(int x,int y,Unit currentUnit) {
			JComboBox<Unit> comboBox = new JComboBox<>(currentUnit.values());
			comboBox.setSelectedItem(currentUnit);
			comboBox.setEditable(false);
			comboBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println(e.getActionCommand()+" "+currentUnit+" - "+comboBox.getSelectedItem());
					ValueField.this.changeUnit((Unit)comboBox.getSelectedItem());
				}
			});

			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.fill = GridBagConstraints.BOTH;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.gridx = x;
			gbc_comboBox.gridy = y;
			add(comboBox, gbc_comboBox);
			return comboBox;
		}

		void changeUnit(Unit newUnit) {
			if(!(this.data instanceof DoubleUnitValue)){
				return;
			}
			DoubleUnitValue uVal = (DoubleUnitValue) this.data;
			//Get current Unit & current Value
			double currentVal = uVal.value;
			
			double newVal = currentVal;
			if(this.data.isEditable) {
				try {
					newVal = Double.parseDouble(this.editField.getText());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
			newVal = Unit.recalculate(newVal, uVal.unit, newUnit);
			
			//Set new Value
			
			if(this.data.isEditable) {
				this.editField.setText(newVal+"");
				this.editField.setCaretPosition(0);
			} else {
				DoubleUnitValue nVal = new DoubleUnitValue(newVal, uVal.description, newUnit);
				this.descrip.setText(nVal.toString());
			}
			
			System.out.println(newVal);
			noteChange();
		}

		public void applyEdit() {
			if (this.data instanceof DoubleUnitValue) {
				DoubleUnitValue unitValueData = (DoubleUnitValue) this.data;
				//Update Unit
				Unit nUnit = (Unit) this.unitSelector.getSelectedItem();
				
				unitValueData.value = Unit.recalculate(unitValueData.value, unitValueData.unit, nUnit);
				unitValueData.unit = nUnit;
			}
			
			if(this.data.isEditable) {
				String val = this.editField.getText();
				//Apply
				this.star.update(this.data,val);
			}
		}

		public void resetEdit() {
			//Reset Unit
			if (this.data instanceof DoubleUnitValue) {
				DoubleUnitValue unitData = (DoubleUnitValue) this.data;
				this.unitSelector.setSelectedItem(unitData.unit);
			}

			if(this.data.isEditable) {
				String t = "";

				if (this.data instanceof Value) {
					Value<?> datValue = (Value<?>) this.data;
					t = datValue.value.toString();
				}
				if (this.data instanceof DoubleValue) {
					DoubleValue datDoubleValue = (DoubleValue) this.data;
					t =  datDoubleValue.value+"";
				}
				this.editField.setText(t);
				this.editField.setCaretPosition(0);
			} else {
				this.descrip.setText(this.data.toString());
			}
		}
	}


	private JButton btnApply;
	private Vector<ValueField> valueFields;
	private Star userStar;
	private StarGui starGui;
	/**
	 * Create the panel.
	 */
	public StarDisplayer(Star star,StarGui gui) {
		this.userStar = star;
		this.starGui = gui;
		this.valueFields = new Vector<>();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		//Star Infos
		addStarInfos(star);

		//Buttons
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

	private void addStarInfos(Star star) {
		addNonEditableInfo(1, 1,3, "Startype: "+star.starType());

		addValueInfo(2, 1, star.name);
		addValueInfo(1, 2, star.mass);
		addValueInfo(1, 3, star.radius);

		if(!(star instanceof BlackHole)) {
			addValueInfo(1, 4,  star.getCircumference());
			addValueInfo(1, 5,  star.getSurfaceArea());
			addValueInfo(1, 6,  star.getVolume());
			addValueInfo(1, 7,  star.getDensity());
		} else {
			addValueInfo(1, 4,  star.getVolume());
			addValueInfo(1, 5,  star.getDensity());
		}


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
			gbc_lblColor.gridwidth = 2;
			add(lblColor, gbc_lblColor);

			addValueInfo(1, 9,  mainClassStar.luminosityInSuns);
			addValueInfo(1, 10, mainClassStar.lifetimeInYears);
			addValueInfo(1, 11, mainClassStar.temperatureInKelvin);
			addValueInfo(1, 12, mainClassStar.habitableZoneInnerInAU);
			addValueInfo(1, 13, mainClassStar.habitableZoneOuterInAU);
		}


	}

	protected void resetChanges() {
		
		for(ValueField field:this.valueFields) {
			field.resetEdit();
		}
		this.btnApply.setEnabled(false);
	}

	protected void applyChanges() {
		for(ValueField field:this.valueFields) {
			field.applyEdit();
		}
		resetChanges();

		//Resort
		//FIXME EDITABLE RESORT CAUSES NULLPOINTER EXCEPTION
		this.starGui.updateStarList();
	}

	private void addValueInfo(int column, int row,ValueInformation data) {
		this.valueFields.addElement(new ValueField(row, column, data, this.userStar));
	}

	JLabel addNonEditableInfo(int column,int row,int width, String data) {
		JLabel label = new JLabel(data);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = column*3-2;
		gbc_label.gridy = row;
		gbc_label.gridwidth = width;
		add(label, gbc_label);
		return label;
	}

	void noteChange() {
		this.btnApply.setEnabled(true);
	}
}
