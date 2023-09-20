package ar.com.unlam.dominio;
import ar.com.unlam.utils.TipoNota;
public class AlumnoNotas {
	
	private Nota primerParcial;
	private Nota segundoParcial;
	private Nota recuperatorio; // recuperatorio de uno de los parciales
	
	public AlumnoNotas() {
		this.primerParcial = new Nota(TipoNota.PRIMER_PARCIAL);
		this.segundoParcial = new Nota(TipoNota.SEGUNDO_PARCIAL);
		this.recuperatorio = null;
	}
	
	
	public Boolean calificar(TipoNota tipo , Integer puntaje) {
		Boolean exito = false;
		switch(tipo) {
			case PRIMER_PARCIAL:
				exito = this.primerParcial.asignarPuntaje(puntaje);
				break;
			case SEGUNDO_PARCIAL:
				exito = this.segundoParcial.asignarPuntaje(puntaje);
				break;
			case RECUPERATORIO_PRIMER_PARCIAL:
			case RECUPERATORIO_SEGUNDO_PARCIAL:
				if(debeRecuperarAmbas() != true) {
					if(debeRecuperar(this.primerParcial) && this.recuperatorio == null)
						this.recuperatorio = new Nota(TipoNota.RECUPERATORIO_PRIMER_PARCIAL);
					if(debeRecuperar(this.segundoParcial) && this.recuperatorio == null)
						this.recuperatorio = new Nota(TipoNota.RECUPERATORIO_SEGUNDO_PARCIAL);
					
					if(this.recuperatorio!=null)
						exito = this.recuperatorio.asignarPuntaje(puntaje);
				}
				break;
		}
		return exito;
	}
	

	
	private boolean debeRecuperarAmbas() {
		return debeRecuperar(primerParcial) && debeRecuperar(segundoParcial);
	}
	private boolean debeRecuperar(Nota nota) {
		return nota.getPuntaje()<7;
	}

	public Nota getPrimerParcial() {
		return primerParcial;
	}


	public Nota getSegundoParcial() {
		return segundoParcial;
	}


	public Nota getRecuperatorio() {
		return recuperatorio;
	}


}