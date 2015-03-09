package threadpool;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

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
	}
}
