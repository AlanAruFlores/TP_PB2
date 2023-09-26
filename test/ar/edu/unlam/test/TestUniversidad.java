package ar.edu.unlam.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import java.time.LocalDate;
import ar.edu.unlam.dominio.*;
import ar.edu.unlam.utils.*;

public class TestUniversidad {

	@Test
	public void queSePuedaInstanciarUnaUniversidad() {
		String nombreUniversidad = "UNLAM";

		Universidad universidad = new Universidad(nombreUniversidad);

		assertNotNull(universidad);
		assertEquals(nombreUniversidad, universidad.getNombre());
	}

	@Test
	public void queSePuedaRegistrarMateria() {
		Materia materia = new Materia(2224, "Matematica General");

		String nombreUniversidad = "UNLAM";
		Universidad universidad = new Universidad(nombreUniversidad);

		Boolean resultado = universidad.registrarMateria(materia);

		Integer cantidadEsperada = 1;

		assertEquals(true, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getMaterias().size());
	}

	@Test
	public void queNoSePuedaRegistrarMateriaConMismoCodigo() {
		Materia materia = new Materia(2224, "Matematica General");
		Materia otraMateria = new Materia(2224, "Seminario");

		String nombreUniversidad = "UNLAM";
		Universidad universidad = new Universidad(nombreUniversidad);

		universidad.registrarMateria(materia);
		Boolean resultado = universidad.registrarMateria(otraMateria);

		Integer cantidadEsperada = 1;
		assertEquals(false, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getMaterias().size());
	}

	/* Tests con correleativas */
	@Test
	public void queSePuedaAsignarCorreleativasAMateria() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);

		Integer codMateria = 2222;
		Integer codCorreleativa = 2232;
		Boolean resultado = universidad.asignarAMateriaCorreleativa(codMateria, codCorreleativa);

		assertEquals(true, resultado);
		assertEquals(1, pb2.getCorreleativas().size());
	}

	@Test
	public void queNoSePuedaAsignarCorreleativasAMateriaSiNoExiste() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);

		Integer codMateria = 2222;
		Integer codCorreleativa = 4444;
		Boolean resultado = universidad.asignarAMateriaCorreleativa(codMateria, codCorreleativa);

		assertEquals(false, resultado);
		assertEquals(0, pb2.getCorreleativas().size());
	}

	@Test
	public void queSeElimineUnaCorreleativaDeMateria() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);

		Integer codMateria = 2222;
		Integer codCorreleativa = 2232;

		universidad.asignarAMateriaCorreleativa(codMateria, codCorreleativa);
		assertEquals(1, pb2.getCorreleativas().size());

		Boolean resultado = universidad.eliminarCorreleativaMateria(codMateria, codCorreleativa);
		assertEquals(true, resultado);
		assertEquals(0, pb2.getCorreleativas().size());

	}

	@Test
	public void queNoSeElimineUnaCorreleativaDeMateriaSiNoExisteCodigo() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);

		universidad.asignarAMateriaCorreleativa(2222, 2232);
		assertEquals(1, pb2.getCorreleativas().size());

		Boolean resultado = universidad.eliminarCorreleativaMateria(2222, 444);
		assertEquals(false, resultado);
		assertEquals(1, pb2.getCorreleativas().size());
	}

	@Test
	public void queNoSeElimineUnaCorreleativaSiMateriaNoLaContiene() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		Universidad universidad = new Universidad("UNLAM");
		universidad.registrarMateria(pb1);
		universidad.registrarMateria(pb2);

		Integer codMateria = 2222;
		Integer codCorreleativa = 2232;

		// universidad.asignarAMateriaCorreleativa(codMateria, codCorreleativa);
		Boolean resultado = universidad.eliminarCorreleativaMateria(codMateria, codCorreleativa);
		assertEquals(false, resultado);
		assertEquals(0, pb2.getCorreleativas().size());
	}

	/*
	 * Test Ciclo Lectivo
	 */
	@Test
	public void queSePuedaAgregarCicloLectivo() {
		Universidad universidad = new Universidad("UNLAM");

		/* Ciclo Lectivo */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 3, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 6, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		assertTrue(universidad.agregarCicloLectivo(ciclo));
	}

	@Test
	public void queSePuedaAgregar2CiclosLectivosNOSuperpuestos() {
		Universidad universidad = new Universidad("UNLAM");

		/* Ciclo Lectivo */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 3, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 6, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		/* Ciclo Lectivo 2 */
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2023, 7, 1);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2023, 12, 14);
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2023, 7, 1);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2023, 7, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.agregarCicloLectivo(ciclo2));
	}

	@Test
	public void queNoSePuedaAgregarCicloLectivosSuperpuestos() {
		Universidad universidad = new Universidad("UNLAM");

		/* Ciclo Lectivo */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 3, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 6, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		/* Ciclo Lectivo 2 (Superpuesto) */
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2023, 4, 1);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2023, 4, 14);
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertFalse(universidad.agregarCicloLectivo(ciclo2));
	}

	@Test
	public void queNoSePuedaAgregarCicloLectivosSuperpuestosCaso2() {
		Universidad universidad = new Universidad("UNLAM");

		/* Ciclo Lectivo */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 6, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 3, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 3, 28);

		/* Ciclo Lectivo 2 */
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2023, 7, 1);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2023, 12, 14);
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2023, 7, 1);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2023, 7, 28);

		/* Ciclo Lectivo 3 (Superpuesto) */
		LocalDate fechaInicioCicloLectivo3 = LocalDate.of(2023, 5, 1);
		LocalDate fechaFinalizacionCicloLectivo3 = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion3 = LocalDate.of(2023, 5, 1);
		LocalDate fechaFinalizacionInscripcion3 = LocalDate.of(2023, 5, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);

		CicloLectivo ciclo3 = new CicloLectivo(fechaInicioCicloLectivo3, fechaFinalizacionCicloLectivo3,
				fechaInicioInscripcion3, fechaFinalizacionInscripcion3);

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.agregarCicloLectivo(ciclo2));
		assertFalse(universidad.agregarCicloLectivo(ciclo3)); // Ciclo superpuesto

	}

	/*
	 * Tests con Alumno
	 */

	@Test
	public void queSePuedaRegistrarAlumno() {
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", LocalDate.of(2004, 2, 1), LocalDate.of(2023, 2, 1));
		Alumno alumno2 = new Alumno(442323, "Gonza", "Leonel", LocalDate.of(2000, 1, 1), LocalDate.of(2023, 2, 12));

		String nombreUniversidad = "UNLAM";
		Universidad universidad = new Universidad(nombreUniversidad);

		assertTrue(universidad.registrarAlumno(alumno));
		Boolean resultado = universidad.registrarAlumno(alumno2);
		Integer cantidadEsperada = 2;
		assertEquals(true, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getAlumnos().size());
	}

	@Test
	public void queNoSePuedaRegistrarAlumnosConMismoDNI() {
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", LocalDate.of(2004, 2, 1), LocalDate.of(2023, 2, 1));

		Alumno alumno2 = new Alumno(453232, "Gonza", "Leonel", LocalDate.of(2000, 1, 1), LocalDate.of(2023, 2, 12));

		String nombreUniversidad = "UNLAM";
		Universidad universidad = new Universidad(nombreUniversidad);

		assertTrue(universidad.registrarAlumno(alumno));

		Boolean resultado = universidad.registrarAlumno(alumno2);
		Integer cantidadEsperada = 1;
		assertEquals(false, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getAlumnos().size());
	}

	/* Tests con Profesores */
	@Test
	public void queSePuedaRegistrarProfesor() {
		Profesor profesor = new Profesor(4432323, "Andres", "Borgeat");
		Universidad universidad = new Universidad("UNLAM");
		Boolean resultado = universidad.registrarProfesor(profesor);
		Integer cantidadEsperada = 1;
		assertEquals(true, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getProfesores().size());
	}

	@Test
	public void queNoSePuedaRegistrarProfesorConMismoDNI() {
		Profesor profesor = new Profesor(4432323, "Andres", "Borgeat");
		Profesor profesor2 = new Profesor(4432323, "Juan", "Monteagudo");

		Universidad universidad = new Universidad("UNLAM");

		assertTrue(universidad.registrarProfesor(profesor));
		Boolean resultado = universidad.registrarProfesor(profesor2);
		Integer cantidadEsperada = 1;
		assertEquals(false, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getProfesores().size());
	}

	/* Test con cursos */

	@Test
	public void queSePuedaAgregarCurso() {
		Integer codigoCurso = 2424;
		// Materia Correleativa
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// HORARIO DEL CURSO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);
		// CURSO
		Curso curso = new Curso(codigoCurso, pb2, horario, ciclo);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(pb2));
		assertTrue(universidad.asignarAMateriaCorreleativa(2232, 2222));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(curso));

		Integer cantidadEsperada = 1;
		assertEquals(cantidadEsperada.intValue(), universidad.getCursos().size());
	}

	@Test
	public void queSePuedaAgregarElMismoCursoEn2CiclosDiferentes() {
		// Materia Correleativa
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");
		// 1ER CUATRIMESTRE 2022
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2022, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2022, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2022, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2022, 2, 28);
		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);

		// HORARIO DEL CURSO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);
		// CURSO
		Curso curso = new Curso(2424, pb2, horario, ciclo);
		Curso otroCurso = new Curso(2323, pb2, horario, ciclo2);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(pb2));
		assertTrue(universidad.asignarAMateriaCorreleativa(2232, 2222));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.agregarCicloLectivo(ciclo2));
		assertTrue(universidad.crearCurso(curso));
		assertTrue(universidad.crearCurso(otroCurso));

		Integer cantidadEsperada = 2;
		assertEquals(cantidadEsperada.intValue(), universidad.getCursos().size());
	}

	@Test
	public void queNoSePuedaAgregarCursoSiTieneMismoCodigo() {
		// Materia Correleativa
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);
		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		Aula aula = new Aula(215, 84);

		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// CURSO
		Curso curso = new Curso(2424, pb2, horario, ciclo);
		Curso otroCurso = new Curso(2424, pb1, horario, ciclo);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(pb2));
		assertTrue(universidad.asignarAMateriaCorreleativa(2222, 2232));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(curso));

		Boolean resultado = universidad.crearCurso(otroCurso);
		Integer cantidadEsperada = 1;
		assertEquals(false, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getCursos().size());
	}

	@Test
	public void queNoSePuedaAgregarCursoSiSonLoMismo() {
		// Materia
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");

		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		Aula aula = new Aula(215, 84);

		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);

		Horario horario = new Horario(diasCursada, Turno.MAÑANA);
		// CURSO
		Curso curso = new Curso(2424, pb1, horario, ciclo);
		Curso otroCurso = new Curso(2323, pb1, horario, ciclo);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(curso));

		Boolean resultado = universidad.crearCurso(otroCurso);
		Integer cantidadEsperada = 1;
		assertEquals(false, resultado);
		assertEquals(cantidadEsperada.intValue(), universidad.getCursos().size());
	}

	// Asignar Aula a Curso
	@Test
	public void queSePuedaAsignarAulaAUnCurso() {
		// Materia Correleativa
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		// AULA
		Aula aula = new Aula(215, 84);
		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);

		Horario horario = new Horario(diasCursada, Turno.MAÑANA);
		// CURSO
		Curso curso = new Curso(2424, pb2, horario, ciclo);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(pb2));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.asignarAMateriaCorreleativa(2222, 2232));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.crearCurso(curso));

		// Asignamos una aula a un curso
		Integer codAula = 215;
		Integer codCurso = 2424;
		Boolean resultado = universidad.asignarAulaAlCurso(codAula, codCurso);
		assertEquals(true, resultado);
		assertEquals(aula, curso.getAula());
	}

	@Test
	public void queSePuedaAsignarAulaACursoSiTienenDistintoCiclo() {
		// Materia Correleativa
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		// 2DO CUATRIMESTRE 2022
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2022, 8, 1);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2022, 11, 14);
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2022, 8, 1);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2022, 8, 15);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);
		// AULA
		Aula aula = new Aula(215, 84);
		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);

		Horario horario = new Horario(diasCursada, Turno.MAÑANA);
		// CURSO
		Curso cursoPB1 = new Curso(1212, pb1, horario, ciclo);
		Curso cursoPB2 = new Curso(2424, pb2, horario, ciclo2);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(pb2));
		assertTrue(universidad.asignarAMateriaCorreleativa(2222, 2232));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.agregarCicloLectivo(ciclo2));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.crearCurso(cursoPB2));

		// Asignamos una aula a un curso
		Integer codAula = 215;
		universidad.asignarAulaAlCurso(codAula, 1212);
		Boolean resultado = universidad.asignarAulaAlCurso(codAula, 2424);

		assertEquals(aula, cursoPB1.getAula());
		assertEquals(aula, cursoPB2.getAula());
		assertEquals(true, resultado);
	}

	@Test
	public void queSePuedaAsignarAulaACursoSiTienenDistintoDias() {
		// Materia Correleativa
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 1, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// AULA
		Aula aula = new Aula(215, 84);
		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);

		ArrayList<Dia> diasCursada2 = new ArrayList<>();
		diasCursada2.add(Dia.MIERCOLES);
		diasCursada2.add(Dia.VIERNES);

		// Horario
		Horario horarioPB1 = new Horario(diasCursada, Turno.MAÑANA);
		Horario horarioPB2 = new Horario(diasCursada2, Turno.MAÑANA);
		// CURSO
		Curso cursoPB1 = new Curso(1212, pb1, horarioPB1, ciclo);
		Curso cursoPB2 = new Curso(2424, pb2, horarioPB2, ciclo);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(pb2));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.asignarAMateriaCorreleativa(2232, 2222));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.crearCurso(cursoPB2));

		// Asignamos una aula a un curso
		Integer codAula = 215;
		assertTrue(universidad.asignarAulaAlCurso(codAula, 1212));
		Boolean resultado = universidad.asignarAulaAlCurso(codAula, 2424);

		assertEquals(true, resultado);
		assertEquals(aula, cursoPB1.getAula());
		assertEquals(aula, cursoPB2.getAula());
	}

	@Test
	public void queSePuedaAsignarAulaACursoSiTienenDistintoTurnos() {
		// Materia Correleativa
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// AULA
		Aula aula = new Aula(215, 84);
		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);

		// Horario
		Horario horarioPB1 = new Horario(diasCursada, Turno.MAÑANA);
		Horario horarioPB2 = new Horario(diasCursada, Turno.NOCHE);
		// CURSO
		Curso cursoPB1 = new Curso(1212, pb1, horarioPB1, ciclo);
		Curso cursoPB2 = new Curso(2424, pb2, horarioPB2, ciclo);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(pb2));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.asignarAMateriaCorreleativa(2232, 2222));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.crearCurso(cursoPB2));

		// Asignamos una aula a un curso
		Integer codAula = 215;
		assertTrue(universidad.asignarAulaAlCurso(codAula, 1212));
		Boolean resultado = universidad.asignarAulaAlCurso(codAula, 2424);

		assertEquals(true, resultado);
		assertEquals(aula, cursoPB1.getAula());
		assertEquals(aula, cursoPB2.getAula());
	}

	@Test
	public void queNoSePuedaAsignarAulaACursoSiTienenElMismoHorarioYCiclo() {
		// Materia Correleativa
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		// AULA
		Aula aula = new Aula(215, 84);
		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);

		Horario horario = new Horario(diasCursada, Turno.MAÑANA);
		// CURSO
		Curso cursoPB1 = new Curso(1212, pb1, horario, ciclo);
		Curso cursoPB2 = new Curso(2424, pb2, horario, ciclo);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(pb2));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.asignarAMateriaCorreleativa(2232, 2222));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.crearCurso(cursoPB2));

		// Asignamos una aula a un curso
		Integer codAula = 215;
		assertTrue(universidad.asignarAulaAlCurso(codAula, 1212));
		Boolean resultado = universidad.asignarAulaAlCurso(codAula, 2424);

		assertEquals(false, resultado);
		assertEquals(aula, cursoPB1.getAula());
		assertNull(cursoPB2.getAula());
	}

	@Test
	public void queNoSePuedaAsignarAulaACursoSiExisteAlMenosUnDiaQueCoincidenEntreSi() {
		// Materia Correleativa
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");

		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 3, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 6, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		// AULA
		Aula aula = new Aula(215, 84);
		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);

		ArrayList<Dia> diasCursada2 = new ArrayList<>();
		diasCursada2.add(Dia.LUNES);
		diasCursada2.add(Dia.JUEVES);

		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		Horario horario2 = new Horario(diasCursada2, Turno.MAÑANA);

		// CURSO
		Curso cursoPB1 = new Curso(1212, pb1, horario, ciclo);
		Curso cursoPB2 = new Curso(2424, pb2, horario2, ciclo);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(pb2));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.asignarAMateriaCorreleativa(2232, 2222));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.crearCurso(cursoPB2));

		// Asignamos una aula a los cursos
		Integer codAula = 215;
		assertTrue(universidad.asignarAulaAlCurso(codAula, 1212));
		Boolean resultado = universidad.asignarAulaAlCurso(codAula, 2424);
		assertEquals(false, resultado);

		assertEquals(aula, cursoPB1.getAula());
		assertNull(cursoPB2.getAula());
	}

	/* Test de Asignacion a alumnos */
	@Test
	public void queSePuedaInscribirAlumnoACursoSinCorreleativa() {
		Universidad universidad = new Universidad("UNLAM");

		/* 1ER CUATRIMESTRE 2023 */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111, 100);
		Curso cursoPB1 = new Curso(2323, pb1, horario, ciclo);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 2, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aulaPB1));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.asignarAulaAlCurso(111, 2323));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 2);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);

		assertEquals(true, resultado);

		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursoPB1);
		assertNotNull(cursoAlumno);
	}

	@Test
	public void queNOSePuedaInscribirAlumnoACursoSinCorreleativaSiNoEstaDentroDeLaFechaDeInscripcion() {
		Universidad universidad = new Universidad("UNLAM");

		/* 1ER CUATRIMESTRE 2023 */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111, 100);
		Curso cursoPB1 = new Curso(2323, pb1, horario, ciclo);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 2, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aulaPB1));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.asignarAulaAlCurso(111, 2323));

		LocalDate fechaInscripcion = LocalDate.of(2022, 3, 2);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);
		assertEquals(false, resultado);

		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursoPB1);
		assertNull(cursoAlumno);
	}

	/* Test Asignacion de Notas En Materia sin correleativas */
	@Test
	public void queSePuedaAsignarYObtenerUnaNotaAlumnoSiEstaEntre1Y10() {
		Universidad universidad = new Universidad("UNLAM");

		/* 1ER CUATRIMESTRE 2023 */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111, 100);
		Curso cursoPB1 = new Curso(2323, pb1, horario, ciclo);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aulaPB1));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.asignarAulaAlCurso(111, 2323));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 2);
		assertTrue(universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion));

		Integer dniAlumno = 453232;
		Integer codCurso = 2323;

		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL, 8);
		Boolean resultado = universidad.registrarNota(dniAlumno, codCurso, primerParcial);
		assertEquals(true, resultado);

		/*
		 * CursoAlumno cursoAlumno =
		 * universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno,cursoPB1);
		 * 
		 * Integer notaEsperada = 8; assertEquals(true, resultado);
		 * assertNotNull(cursoAlumno); assertEquals(notaEsperada.intValue(),
		 * cursoAlumno.getNotas().getPrimerParcial().getPuntaje().intValue());
		 */

		Nota notaAlumnoObtenida = universidad.obtenerNota(453232, 1111, TipoNota.PRIMER_PARCIAL);
		assertNotNull(notaAlumnoObtenida);
		assertEquals(8, notaAlumnoObtenida.getPuntaje().intValue());

	}

	@Test
	public void queNoSePuedaAsignarUnaNotaAlumnoSiNoEstaEntre1Y10() {
		Universidad universidad = new Universidad("UNLAM");

		/* 1ER CUATRIMESTRE 2023 */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111, 100);
		Curso cursoPB1 = new Curso(2323, pb1, horario, ciclo);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aulaPB1));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.asignarAulaAlCurso(111, 2323));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);

		Integer dniAlumno = 453232;
		Integer codCurso = 2323;

		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL, 11);

		Boolean resultado = universidad.registrarNota(dniAlumno, codCurso, primerParcial);

		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursoPB1);

		Integer notaEsperada = 0;
		assertEquals(false, resultado);
		assertNotNull(cursoAlumno);
		assertEquals(notaEsperada.intValue(), cursoAlumno.getNotas().getPrimerParcial().getPuntaje().intValue());

	}

	@Test
	public void queSeAsigneNotasParcialesRecuperatorios() {
		Universidad universidad = new Universidad("UNLAM");

		/* 1ER CUATRIMESTRE 2023 */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111, 100);
		Curso cursoPB1 = new Curso(2323, pb1, horario, ciclo);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aulaPB1));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.asignarAulaAlCurso(111, 2323));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);

		Integer dniAlumno = 453232;
		Integer codCurso = 2323;

		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL, 8);
		Nota segundoParcial = new Nota(TipoNota.SEGUNDO_PARCIAL, 6);
		Nota recuperatorio2doParcial = new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL, 7);

		universidad.registrarNota(dniAlumno, codCurso, primerParcial);
		universidad.registrarNota(dniAlumno, codCurso, segundoParcial);
		universidad.registrarNota(dniAlumno, codCurso, recuperatorio2doParcial);

		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(new Alumno(dniAlumno),
				new Curso(codCurso));

		// Notas Esperadas
		Integer primeraNota = 8;
		Integer segundaNota = 6;
		Integer recuperatorioNota = 7;

		assertNotNull(cursoAlumno);
		assertEquals(primeraNota.intValue(), cursoAlumno.getNotas().getPrimerParcial().getPuntaje().intValue());
		assertEquals(segundaNota.intValue(), cursoAlumno.getNotas().getSegundoParcial().getPuntaje().intValue());
		assertEquals(recuperatorioNota.intValue(), cursoAlumno.getNotas().getRecuperatorio().getPuntaje().intValue());
	}

	@Test
	public void queNoSeAsigneNotaRecuperatorioSiPromociono() {
		Universidad universidad = new Universidad("UNLAM");

		/* 1ER CUATRIMESTRE 2023 */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111, 100);
		Curso cursoPB1 = new Curso(2323, pb1, horario, ciclo);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aulaPB1));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.asignarAulaAlCurso(111, 2323));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);

		Integer dniAlumno = 453232;
		Integer codCurso = 2323;

		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL, 8);
		Nota segundoParcial = new Nota(TipoNota.SEGUNDO_PARCIAL, 10);

		Nota recuperatorio2doParcial = new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL, 7); // null ya que promociono

		assertTrue(universidad.registrarNota(dniAlumno, codCurso, primerParcial));
		assertTrue(universidad.registrarNota(dniAlumno, codCurso, segundoParcial));
		Boolean resultado = universidad.registrarNota(dniAlumno, codCurso, recuperatorio2doParcial);

		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursoPB1);

		Integer primeraNota = 8;
		Integer segundaNota = 10;
		// Integer recuperatorioNota = 0;

		assertEquals(false, resultado);
		assertEquals(primeraNota.intValue(), cursoAlumno.getNotas().getPrimerParcial().getPuntaje().intValue());
		assertEquals(segundaNota.intValue(), cursoAlumno.getNotas().getSegundoParcial().getPuntaje().intValue());

		// Verifico que no se haya asignado el recuperatorio ya que promociono
		assertNull(cursoAlumno.getNotas().getRecuperatorio());
	}

	@Test
	public void queNoSeAsigneMuchasVecesLaNotaDeUnDeterminadoParcial() {
		Universidad universidad = new Universidad("UNLAM");

		/* 1ER CUATRIMESTRE 2023 */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111, 100);
		Curso cursoPB1 = new Curso(2323, pb1, horario, ciclo);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aulaPB1));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.asignarAulaAlCurso(111, 2323));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);

		Integer dniAlumno = 453232;
		Integer codCurso = 2323;

		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL, 8);
		Nota primerParcial2 = new Nota(TipoNota.PRIMER_PARCIAL, 10);

		assertTrue(universidad.registrarNota(dniAlumno, codCurso, primerParcial));
		Boolean resultado = universidad.registrarNota(dniAlumno, codCurso, primerParcial2);

		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursoPB1);

		Integer notaEsperada = 8;
		assertEquals(false, resultado);
		assertEquals(notaEsperada.intValue(), cursoAlumno.getNotas().getPrimerParcial().getPuntaje().intValue());

	}

	/* Test Asignacion de alumnos con correleativas */

	@Test
	public void queSePuedaAlumnoInscribirAUnaMateriaCorreleativaSiPromocionoAmbosParciales() {
		Universidad universidad = new Universidad("UNLAM");

		/* Ciclo lectivo de Ingles I */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 3, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 6, 14);

		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		/* Ciclo lectivo de Ingles II */
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2024, 3, 1);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2024, 6, 14);

		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2024, 2, 1);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2024, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);
		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada2.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2, Turno.NOCHE);

		// Materia INGLES 1
		Materia ingles1 = new Materia(1234, "INGLES TECNICO 1");
		Aula aulaIngles1 = new Aula(90, 30);
		Curso cursoIng1 = new Curso(2323, ingles1, horario, ciclo);

		// Materia INGLES 2
		Materia ingles2 = new Materia(2468, "INGLES TECNICO 2");
		Aula aulaIngles2 = new Aula(80, 30);
		Curso cursoIng2 = new Curso(4646, ingles2, horario2, ciclo2);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(ingles1));
		assertTrue(universidad.registrarAula(aulaIngles1));
		assertTrue(universidad.crearCurso(cursoIng1));

		assertTrue(universidad.agregarCicloLectivo(ciclo2));
		assertTrue(universidad.registrarMateria(ingles2));
		assertTrue(universidad.registrarAula(aulaIngles2));
		assertTrue(universidad.crearCurso(cursoIng2));

		assertTrue(universidad.asignarAulaAlCurso(90, 2323));
		assertTrue(universidad.asignarAulaAlCurso(80, 4646));

		assertTrue(universidad.asignarAMateriaCorreleativa(2468, 1234));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		assertTrue(universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion));
		// Registrando notas de ingles 1
		assertTrue(universidad.registrarNota(453232, 2323, new Nota(TipoNota.PRIMER_PARCIAL, 8)));
		assertTrue(universidad.registrarNota(453232, 2323, new Nota(TipoNota.SEGUNDO_PARCIAL, 8)));

		// Inscribiendose al siguiente nivel (ingles 2)
		LocalDate fechaInscripcion2 = LocalDate.of(2024, 2, 18);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 4646, fechaInscripcion2);

		assertEquals(true, resultado);

		CursoAlumno cursoAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursoIng2);
		assertNotNull(cursoAlumno);

	}

	@Test
	public void queSePuedaAlumnoInscribirAUnaMateriaCorreleativaSiPromocionoParcialRecuperatorio() {
		Universidad universidad = new Universidad("UNLAM");

		/* Ciclo lectivo de Ingles I */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		/* Ciclo lectivo de Ingles II */
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2024, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2024, 7, 14);
		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2024, 2, 1);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2024, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada2.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2, Turno.NOCHE);

		// Materia INGLES 1
		Materia ingles1 = new Materia(1234, "INGLES TECNICO 1");
		Aula aulaIngles1 = new Aula(90, 30);
		Curso cursoIng1 = new Curso(2323, ingles1, horario, ciclo);

		// Materia INGLES 2
		Materia ingles2 = new Materia(2468, "INGLES TECNICO 2");
		Aula aulaIngles2 = new Aula(80, 30);
		Curso cursoIng2 = new Curso(4646, ingles2, horario2, ciclo2);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(ingles1));
		assertTrue(universidad.registrarAula(aulaIngles1));
		assertTrue(universidad.crearCurso(cursoIng1));

		assertTrue(universidad.agregarCicloLectivo(ciclo2));
		assertTrue(universidad.registrarMateria(ingles2));
		assertTrue(universidad.registrarAula(aulaIngles2));
		assertTrue(universidad.crearCurso(cursoIng2));
		assertTrue(universidad.asignarAMateriaCorreleativa(2468, 1234));

		assertTrue(universidad.asignarAulaAlCurso(90, 2323));
		assertTrue(universidad.asignarAulaAlCurso(80, 4646));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		assertTrue(universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion));
		// Registrando notas de ingles 1
		assertTrue(universidad.registrarNota(453232, 2323, new Nota(TipoNota.PRIMER_PARCIAL, 8)));
		assertTrue(universidad.registrarNota(453232, 2323, new Nota(TipoNota.SEGUNDO_PARCIAL, 3)));
		assertTrue(universidad.registrarNota(453232, 2323, new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL, 7)));

		Nota primerParcialObtenido = universidad.obtenerNota(453232, 1234, TipoNota.PRIMER_PARCIAL);
		Nota segundoParcialObtenido = universidad.obtenerNota(453232, 1234, TipoNota.SEGUNDO_PARCIAL);
		Nota recuperatorioObtenido = universidad.obtenerNota(453232, 1234, TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL);

		assertEquals(8, primerParcialObtenido.getPuntaje().intValue());
		assertEquals(3, segundoParcialObtenido.getPuntaje().intValue());
		assertEquals(7, recuperatorioObtenido.getPuntaje().intValue());

		// Inscribiendose al siguiente nivel (ingles 2)
		LocalDate fechaInscripcion2 = LocalDate.of(2024, 2, 24);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 4646, fechaInscripcion2);
		assertEquals(true, resultado);

		CursoAlumno cursoDelAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursoIng2);
		assertNotNull(cursoDelAlumno);

	}

	@Test
	public void queSePuedaAlumnoInscribirAUnaMateriaCorreleativaSiApruebaCorreleativaPeroNoLaPromociona() {
		Universidad universidad = new Universidad("UNLAM");

		/* Ciclo lectivo de Ingles I */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		/* Ciclo lectivo de Ingles II */
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2024, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2024, 7, 14);
		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2024, 2, 1);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2024, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2, Turno.NOCHE);

		// Materia INGLES 1
		Materia ingles1 = new Materia(1234, "INGLES TECNICO 1");
		Aula aulaIngles1 = new Aula(90, 30);
		Curso cursoIng1 = new Curso(2323, ingles1, horario, ciclo);

		// Materia INGLES 2
		Materia ingles2 = new Materia(2468, "INGLES TECNICO 2");
		Aula aulaIngles2 = new Aula(80, 30);
		Curso cursoIng2 = new Curso(4646, ingles2, horario2, ciclo2);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(ingles1));
		assertTrue(universidad.registrarAula(aulaIngles1));
		assertTrue(universidad.crearCurso(cursoIng1));

		assertTrue(universidad.agregarCicloLectivo(ciclo2));
		assertTrue(universidad.registrarMateria(ingles2));
		assertTrue(universidad.registrarAula(aulaIngles2));
		assertTrue(universidad.crearCurso(cursoIng2));
		assertTrue(universidad.asignarAMateriaCorreleativa(2468, 1234));

		assertTrue(universidad.asignarAulaAlCurso(90, 2323));
		assertTrue(universidad.asignarAulaAlCurso(80, 4646));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);
		// Registrando notas de ingles 1
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.PRIMER_PARCIAL, 4));
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.SEGUNDO_PARCIAL, 7));
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.RECUPERATORIO_PRIMER_PARCIAL, 5));

		Nota primerParcialObtenido = universidad.obtenerNota(453232, 1234, TipoNota.PRIMER_PARCIAL);
		Nota segundoParcialObtenido = universidad.obtenerNota(453232, 1234, TipoNota.SEGUNDO_PARCIAL);
		Nota recuperatorioObtenido = universidad.obtenerNota(453232, 1234, TipoNota.RECUPERATORIO_PRIMER_PARCIAL);

		assertEquals(4, primerParcialObtenido.getPuntaje().intValue());
		assertEquals(7, segundoParcialObtenido.getPuntaje().intValue());
		assertEquals(5, recuperatorioObtenido.getPuntaje().intValue());

		// Inscribiendose al siguiente nivel (ingles 2)
		LocalDate fechaInscripcion2 = LocalDate.of(2024, 2, 24);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 4646, fechaInscripcion2);
		assertEquals(true, resultado);

		CursoAlumno cursoDelAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursoIng2);
		assertNotNull(cursoDelAlumno);
	}

	@Test
	public void queNoSePuedaAlumnoInscribirAUnaMateriaCorreleativaSiNoLaCurso() {
		Universidad universidad = new Universidad("UNLAM");

		/* Ciclo lectivo de Ingles I */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		/* Ciclo lectivo de Ingles II */
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2024, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2024, 7, 14);
		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2024, 2, 1);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2024, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2, Turno.NOCHE);

		// Materia INGLES 1
		Materia ingles1 = new Materia(1234, "INGLES TECNICO 1");
		Aula aulaIngles1 = new Aula(90, 30);
		Curso cursoIng1 = new Curso(2323, ingles1, horario, ciclo);

		// Materia INGLES 2
		Materia ingles2 = new Materia(2468, "INGLES TECNICO 2");
		Aula aulaIngles2 = new Aula(80, 30);
		Curso cursoIng2 = new Curso(4646, ingles2, horario2, ciclo2);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(ingles1));
		assertTrue(universidad.registrarAula(aulaIngles1));
		assertTrue(universidad.crearCurso(cursoIng1));

		assertTrue(universidad.agregarCicloLectivo(ciclo2));
		assertTrue(universidad.registrarMateria(ingles2));
		assertTrue(universidad.registrarAula(aulaIngles2));
		assertTrue(universidad.crearCurso(cursoIng2));
		assertTrue(universidad.asignarAMateriaCorreleativa(2468, 1234));

		assertTrue(universidad.asignarAulaAlCurso(90, 2323));
		assertTrue(universidad.asignarAulaAlCurso(80, 4646));

		/*
		 * Inscribiendose y registrando notas de ingles 1 Date fechaInscripcion = new
		 * Date(2023,2,24); universidad.inscribirAlumnoACurso(453232,
		 * 2323,fechaInscripcion); universidad.registrarNota(453232, 2323, new
		 * Nota(TipoNota.PRIMER_PARCIAL,8)); universidad.registrarNota(453232, 2323, new
		 * Nota(TipoNota.SEGUNDO_PARCIAL,4)); universidad.registrarNota(453232, 2323,
		 * new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL,7));
		 */

		// Inscribiendose al siguiente nivel (ingles 2)
		LocalDate fechaInscripcion = LocalDate.of(2024, 2, 24);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 4646, fechaInscripcion);
		assertEquals(false, resultado);

		CursoAlumno cursoDelAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursoIng2);
		assertNull(cursoDelAlumno);
	}

	@Test
	public void queNoSePuedaAlumnoInscribirAUnaMateriaCorreleativaSiDebeRecursarla() {
		Universidad universidad = new Universidad("UNLAM");

		/* Ciclo lectivo de Ingles I */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		/* Ciclo lectivo de Ingles II */
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2024, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2024, 7, 14);
		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2024, 2, 1);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2024, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2, Turno.NOCHE);

		// Materia INGLES 1
		Materia ingles1 = new Materia(1234, "INGLES TECNICO 1");
		Aula aulaIngles1 = new Aula(90, 30);
		Curso cursoIng1 = new Curso(2323, ingles1, horario, ciclo);

		// Materia INGLES 2
		Materia ingles2 = new Materia(2468, "INGLES TECNICO 2");
		Aula aulaIngles2 = new Aula(80, 30);
		Curso cursoIng2 = new Curso(4646, ingles2, horario2, ciclo2);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(ingles1));
		assertTrue(universidad.registrarAula(aulaIngles1));
		assertTrue(universidad.crearCurso(cursoIng1));

		assertTrue(universidad.agregarCicloLectivo(ciclo2));
		assertTrue(universidad.registrarMateria(ingles2));
		assertTrue(universidad.registrarAula(aulaIngles2));
		assertTrue(universidad.crearCurso(cursoIng2));
		assertTrue(universidad.asignarAMateriaCorreleativa(2468, 1234));

		assertTrue(universidad.asignarAulaAlCurso(90, 2323));
		assertTrue(universidad.asignarAulaAlCurso(80, 4646));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion);
		// Registrando notas de ingles 1
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.PRIMER_PARCIAL, 2));
		universidad.registrarNota(453232, 2323, new Nota(TipoNota.SEGUNDO_PARCIAL, 3));

		Nota primerParcialObtenido = universidad.obtenerNota(453232, 1234, TipoNota.PRIMER_PARCIAL);
		Nota segundoParcialObtenido = universidad.obtenerNota(453232, 1234, TipoNota.SEGUNDO_PARCIAL);

		assertEquals(2, primerParcialObtenido.getPuntaje().intValue());
		assertEquals(3, segundoParcialObtenido.getPuntaje().intValue());

		// Inscribiendose al siguiente nivel (ingles 2)
		LocalDate fechaInscripcion2 = LocalDate.of(2024, 2, 24);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 4646, fechaInscripcion2);
		assertEquals(false, resultado);

		CursoAlumno cursoDelAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursoIng2);
		assertNull(cursoDelAlumno);
	}

	@Test
	public void queNoSePuedaAlumnoInscribirAUnaMateriaCorreleativaSiHuboAlMenosUnaQueDebeRecursar() {
		Universidad universidad = new Universidad("UNLAM");

		/* Ciclo lectivo PB1 Y INF GENERAL */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);

		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		/* Ciclo lectivo PW1 */
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2023, 8, 11);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2023, 11, 14);

		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2023, 8, 11);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2023, 8, 30);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2, Turno.NOCHE);

		// Horario3
		ArrayList<Dia> diasCursada3 = new ArrayList<Dia>();
		diasCursada.add(Dia.VIERNES);
		Horario horario3 = new Horario(diasCursada3, Turno.NOCHE);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulapb1 = new Aula(90, 30);
		Curso cursoPb1 = new Curso(1010, pb1, horario, ciclo);

		// Materia INF.GENERAL
		Materia infGeneral = new Materia(2222, "INFORMATICA GENERAL");
		Aula aulainf = new Aula(80, 30);
		Curso cursoInf = new Curso(2020, infGeneral, horario2, ciclo);

		// Materia Programacion Web 1
		Materia pw1 = new Materia(3333, "Programacion Web 1");
		Aula aulapw1 = new Aula(200, 80);
		Curso cursopw = new Curso(3030, pw1, horario3, ciclo2);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aulapb1));
		assertTrue(universidad.crearCurso(cursoPb1));

		assertTrue(universidad.registrarMateria(infGeneral));
		assertTrue(universidad.registrarAula(aulainf));
		assertTrue(universidad.crearCurso(cursoInf));

		// Registrar programacion web 1
		assertTrue(universidad.agregarCicloLectivo(ciclo2));
		assertTrue(universidad.registrarMateria(pw1));
		assertTrue(universidad.registrarAula(aulapw1));
		assertTrue(universidad.asignarAMateriaCorreleativa(3333, 1111));
		assertTrue(universidad.asignarAMateriaCorreleativa(3333, 2222));
		assertTrue(universidad.crearCurso(cursopw));

		assertTrue(universidad.asignarAulaAlCurso(90, 1010));
		assertTrue(universidad.asignarAulaAlCurso(80, 2020));
		assertTrue(universidad.asignarAulaAlCurso(200, 3030));

		// Registrando notas de PB1 y InfGeneral
		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		assertTrue(universidad.inscribirAlumnoACurso(453232, 1010, fechaInscripcion));
		assertTrue(universidad.inscribirAlumnoACurso(453232, 2020, fechaInscripcion));

		universidad.registrarNota(453232, 1010, new Nota(TipoNota.PRIMER_PARCIAL, 3));
		universidad.registrarNota(453232, 1010, new Nota(TipoNota.SEGUNDO_PARCIAL, 4));

		Nota pParcialPb1 = universidad.obtenerNota(453232, 1111, TipoNota.PRIMER_PARCIAL);
		Nota sParcialPb1 = universidad.obtenerNota(453232, 1111, TipoNota.SEGUNDO_PARCIAL);

		assertEquals(3, pParcialPb1.getPuntaje().intValue());
		assertEquals(4, sParcialPb1.getPuntaje().intValue());

		universidad.registrarNota(453232, 2020, new Nota(TipoNota.PRIMER_PARCIAL, 8));
		universidad.registrarNota(453232, 2020, new Nota(TipoNota.SEGUNDO_PARCIAL, 7));

		Nota infParcial1 = universidad.obtenerNota(453232, 2222, TipoNota.PRIMER_PARCIAL);
		Nota infParcial2 = universidad.obtenerNota(453232, 2222, TipoNota.SEGUNDO_PARCIAL);

		assertEquals(8, infParcial1.getPuntaje().intValue());
		assertEquals(7, infParcial2.getPuntaje().intValue());

		// Inscribiendose a PW1 (En si no puede ya que no aprobo pb1)
		LocalDate fechaInscripcion2 = LocalDate.of(2023, 8, 24);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 3030, fechaInscripcion2);
		assertEquals(false, resultado);

		CursoAlumno cursoDelAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursopw);
		assertNull(cursoDelAlumno);
	}
	
	@Test
	public void queNoPuedaPromocionarSiElAlumnoDebeUnFinalCorreleativaDeLaMateria() {
		Universidad universidad = new Universidad("UNLAM");

		/* Ciclo lectivo PB1 Y INF GENERAL */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);

		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		/* Ciclo lectivo PW1 */
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2023, 8, 11);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2023, 11, 14);

		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2023, 8, 11);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2023, 8, 30);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Horario2
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada.add(Dia.JUEVES);
		Horario horario2 = new Horario(diasCursada2, Turno.NOCHE);

		// Horario3
		ArrayList<Dia> diasCursada3 = new ArrayList<Dia>();
		diasCursada.add(Dia.VIERNES);
		Horario horario3 = new Horario(diasCursada3, Turno.NOCHE);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulapb1 = new Aula(90, 30);
		Curso cursoPb1 = new Curso(1010, pb1, horario, ciclo);

		// Materia INF.GENERAL
		Materia infGeneral = new Materia(2222, "INFORMATICA GENERAL");
		Aula aulainf = new Aula(80, 30);
		Curso cursoInf = new Curso(2020, infGeneral, horario2, ciclo);

		// Materia Programacion Web 1
		Materia pw1 = new Materia(3333, "Programacion Web 1");
		Aula aulapw1 = new Aula(200, 80);
		Curso cursopw = new Curso(3030, pw1, horario3, ciclo2);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aulapb1));
		assertTrue(universidad.crearCurso(cursoPb1));

		assertTrue(universidad.registrarMateria(infGeneral));
		assertTrue(universidad.registrarAula(aulainf));
		assertTrue(universidad.crearCurso(cursoInf));

		// Registrar programacion web 1
		assertTrue(universidad.agregarCicloLectivo(ciclo2));
		assertTrue(universidad.registrarMateria(pw1));
		assertTrue(universidad.registrarAula(aulapw1));
		assertTrue(universidad.asignarAMateriaCorreleativa(3333, 1111));
		assertTrue(universidad.asignarAMateriaCorreleativa(3333, 2222));
		assertTrue(universidad.crearCurso(cursopw));

		assertTrue(universidad.asignarAulaAlCurso(90, 1010));
		assertTrue(universidad.asignarAulaAlCurso(80, 2020));
		assertTrue(universidad.asignarAulaAlCurso(200, 3030));

		// Registrando notas de PB1 y InfGeneral
		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		assertTrue(universidad.inscribirAlumnoACurso(453232, 1010, fechaInscripcion));
		assertTrue(universidad.inscribirAlumnoACurso(453232, 2020, fechaInscripcion));

		//Esta para final
		universidad.registrarNota(453232, 1010, new Nota(TipoNota.PRIMER_PARCIAL, 6));
		universidad.registrarNota(453232, 1010, new Nota(TipoNota.SEGUNDO_PARCIAL, 10));
		universidad.registrarNota(453232, 1010, new Nota(TipoNota.RECUPERATORIO_PRIMER_PARCIAL, 5));
		
		Nota pParcialPb1 = universidad.obtenerNota(453232, 1111, TipoNota.PRIMER_PARCIAL);
		Nota sParcialPb1 = universidad.obtenerNota(453232, 1111, TipoNota.SEGUNDO_PARCIAL);
		Nota pRecuperatorio = universidad.obtenerNota(453232, 1111, TipoNota.RECUPERATORIO_PRIMER_PARCIAL);

		assertEquals(6, pParcialPb1.getPuntaje().intValue());
		assertEquals(10, sParcialPb1.getPuntaje().intValue());
		assertEquals(5, pRecuperatorio.getPuntaje().intValue());

		universidad.registrarNota(453232, 2020, new Nota(TipoNota.PRIMER_PARCIAL, 8));
		universidad.registrarNota(453232, 2020, new Nota(TipoNota.SEGUNDO_PARCIAL, 7));

		Nota infParcial1 = universidad.obtenerNota(453232, 2222, TipoNota.PRIMER_PARCIAL);
		Nota infParcial2 = universidad.obtenerNota(453232, 2222, TipoNota.SEGUNDO_PARCIAL);

		assertEquals(8, infParcial1.getPuntaje().intValue());
		assertEquals(7, infParcial2.getPuntaje().intValue());

		LocalDate fechaInscripcion2 = LocalDate.of(2023, 8, 24);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 3030, fechaInscripcion2);
		assertEquals(true, resultado);

		CursoAlumno cursoDelAlumno = universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumno, cursopw);
		assertNotNull(cursoDelAlumno);
				
		universidad.registrarNota(453232, 3030, new Nota(TipoNota.PRIMER_PARCIAL, 8));
		universidad.registrarNota(453232, 3030, new Nota(TipoNota.SEGUNDO_PARCIAL, 10));	
		
		assertEquals(6, universidad.calcularPromedio(453232, 3030).intValue()); //NO PROMOCIONA
	}
	
	@Test
	public void queNoSePuedaAgregarMasAlumnosSiElAulaEstaLleno() {
		Universidad universidad = new Universidad("UNLAM");
		/* 1ER CUATRIMESTRE 2023 */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111, 3); // Tiene disponible 3 lugares
		Curso cursoPB1 = new Curso(2323, pb1, horario, ciclo);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2004, 2, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);

		Alumno alumnoUno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);
		Alumno alumnoDos = new Alumno(444223, "Eze", "Flores", fechaNacimiento, fechaIngreso);
		Alumno alumnoTres = new Alumno(432321, "Gonza", "Leonel", fechaNacimiento, fechaIngreso);
		Alumno alumnoCuatro = new Alumno(444222, "Alex", "Diaz", fechaNacimiento, fechaIngreso);

		assertTrue(universidad.registrarAlumno(alumnoUno));
		assertTrue(universidad.registrarAlumno(alumnoDos));
		assertTrue(universidad.registrarAlumno(alumnoTres));
		assertTrue(universidad.registrarAlumno(alumnoCuatro));

		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aulaPB1));
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.crearCurso(cursoPB1));
		assertTrue(universidad.asignarAulaAlCurso(111, 2323));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 2);
		assertTrue(universidad.inscribirAlumnoACurso(453232, 2323, fechaInscripcion));
		assertTrue(universidad.inscribirAlumnoACurso(444223, 2323, fechaInscripcion));
		assertTrue(universidad.inscribirAlumnoACurso(432321, 2323, fechaInscripcion));
		assertFalse(universidad.inscribirAlumnoACurso(444222, 2323, fechaInscripcion));

		assertNotNull(universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumnoUno, cursoPB1));
		assertNotNull(universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumnoDos, cursoPB1));
		assertNotNull(universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumnoTres, cursoPB1));
		assertNull(universidad.buscarAsignacionAlumnoPorAlumnoCurso(alumnoCuatro, cursoPB1));
	}

	/*
	 * Tests con profesor: No se puede asignar un profesor para dos cursos en el
	 * mismo horario y ciclo lectivo (1er cuatri,2do cuatri, fecha de
	 * inicialización).
	 */
	@Test
	public void queSeAsigneUnProfeACurso() {

		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia infGeneral = new Materia(1111, "INFORMATICA GENERAL");

		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		Aula aula = new Aula(215, 84);

		// HORARIO 1
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);

		// HORARIO 2
		ArrayList<Dia> diasCursada2 = new ArrayList<>();
		diasCursada.add(Dia.MARTES);

		Horario horario = new Horario(diasCursada, Turno.MAÑANA);
		Horario horario2 = new Horario(diasCursada2, Turno.NOCHE);

		// CURSO
		Curso curso = new Curso(2424, pb1, horario2, ciclo);
		Curso otroCurso = new Curso(4242, infGeneral, horario, ciclo);
		// PROFESOR
		Profesor profesor = new Profesor(4432323, "Andres", "Borgeat");
		// ALUMNO
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		Universidad universidad = new Universidad("UNLAM");

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(infGeneral));
		assertTrue(universidad.registrarProfesor(profesor));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.registrarAlumno(alumno));

		assertTrue(universidad.crearCurso(curso));
		assertTrue(universidad.crearCurso(otroCurso));

		assertTrue(universidad.asignarAulaAlCurso(215, 2424));
		assertTrue(universidad.asignarAulaAlCurso(215, 4242));

		// Agrego el alumno en los cursos ya que almenos debe haber 1 para que el profe
		// ingrese a dicho curso
		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);

		assertTrue(universidad.inscribirAlumnoACurso(453232, 2424, fechaInscripcion));
		assertTrue(universidad.inscribirAlumnoACurso(453232, 4242, fechaInscripcion));

		Integer dniProfesor = 4432323;
		Integer codigo = 2424;
		universidad.agregarProfesorACurso(dniProfesor, codigo);
		codigo = 4242;
		Boolean resultado = universidad.agregarProfesorACurso(dniProfesor, 4242);
		assertEquals(true, resultado);

		CursoProfesor cursoProfe = universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		assertNotNull(cursoProfe);
		assertEquals(curso, cursoProfe.getCurso());
		assertEquals(profesor, cursoProfe.getProfesor());
	}

	@Test
	public void queSeAsigneUnProfeACursoSiEsDisntintoElCicloLectivo() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia infGeneral = new Materia(1111, "INFORMATICA GENERAL");

		// 1 CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// 1 CUATRIMESTRE 2022
		LocalDate fechaInicioCicloLectivo2 = LocalDate.of(2022, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo2 = LocalDate.of(2022, 7, 14);
		LocalDate fechaInicioInscripcion2 = LocalDate.of(2022, 2, 1);
		LocalDate fechaFinalizacionInscripcion2 = LocalDate.of(2022, 2, 28);

		CicloLectivo ciclo2 = new CicloLectivo(fechaInicioCicloLectivo2, fechaFinalizacionCicloLectivo2,
				fechaInicioInscripcion2, fechaFinalizacionInscripcion2);

		// AULA
		Aula aula = new Aula(215, 84);

		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// CURSO
		Curso curso = new Curso(2424, pb1, horario, ciclo);
		Curso otroCurso = new Curso(4242, infGeneral, horario, ciclo2);
		// PROFESOR
		Profesor profesor = new Profesor(4432323, "Andres", "Borgeat");
		// ALUMNO
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		Universidad universidad = new Universidad("UNLAM");

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.agregarCicloLectivo(ciclo2));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(infGeneral));
		assertTrue(universidad.registrarProfesor(profesor));
		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.crearCurso(curso));
		assertTrue(universidad.crearCurso(otroCurso));

		assertTrue(universidad.asignarAulaAlCurso(215, 2424));
		assertTrue(universidad.asignarAulaAlCurso(215, 4242));

		// Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los
		// cursos
		// Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		LocalDate fechaInscripcion2 = LocalDate.of(2022, 2, 10);
		assertTrue(universidad.inscribirAlumnoACurso(453232, 4242, fechaInscripcion2));

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		assertTrue(universidad.inscribirAlumnoACurso(453232, 2424, fechaInscripcion));

		Integer dniProfesor = 4432323;
		Boolean resultado = universidad.agregarProfesorACurso(dniProfesor, 2424);
		assertEquals(true, resultado);
		resultado = universidad.agregarProfesorACurso(dniProfesor, 4242);
		assertEquals(true, resultado);

		CursoProfesor pb1Profe = universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		CursoProfesor infGeneralProfe = universidad.buscarAsignacionProfesorPorCursoProfe(otroCurso, profesor);

		assertNotNull(pb1Profe);
		assertNotNull(infGeneralProfe);

		assertEquals(curso, pb1Profe.getCurso());
		assertEquals(profesor, pb1Profe.getProfesor());

		assertEquals(otroCurso, infGeneralProfe.getCurso());
		assertEquals(profesor, infGeneralProfe.getProfesor());
	}

	@Test
	public void queSeAsigneUnProfeACursoSiEsDisntintoElTurnoDeLosCursos() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia infGeneral = new Materia(1111, "INFORMATICA GENERAL");

		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		Aula aula = new Aula(215, 84);

		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);
		Horario horario2 = new Horario(diasCursada, Turno.NOCHE);

		// CURSO
		Curso curso = new Curso(2424, pb1, horario, ciclo);
		Curso otroCurso = new Curso(4242, infGeneral, horario2, ciclo);
		// PROFESOR
		Profesor profesor = new Profesor(4432323, "Andres", "Borgeat");
		// ALUMNO
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		Universidad universidad = new Universidad("UNLAM");

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(infGeneral));
		assertTrue(universidad.registrarProfesor(profesor));
		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.crearCurso(curso));
		assertTrue(universidad.crearCurso(otroCurso));
		assertTrue(universidad.asignarAulaAlCurso(215, 4242));
		assertTrue(universidad.asignarAulaAlCurso(215, 2424));

		// Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los
		// cursos
		// Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		assertTrue(universidad.inscribirAlumnoACurso(453232, 2424, fechaInscripcion));
		assertTrue(universidad.inscribirAlumnoACurso(453232, 4242, fechaInscripcion));

		Integer dniProfesor = 4432323;

		Boolean resultado = universidad.agregarProfesorACurso(dniProfesor, 2424);
		assertEquals(true, resultado);

		resultado = universidad.agregarProfesorACurso(dniProfesor, 4242);
		assertEquals(true, resultado);

		CursoProfesor pb1Profe = universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		CursoProfesor infGeneralProfe = universidad.buscarAsignacionProfesorPorCursoProfe(otroCurso, profesor);

		assertNotNull(pb1Profe);
		assertNotNull(infGeneralProfe);

		assertEquals(curso, pb1Profe.getCurso());
		assertEquals(profesor, pb1Profe.getProfesor());

		assertEquals(otroCurso, infGeneralProfe.getCurso());
		assertEquals(profesor, infGeneralProfe.getProfesor());
	}

	@Test
	public void queNoSePuedaAsociarCursoYProfesorDosVeces() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");

		// 1 CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// AULA
		Aula aula = new Aula(215, 84);

		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// CURSO
		Curso curso = new Curso(2424, pb1, horario, ciclo);
		// PROFESOR
		Profesor profesor = new Profesor(4432323, "Andres", "Borgeat");
		// ALUMNO
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarProfesor(profesor));
		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.crearCurso(curso));
		assertTrue(universidad.asignarAulaAlCurso(215, 2424));

		// Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los
		// cursos
		// Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		universidad.inscribirAlumnoACurso(453232, 2424, fechaInscripcion);

		Integer dniProfesor = 4432323;
		Integer codigo = 2424;
		Boolean resultado = universidad.agregarProfesorACurso(dniProfesor, codigo);
		assertEquals(true, resultado);
		resultado = universidad.agregarProfesorACurso(dniProfesor, codigo);
		assertEquals(false, resultado);

	}

	@Test
	public void queNoSePuedaAsociarCursoYProfesorSiProfesorEstaOcupado() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");
		Materia infGeneral = new Materia(1111, "INFORMATICA GENERAL");

		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// AULA
		Aula aula = new Aula(215, 84);
		Aula aula2 = new Aula(200, 100);

		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);
		Horario horario2 = new Horario(diasCursada, Turno.MAÑANA);

		// CURSO
		Curso curso = new Curso(2424, pb1, horario, ciclo);
		Curso otroCurso = new Curso(4242, infGeneral, horario2, ciclo);
		// PROFESOR
		Profesor profesor = new Profesor(4432323, "Andres", "Borgeat");
		// ALUMNO
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		// ALUMNO 2
		LocalDate fechaNacimiento2 = LocalDate.of(2003, 1, 4);
		LocalDate fechaIngreso2 = LocalDate.of(2020, 2, 1);
		Alumno alumno2 = new Alumno(232323, "Gonza", "Leonel", fechaNacimiento2, fechaIngreso2);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarMateria(infGeneral));
		assertTrue(universidad.registrarProfesor(profesor));
		assertTrue(universidad.registrarAlumno(alumno));
		assertTrue(universidad.registrarAlumno(alumno2));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.registrarAula(aula2));
		assertTrue(universidad.crearCurso(curso));
		assertTrue(universidad.crearCurso(otroCurso));

		assertTrue(universidad.asignarAulaAlCurso(215, 2424));
		assertTrue(universidad.asignarAulaAlCurso(200, 4242));

		// Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los
		// cursos
		// Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		LocalDate fechaInscripcion2 = LocalDate.of(2023, 2, 8);

		assertTrue(universidad.inscribirAlumnoACurso(453232, 2424, fechaInscripcion));
		assertTrue(universidad.inscribirAlumnoACurso(453232, 4242, fechaInscripcion2));

		Integer dniProfesor = 4432323;
		Boolean resultado = universidad.agregarProfesorACurso(dniProfesor, 2424);
		assertEquals(true, resultado);

		resultado = universidad.agregarProfesorACurso(dniProfesor, 4242); // Ocupado
		assertEquals(false, resultado);

		CursoProfesor pb1Profe = universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		CursoProfesor infGeneralProfe = universidad.buscarAsignacionProfesorPorCursoProfe(otroCurso, profesor);

		assertNotNull(pb1Profe);
		assertEquals(curso, pb1Profe.getCurso());
		assertEquals(profesor, pb1Profe.getProfesor());

		assertNull(infGeneralProfe);
	}

	@Test
	public void queNoSeAsigneUnProfeACursoSiProfesorNoEstaRegistrado() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");

		// 1 CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// AULA
		Aula aula = new Aula(215, 84);

		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// CURSO
		Curso curso = new Curso(2424, pb1, horario, ciclo);
		// PROFESOR
		Profesor profesor = new Profesor(4432323, "Andres", "Borgeat");
		// ALUMNO
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		Universidad universidad = new Universidad("UNLAM");
		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.registrarAlumno(alumno));
		// universidad.registrarProfesor(profesor);
		assertTrue(universidad.crearCurso(curso));
		assertTrue(universidad.asignarAulaAlCurso(215, 2424));

		// Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los
		// cursos
		// Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 8);
		universidad.inscribirAlumnoACurso(453232, 2424, fechaInscripcion);

		Integer dniProfesor = 4432323;
		Integer codigo = 2424;

		Boolean resultado = universidad.agregarProfesorACurso(dniProfesor, codigo);
		assertEquals(false, resultado);

		CursoProfesor pb1Profe = universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		assertNull(pb1Profe);
	}

	@Test
	public void queNoSeAsigneUnProfeACursoSiCursoNoEstaRegistrado() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");

		// 1ER CUATRIMESTRE 2023
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		// AULA
		Aula aula = new Aula(215, 84);

		// HORARIO
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// CURSO
		Curso curso = new Curso(2424, pb1, horario, ciclo);
		// PROFESOR
		Profesor profesor = new Profesor(4432323, "Andres", "Borgeat");
		// ALUMNO
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(453232, "Alan", "Aruquipa", fechaNacimiento, fechaIngreso);

		Universidad universidad = new Universidad("UNLAM");

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		universidad.registrarMateria(pb1);
		universidad.registrarAula(aula);
		universidad.registrarProfesor(profesor);
		universidad.registrarAlumno(alumno);
		// universidad.crearCurso(curso);

		// Agrego el alumno en los cursos ya que sino el profe no podra ingresar a los
		// cursos
		// Nota: debe haber minimo 1 alumno para que un profesor ingrese a dicho curso
		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 8);
		Boolean resultado = universidad.inscribirAlumnoACurso(453232, 2424, fechaInscripcion);
		assertEquals(false, resultado);

		Integer dniProfesor = 4432323;
		Integer codigo = 2424;
		universidad.agregarProfesorACurso(dniProfesor, codigo);
		resultado = universidad.agregarProfesorACurso(dniProfesor, codigo);
		assertEquals(false, resultado);

		CursoProfesor pb1Profe = universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesor);
		assertNull(pb1Profe);
	}

	@Test
	public void queSePuedaAsignar1ProfesorPorCada20Alumnos() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");

		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		Aula aula = new Aula(215, 84);

		// HORARIO 1
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// CURSO
		Curso curso = new Curso(2424, pb1, horario, ciclo);
		// PROFESORES
		Profesor profesorUno = new Profesor(4432323, "Andres", "Borgeat");
		Profesor profesorDos = new Profesor(4422332, "Juan", "Monteagudo");
		Profesor profesorTres = new Profesor(4323232, "Alejandro", "Goitea");

		Universidad universidad = new Universidad("UNLAM");

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarProfesor(profesorUno));
		assertTrue(universidad.registrarProfesor(profesorDos));
		assertTrue(universidad.registrarProfesor(profesorTres));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.crearCurso(curso));
		assertTrue(universidad.asignarAulaAlCurso(215, 2424));

		// ALUMNO
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		for (int i = 0; i < 75; i++) {
			Alumno aux = new Alumno(i, "Nombre " + i, "Apellido " + i, fechaNacimiento, fechaIngreso);
			assertTrue(universidad.registrarAlumno(aux));
			assertTrue(universidad.inscribirAlumnoACurso(i, 2424, fechaInscripcion));
		}

		assertTrue(universidad.agregarProfesorACurso(4432323, 2424));
		assertTrue(universidad.agregarProfesorACurso(4422332, 2424));
		assertTrue(universidad.agregarProfesorACurso(4323232, 2424));

		// universidad.agregarProfesorACurso(dniProfesor, 2424);

		assertNotNull(universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesorUno));
		assertNotNull(universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesorDos));
		assertNotNull(universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesorTres));

	}

	@Test
	public void queNoSePuedanAgregar3ProfesoresSiHayMenosDe40Inscriptos() {
		Materia pb1 = new Materia(2232, "PROGRAMACION BASICA 1");

		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);
		Aula aula = new Aula(215, 84);

		// HORARIO 1
		ArrayList<Dia> diasCursada = new ArrayList<>();
		diasCursada.add(Dia.LUNES);
		diasCursada.add(Dia.JUEVES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);

		// CURSO
		Curso curso = new Curso(2424, pb1, horario, ciclo);
		// PROFESORES
		Profesor profesorUno = new Profesor(4432323, "Andres", "Borgeat");
		Profesor profesorDos = new Profesor(4422332, "Juan", "Monteagudo");
		Profesor profesorTres = new Profesor(4323232, "Alejandro", "Goitea");

		Universidad universidad = new Universidad("UNLAM");

		assertTrue(universidad.agregarCicloLectivo(ciclo));
		assertTrue(universidad.registrarMateria(pb1));
		assertTrue(universidad.registrarProfesor(profesorUno));
		assertTrue(universidad.registrarProfesor(profesorDos));
		assertTrue(universidad.registrarProfesor(profesorTres));
		assertTrue(universidad.registrarAula(aula));
		assertTrue(universidad.crearCurso(curso));
		assertTrue(universidad.asignarAulaAlCurso(215, 2424));

		// ALUMNO
		LocalDate fechaNacimiento = LocalDate.of(2004, 1, 4);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		for (int i = 0; i < 40; i++) {
			Alumno aux = new Alumno(i, "Nombre " + i, "Apellido " + i, fechaNacimiento, fechaIngreso);
			assertTrue(universidad.registrarAlumno(aux));
			assertTrue(universidad.inscribirAlumnoACurso(i, 2424, fechaInscripcion));
		}

		assertTrue(universidad.agregarProfesorACurso(4432323, 2424));
		assertTrue(universidad.agregarProfesorACurso(4422332, 2424));
		assertFalse(universidad.agregarProfesorACurso(4323232, 2424));

		assertNotNull(universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesorUno));
		assertNotNull(universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesorDos));
		assertNull(universidad.buscarAsignacionProfesorPorCursoProfe(curso, profesorTres));
	}

	@Test
	public void queSePuedaAsignarPlanDeEstudios() {
		Universidad uni = new Universidad("UNLAM");
		Materia materia1 = new Materia(1, "pb1");
		Materia materia2 = new Materia(2, "pb2");
		Materia materia3 = new Materia(3, "tallerWeb1");
		ArrayList<Materia> planDeEstudios = new ArrayList<>();
		planDeEstudios.add(materia1);
		planDeEstudios.add(materia2);
		planDeEstudios.add(materia3);

		uni.asignarPlanDeEstudios(planDeEstudios);

		assertEquals(uni.obtenerPlanDeEstudios(), planDeEstudios);
	}

	@Test
	public void queDevuelvaMateriasAprobadasPorAlumno() {
		Universidad uni = new Universidad("UNLAM");
		ArrayList<Materia> planDeEstudios = new ArrayList<>();
		/* Ciclo lectivo */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1); 
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);

		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada2.add(Dia.MARTES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);
		Horario horario2 = new Horario(diasCursada2, Turno.MAÑANA);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111, 100);
		Curso cursoPB1 = new Curso(2323, pb1, horario, ciclo);
		planDeEstudios.add(pb1);

		// Materia PB2
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");
		Aula aulaPB2 = new Aula(222, 100);
		Curso cursoPB2 = new Curso(2424, pb2, horario2, ciclo);
		planDeEstudios.add(pb2);
		uni.asignarPlanDeEstudios(planDeEstudios);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2001, 7, 10);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(43506696, "Gonzalo", "Viale", fechaNacimiento, fechaIngreso);

		
		uni.agregarCicloLectivo(ciclo);
		uni.registrarAlumno(alumno);
		uni.registrarMateria(pb1);
		uni.registrarAula(aulaPB1);
		uni.crearCurso(cursoPB1);
		uni.asignarAulaAlCurso(111, 2323);

		uni.registrarMateria(pb2);
		uni.registrarAula(aulaPB2);
		uni.crearCurso(cursoPB2);
		uni.asignarAulaAlCurso(222, 2424);

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		uni.inscribirAlumnoACurso(43506696, 2323, fechaInscripcion);
		LocalDate fechaInscripcion2 = LocalDate.of(2023, 2, 24);
		uni.inscribirAlumnoACurso(43506696, 2424, fechaInscripcion2);

		Integer dniAlumno = 43506696;
		Integer codCurso = 2323;

		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL, 8);
		Nota segundoParcial = new Nota(TipoNota.SEGUNDO_PARCIAL, 6);
		Nota recuperatorio2doParcial = new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL, 7);

		uni.registrarNota(dniAlumno, codCurso, primerParcial);
		uni.registrarNota(dniAlumno, codCurso, segundoParcial);
		uni.registrarNota(dniAlumno, codCurso, recuperatorio2doParcial);

		uni.agregarMateriaAprobada(cursoPB1, alumno, pb1);
		ArrayList<Materia> aprobadas = uni.obtenerMateriasAprobadasParaUnAlumno(dniAlumno);

		assertEquals(pb1, aprobadas.get(0));
	}

	@Test
	public void queDevuelvaMateriasQueFaltanCursarPorAlumno() {
		Universidad uni = new Universidad("UNLAM");
		ArrayList<Materia> planDeEstudios = new ArrayList<>();
		/* Ciclo lectivo */
		LocalDate fechaInicioCicloLectivo = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionCicloLectivo = LocalDate.of(2023, 7, 14);

		// Fecha de inicio y finalizacion de inscripcion
		LocalDate fechaInicioInscripcion = LocalDate.of(2023, 2, 1);
		LocalDate fechaFinalizacionInscripcion = LocalDate.of(2023, 2, 28);

		CicloLectivo ciclo = new CicloLectivo(fechaInicioCicloLectivo, fechaFinalizacionCicloLectivo,
				fechaInicioInscripcion, fechaFinalizacionInscripcion);

		// Horario
		ArrayList<Dia> diasCursada = new ArrayList<Dia>();
		diasCursada.add(Dia.LUNES);
		ArrayList<Dia> diasCursada2 = new ArrayList<Dia>();
		diasCursada2.add(Dia.MARTES);
		Horario horario = new Horario(diasCursada, Turno.MAÑANA);
		Horario horario2 = new Horario(diasCursada2, Turno.MAÑANA);

		// Materia PB1
		Materia pb1 = new Materia(1111, "PROGRAMACION BASICA 1");
		Aula aulaPB1 = new Aula(111, 100);
		Curso cursoPB1 = new Curso(2323, pb1, horario, ciclo);
		planDeEstudios.add(pb1);

		// Materia PB2
		Materia pb2 = new Materia(2222, "PROGRAMACION BASICA 2");
		Aula aulaPB2 = new Aula(222, 100);
		Curso cursoPB2 = new Curso(2424, pb2, horario2, ciclo);
		planDeEstudios.add(pb2);
		uni.asignarPlanDeEstudios(planDeEstudios);

		// Alumno
		LocalDate fechaNacimiento = LocalDate.of(2001, 7, 10);
		LocalDate fechaIngreso = LocalDate.of(2023, 2, 1);
		Alumno alumno = new Alumno(43506696, "Gonzalo", "Viale", fechaNacimiento, fechaIngreso);

		uni.agregarCicloLectivo(ciclo);
		uni.registrarAlumno(alumno);
		uni.registrarMateria(pb1);
		uni.registrarAula(aulaPB1);
		uni.crearCurso(cursoPB1);
		uni.asignarAulaAlCurso(111, 2323);

		uni.registrarMateria(pb2);
		uni.registrarAula(aulaPB2);
		uni.crearCurso(cursoPB2);
		uni.asignarAulaAlCurso(222, 2424);

		LocalDate fechaInscripcion = LocalDate.of(2023, 2, 24);
		uni.inscribirAlumnoACurso(43506696, 2323, fechaInscripcion);
		LocalDate fechaInscripcion2 = LocalDate.of(2023, 2, 24);
		uni.inscribirAlumnoACurso(43506696, 2424, fechaInscripcion2);

		Integer dniAlumno = 43506696;
		Integer codCurso = 2323;

		Nota primerParcial = new Nota(TipoNota.PRIMER_PARCIAL, 8);
		Nota segundoParcial = new Nota(TipoNota.SEGUNDO_PARCIAL, 6);
		Nota recuperatorio2doParcial = new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL, 7);

		uni.registrarNota(dniAlumno, codCurso, primerParcial);
		uni.registrarNota(dniAlumno, codCurso, segundoParcial);
		uni.registrarNota(dniAlumno, codCurso, recuperatorio2doParcial);

		uni.agregarMateriaAprobada(cursoPB1, alumno, pb1);
		ArrayList<Materia> faltanCursar = uni.obtenerMateriasQueFaltanCursarParaUnAlumno(dniAlumno);
		ArrayList<Materia> faltanCursarEsperadas = new ArrayList<>();
		faltanCursarEsperadas.add(pb2);

		assertEquals(faltanCursarEsperadas, faltanCursar);
	}

}