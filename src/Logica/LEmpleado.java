package Logica;

import Bean.BEmpleado;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;

public class LEmpleado extends BEmpleado implements IPersistencia<BEmpleado> {

	public LEmpleado() {}

	public LEmpleado(int id, String nombre, long sueldo, int annio, int mes, int dia)
	{
		super.setIdEmp(id);
		super.setNombreEmp(nombre);
		super.setSueldoEmp(sueldo);
		super.setFechaNacEmp(LocalDate.of(annio,mes,dia));
	}

	public LEmpleado(BEmpleado empleado, LocalDate fechaNacimientoEmp)
	{
		super.setIdEmp(empleado.getIdEmp());
		super.setNombreEmp(empleado.getNombreEmp());
		super.setSueldoEmp(empleado.getSueldoEmp());
		super.setFechaNacEmp(fechaNacimientoEmp);
	}

	public LEmpleado(BEmpleado empleado)
	{
		super.setIdEmp(empleado.getIdEmp());
		super.setNombreEmp(empleado.getNombreEmp());
		super.setSueldoEmp(empleado.getSueldoEmp());
		super.setFechaNacEmp(empleado.getFechaNacEmp());
	}

	public void LEmpleadoRegistrar(int id,String nombre, long sueldo, int annio, int mes, int dia)
	{
		super.setIdEmp(id);
		super.setNombreEmp(nombre);
		super.setSueldoEmp(sueldo);
		super.setFechaNacEmp(LocalDate.of(annio,mes,dia));
		LEmpleadoIngresar();
	}

	public void LEmpleadoRegistrar(BEmpleado empleado, LocalDate fechaNacimientoEmp)
	{
		super.setIdEmp(empleado.getIdEmp());
		super.setNombreEmp(empleado.getNombreEmp());
		super.setSueldoEmp(empleado.getSueldoEmp());
		super.setFechaNacEmp(fechaNacimientoEmp);
		LEmpleadoIngresar();
	}

	public void LEmpleadoRegistrar(BEmpleado empleado)
	{
		super.setIdEmp(empleado.getIdEmp());
		super.setNombreEmp(empleado.getNombreEmp());
		super.setSueldoEmp(empleado.getSueldoEmp());
		super.setFechaNacEmp(empleado.getFechaNacEmp());
		LEmpleadoIngresar();
	}

	public void LAumentoSueldo(double porcentaje)
	{
		long aumento = (long)(getSueldoEmp() * porcentaje/100);
		super.setSueldoEmp(super.getSueldoEmp() + aumento);
	}

	// Guarda este empleado en empleados.txt usando el metodo de la interfaz
	public void LEmpleadoIngresar()
	{
		guardar(this);
	}

	// ===================== METODOS DE LA INTERFAZ IPersistencia<BEmpleado> =====================

	// Guarda UN empleado al final del archivo empleados.txt (acceso aleatorio).
	// OJO: esto NO es serializacion, es escritura campo por campo.
	public void guardar(BEmpleado objeto)
	{
		try
		{
			RandomAccessFile archivo = new RandomAccessFile("./empleados.txt","rw");
			archivo.seek(archivo.length());
			archivo.writeInt(objeto.getIdEmp());
			archivo.writeUTF(objeto.getNombreEmp());
			archivo.writeLong(objeto.getSueldoEmp());
			archivo.writeUTF(String.valueOf(objeto.getFechaNacEmp()));
			archivo.close();
		}catch(IOException e) {System.out.print(e); }
	}

	// Lee TODOS los empleados de empleados.txt y los devuelve en una lista.
	public ArrayList<BEmpleado> leerTodos()
	{
		ArrayList<BEmpleado> lista = new ArrayList<BEmpleado>();
		try
		{
			RandomAccessFile archivo = new RandomAccessFile("./empleados.txt","r");
			while(archivo.getFilePointer() < archivo.length())
			{
				BEmpleado emp = new BEmpleado();
				emp.setIdEmp(archivo.readInt());
				emp.setNombreEmp(archivo.readUTF());
				emp.setSueldoEmp(archivo.readLong());
				emp.setFechaNacEmp(LocalDate.parse(archivo.readUTF()));
				lista.add(emp);
			}
			archivo.close();
		}catch(IOException e) {System.out.println(e); }
		return lista;
	}

	// ===================== SERIALIZACION (ESCRIBIR) =====================
	// Que hace: recibe la lista completa de empleados y la guarda COMPLETA,
	// como un solo objeto, en empleadosSerializados.txt usando ObjectOutputStream.
	// Se puede hacer porque BEmpleado implements Serializable (y ArrayList tambien).
	// Cada vez que se ejecuta, el archivo se reemplaza con la lista recibida.
	public void serializar(ArrayList<BEmpleado> lista)
	{
		try
		{
			FileOutputStream ruta_salida = new FileOutputStream("./empleadosSerializados.txt");
			ObjectOutputStream archivo_salida = new ObjectOutputStream(ruta_salida);
			archivo_salida.writeObject(lista); // aqui ocurre la serializacion
			archivo_salida.close();
		}catch(Exception e)
		{
			System.out.println("Problemas con el acceso al archivo de datos - " + e);
		}
	}

	// ===================== SERIALIZACION (LEER) =====================
	// Que hace: abre empleadosSerializados.txt, reconstruye la lista de
	// BEmpleado con ObjectInputStream (deserializar) y la devuelve.
	public ArrayList<BEmpleado> leerSerializado()
	{
		ArrayList<BEmpleado> lista = new ArrayList<BEmpleado>();
		try
		{
			FileInputStream ruta_entrada = new FileInputStream("./empleadosSerializados.txt");
			ObjectInputStream leer_datos = new ObjectInputStream(ruta_entrada);
			lista = (ArrayList<BEmpleado>) leer_datos.readObject(); // aqui se deserializa
			leer_datos.close();
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Problemas con el acceso al archivo de datos.");
		}
		return lista;
	}
}