import java.util.*;

class HiloEjercicio4 extends Thread {
	private long espera;
	
	public HiloEjercicio4 (long espera, String nombre) {
		this.espera = espera;
		this.setName(nombre);
	}
	
	public void run() {
		String nombreHilo = this.getName();
		System.out.println("Soy el "+ nombreHilo +" empezando.");
		try {
			System.out.println("Soy el "+ nombreHilo + "y me voy a esperar "
					+ espera/1000 +" segundos");
			Thread.sleep(espera);
		} catch (InterruptedException e) {
			System.out.println(nombreHilo +" interrumpido");
			return;
		}
		System.out.println(nombreHilo +" acabando.");
	}
}

public class Ejercicio4 {
	public static void main(String[] args) {
		System.out.println("Hilo PRINCIPAL empezando.");
		
		HiloEjercicio4 thread1 = new HiloEjercicio4(3000, "Hilo 1");
		HiloEjercicio4 thread2 = new HiloEjercicio4(7000, "hilo 2");
				
		thread1.start();
		thread2.start();

		System.out.println("Soy el hilo PRINCIPAL y voy a esperarme 4 segundos.");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			System.out.println("Hilo PRINCIPAL interrumpido");
			return;
		}
		System.out.println("Hilo PRINCIPAL acabando.");
	}
}