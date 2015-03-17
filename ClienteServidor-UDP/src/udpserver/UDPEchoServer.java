package udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPEchoServer {
  static final int BUFFERSIZE = 256;

  public static void main(String[] args) {
    DatagramSocket sock;
    DatagramPacket pack = new DatagramPacket(new byte[BUFFERSIZE],
        BUFFERSIZE);
    try {
      sock = new DatagramSocket(1919);
    } catch (SocketException e) {
      System.out.println(e);
      return;
    }
    // echo back everything
    while (true) {
      try {
        sock.receive(pack);
        System.out.println("["+pack.getAddress().getHostAddress()+":"+
                pack.getPort()+"> "+
                (new String(pack.getData())));
        sock.send(pack);
      } catch (IOException ioe) {
        System.out.println(ioe);
      }
    }
  }
}
