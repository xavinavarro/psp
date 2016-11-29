package Ejemplos;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Documento {
	private static boolean sePuedeLeer = true;
	
	private static FileWriter fichero = null;
	private static BufferedWriter bw = null;
	
	private static String nombreDocumento;

	public Documento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
		sePuedeLeer = true;
	}

	public synchronized void permisoLectura() {
		String nombreHilo = Thread.currentThread().getName();
		try {
			while (!sePuedeLeer) {
				if(nombreHilo.compareTo("Lector 1")==0)
			    	Interfaz.lector1Label.setText(nombreHilo +" - no puedo leer");
			    else if(nombreHilo.compareTo("Lector 2")==0)
			    	Interfaz.lector2Label.setText(nombreHilo +" - no puedo leer");
			    else if(nombreHilo.compareTo("Lector 3")==0)
			    	Interfaz.lector3Label.setText(nombreHilo +" - no puedo leer");
			    else if(nombreHilo.compareTo("Lector 4")==0)
			    	Interfaz.lector4Label.setText(nombreHilo +" - no puedo leer");
				wait();
			}
			
			if(nombreHilo.compareTo("Lector 1")==0)
		    	Interfaz.lector1Label.setText(nombreHilo +" - leyendo");
		    else if(nombreHilo.compareTo("Lector 2")==0)
		    	Interfaz.lector2Label.setText(nombreHilo +" - leyendo");
		    else if(nombreHilo.compareTo("Lector 3")==0)
		    	Interfaz.lector3Label.setText(nombreHilo +" - leyendo");
		    else if(nombreHilo.compareTo("Lector 4")==0)
		    	Interfaz.lector4Label.setText(nombreHilo +" - leyendo");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void leer() {
		String nombreHilo = Thread.currentThread().getName();
		
		try
		{
			FileReader fr = new FileReader(nombreDocumento);
			int caract;

			do {
				permisoLectura();
				caract = fr.read();
				if(nombreHilo.compareTo("Lector 1")==0)
			    	Interfaz.lector1Texto.append(""+(char)caract);
			    else if(nombreHilo.compareTo("Lector 2")==0)
			    	Interfaz.lector2Texto.append(""+(char)caract);
			    else if(nombreHilo.compareTo("Lector 3")==0)
			    	Interfaz.lector3Texto.append(""+(char)caract);
			    else if(nombreHilo.compareTo("Lector 4")==0)
			    	Interfaz.lector4Texto.append(""+(char)caract);
				Esperar.esperar(50);				
			} while(caract != -1);
			
			
			if(nombreHilo.compareTo("Lector 1")==0)
		    	Interfaz.lector1Label.setText(nombreHilo +" - acabó");
		    else if(nombreHilo.compareTo("Lector 2")==0)
		    	Interfaz.lector2Label.setText(nombreHilo +" - acabó");
		    else if(nombreHilo.compareTo("Lector 3")==0)
		    	Interfaz.lector3Label.setText(nombreHilo +" - acabó");
		    else if(nombreHilo.compareTo("Lector 4")==0)
		    	Interfaz.lector4Label.setText(nombreHilo +" - acabó");
			
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void escribir() {
		try {
			String nombreHilo = Thread.currentThread().getName();
			FileWriter fichero = new FileWriter("vetusta.txt", true);
			sePuedeLeer = false;
					
			Esperar.esperar(2000);
				
			synchronized (this) {
				System.out.println(Thread.currentThread().getName() +" escribiendo en el documento");
				String texto = new String();
				if(nombreHilo.compareTo("Escritor 1")==0)
					texto = "\r\nHe escuchado el ritmo de los feriantes\r\nPoniendo precio a mi agonía\r\nFamilias de erizos en sus manos frías";
			    else if(nombreHilo.compareTo("Escritor 2")==0)
			    	texto = "\r\nHabrá que inventarse una salida\r\nYa no hay timón en la deriva";
				
				for (int i=0; i<texto.length(); i++)
					fichero.write(texto.charAt(i));				
				
				sePuedeLeer = true;
				notifyAll();
			}
			fichero.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

class Lector extends Thread{
	Documento documento;

	public Lector (String nombre, Documento documento) {
		this.documento = documento;
		this.setName(nombre);
	}
	
	public void run () {
		String nombreHilo = Thread.currentThread().getName();
		if(nombreHilo.compareTo("Lector 1")==0)
			Interfaz.lector1Label.setText(nombreHilo +" llegó.");
		else if(nombreHilo.compareTo("Lector 2")==0)
			Interfaz.lector2Label.setText(nombreHilo +" llegó.");
	    else if(nombreHilo.compareTo("Lector 3")==0)
	    	Interfaz.lector3Label.setText(nombreHilo +" llegó.");
	    else if(nombreHilo.compareTo("Lector 4")==0)
	    	Interfaz.lector4Label.setText(nombreHilo +" llegó.");
		
		Esperar.esperar(2000);
		
		System.out.println("Lector "+ Thread.currentThread().getName() +" llegó.");
		documento.leer();
	}
}

class Escritor extends Thread{
	Documento documento;

	public Escritor (String nombre, Documento documento) {
		this.documento = documento;
		this.setName(nombre);
	}

	public void run () {
		String nombreHilo = Thread.currentThread().getName();
		if(nombreHilo.compareTo("Escritor 1")==0)
			Interfaz.escritor1Info.setText(nombreHilo +" escribiendo.");
		else if(nombreHilo.compareTo("Escritor 2")==0)
			Interfaz.escritor2Info.setText(nombreHilo +" escribiendo.");
		
		documento.escribir();
		
		if(nombreHilo.compareTo("Escritor 1")==0)
			Interfaz.escritor1Info.setText(nombreHilo +" acabó.");
		else if(nombreHilo.compareTo("Escritor 2")==0)
			Interfaz.escritor2Info.setText(nombreHilo +" acabó.");
	}
}

public class LectoresEscritores {
	public static void main(String[] args) {
		Interfaz.creaInterfaz();
		Documento doc = new Documento("vetusta.txt");
		
		int n_lectores = 4;
		for (int i=1 ; i<=n_lectores; i++) {
			Lector lector = new Lector("Lector "+ i, doc);
			lector.start();
			Esperar.esperar(1500);
		}
		Escritor escritor1 = new Escritor("Escritor 1", doc);
		Escritor escritor2 = new Escritor("Escritor 2", doc);
		
		Esperar.esperar(2000);
		escritor1.start();
		Esperar.esperar(4000);
		escritor2.start();
	}
}

class Esperar { 
	public static void esperar(long milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			System.err.println("Thread lector interrumpido!");
			System.exit(-1);
		}
	}
}

class Interfaz extends JFrame {
	public static JFrame frame;
	
	static JLabel lector1Label;
	static JLabel lector2Label;
	static JLabel lector3Label;
	static JLabel lector4Label;
	static JTextArea lector1Texto;
	static JTextArea lector2Texto;
	static JTextArea lector3Texto;
	static JTextArea lector4Texto;
	static JLabel escritor1Info;
	static JLabel escritor2Info;
	
	public static void creaInterfaz() {
		frame = new JFrame("Lectores y Escritores");

		frame.setLayout(new GridLayout(5, 2, 0, 10));
		frame.setSize(600, 1200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		lector1Label = new JLabel("Lector 1");
		frame.add(lector1Label);
		
		lector1Texto = new JTextArea(8, 300);
		frame.add(lector1Texto);
		
		lector2Label = new JLabel("Lector 2");
		frame.add(lector2Label);
		
		lector2Texto = new JTextArea(8, 300);
		frame.add(lector2Texto);
		
		lector3Label = new JLabel("Lector 3");	
		frame.add(lector3Label);
		
		lector3Texto = new JTextArea(8, 300);
		frame.add(lector3Texto);
		
		lector4Label = new JLabel("Lector 4");
		frame.add(lector4Label);
		
		lector4Texto = new JTextArea(8, 300);
		frame.add(lector4Texto);
		
		escritor1Info = new JLabel("Info Escritor 1");
		frame.add(escritor1Info);
		
		escritor2Info = new JLabel("Info Escritor 2");
		frame.add(escritor2Info);
		
		frame.setVisible(true);
	}
}	