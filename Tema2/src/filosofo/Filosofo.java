package filosofo;

// Programa principal
public class Filosofo {    
    public static void main(String[] args) {
    	// Se declaran tantos tnedores como fil�sofos, los tenedores ser�n el monitor para que el fil�sofo pueda comer
    	Tenedor[] tenedores ={
                new Tenedor(1),
                new Tenedor(2),
                new Tenedor(3),
                new Tenedor(4),
                new Tenedor(5)
        };
    	
    	// Se crean los cinco fil�sofo y se les asignan sus tenedores
    	for(int i=0; i<5; i++) {
			if(i<4) {
    			Filosofo filosofo = new Filosofo("Filo"+(i+1), tenedores[i], tenedores[i+1]);
    			filosofo.start();
			}
			else {
				Filosofo filosofo = new Filosofo("Filo"+(i+1), tenedores[i], tenedores[0]);
				filosofo.start();
			}
    	}
    }
}

class Filosofo extends Thread {
	// Objetos que har�n de monitor
    private Tenedor tenedor1;
    private Tenedor tenedor2;
    
    // Variables que nos permitir�n saber si se tiene cogido algunos de los tenedores 
    private boolean tengoTenedor1 = false;
    private boolean tengoTenedor2 = false;
    public void setTengoTenedor1 (boolean tengoTenedor1) {
		this.tengoTenedor1 = tengoTenedor1;
	}
    public void setTengoTenedor2(boolean tengoTenedor2) {
		this.tengoTenedor2 = tengoTenedor2;
	}
    
    // constructor del fil�sofo
    public Filosofo (String nombre, Tenedor tenedor1, Tenedor tenedor2){
        this.setName(nombre);
        this.tenedor1=tenedor1;
        this.tenedor2=tenedor2;
    }
    
    // M�todo para liberar los tenedores
    public void soltarTenedores () {
    	// Se suelta el tenedor que se tuviera cogido
    	if(tengoTenedor1)
    		tenedor1.soltar(this, 1);
    	if(tengoTenedor2)
    		tenedor2.soltar(this, 2);
    }
    
    // C�digo que se ejecutar� en el fil�sofo
    public void run(){
    	//while(true) {
    	// Para acotar la ejecuci�n cada fil�sofo comer� 5 veces
    	for(int i=0; i<5; i++) {
    		// Hasta qeu no se hayan cogido los dos tenedores no se puede continuar
    		while(!tengoTenedor1 && !tengoTenedor2) {
    			// Solo se intentar�a coger el tenedor que no se tenga cogido a�n
    			if(!tengoTenedor1)
    				tenedor1.coger(this, 1);	
    			if(!tengoTenedor2)
    	        	tenedor2.coger(this, 2);
    		}
	        System.out.println(Thread.currentThread().getName() +" he cogido ambos tenedores y me pongo a comer");
	        // Se come durante 3 segundos
	        Espera.espera(3000);
	        // Se sueltan los tenedores
	        soltarTenedores();
    	}
    }
}

class Tenedor {
	//Los tenedores tienen un id y una variable para comprobar el acceso
    private int id;
    private boolean ocupado = false;
    
    public Tenedor(int id){
        this.id=id;
	}

    // M�todo para coger un tenedor
    public void coger(Filosofo filosofo, int idTenedor) { //String filosofo) {
        synchronized (this) {
            try {
            	// Si uno de los tenedores que se intenta coger est� ocupado se esperar� hasta que se le notifique que puede volver a intentarlo
                while (ocupado) {
                    System.out.println(filosofo.getName() + " No puedo comer con el tenedor "+ id);
                    // Para evitar interbloqueos antes de esperar se soltar�n los tenedores que tuviera cogidos
                    filosofo.soltarTenedores();
                    wait();
                }
                // Se marca el tenedor como cogido para que otro fil�sofo no pueda cogerlo
                ocupado = true;
            } catch (InterruptedException e) {
                System.out.println(filosofo.getName() +" He sido interrumpido");
            }
        }
        
        // Se indica en el fil�sofo que se ha cogido el tenedor
        if(idTenedor == 1)
        	filosofo.setTengoTenedor1(true);
        else
        	filosofo.setTengoTenedor2(true);
        
        System.out.println(filosofo.getName() +" cojo el tenedor "+ id);
    }

    // M�todo para soltar un tenedor
    public synchronized void soltar(Filosofo filosofo, int idTenedor){
        ocupado = false;
        // Se indica en el fil�sofo que se suelta el tenedor
        if(idTenedor == 1)
        	filosofo.setTengoTenedor1(false);
        else
        	filosofo.setTengoTenedor2(false);
        
        // Se despierta a otros fil�sofos que pudieran estar esperando el tenedor
        notifyAll();
        System.out.println(filosofo.getName() +" suelto el tenedor "+ id);
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
