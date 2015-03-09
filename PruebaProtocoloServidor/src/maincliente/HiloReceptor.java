package maincliente;

import java.io.*;
import java.net.Socket;

public class HiloReceptor extends Thread {
    private boolean parado;
    private final Socket socket;
    private final PrintStream pantalla;
    private final BufferedReader red;

    public HiloReceptor(Socket socket, OutputStream oe) throws IOException {
        this.parado = false;
        this.pantalla = new PrintStream(oe);
        this.socket = socket;
        this.red = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    protected void para() {
        this.parado = true;
    }

    @Override
    public void run() {
        while (true) {
            if (parado) {
                System.out.println("Saliendo!");
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.err.println("Excepcion IO: " + ex);
                }
                return;
            }
            try {
                String linea = this.red.readLine();
                this.pantalla.println(linea);
            } catch (IOException ex) {
                System.err.println("Excepcion IO: "+ ex);
            }
        }
    }
}
