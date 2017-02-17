package proyectomensajeria;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JORCH
 */

class HiloEnviar extends Thread{
    public static Mensaje mensaje;
    private Socket clienteSocket;
    
    public HiloEnviar(Socket socket) {
        this.clienteSocket = socket;
    }
    public static void setMensaje(Mensaje mensaje){
        Object object = new Object();
        synchronized(object){
            HiloEnviar.mensaje = mensaje;
        }
    }
    @Override
    public void run() {
        try {
            Mensaje mensajeEnviado;
            OutputStream os = clienteSocket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            
            switch(mensaje.getCodigo()){
                //CODIGOS ENVIADOS DESDE EL SERVIDOR
                case 2:
                    mensajeEnviado = new Mensaje(2, mensaje.getUsuario(), "conectado");
                    oos.writeObject(mensajeEnviado);
                    break;
                case 4:
                    mensajeEnviado = new Mensaje(4, "", mensaje.getMensaje());
                    for (int i = 0; i < Servidor.socketsConectados.size(); i++) {
                        OutputStream osAll = Servidor.socketsConectados.get(i).getOutputStream();
                        ObjectOutputStream oosAll = new ObjectOutputStream(osAll);
                        oosAll.writeObject(mensajeEnviado);
                    }
                    break;
                case 5:
                    mensajeEnviado = new Mensaje(5, mensaje.getUsuario(), mensaje.getMensaje());
                    
                    break;
                case 6:
                    mensajeEnviado = new Mensaje(6, mensaje.getUsuario(),
                    "El mensaje \'"+mensaje.getMensaje()+"\' no se ha podido enviar a el usuario "
                        + "\'"+mensaje.getUsuario()+"\'"
                        + "por haberse desconectado del sitema.");
                    oos.writeObject(mensajeEnviado);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class HiloRecibir extends Thread{
    private Socket socketCliente;
    public HiloRecibir(Socket socketCliente){
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        try {
            InputStream is = socketCliente.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            
            Mensaje mensajeRecibido = new Mensaje();
            try {
                mensajeRecibido = (Mensaje) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            
            
            switch(mensajeRecibido.getCodigo()){
                //CODIGOS RECIBIDOS DEL CLIENTE
                case 1:
                    Servidor.usuariosConectados.add(mensajeRecibido.getUsuario());
                    mensajeRecibido.setCodigo(2);
                    HiloEnviar.setMensaje(mensajeRecibido);
                    break;
                case 3:
                    
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
   
}

public class Servidor {
    public static int contadorConexiones = 0;
    public static List<Socket> socketsConectados;
    public static List<String> usuariosConectados;
    public static void main(String[] args) {
        try {
            socketsConectados = new ArrayList<>();
            usuariosConectados = new ArrayList<>();
            
            System.out.println("Arrancando servidor.");
            ServerSocket serverSocket = new ServerSocket();
            InetSocketAddress address = new InetSocketAddress("localhost", 5555);
            serverSocket.bind(address);
            
            System.out.println("Aceptando conexiones.");
            while (contadorConexiones < 5) {
                try {
                    Socket nuevoSocket = serverSocket.accept();
                    socketsConectados.add(nuevoSocket);
                    contadorConexiones++;
                    
                    System.out.println("Conexion recibida.");
                    HiloRecibir hiloRecibir = new HiloRecibir(nuevoSocket);
                    hiloRecibir.start();
                    
                    HiloEnviar hiloEnviar = new HiloEnviar(nuevoSocket);
                    hiloEnviar.start();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            System.out.println("Cerrando servidor.");
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
