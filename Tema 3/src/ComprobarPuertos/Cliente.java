package ComprobarPuertos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) {
		
		try{
			for (int i = 0; i < 65536; i++) {
				try{
					Socket clientSocket = new Socket("localhost", i);
					System.out.println("Puerto "+i + "abierto");
					clientSocket.close();
				}catch (Exception e){
					//System.out.println("No se ha podido conectar");
				}
			}
		}catch (Exception e){
			
		}
	}
}