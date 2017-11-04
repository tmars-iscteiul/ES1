package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import antiSpamFilter.AntiSpamFilterStyles.*;


public class AntiSpamFilterGUI extends JComponent {
	
	/**
	 * <h2>AntiSpamFilterGUI - the visual class</h2>
	 * <p>
	 * Creates the Graphics User Interface for the AntiSpamFilter app. 
	 * The default window has a 500x600 size and a modern design.
	 * The class also includes the styles used to define the design of 
	 * the JComponents. The style classes are:</p>
	 * <li>
	 * 	<ul>AntiSpamFilterPanel extends JPanel;</ul>
	 * 	<ul>AntiSpamFilterLabel extends JLabel;</ul>
	 * 	<ul>AntiSpamFilterComboBox extends JComboBox;</ul>
	 * 	<ul>AntiSpamFilterButton extends JButton;</ul>
	 *	<ul>AntiSpamFilterList extends JList;</ul>
	 * </li>
	 *
	 * @param  url  an absolute URL giving the base location of the image
	 * @param  name the location of the image, relative to the url argument
	 * @return      the image at the specified URL
	 * @see         Image
	 */

	private static final long serialVersionUID = 1L;
	private JFrame antiSpamFilterFrame = new JFrame("AntiSpamFilter Optimizer v1.0");
	
	private final int WINDOW_HSIZE = 500;
	private final int WINDOW_VSIZE = 600;
	private final int COMPONENT_GAP = 20;
	private final int COMPONENT_MAX_WIDTH = WINDOW_HSIZE-(2*COMPONENT_GAP);
	
	
	//Inicializacão dos painéis
	APanel carregamentoPanel, iniciacaoPanel, resultadosPanel, conclusaoPanel,
		resultadosEConclusaoPanel;

	public AntiSpamFilterGUI() {
		//Dimensionamento da janela
		antiSpamFilterFrame.setSize(WINDOW_HSIZE, WINDOW_VSIZE);
		antiSpamFilterFrame.setLocationRelativeTo(null);

		carregamentoPanel = new AntiSpamFilterStyles().new APanel();
		carregamentoPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,250));
		//Sprint Item
		//Implementação da janela de carregamento
		ALabel spamlabel = 
				new AntiSpamFilterStyles().new ALabel("Janela de Carregamento");
		carregamentoPanel.add(spamlabel);
		// TODO Auto-generated method stub


		iniciacaoPanel = new AntiSpamFilterStyles().new APanel();
		iniciacaoPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,50));
		//Sprint Item
		//Implementação da janela de iniciacao
		setIniciacaoPanel();		
		
		
		resultadosPanel = new AntiSpamFilterStyles().new APanel();
		resultadosPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,200));
		//Sprint Item
		//Implementação da janela de resultados
		ALabel resultadoslabel = new AntiSpamFilterStyles().new ALabel("Janela de Resultados");
		resultadosPanel.add(resultadoslabel);
		// TODO Auto-generated method stub


		conclusaoPanel = new AntiSpamFilterStyles().new APanel();
		conclusaoPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,60));
		//Sprint Item
		//Implementação da janela de resultados
		setConclusaoPanel();

		
		//Sprint Item
		//Implementação do ambiente
		resultadosEConclusaoPanel = new AntiSpamFilterStyles().new APanel();
		
		resultadosEConclusaoPanel.setLayout(new BorderLayout());
		resultadosEConclusaoPanel.add(resultadosPanel,BorderLayout.CENTER);
		resultadosEConclusaoPanel.add(conclusaoPanel,BorderLayout.PAGE_END);
		
		antiSpamFilterFrame.setLayout(new BorderLayout());

		antiSpamFilterFrame.getContentPane().add(carregamentoPanel,BorderLayout.PAGE_START);
		antiSpamFilterFrame.getContentPane().add(iniciacaoPanel,BorderLayout.CENTER);
		antiSpamFilterFrame.getContentPane().add(resultadosEConclusaoPanel,BorderLayout.PAGE_END);

		antiSpamFilterFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("Icon.png"));
		antiSpamFilterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		antiSpamFilterFrame.setResizable(false);
		antiSpamFilterFrame.setVisible(true);
	}

	private void setIniciacaoPanel() {
		//Criação do painel de botões Clear e Start
		APanel buttonPanel = setButtonPanel(2);

		//Criação dos dois botões
		AButton clearButton = 
				new AntiSpamFilterStyles().
				new AButton("Configure rules", AntiSpamFilterStyles.BTN_DEFAULT);
		AButton startButton = 
				new AntiSpamFilterStyles().
				new AButton("Start optimization", AntiSpamFilterStyles.BTN_SUCCESS);

		buttonPanel.add(clearButton);
		buttonPanel.add(startButton);
		iniciacaoPanel.add(buttonPanel);
	}
	
	private void setConclusaoPanel() {
		//Criação do painel de botões ExitWithout e SaveExit
		APanel buttonPanel = setButtonPanel(2);

		//Criação dos dois botões
		AButton withoutSaveButton = 
				new AntiSpamFilterStyles().
				new AButton("Exit without saving", AntiSpamFilterStyles.BTN_DEFAULT);
		AButton saveButton = 
				new AntiSpamFilterStyles().
				new AButton("Save optimization", AntiSpamFilterStyles.BTN_DEFAULT);

		buttonPanel.add(withoutSaveButton);
		buttonPanel.add(saveButton);
		conclusaoPanel.add(buttonPanel);
	}
	
	private APanel setButtonPanel(int n) {
		//Criação de paineis para os botões
		APanel buttonPanel = new AntiSpamFilterStyles().new APanel();
		buttonPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,40));
		GridLayout grid = new GridLayout(1,n);
		grid.setHgap(COMPONENT_GAP);
		buttonPanel.setLayout(grid);
		return buttonPanel;
	}
	
	
}