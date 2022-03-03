package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Puntuacion implements Comparable<Puntuacion> {
	private String name;
	private int puntuacion;
	private Date fecha;
	
	public Puntuacion(String name, int puntuacion, String fecha) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.name = name;
		this.puntuacion = puntuacion;
		try {
			this.fecha = df.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public Date getFecha() {
		return fecha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + puntuacion;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Puntuacion other = (Puntuacion) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (puntuacion != other.puntuacion)
			return false;
		return true;
	}

	@Override
	public int compareTo(Puntuacion arg0) {
		if (this.fecha.equals(arg0.getFecha())) return 0;
		if (this.puntuacion <= arg0.getPuntuacion()) return 1;
		return -1;
	}
	
}
