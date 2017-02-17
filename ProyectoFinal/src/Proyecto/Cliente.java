package Proyecto;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Cliente {

	public static void main (String[] args){
		try{
			System.out.println("");
			System.out.println("Creando socket cliente");
	        Socket clientSocket = new Socket();
	        
	        InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
	        clientSocket.connect(addr);
		}catch(IOException e) {
			e.printStackTrace();			
		}
	}
}
