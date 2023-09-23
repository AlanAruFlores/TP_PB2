package ar.edu.unlam.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import ar.edu.unlam.dominio.*;
import ar.edu.unlam.utils.*;

@SuppressWarnings("deprecation")
public class TestUniversidad {

	@Test
	public void queSePuedaInstanciarUnaUniversidad() {
		String nombreUniversidad = "UNLAM";
		
		Universidad universidad = new Universidad(nombreUniversidad);
	
		assertNotNull(universidad);
		assertEquals(nombreUniversidad, universidad.getNombre());
	}
	
	@Test
	public void queSePuedaRegistrarMateria() {
		Materia materia = new Materia(2224, "Matematica General");
		
		String nombreUniversidad = "UNLAM";
		Universidad universidad = new Universidad(nombreUniversidad);
	
		Boolean resultado = universidad.registrarMateria(materia);
		
		Integer cantidadEsperada = 1;
		
		assertEquals(true,resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getMaterias().size());
	}
	
	
	
	@Test
	public void queNoSePuedaRegistrarMateriaConMismoCodigo() {
		Materia materia = new Materia(2224, "Matematica General");
		Materia otraMateria = new Materia(2224, "Seminario");
	
		String nombreUniversidad = "UNLAM";
		Universidad universidad = new Universidad(nombreUniversidad);
	
		universidad.registrarMateria(materia);
		Boolean resultado = universidad.registrarMateria(otraMateria);
		
		Integer cantidadEsperada = 1;
		assertEquals(false,resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getMaterias().size());
	}
	
	/*Tests con correleativas*/
	@Test
	public void queSePuedaAsignarCorreleativasAMateria() {
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");
		
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);
		
		Integer codMateria = 2222;
		Integer codCorreleativa = 2232;
		Boolean resultado = universidad.asignarAMateriaCorreleativa(codMateria, codCorreleativa);
	
		assertEquals(true, resultado);
		assertEquals(1, pb2.getCorreleativas().size());
	}
	
	@Test
	public void queNoSePuedaAsignarCorreleativasAMateriaSiNoExiste() {
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");
		
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);
		
		Integer codMateria = 2222;
		Integer codCorreleativa = 4444;
		Boolean resultado = universidad.asignarAMateriaCorreleativa(codMateria, codCorreleativa);
	
		assertEquals(false, resultado);
		assertEquals(0, pb2.getCorreleativas().size());		
	}
	
	@Test
	public void queSeElimineUnaCorreleativaDeMateria() {
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");
		
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);
		
		Integer codMateria = 2222;
		Integer codCorreleativa = 2232;
		
		universidad.asignarAMateriaCorreleativa(codMateria, codCorreleativa);
		assertEquals(1, pb2.getCorreleativas().size());
		
		Boolean resultado = universidad.eliminarCorreleativaMateria(codMateria, codCorreleativa);
		assertEquals(true, resultado);
		assertEquals(0, pb2.getCorreleativas().size());
		
	}
	
	@Test
	public void queNoSeElimineUnaCorreleativaDeMateriaSiNoExisteCodigo() {
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");
		
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);
		
		
		universidad.asignarAMateriaCorreleativa(2222, 2232);
		assertEquals(1, pb2.getCorreleativas().size());
		
		Boolean resultado = universidad.eliminarCorreleativaMateria(2222, 444);
		assertEquals(false, resultado);
		assertEquals(1, pb2.getCorreleativas().size());		
	}
	
	@Test
	public void queNoSeElimineUnaCorreleativaSiMateriaNoLaContiene() {
			Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
			Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");
			
			Universidad universidad = new Universidad("UNLAM");
			universidad.registrarMateria(pb1);
			universidad.registrarMateria(pb2);
			
			Integer codMateria = 2222;
			Integer codCorreleativa = 2232;
			
			//universidad.asignarAMateriaCorreleativa(codMateria, codCorreleativa);
			Boolean resultado = universidad.eliminarCorreleativaMateria(codMateria, codCorreleativa);
			assertEquals(false, resultado);
			assertEquals(0, pb2.getCorreleativas().size());
	}
	
	
	/*Tests con Alumno*/
	@Test
	public void queSePuedaRegistrarAlumno() {
		Alumno alumno = new Alumno(453232,"Alan", "Aruquipa",
				new Date(2004,1,1),new Date(2023,2,1));
		Alumno alumno2 = new Alumno(442323,"Eze", "Carrera",
				new Date(2004,1,1),new Date(2023,2,1));	
		
		String nombreUniversidad = "UNLAM";
		Universidad universidad = new Universidad(nombreUniversidad);
		
		universidad.registrarAlumno(alumno);
		Boolean resultado = universidad.registrarAlumno(alumno2);
		Integer cantidadEsperada = 2;
		assertEquals(true, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getAlumnos().size());
	}
	
	@Test
	public void queNoSePuedaRegistrarAlumnosConMismoDNI() {
		Alumno alumno = new Alumno(453232,"Alan", "Aruquipa",
				new Date(2004,1,1),new Date(2023,2,1));
		
		Alumno alumno2 = new Alumno(453232,"Eze", "Carrera",
				new Date(2004,1,1),new Date(2023,2,1));
		
		String nombreUniversidad = "UNLAM";
		Universidad universidad = new Universidad(nombreUniversidad);
		
		universidad.registrarAlumno(alumno);
		Boolean resultado = universidad.registrarAlumno(alumno2);
		Integer cantidadEsperada = 1;
		assertEquals(false, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getAlumnos().size());
	}
	
	
	/*Tests con Profesors*/
	@Test
	public void queSePuedaRegistrarProfesor() {
		Profesor profesor  = new Profesor(4432323,"Andres","Borgeat");

		Universidad universidad = new Universidad("UNLAM");
		
		Boolean resultado = universidad.registrarProfesor(profesor);
		
		Integer cantidadEsperada = 1;
		assertEquals(true, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getProfesores().size());
	}
	
	@Test
	public void queNoSePuedaRegistrarProfesorConMismoDNI() {
		Profesor profesor  = new Profesor(4432323,"Andres","Borgeat");
		Profesor profesor2  = new Profesor(4432323,"Juan","Monteagudo");

		Universidad universidad = new Universidad("UNLAM");

		universidad.registrarProfesor(profesor);
		Boolean resultado = universidad.registrarProfesor(profesor2);
		Integer cantidadEsperada = 1;
		assertEquals(false,resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getProfesores().size());
	}
	
	
	/*Test con cursos*/
	@Test
	public void queSePuedaAgregarCurso() {
		Integer codigoCurso = 2424;
		//Materia Correleativa
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");

		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		//AULA
		Aula aula = new Aula(215,84);
		
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//CURSO
		Curso curso = new Curso(codigoCurso,pb2,horario,ciclo);
	
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);
		universidad.asignarAMateriaCorreleativa(2232, 2222);
		Boolean resultado = universidad.crearCurso(curso);
				
		Integer cantidadEsperada = 1;
		assertTrue(resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getCursos().size());
	}
	
	@Test
	public void queNoSePuedaAgregarCursoSiTieneMismoCodigo() {
		//Materia Correleativa
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");

		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		Aula aula = new Aula(215,84);
		
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		//CURSO
		Curso curso = new Curso(2424,pb2,horario,ciclo);
		Curso otroCurso = new Curso(2424,pb1,horario,ciclo);

		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb2);
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aula);
		
		universidad.crearCurso(curso);
		universidad.asignarAulaAlCurso(215, 2424);

		Boolean resultado = universidad.crearCurso(otroCurso);
		
		Integer cantidadEsperada = 1;
		assertEquals(false, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getCursos().size());
	}
	
	@Test
	public void queNoSePuedaAgregarCursoSiSonLoMismo() {
		//Materia
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		Aula aula = new Aula(215,84);
		
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		//CURSO
		Curso curso = new Curso(2424,pb1,horario,ciclo);
		Curso otroCurso = new Curso(2323,pb1,horario,ciclo);

		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aula);
		
		universidad.crearCurso(curso);
		universidad.asignarAulaAlCurso(215, 2424);

		Boolean resultado = universidad.crearCurso(otroCurso);
		
		Integer cantidadEsperada = 1;
		assertEquals(false, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getCursos().size());
	}

	
	//Asignar Aula a Curso
	@Test
	public void queSePuedaAsignarAulaAUnCurso() {
		//Materia Correleativa
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");

		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		//AULA
		Aula aula = new Aula(215,84);
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		//CURSO
		Curso curso = new Curso(2424,pb2,horario,ciclo);
	
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);
		universidad.registrarAula(aula);
		universidad.asignarAMateriaCorreleativa(2232, 2222);
		universidad.crearCurso(curso);
		
		//Asignamos una aula a un curso
		Integer codAula = 215; 
		Integer codCurso = 2424;
		Boolean resultado  = universidad.asignarAulaAlCurso(codAula, codCurso);
				
		assertEquals(true, resultado);
		assertEquals(aula, curso.getAula());
	}
	
	@Test
	public void queSePuedaAsignarAulaAUnCursoSiTieneDistintoCiclo() {
		//Materia Correleativa
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");

		//SEGUNDO CUATRIMESTRE 2023
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		//SEGUNDO CUATRIMESTRE 2022
		Date fechaInicioCicloLectivo2 = new Date(2022,3,1);	
		Date fechaFinalizacionCicloLectivo2 = new Date(2022,6,14);
		Date fechaInicioInscripcion2 = new Date(2022,2,1);
		Date fechaFinalizacionInscripcion2 = new Date(2022,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2,fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);
		//AULA
		Aula aula = new Aula(215,84);
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		//CURSO
		Curso cursoPB1 = new Curso(1212,pb1,horario,ciclo);
		Curso cursoPB2 = new Curso(2424,pb2,horario,ciclo2);
	
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);
		universidad.registrarAula(aula);
		universidad.asignarAMateriaCorreleativa(2232, 2222);
		universidad.crearCurso(cursoPB1);
		universidad.crearCurso(cursoPB2);
		
		//Asignamos una aula a un curso
		Integer codAula = 215; 
		universidad.asignarAulaAlCurso(codAula, 1212);
		Boolean resultado  = universidad.asignarAulaAlCurso(codAula, 2424);
				
		assertEquals(aula, cursoPB1.getAula());
		assertEquals(aula, cursoPB2.getAula());
		assertEquals(true, resultado);
	}	
	
	@Test
	public void queSePuedaAsignarAulaAUnCursoSiTieneDistintoDias() {
		//Materia Correleativa
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");

		//SEGUNDO CUATRIMESTRE 2023
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//AULA
		Aula aula = new Aula(215,84);
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		ArrayList<Dia> diasCursada2 = new ArrayList<>();
		diasCursada2.add(Dia.MIERCOLES);
		diasCursada2.add(Dia.VIERNES);
		
		//Horario
		Horario horarioPB1 = new Horario(diasCursada,Turno.MAÑANA);
		Horario horarioPB2 = new Horario(diasCursada2, Turno.MAÑANA);
		//CURSO
		Curso cursoPB1 = new Curso(1212,pb1,horarioPB1,ciclo);
		Curso cursoPB2 = new Curso(2424,pb2,horarioPB2,ciclo);
	
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);
		universidad.registrarAula(aula);
		universidad.asignarAMateriaCorreleativa(2232, 2222);
		universidad.crearCurso(cursoPB1);
		universidad.crearCurso(cursoPB2);
		
		//Asignamos una aula a un curso
		Integer codAula = 215; 
		universidad.asignarAulaAlCurso(codAula, 1212);
		Boolean resultado  = universidad.asignarAulaAlCurso(codAula, 2424);
			
		assertEquals(true, resultado);
		assertEquals(aula, cursoPB1.getAula());
		assertEquals(aula, cursoPB2.getAula());
	}	
	
	@Test
	public void queSePuedaAsignarAulaAUnCursoSiTieneDistintoTurnos() {
		//Materia Correleativa
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");

		//SEGUNDO CUATRIMESTRE 2023
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//AULA
		Aula aula = new Aula(215,84);
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		//Horario
		Horario horarioPB1 = new Horario(diasCursada,Turno.MAÑANA);
		Horario horarioPB2 = new Horario(diasCursada, Turno.NOCHE);
		//CURSO
		Curso cursoPB1 = new Curso(1212,pb1,horarioPB1,ciclo);
		Curso cursoPB2 = new Curso(2424,pb2,horarioPB2,ciclo);
	
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);
		universidad.registrarAula(aula);
		universidad.asignarAMateriaCorreleativa(2232, 2222);
		universidad.crearCurso(cursoPB1);
		universidad.crearCurso(cursoPB2);
		
		//Asignamos una aula a un curso
		Integer codAula = 215; 
		universidad.asignarAulaAlCurso(codAula, 1212);
		Boolean resultado  = universidad.asignarAulaAlCurso(codAula, 2424);
			
		assertEquals(true, resultado);
		assertEquals(aula, cursoPB1.getAula());
		assertEquals(aula, cursoPB2.getAula());
	}	
	
	@Test
	public void queNoSePuedaAsignarAulaAUnCursoSiElHorarioEstaOcupado() {
		//Materia Correleativa
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");

		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		//AULA
		Aula aula = new Aula(215,84);
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		//CURSO
		Curso cursoPB1 = new Curso(1212,pb1,horario,ciclo);
		Curso cursoPB2 = new Curso(2424,pb2,horario,ciclo);
	
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);
		universidad.registrarAula(aula);
		universidad.asignarAMateriaCorreleativa(2232, 2222);
		universidad.crearCurso(cursoPB1);
		universidad.crearCurso(cursoPB2);
		
		//Asignamos una aula a un curso
		Integer codAula = 215; 
		universidad.asignarAulaAlCurso(codAula, 1212);
		Boolean resultado  = universidad.asignarAulaAlCurso(codAula, 2424);
				
		assertEquals(aula, cursoPB1.getAula());
		assertEquals(false, resultado);
	}	

	@Test
	public void queNoSePuedaAsignarAulaAUnCursoSiExisteUnDiaDeCursadaOcupado() {
		//Materia Correleativa
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");

		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		//AULA
		Aula aula = new Aula(215,84);
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		
		ArrayList<Dia> diasCursada2 = new ArrayList<>();
		diasCursada2.add(Dia.LUNES);
		diasCursada2.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		Horario horario2 = new Horario(diasCursada2,Turno.MAÑANA);

		//CURSO
		Curso cursoPB1 = new Curso(1212,pb1,horario,ciclo);
		Curso cursoPB2 = new Curso(2424,pb2,horario2,ciclo);
	
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);
		universidad.registrarAula(aula);
		universidad.asignarAMateriaCorreleativa(2232, 2222);
		universidad.crearCurso(cursoPB1);
		universidad.crearCurso(cursoPB2);
		
		//Asignamos una aula a un curso
		Integer codAula = 215; 
		universidad.asignarAulaAlCurso(codAula, 1212);
		Boolean resultado  = universidad.asignarAulaAlCurso(codAula, 2424);
		assertEquals(false, resultado);
		assertEquals(aula, cursoPB1.getAula());
		assertNull(cursoPB2.getAula());
	}
	
	/*Test de Asignacion a alumnos*/	
	
	@Test	
	public void queSePuedaInscribirAlumnoACursoSinCorreleativa() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Materia PB1
		Materia pb1  = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111,100);
		Curso cursoPB1  = new Curso(2323,pb1,horario,ciclo);

		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
		
		universidad.registrarAlumno(alumno);
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aulaPB1);
		universidad.crearCurso(cursoPB1);
		universidad.asignarAulaAlCurso(111, 2323);
		
		Date fechaInscripcion = new Date(2023,2,2);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);
		
		assertEquals(true,resultado);
		
	}
	
	@Test	
	public void queSePuedaInscribirAlumnoACursoSinCorreleativaSiNoEstaDentroDeLaFechaDeInscripcion() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Materia PB1
		Materia pb1  = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111,100);
		Curso cursoPB1  = new Curso(2323,pb1,horario,ciclo);

		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
		
		universidad.registrarAlumno(alumno);
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aulaPB1);
		universidad.crearCurso(cursoPB1);
		universidad.asignarAulaAlCurso(111, 2323);
		
		Date fechaInscripcion = new Date(2022,3,2);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);
		
		assertEquals(false,resultado);
		
		
	}
	

	
	/*Test Asignacion de Notas En Materia sin correleativas*/
	@Test 
	public void queSePuedaAsignarUnaNotaAlumnoSiEstaEntre1Y10() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		//Fecha de inicio y finalizacion inscripcion
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Materia PB1
		Materia pb1  = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111,100);
		Curso cursoPB1  = new Curso(2323,pb1,horario,ciclo);

		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
				
		
		universidad.registrarAlumno(alumno);
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aulaPB1);
		universidad.crearCurso(cursoPB1);
		universidad.asignarAulaAlCurso(111, 2323);
		
		Date fechaInscripcion = new Date(2023,2,2);		
		universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);
		
		Integer dniAlumno = 453232;
		Integer codCurso  = 2323;
		
		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL,8);

		Boolean resultado = universidad.registrarNota(dniAlumno, codCurso, primerParcial);
		
		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(
			new Alumno(dniAlumno), new Curso(codCurso));
		
		Integer notaEsperada = 8;
		
		assertEquals(true, resultado);
		
		assertEquals(notaEsperada.intValue(), 
		  cursoAlumno.getNotas().getPrimerParcial().getPuntaje().intValue());
		
		
	}
	@Test 
	public void queNoSePuedaAsignarUnaNotaAlumnoSiNoEstaEntre1Y10() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		/*Fecha de inicio y finalizacion*/
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Materia PB1
		Materia pb1  = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111,100);
		Curso cursoPB1  = new Curso(2323,pb1,horario,ciclo);

		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
				
		
		universidad.registrarAlumno(alumno);
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aulaPB1);
		universidad.crearCurso(cursoPB1);
		universidad.asignarAulaAlCurso(111, 2323);
		
		Date fechaInscripcion = new Date(2023,2,24);
		universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);
		
		Integer dniAlumno = 453232;
		Integer codCurso  = 2323;
		
		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL,11);
		
		Boolean resultado = universidad.registrarNota(dniAlumno, codCurso, primerParcial);

		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(
			new Alumno(dniAlumno), new Curso(codCurso));
		
		Integer notaEsperada = 0;

		assertEquals(false, resultado);
		assertEquals(notaEsperada.intValue(), 
		  cursoAlumno.getNotas().getPrimerParcial().getPuntaje().intValue());
		
		
	}
	
	@Test
	public void queSeAsigneNotasParcialesRecuperatorios() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Materia PB1
		Materia pb1  = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111,100);
		Curso cursoPB1  = new Curso(2323,pb1,horario,ciclo);

		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
				
		
		universidad.registrarAlumno(alumno);
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aulaPB1);
		universidad.crearCurso(cursoPB1);
		universidad.asignarAulaAlCurso(111, 2323);
		
		Date fechaInscripcion = new Date(2023,2,24);
		universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);
		
		Integer dniAlumno = 453232;
		Integer codCurso  = 2323;
		
		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL,8);
		Nota segundoParcial = new Nota(TipoNota.SEGUNDO_PARCIAL,6);
		Nota recuperatorio2doParcial = new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL,7);
		
		
		universidad.registrarNota(dniAlumno, codCurso, primerParcial);
		universidad.registrarNota(dniAlumno, codCurso, segundoParcial);
		universidad.registrarNota(dniAlumno, codCurso, recuperatorio2doParcial);
		
		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(
			new Alumno(dniAlumno), new Curso(codCurso));
		
		//Notas Esperadas
		Integer primeraNota = 8;
		Integer segundaNota  = 6;
		Integer recuperatorioNota = 7;
		
		assertEquals(primeraNota.intValue(), 
		  cursoAlumno.getNotas().getPrimerParcial().getPuntaje().intValue());
		assertEquals(segundaNota.intValue(), 
		  cursoAlumno.getNotas().getSegundoParcial().getPuntaje().intValue());
		assertEquals(recuperatorioNota.intValue(), 
		  cursoAlumno.getNotas().getRecuperatorio().getPuntaje().intValue());			
	}
	
	
	@Test
	public void queNoSeAsigneNotaRecuperatorioSiPromociono() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
	
		//Fecha de inicio y finalizacion de inscripcion 
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Materia PB1
		Materia pb1  = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111,100);
		Curso cursoPB1  = new Curso(2323,pb1,horario,ciclo);

		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
				
		
		universidad.registrarAlumno(alumno);
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aulaPB1);
		universidad.crearCurso(cursoPB1);
		universidad.asignarAulaAlCurso(111, 2323);
		
		Date fechaInscripcion = new Date(2023,2,24);
		universidad.inscribirAlumnoACurso(453232, 2323,fechaInscripcion);
		
		Integer dniAlumno = 453232;
		Integer codCurso  = 2323;
		
		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL,8);
		Nota segundoParcial = new Nota(TipoNota.SEGUNDO_PARCIAL,10);
		Nota recuperatorio2doParcial = new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL,7);
		
		
		universidad.registrarNota(dniAlumno, codCurso, primerParcial);
		universidad.registrarNota(dniAlumno, codCurso, segundoParcial);
		Boolean resultado = universidad.registrarNota(dniAlumno, codCurso, recuperatorio2doParcial);
		
		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(
			new Alumno(dniAlumno), new Curso(codCurso));
		
		Integer primeraNota = 8;
		Integer segundaNota  = 10;
		//Integer recuperatorioNota = 0;
		
		assertEquals(false, resultado);
		assertEquals(primeraNota.intValue(), 
		  cursoAlumno.getNotas().getPrimerParcial().getPuntaje().intValue());
		assertEquals(segundaNota.intValue(), 
		  cursoAlumno.getNotas().getSegundoParcial().getPuntaje().intValue());
		
		//Verifico que no se haya asignado el recuperatorio ya que promociono
		assertNull(cursoAlumno.getNotas().getRecuperatorio());			
	}
	
	@Test
	public void queNoSeAsigneMuchasVecesLaNotaDeUnDeterminadoParcial() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Materia PB1
		Materia pb1  = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111,100);
		Curso cursoPB1  = new Curso(2323,pb1,horario,ciclo);

		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
				
		
		universidad.registrarAlumno(alumno);
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aulaPB1);
		universidad.crearCurso(cursoPB1);
		universidad.asignarAulaAlCurso(111, 2323);
		
		Date fechaInscripcion = new Date(2023,2,24);
		universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);
		
		Integer dniAlumno = 453232;
		Integer codCurso  = 2323;
		
		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL,8);
		Nota primerParcial2 = new Nota(TipoNota.PRIMER_PARCIAL,10);
		
		universidad.registrarNota(dniAlumno, codCurso, primerParcial);
		Boolean resultado  = universidad.registrarNota(dniAlumno, codCurso, primerParcial2);
		
		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(
			new Alumno(dniAlumno), new Curso(codCurso));
		
		
		Integer notaEsperada = 8;
		assertEquals(false, resultado);
		assertEquals(notaEsperada.intValue(), 
		  cursoAlumno.getNotas().getPrimerParcial().getPuntaje().intValue());
		
	}
	
	/*Test Asignacion alumnos con correleativas*/
	
	@Test
	public void queSePuedaAlumnoInscribirAUnaMateriaCorreleativaSiPromocionoAmbosParciales() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo de Ingles I*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion 
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		/*Ciclo lectivo de Ingles II*/
		Date fechaInicioCicloLectivo2  = new Date(2024,3,1);	
		Date fechaFinalizacionCicloLectivo2 = new Date(2024,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion 
		Date fechaInicioInscripcion2 = new Date(2024,2,1);
		Date fechaFinalizacionInscripcion2 = new Date(2024,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
	
		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada2.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2,Turno.NOCHE);
		
		//Materia INGLES 1
		Materia ingles1  = new Materia(1234, "INGLES TECNICO 1");
		Aula aulaIngles1 = new Aula(90,30);
		Curso cursoIng1 = new Curso(2323,ingles1,horario,ciclo);

		//Materia INGLES 2
		Materia ingles2 = new Materia(2468, "INGLES TECNICO 2");
		Aula aulaIngles2 = new Aula(80,30);
		Curso cursoIng2 = new Curso(4646,ingles2, horario2,ciclo2);
		
		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
		
		universidad.registrarAlumno(alumno);
		universidad.registrarMateria(ingles1);
		universidad.registrarAula(aulaIngles1);
		universidad.crearCurso(cursoIng1);
		
		universidad.registrarMateria(ingles2);
		universidad.registrarAula(aulaIngles2);
		universidad.crearCurso(cursoIng2);
		
		universidad.asignarAulaAlCurso(90, 2323);
		universidad.asignarAulaAlCurso(80, 4646);

		universidad.asignarAMateriaCorreleativa(2468, 1234);
		
		Date fechaInscripcion = new Date(2023,2,24);
		universidad.inscribirAlumnoACurso(453232, 2323,fechaInscripcion);
		//Registrando notas de ingles 1
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.PRIMER_PARCIAL,8));
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.SEGUNDO_PARCIAL,8));

		//Inscribiendose al siguiente nivel (ingles 2)
		Date fechaInscripcion2 = new Date(2024,2,18);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 4646,fechaInscripcion2);
		
		assertEquals(true,resultado);
	}
	
	@Test
	public void queSePuedaAlumnoInscribirAUnaMateriaCorreleativaSiPromocionoParcialRecuperatorio() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo de Ingles I*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion 
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		/*Ciclo lectivo de Ingles II*/
		Date fechaInicioCicloLectivo2  = new Date(2024,3,1);	
		Date fechaFinalizacionCicloLectivo2 = new Date(2024,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion 
		Date fechaInicioInscripcion2 = new Date(2024,2,1);
		Date fechaFinalizacionInscripcion2 = new Date(2024,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
	
		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada2.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2,Turno.NOCHE);
		
		//Materia INGLES 1
		Materia ingles1  = new Materia(1234, "INGLES TECNICO 1");
		Aula aulaIngles1 = new Aula(90,30);
		Curso cursoIng1 = new Curso(2323,ingles1,horario,ciclo);

		//Materia INGLES 2
		Materia ingles2 = new Materia(2468, "INGLES TECNICO 2");
		Aula aulaIngles2 = new Aula(80,30);
		Curso cursoIng2 = new Curso(4646,ingles2,horario2,ciclo2);
		
		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
		
		universidad.registrarAlumno(alumno);
		universidad.registrarMateria(ingles1);
		universidad.registrarAula(aulaIngles1);
		universidad.crearCurso(cursoIng1);
		
		universidad.registrarMateria(ingles2);
		universidad.registrarAula(aulaIngles2);
		universidad.crearCurso(cursoIng2);
		
		universidad.asignarAulaAlCurso(90, 2323);
		universidad.asignarAulaAlCurso(80, 4646);
		
		universidad.asignarAMateriaCorreleativa(2468, 1234);
		
		Date fechaInscripcion = new Date(2023,2,24);		
		universidad.inscribirAlumnoACurso(453232, 2323,fechaInscripcion);
		//Registrando notas de ingles 1
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.PRIMER_PARCIAL,8));
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.SEGUNDO_PARCIAL,4));
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL,5));

		//Inscribiendose al siguiente nivel (ingles 2)
		Date fechaInscripcion2 = new Date(2024,2,24);		
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 4646,fechaInscripcion2);
		assertEquals(false,resultado);
	}
	
	@Test
	public void queSePuedaAlumnoInscribirAUnaMateriaCorreleativaInclusoSiNoLaPromociona() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo de Ingles I*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion 
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		/*Ciclo lectivo de Ingles II*/
		Date fechaInicioCicloLectivo2  = new Date(2024,3,1);	
		Date fechaFinalizacionCicloLectivo2 = new Date(2024,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion 
		Date fechaInicioInscripcion2 = new Date(2024,2,1);
		Date fechaFinalizacionInscripcion2 = new Date(2024,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
	
		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);
		
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2,Turno.NOCHE);
		
		//Materia INGLES 1
		Materia ingles1  = new Materia(1234, "INGLES TECNICO 1");
		Aula aulaIngles1 = new Aula(90,30);
		Curso cursoIng1 = new Curso(2323,ingles1,horario,ciclo);

		//Materia INGLES 2
		Materia ingles2 = new Materia(2468, "INGLES TECNICO 2");
		Aula aulaIngles2 = new Aula(80,30);
		Curso cursoIng2 = new Curso(4646,ingles2,horario2,ciclo2);
		
		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
		
		universidad.registrarAlumno(alumno);
		
		universidad.registrarMateria(ingles1);
		universidad.registrarAula(aulaIngles1);
		universidad.crearCurso(cursoIng1);
		
		universidad.registrarMateria(ingles2);
		universidad.registrarAula(aulaIngles2);
		universidad.crearCurso(cursoIng2);
		
		universidad.asignarAulaAlCurso(90, 2323);
		universidad.asignarAulaAlCurso(80, 4646);		
		universidad.asignarAMateriaCorreleativa(2468, 1234);
		
		Date fechaInscripcion = new Date(2023,2,24);		
		universidad.inscribirAlumnoACurso(453232, 2323,fechaInscripcion);
		//Registrando notas de ingles 1
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.PRIMER_PARCIAL,4));
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.SEGUNDO_PARCIAL,7));
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.RECUPERATORIO_PRIMER_PARCIAL,5));

		//Inscribiendose al siguiente nivel (ingles 2)
		Date fechaInscripcion2 = new Date(2024,2,24);		
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 4646,fechaInscripcion2);
		assertEquals(true,resultado);
	}
	
	
	@Test
	public void queNoSePuedaAlumnoInscribirAUnaMateriaCorreleativaSiNoLaCurso() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo de Ingles I*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion 
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		/*Ciclo lectivo de Ingles II*/
		Date fechaInicioCicloLectivo2  = new Date(2024,3,1);	
		Date fechaFinalizacionCicloLectivo2 = new Date(2024,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion 
		Date fechaInicioInscripcion2 = new Date(2024,2,1);
		Date fechaFinalizacionInscripcion2 = new Date(2024,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
	
		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2,Turno.NOCHE);
		
		//Materia INGLES 1
		Materia ingles1  = new Materia(1234, "INGLES TECNICO 1");
		Aula aulaIngles1 = new Aula(90,30);
		Curso cursoIng1 = new Curso(2323,ingles1,horario,ciclo);

		//Materia INGLES 2
		Materia ingles2 = new Materia(2468, "INGLES TECNICO 2");
		Aula aulaIngles2 = new Aula(80,30);
		Curso cursoIng2 = new Curso(4646,ingles2,horario2,ciclo2);
		
		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
		
		universidad.registrarAlumno(alumno);
		
		universidad.registrarMateria(ingles1);
		universidad.registrarAula(aulaIngles1);
		universidad.crearCurso(cursoIng1);
		
		universidad.registrarMateria(ingles2);
		universidad.registrarAula(aulaIngles2);
		universidad.crearCurso(cursoIng2);

		universidad.asignarAulaAlCurso(90, 2323);
		universidad.asignarAulaAlCurso(80, 4646);	
		universidad.asignarAMateriaCorreleativa(2468, 1234);
		
		/*Registrando notas de ingles 1
		Date fechaInscripcion = new Date(2023,2,24);		
		universidad.inscribirAlumnoACurso(453232, 2323,fechaInscripcion);
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.PRIMER_PARCIAL,8));
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.SEGUNDO_PARCIAL,4));
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL,7));
		 */
		
		//Inscribiendose al siguiente nivel (ingles 2)
		Date fechaInscripcion = new Date(2024,2,24);		
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 4646,fechaInscripcion);
		assertEquals(false,resultado);
	}
	
	@Test
	public void queNoSePuedaAlumnoInscribirAUnaMateriaCorreleativaSiDebeRecursarla() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo de Ingles I*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion 
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		/*Ciclo lectivo de Ingles II*/
		Date fechaInicioCicloLectivo2  = new Date(2024,3,1);	
		Date fechaFinalizacionCicloLectivo2 = new Date(2024,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion 
		Date fechaInicioInscripcion2 = new Date(2024,2,1);
		Date fechaFinalizacionInscripcion2 = new Date(2024,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
	
		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2,Turno.NOCHE);
		
		//Materia INGLES 1
		Materia ingles1  = new Materia(1234, "INGLES TECNICO 1");
		Aula aulaIngles1 = new Aula(90,30);
		Curso cursoIng1 = new Curso(2323,ingles1,horario,ciclo);

		//Materia INGLES 2
		Materia ingles2 = new Materia(2468, "INGLES TECNICO 2");
		Aula aulaIngles2 = new Aula(80,30);
		Curso cursoIng2 = new Curso(4646,ingles2,horario2,ciclo);
		
		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
		
		universidad.registrarAlumno(alumno);
		
		universidad.registrarMateria(ingles1);
		universidad.registrarAula(aulaIngles1);
		universidad.crearCurso(cursoIng1);
		
		universidad.registrarMateria(ingles2);
		universidad.registrarAula(aulaIngles2);
		universidad.crearCurso(cursoIng2);
		
		universidad.asignarAulaAlCurso(90, 2323);
		universidad.asignarAulaAlCurso(80, 4646);
		universidad.asignarAMateriaCorreleativa(2468, 1234);
		
		Date fechaInscripcion = new Date(2023,2,24);		
		universidad.inscribirAlumnoACurso(453232, 2323,fechaInscripcion);
		//Registrando notas de ingles 1
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.PRIMER_PARCIAL,2));
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.SEGUNDO_PARCIAL,3));
		 
		
		//Inscribiendose al siguiente nivel (ingles 2)
		Date fechaInscripcion2= new Date(2024,2,24);		
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 4646,fechaInscripcion2);
		assertEquals(false,resultado);
	}
	
	
	@Test
	public void queNoSePuedaAlumnoInscribirAUnaMateriaCorreleativaSiHuboAlMenosUnaQueDebeRecursar() {
		Universidad universidad = new Universidad("UNLAM");

		/*Ciclo lectivo PB1 Y INF GENERAL*/
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		
		//Fecha de inicio y finalizacion de inscripcion
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		/*Ciclo lectivo PW1*/
		Date fechaInicioCicloLectivo2  = new Date(2023,6,1);	
		Date fechaFinalizacionCicloLectivo2 = new Date(2023,11,14);
		
		//Fecha de inicio y finalizacion de inscripcion
		Date fechaInicioInscripcion2 = new Date(2023,5,1);
		Date fechaFinalizacionInscripcion2 = new Date(2023,5,30);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2,Turno.NOCHE);
		
		//Horario3
		ArrayList<Dia> diasCursada3 = new ArrayList<Dia>();
		diasCursada.add(Dia.VIERNES);
		Horario horario3 = new Horario(diasCursada3,Turno.NOCHE);
		
		//Materia PB1
		Materia pb1  = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulapb1 = new Aula(90,30);
		Curso cursoPb1 = new Curso(1010,pb1,horario,ciclo);

		//Materia INF.GENERAL 
		Materia infGeneral = new Materia(2222, "INFORMATICA GENERAL");
		Aula aulainf = new Aula(80,30);
		Curso cursoInf = new Curso(2020,infGeneral,horario2,ciclo);
		
		//Materia Programacion Web 1
		Materia pw1 = new Materia(3333,"Programacion Web 1");
		Aula aulapw1 = new Aula(200,80);
		Curso cursopw = new Curso(3030,pw1,horario3,ciclo);
		
		//Alumno
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232,"Alan","Aruquipa",fechaNacimiento,fechaIngreso);
		
		universidad.registrarAlumno(alumno);
		
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aulapb1);
		universidad.crearCurso(cursoPb1);
		
		universidad.registrarMateria(infGeneral);
		universidad.registrarAula(aulainf);
		universidad.crearCurso(cursoInf);
		
		//Registrar programacion web 1 
		universidad.registrarMateria(pw1);
		universidad.registrarAula(aulapw1);
		universidad.asignarAMateriaCorreleativa(3333, 1111);
		universidad.asignarAMateriaCorreleativa(3333, 2222);
		universidad.crearCurso(cursopw);
				
		universidad.asignarAulaAlCurso(90, 1010);
		universidad.asignarAulaAlCurso(80, 2020);
		universidad.asignarAulaAlCurso(200, 3030);

		//Registrando notas de PB1 y InfGeneral
		Date fechaInscripcion = new Date(2023,2,24);
		universidad.inscribirAlumnoACurso(453232, 1010,fechaInscripcion);
		universidad.inscribirAlumnoACurso(453232, 2020, fechaInscripcion);
		universidad.registrarNota(453232, 1010, new Nota(TipoNota.PRIMER_PARCIAL,3));
		universidad.registrarNota(453232, 1010, new Nota(TipoNota.SEGUNDO_PARCIAL,4));
		 
		universidad.registrarNota(453232, 2020, new Nota(TipoNota.PRIMER_PARCIAL,8));
		universidad.registrarNota(453232, 2020, new Nota(TipoNota.SEGUNDO_PARCIAL,7));
		
		//Inscribiendose a PW1
		Date fechaInscripcion2 = new Date(2023,5,24);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 3030,fechaInscripcion2);
		assertEquals(false,resultado);
	}
	
	
	
	/*Tests con profesor: 	
	 *	No se puede asignar un profesor para dos cursos en el mismo horario 
	 *	y ciclo lectivo (1er cuatri,2do cuatri, fecha de inicialización). 
	 */
	@Test
	public void queSeAsigneUnProfeACurso() {
		
		Materia pb1 = new Materia(2232,"PROqueSeAsigneUnProfeACursoGRAMACION BASICA 1");
		Materia infGeneral = new Materia(1111,"INFORMATICA GENERAL");
		
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		Aula aula = new Aula(215,84);
		
		
		//HORARIO 1
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		//HORARIO 2
		ArrayList<Dia> diasCursada2 = new ArrayList<>();
		diasCursada.add(Dia.MARTES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		Horario horario2 = new Horario(diasCursada2,Turno.NOCHE);

		//CURSO
		Curso curso = new Curso(2424,pb1,horario2,ciclo);
		Curso otroCurso = new Curso(4242, infGeneral,horario,ciclo);
		//PROFESOR
		Profesor profesor  = new Profesor(4432323,"Andres","Borgeat");
		//ALUMNO 
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa",fechaNacimiento, fechaIngreso);
		
		
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(infGeneral);
		universidad.registrarProfesor(profesor);
		universidad.registrarAula(aula);
		universidad.registrarAlumno(alumno);
		
		universidad.crearCurso(curso);
		universidad.crearCurso(otroCurso);
		
		universidad.asignarAulaAlCurso(215, 2424);
		universidad.asignarAulaAlCurso(215, 4242);

		//Agrego el alumno en los cursos ya que sino el profe no podra registrarse
		Date fechaInscripcion  = new Date(2023,2,24);
		universidad.inscribirAlumnoACurso(453232, 2424,fechaInscripcion);
		universidad.inscribirAlumnoACurso(453232, 4242,fechaInscripcion);

		Integer dniProfesor  = 4432323;
		Integer codigo = 2424;
		universidad.agregarProfesorACurso(dniProfesor, codigo);
		codigo = 4242;
		Boolean resultado = universidad.agregarProfesorACurso(dniProfesor, 4242);	
		assertEquals(true,resultado);
		
		CursoProfesor cursoProfe = universidad.buscarAsignacionProfesorPorCursoProfe(curso,profesor);
		assertNotNull(cursoProfe);
		assertEquals(curso, cursoProfe.getCurso());
		assertEquals(profesor, cursoProfe.getProfesor());
	}
	
	@Test
	public void queSeAsigneUnProfeACursoSiEsDisntintoElCicloLectivo() {
		Materia pb1 = new Materia(2232,"PROGRAMACION BASICA 1");
		Materia infGeneral = new Materia(1111,"INFORMATICA GENERAL");
		
		//1 CUATRIMESTRE 2023
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		//1 CUATRIMESTRE 2022
		Date fechaInicioCicloLectivo2  = new Date(2022,3,1);	
		Date fechaFinalizacionCicloLectivo2 = new Date(2022,6,14);
		Date fechaInicioInscripcion2 = new Date(2022,2,1);
		Date fechaFinalizacionInscripcion2 = new Date(2022,2,28);
		
		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);
		
		//AULA
		Aula aula = new Aula(215,84);
		
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);

		//CURSO
		Curso curso = new Curso(2424,pb1,horario,ciclo);
		Curso otroCurso = new Curso(4242, infGeneral,horario,ciclo2);
		//PROFESOR
		Profesor profesor  = new Profesor(4432323,"Andres","Borgeat");
		//ALUMNO 
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa",fechaNacimiento, fechaIngreso);
		
		
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(infGeneral);
		universidad.registrarProfesor(profesor);
		universidad.registrarAlumno(alumno);
		universidad.registrarAula(aula);
		universidad.crearCurso(curso);
		universidad.crearCurso(otroCurso);
		
		universidad.asignarAulaAlCurso(215, 2424);
		universidad.asignarAulaAlCurso(215, 4242);


		//Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los cursos
		//Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		Date fechaInscripcion2  = new Date(2022,2,10);
		universidad.inscribirAlumnoACurso(453232, 4242,fechaInscripcion2);
		
		Date fechaInscripcion  = new Date(2023,2,24);
		universidad.inscribirAlumnoACurso(453232, 2424,fechaInscripcion);

		
		Integer dniProfesor  = 4432323;
		Boolean resultado =universidad.agregarProfesorACurso(dniProfesor, 2424);
		assertEquals(true,resultado);
		resultado = universidad.agregarProfesorACurso(dniProfesor, 4242);
		assertEquals(true,resultado);
		
		CursoProfesor pb1Profe = universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		CursoProfesor infGeneralProfe = universidad.buscarAsignacionProfesorPorCursoProfe(otroCurso, profesor);

		assertNotNull(pb1Profe);
		assertNotNull(infGeneralProfe);
		
		assertEquals(curso, pb1Profe.getCurso());
		assertEquals(profesor, pb1Profe.getProfesor());
		
		assertEquals(otroCurso, infGeneralProfe.getCurso());
		assertEquals(profesor, infGeneralProfe.getProfesor());
	}
	
	
	@Test
	public void queSeAsigneUnProfeACursoSiEsDisntintoElTurnoDeLosCursos() {
		Materia pb1 = new Materia(2232,"PROGRAMACION BASICA 1");
		Materia infGeneral = new Materia(1111,"INFORMATICA GENERAL");
		
		//2 CUATRIMESTRE 2023
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		Aula aula = new Aula(215,84);
		
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		Horario horario2 = new Horario(diasCursada,Turno.NOCHE);

		//CURSO
		Curso curso = new Curso(2424,pb1,horario,ciclo);
		Curso otroCurso = new Curso(4242, infGeneral,horario2,ciclo);
		//PROFESOR
		Profesor profesor  = new Profesor(4432323,"Andres","Borgeat");
		//ALUMNO 
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa",fechaNacimiento, fechaIngreso);
		
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(infGeneral);
		universidad.registrarProfesor(profesor);
		universidad.registrarAlumno(alumno);
		universidad.registrarAula(aula);
		universidad.crearCurso(curso);
		universidad.crearCurso(otroCurso);
		universidad.asignarAulaAlCurso(215, 4242);
		universidad.asignarAulaAlCurso(215, 2424);

		//Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los cursos
		//Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		Date fechaInscripcion  = new Date(2023,2,24);
		universidad.inscribirAlumnoACurso(453232, 2424,fechaInscripcion);
		universidad.inscribirAlumnoACurso(453232, 4242,fechaInscripcion);

		
		Integer dniProfesor  = 4432323;

		Boolean resultado = universidad.agregarProfesorACurso(dniProfesor, 2424);
		assertEquals(true,resultado);

		resultado = universidad.agregarProfesorACurso(dniProfesor, 4242);
		assertEquals(true,resultado);

		CursoProfesor pb1Profe = universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		CursoProfesor infGeneralProfe = universidad.buscarAsignacionProfesorPorCursoProfe(otroCurso, profesor);

		assertNotNull(pb1Profe);
		assertNotNull(infGeneralProfe);
		
		assertEquals(curso, pb1Profe.getCurso());
		assertEquals(profesor, pb1Profe.getProfesor());
		
		assertEquals(otroCurso, infGeneralProfe.getCurso());
		assertEquals(profesor, infGeneralProfe.getProfesor());
	}
	
	@Test
	public void queNoSePuedaAsociarCursoYProfesorDosVeces() {
		Materia pb1 = new Materia(2232,"PROGRAMACION BASICA 1");
		
		//1 CUATRIMESTRE 2023
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//AULA
		Aula aula = new Aula(215,84);

		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//CURSO
		Curso curso = new Curso(2424,pb1,horario,ciclo);
		//PROFESOR
		Profesor profesor  = new Profesor(4432323,"Andres","Borgeat");
		//ALUMNO
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa",fechaNacimiento, fechaIngreso);		
		
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarProfesor(profesor);
		universidad.registrarAlumno(alumno);
		universidad.registrarAula(aula);
		universidad.crearCurso(curso);		
		universidad.asignarAulaAlCurso(215, 2424);

		
		//Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los cursos
		//Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		Date fechaInscripcion  = new Date(2023,2,24);
		universidad.inscribirAlumnoACurso(453232, 2424,fechaInscripcion);
		
		Integer dniProfesor  = 4432323;
		Integer codigo = 2424;
		Boolean resultado  = universidad.agregarProfesorACurso(dniProfesor, codigo);
		assertEquals(true, resultado);
		resultado = universidad.agregarProfesorACurso(dniProfesor, codigo);		
		assertEquals(false,resultado);
		
		
	}
	
	@Test
	public void queNoSePuedaAsociarCursoYProfesorSiProfesorEstaOcupado() {
		Materia pb1 = new Materia(2232,"PROGRAMACION BASICA 1");
		Materia infGeneral = new Materia(1111,"INFORMATICA GENERAL");
		
		//2 CUATRIMESTRE 2023
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//AULA
		Aula aula = new Aula(215,84);
		Aula aula2 = new Aula(200,100);

		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		Horario horario2 = new Horario(diasCursada,Turno.MAÑANA);
		
		//CURSO
		Curso curso = new Curso(2424,pb1,horario,ciclo);
		Curso otroCurso = new Curso(4242, infGeneral,horario2,ciclo);
		//PROFESOR
		Profesor profesor  = new Profesor(4432323,"Andres","Borgeat");
		//ALUMNO 
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa",fechaNacimiento, fechaIngreso);
		
		//ALUMNO  2
		Date fechaNacimiento2 = new Date(2003,1,4);
		Date fechaIngreso2 = new Date(2020,2,1);
		Alumno alumno2 = new Alumno(232323, "Tomas", "Flores",fechaNacimiento2, fechaIngreso2);
		
		
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(infGeneral);
		universidad.registrarProfesor(profesor);
		universidad.registrarAlumno(alumno);
		universidad.registrarAlumno(alumno2);
		universidad.registrarAula(aula);
		universidad.registrarAula(aula2);
		universidad.crearCurso(curso);
		universidad.crearCurso(otroCurso);
		
		universidad.asignarAulaAlCurso(215, 2424);
		universidad.asignarAulaAlCurso(200, 4242);

		//Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los cursos
		//Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		Date fechaInscripcion  = new Date(2023,2,24);
		Date fechaInscripcion2  = new Date(2023,2,8);

		universidad.inscribirAlumnoACurso(453232, 2424,fechaInscripcion);
		universidad.inscribirAlumnoACurso(2323, 4242,fechaInscripcion2);

		
		Integer dniProfesor  = 4432323;
		Boolean resultado = universidad.agregarProfesorACurso(dniProfesor, 2424);
		assertEquals(true,resultado);
		
		resultado = universidad.agregarProfesorACurso(dniProfesor, 4242); // Ocupado
		assertEquals(false,resultado);

		CursoProfesor pb1Profe = universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		CursoProfesor infGeneralProfe = universidad.buscarAsignacionProfesorPorCursoProfe(otroCurso, profesor);

		assertNotNull(pb1Profe);
		assertEquals(curso, pb1Profe.getCurso());
		assertEquals(profesor, pb1Profe.getProfesor());
		
		assertNull(infGeneralProfe);
	}
	
	
	
	@Test
	public void queNoSeAsigneUnProfeACursoSiProfesorNoEstaRegistrado() {
		Materia pb1 = new Materia(2232,"PROGRAMACION BASICA 1");

		//1 CUATRIMESTRE 2023
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//AULA
		Aula aula = new Aula(215,84);
		
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//CURSO
		Curso curso = new Curso(2424,pb1,horario,ciclo);
		//PROFESOR
		Profesor profesor  = new Profesor(4432323,"Andres","Borgeat");
		//ALUMNO 
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa",fechaNacimiento, fechaIngreso);
		
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aula);
		universidad.registrarAlumno(alumno);
		//universidad.registrarProfesor(profesor);
		universidad.crearCurso(curso);
		universidad.asignarAulaAlCurso(215, 2424);
	
		
		//Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los cursos
		//Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		Date fechaInscripcion = new Date(2023,2,8);
		universidad.inscribirAlumnoACurso(453232, 2424,fechaInscripcion);	
		
		Integer dniProfesor  = 4432323;
		Integer codigo = 2424;
		
		Boolean resultado = universidad.agregarProfesorACurso(dniProfesor, codigo);
		assertEquals(false,resultado);

		CursoProfesor pb1Profe =  universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		assertNull(pb1Profe);
	}
	
	
	@Test
	public void queNoSeAsigneUnProfeACursoSiCursoNoEstaRegistrado() {
		Materia pb1 = new Materia(2232,"PROGRAMACION BASICA 1");

		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		//AULA
		Aula aula = new Aula(215,84);
		
		//HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//CURSO
		Curso curso = new Curso(2424,pb1,horario,ciclo);
		//PROFESOR
		Profesor profesor  = new Profesor(4432323,"Andres","Borgeat");
		//ALUMNO 
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa",fechaNacimiento, fechaIngreso);
		
		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aula);
		universidad.registrarProfesor(profesor);
		universidad.registrarAlumno(alumno);
		//universidad.crearCurso(curso);
		
		//Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los cursos
		//Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		Date fechaInscripcion = new Date(2023,2,8);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 2424,fechaInscripcion);			
		assertEquals(false, resultado);
		
		Integer dniProfesor  = 4432323;
		Integer codigo = 2424;
		universidad.agregarProfesorACurso(dniProfesor, codigo);
		resultado = universidad.agregarProfesorACurso(dniProfesor,codigo);
		assertEquals(false,resultado);
		
		CursoProfesor pb1Profe = universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		assertNull(pb1Profe);
	}
	
	
}
