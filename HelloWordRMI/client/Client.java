package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
   private Client () { }

   public static void main(String [ ] args) {
      String host = (args.length < 1) ? null : args[0];
      try {
         /* OBSOLETO: Esto está un poco rancio,
            Registry registro = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registro.lookup(***);
          */
         Hello stub = (Hello) Naming.lookup(***); /* ¿Qué hay aquí?*/

         String respuesta = stub.sayHello();
         System.out.println("[Respuesta: "+respuesta+"]");
      } catch (Exception e) {
         System.err.println("<Cliente: Excepcion: "+e);
         e.printStackTrace();
      }
   }
}