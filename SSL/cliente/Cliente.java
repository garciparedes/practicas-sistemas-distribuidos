package clienteHola;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;

import javax.rmi.ssl.SslRMIClientSocketFactory;

import servidorHola.Hola;


public class Cliente {
    private Cliente() { }

    public static void main(String[] args) {
        String host = (args.length < 1) ? null : args[0];

        try {
  		  	RMIClientSocketFactory rmicsf = new SslRMIClientSocketFactory();

        	Registry registro = LocateRegistry.getRegistry("localhost", 1099, rmicsf);


            Hola stub = (Hola) registro.lookup("ObjetoHola");

            String respuesta = stub.sayHello();
            System.out.println("response: " + respuesta);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
} 