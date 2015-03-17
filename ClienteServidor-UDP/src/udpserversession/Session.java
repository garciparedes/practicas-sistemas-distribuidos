package udpserversession;

import java.net.SocketAddress;

/**
 * Sesion donde almacenamos los datos del contexto que necesitemos.
 * @author cllamas
 */
public class Session {
    final SocketAddress sa;
    
    static private int idCounter = 0;
    
    private final int id;
    
    Session(SocketAddress sa) {
        this.sa = sa;
        this.id = ++Session.idCounter;
    }

    public int getId() {
        return id;
    }
}