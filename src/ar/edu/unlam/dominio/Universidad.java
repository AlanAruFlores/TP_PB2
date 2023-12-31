package ar.edu.unlam.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import ar.edu.unlam.utils.*;

public class Universidad {

	private String nombreUniversidad;
	private ArrayList<Materia> materias;
	private ArrayList<Alumno> alumnos;
	private ArrayList<Curso> cursos;
	private ArrayList<Profesor> profesores;
	private ArrayList<Aula> aulas;
	private ArrayList<CursoProfesor> asignacionesCP;
	private ArrayList<CursoAlumno> asignacionesCA;
	private ArrayList<CicloLectivo> ciclos;
	private ArrayList<Materia> planDeEstudios;

	public Universidad(String nombreUniversidad) {
		this.nombreUniversidad = nombreUniversidad;
		this.materias = new ArrayList<>();
		this.alumnos = new ArrayList<>();
		this.cursos = new ArrayList<>();
		this.profesores = new ArrayList<>();
		this.asignacionesCP = new ArrayList<>();
		this.asignacionesCA = new ArrayList<>();
		this.aulas = new ArrayList<>();
		this.ciclos = new ArrayList<>();
	}

	/* REGISTRO */
	public Boolean registrarMateria(Materia materia) {
		Materia materiaABuscar = buscarMateriaPorCodigo(materia.getCodigo());
		if (materiaABuscar == null) {
			this.materias.add(materia);
			return true;
		}
		return false;
	}

	public Boolean registrarAlumno(Alumno alumno) {
		Alumno alumnoABuscar = buscarAlumnoPorDNI(alumno.getDni());
		if (alumnoABuscar == null) {
			this.alumnos.add(alumno);
			return true;
		}
		return false;
	}

	public Boolean  crearCurso(Curso curso) {
		Materia existeMateria = buscarMateriaPorCodigo(curso.getMateria().getCodigo());
		Curso existeCurso = buscarCursoPorCodigo(curso.getCodigo());
		
		if(existeMateria == null || existeCurso != null) 
			return false;
		
		//Verifico si el ciclo lectivo existe
		Boolean existe = buscarCiclo(curso.getCiclo())!= null ? true : false;
		if(!existe)
			return false;
		
		//Verifico si existe 
		Boolean resultado  = verificarSiExisteCursoIdentico(curso);
		if(resultado)
			return false;
		
		this.cursos.add(curso);
		return true;
	}

	public Boolean registrarProfesor(Profesor profesor) {
		Profesor existe = buscarProfesorPorDNI(profesor.getDni());

		if (existe == null) {
			this.profesores.add(profesor);
			return true;
		}
		return false;
	}

	public Boolean registrarAula(Aula aula) {
		Aula existe = buscarAulaPorCodigo(aula.getNumero());

		if (existe != null)
			return false;

		this.aulas.add(aula);
		return true;
	}

	public Boolean asignarAMateriaCorreleativa(Integer codMateria, Integer codCorreleativa) {
		Materia correleativa = buscarMateriaPorCodigo(codCorreleativa);
		Materia materia = buscarMateriaPorCodigo(codMateria);

		if (correleativa == null || materia == null)
			return false;

		materia.agregarCorreleativa(correleativa);
		return true;
	}

	public Boolean asignarAulaAlCurso(Integer codAula, Integer codCurso) {
		Aula aula = buscarAulaPorCodigo(codAula);
		Curso curso = buscarCursoPorCodigo(codCurso);

		if (aula == null || curso == null)
			return false;

		Boolean aulaOcupada = verificarSiAulaEstaOcupada(aula, curso);
		if (aulaOcupada)
			return false;

		curso.setAula(aula);
		return true;
	}

	/**
	 * Metodo que permite obtener una del estudiante
	 * 
	 * @param idAlumno
	 * @param idMateria
	 * @return
	 */

	public Nota obtenerNota(Integer idAlumno, Integer idMateria, TipoNota tipoDeNota) {
		Alumno alumno = buscarAlumnoPorDNI(idAlumno);
		Materia materia = buscarMateriaPorCodigo(idMateria);

		if (alumno == null || materia == null)
			return null;

		CursoAlumno ca = buscarAsignacionPorAlumnoMateria(materia, idAlumno);
		Nota notaObtenida = buscarNotaPorTipo(ca, tipoDeNota);

		return notaObtenida;
	}

	public Double calcularPromedio(Integer idAlumno, Integer idCurso) {
		CursoAlumno cursoDelalumno = buscarAsignacionAlumnoPorAlumnoCurso(new Alumno(idAlumno), new Curso(idCurso));
		if (cursoDelalumno == null)
			return 0.0;

		return cursoDelalumno.obtenerNotaFinal();
	}

	public Boolean agregarProfesorACurso(Integer dniProfesor, Integer codigo) {
		Curso curso = buscarCursoPorCodigo(codigo);
		Profesor profesor = buscarProfesorPorDNI(dniProfesor);

		if (curso == null || profesor == null)
			return false;

		Integer cantidadDeAlumnos = calcularCantidadDeAlumnos(curso);
		if (curso.getAula() == null || cantidadDeAlumnos <= 0) // Si no existe un aula asignada , no se podra asignar el
																// profe
			return false;

		// Verifica que no se asigne el mismo docente 2 veces en el mismo curso
		Boolean estaRepetido = verificarSiSeAsignaProfesorMasDeUnaVez(profesor, curso);
		if (estaRepetido)
			return false;

		Boolean estaOcupado = verificarSiEstaOcupado(curso, profesor);
		if (estaOcupado)
			return false;

		Boolean seNecesitaPersonal = verificarSiSeNecesitaProfesor(cantidadDeAlumnos, curso);
		if (seNecesitaPersonal != true)
			return false;

		agregarDocentesAComision(profesor, curso);
		return true;
	}

	/**
	 * Metodo que se encarga de agregar un ciclo lectivo, pero se debe tener en
	 * cuenta que no puede suporponerse con otras
	 * 
	 * @param ciclo el objeto
	 * @return retorna un verdadero si se ingreso, sino un false
	 */
	public boolean agregarCicloLectivo(CicloLectivo ciclo) {

		// Verifico primero si ya existio dicho ciclo
		CicloLectivo existe = buscarCiclo(ciclo);
		if (existe != null)
			return false;

		// Verifico superposicion
		Boolean existeSuperposicion = determinarSiExisteUnaSuperposicion(ciclo);
		if (existeSuperposicion)
			return false;

		this.ciclos.add(ciclo);
		return true;
	}

	/**
	 * Metodo encargado de registrar la nota de un alumno
	 * 
	 * @param dniAlumno identificador del alumno
	 * @param codCurso  identificador del curso
	 * @param nota      esta es la calificacion a asignar al alumno
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
		
		Boolean verificacion = debeUnaCorreleativaAFinal(cursoAlumno,nota);
		
		if(verificacion && nota.getPuntaje()>=7)
			nota.setPuntaje(6);
		
		Boolean resultado = cursoAlumno.asignarNota(nota);
		return resultado;
	}
	/**
	 * Este metodo tiene como fun buscar una instancia del tipo CursoAlumno
	 * 
	 * @param materia materia a buscar
	 * @param dni     el identificador del alumno
	 * @return retorna un objeto de Asignacion curso alumno
	 */
	public CursoAlumno buscarAsignacionPorAlumnoMateria(Materia materia, Integer dni) {
		Alumno aux = new Alumno(dni);
		for (int i = 0; i < this.asignacionesCA.size(); i++) {
			if (this.asignacionesCA.get(i).getAlumno().equals(aux)
					&& this.asignacionesCA.get(i).getCurso().getMateria().equals(materia)) {
				return this.asignacionesCA.get(i);
			}
		}
		return null;
	}

	/**
	 * Metodo para buscar instancias de "AsignacionAlumno" recibiendo como
	 * argumentos el objeto alumno y curso
	 * 
	 * @param alumno
	 * @param curso
	 * @return retorna la instancia en caso de que se encuentre, sino null
	 */
	public CursoAlumno buscarAsignacionAlumnoPorAlumnoCurso(Alumno alumno, Curso curso) {
		for (int i = 0; i < this.asignacionesCA.size(); i++) {
			if (this.asignacionesCA.get(i).getCurso().equals(curso)
					&& this.asignacionesCA.get(i).getAlumno().equals(alumno)) {
				return this.asignacionesCA.get(i);
			}
		}
		return null;
	}

	/**
	 * Se encarga de inscribir el alumno a un curso
	 * 
	 * @param dni         --> identificador del alumno
	 * @param codigoCurso --> identificador del curso
	 * @return retorna un true si se pudo incribir, sino un false
	 */
	public Boolean inscribirAlumnoACurso(Integer dni, Integer codigoCurso, LocalDate fechaInscripcion) {
		Alumno alumno = buscarAlumnoPorDNI(dni);
		Curso curso = buscarCursoPorCodigo(codigoCurso);

		if (alumno == null || curso == null)
			return false;

		Boolean estaDentroDeLaFecha = verificarSiDentroDeLaFecha(fechaInscripcion, curso);
		if (estaDentroDeLaFecha != true)
			return false;

		Boolean resultado = verificarSiAproboCorreleativas(alumno.getDni(), curso.getMateria());
		if (resultado != true)
			return false;

		Boolean estaLleno = verificarSiAulaEstaLleno(curso);

		if (estaLleno)
			return false;

		CursoAlumno nueva = new CursoAlumno(codigoCurso, curso, alumno);
		this.asignacionesCA.add(nueva);
		return true;
	}

	/**
	 * Metodo que se encarga de eliminar las correleativas de una materia
	 * 
	 * @param codMateria      --> identificador de la materia
	 * @param codCorreleativa --> identificador de la materia correleativa
	 * @return retorna true si se elimino, sino false
	 */
	public Boolean eliminarCorreleativaMateria(Integer codMateria, Integer codCorreleativa) {
		Materia correleativa = buscarMateriaPorCodigo(codCorreleativa);
		Materia materia = buscarMateriaPorCodigo(codMateria);

		if (correleativa == null || materia == null)
			return false;

		Boolean resultado = materia.eliminarCorreleativa(correleativa);
		return resultado;
	}

	public CursoProfesor buscarAsignacionProfesorPorCursoProfe(Curso curso, Profesor profesor) {
		for (CursoProfesor cp : this.asignacionesCP) {
			if (cp.getCurso().equals(curso) && cp.getProfesor().equals(profesor))
				return cp;
		}
		return null;
	}

	public void asignarPlanDeEstudios(ArrayList<Materia> planDeEstudios) {
		this.planDeEstudios = planDeEstudios;
	}

	public ArrayList<Materia> obtenerPlanDeEstudios() {
		return planDeEstudios;
	}

	public ArrayList<Materia> obtenerMateriasQueFaltanCursarParaUnAlumno(Integer alumnoDni) {
		Alumno alumno = buscarAlumnoPorDNI(alumnoDni);
		ArrayList<Materia> aprobadasAlumno = alumno.getMateriasAprobadasPorAlumno();
		ArrayList<Materia> faltanCursar = new ArrayList<>();
		for (int i = 0; i < aprobadasAlumno.size(); i++) {
			for (int j = 0; j < this.planDeEstudios.size(); j++) {
				if (!this.planDeEstudios.get(j).equals(aprobadasAlumno.get(i))) {
					faltanCursar.add(planDeEstudios.get(j));
				}
			}
		}
		return faltanCursar;
	}

	public ArrayList<Materia> obtenerMateriasAprobadasParaUnAlumno(Integer alumnoDni) {
		Alumno aprobadasAlumno = buscarAlumnoPorDNI(alumnoDni);
		return aprobadasAlumno.getMateriasAprobadasPorAlumno();
	}

	public void agregarMateriaAprobada(Curso curso, Alumno alumno, Materia materia) {
		CursoAlumno aux = buscarAsignacionAlumnoPorAlumnoCurso(alumno, curso);
		AlumnoNotas notas = aux.getNotas();
		if ((notas.getPrimerParcial().getPuntaje() >= 7 || notas.getRecuperatorio().getPuntaje() >= 7)
				&& (notas.getSegundoParcial().getPuntaje() >= 7 || notas.getRecuperatorio().getPuntaje() >= 7)) {
			alumno.agregarMateriaAprobadaAlArray(materia);
		}
	}

	/* GETTERS */
	public ArrayList<CursoProfesor> getAsignacionesProfeCurso() {
		return this.asignacionesCP;
	}

	public String getNombre() {
		return this.nombreUniversidad;
	}

	public ArrayList<Materia> getMaterias() {
		return this.materias;
	}

	public ArrayList<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public ArrayList<Curso> getCursos() {
		return this.cursos;
	}

	public ArrayList<Profesor> getProfesores() {
		return this.profesores;
	}

	private Boolean verificarSiExisteCursoIdentico(Curso curso) {

		for (Curso c : this.cursos) {
			if (c.getMateria().getNombre().equals(curso.getMateria().getNombre())
					&& c.getCiclo().equals(curso.getCiclo())
					&& c.getHorario().getTurno().equals(curso.getHorario().getTurno())) {
				return true;
			}
		}
		return false;
	}

	private Aula buscarAulaPorCodigo(Integer codigo) {
		Aula aux = new Aula(codigo);
		for (int i = 0; i < this.aulas.size(); i++) {
			if (this.aulas.get(i).equals(aux))
				return this.aulas.get(i);
		}
		return null;
	}

	private Nota buscarNotaPorTipo(CursoAlumno ca, TipoNota tipoDeNota) {
		Nota aux = null;
		switch (tipoDeNota) {
		case PRIMER_PARCIAL:
			aux = ca.getNotas().getPrimerParcial();
			break;
		case SEGUNDO_PARCIAL:
			aux = ca.getNotas().getSegundoParcial();
			break;
		case RECUPERATORIO_PRIMER_PARCIAL:
		case RECUPERATORIO_SEGUNDO_PARCIAL:
			if (ca.getNotas().getRecuperatorio().getTipoNota().equals(tipoDeNota))
				aux = ca.getNotas().getRecuperatorio();
			break;
		}
		return aux;
	}

	private Boolean verificarSiAulaEstaOcupada(Aula aula, Curso curso) {
		ArrayList<Curso> cursosDelAula = buscarCursosDeUnAula(aula);
		// System.out.println(cursosDelAula.size());
		if (cursosDelAula.size() == 0)
			return false;

		Boolean resultado = laAulaEstaOcupada(curso, cursosDelAula);
		if (resultado)
			return true;

		return false;
	}

	private Boolean laAulaEstaOcupada(Curso curso, ArrayList<Curso> cursosDelAula) {
		for (Curso c : cursosDelAula) {
			if (c.getCiclo().equals(curso.getCiclo()) && estaOcupado(curso, c))
				return true;
		}
		return false;
	}

	private ArrayList<Curso> buscarCursosDeUnAula(Aula aula) {
		ArrayList<Curso> cursosAula = new ArrayList<>();
		for (Curso c : this.cursos) {
			if (c.getAula() != null && c.getAula().equals(aula)) {
				cursosAula.add(c);
			}
		}
		return cursosAula;
	}

	private Boolean verificarSiSeAsignaProfesorMasDeUnaVez(Profesor profesor, Curso curso) {
		CursoProfesor cp = buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		return cp != null;
	}

	private void agregarDocentesAComision(Profesor profesor, Curso curso) {
		CursoProfesor nuevo = new CursoProfesor(curso, profesor);
		this.asignacionesCP.add(nuevo);
	}

	/**
	 * Metodo que se encarga de verificar si falta profesores Por cada 20 alumnos se
	 * asigna un profesor
	 * 
	 * @param cantidadDeAlumnos
	 * @param curso
	 * @return retorna un true si se necesita un profesor, en caso contrario false
	 */
	private Boolean verificarSiSeNecesitaProfesor(Integer cantidadDeAlumnos, Curso curso) {
		Integer cantidadProfesores = calcularCantidadProfesoresPorCurso(curso);
		return (cantidadDeAlumnos > (cantidadProfesores * 20));
	}

	/**
	 * Metodo encargado de calcular la cantidad de profesores en un curso
	 * 
	 * @param curso
	 * @return retorna la cantidad de profesores
	 */
	private Integer calcularCantidadProfesoresPorCurso(Curso curso) {
		Integer cantidad = 0;
		for (CursoProfesor c : asignacionesCP) {
			if (c.getCurso().equals(curso))
				cantidad++;
		}
		return cantidad;
	}

	// BUSQUEDAS
	private Materia buscarMateriaPorCodigo(Integer codigo) {
		Materia aux = new Materia(codigo);
		for (int i = 0; i < this.materias.size(); i++) {
			if (this.materias.get(i).equals(aux)) {
				return this.materias.get(i);
			}
		}
		return null;
	}

	private Alumno buscarAlumnoPorDNI(Integer dni) {
		Alumno aux = new Alumno(dni);
		for (int i = 0; i < this.alumnos.size(); i++) {
			if (this.alumnos.get(i).equals(aux)) {
				return this.alumnos.get(i);
			}
		}
		return null;
	}

	private Curso buscarCursoPorCodigo(Integer codigo) {
		Curso aux = new Curso(codigo);
		for (int i = 0; i < this.cursos.size(); i++) {
			if (this.cursos.get(i).equals(aux)) {
				return this.cursos.get(i);
			}
		}
		return null;
	}

	private Profesor buscarProfesorPorDNI(Integer dni) {
		Profesor aux = new Profesor(dni);
		for (int i = 0; i < this.profesores.size(); i++) {
			if (this.profesores.get(i).equals(aux)) {
				return this.profesores.get(i);
			}
		}
		return null;
	}

	/**
	 * Metodo que verifica si el profesor tiene algun curso que ocupe el mismo
	 * horario del CURSO NUEVO a registrar
	 * 
	 * @param curso    --> curso nuevo
	 * @param profesor --> profesor a vincular dicho curso
	 * @return --> retorna si es posible que el profesor pueda dar el curso
	 */
	private Boolean verificarSiEstaOcupado(Curso curso, Profesor profesor) {
		ArrayList<Curso> cursosDelProfe = obtenerCursosPorProfesor(profesor.getDni());
		for (int i = 0; i < cursosDelProfe.size(); i++) {
			if (cursosDelProfe.get(i).getCiclo().equals(curso.getCiclo())
					&& estaOcupado(curso, cursosDelProfe.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodo que se encarga de evaluar si el horario de ambos cursos coinciden
	 * entre si
	 * 
	 * @param cursoARegistrar
	 * @param cursoAComparar
	 * @return si esta ocupado retorna true, sino false
	 */
	private Boolean estaOcupado(Curso cursoARegistrar, Curso cursoAComparar) {
		Horario horarioOcupado = cursoAComparar.getHorario();
		Horario horarioCursoNuevo = cursoARegistrar.getHorario();

		Dia aux = null;
		Turno turnoOcupado = horarioOcupado.getTurno(), turnoCursoNuevo = horarioCursoNuevo.getTurno();
		for (int i = 0; i < horarioOcupado.getDias().size(); i++) {
			aux = horarioOcupado.getDias().get(i);
			if (horarioCursoNuevo.getDias().contains(aux) && turnoCursoNuevo.equals(turnoOcupado))
				return true;
		}

		return false;
	}

	private ArrayList<Curso> obtenerCursosPorProfesor(Integer dni) {
		Profesor aux = new Profesor(dni);
		ArrayList<Curso> cursosSeleccionados = new ArrayList<>();
		for (int i = 0; i < this.asignacionesCP.size(); i++) {
			if (this.asignacionesCP.get(i).getProfesor().equals(aux)) {
				cursosSeleccionados.add(this.asignacionesCP.get(i).getCurso());
			}
		}
		return cursosSeleccionados;
	}

	private Boolean verificarSiDentroDeLaFecha(LocalDate fechaInscripcion, Curso curso) {
		LocalDate fechaInicioInscripcion = curso.getCiclo().getfechaInicioInscripcion();
		LocalDate fechaFinalizacionInscripcion = curso.getCiclo().getfechaFinalizacionInscripcion();

		return fechaInicioInscripcion.isBefore(fechaInscripcion)
				&& fechaFinalizacionInscripcion.isAfter(fechaInscripcion);
	}

	/**
	 * Se encarga de ver si el aula de dicho curso esta lleno
	 * 
	 * @param curs o
	 * @return retorna true si esta lleno y no si es false
	 */
	private Boolean verificarSiAulaEstaLleno(Curso curso) {
		Aula aula = curso.getAula();
		Integer cantidadAlumnos = calcularCantidadDeAlumnos(curso);
		return aula.getCapacidad() <= cantidadAlumnos;
	}

	private Integer calcularCantidadDeAlumnos(Curso curso) {
		Integer cantidad = 0;
		for (int i = 0; i < this.asignacionesCA.size(); i++) {
			if (this.asignacionesCA.get(i).getCurso().equals(curso)) {
				cantidad++;
			}
		}
		return cantidad;
	}

	/**
	 * Verifica si el alumno aprobo las correleativas de una materia
	 * 
	 * @param dni               --> identificador del alumno
	 * @param materiaAInscribir --> objeto de la materia a evaluar las correleativas
	 * @return retorna true si aprobo , sino false
	 */
	private Boolean verificarSiAproboCorreleativas(Integer dni, Materia materiaAInscribir) {
		ArrayList<Materia> correleativas = materiaAInscribir.getCorreleativas();
		if (correleativas.size() == 0) // En el caso de que la materia no tiene correleativas
			return true;

		for (int i = 0; i < correleativas.size(); i++)
			if (aprobo(correleativas.get(i), dni) != true) // Verifico hubo almenos una correleativa no aprobada
				return false;

		return true;
	}

	private boolean aprobo(Materia materia, Integer dni) {
		CursoAlumno asignacionAlumno = buscarAsignacionPorAlumnoMateria(materia, dni);

		if (asignacionAlumno == null) // Verifico si le falto cursar alguna correleativa
			return false;

		if (!(asignacionAlumno.estaCursando())) // Si no la Promociono o no la Aprobo
			return false;

		return true;
	}

	private Boolean existeSuperposicion(CicloLectivo c, CicloLectivo cicloNuevo) {
		return esCicloAnteriorAOtra(c, cicloNuevo) != true && esCicloDespuesAOtra(c, cicloNuevo) != true;
	}

	private Boolean esCicloAnteriorAOtra(CicloLectivo c, CicloLectivo cicloNuevo) {
		LocalDate inicioNuevo = cicloNuevo.getFechaInicioCicloLectivo();
		LocalDate finalNuevo = cicloNuevo.getfechaFinalizacionCicloLectivo();
		return (inicioNuevo.isBefore(c.getfechaFinalizacionCicloLectivo())
				&& inicioNuevo.isBefore(c.getFechaInicioCicloLectivo()))
				&& (finalNuevo.isBefore(c.getfechaFinalizacionCicloLectivo())
						&& finalNuevo.isBefore(c.getfechaInicioInscripcion()));
	}

	private Boolean esCicloDespuesAOtra(CicloLectivo c, CicloLectivo cicloNuevo) {
		LocalDate inicioNuevo = cicloNuevo.getFechaInicioCicloLectivo();
		LocalDate finalNuevo = cicloNuevo.getfechaFinalizacionCicloLectivo();
		return (inicioNuevo.isAfter(c.getfechaFinalizacionCicloLectivo())
				&& inicioNuevo.isAfter(c.getFechaInicioCicloLectivo()))
				&& (finalNuevo.isAfter(c.getfechaFinalizacionCicloLectivo())
						&& finalNuevo.isAfter(c.getfechaInicioInscripcion()));
	}

	private Boolean determinarSiExisteUnaSuperposicion(CicloLectivo ciclo) {
		for (CicloLectivo c : this.ciclos) {
			if (existeSuperposicion(c, ciclo))
				return true;
		}
		return false;
	}

	private CicloLectivo buscarCiclo(CicloLectivo ciclo) {
		for (CicloLectivo c : this.ciclos) {
			if (c.equals(ciclo))
				return c;
		}
		return null;
	}
	
	private Boolean debeUnaCorreleativaAFinal(CursoAlumno cursoAlumno, Nota nota) {
		ArrayList<Materia> correleativas = cursoAlumno.getCurso().getMateria().getCorreleativas();
		Integer dniAlumno = cursoAlumno.getAlumno().getDni();
		
		if(correleativas.size() == 0)
			return false;
		
		for(Materia mat : correleativas) {
			CursoAlumno ca = buscarAsignacionPorAlumnoMateria(mat, dniAlumno);
			if(ca.estaPromocionado()!=true)
					return true;
		}
		
		return false;
	}



}
