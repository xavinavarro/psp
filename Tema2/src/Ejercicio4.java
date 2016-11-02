class CreaHilo extends Thread {

	@Override

	public void run() {
		System.out.println("Soy el " + Thread.currentThread().getName() + " empezando.");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrumpido.");
			return;
		}
		System.out.println(Thread.currentThread().getName() + " acabado.");
	}
}

public class Ejercicio4 {
	public static void main(String args[]) {
		HiloEspera hilo1 = new HiloEspera();
		HiloEspera hilo2 = new HiloEspera();
		hilo1.setName("hilo 1");
		hilo2.setName("hilo 2");
		hilo1.start();
		hilo2.start();
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " interrumpido. ");
			return;
		}
		hilo1.interrupt();
	}
}