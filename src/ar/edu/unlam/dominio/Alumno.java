package ar.edu.unlam.dominio;
import java.time.LocalDate;
import java.util.Objects;

public class Alumno {

		private Integer dni;
		private String nombre; 
		private String apellido;
		private LocalDate fechaNacimiento;
		private LocalDate fechaIngreso;
		
		
		public Alumno(Integer dni) {
			this.dni = dni;
		}
		
		public Alumno(Integer dni, String nombre, String apellido, LocalDate fechaNacimiento, LocalDate fechaIngreso) {
			this.dni = dni;
			this.nombre = nombre;
			this.apellido = apellido;
			this.fechaNacimiento = fechaNacimiento;
			this.fechaIngreso = fechaIngreso;
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

		public LocalDate getFechaNacimiento() {
			return this.fechaNacimiento;
		}

		public LocalDate getFechaIngreso() {
			return this.fechaIngreso;
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
