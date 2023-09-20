package ar.edu.unlam.dominio;
import java.util.ArrayList;
import ar.edu.unlam.utils.Dia;
import ar.edu.unlam.utils.Turno;;
public class Horario {
	
	private ArrayList<Dia> dias;
	private Turno turno;
	
	public Horario(ArrayList<Dia> dias, Turno turno) {
		this.dias = dias;
		this.turno  = turno;
	}
	
	public ArrayList<Dia> getDias(){
		return this.dias;
	}
	public Turno getTurno() {
		return this.turno;
	}
}
