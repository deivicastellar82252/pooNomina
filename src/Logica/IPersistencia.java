package Logica;

import java.io.Serializable;
import java.util.ArrayList;

// T es el tipo de objeto con el que se trabaja (por ejemplo BEmpleado).
// "extends Serializable" obliga a que T se pueda serializar.
public interface IPersistencia<T extends Serializable> {

	// Guarda UN objeto al final del archivo de acceso aleatorio
	void guardar(T objeto);

	// Lee TODOS los objetos del archivo de acceso aleatorio
	ArrayList<T> leerTodos();

	// Serializa la lista completa en un archivo
	void serializar(ArrayList<T> lista);

	// Deserializa la lista completa desde el archivo
	ArrayList<T> leerSerializado();
}