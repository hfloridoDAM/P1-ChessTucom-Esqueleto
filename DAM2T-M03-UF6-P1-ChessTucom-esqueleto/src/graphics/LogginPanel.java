package graphics;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Color;

public class LogginPanel extends JPanel {
	private JTextField textField;
	private JLabel textLoggin;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JButton btnSalir;

	/**
	 * Create the panel.
	 */
	public LogginPanel() {
		setLayout(null);
		setBounds(0,0,673,432);
		
		btnNewButton = new JButton("Aceptar");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setBounds(398, 212, 173, 39);
		add(btnNewButton);
		
		textField = new JTextField("Hector");
		textField.setBounds(398, 125, 173, 33);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField("aabbcc");
		passwordField.setBounds(398, 168, 173, 33);
		add(passwordField);
		
		textLoggin = new JLabel();
		textLoggin.setBounds(398, 261, 173, 33);
		add(textLoggin);
		

		setFocusable(false);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBackground(Color.RED);
		btnSalir.setBounds(398, 261, 173, 39);
		add(btnSalir);

	}
	
	public JButton getButton() {
		return this.btnNewButton;
	}
	
	public JButton getSalirButton() {
		return this.btnSalir;
	}
	
	public JTextField getUser() {
		return this.textField;
	}
	
	public JPasswordField getPassword() {
		return this.passwordField;
	}
	
	public void printText(String text) {
		textLoggin.setText(text);
	}
}
