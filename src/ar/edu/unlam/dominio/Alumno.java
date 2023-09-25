package ar.edu.unlam.dominio;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Alumno {

		private Integer dni;
		private String nombre; 
		private String apellido;
		private Date fechaNacimiento;
		private Date fechaIngreso;
		private ArrayList<Materia> materiasAprobadasPorAlumno;
		
		
		public Alumno(Integer dni) {
			this.dni = dni;
		}
		
		public Alumno(Integer dni, String nombre, String apellido, Date fechaNacimiento, Date fechaIngreso) {
			this.dni = dni;
			this.nombre = nombre;
			this.apellido = apellido;
			this.fechaNacimiento = fechaNacimiento;
			this.fechaIngreso = fechaIngreso;
			this.materiasAprobadasPorAlumno = new ArrayList<>();
		}
		
		public void agregarMateriaAprobadaAlArray(Materia materiaAprob) {
			materiasAprobadasPorAlumno.add(materiaAprob);
		}

		public Integer getDni() {
			return this.dni;
		}

		public String getNombre() {
			return this.nombre;
		}

		public String getApellido() {
			return this.apellido;
		}

		public Date getFechaNacimiento() {
			return this.fechaNacimiento;
		}

		public Date getFechaIngreso() {
			return this.fechaIngreso;
		}
		
		public ArrayList<Materia> getMateriasAprobadasPorAlumno() {
			return materiasAprobadasPorAlumno;
		}

		public void setMateriasAprobadasPorAlumno(ArrayList<Materia> materiasAprobadasPorAlumno) {
			this.materiasAprobadasPorAlumno = materiasAprobadasPorAlumno;
		}

		@Override
		public int hashCode() {
			return Objects.hash(dni);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Alumno other = (Alumno) obj;
			return Objects.equals(dni, other.dni);
		}

	}
