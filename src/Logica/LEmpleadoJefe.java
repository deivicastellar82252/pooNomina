package Logica;

public class LEmpleadoJefe extends LEmpleado
{
	private long bonificacion;

	public LEmpleadoJefe(int id,String nombre, long sueldo, int annio, int mes, int dia) {
		super(id,nombre,sueldo,annio,mes,dia);
	}

	// Este metodo faltaba: la vista lo llamaba y no existia.
	public void estableceBonificacion(long bonificacion) {
		this.bonificacion = bonificacion;
	}

	public long getSueldoEmp() {
		long salariojefe = super.getSueldoEmp();
		return (salariojefe + bonificacion);
	}
}