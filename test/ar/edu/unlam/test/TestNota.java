package ar.edu.unlam.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.dominio.Nota;
import ar.edu.unlam.utils.TipoNota;

public class TestNota {

	@Test
	public void queSeRegistreNota() {
		
		Nota nota = new Nota(TipoNota.PRIMER_PARCIAL);
		Integer valorDefecto = 0;
		assertNotNull(nota);
		assertEquals(valorDefecto, nota.getPuntaje());
		
	}
	
	@Test
	public void queSePuedaAsignarNotaEntreUnoYDiez() {
		Integer puntaje = 8;
		Nota nota = new Nota(TipoNota.SEGUNDO_PARCIAL);
		nota.asignarPuntaje(puntaje);
		
		Integer puntajeEsperado = 8;
		assertEquals(puntajeEsperado, nota.getPuntaje());
		
		
	}
	
	@Test
	public void queNoSePuedaAsignarNotaSiNoEstaEntreUnoYDiez() {
		Integer puntaje = 11;

		Nota nota = new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL);
		nota.asignarPuntaje(puntaje);
		
		Integer puntajeEsperado = 0;
		assertEquals(puntajeEsperado, nota.getPuntaje());
	}
}