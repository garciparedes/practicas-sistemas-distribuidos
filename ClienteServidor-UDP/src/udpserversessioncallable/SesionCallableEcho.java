package udpserversessioncallable;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.concurrent.Callable;

/**
 * SesionCallableEcho que se invoca por petición, no por cliente.
 * @author cllamas
 */
public class SesionCallableEcho implements Callable {
    final SocketAddress sa;
    static private int idCounter = 0;
    private final int id;
    private DatagramPacket dp;
    private DatagramSocket ds;
    
    SesionCallableEcho(SocketAddress sa, DatagramSocket ds) {
        this.sa = sa;
        this.ds = ds;
        this.id = ++SesionCallableEcho.idCounter;
    }

    @Override
    public Boolean call() {
        String texto = new String(dp.getData());
        if (!texto.equals("Adios.") ) {
            System.out.println("<"+id+">["+sa+"]: "+texto);
            try { this.ds.send(dp); }
            catch (IOException ioe) {
                System.err.println("SessionCallableEcho: IOE: "+ioe);
            }
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    public int getId() {
        return id;
    }
    public void putDP(DatagramPacket dp) {
        this.dp = dp;
    }
}