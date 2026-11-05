package Logica;

import java.io.Serializable;
import java.util.ArrayList;


public interface IPersistencia<T extends Serializable> {

	
	void guardar(T objeto);

	
	ArrayList<T> leerTodos();

		void serializar(ArrayList<T> lista);

	
	ArrayList<T> leerSerializado();
}