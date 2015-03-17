package udpclient;

import java.io.*;
import java.net.*;

import mensajes.MensajeProtocolo;
import mensajes.Primitive;
import mensajes.UDPHelper;

/**
 * Cliente0 con un escenario de uso del protocolo de la Lección 2.
 * @author cllamas
 */
public class Cliente0 {

    static final private int PUERTO = 1919;
    static final private int MAXDATAGRAMA = 1024;
    
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        String linea;
        
        byte[] bytesSalida  = null;
        byte[] bytesEntrada = new byte[MAXDATAGRAMA];
        UDPHelper serialHandler = new UDPHelper(bytesEntrada);
        DatagramPacket output = null;
        DatagramPacket input  = null;      
        
        DatagramSocket socket = new DatagramSocket();
        InetAddress iaServer = InetAddress.getByName(host);
        socket.connect(iaServer, PUERTO);
        
        try {
            System.out.println("Pulsa <Enter> para comenzar"); System.in.read();
/* Caso 1 */
            /* >> */ bytesSalida = serialHandler.aBytes(new MensajeProtocolo(Primitive.HELLO, "Pedro"));
            output = new DatagramPacket(bytesSalida, bytesSalida.length, iaServer, PUERTO);
            socket.send(output);
           
/* << */    input       = new DatagramPacket(bytesEntrada, bytesEntrada.length);
            socket.receive(input);
            System.out.println((MensajeProtocolo) serialHandler.aMensaje());

            System.out.println("Pulsa <Enter> para continuar"); System.in.read();
/* Caso 2.1 */
/* >> */    bytesSalida = serialHandler.aBytes(new MensajeProtocolo(Primitive.PUSH, "Estamos en Estambul."));
            output      = new DatagramPacket(bytesSalida, bytesSalida.length, iaServer, PUERTO);
            socket.send(output);

/* << */    input       = new DatagramPacket(bytesEntrada, bytesEntrada.length);
            socket.receive(input);
            System.out.println((MensajeProtocolo) serialHandler.aMensaje());
            
            System.out.println("Pulsa <Enter> para continuar"); System.in.read();
/* Caso 2.2 */
/* >> */    bytesSalida = serialHandler.aBytes(new MensajeProtocolo(Primitive.PUSH, "Estamos en Lisboa."));
            output      = new DatagramPacket(bytesSalida, bytesSalida.length, iaServer, PUERTO);
            socket.send(output);

/* << */    input       = new DatagramPacket(bytesEntrada, bytesEntrada.length);
            socket.receive(input);
            System.out.println((MensajeProtocolo) serialHandler.aMensaje());
            
            System.out.println("Pulsa <Enter> para continuar"); System.in.read();
/* Caso 3 */
/* >> */    bytesSalida = serialHandler.aBytes(new MensajeProtocolo(Primitive.PULL));
            output      = new DatagramPacket(bytesSalida, bytesSalida.length, iaServer, PUERTO);
            socket.send(output);

/* << */    input       = new DatagramPacket(bytesEntrada, bytesEntrada.length);
            socket.receive(input);
            System.out.println((MensajeProtocolo) serialHandler.aMensaje());
            
            System.out.println("Pulsa <Enter> para continuar"); System.in.read();
/* Caso 4 */
/* >> */    bytesSalida = serialHandler.aBytes(new MensajeProtocolo(Primitive.PULL_WAIT));
            output      = new DatagramPacket(bytesSalida, bytesSalida.length, iaServer, PUERTO);
            socket.send(output);

/* << */    input       = new DatagramPacket(bytesEntrada, bytesEntrada.length);
            socket.receive(input);
            System.out.println((MensajeProtocolo) serialHandler.aMensaje());

            System.out.println("Pulsa <Enter> para finalizar"); System.in.read();

            /**
             * * aquí se supone que tiene que llegar otro cliente e insertar un
             * mensaje en la cola
             */
            /* FIN del escenario 1 */
        } catch (IOException e) {
            System.err.println("Cliente: Error de apertura o E/S sobre objetos: " + e);
        } catch (Exception e) {
            System.err.println("Cliente: Excepción. Cerrando Sockets: " + e);
        } finally {
            socket.close();
        }
    }
}
