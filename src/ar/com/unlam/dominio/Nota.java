package ar.com.unlam.dominio;
import ar.com.unlam.utils.TipoNota;

public class Nota {

	private Integer puntaje;
	private TipoNota tipo;
	
	public Nota(TipoNota tipo) {
		this.puntaje = 0;
		this.tipo = tipo;
	}
	
	public Nota(TipoNota tipo, Integer puntaje) {
		this.tipo = tipo;
		this.puntaje = puntaje;
	}
	
	public Integer getPuntaje() {
		return this.puntaje;
	}
	public TipoNota getTipoNota() {
		return this.tipo;
	}

	public Boolean asignarPuntaje(Integer puntaje) {
		if(this.puntaje != 0) //Quiere decir que se asigno ya una nota anterior
			return false;
		
		if(puntaje < 1 ||puntaje >10)  //Quiere decir que si se sale del rango, no se asigne la nota
			return false;
		
		this.puntaje = puntaje;
		return true;
	}

}