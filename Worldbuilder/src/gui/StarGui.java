package gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.OptionalDouble;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import stargenerator.Stargenerator;
import stars.MainClassStar.StarClass;
import stars.Star;

public class StarGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	public Vector<Star> stars;
	JList<Star> list;
	JPanel panel;
	private GridBagConstraints gbc_panel;
	SortType sortType = SortType.TYPE_FIRST;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StarGui frame = new StarGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StarGui() {
		this.stars = new Vector<>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSaveStars = new JMenuItem("Save Stars");
		mntmSaveStars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveStars();
			}
		});
		mnFile.add(mntmSaveStars);

		JMenuItem mntmLoadStars = new JMenuItem("Load Stars");
		mntmLoadStars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadStars();
			}
		});
		mnFile.add(mntmLoadStars);

		JMenuItem mntmExportDataSheets = new JMenuItem("Export Data Sheets");
		mntmExportDataSheets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportDataSheets();
			}
		});
		mnFile.add(mntmExportDataSheets);

		JMenu mnAdd = new JMenu("Add");
		menuBar.add(mnAdd);

		JMenuItem mntmAddNumberOf = new JMenuItem("Add Number of Stars");
		mntmAddNumberOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNumber();
			}
		});
		mnAdd.add(mntmAddNumberOf);

		JMenuItem mntmAddStarsFrom = new JMenuItem("Add Stars from Volume");
		mntmAddStarsFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addVolume();
			}
		});
		mnAdd.add(mntmAddStarsFrom);

		JMenu mnObject = new JMenu("Object");
		mnAdd.add(mnObject);

		JMenuItem mntmOClassStar = new JMenuItem("O Class Star");
		mntmOClassStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StarGui.this.stars.addElement(Stargenerator.generateMainClassStar(StarClass.O));
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmOClassStar);

		JMenuItem mntmBClassStar = new JMenuItem("B Class Star");
		mntmBClassStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.stars.addElement(Stargenerator.generateMainClassStar(StarClass.B));
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmBClassStar);

		JMenuItem mntmAClassStar = new JMenuItem("A Class Star");
		mntmAClassStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.stars.addElement(Stargenerator.generateMainClassStar(StarClass.A));
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmAClassStar);

		JMenuItem mntmFClassStar = new JMenuItem("F Class Star");
		mntmFClassStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.stars.addElement(Stargenerator.generateMainClassStar(StarClass.F));
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmFClassStar);

		JMenuItem mntmGClassStar = new JMenuItem("G Class Star");
		mntmGClassStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.stars.addElement(Stargenerator.generateMainClassStar(StarClass.G));
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmGClassStar);

		JMenuItem mntmKClassStar = new JMenuItem("K Class Star");
		mntmKClassStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.stars.addElement(Stargenerator.generateMainClassStar(StarClass.K));
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmKClassStar);

		JMenuItem mntmMClassStar = new JMenuItem("M Class Star");
		mntmMClassStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.stars.addElement(Stargenerator.generateMainClassStar(StarClass.M));
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmMClassStar);

		JMenuItem mntmGiantStar = new JMenuItem("Giant Star");
		mntmGiantStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.stars.addElement(Stargenerator.generateGiantStar());
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmGiantStar);

		JMenuItem mntmWhiteDwraf = new JMenuItem("White Dwraf");
		mntmWhiteDwraf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.stars.addElement(Stargenerator.generateWhiteDwraf());
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmWhiteDwraf);

		JMenuItem mntmNeutronStar = new JMenuItem("Neutron Star");
		mntmNeutronStar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.stars.addElement(Stargenerator.generateNeutronStar());
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmNeutronStar);

		JMenuItem mntmBlackHole = new JMenuItem("Black Hole");
		mntmBlackHole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.stars.addElement(Stargenerator.generateBlackHole());
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmBlackHole);

		JMenuItem mntmSuperMassiveBlack = new JMenuItem("Super Massive Black Hole");
		mntmSuperMassiveBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StarGui.this.stars.addElement(Stargenerator.generateSuperMassiveBlackHole());
				StarGui.this.updateStarList();
			}
		});
		mnObject.add(mntmSuperMassiveBlack);

		JMenu mnSort = new JMenu("Sort");
		menuBar.add(mnSort);

		JMenuItem mntmTypeFirst = new JMenuItem("Type First");
		mntmTypeFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.sortType = SortType.TYPE_FIRST;
				StarGui.this.updateStarList();
			}
		});
		mnSort.add(mntmTypeFirst);

		JMenuItem mntmNameFirst = new JMenuItem("Name First");
		mntmNameFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StarGui.this.sortType = SortType.NAME_FIRST;
				StarGui.this.updateStarList();
			}
		});
		mnSort.add(mntmNameFirst);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.contentPane.setLayout(gbl_contentPane);

		this.panel = new JPanel();
		this.gbc_panel = new GridBagConstraints();
		this.gbc_panel.insets = new Insets(0, 0, 0, 5);
		this.gbc_panel.fill = GridBagConstraints.BOTH;
		this.gbc_panel.gridx = 0;
		this.gbc_panel.gridy = 0;
		this.contentPane.add(this.panel, this.gbc_panel);


		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.anchor = GridBagConstraints.EAST;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		this.contentPane.add(scrollPane, gbc_scrollPane);

		this.list = new JList<>();
		this.list.setModel(new DefaultListModel<>());
		this.list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				StarGui.this.updatePanel();
			}
		});

		this.list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_DELETE && !StarGui.this.list.isSelectionEmpty()) {
					DefaultListModel<Star> model = (DefaultListModel<Star>) StarGui.this.list.getModel();
					int selectedIndex = StarGui.this.list.getSelectedIndex();
					model.remove(selectedIndex);
					StarGui.this.updatePanel();
				}
			}
		});
		scrollPane.setViewportView(this.list);
	}

	protected void exportDataSheets() {
		String fName = getSaveFolder();
		if(fName==null || fName=="") {
			return;
		}

		try {
			for(int i=0;i<this.stars.size();i++) {
				Star s = this.stars.elementAt(i);
				//Build Name
				String name = (i+1)+" "+s.toString()+".txt";
				name = fName+"\\"+name;

				BufferedWriter output = null;

				File file = new File(name);
				output = new BufferedWriter(new FileWriter(file));
				
				String dataSheet = s.dataSheet().replaceAll("\\n", "%n");
				output.write(String.format(dataSheet));
				
				output.close();
			}
			JOptionPane.showMessageDialog(this, this.stars.size()+" were exported to the directory \""+fName+"\".", "Exporting", JOptionPane.INFORMATION_MESSAGE);
			
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	private  String getSaveFolder() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a directory to export to");    
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int userSelection = fileChooser.showOpenDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			return fileToSave.getAbsolutePath();
		}
		return null;
	}

	protected void loadStars() {
		String fName = getLoadName();
		if(fName==null || fName=="") {
			return;
		}

		try {
			Path p = new File(fName).toPath();
			//For each Line Decode and add
			Files.lines(p,StandardCharsets.UTF_8).map(Star::decode).forEach((s)->{if(s!=null)this.stars.add(s);});
			Files.lines(p,StandardCharsets.UTF_8).forEach(System.out::println);
			updateStarList();
			JOptionPane.showMessageDialog(this, "The File \""+fName+"\" was sucessfully loaded.", "Loading", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getLoadName() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to load");    

		int userSelection = fileChooser.showOpenDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			return fileToSave.getAbsolutePath();
		}
		return null;
	}

	protected void saveStars() {
		String fName = getSaveName();
		if(fName==null || fName=="") {
			return;
		}

		BufferedWriter output = null;
		try {
			File file = new File(fName);
			output = new BufferedWriter(new FileWriter(file));

			for(Star s:this.stars) {
				output.write(s.encode()+"\n");
			}
			output.close();

			JOptionPane.showMessageDialog(this, "The File \""+fName+"\" was sucessfully saved.", "Saving", JOptionPane.INFORMATION_MESSAGE);
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}

	private String getSaveName() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");    

		int userSelection = fileChooser.showSaveDialog(this);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			return fileToSave.getAbsolutePath();
		}
		return null;
	}

	protected void updatePanel() {
		this.contentPane.remove(this.panel);

		if(this.list.getSelectedValue()==null) {
			this.panel = new JPanel();
		} else {
			this.panel = new StarDisplayer(this.list.getSelectedValue(),this);
		}

		this.contentPane.add(this.panel,this.gbc_panel);
		this.contentPane.invalidate();
		this.contentPane.validate();
		this.contentPane.repaint();
	}

	protected void addVolume() {
		OptionalDouble result = askForDouble("Add Volume", "Insert the volume you want to add (AU³)", "25000");
		if(result.isPresent()) {
			double dResult = result.getAsDouble();
			if(dResult>0) {
				this.stars.addAll(Stargenerator.generateFromSize(dResult));
				updateStarList();
			}
		}
	}

	protected void addNumber() {
		OptionalDouble result = askForDouble("Add Stars", "Insert the number of Stars you want to add", "1000");
		if(result.isPresent()) {
			int iResult = (int)result.getAsDouble();
			if(iResult>0) {
				this.stars.addAll(Stargenerator.generateFromNumber(iResult));
				updateStarList();
			}
		}

	}

	void updateStarList() {
		Star select = this.list.getSelectedValue();
		Collections.sort(this.stars,this.sortType.starSorter);
		DefaultListModel<Star> model = new DefaultListModel<>();
		for(Star s: this.stars) {
			model.addElement(s);
		}
		this.list.setModel(model);
		this.contentPane.invalidate();
		this.contentPane.validate();
		this.contentPane.repaint();

		if(select!=null) {
			this.list.setSelectedValue(select, true);
		}
	}

	private OptionalDouble askForDouble(String title,String message,String stdValue) {
		String s = (String)JOptionPane.showInputDialog(
				this,
				message,
				title,
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				stdValue);
		if(s!=null && s.length()>0) {
			try {
				double d = Double.parseDouble(s);
				return OptionalDouble.of(d);
			} catch (NumberFormatException e) {
				return OptionalDouble.empty();
			}
		}

		return OptionalDouble.empty();
	}

	public JList<Star> getList() {
		return this.list;
	}

	public JPanel getPanel() {
		return this.panel;
	}
}
