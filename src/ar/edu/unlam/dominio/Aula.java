package ar.edu.unlam.dominio;
import java.util.Objects;

public class Aula {
	private Integer numeroAula;
	private Integer capacidad;

	public Aula(Integer numeroAula, Integer capacidad) {
		this.numeroAula = numeroAula;
		this.capacidad = capacidad;
	}
	public Aula(Integer numeroAula) {
		this.numeroAula = numeroAula;
		
	}
	
	public Integer getNumero() {
		return this.numeroAula;
	}

	public Integer getCapacidad() {
		return this.capacidad;
	}
	@Override
	public int hashCode() {
		return Objects.hash(numeroAula);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aula other = (Aula) obj;
		return Objects.equals(numeroAula, other.numeroAula);
	}
	
}
