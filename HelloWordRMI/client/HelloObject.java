package server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloObject extends UnicastRemoteObject implements Hello {

	public HelloObject() throws RemoteException { }

	public String sayHello() throws RemoteException {
		return "Hola, mundo!";
	}

	public void pruebaExcepcion(boolean lanza) 
		throws RemoteException, MyPruebaException {

		if (lanza){
			System.err.println("Salta la Exception");
			throw new MyPruebaException("Exception de prueba");
		} else {

			System.err.println("No salta la Exception");
		}
	}

}