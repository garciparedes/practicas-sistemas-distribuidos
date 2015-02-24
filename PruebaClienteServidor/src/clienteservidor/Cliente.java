package clienteservidor;

import java.net.*;
import java.io.*;

public class Cliente {
	
public static void main(String[] args) throws IOException {        
        // String host = args[0];
        String host = "localhost";  // para simplificar
        String linea;

        try (Socket sock = new Socket(host, 2000) /* creación del socket */) {
            final Reader r1 = new InputStreamReader(System.in);
            final BufferedReader teclado = new BufferedReader(r1); /* teclado */

            OutputStream out = sock.getOutputStream(); /* socket salida */

            BufferedReader inred;
            try (PrintStream outred = new PrintStream(out)) {
                InputStream in = sock.getInputStream(); /* socket de entrada */
                Reader r2 = new InputStreamReader(in);
                inred = new BufferedReader(r2);
                
                while ((linea = teclado.readLine()) != null) { /* lee el teclado */
                    outred.println(linea); /* manda al servidor */
                    linea = inred.readLine(); /* lee echo del servidor */
                    System.out.println("<<"+linea+">>");
                }
            } /* socket de entrada */
            inred.close();
        }
}
}
