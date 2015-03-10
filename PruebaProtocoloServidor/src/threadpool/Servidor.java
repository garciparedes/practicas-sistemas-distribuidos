package threadpool;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Servidor {
	
	public static final int PUERTO=2000; /* puerto de escucha del ServerSocket */
	public static final int NThreads=5; /* número de hilos del ThreadPool */
	
	private static final ColaStrings cola = new ColaStrings();
	
	public static ExecutorService exec = Executors.newFixedThreadPool(NThreads);
	/* tanto static queda un poco feo, pero es mas corto para la ocasión */
	
	public static void main(String args[]) {
		System.out.println("Arrancando el servidor:");
		runServer();
	}
	 
	private static void runServer() {
		try {
			ServerSocket sock = new ServerSocket(PUERTO);
			while (true) {
				Socket s = sock.accept();
				exec.execute(new SirvienteRunnable(s, cola));
			}
		} catch (IOException ioe) {
			
			exec.shutdown();
			try{
				exec.awaitTermination(1000, TimeUnit.MILLISECONDS);
			} catch(InterruptedException ie){}
			while(!exec.isTerminated()){}
			System.err.println("Servidor: Error de E/S: "+ioe);
		}
	}
}