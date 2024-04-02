package AppSocketsJavaMultihilo.ServidorMultihilo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Paths;

public class HiloHandler implements Runnable {
    private final Socket ch;
    PrintWriter out;
    BufferedReader in;

    public HiloHandler(Socket ch, int contador) throws IOException {
        this.ch = ch;
        out = new PrintWriter(ch.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(ch.getInputStream()));

        System.out.println("Conexión recibida del cliente " + contador + ": "+ ch.getInetAddress().getHostAddress());
    }

    @Override
    public void run() {
        try {
            String pathArchivo = Paths.get("AppSocketsJavaMultihilo\\ServidorMultihilo\\archivote.csv").toAbsolutePath().toString();
            File file_in = new File(pathArchivo);

            FileReader fr;
            fr = new FileReader(file_in);
            BufferedReader br = new BufferedReader(fr);

            String lineaLeida;
            while ((lineaLeida = br.readLine()) != null) {
                out.println(lineaLeida);
            }

            out.println("EOF");
            br.close();
            fr.close();

            System.out.println("Cilente: " + in.readLine());

            out.close();
            in.close();
            ch.close();
        }catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
