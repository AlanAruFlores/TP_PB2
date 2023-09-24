package ar.edu.unlam.dominio;
import java.util.Date;
import java.util.Objects;


public class CicloLectivo {
		static Integer contId = 0;
		private Integer id;
		private Date fechaInicioCicloLectivo;
		private Date fechaFinalizacionCicloLectivo;
		private Date fechaInicioInscripcion;
		private Date fechaFinalizacionInscripcion;

		
	
		public CicloLectivo(Date fechaInicioCicloLectivo, Date fechaFinalizacionCicloLectivo, Date fechaInicioInscripcion,
				Date fechaFinalizacionInscripcion) {
			this.id  = ++contId;
			this.fechaInicioCicloLectivo = fechaInicioCicloLectivo;
			this.fechaFinalizacionCicloLectivo  =fechaFinalizacionCicloLectivo;
			this.fechaInicioInscripcion = fechaInicioInscripcion;
			this.fechaFinalizacionInscripcion = fechaFinalizacionInscripcion;
		}

		public Integer getID() {
			return this.id;
		}
		public Date getFechaInicioCicloLectivo() {
			// TODO Auto-generated method stub
			return this.fechaInicioCicloLectivo;
		}

		public Date getfechaInicioInscripcion() {
			// TODO Auto-generated method stub
			return this.fechaInicioInscripcion;
		}

		public Date getfechaFinalizacionInscripcion() {
			// TODO Auto-generated method stub
			return this.fechaFinalizacionInscripcion;
		}

		public Date getfechaFinalizacionCicloLectivo() {
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
