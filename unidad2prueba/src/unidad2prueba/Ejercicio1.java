package unidad2prueba;

import java.io.IOException;

// Clase principal del programa
public class Ejercicio1 extends Thread{
	public static void main(String[] args){
		// Creamos los hilos y los iniciamos
		Hilo1 hilo1 = new Hilo1();
		hilo1.run();
		Hilo2 hilo2 = new Hilo2();
		hilo2.run();
	}
}

// Clase para el hilo que ejecuta gedit
class Hilo1 extends Thread{
	// M�todo que ejecuta el hilo al iniciarse
	public void run(){
		try{
			// creaci�n e inicializaci�n del proceso
			Runtime.getRuntime().exec("gedit");
			// Tambi�n podr�a hacerse con la l�nea siguiente si se quisiera tener informaci�n del proceso creado
			//Process proceso = Runtime.getRuntime().exec("explorer");	
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

class Hilo2 extends Thread{
	public void run(){
		try{
			Runtime.getRuntime().exec("firefox");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}