package holaServer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunHola {

	   public static void main(String [ ] args) {
	      try {
	         HolaImpl oRemoto = new HolaImpl();
	         //ES UN GET REGISTRY
	         //Registry registro = LocateRegistry.getRegistry("localhost");
	           Registry registro = LocateRegistry.createRegistry(1099);

	         registro.rebind("ObjetoHola", oRemoto);

	         System.err.println("Servidor preparado");
	      } catch (Exception e) {
	         System.err.println("Excepción del servidor: "+e.toString());
	         e.printStackTrace();
	      }
	   }
	}