package PruebaXaviNavarro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class Ejercicio1 {

	public static void main(String[] args) throws IOException, InterruptedException{
		try{
			String comand1 = "gedit";
			String comand2 = "firefox";
			
			Process hilo1 = Runtime.getRuntime().exec(comand1);
			
			InputStream isHilo1 = hilo1.getInputStream();
			BufferedReader br = new BufferedReader (new InputStreamReader(isHilo1));
			
			String line;
			
			Process hijo2 = Runtime.getRuntime().exec(comand2);
            OutputStream osHijo2 = hijo2.getOutputStream();
            PrintStream ps = new PrintStream(osHijo2);
            BufferedReader br2 = new BufferedReader (new InputStreamReader(hijo2.getInputStream()));
			
			while ((line = br.readLine()) != null) {
            	ps.println(line);
            	ps.flush();
		}
			while ((line = br2.readLine()) != null) {
            	System.out.println(line);
			}
	}catch (IOException e) {
		System.out.println("Error ocurrió ejecutando el	comando. Descripción: " + e.getMessage());
		}
	}
}
