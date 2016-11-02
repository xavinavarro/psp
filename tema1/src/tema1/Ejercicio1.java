package tema1;

import java.io.IOException;

public class Ejercicio1 {
	public static void main (String[] args) throws IOException{
		if(args.length <= 0){
			
		}
		ProcessBuilder pb = new ProcessBuilder("comando");
	}
	try{
		Process proceso = pb.start();
		int retorno = proceso.waitFor();
		
	}
}
