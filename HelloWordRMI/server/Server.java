package server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Hello {

   public Server() throws RemoteException { }

   public String sayHello() throws RemoteException {
      return "Hola, mundo!";
   }

   public static void main(String [ ] args) {
      try {
         Server oRemoto = new Server();
         /* Esto era necesario anteriormente, cuando no se extendía directamente
          * UnicastRemoteObject:
            Hello stub = (Hello) UnicastRemoteObject.exportObject(oRemoto, 0);
          */

         /* Enlaza el stub del objeto remoto en el registro */
         Registry registro = LocateRegistry.getRegistry("localhost");
         /* Anteriormente
            registro.bind("ObjetoHello", stub);
          */
         registro.rebind("ObjetoHello", oRemoto); /* stub); anteriormente */

         System.err.println("Servidor preparado");
      } catch (Exception e) {
         System.err.println("Excepción del servidor: "+e.toString());
         e.printStackTrace();
      }
   }
}