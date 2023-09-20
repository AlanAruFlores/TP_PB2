package ar.edu.unlam.test;

import static org.junit.Assert.*;
import ar.edu.unlam.dominio.AlumnoNotas;
import ar.edu.unlam.utils.TipoNota;

import org.junit.Test;

public class TestAlumnoNotas {

	@Test
	public void queSePuedaAsignarRecuperatorioSiPromocionoAlmenosUnParcial() {
		AlumnoNotas alumNota = new AlumnoNotas();
		alumNota.calificar(TipoNota.PRIMER_PARCIAL, 8);
		alumNota.calificar(TipoNota.SEGUNDO_PARCIAL, 4);
		alumNota.calificar(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL, 7);

		assertNotNull(alumNota);
		assertEquals(8, alumNota.getPrimerParcial().getPuntaje().intValue());
		assertEquals(4, alumNota.getSegundoParcial().getPuntaje().intValue());
		assertEquals(7, alumNota.getRecuperatorio().getPuntaje().intValue());
	}

	@Test
	public void queNoSeAsigneRecuperatorioSiYaPromocionoAmbosParciales() {
		AlumnoNotas alumNota = new AlumnoNotas();
		alumNota.calificar(TipoNota.PRIMER_PARCIAL, 8);
		alumNota.calificar(TipoNota.SEGUNDO_PARCIAL, 10);
		Boolean resultado = alumNota.calificar(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL, 7);
		
		assertEquals(false ,resultado);
		assertNull(alumNota.getRecuperatorio());
	}
	
	@Test
	public void queNoSeAsigneRecuperatorioSiNoPromocionoAmbosParciales() {
		AlumnoNotas alumNota = new AlumnoNotas();
		alumNota.calificar(TipoNota.PRIMER_PARCIAL, 4);
		alumNota.calificar(TipoNota.SEGUNDO_PARCIAL, 6);
		Boolean resultado = alumNota.calificar(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL, 7);
		
		assertEquals(false ,resultado);
		assertNull(alumNota.getRecuperatorio());
	}
	
	@Test
	public void queNoSeAsigneNotaDeUnMismoTipo() {
		AlumnoNotas alumNota = new AlumnoNotas();
		alumNota.calificar(TipoNota.PRIMER_PARCIAL, 4);
		Boolean resultado  = alumNota.calificar(TipoNota.PRIMER_PARCIAL, 6);
		
		assertEquals(false ,resultado);
		assertEquals(4,alumNota.getPrimerParcial().getPuntaje().intValue());
	}
}