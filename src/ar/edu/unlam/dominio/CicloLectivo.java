package ar.edu.unlam.dominio;
import java.time.LocalDate;
import java.util.Objects;


public class CicloLectivo {
		static Integer contId = 0;
		private Integer id;
		private LocalDate fechaInicioCicloLectivo;
		private LocalDate fechaFinalizacionCicloLectivo;
		private LocalDate fechaInicioInscripcion;
		private LocalDate fechaFinalizacionInscripcion;

		
	
		public CicloLectivo(LocalDate fechaInicioCicloLectivo2, LocalDate fechaFinalizacionCicloLectivo2, LocalDate fechaInicioInscripcion2,
				LocalDate fechaFinalizacionInscripcion2) { 
			this.id  = ++contId;
			this.fechaInicioCicloLectivo = fechaInicioCicloLectivo2;
			this.fechaFinalizacionCicloLectivo  =fechaFinalizacionCicloLectivo2;
			this.fechaInicioInscripcion = fechaInicioInscripcion2;
			this.fechaFinalizacionInscripcion = fechaFinalizacionInscripcion2;
		}

		public Integer getID() {
			return this.id;
		}
		public LocalDate getFechaInicioCicloLectivo() {
			// TODO Auto-generated method stub
			return this.fechaInicioCicloLectivo;
		}

		public LocalDate getfechaInicioInscripcion() {
			// TODO Auto-generated method stub
			return this.fechaInicioInscripcion;
		}

		public LocalDate getfechaFinalizacionInscripcion() {
			// TODO Auto-generated method stub
			return this.fechaFinalizacionInscripcion;
		}

		public LocalDate getfechaFinalizacionCicloLectivo() {
			// TODO Auto-generated method stub
			return this.fechaFinalizacionCicloLectivo;
		}

		@Override
		public int hashCode() {
			return Objects.hash(fechaFinalizacionCicloLectivo, fechaInicioCicloLectivo);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CicloLectivo other = (CicloLectivo) obj;
			return Objects.equals(fechaFinalizacionCicloLectivo, other.fechaFinalizacionCicloLectivo)
					&& Objects.equals(fechaInicioCicloLectivo, other.fechaInicioCicloLectivo);
		}

		
	
}