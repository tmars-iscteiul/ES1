package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;

import antiSpamFilter.AntiSpamFilterStyles.*;

/**
 * <p>AntiSpamFilterGUI - the main GUI panel class</br>
 * </br>
 * Creates the Graphics User Interface for the AntiSpamFilter main window. 
 * The default window has a 500x600 size and a modern design. </p>
 *
 * @author ES1-2017-LIGE-PL-102
 */

public class AntiSpamFilterGUI extends JComponent {
	
	
	private static final long serialVersionUID = 1L;
	private JFrame antiSpamFilterFrame = new JFrame("AntiSpamFilter Optimizer v1.0");
	
	private final int WINDOW_HSIZE = 500;
	private final int WINDOW_VSIZE = 600;
	private final int COMPONENT_GAP = 20;
	private final int COMPONENT_MAX_WIDTH = WINDOW_HSIZE-(2*COMPONENT_GAP);
	
	private String SPAM_FILENAME;
	private String SPAM_DIRECTORY;
	private File SPAM_FILE;
	
	private String HAM_FILENAME;
	private String HAM_DIRECTORY;
	private File HAM_FILE;
	
	private String RULES_FILENAME;
	private String RULES_DIRECTORY;
	private File RULES_FILE;
	
	
	//Panels initiation
	APanel loadingPanel, initiationPanel, resultsPanel, conclusionPanel,
		resultsAndConclusionPanel;
	
	AntiSpamFilterConfigurationGUI configureGUI;

	public AntiSpamFilterGUI() {
		//Window dimension
		antiSpamFilterFrame.setSize(WINDOW_HSIZE, WINDOW_VSIZE);
		antiSpamFilterFrame.setLocationRelativeTo(null);
		
		configureGUI = new AntiSpamFilterConfigurationGUI(this,false);

		loadingPanel = new AntiSpamFilterStyles().new APanel();
		loadingPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,250));
		loadingPanel.setLayout(new GridLayout(4,1));
		
		
		//Sprint Item
		//Implementation of the loading panel
		//Labels
		ALabel windowLabel = new AntiSpamFilterStyles().new ALabel("File Loading Window");
		windowLabel.setHorizontalAlignment(ALabel.CENTER);
		ALabel spamLabel = new AntiSpamFilterStyles().new ALabel("SPAM Log");
		spamLabel.setHorizontalAlignment(ALabel.CENTER);
		spamLabel.setPreferredSize(new Dimension(100, 30));
		ALabel hamLabel = new AntiSpamFilterStyles().new ALabel("HAM Log");
		hamLabel.setHorizontalAlignment(ALabel.CENTER);
		hamLabel.setPreferredSize(new Dimension(100, 30));
		ALabel rulesLabel = new AntiSpamFilterStyles().new ALabel("RULES File");
		rulesLabel.setHorizontalAlignment(ALabel.CENTER);
		rulesLabel.setPreferredSize(new Dimension(100, 30));
		
		//JTextAreas
		ATextField spamArea = new AntiSpamFilterStyles().new ATextField("");
		spamArea.setPreferredSize(new Dimension(300,30));
		spamArea.setEditable(false);
		
		ATextField hamArea = new AntiSpamFilterStyles().new ATextField("");
		hamArea.setEditable(false);
		
		ATextField rulesArea = new AntiSpamFilterStyles().new ATextField("");
		rulesArea.setEditable(false);
		
		
		//Buttons
		AButton spamButton = new AntiSpamFilterStyles().new AButton("Select path", AntiSpamFilterStyles.BTN_DEFAULT);
		spamButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileDialog spamFile = new FileDialog(antiSpamFilterFrame, "SPAM log");
				spamFile.setMode(FileDialog.LOAD);
				spamFile.setFile("*.log");
				spamFile.setVisible(true);
				SPAM_DIRECTORY = spamFile.getDirectory();
				SPAM_FILENAME = spamFile.getFile();
				SPAM_FILE = new File(SPAM_FILENAME);
				
				spamArea.setText(SPAM_FILENAME);
			}
		});
		
		AButton hamButton = new AntiSpamFilterStyles().new AButton("Select path", AntiSpamFilterStyles.BTN_DEFAULT);
		hamButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileDialog hamFile = new FileDialog(antiSpamFilterFrame, "HAM log");
				hamFile.setMode(FileDialog.LOAD);
				hamFile.setFile("*.log");
				hamFile.setVisible(true);
				HAM_DIRECTORY = hamFile.getDirectory();
				HAM_FILENAME = hamFile.getFile();
				HAM_FILE = new File(HAM_FILENAME);
				
				hamArea.setText(HAM_FILENAME);
			}
		});
		
		AButton rulesButton = new AntiSpamFilterStyles().new AButton("Select path", AntiSpamFilterStyles.BTN_DEFAULT);
		rulesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileDialog rulesFile = new FileDialog(antiSpamFilterFrame, "Rules file");
				rulesFile.setMode(FileDialog.LOAD);
				rulesFile.setFile("*.cf");
				rulesFile.setVisible(true);
				RULES_DIRECTORY = rulesFile.getDirectory();
				RULES_FILENAME = rulesFile.getFile();
				RULES_FILE = new File(RULES_FILENAME);
				
				rulesArea.setText(RULES_FILENAME);
			}
		});
		
		
		//Panels
		APanel spamPanel = new AntiSpamFilterStyles().new APanel();
		APanel spamPanelIn = new AntiSpamFilterStyles().new APanel();
		spamPanelIn.setLayout(new BorderLayout());
		spamPanelIn.add(spamArea, BorderLayout.CENTER);
		spamPanelIn.add(spamButton, BorderLayout.LINE_END);
		spamPanel.setLayout(new BorderLayout(10,0));
		spamPanel.add(spamLabel, BorderLayout.LINE_START);
		spamPanel.add(spamPanelIn, BorderLayout.CENTER);
		
		APanel hamPanel = new AntiSpamFilterStyles().new APanel();
		APanel hamPanelIn = new AntiSpamFilterStyles().new APanel();
		hamPanelIn.setLayout(new BorderLayout());
		hamPanelIn.add(hamArea, BorderLayout.CENTER);
		hamPanelIn.add(hamButton, BorderLayout.LINE_END);
		hamPanel.setLayout(new BorderLayout(10,0));
		hamPanel.add(hamLabel, BorderLayout.LINE_START);
		hamPanel.add(hamPanelIn, BorderLayout.CENTER);
		
		APanel rulesPanel = new AntiSpamFilterStyles().new APanel();
		APanel rulesPanelIn = new AntiSpamFilterStyles().new APanel();
		rulesPanelIn.setLayout(new BorderLayout());
		rulesPanelIn.add(rulesArea, BorderLayout.CENTER);
		rulesPanelIn.add(rulesButton, BorderLayout.LINE_END);
		rulesPanel.setLayout(new BorderLayout(10,0));
		rulesPanel.add(rulesLabel, BorderLayout.LINE_START);
		rulesPanel.add(rulesPanelIn, BorderLayout.CENTER);
		
		GridLayout border = new GridLayout(4, 1);
		border.setHgap(COMPONENT_GAP);
		border.setVgap(COMPONENT_GAP);
		loadingPanel.setLayout(border);
		loadingPanel.setBorder(
				BorderFactory.createEmptyBorder(0,COMPONENT_GAP,COMPONENT_GAP,COMPONENT_GAP));
		loadingPanel.add(windowLabel);
		loadingPanel.add(spamPanel);
		loadingPanel.add(hamPanel);
		loadingPanel.add(rulesPanel);		


		initiationPanel = new AntiSpamFilterStyles().new APanel();
		initiationPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,50));
		
		//Sprint Item
		//Implementation of initiating panel
		setIniciacaoPanel();		
		

		resultsPanel = new AntiSpamFilterStyles().new APanel();
		resultsPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,200));
		resultsPanel.setLayout(new BorderLayout());

		resultsPanel = new AntiSpamFilterStyles().new APanel();
		resultsPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,200));
		resultsPanel.setLayout(new BorderLayout());
		//Implementação da janela de resultados
		ALabel resultadoslabel = new AntiSpamFilterStyles().new ALabel("RESULTS WINDOW");
		resultadoslabel.setHorizontalAlignment(ALabel.CENTER);		
		

		//Sprint Item
		//Implementation of results panel
		ALabel resultsLabel = new AntiSpamFilterStyles().new ALabel("Results Window");
		resultsLabel.setHorizontalAlignment(ALabel.CENTER);
		resultsLabel.setPreferredSize(new Dimension(500, 30));
		ATextArea textBox= new AntiSpamFilterStyles().new ATextArea();
		textBox.setEditable(false);
		
		resultsPanel.add(resultsLabel, BorderLayout.NORTH);
		resultsPanel.add(textBox, BorderLayout.CENTER);
		resultsPanel.setBorder(
				BorderFactory.createEmptyBorder(0,COMPONENT_GAP,COMPONENT_GAP,COMPONENT_GAP));
			
		AScrollPane scrollArea = new AntiSpamFilterStyles().new AScrollPane(textBox);
		resultsPanel.add(scrollArea);

		
		//futuramente iremos colocar os FP, FN e efficiency que vêm da configuração como variaveis
		String title= new String("Optimizing Process Result:");
		String fp= new String(" Final count of FP: X");
		String fn= new String(" Final count of FN: Y");
		String efficiency= new String(" Optimizing efficiency: W");
		textBox.append(title + "\n");
		textBox.append(fp + "\n" );
		textBox.append(fn + "\n");
		textBox.append(efficiency + "\n");
		
			
		conclusionPanel = new AntiSpamFilterStyles().new APanel();
		conclusionPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,60));
		
		//Sprint Item
		//Implementation of results panel
		setConclusaoPanel();

		
		//Sprint Item
		//Implementation of the environment
		resultsAndConclusionPanel = new AntiSpamFilterStyles().new APanel();
		
		resultsAndConclusionPanel.setLayout(new BorderLayout());
		resultsAndConclusionPanel.add(resultsPanel,BorderLayout.CENTER);
		resultsAndConclusionPanel.add(conclusionPanel,BorderLayout.PAGE_END);
		
		antiSpamFilterFrame.setLayout(new BorderLayout());

		antiSpamFilterFrame.getContentPane().add(loadingPanel,BorderLayout.PAGE_START);
		antiSpamFilterFrame.getContentPane().add(initiationPanel,BorderLayout.CENTER);
		antiSpamFilterFrame.getContentPane().add(resultsAndConclusionPanel,BorderLayout.PAGE_END);

		antiSpamFilterFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("Icon.png"));
		antiSpamFilterFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		antiSpamFilterFrame.setResizable(false);
		antiSpamFilterFrame.setVisible(true);

		antiSpamFilterFrame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}

			@Override
			public void windowClosing(WindowEvent e) {
				confirmCloseWindow();
			}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowActivated(WindowEvent e) {}
		});
	}

	private void setIniciacaoPanel() {
		//Implementation of the button panel Clear e Start
		APanel buttonPanel = setButtonPanel(2);

		AButton configurationButton = 
				new AntiSpamFilterStyles().
				new AButton("Configure rules", AntiSpamFilterStyles.BTN_DEFAULT);
		
		configurationButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				configureGUI.setVisible(true);
				antiSpamFilterFrame.setEnabled(false);
			}
		});
		
		AButton startButton = 
				new AntiSpamFilterStyles().
				new AButton("Start optimization", AntiSpamFilterStyles.BTN_DEFAULT);
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Test Start optimization Button ");
			}
		});
		

		buttonPanel.add(configurationButton);
		buttonPanel.add(startButton);
		initiationPanel.add(buttonPanel);
	}
	
	private void setConclusaoPanel() {
		//Implementation of the button panel ExitWithout e SaveExit
		APanel buttonPanel = setButtonPanel(2);

		AButton withoutSaveButton = 
				new AntiSpamFilterStyles().
				new AButton("Exit without saving", AntiSpamFilterStyles.BTN_DEFAULT);
		
		withoutSaveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AntiSpamFilterStyles().new AOptionPane();
				int result = AOptionPane.showConfirmDialog(null, 
						"Are you sure? All the changes will be lost",
						null, AOptionPane.YES_NO_OPTION);
				
				if (result == AOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});
		
		AButton saveButton = 
				new AntiSpamFilterStyles().
				new AButton("Save optimization", AntiSpamFilterStyles.BTN_DEFAULT);
		
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Test Save optimization Button ");
			}
		});

		buttonPanel.add(withoutSaveButton);
		buttonPanel.add(saveButton);
		conclusionPanel.add(buttonPanel);
	}
	
	private APanel setButtonPanel(int n) {
		APanel buttonPanel = new AntiSpamFilterStyles().new APanel();
		buttonPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,40));
		GridLayout grid = new GridLayout(1,n);
		grid.setHgap(COMPONENT_GAP);
		buttonPanel.setLayout(grid);
		return buttonPanel;
	}

	public void setEnable(boolean b) {
		antiSpamFilterFrame.setEnabled(b);
		antiSpamFilterFrame.setVisible(b);
	}
	
	private void confirmCloseWindow() {
		new AntiSpamFilterStyles().new AOptionPane();
		int result = AOptionPane.showConfirmDialog(null, 
				"Are you sure? All the changes will be lost",
				null, AOptionPane.YES_NO_OPTION);
		
		if (result == AOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getWINDOW_HSIZE() {
		return WINDOW_HSIZE;
	}

	public int getWINDOW_VSIZE() {
		return WINDOW_VSIZE;
	}

	public int getCOMPONENT_GAP() {
		return COMPONENT_GAP;
	}

	public int getCOMPONENT_MAX_WIDTH() {
		return COMPONENT_MAX_WIDTH;
	}

	public String getSPAM_FILENAME() {
		return SPAM_FILENAME;
	}

	public String getSPAM_DIRECTORY() {
		return SPAM_DIRECTORY;
	}

	public File getSPAM_FILE() {
		return SPAM_FILE;
	}

	public String getHAM_FILENAME() {
		return HAM_FILENAME;
	}

	public String getHAM_DIRECTORY() {
		return HAM_DIRECTORY;
	}

	public File getHAM_FILE() {
		return HAM_FILE;
	}

	public String getRULES_FILENAME() {
		return RULES_FILENAME;
	}

	public String getRULES_DIRECTORY() {
		return RULES_DIRECTORY;
	}

	public File getRULES_FILE() {
		return RULES_FILE;
	}
	
	public int te () {
		return 1;
	}

}