package antiSpamFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class AntiSpamFilterGUI extends JComponent {

	private static final long serialVersionUID = 1L;
	private JFrame antiSpamFilterFrame = new JFrame("AntiSpamFilter Optimizer v1.0");
	
	private final int WINDOW_HSIZE = 500;
	private final int WINDOW_VSIZE = 600;
	private final int COMPONENT_GAP = 20;
	private final int COMPONENT_MAX_WIDTH = WINDOW_HSIZE-(2*COMPONENT_GAP);
	
	private final int BTN_DEFAULT = 1;
	private final int BTN_SUCCESS = 2;
	private final int BTN_DANGER = 3;
	
	private final Color TEXTCOLOR = new Color(100,100,100);
	private final Color DEFAULT = new Color(102,153,255);
	private final Color DANGER = new Color(255,102,102);
	private final Color SUCCESS = new Color(153,204,51);

	//Definição dos estilos para os objetos das janelas:
	//Panel	-> utilizar AntiSpamFilterPanel	
	class AntiSpamFilterPanel extends JPanel {
		public AntiSpamFilterPanel() {
			super();
			super.setBackground(Color.WHITE);
		}
	}	

	//Label	-> utilizar AntiSpamFilterLabel		
	class AntiSpamFilterLabel extends JLabel {
		public AntiSpamFilterLabel(String string) {
			super(string);
			super.setFont(new Font("Arial", Font.PLAIN, 18));
			super.setForeground(TEXTCOLOR);
		}
	}

	//Listbox -> utilizar AntiSpamFilterComboBox		
	class AntiSpamFilterComboBox extends JComboBox {
		public AntiSpamFilterComboBox(String[] string) {
			super(string);
			super.setFont(new Font("Arial", Font.PLAIN, 14));
			super.setForeground(TEXTCOLOR);
			super.setBackground(Color.WHITE);
		}
	}

	//Button -> utilizar AntiSpamFilterButton			
	class AntiSpamFilterButton extends JButton {
		public AntiSpamFilterButton(String string, int tipo) {
			super(string);
			super.setFont(new Font("Arial", Font.PLAIN, 16));
			super.setForeground(TEXTCOLOR);
			if (tipo == BTN_DANGER) {
				super.setBackground(DANGER);
				super.setForeground(Color.WHITE);
			}
			else if (tipo == BTN_SUCCESS) {
				super.setBackground(SUCCESS);
				super.setForeground(Color.WHITE);
			} 
			else if (tipo == BTN_DEFAULT) {
				super.setBackground(DEFAULT);
				super.setForeground(Color.WHITE);
			}
		}
	}

	//List -> utilizar AntiSpamFilterList		
	class AntiSpamFilterList extends JList {
		public AntiSpamFilterList(DefaultListModel list) {
			super(list);
			super.setFont(new Font("Arial", Font.PLAIN, 16));
			super.setForeground(TEXTCOLOR);

		}
	}
	
	//Inicializacão dos painéis
	AntiSpamFilterPanel carregamentoPanel, iniciacaoPanel, resultadosPanel, conclusaoPanel,
		resultadosEConclusaoPanel;

	public AntiSpamFilterGUI() {
		//Dimensionamento da janela
		antiSpamFilterFrame.setSize(WINDOW_HSIZE, WINDOW_VSIZE);
		antiSpamFilterFrame.setLocationRelativeTo(null);

		carregamentoPanel = new AntiSpamFilterPanel();
		carregamentoPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,250));
		//Sprint Item
		//Implementação da janela de carregamento
		AntiSpamFilterLabel spamlabel = new AntiSpamFilterLabel("Janela de Carregamento");
		carregamentoPanel.add(spamlabel);
		// TODO Auto-generated method stub


		iniciacaoPanel = new AntiSpamFilterPanel();
		iniciacaoPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,50));
		//Sprint Item
		//Implementação da janela de iniciacao
		setIniciacaoPanel();		
		
		
		resultadosPanel = new AntiSpamFilterPanel();
		resultadosPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,200));
		//Sprint Item
		//Implementação da janela de resultados
		AntiSpamFilterLabel resultadoslabel = new AntiSpamFilterLabel("Janela de Resultados");
		resultadosPanel.add(resultadoslabel);
		// TODO Auto-generated method stub


		conclusaoPanel = new AntiSpamFilterPanel();
		conclusaoPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,60));
		//Sprint Item
		//Implementação da janela de resultados
		setConclusaoPanel();

		
		//Sprint Item
		//Implementação do ambiente
		resultadosEConclusaoPanel = new AntiSpamFilterPanel();
		
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
		AntiSpamFilterPanel buttonPanel = setButtonPanel(2);

		//Criação dos dois botões
		AntiSpamFilterButton clearButton = new AntiSpamFilterButton("Clear paths", BTN_DEFAULT);
		AntiSpamFilterButton startButton = new AntiSpamFilterButton("Start optimization", BTN_DEFAULT);

		buttonPanel.add(clearButton);
		buttonPanel.add(startButton);
		iniciacaoPanel.add(buttonPanel);
	}
	
	private void setConclusaoPanel() {
		//Criação do painel de botões ExitWithout e SaveExit
		AntiSpamFilterPanel buttonPanel = setButtonPanel(2);

		//Criação dos dois botões
		AntiSpamFilterButton clearButton = new AntiSpamFilterButton("Exit without saving", BTN_DEFAULT);
		AntiSpamFilterButton startButton = new AntiSpamFilterButton("Save and quit", BTN_DEFAULT);

		buttonPanel.add(clearButton);
		buttonPanel.add(startButton);
		conclusaoPanel.add(buttonPanel);
	}
	
	private AntiSpamFilterPanel setButtonPanel(int n) {
		//Criação de paineis para os botões
		AntiSpamFilterPanel buttonPanel = new AntiSpamFilterPanel();
		buttonPanel.setPreferredSize(new Dimension(COMPONENT_MAX_WIDTH,40));
		GridLayout grid = new GridLayout(1,n);
		grid.setHgap(COMPONENT_GAP);
		buttonPanel.setLayout(grid);
		return buttonPanel;
	}
	
	
}