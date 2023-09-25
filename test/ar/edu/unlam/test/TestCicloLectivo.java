package ar.edu.unlam.test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Test;
import ar.edu.unlam.dominio.*;

public class TestCicloLectivo {
	
	@Test
	public void queSePuedaInstanciarUnCicloLectivo() {
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 3, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023,6,14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023,2,1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023,2,28);
		
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
	
		Integer idEsperado = 1;
		assertNotNull(ciclo);
		assertEquals(idEsperado.intValue(), ciclo.getID().intValue());
		assertEquals(fechaInicioCicloLectivo, ciclo.getFechaInicioCicloLectivo());
		assertEquals(fechaFinalizacionCicloLectivo, ciclo.getfechaFinalizacionCicloLectivo());
		assertEquals(fechaInicioInscripcion, ciclo.getfechaInicioInscripcion());
		assertEquals(fechaFinalizacionInscripcion, ciclo.getfechaFinalizacionInscripcion());
		
	}

}