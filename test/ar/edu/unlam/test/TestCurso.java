package ar.edu.unlam.test;

import static org.junit.Assert.*; 
import java.util.ArrayList;
import org.junit.Test;
import ar.edu.unlam.dominio.Aula;
import ar.edu.unlam.dominio.CicloLectivo;
import ar.edu.unlam.dominio.Curso;
import ar.edu.unlam.dominio.Horario;
import ar.edu.unlam.dominio.Materia;
import ar.edu.unlam.utils.Dia;
import ar.edu.unlam.utils.Turno;
import java.time.LocalDate;

public class TestCurso {

	@Test
	public void queSePuedaCrearUnCurso() {
		//Materia Correleativa
		Materia pb1  = new Materia(2232, "PROGRAMACION BASICA 1");
		
		Materia pb2 = new Materia(2222,"PROGRAMACION BASICA 2");
		pb2.agregarCorreleativa(pb1);		
		
		//Ciclo Lectivo
		LocalDate fechaInicioCicloLectivo  = LocalDate.of(2023,3,1);	
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023,6,14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023,2,1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		
		//Aula  
		Aula aula = new Aula(110, 84);
		
		//Horario
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		
		Horario horario = new Horario(diasCursada,Turno.MAÑANA);
		
		//CURSO
		Curso curso = new Curso(2424,pb2,horario,ciclo);
		curso.setAula(aula);
		
		assertNotNull(curso);
		assertEquals(2424, curso.getCodigo().intValue());
		assertEquals(pb2 , curso.getMateria());
		assertEquals(horario, curso.getHorario());
		assertEquals(ciclo, curso.getCiclo());
		assertEquals(aula, curso.getAula());
		 
		
	}

}
