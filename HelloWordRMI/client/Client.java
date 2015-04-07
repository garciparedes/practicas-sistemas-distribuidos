package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;
import server.Hello;
import server.MyPruebaException;

public class Client {
	private Client () { }

	public static void main(String [ ] args) {

		String host = (args.length < 1) ? null : args[0];

		try {
			Hello stub = (Hello) Naming.lookup(host); 

			String respuesta = stub.sayHello();
			System.out.println("[Respuesta: "+respuesta+"]");

			try {
				stub.pruebaExcepcion(false);
				stub.pruebaExcepcion(true);

			} catch(MyPruebaException e){
				System.err.println(e.getMessage());
			}

		} catch (Exception e) {
			System.err.println("<Cliente: Excepcion: "+e);
			e.printStackTrace();
		}
	}
}