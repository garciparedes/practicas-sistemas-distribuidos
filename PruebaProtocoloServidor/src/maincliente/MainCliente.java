package maincliente;

import java.net.InetAddress;
import java.net.Socket;

public class MainCliente {

    public static void main(String[] args) throws Exception {
        String hostname = "localhost";
        InetAddress ia    = InetAddress.getByName(hostname);
        Socket socket     = new Socket(ia, 2000);
        HiloReceptor receptor = new HiloReceptor(socket, System.out);
        HiloEmisor emisor       = new HiloEmisor(socket, System.in, receptor);

        /* por acuerdo, el cliente termina cuando se teclea una línea
         * con un punto, que es repetida por el servidor, pero que termina el
         * cliente antes de imprimirse.
         */
        emisor.start();
        receptor.start();

    }
}