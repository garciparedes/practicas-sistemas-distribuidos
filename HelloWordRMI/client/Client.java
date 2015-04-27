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
		
		String hostName = "127.0.0.1";
		String port = "1099";
		String SERVICE_NAME = "HelloObjeto";
		
		/*
		String service= (
			"//" + hostName 
			+ ":" + port 
			+ "/" + SERVICE_NAME
		);
		*/

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