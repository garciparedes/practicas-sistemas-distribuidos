package udpserversessioncallable;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * UDPEchoServerSessionCallable mantiene sesiones por cliente e invoca
 * hilos adicionales por cada invocación, no por cada cliente.
 * @author cllamas
 */
public class UDPEchoServerSessionCallable {
  static final int BUFFERSIZE = 256;
  static final HashMap<SocketAddress,SesionCallableEcho> sesiones = new HashMap();

  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(3);
    DatagramSocket sock;
    try {
      sock = new DatagramSocket(1919);
    } catch (SocketException e) {
      System.out.println(e);
      return;
    }
    // echo back everything
    while (true) {
      try {
        DatagramPacket pack = new DatagramPacket(new byte[BUFFERSIZE],BUFFERSIZE);
    
        sock.receive(pack);
        SesionCallableEcho s;
        
        if ((s = sesiones.get(pack.getSocketAddress())) == null) {
            s = new SesionCallableEcho(pack.getSocketAddress(), sock);
            sesiones.put(pack.getSocketAddress(), s);
        }
        s.putDP(pack);
        Future<Boolean> submit = pool.submit(s);
 
      } catch (IOException ioe) {
        System.out.println(ioe);
      }
    }
  }
}