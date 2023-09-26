package ar.edu.unlam.dominio;
import java.util.ArrayList;
import ar.edu.unlam.utils.TipoNota;

public class CursoAlumno {
	
	private Integer id;
	private Curso curso;
	private Alumno alumno;
	private AlumnoNotas notas;
	
	public CursoAlumno(Integer id, Curso curso, Alumno alumno) {
		this.id = id;
		this.curso = curso;
		this.alumno = alumno;
		this.notas = new AlumnoNotas();
	}

	public Curso getCurso() {
		return this.curso;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public Integer getId() {
		return this.id;
	}
	public AlumnoNotas getNotas() {
		return this.notas;
	}
	
	public Double obtenerNotaFinal() {
		ArrayList<Nota> notasRelevantes = obtenerNotasRelevantes();
		Double  promedio = 0.0;
		Double suma = 0.0;
		
		for(int i = 0; i<notasRelevantes.size(); i++) 
			suma += notasRelevantes.get(i).getPuntaje();
		
		
		promedio = Math.floor(suma/notasRelevantes.size());
		return promedio;
	}

	private ArrayList<Nota> obtenerNotasRelevantes() {
		ArrayList<Nota>notasRelevantes = new ArrayList<>();
		
		if(this.notas.getRecuperatorio() != null){ // ME FIJO SI RINDIO RECUPERATORIO
			Nota notaMayor = null;
			TipoNota tipoRecu = this.notas.getRecuperatorio().getTipoNota();
			
			if(tipoRecu.equals(TipoNota.RECUPERATORIO_PRIMER_PARCIAL)) {
				notasRelevantes.add(notas.getSegundoParcial());
				notaMayor = obtenerNotaMayorRecuperatorio(this.notas.getPrimerParcial(), this.notas.getRecuperatorio());
			}else {
				notasRelevantes.add(notas.getPrimerParcial());
				notaMayor = obtenerNotaMayorRecuperatorio(this.notas.getSegundoParcial(), this.notas.getRecuperatorio());
			
			}
			notasRelevantes.add(notaMayor);			
		}else { //EN CASO DE QUE PROMOCIONO O NO APROBO LOS DOS PARCIALES CON UN MINIMO DE 4 (RECURSA)
			notasRelevantes.add(this.notas.getPrimerParcial());
			notasRelevantes.add(this.notas.getSegundoParcial());
		}
		return notasRelevantes;
	}
	
	public boolean estaCursando() {
		Double resultado = obtenerNotaFinal();
		Boolean aprobo = false; 
		
		if(resultado>=4)
			aprobo = true;
		
		return aprobo;
	}
	
	public boolean estaParaFinal() {
		if(!estaCursando())
			return false;
		
		ArrayList<Nota> notasMayores = obtenerNotasRelevantes();
		for(Nota nota: notasMayores) {
			if(nota.getPuntaje()<7)
				return true;
		}
		
		return false;
		
	}
	
	public boolean estaPromocionado() {	
		return (obtenerNotaFinal() >= 7
				)&& (estaParaFinal() != true);
	}
	
	private Nota obtenerNotaMayorRecuperatorio(Nota parcial, Nota recu) {
		Nota aux = null;
		if(parcial.getPuntaje() > recu.getPuntaje())
			aux = parcial;
		else 
			aux = recu;
		return aux;
	}

	public Boolean asignarNota(Nota nota) {
		return this.notas.calificar(nota.getTipoNota(), nota.getPuntaje());
	}

	
}