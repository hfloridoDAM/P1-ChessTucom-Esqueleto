package graphics;

import javax.swing.JPanel;
import javax.swing.JTable;

import model.Puntuacion;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenuPanel extends JPanel {
	private JTable table;
	private JButton nuevaPartida;
	private JButton finPartida;
	private JButton pause;
	private JButton loggOut;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public MenuPanel() {
		setLayout(null);
		
		nuevaPartida = new JButton("Nueva Partida");
		nuevaPartida.setBounds(92, 260, 230, 41);
		add(nuevaPartida);
		
		setFocusable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(92, 47, 454, 203);
		add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		finPartida = new JButton("Fin Partida");
		finPartida.setBounds(92, 306, 230, 41);
		add(finPartida);
		
		pause = new JButton("Salir");
		pause.setVisible(false);
		pause.setBounds(332, 259, 213, 41);
		add(pause);
		
		loggOut = new JButton("Loggout");
		loggOut.setBounds(332, 306, 213, 41);
		add(loggOut);

	}
	
	public JButton getNuevaPartida() {
		return this.nuevaPartida;
	}
	
	public JButton getFinPartida() {
		return this.finPartida;
	}
	
	public JButton getClose() {
		return this.pause;
	}
	
	public JButton getLoggOut() {
		return this.loggOut;
	}
	
	public void setPuntuaciones(ArrayList<Puntuacion> puntuaciones) {
		String[] columNames = {"Name", "SCORE", "DATE"};
		String[][] data = new String[puntuaciones.size()][3];
		for (int i = 0; i < puntuaciones.size(); i++) {
			data[i][0] = puntuaciones.get(i).getName();
			data[i][1] = String.valueOf(puntuaciones.get(i).getPuntuacion());
			data[i][2] = puntuaciones.get(i).getFecha().toString();
		}
		table = new JTable(data,columNames);
		scrollPane.setViewportView(table);
	}
}
