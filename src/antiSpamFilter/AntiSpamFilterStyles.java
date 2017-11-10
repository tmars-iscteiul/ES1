package antiSpamFilter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
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

/**
 * <p>This class includes the styles used to define the design of 
 * the JComponents. The style classes are:</br>
 * 1. APanel extends JPanel;</br>
 * 2. ALabel extends JLabel;</br>
 * 3. AComboBox extends JComboBox;</br>
 * 4. AButton extends JButton;</br>
 * 5. AList extends JList;</br>
 * 6. ATextField extends JTextField;</br>
 * 7. ASpinner extends JSpinner;</br>
 * 8. AOptionPane extends JOptionPane;</br>
 * 9. ATextArea extends JTextArea;</br>
 * 10. AScrollPane extends JScrollPane;</br>
 * </p>
 * 
 * @author ES1-2017-LIGE-PL-102
 * 
 */

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
	protected class APanel extends JPanel {
		/**
		 * <p> New Panel with white background. </p>
		 */
		private static final long serialVersionUID = 1L;

		public APanel() {
			super();
			super.setBackground(Color.WHITE);
		}
	}	

	//JLabel -> use ALabel		
	protected class ALabel extends JLabel {
		/**
		 * <p> New Label with gray foreground and font Arial.
		 * You can define the label with the type Title (size 18) or Small (size 16). </p>
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
	protected class AComboBox extends JComboBox<Object> {
		/**
		 * <p> New ComboBox with white background, gray foreground and font Arial 14. </p>
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
	protected class AButton extends JButton {
		/**
		 * <p> New Button with font Arial 16. 
		 * You can define the button with the type DANGER (color red), 
		 * type SUCCESS (color green) or type DEFAULT (color blue). </p>
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

	//JList -> use AList		
	protected class AList extends JList<Object> {
		/**
		 * <p> New ListBox with white background, gray foreground and font Arial 14. </p>
		 */
		private static final long serialVersionUID = 1L;
		
		public AList(String[] list) {
			super(list);
			super.setFont(new Font("Arial", Font.PLAIN, 14));
			super.setForeground(TEXTCOLOR);

		}
	}
	
	//JTextField -> use ATextField
	protected class ATextField extends JTextField {
		
		/**
		 * <p> New TextField with white background, gray foreground and font Arial 16.
		 * It is possible to use a placeholder to define the field. </p>
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
	protected class ASpinner extends JSpinner {

		/**
		 * <p> New Spinner with white background, gray foreground and font Arial 16. </p>
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
	protected class AOptionPane extends JOptionPane {
		
		/**
		 * <p> New OptionPane with white background, gray foreground and font Arial 14. </p>
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
	
	//JTextArea -> use ATextArea
	protected class ATextArea extends JTextArea {

		/**
		 * <p> New TextArea with white background, gray foreground and font Arial 16. </p>
		 */
		private static final long serialVersionUID = 1L;
		
		public ATextArea() {
			super();
			super.setFont(new Font("Arial", Font.PLAIN, 16));
			super.setBackground(Color.WHITE);
			super.setForeground(TEXTCOLOR);
		}
		
	}
	
	//JScrollPane -> use AScrollPane
	protected class AScrollPane extends JScrollPane {

		/**
		 * <p> New ScrollPane with light gray border. </p>
		 */
		private static final long serialVersionUID = 1L;
		
		public AScrollPane(ATextArea textBox) {
			super(textBox);
			super.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));
		}
		
	}
}
