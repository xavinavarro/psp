package Ejercicios;

class HiloPrioridad extends Thread {
	public void run() {
		System.out.println("Soy el " + Thread.currentThread().getName() + " empezando.");
		for (int i = 0; i < 5; i++)
			System.out.println("Soy el " + Thread.currentThread().getName() + " iteración " + i);
		System.out.println("Soy el " + Thread.currentThread().getName() + " acabando.");
	}
}

public class Prioridad {
	public static void main(String[] args) {
		HiloPrioridad thread1 = new HiloPrioridad();
		thread1.setName("Hilo 1");
		thread1.setPriority(1);
		HiloPrioridad thread2 = new HiloPrioridad();
		thread2.setName("Hilo 2");
		thread2.setPriority(Thread.MAX_PRIORITY);
		thread1.start();
		thread2.start();
	}
}