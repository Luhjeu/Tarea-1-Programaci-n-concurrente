import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        try {
            int puertoEscuchaConexiones = 1212;
            System.out.println("SERVIDOR: Creando socket servidor y realizando el bind en el puerto: " + puertoEscuchaConexiones);
            ServerSocket serverSocket = new ServerSocket(puertoEscuchaConexiones);

            System.out.println("SERVIDOR: Aceptando conexiones. El servidor se bloquea esperando conexiones entrantes \n");

            int numCliente = 0;
            while (true) {
                Socket newSocket = serverSocket.accept();
                ++numCliente;
                System.out.println("SERVIDOR: Conexión recibida. Hilo para el cliente " + numCliente + " creado.");

                String[] opciones = {"Opción 1", "Opción 2", "Opción 3", "Opción 4", "Opción 5","END_OPTIONS"};
                HiloServidorComunicaciones hiloCom = new HiloServidorComunicaciones(newSocket, numCliente, opciones);
                hiloCom.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
