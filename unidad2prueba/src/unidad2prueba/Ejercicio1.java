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
	// Método que ejecuta el hilo al iniciarse
	public void run(){
		try{
			// creación e inicialización del proceso
			Runtime.getRuntime().exec("gedit");
			// También podría hacerse con la línea siguiente si se quisiera tener información del proceso creado
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