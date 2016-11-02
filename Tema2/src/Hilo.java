import java.util.Random;

public class Hilo implements Runnable {

	private int espera;

	public static void main(String[] args) {

		// Lanzamos dos hilos de forma concurrente que duren un tiempo
		// aleatorio:

		Random aleatorio = new Random(1725);

		for (int i = 0; i < 2; i++) {

			// Un hilo tendrá un tiempo de ejecución comprendido entre los 0 y
			// 10 segundos.

			new Thread(new Hilo(aleatorio.nextInt(5000))).start();

		}
	}

	public Hilo(int espera) {

		this.espera = espera;

	}
	// Método que contiene las acciones que hará el hilo cuando se ejecute.

	@Override

	public void run() {

		String nombre = Thread.currentThread().getName();

		System.out.println("Soy el hilo " + nombre + " y he iniciado mi ejecución.");

		System.out.println("Soy el hilo " + nombre + " y voy a parar mi ejecución " + espera + " ms.");

		try {

			Thread.sleep(espera);

		} catch (InterruptedException e) {

			System.err.println("Soy el hilo " + nombre + " y me han interrumpido.");

		}

		System.out.println("Soy el hilo " + nombre + " y continúo mi ejecución.");

		System.out.println("Soy el hilo " + nombre + " y he finalizado mi ejecución.");

	}
}