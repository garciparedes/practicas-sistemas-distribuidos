package threadpool;

import java.util.*;

public class ColaStrings {
	private ArrayList<String> lista = new ArrayList<String>();
	
	public synchronized void push(final String p) {
		lista.add(p);
		this.notify(); // hace saber que ha llegado un String
	}
	
	public synchronized String pop() {
		while (lista.size() == 0)
			try { 
				this.wait(); // espera la llegada de un String
			} catch (final InterruptedException e) { }
		return lista.remove(0);
	}
	
	public boolean estaVacia() {
		return lista.isEmpty();
	}
	
	public int size() {
		return lista.size();
	}
}

