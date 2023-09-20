package ar.edu.unlam.dominio;
import java.util.ArrayList;
import java.util.Objects;
public class Materia {

	
	private Integer codigoMateria;
	private String nombreMateria;
	private ArrayList<Materia> correleativas;
	

	public Materia(Integer codigoMateria) {
		this.codigoMateria  = codigoMateria;
	}
	public Materia(Integer codigoMateria, String nombreMateria) {
		this.codigoMateria = codigoMateria;
		this.nombreMateria = nombreMateria;
		this.correleativas = new ArrayList<>();
	}

	public Integer getCodigo() {
		return this.codigoMateria;
	}

	public String getNombre() {
		return this.nombreMateria;
	}

	public ArrayList<Materia> getCorreleativas() {
		return this.correleativas;
	}
	
	
	public void agregarCorreleativa(Materia m) {
		this.correleativas.add(m);
	}
	
	public Boolean eliminarCorreleativa(Materia correleativa) {
		if(this.correleativas.contains(correleativa)) {
			this.correleativas.remove(correleativa);
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoMateria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materia other = (Materia) obj;
		return Objects.equals(codigoMateria, other.codigoMateria);
	}


	
	
}
