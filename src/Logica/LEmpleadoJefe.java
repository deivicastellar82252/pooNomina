package Logica;

public class LEmpleadoJefe extends LEmpleado
{
	private long bonificacion;

	public LEmpleadoJefe(int id,String nombre, long sueldo, int annio, int mes, int dia, long bonificacion) {
		super(id,nombre,sueldo,annio,mes,dia);
		this.bonificacion = bonificacion;
	}

	
	public void estableceBonificacion(long bonificacion) {
		if (bonificacion >= 0) {
			this.bonificacion = bonificacion;
		}
	}
	public long getSueldoEmp() {
		return super.getSueldoEmp() + bonificacion;
	}
	}
