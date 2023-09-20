package ar.edu.unlam.dominio;

import java.util.ArrayList;

import ar.edu.unlam.utils.Dia;
import ar.edu.unlam.utils.Turno;

public class Universidad {

	private String nombreUniversidad;
	private ArrayList<Materia> materias;
	private ArrayList<Alumno> alumnos;
	private ArrayList<Curso> cursos;
	private ArrayList<Profesor> profesores;
	private ArrayList<Aula> aulas;
	private ArrayList<CursoProfesor> asignacionesCP;
	private ArrayList<CursoAlumno> asignacionesCA;

	public Universidad(String nombreUniversidad) {
		this.nombreUniversidad = nombreUniversidad;
		this.materias = new ArrayList<>();
		this.alumnos = new ArrayList<>();
		this.cursos  = new ArrayList<>();
		this.profesores = new ArrayList<>();
		this.asignacionesCP  = new ArrayList<>();
		this.asignacionesCA = new ArrayList<>();
		this.aulas = new ArrayList<>();
	}


	/*REGISTRO*/
	public Boolean registrarMateria(Materia materia) {
		Materia materiaABuscar = buscarMateriaPorCodigo(materia.getCodigo());
		if(materiaABuscar==null) {
			this.materias.add(materia);
			return true;
		}
		return false;
	}
	
	public Boolean registrarAlumno(Alumno alumno) {
		Alumno alumnoABuscar = buscarAlumnoPorDNI(alumno.getDni());
		if(alumnoABuscar == null) {
			this.alumnos.add(alumno);
			return true;
		}
		return false;
	}
	
	public Boolean  crearCurso(Curso curso) {
		Materia existeMateria = buscarMateriaPorCodigo(curso.getMateria().getCodigo());
		Curso existeCurso = buscarCursoPorCodigo(curso.getCodigo());
		if(existeMateria != null && existeCurso == null) {
			this.cursos.add(curso);
			return true;
		}
		return false;
	}
	public Boolean registrarProfesor(Profesor profesor) {
		Profesor existe  = buscarProfesorPorDNI(profesor.getDni());
	
		if(existe == null) {
			this.profesores.add(profesor);
			return true;
		}
		return false;
	}
	
	public Boolean registrarAula(Aula aula) {
		Aula existe = buscarAulaPorCodigo(aula.getNumero());
		
		if(existe != null)
			return false;
		
		this.aulas.add(aula);
		return true;
	}
	private Aula buscarAulaPorCodigo(Integer codigo) {
		Aula aux = new Aula(codigo);
		for(int i  = 0 ; i<this.aulas.size(); i++) {
			if(this.aulas.get(i).equals(aux))
				return this.aulas.get(i);
		}
		return null;
	}


	public Boolean asignarAMateriaCorreleativa(Integer codMateria, Integer codCorreleativa) {
		Materia correleativa = buscarMateriaPorCodigo(codCorreleativa);
		Materia materia = buscarMateriaPorCodigo(codMateria);
		
		if(correleativa == null || materia == null) 
			return false;
		
		materia.agregarCorreleativa(correleativa);
		return true;
	}
	
	public Boolean asignarAulaAlCurso(Integer codAula, Integer codCurso) {
		Aula aula = buscarAulaPorCodigo(codAula);
		Curso curso  = buscarCursoPorCodigo(codCurso);
		
		if(aula == null || curso == null)
			return false;
		
		Boolean aulaOcupada = verificarSiAulaEstaOcupada(aula, curso);
		if(aulaOcupada)
			return false;
		
		curso.setAula(aula);
		return true;
	}

	
	private Boolean verificarSiAulaEstaOcupada(Aula aula, Curso curso) {
		ArrayList<Curso> cursosDelAula = buscarCursosDeUnAula(aula);
		System.out.println(cursosDelAula.size());
		if(cursosDelAula.size() == 0)
			return false;

		Boolean resultado = laAulaEstaOcupada(curso,cursosDelAula);
		if(resultado)
			return true;			
			
		return false;
	}	


	private Boolean laAulaEstaOcupada(Curso curso, ArrayList<Curso> cursosDelAula) {
		for(Curso c: cursosDelAula) {
			if(c.getCiclo().equals(curso.getCiclo()) &&
					estaOcupado(curso,c))
				return true;
		}
		return false;
	}
 

	private ArrayList<Curso> buscarCursosDeUnAula(Aula aula) {
		ArrayList<Curso> cursosAula =  new ArrayList<>();
		for(Curso c : this.cursos) {
			if(c.getAula() != null && c.getAula().equals(aula)) {	
				cursosAula.add(c);
			}
		}
		return cursosAula;
	}


	public Boolean agregarProfesorACurso(Integer dniProfesor, Integer codigo) {
		Curso curso = buscarCursoPorCodigo(codigo);
		Profesor profesor = buscarProfesorPorDNI(dniProfesor);
		
		if(curso == null || profesor == null)
			return false;
		
		Boolean resultado  = verificarSiEstaOcupado(curso,profesor);
		
		if(resultado)
			return false;
		
		CursoProfesor nuevo = new CursoProfesor(curso, profesor);
		this.asignacionesCP.add(nuevo);
		return true;
	}

	/**
	 * Metodo encargado de registrar la nota de un alumno
	 * @param dniAlumno identificador del alumno
	 * @param codCurso identificador del curso
	 * @param nota esta es la calificacion a asignar al alumno
	 * @return retorno si se agrego la nota exitosamente.
	 */
	public Boolean registrarNota(Integer dniAlumno, Integer codCurso, Nota nota) {
		Alumno alumno = buscarAlumnoPorDNI(dniAlumno);
		Curso curso = buscarCursoPorCodigo(codCurso);
		
		if(alumno == null || curso == null)
			return false;
		
		CursoAlumno cursoAlumno  = buscarAsignacionAlumnoPorAlumnoCurso(alumno, curso);
		
		if(cursoAlumno == null)
			return false;
		
		Boolean resultado = cursoAlumno.asignarNota(nota);
		return resultado;
	}
	
	//BUSQUEDAS
	private Materia buscarMateriaPorCodigo(Integer codigo) {
		Materia aux = new Materia(codigo);
		for(int i = 0 ; i<this.materias.size(); i++) {
			if(this.materias.get(i).equals(aux)) {
				return this.materias.get(i);
			}
		}
		return null;
	}
	
	private Alumno buscarAlumnoPorDNI(Integer dni){
		Alumno aux = new Alumno(dni);
		for(int i = 0 ; i < this.alumnos.size(); i++) {
			if(this.alumnos.get(i).equals(aux)) {
				return this.alumnos.get(i);
			}
		}
		return null;
	}	
	
	private Curso buscarCursoPorCodigo(Integer codigo) {
		Curso aux = new Curso(codigo);
		for(int i = 0 ; i<this.cursos.size(); i++) {
			if(this.cursos.get(i).equals(aux)) {
				return this.cursos.get(i);
			}
		}
		return null;
	}

	private Profesor buscarProfesorPorDNI(Integer dni) {
		Profesor aux = new Profesor(dni);
		for(int i  = 0 ; i<this.profesores.size(); i++) {
			if(this.profesores.get(i).equals(aux)) {
				return this.profesores.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Este metodo tiene como fun buscar una instancia del tipo CursoAlumno
	 * 
	 * @param materia materia a buscar
	 * @param dni el identificador del alumno
	 * @return retorna un objeto de Asignacion curso alumno
	 */
	public CursoAlumno buscarAsignacionPorAlumnoMateria(Materia materia, Integer dni) {
		Alumno aux = new Alumno(dni);
		for(int i = 0 ; i<this.asignacionesCA.size(); i++) {
			if(this.asignacionesCA.get(i).getAlumno().equals(aux) &&
			this.asignacionesCA.get(i).getCurso().getMateria().equals(materia)) {
				return this.asignacionesCA.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Metodo para buscar instancias de "AsignacionAlumno" recibiendo como
	 * argumentos el objeto alumno y curso
	 * @param alumno
	 * @param curso
	 * @return retorna la instancia en caso de que se encuentre, sino null
	 */
	public CursoAlumno buscarAsignacionAlumnoPorAlumnoCurso(Alumno alumno, Curso curso) {
		for(int i  = 0; i<this.asignacionesCA.size(); i++) {
			if(this.asignacionesCA.get(i).getCurso().equals(curso)&&
				this.asignacionesCA.get(i).getAlumno().equals(alumno)) {
					return this.asignacionesCA.get(i);
			}
		}
		return null;
	}


	/**
	 * Metodo que verifica si el profesor tiene algun curso que ocupe
	 * el mismo horario del CURSO NUEVO a registrar
	 * @param curso --> curso nuevo
	 * @param profesor --> profesor a vincular dicho curso
	 * @return --> retorna si es posible que el profesor pueda dar el curso
	 */
	private Boolean  verificarSiEstaOcupado(Curso curso, Profesor profesor) {
		ArrayList<Curso> cursosDelProfe = obtenerCursosPorProfesor(profesor.getDni());
		for(int i = 0; i<cursosDelProfe.size(); i++) {
			if( cursosDelProfe.get(i).getCiclo().equals(curso.getCiclo())
					&& estaOcupado(curso, cursosDelProfe.get(i)) ){
				return true; 
			}
		}
		return false;
	}
	
	/**
	 * Metodo que se encarga de evaluar si el horario de ambos cursos
	 * coinciden entre si
	 * @param cursoARegistrar
	 * @param cursoAComparar
	 * @return si esta ocupado retorna true, sino false
	 */
	private Boolean estaOcupado(Curso cursoARegistrar, Curso cursoAComparar) {
		Horario horarioOcupado = cursoAComparar.getHorario();
		Horario horarioCursoNuevo = cursoARegistrar.getHorario();
		
		Dia aux = null;
		Turno turnoOcupado = horarioOcupado.getTurno(), 
				turnoCursoNuevo = horarioCursoNuevo.getTurno();
		for(int i = 0 ; i<horarioOcupado.getDias().size(); i++) {
			aux = horarioOcupado.getDias().get(i);
			if(horarioCursoNuevo.getDias().contains(aux)
				&& turnoCursoNuevo.equals(turnoOcupado)) 
				return true;
		}
		
		return false;
	}



	
}
