package Bean;

import java.io.Serializable;
import java.time.LocalDate;

// SERIALIZACION: "implements Serializable" es lo que permite convertir
// objetos de esta clase en bytes para guardarlos en un archivo.

public class BEmpleado implements Serializable {

	private int idEmp;
	private String nombreEmp;
	private long sueldoEmp;
	private LocalDate fechaNacEmp;

	public BEmpleado() {}

	public int getIdEmp() {
		return idEmp;
	}
	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}
	public String getNombreEmp() {
		return nombreEmp;
	}
	public void setNombreEmp(String nombreEmp) {
		this.nombreEmp = nombreEmp;
	}
	public long getSueldoEmp() {
		return sueldoEmp;
	}
	public void setSueldoEmp(long sueldoEmp) {
		this.sueldoEmp = sueldoEmp;
	}
	public LocalDate getFechaNacEmp() {
		return fechaNacEmp;
	}
	public void setFechaNacEmp(LocalDate fechaNacEmp) {
		this.fechaNacEmp = fechaNacEmp;
	}
}