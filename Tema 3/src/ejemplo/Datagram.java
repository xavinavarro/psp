package ejemplo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Datagram {
	public static void main(String[] args) {
		try {
			System.out.println("Creando socket datagram");
			DatagramSocket datagramSocket = new DatagramSocket();
			System.out.println("Enviando mensaje");
			String mensaje = "mensaje desde el emisor";
			InetAddress addr = InetAddress.getByName("localhost");
			DatagramPacket datagrama = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, addr, 5555);
			datagramSocket.send(datagrama);
			System.out.println("Mensaje enviado");
			System.out.println("Cerrando el socket datagrama");
			datagramSocket.close();
			System.out.println("Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}