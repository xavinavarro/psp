package PruebaXaviNavarro;

	class EjercicioDos extends Thread {
		public void run() {
			
		}
	}

public class Ejercicio2 {
		public static void main(String[] args) {
			EjercicioDos thread1 = new EjercicioDos();
			thread1.setName("Hilo 1");
			thread1.setPriority(1);
			for (int i = 0; i < 5; i++)
				System.out.println(thread1.getName() + ": A mí me rebota y a ti te explota ");
			EjercicioDos thread2 = new EjercicioDos();
			thread2.setName("Hilo 2");
			thread2.setPriority(Thread.MAX_PRIORITY);
			for(int i= 0; i<5; i++)
				System.out.println(thread2.getName() + ": A ti mas");
			thread1.start();
			thread2.start();
			for(int i= 0; i<1; i++)
				System.out.println(thread1.getName() +": Dejaos de gilipolleces");
			
		}
	}