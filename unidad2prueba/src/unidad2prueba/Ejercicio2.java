package unidad2prueba;

public class Ejercicio2 extends Thread{
	static Rebota rebota = new Rebota();
	static Explota explota = new Explota();
		
	public static void main(String[] args){
		rebota.start();
		explota.start();
		try{
			rebota.join();
			explota.join();
		}catch (InterruptedException e) {
			System.out.println("Interrumpido hilo padre");
		}
		System.out.println("Dejaos de gilipolleces");	
	}
}

class Rebota extends Thread{
	public void run(){
		for (int i = 0; i < 5; i++){
			System.out.println("A mí me rebota y a ti te explota");
			Ejercicio2.explota.interrupt();
			try{
				Ejercicio2.explota.join();
			}catch (InterruptedException e){ }
		}
	}
}
class Explota extends Thread{
	public void run(){
		for (int i = 0; i < 5; i++){
			try{
				Ejercicio2.rebota.join();
			}catch (InterruptedException e){ }
			System.out.println("Y tú más");
			Ejercicio2.rebota.interrupt();
		}
	}
}
