package Vista;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Bean.BEmpleado;
import Logica.IPersistencia;
import Logica.LEmpleado;
import Logica.LEmpleadoJefe;

public class VPedirDatosEmpleado {

	public VPedirDatosEmpleado() {
		Scanner captura = new Scanner(System.in);
		System.out.println("Indica el numero de empleados");
		int nroEmpleados = 0;
		String tipoEmpleado;
		LEmpleado[] lempleado;
		nroEmpleados = captura.nextInt();

		if(nroEmpleados > 0){
			lempleado = new LEmpleado[nroEmpleados];
			for(int i=0; i < (lempleado.length); i++) {
				int annio, mes, dia;
				lempleado[i] = new LEmpleado();
				System.out.println("Escribe el id del empleado" + (i+1));
				lempleado[i].setIdEmp(captura.nextInt());
				captura.nextLine();
				System.out.println("Escribe el nombre del empleado" + (i+1));
				lempleado[i].setNombreEmp(captura.nextLine());
				lempleado[i].setSueldoEmp(1000000);
				System.out.println("Escribe el año de nacimiento" + (i+1));
				annio= captura.nextInt();
				System.out.println("Escribe el mes de nacimiento" + (i+1));
				mes= captura.nextInt();
				System.out.println("Escribe el día de nacimiento" + (i+1));
				dia= captura.nextInt();
				lempleado[i].setFechaNacEmp(LocalDate.of(annio, mes, dia));
				System.out.println("El empleado es un jefe (S/N)?");
				tipoEmpleado = captura.next();

				if (tipoEmpleado.equalsIgnoreCase("S")) {
					LEmpleadoJefe jefe_sistemas = new LEmpleadoJefe(lempleado[i].getIdEmp(),lempleado[i].getNombreEmp(),lempleado[i].getSueldoEmp(),annio,mes,dia);
					jefe_sistemas.estableceBonificacion(1000000);
					lempleado[i] = jefe_sistemas;
				}
			}

			for(LEmpleado e : lempleado ) {
				e.LAumentoSueldo(3.0);
			}

			// La variable es de tipo interfaz: trabaja con BEmpleado
			// (LEmpleado y LEmpleadoJefe tambien sirven porque son BEmpleado)
			IPersistencia<BEmpleado> persistencia = new LEmpleado();

			// Cada empleado se guarda en empleados.txt
			for(LEmpleado e : lempleado) {
				System.out.println("Datos de la Lista antes de enviar al archivo de datos");
				System.out.println("Empleado: " + e.getIdEmp() + " Nombre: " + e.getNombreEmp() + " Sueldo: " + e.getSueldoEmp() +
				" Fecha Nac: " + e.getFechaNacEmp());
				System.out.println("-------------------------------------------------------");
				persistencia.guardar(e);
			}

			// SERIALIZACION: se hace UNA sola vez, cuando ya estan todos guardados.
			// 1) leerTodos: lee empleados.txt y devuelve la lista
			// 2) serializar: guarda la lista completa como objeto en empleadosSerializados.txt
			// 3) leerSerializado: lee ese archivo y devuelve la lista para mostrarla
			ArrayList<BEmpleado> listaEmpleados = persistencia.leerTodos();
			persistencia.serializar(listaEmpleados);
			ArrayList<BEmpleado> listaLeida = persistencia.leerSerializado();

			for(BEmpleado b : listaLeida) {
				System.out.println(b.getIdEmp() + " - " + b.getNombreEmp());
			}

		} //Fin del cierto del if numero de empleado para capturar
		else {
			System.out.println("Canceló la captura de empleados.");
		}
		captura.close();
	}
}