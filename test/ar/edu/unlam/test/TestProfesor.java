package ar.edu.unlam.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.dominio.Profesor;

public class TestProfesor {

	@Test
	public void queSePuedaCrearProfesor() {
		Integer dni = 45323636;
		String nombre = "Chema";
		String apellido = "Alonso";
		
		Profesor profesor = new Profesor(dni,nombre,apellido);
		
		assertNotNull(profesor);
		assertEquals(dni, profesor.getDni());
		assertEquals(nombre, profesor.getNombre());
		assertEquals(apellido, profesor.getApellido());
		
	}

}