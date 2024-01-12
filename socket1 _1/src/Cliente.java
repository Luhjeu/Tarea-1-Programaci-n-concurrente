import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            System.out.println("CLIENTE: Iniciamos el programa cliente. Presiona tecla enter para empezar.");
            Scanner teclado = new Scanner(System.in);
            teclado.nextLine();

            System.out.println("CLIENTE: Creando socket cliente");
            Socket clientSocket = new Socket();
            System.out.println("CLIENTE: Estableciendo la conexión");
            InetSocketAddress addr = new InetSocketAddress("localhost", 1212);
            clientSocket.connect(addr);

            // Obtener opciones del servidor
            InputStream streamEntrada = clientSocket.getInputStream();
            BufferedReader lectorBufer = new BufferedReader(new InputStreamReader(streamEntrada));
            String linea;
            System.out.println("Eliges una de las siguientes opción:");
            while (!(linea = lectorBufer.readLine()).equals("END_OPTIONS")) {
                System.out.println(linea);
            }

            // Seleccionar una opción
            System.out.println("CLIENTE: Selecciona una opción:");
            Scanner scanner = new Scanner(System.in);
            String seleccion = scanner.nextLine();

            // Enviar la opción seleccionada al servidor
            OutputStream streamSalida = clientSocket.getOutputStream();
            streamSalida.write(seleccion.getBytes());

            // Recibir y mostrar la respuesta del servidor
            String respuesta = lectorBufer.readLine();
            System.out.println("CLIENTE: Respuesta del servidor:\n" + respuesta);

            // Cerrar recursos
            streamEntrada.close();
            streamSalida.close();
            clientSocket.close();
            System.out.println("CLIENTE: Terminado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
