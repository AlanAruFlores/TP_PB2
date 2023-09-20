package ar.edu.unlam.dominio;

public class CursoProfesor {

	static Integer contId = 0;
	private Integer id;
	private Curso curso;
	private Profesor profesor;
	
	public CursoProfesor(Curso curso, Profesor profesor) {
		this.id = ++contId;
		this.curso = curso;
		this.profesor = profesor;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public Profesor getProfesor() {
		return this.profesor;
	}

	public Integer getId() {
		return this.id;
	}

	
	
}
