package ar.edu.unlam.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

import ar.edu.unlam.dominio.Alumno;
import ar.edu.unlam.dominio.Aula;
import ar.edu.unlam.dominio.CicloLectivo;
import ar.edu.unlam.dominio.Curso;
import ar.edu.unlam.dominio.CursoAlumno;
import ar.edu.unlam.dominio.Horario;
import ar.edu.unlam.dominio.Materia;
import ar.edu.unlam.dominio.Nota;
import ar.edu.unlam.utils.Dia;
import ar.edu.unlam.utils.TipoNota;
import ar.edu.unlam.utils.Turno;

public class TestCursoAlumno {

	@Test
	public void queSeCreeUnaAsignacion() {
		
		Integer codigoCurso = 2424;
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
			
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");
		pb2.agregarCorreleativa(pb1);
		/*Ciclo Lectivo*/
		LocalDate fechaInicioCicloLectivo  = LocalDate.of(2023,3,1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023,6,14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023,2,1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		Aula aula = new Aula(215,84); 
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Curso
		Curso curso = new Curso(codigoCurso, pb2,horario,ciclo);
		curso.setAula(aula);
		//Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004,1,4);
		LocalDate fechaIngreso = LocalDate.of(2023,2,1);
		Alumno alumno = new Alumno(4323445,"Alan","Aruquipa",fechaNacimiento, fechaIngreso);
		//Nota 
		CursoAlumno asignacion = new CursoAlumno(1,curso,alumno);
		
		Integer idEsperado =  1;
		assertNotNull(asignacion);
		assertEquals(idEsperado , asignacion.getId());
		assertEquals(curso, asignacion.getCurso());
		assertEquals(alumno, asignacion.getAlumno());	
		assertEquals(aula, asignacion.getCurso().getAula());
		assertEquals(horario, asignacion.getCurso().getHorario());

	}
	
	@Test
	public void queAlumnoPromocioneUnCurso() {
		
		Integer codigoCurso = 2424;
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
			
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");
		pb2.agregarCorreleativa(pb1);
		/*Ciclo Lectivo*/
		LocalDate fechaInicioCicloLectivo  = LocalDate.of(2023,3,1);	
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023,6,14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023,2,1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		Aula aula = new Aula(215,84);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Curso
		Curso curso = new Curso(codigoCurso, pb2,horario,ciclo);
		curso.setAula(aula);
		//Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004,1,4);
		LocalDate fechaIngreso = LocalDate.of(2023,2,1);
		Alumno alumno = new Alumno(4323445,"Alan","Aruquipa",fechaNacimiento, fechaIngreso);
		//Nota 
		CursoAlumno asignacion = new CursoAlumno(1,curso,alumno);
		
		asignacion.asignarNota(new Nota(TipoNota.PRIMER_PARCIAL,8));
		asignacion.asignarNota(new Nota(TipoNota.SEGUNDO_PARCIAL,7));

		Boolean promociono = asignacion.estaPromocionado();
		assertEquals(true, promociono);
		assertEquals(8,asignacion.getNotas().getPrimerParcial().getPuntaje().intValue());
		assertEquals(7,asignacion.getNotas().getSegundoParcial().getPuntaje().intValue());
	}

	@Test
	public void queAlumnoPromocioneConRecuperatorioUnCurso() {
		
		Integer codigoCurso = 2424;
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
			
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");
		pb2.agregarCorreleativa(pb1);
		/*Ciclo Lectivo*/
		LocalDate fechaInicioCicloLectivo  = LocalDate.of(2023,3,1);	
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023,6,14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023,2,1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		Aula aula = new Aula(215,84);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//Curso
		Curso curso = new Curso(codigoCurso, pb2,horario,ciclo);
		curso.setAula(aula);
		//Alumno
		//CLas
		LocalDate fechaNacimiento = LocalDate.of(2004,1,4);
		LocalDate fechaIngreso = LocalDate.of(2023,2,1);
		Alumno alumno = new Alumno(4323445,"Alan","Aruquipa",fechaNacimiento, fechaIngreso);
		//Nota 
		CursoAlumno asignacion = new CursoAlumno(1,curso,alumno);
		
		asignacion.asignarNota(new Nota(TipoNota.PRIMER_PARCIAL,8));
		asignacion.asignarNota(new Nota(TipoNota.SEGUNDO_PARCIAL,4));
		asignacion.asignarNota(new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL,9));

		Boolean promociono = asignacion.estaPromocionado();
		assertEquals(true, promociono);
		assertEquals(8,asignacion.getNotas().getPrimerParcial().getPuntaje().intValue());
		assertEquals(4,asignacion.getNotas().getSegundoParcial().getPuntaje().intValue());
		assertEquals(9,asignacion.getNotas().getRecuperatorio().getPuntaje().intValue());

	}
}
