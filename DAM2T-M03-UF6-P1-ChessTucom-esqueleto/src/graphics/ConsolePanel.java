package graphics;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextPane;

public class ConsolePanel extends JPanel {

	private JTextPane textPane;
	/**
	 * Create the panel.
	 */
	public ConsolePanel() {
		setBackground(Color.BLACK);
		setLayout(null);
		
		textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setBackground(Color.BLACK);
		textPane.setBounds(10, 10, 180, 311);
		add(textPane);
		
		textPane.setText("CONSOLA:");
		

		setFocusable(false);

	}
	
	public void addtext(String text) {
		this.textPane.setText(this.textPane.getText() + "\n" + text);
	}

	public void reset() {
		this.textPane.setText("CONSOLA:");
		
	}
}
