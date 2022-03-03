package graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*; 

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JSplitPane splitPane;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Chess");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		splitPane = new JSplitPane();
		splitPane.setBounds(10, 0, 959, 447);
		//splitPane.setEnabled(false);
		contentPane.add(splitPane);
		
		JPanel panel = new JPanel();
		panel.setMaximumSize(new Dimension(250,490));
		panel.setMinimumSize(new Dimension(250,490));
		splitPane.setLeftComponent(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMaximumSize(new Dimension(730,490));
		panel_1.setMinimumSize(new Dimension(730,490));
		splitPane.setRightComponent(panel_1);
		
		setFocusable(false);
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}
	
	

}
