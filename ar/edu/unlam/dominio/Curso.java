package ar.edu.unlam.dominio;

import java.util.Objects;

public class Curso {

	private Integer codigoCurso;
	private Materia materia;
	private Aula aula;
	private Horario horarioCursada;
	private CicloLectivo ciclo;

	public Curso(Integer codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	
	public Curso(Integer codigoCurso, Materia materia, Horario horario, CicloLectivo ciclo) {
		this.codigoCurso = codigoCurso;
		this.materia = materia;
		this.horarioCursada = horario;
		this.ciclo = ciclo;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public Horario getHorario() {
		return this.horarioCursada;
	}

	public CicloLectivo getCiclo() {
		return this.ciclo;
	}

	public Aula getAula() {
		return this.aula;
	}

	public Integer getCodigo() {
		return this.codigoCurso;
	}

	//Setter
	
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codigoCurso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(codigoCurso, other.codigoCurso);
	}

	
}
