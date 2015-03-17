package udpprotocolserver;

import mensajes.MensajeProtocolo;
import mensajes.Primitive;
import mensajes.UDPHelper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import mensajes.ExceptionFaltaMensaje;
import mensajes.ExceptionSobraMensaje;

/**
 * UDPProtocolServer que obedece al protocolo de la Lección 2.
 * Es Monoenhebrado, emplea sockets UDP y serialización.
 * @author cllamas
 */
public class UDPProtocolServer {

    static final private int PUERTO = 1919;
    static final private int MAXDATAGRAMA = 1024;

    public static void main(String[] args) throws IOException {
        String linea;
        MensajeProtocolo me;
        MensajeProtocolo ms;
        ColaStrings cola = new ColaStrings();

        byte[] bytesSalida = null;
        byte[] bytesEntrada = new byte[MAXDATAGRAMA];
        UDPHelper serialHandler = new UDPHelper(bytesEntrada);
        DatagramPacket output = null;
        DatagramPacket input = null;

        DatagramSocket socket;
        InetAddress iaServer = null;

        try {
            socket = new DatagramSocket(PUERTO);
        } catch (SocketException e) {
            System.out.println("Puerto ocupado en el servidor: " + e);
            return;
        }
        // echo back everything
        while (true) {
            try {
                /* Recepción del mensaje del protocolo */
                /* << */ input = new DatagramPacket(bytesEntrada, bytesEntrada.length);
                                 socket.receive(input);

                me = (MensajeProtocolo) serialHandler.aMensaje();
                System.out.println("<<" + me);

                switch (me.getPrimitive()) {
                    case HELLO:
                        ms = new MensajeProtocolo(Primitive.HELLO, "Hola desde el servidor");
                        break;
                    case PUSH:
                        cola.push(me.getMessage());
                        ms = new MensajeProtocolo(Primitive.PUSH_OK);
                        break;
                    case PULL:
                        synchronized (cola) {
                            if (cola.estaVacia()) {
                                ms = new MensajeProtocolo(Primitive.NOTHING);
                            } else {
                                ms = new MensajeProtocolo(Primitive.PULL_OK, cola.pop());
                            }
                        }
                        break;
                    case PULL_WAIT:
                        ms = new MensajeProtocolo(Primitive.PULL_OK, cola.pop());
                        break;
                    default:
                        ms = new MensajeProtocolo(Primitive.NOTUNDERSTAND);
                        // break;
                }
                /* Envío del mensaje del protocolo */
                /* >> */ bytesSalida = serialHandler.aBytes(ms);
                System.out.println("He convertido a bytes: "+ms);
                output = new DatagramPacket(bytesSalida, bytesSalida.length, input.getAddress(), input.getPort());
                System.out.println("He creado el datagrama y voy a enviar");
                socket.send(output);
            } catch (IOException ioe) {
                System.out.println(ioe);
            } catch (ExceptionSobraMensaje e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (ExceptionFaltaMensaje e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
