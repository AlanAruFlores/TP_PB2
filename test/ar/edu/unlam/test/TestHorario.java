package ar.edu.unlam.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import ar.edu.unlam.dominio.Horario;
import ar.edu.unlam.utils.Dia;
import ar.edu.unlam.utils.Turno;

public class TestHorario {

	@Test
	public void asignarHorario() {
		ArrayList<Dia> dias = new ArrayList<>();
		dias.add(Dia.MARTES);
		dias.add(Dia.SABADO);
		Horario horarios = new Horario(dias, Turno.MAÑANA);

		assertEquals(Dia.MARTES, horarios.getDias().get(0));
		assertEquals(Dia.SABADO, horarios.getDias().get(1));
		assertEquals(Turno.MAÑANA, horarios.getTurno());
	}

}
