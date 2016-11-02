class RunnableClass implements Runnable{
	public void run(){
		for(int i = 1; i<5; i++){
			System.out.println("Ejecutando" +Thread.currentThread().getName() +":"+i);
		}
	}
}
public class Hilos {
	public static void main(String args[]){
		RunnableClass rc = new RunnableClass();
		Thread hilo1 = new Thread(rc);
		Thread hilo2 = new Thread(rc);
		Thread hilo3 = new Thread(rc);
		
		hilo1.setName("Hilo1");
		hilo2.setName("Hilo1");
		hilo3.setName("Hilo1");
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
	}
}