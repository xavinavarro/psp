package ProgramaClase;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EjercicioClase {
	public static void main(String[] args) {
		
		String mensaje;
		Hiloserver server1 = new Hiloserver();
		Hiloclient client1 = new Hiloclient();
		server1.start();
		client1.start();
	}
}

class Hiloserver extends Thread {
	public void run(){
		try{
			ServerSocket serversocket = new ServerSocket ();
			InetSocketAddress addr = new InetSocketAddress("192.168.26.115", 60000);
			serversocket.bind(addr);
			System.out.println("Aceptando conexiones");
			Socket newSocket = serversocket.accept();
			System.out.println("cliente conectado");
		}catch (IOException e){ 
			//e.printStackTrace();
			//System.out.println("Error Servidor");
		}
		
		while(true){}
		
	}
}

class Hiloclient extends Thread {
	public void run(){
		Socket clientsocket = new Socket();
		do{
			try{
				clientsocket = new Socket ("192.168.26.149", 60000);
			}catch (IOException e){ 
				//e.printStackTrace();
				try {
						Thread.sleep(3000);
				}catch(Exception ex){
				}
				System.out.println("Intentando reconectar");
				
			}
		}
		while(!clientsocket.isConnected());
		System.out.println("Conectado al servidor");
		while(true){}
	}
}