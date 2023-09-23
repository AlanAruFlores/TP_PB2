package ar.edu.unlam.dominio;

import java.util.Objects;

public class Profesor {

	private Integer dni;
	private String nombre;
	private String apellido;

	public Profesor(Integer dni) {
		this.dni = dni;
	}
	public Profesor(Integer dni, String nombre, String apellido) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido  = apellido;
	}

	public Integer getDni() {
		return this.dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		return Objects.equals(dni, other.dni);
	}
	
	

}
