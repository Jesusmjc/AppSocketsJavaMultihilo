package AppSocketsJavaMultihilo.ServidorMultihilo;
import java.io.IOException;
import java.net.ServerSocket;

public class ServidorMultihilo {
    public static void main(String[] args) {
        int puerto = 8080;

        try (ServerSocket ss = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto: " + puerto + "...");
            int contador = 0;

            while (true) {
                HiloHandler cliente = new HiloHandler(ss.accept(), contador);
                Thread h1 = new Thread(cliente);
                h1.start();
                contador += 1;
                System.out.println("El Servidor tiene " + contador + "clientes conectados");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
