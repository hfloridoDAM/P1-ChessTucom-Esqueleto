package graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*; 

public class MainFrame extends JFrame {

	private JSplitPane splitPane;
	private JButton btnTestChess;
	private JButton btnCloseChess;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Chess");
		setBounds(0, 0, 1000, 500);		
		setFocusable(false);
		getContentPane().setLayout(null);
		
		btnTestChess = new JButton("Test Chess");
		btnTestChess.setBounds(430, 211, 121, 35);
		getContentPane().add(btnTestChess);
		
		btnCloseChess = new JButton("Close Chess");
		btnCloseChess.setBounds(431, 256, 121, 35);
		getContentPane().add(btnCloseChess);
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}
	
	public JButton getBtnTestChess() {
		return this.btnTestChess;
	}
	
	public JButton getBtnCloseChess() {
		return this.btnCloseChess;
	}
	

}
