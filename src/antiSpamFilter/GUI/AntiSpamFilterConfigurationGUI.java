package antiSpamFilter.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import antiSpamFilter.AntiSpamFilterManualConfiguration;
import antiSpamFilter.GUI.AntiSpamFilterStyles.*;

/**
 * <p>AntiSpamFilterConfigurationGUI - the configuration GUI class</br>
 * </br>
 * The Graphics User Interface for the AntiSpamFilter manual configuration window. 
 * The default window has a 500x500 size and a modern design. 
 * With a six panel system design, it is possible to check and search all the
 * rules applied in the e-mails, change the weight of them manually and test the
 * optimization comparing it with the previous values.</p>
 *
 * @author ES1-2017-LIGE-PL-102
 */

public class AntiSpamFilterConfigurationGUI {
	private JFrame antiSpamFilterFrame = new JFrame("AntiSpamFilter Configuration v1.0");
	private AntiSpamFilterManualConfiguration main;
	
	private final int WINDOW_HSIZE = 500;
	private final int WINDOW_VSIZE = 500;
	private final int COMPONENT_GAP = 10;
	private final int COMPONENT_MAX_WIDTH = WINDOW_HSIZE-(2*COMPONENT_GAP);
	private final int COMPONENT_MAX_HEIGHT = WINDOW_VSIZE-(2*COMPONENT_GAP);
	
	private final double MIN_RULE = -5.0;
	private final double MAX_RULE = 5.0;
	private final double INT_RULE = 0.1;
	private final double INIC_RULE = 0.0;
	
	//Panels initiation
	APanel ruleslistPanel, principalPanel, searchPanel, configurationPanel, inputPanel, applyPanel,
		testsPanel, conclusionPanel;
	
	AList<String> rulesList;
	AScrollPane scroll;
	ASpinner spinner;
	DefaultListModel<String> rulesListModel, resultsListModel;
	int lastSelectedRule;

	public AntiSpamFilterConfigurationGUI(
			AntiSpamFilterManualConfiguration main, boolean visible) {
		//Dimension and position of the window
		antiSpamFilterFrame.setSize(WINDOW_HSIZE, WINDOW_VSIZE);
		antiSpamFilterFrame.setLocationRelativeTo(null);
		antiSpamFilterFrame.setLocation(
				(int)antiSpamFilterFrame.getLocation().getX()+80,
				(int)antiSpamFilterFrame.getLocation().getY()+20);
		
		antiSpamFilterFrame.setVisible(visible);
		this.main = main;
				
		setRulesListPanel();
		setMainConfigurationPanel();		
		setSearchPanel();
		setRuleConfigurationPanel();		
		setInputPanel();
		setApplyTestButtons();		
		setTestResultsPanel();
		
		configurationPanel.setLayout(new BorderLayout(COMPONENT_GAP,COMPONENT_GAP));
		configurationPanel.setPreferredSize(new Dimension(200,300));
		configurationPanel.setBorder(
				BorderFactory.createEmptyBorder(COMPONENT_GAP, 0, COMPONENT_GAP, 0));
		
		configurationPanel.add(inputPanel, BorderLayout.PAGE_START);
		configurationPanel.add(applyPanel, BorderLayout.CENTER);
		configurationPanel.add(testsPanel, BorderLayout.PAGE_END);
		
		//Implementation of back and save buttons
		conclusionPanel = new AntiSpamFilterStyles().new APanel();
		conclusionPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,50));
		setConclusaoPanel();
		
		//Inclusion of the panels in the application window
		principalPanel.add(searchPanel, BorderLayout.NORTH);
		principalPanel.add(configurationPanel, BorderLayout.CENTER);
		principalPanel.add(conclusionPanel, BorderLayout.PAGE_END);

		
		//Implementation of the environment
		antiSpamFilterFrame.setLayout(new BorderLayout(2,2));

		antiSpamFilterFrame.getContentPane().add(ruleslistPanel,BorderLayout.WEST);
		antiSpamFilterFrame.getContentPane().add(principalPanel,BorderLayout.CENTER);

		antiSpamFilterFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("Icon.png"));
		antiSpamFilterFrame.setResizable(false);
		antiSpamFilterFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
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

	private void setRulesListPanel() {
		//Implementation of rules list window
		ruleslistPanel = new AntiSpamFilterStyles().new APanel();
		ruleslistPanel.setPreferredSize(new Dimension(200,COMPONENT_MAX_HEIGHT));
		ruleslistPanel.setLayout(new BorderLayout(10,10));
		ruleslistPanel.setBorder(
				BorderFactory.createEmptyBorder(COMPONENT_GAP,COMPONENT_GAP,COMPONENT_GAP,COMPONENT_GAP));

		ALabel spamLabel = new AntiSpamFilterStyles().new ALabel("Rules List");
		ruleslistPanel.add(spamLabel, BorderLayout.PAGE_START);
	}

	
	private void setMainConfigurationPanel() {
		//Implementation of main configuration window
		principalPanel = new AntiSpamFilterStyles().new APanel();
		principalPanel.setLayout(new BorderLayout(COMPONENT_GAP,COMPONENT_GAP));
		principalPanel.setBorder(
				BorderFactory.createEmptyBorder(COMPONENT_GAP,COMPONENT_GAP,COMPONENT_GAP,COMPONENT_GAP));
	}

	
	private void setSearchPanel() {
		//Implementation of search window
		searchPanel = new AntiSpamFilterStyles().new APanel();
		ATextField searchField = new AntiSpamFilterStyles().new ATextField("");
		searchField.setPlaceholder("Search field");

		AButton clearSearchButton = 
				new AntiSpamFilterStyles().
				new AButton("Clear search", AntiSpamFilterStyles.BTN_DEFAULT);
		
		searchField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				main.filterRulesList(searchField.getText().toUpperCase());
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				main.filterRulesList(searchField.getText().toUpperCase());
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
					
			}
		});
		
		clearSearchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchField.setText("");
			}
		});

		searchPanel.setLayout(new BorderLayout(COMPONENT_GAP,5));
		searchPanel.setPreferredSize(new Dimension(200,75));
		searchPanel.add(searchField,BorderLayout.NORTH);
		searchPanel.add(clearSearchButton, BorderLayout.CENTER);
	}

	
	private void setRuleConfigurationPanel() {
		//Implementation of rule configuration window
		configurationPanel = new AntiSpamFilterStyles().new APanel();
		inputPanel = new AntiSpamFilterStyles().new APanel();
		testsPanel = new AntiSpamFilterStyles().new APanel();
	}

	
	private void setInputPanel() {
		//Implementation of input window
		inputPanel.setLayout(new BorderLayout(COMPONENT_GAP,COMPONENT_GAP));
		ALabel configurationLabel = new AntiSpamFilterStyles().new ALabel("Configuration Panel");
		ALabel weightLabel = new AntiSpamFilterStyles().new ALabel("Rule weight:", AntiSpamFilterStyles.TXT_SMALL);

		SpinnerModel spinnerModel = new SpinnerNumberModel(INIC_RULE, MIN_RULE, MAX_RULE, INT_RULE);
		spinner = new AntiSpamFilterStyles().new ASpinner(spinnerModel);

		inputPanel.add(configurationLabel, BorderLayout.PAGE_START);
		inputPanel.add(weightLabel, BorderLayout.LINE_START);
		inputPanel.add(spinner, BorderLayout.CENTER);
	}

	
	private void setApplyTestButtons() {
		//Implementation of apply and test buttons
		applyPanel = new AntiSpamFilterStyles().new APanel();
		applyPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,40));
		setApplyPanel();
	}

	
	private void setTestResultsPanel() {
		//Implements the test results window
		testsPanel.setLayout(new BorderLayout(COMPONENT_GAP,COMPONENT_GAP));
		ALabel testsLabel = new AntiSpamFilterStyles().new ALabel("Test results");
		
		resultsListModel = new DefaultListModel<String>();
		resultsListModel.addElement("FP: ");
		resultsListModel.addElement("FN: ");
		resultsListModel.addElement("Compare results: ");
		resultsListModel.addElement("Efficiency: ");
		resultsListModel.addElement("Tip: ");
		
		AList<String> testResultsList = new AntiSpamFilterStyles().new AList<String>(resultsListModel);
		testResultsList.setFont(new Font("Arial", Font.PLAIN, 16));
		testsPanel.add(testsLabel, BorderLayout.PAGE_START);
		testsPanel.add(testResultsList, BorderLayout.CENTER);
	}

	
	private void setApplyPanel() {
		APanel buttonPanel = setButtonPanel();

		AButton applyButton = 
				new AntiSpamFilterStyles().
				new AButton("< Apply", AntiSpamFilterStyles.BTN_DEFAULT);
		
		AButton testButton = 
				new AntiSpamFilterStyles().
				new AButton("Test configuration", AntiSpamFilterStyles.BTN_DEFAULT);

		buttonPanel.add(applyButton, BorderLayout.LINE_START);
		buttonPanel.add(testButton, BorderLayout.CENTER);
		applyPanel.add(buttonPanel);
		
		testButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				System.out.println("Test button test");
			}
		});
		
		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				main.applyWeightValue(rulesList.getSelectedIndex(), (Double)spinner.getValue());
			}
		});
	}
	
	private void setConclusaoPanel() {
		APanel buttonPanel = setButtonPanel();

		AButton backButton = 
				new AntiSpamFilterStyles().
				new AButton("Back", AntiSpamFilterStyles.BTN_DEFAULT);
		
		
		AButton saveButton = 
				new AntiSpamFilterStyles().
				new AButton("Save configuration", AntiSpamFilterStyles.BTN_DEFAULT);
		
		buttonPanel.add(backButton, BorderLayout.LINE_START);
		buttonPanel.add(saveButton, BorderLayout.CENTER);
		conclusionPanel.add(buttonPanel);
		
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				confirmCloseWindow();
			}
		});

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				new AntiSpamFilterStyles().new AOptionPane();
				int result = AOptionPane.showConfirmDialog(null, 
						"Are you sure? The manual changes will be permanent",
						null, AOptionPane.YES_NO_OPTION);
				
				if (result == AOptionPane.OK_OPTION) {
					System.out.println("Save button test");
				}
			}
		});
	}
	
	private APanel setButtonPanel() {
		APanel buttonPanel = new AntiSpamFilterStyles().new APanel();
		buttonPanel.setPreferredSize(new Dimension(270,40));
		BorderLayout layout = new BorderLayout(COMPONENT_GAP,COMPONENT_GAP);
		buttonPanel.setLayout(layout);
		return buttonPanel;
	}

	public void setVisible(boolean visible) {
		antiSpamFilterFrame.setVisible(visible);
	}
	
	private void confirmCloseWindow() {
		new AntiSpamFilterStyles().new AOptionPane();
		int result = AOptionPane.showConfirmDialog(null, 
				"Are you sure? All the changes will be lost",
				null, AOptionPane.YES_NO_OPTION);

		if (result == AOptionPane.OK_OPTION) {
			antiSpamFilterFrame.setVisible(false);
			main.setWindowClose();
		}
	}

	public void startConfiguration() {
		rulesListModel = new DefaultListModel<String>();
		
		for (String rule : main.getListOfNames()) {
			rulesListModel.addElement(rule);
		}
		
		rulesList = new AntiSpamFilterStyles().new AList<String>(rulesListModel);
		scroll = new AntiSpamFilterStyles().new AScrollPane(rulesList,
				AScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            AScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		ruleslistPanel.add(scroll, BorderLayout.CENTER);
		
		rulesList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (rulesList.getSelectedIndex() >= 0) {
					spinner.setValue(new Double(main.getRuleWeight(rulesList.getSelectedIndex())));
					lastSelectedRule = rulesList.getSelectedIndex();
				}
			}
		});
		
		rulesList.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
		        if ( SwingUtilities.isRightMouseButton(e) ) {
		            main.resetWeightValue(rulesList.locationToIndex(e.getPoint()));
		        }
		    }
		});
	}
	
	public void refreshRulesList() {
		rulesListModel.removeAllElements();
		for (String rule : main.getListOfNames())
			rulesListModel.addElement(rule);
		
		rulesList.setSelectedIndex(lastSelectedRule);
	}

}
