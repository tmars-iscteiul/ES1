package antiSpamFilter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

public class AntiSpamFilterStyles {
	
	protected final static int BTN_DEFAULT = 1;
	protected final static int BTN_SUCCESS = 2;
	protected final static int BTN_DANGER = 3;
	
	protected final static int TXT_TITLE = 1;
	protected final static int TXT_SMALL = 2;
	
	protected final static Color TEXTCOLOR = new Color(100,100,100);
	protected final static Color DEFAULT = new Color(102,153,255);
	protected final static Color DANGER = new Color(255,102,102);
	protected final static Color SUCCESS = new Color(153,204,51);

	//Style definition for the GUI objets:
	//JPanel -> use APanel	
	class APanel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public APanel() {
			super();
			super.setBackground(Color.WHITE);
		}
	}	

	//JLabel -> use ALabel		
	class ALabel extends JLabel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ALabel(String string) {
			super(string);
			super.setFont(new Font("Arial", Font.PLAIN, 18));
			super.setForeground(TEXTCOLOR);
		}
		
		public ALabel(String string, int tipo) {
			super(string);
			if (tipo == TXT_TITLE) {super.setFont(new Font("Arial", Font.PLAIN, 18));}
			else if (tipo == TXT_SMALL) {super.setFont(new Font("Arial", Font.PLAIN, 16));}
			super.setForeground(TEXTCOLOR);
		}
	}

	//JComboBox -> use AComboBox		
	class AComboBox extends JComboBox<Object> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AComboBox(String[] string) {
			super(string);
			super.setFont(new Font("Arial", Font.PLAIN, 14));
			super.setForeground(TEXTCOLOR);
			super.setBackground(Color.WHITE);
		}
	}

	//JButton -> use AButton			
	class AButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AButton(String string, int tipo) {
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
	
	//FileDialog -> use ADialog
	class ADialog extends FileDialog{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String filename;
		private String directory;
		private File file;
		public ADialog(String s) {
			super((Frame)null, s);
			this.setMode(FileDialog.LOAD);
			this.setFile("*.log;*.cf");
			this.setVisible(true);
			directory = this.getDirectory();
			filename = this.getFile();
			file = new File(directory);
		}
		public void sendToTextArea (ATextField a) {
			a.setText(filename);
		}
	}

	//JList -> use AList		
	class AList extends JList<Object> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AList(DefaultListModel list) {
			super(list);
			super.setFont(new Font("Arial", Font.PLAIN, 14));
			super.setForeground(TEXTCOLOR);
		}
		
		public AList(String[] list) {
			super(list);
			super.setFont(new Font("Arial", Font.PLAIN, 14));
			super.setForeground(TEXTCOLOR);

		}
	}
	
	//JTextField -> use ATextField
	class ATextField extends JTextField {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String placeholder = "";

		public ATextField (String string) {
			super(string);
			super.setFont(new Font("Arial", Font.PLAIN, 16));
			super.setForeground(TEXTCOLOR);
			super.setPreferredSize(new Dimension(super.getWidth(),30));
		}
		
		@Override
	    protected void paintComponent(final Graphics pG) {
	        super.paintComponent(pG);

	        if (placeholder.length() == 0 || getText().length() > 0) {
	            return;
	        }

	        final Graphics2D g = (Graphics2D) pG;
	        g.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	        g.setColor(getDisabledTextColor());
	        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
	            .getMaxAscent() + getInsets().top + 3);
	    }

	    public void setPlaceholder(final String s) {
	        placeholder = s;
	    }
	}
	
	//JSpinner -> use ASpinner
	class ASpinner extends JSpinner {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public ASpinner(SpinnerModel s) {
			super(s);
			super.setFont(new Font("Arial", Font.PLAIN, 16));
			super.setForeground(TEXTCOLOR);
			super.setPreferredSize(new Dimension(super.getWidth(),30));
			JComponent editor = this.getEditor();
			JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
			spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
		}
	}
	
	//JOptionPane -> use AOptionPane
	class AOptionPane extends JOptionPane {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public AOptionPane() {
			super();
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			UIManager.put("OptionPane.background", Color.WHITE);
			UIManager.put("Panel.background", Color.WHITE);
			UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
			          "Arial", Font.PLAIN, 14)));
		}
	}
	
	class ATextArea extends JTextArea {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public ATextArea() {
			super();
			super.setFont(new Font("Arial", Font.PLAIN, 16));
			super.setForeground(TEXTCOLOR);
		}
		
	}
	
	class AScrollPane extends JScrollPane {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public AScrollPane(ATextArea textBox) {
			super(textBox);
			super.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));
		}
		
	}
}
