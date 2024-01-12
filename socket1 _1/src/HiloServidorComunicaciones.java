import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HiloServidorComunicaciones extends Thread {



    private Socket socketCliente;
    private int numHilo;
    private String[] opciones;

    public HiloServidorComunicaciones(Socket socketCliente, int numHilo, String[] opciones) {
        this.socketCliente = socketCliente;
        this.numHilo = numHilo;
        this.opciones = opciones;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            OutputStream streamSalida = socketCliente.getOutputStream();

            // Enviar opciones al cliente
            for (String opcion : opciones) {
                streamSalida.write((opcion + "\n").getBytes());
            }
            // Marcar el final de las opciones
            streamSalida.write("END_OPTIONS\n".getBytes());

            // Recibir opción seleccionada por el cliente
            String seleccion = br.readLine();
            System.out.println("Hilo" + numHilo + ": Cliente seleccionó la opción " + seleccion);

            // Enviar respuesta al cliente según la opción seleccionada
            String respuesta = obtenerRespuesta(Integer.parseInt(seleccion));
            streamSalida.write((respuesta + "\n").getBytes());

            // Cerrar recursos
            br.close();
            streamSalida.close();
            socketCliente.close();
            System.out.println("Hilo" + numHilo + ": Comunicación cerrada");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String obtenerRespuesta(int opcion) {
        // Lógica para determinar la respuesta según la opción seleccionada
        // Aquí debes adaptar la lógica según tus necesidades
        switch (opcion) {
            case 1:
                return "Respuesta a la opción 1";
            case 2:
                return "Respuesta a la opción 2";
            case 3:
                return "Respuesta a la opción 3";
            case 4:
                return "Respuesta a la opción 4";
            case 5:
                return "Respuesta a la opción 5";
            default:
                return "Opción no válida";
        }
    }
}
