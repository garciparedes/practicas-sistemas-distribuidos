package udpprotocolserver;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class ColaStrings {
	private LinkedBlockingQueue<String> lista = new LinkedBlockingQueue<String>();
	
	public void push(final String p) throws InterruptedException {
		lista.put(p);
	}
	
	public String pop() throws InterruptedException {
		return lista.take();
		
	}
	
	public boolean estaVacia() {
		return lista.isEmpty();
	}
}

