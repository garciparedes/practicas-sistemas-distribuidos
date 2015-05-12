package servidorHola;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;


public class ServidorHola extends UnicastRemoteObject implements Hola {
   public ServidorHola() throws RemoteException {
	   super();
   }
   
   public ServidorHola(int puerto, RMIClientSocketFactory csf, RMIServerSocketFactory ssf)
		   throws RemoteException {
	   super(puerto, csf, ssf);
   }

   public String sayHello() throws RemoteException {
          return "Hola, mundo!";
   }

   public static void main(String[] args) {
      try {
		  RMIClientSocketFactory rmicsf = new SslRMIClientSocketFactory();
		  RMIServerSocketFactory rmissf = new SslRMIServerSocketFactory(); 
		
		  Registry registro = LocateRegistry.createRegistry(1099, rmicsf, rmissf);

		 ServidorHola oRemoto = new ServidorHola(1099, rmicsf, rmissf);
		
		 registro.rebind("ObjetoHola", oRemoto) ;
		
		 System.err.println("Servidor preparado");
      } catch (Exception e) {
         System.err.println("Excepción del servidor: " + e.toString()); e.printStackTrace();
      }
   }
} 