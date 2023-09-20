package ar.edu.unlam.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import ar.edu.unlam.dominio.Alumno;
import ar.edu.unlam.dominio.CursoProfesor;
import ar.edu.unlam.dominio.Aula;
import ar.edu.unlam.dominio.Profesor;
import ar.edu.unlam.dominio.Curso;
import ar.edu.unlam.dominio.Horario;
import ar.edu.unlam.dominio.Materia;
import ar.edu.unlam.utils.Dia;
import ar.edu.unlam.utils.Turno;
import ar.edu.unlam.dominio.CicloLectivo;



public class TestCursoProfesor {
	@Test
	public void queSePuedaCrearUnaAsignacion() {
		
		//Datos del curso
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");
		pb2.agregarCorreleativa(pb1);
		
		
		Aula aula = new Aula(215,84);
		
		Date fechaInicioCicloLectivo  = new Date(2023,3,1);	
		Date fechaFinalizacionCicloLectivo = new Date(2023,6,14);
		Date fechaInicioInscripcion = new Date(2023,2,1);
		Date fechaFinalizacionInscripcion = new Date(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Curso
		Curso curso = new Curso(2424, pb2, horario, ciclo );
		curso.setAula(aula);
		//Profesor
		Profesor profesor = new Profesor(4423343,"Dario","Leoncini");
		
		CursoProfesor asignacion = new CursoProfesor(curso,profesor);
		
		Integer idEsperado = 1;
		
		
		assertNotNull(asignacion);
		assertEquals(idEsperado, asignacion.getId());
		assertEquals(curso, asignacion.getCurso());
		assertEquals(profesor, asignacion.getProfesor());
		assertEquals(horario, asignacion.getCurso().getHorario());
		assertEquals(aula, asignacion.getCurso().getAula());
		
	}

}