package threadpool;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

import protocolo.MensajeProtocolo;
import protocolo.Primitive;

class SirvienteRunnable implements Runnable {
	private final Socket socket;
	private final ColaStrings cola;
	
	SirvienteRunnable(Socket s, ColaStrings c) {
		this.socket = s;
		this.cola = c;
	}
	public void run() {
	/* Aquí servimos la conexión y es donde se complica la cosa con el
	* protocolo y la serialización.
	*/
		
		/* preparamos los streams para objetos */
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		// bucle de ECHO
		try {
			
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			
			while (true) {
			
				MensajeProtocolo me = (MensajeProtocolo) ois.readObject();
				MensajeProtocolo ms;
				
				System.out.println("<< "+me); /* mensaje entrante de depuración */
				
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
						
				} /* fin switch */
				
				oos.writeObject(ms);
				System.out.println(">> "+ms); /* mensaje saliente de depuración */
			}
			
		} catch (IOException e) {
			System.err.println("Fin de Cliente");
			
		} catch (ClassNotFoundException ex) {
			System.err.println("Clase MensajeProtocolo no encontrada");
			
		} finally {
			/* seguimos deshaciéndonos de los sockets y canales abiertos. */
			try {
				ois.close();
				oos.close();
				socket.close();
				
			} catch (Exception x) {
				System.err.println("Sirviente: Error irrecuperable de cierre de sockets");
				
			}
		}
	}
}
