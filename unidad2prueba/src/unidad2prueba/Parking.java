package unidad2prueba;

public class Parking {	
	public static void main(String[] args) {
		Barrera entrada = new Barrera(5,3);
		for (int i = 0; i < 10; i++){
			Coche coche = new Coche("Coche " + i, entrada);
			coche.start();
			
			if(i<5) {
				Moto moto = new Moto("Moto " + i, entrada);
				moto.start();
			}
			
			//long tEspera = (long) (Math.random()*7+1)*1000;
			long tEspera = (long) (Math.random()*7+1)*50;
			try {
				Thread.sleep(tEspera);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}	
}

class Coche extends Thread{
	private String nombre;
	private Barrera barrera;
	
	public Coche (String nombre, Barrera barrera){
		this.nombre = nombre;
		this.barrera = barrera;
	}
	
	public void run(){
		barrera.entrar(nombre);
		System.out.println(nombre +" he aparcado y me espero.");
		// El coche aparca, se espera y luego sale del parking
		Espera.espera(2500);
		barrera.salir(nombre);
		System.out.println(nombre +" me voy.");
		
	}
}
/* INICIO AÑADIDO */
class Moto extends Thread{
	private String nombre;
	private Barrera barrera;
	
	public Moto (String nombre, Barrera barrera){
		this.nombre = nombre;
		this.barrera = barrera;
	}
	
	public void run(){
		barrera.entrar(nombre);
		System.out.println(nombre +" he aparcado y me espero.");
		// El coche aparca, se espera y luego sale del parking
		Espera.espera(2500);
		barrera.salir(nombre);
	}
}
/* FIN AÑADIDO */

class Barrera {
//	private int huecos;
//	public  Barrera (int huecos){
//		this.huecos = huecos;
//	}
	
	/* INICIO AÑADIDO */
	private int plazasCoches;
	private int plazasMotos;
	public Barrera(int plazasCoches, int plazasMotos) {
		this.plazasCoches = plazasCoches;
		this.plazasMotos = plazasMotos;
	}
	/* FIN AÑADIDO */
	
	public void entrar(String nombre){
		synchronized (this) {
			try {
//				while (huecos < 1 ) {
//					System.out.println(nombre +" no puedo entrar.");
//					wait();
//				}
//				huecos--;
				
				/* INICIO AÑADIDO */
				if(Thread.currentThread().getClass()==Coche.class) {
					while (plazasCoches < 1 ) {
						System.out.println(nombre +" no puedo entrar.");
						wait();
					}
					plazasCoches--;
					System.out.println(nombre +" entrando. PlazasCoches: "+ plazasCoches);
				}
				if(Thread.currentThread().getClass()==Moto.class) {
					while (plazasMotos < 1 ) {
						System.out.println(nombre +" no puedo entrar.");
						wait();
					}
					plazasMotos--;
					System.out.println(nombre +" entrando. PlazasMotos: "+ plazasMotos);
				}
				/* FIN AÑADIDO */
			} catch (InterruptedException e) {
				System.out.println(nombre + " interrumpido");
			}
			
		}
		Espera.espera(5000);
	}
	public synchronized void salir(String nombre){
//		huecos++;
//		notifyAll();
//		System.out.println(nombre +" saliendo. Plazas: "+ huecos);
		
		/* INICIO AÑADIDO */
		if(Thread.currentThread().getClass()==Coche.class) {
			plazasCoches++;
			notifyAll();
		}
		if(Thread.currentThread().getClass()==Moto.class) {
			plazasMotos++;
			notifyAll();
		}
		/* FIN AÑADIDO */
	}
}

class Espera {
	public static void espera(long espera){
		try{
			Thread.sleep(espera);
		}catch (InterruptedException e) {
			System.err.println ("Thread coche interrumpido");
			System.exit(-1);
		}
	}
}