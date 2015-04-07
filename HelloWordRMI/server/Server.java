package server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Server {

	public static final int RMI_PORT = 1099;

	public static Registry theRegistry = null;

	public static void main(String [ ] args) {
		try {


			HelloObject oRemoto = new HelloObject();
			String objectName = "ObjetoHello";

			initRegistry();


			if (theRegistry != null) {
				System.err.println("Binding "
					+ objectName
					+ " to Registry..."
				);

				theRegistry.rebind(objectName, oRemoto);

				System.err.println(objectName
					+ " bound"
				);

			} else {
				System.err.println("No hay registro...");
			}
			 
			theRegistry.rebind("ObjetoHello", oRemoto); /* stub); anteriormente */

			System.err.println("Servidor preparado");
		} catch (Exception e) {
			System.err.println("Excepción del servidor: "+e.toString());
			e.printStackTrace();
		}
	}

	public static void initRegistry(){
	
		try {
			System.err.println("Locating registry...");			
			theRegistry = LocateRegistry.getRegistry(RMI_PORT);
			theRegistry.list();
			System.err.println("Registry located!");
		} catch (Exception e) {

			try {
				System.err.println("Locating registry...");			
				System.err.println("Creating registry...");			
				theRegistry = LocateRegistry.createRegistry(RMI_PORT);
				theRegistry.list();
				System.err.println("Registry created!");
			} catch (Exception e2) {
				System.err.println("Error creating registry: " 
					+ e2.getMessage()
				);
				theRegistry = null;		
			}
		}
	}

}