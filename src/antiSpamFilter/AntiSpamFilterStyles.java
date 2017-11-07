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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;

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
		private String file;
		public ADialog(String s) {
			super((Frame)null, s);
			this.setMode(FileDialog.LOAD);
			this.setVisible(true);
			file = this.getFile();
		}
		public void sendToTextArea (ATextField a) {
			a.setText(file);
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
}
