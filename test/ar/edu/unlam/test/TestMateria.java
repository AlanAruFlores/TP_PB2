package ar.edu.unlam.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.dominio.Materia;

public class TestMateria {

	
	@Test
	public void queSePuedaCrearMateriaConCorreleativas() {
		/*Materias*/
		Materia pb1 = new Materia(2619,"PROGRAMACION BASICA 1");
		Materia infGeneral  = new Materia(2620, "INFORMATICA GENERAL");
		
		
		Materia pb2 = new Materia(2624, "PROGRAMACION BASICA 2");
		pb2.agregarCorreleativa(pb1);
		pb2.agregarCorreleativa(infGeneral);
		
		assertNotNull(pb2);
		assertEquals(2, pb2.getCorreleativas().size());
		
	}
	
	@Test
	public void queSePuedaCrearMateriaSinCorreleativa() {
		Integer codigoMateria = 2424;
		String nombreMateria = "Matematica";
		
		Materia materia = new Materia(codigoMateria, nombreMateria);
		
		assertNotNull(materia);
		assertEquals(codigoMateria, materia.getCodigo());
		assertEquals(nombreMateria, materia.getNombre());
		assertEquals(0, materia.getCorreleativas().size());
	}
}
