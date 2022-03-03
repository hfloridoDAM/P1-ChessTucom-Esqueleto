package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeSet;

import model.Puntuacion;

public class FileManager {

	private String user;
	
	public FileManager() {
	}

	public ArrayList<Puntuacion> getPuntuaciones() {
		Scanner br = null;
		TreeSet<Puntuacion> puntuaciones = new TreeSet<>();
		try {
			br = new Scanner(new File("Files/puntuaciones.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (br.hasNext()) {
			String line = br.nextLine();
			String[] punt = line.split(" ");
			if (punt[0].equalsIgnoreCase(user)) 
				puntuaciones.add(new Puntuacion(punt[0], Integer.valueOf(punt[1]), punt[2]));
		}
		return toArrayList(puntuaciones);
	}
	
	private ArrayList<Puntuacion> toArrayList(TreeSet<Puntuacion> puntuacions) {
		ArrayList<Puntuacion> newPuntuaciones = new  ArrayList<>();
		for (Puntuacion p : puntuacions) {
			newPuntuaciones.add(p);
		}
		return newPuntuaciones;
	}
	
	public void saveScore(int puntos) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String datos = "\n" + user + " " + puntos + " " + df.format(new Date());
		File file = new File("Files/puntuaciones.txt");
		FileWriter fr;
		try {
			fr = new FileWriter(file, true);
			fr.write(datos);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkUser(String user, String pass) {
		Scanner br = null;
		try {
			br = new Scanner(new File("Files/loggin.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (br.hasNext()) {
			String line = br.nextLine();
			String[] usus = line.split(" ");
			System.out.println("usu: " + usus[0] + " con p: " + usus[1]);
			if (user.equals(usus[0]) && pass.equals(usus[1])) {
				this.user = user;
				return true;
			}
		}
		return false;
	}
}
