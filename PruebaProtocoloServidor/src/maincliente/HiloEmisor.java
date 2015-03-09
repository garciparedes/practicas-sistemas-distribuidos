package maincliente;

import java.io.*;
import java.net.Socket;

public class HiloEmisor extends Thread {
    private final Socket socket;
    private final BufferedReader teclado;
    private final PrintStream red;
    private final HiloReceptor hr;

    public HiloEmisor(Socket socket, InputStream is, HiloReceptor hr)  throws IOException {
        this.teclado = new BufferedReader(new InputStreamReader(is));
        this.socket = socket;
        this.red = new PrintStream(socket.getOutputStream());
        this.hr = hr;
    }

    @Override
    public void run() {
        while (true) {

            try {
                String linea = teclado.readLine();

                red.println(linea);
                if (linea.equals(".")) {
                    hr.para();
                    return;
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}