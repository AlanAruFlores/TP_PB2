package ar.edu.unlam.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.dominio.Alumno;
import java.util.Date;

public class TestAlumno {

	@Test
	public void queSePuedaCrearAlumno() {
		Integer dni = 45323639;
		String nombre = "Alan";
		String apellido = "Aruquipa";
		Date fechaNacimiento = new Date(2004,1,4);
		Date fechaIngreso = new Date(2023,2,1);
		
		Alumno alumno = new Alumno(dni,nombre,apellido,fechaNacimiento,fechaIngreso);
		
		assertNotNull(alumno);
		assertEquals(dni, alumno.getDni());
		assertEquals(nombre, alumno.getNombre());
		assertEquals(apellido, alumno.getApellido());
		assertEquals(fechaNacimiento, alumno.getFechaNacimiento());
		assertEquals(fechaIngreso, alumno.getFechaIngreso());
	}

}