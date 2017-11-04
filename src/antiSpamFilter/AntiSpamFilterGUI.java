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
	
	
	//Inicializac�o dos pain�is
	AntiSpamFilterPanel carregamentoPanel, iniciacaoPanel, resultadosPanel, conclusaoPanel,
		resultadosEConclusaoPanel;

	public AntiSpamFilterGUI() {
		//Dimensionamento da janela
		antiSpamFilterFrame.setSize(WINDOW_HSIZE, WINDOW_VSIZE);
		antiSpamFilterFrame.setLocationRelativeTo(null);

		carregamentoPanel = new AntiSpamFilterStyles().new AntiSpamFilterPanel();
		carregamentoPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,250));
		//Sprint Item
		//Implementa��o da janela de carregamento
		AntiSpamFilterLabel spamlabel = 
				new AntiSpamFilterStyles().new AntiSpamFilterLabel("Janela de Carregamento");
		carregamentoPanel.add(spamlabel);
		// TODO Auto-generated method stub


		iniciacaoPanel = new AntiSpamFilterStyles().new AntiSpamFilterPanel();
		iniciacaoPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,50));
		//Sprint Item
		//Implementa��o da janela de iniciacao
		setIniciacaoPanel();		
		
		
		resultadosPanel = new AntiSpamFilterStyles().new AntiSpamFilterPanel();
		resultadosPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,200));
		//Sprint Item
		//Implementa��o da janela de resultados
		AntiSpamFilterLabel resultadoslabel = 
				new AntiSpamFilterStyles().new AntiSpamFilterLabel("Janela de Resultados");
		resultadosPanel.add(resultadoslabel);
		// TODO Auto-generated method stub


		conclusaoPanel = new AntiSpamFilterStyles().new AntiSpamFilterPanel();
		conclusaoPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,60));
		//Sprint Item
		//Implementa��o da janela de resultados
		setConclusaoPanel();

		
		//Sprint Item
		//Implementa��o do ambiente
		resultadosEConclusaoPanel = new AntiSpamFilterStyles().new AntiSpamFilterPanel();
		
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
		//Cria��o do painel de bot�es Clear e Start
		AntiSpamFilterPanel buttonPanel = setButtonPanel(2);

		//Cria��o dos dois bot�es
		AntiSpamFilterButton clearButton = 
				new AntiSpamFilterStyles().new AntiSpamFilterButton("Configure rules", AntiSpamFilterStyles.BTN_DEFAULT);
		AntiSpamFilterButton startButton = 
				new AntiSpamFilterStyles().new AntiSpamFilterButton("Start optimization", AntiSpamFilterStyles.BTN_SUCCESS);

		buttonPanel.add(clearButton);
		buttonPanel.add(startButton);
		iniciacaoPanel.add(buttonPanel);
	}
	
	private void setConclusaoPanel() {
		//Cria��o do painel de bot�es ExitWithout e SaveExit
		AntiSpamFilterPanel buttonPanel = setButtonPanel(2);

		//Cria��o dos dois bot�es
		AntiSpamFilterButton clearButton = 
				new AntiSpamFilterStyles().new AntiSpamFilterButton("Exit without saving", AntiSpamFilterStyles.BTN_DEFAULT);
		AntiSpamFilterButton startButton = 
				new AntiSpamFilterStyles().new AntiSpamFilterButton("Save and quit", AntiSpamFilterStyles.BTN_DEFAULT);

		buttonPanel.add(clearButton);
		buttonPanel.add(startButton);
		conclusaoPanel.add(buttonPanel);
	}
	
	private AntiSpamFilterPanel setButtonPanel(int n) {
		//Cria��o de paineis para os bot�es
		AntiSpamFilterPanel buttonPanel = 
				new AntiSpamFilterStyles().new AntiSpamFilterPanel();
		buttonPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,40));
		GridLayout grid = new GridLayout(1,n);
		grid.setHgap(COMPONENT_GAP);
		buttonPanel.setLayout(grid);
		return buttonPanel;
	}
	
	
}