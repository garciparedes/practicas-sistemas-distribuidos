package holaCliente;

import java.util.Map;
import java.rmi.Naming;
import holaServer.Hola;
import holaServer.HolaRegistro;

public class Cliente {

   public static void main(String [ ] args) {
      String nombre = (args.length < 1) ? "anonimo" : args[0];
      try {
         Object obj = Naming.lookup("ObjetoHola");

         Hola oHola = (Hola) obj;
         
         HolaRegistro oHolaRegistro = (HolaRegistro) obj;

         String respuesta = oHola.saluda(nombre);
         System.out.println("[Respuesta: "+respuesta+"]");

         Map<String,String> listado;
         listado = oHolaRegistro.lista();

         for (String clave : listado.keySet()) {
             String valor = listado.get(clave);
             System.out.println("< "+clave+" , "+valor+" >");
         }
      } catch (Exception e) {
         System.err.println("<Cliente: Excepcion: "+e);
         e.printStackTrace();
      }
   }
}