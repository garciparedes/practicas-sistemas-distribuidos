package udpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteSimple {

    public static void main(String[] args) throws IOException {
        String host = "localhost";
        String linea;
        int port = 1919;
        InetAddress ia = InetAddress.getByName(host);
        DatagramSocket socket = new DatagramSocket();
        socket.connect(ia, port);

        
        Reader r1 = new InputStreamReader(System.in);
        BufferedReader teclado = new BufferedReader(r1); /* teclado */
        
        while ((linea = teclado.readLine()) != null) {
            byte[] dataOut = linea.getBytes();
            DatagramPacket output =
                    new DatagramPacket(dataOut, dataOut.length, ia, port);
            socket.send(output);
            byte[] dataIn = new byte[160];        
            DatagramPacket input = new DatagramPacket(dataIn, dataIn.length);
        
            socket.receive(input);
            linea = new String(input.getData());
            System.out.println("Echo: "+linea);
            if (linea.equals("Adios.")) System.exit(0);
        }
        socket.close();
    }
}
