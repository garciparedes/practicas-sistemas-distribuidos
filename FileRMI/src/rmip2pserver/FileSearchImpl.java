package rmip2pserver;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


/**
 * Interfaz que referencias a manejadores de archivos entre clientes. Cada
* cliente es libre de crear y registrar manejadores de archivos en su proceso
* local y los registra frente a este servicio.
*
*@author cllamas
*/
public class FileSearchImpl extends UnicastRemoteObject implements FileSearch, Serializable {

    private final HashMap<String, FileServer> oremotos;

    public FileSearchImpl() throws RemoteException {
        super();
        this.oremotos = new HashMap<>();
    }

    @Override
    public String registra(String nombre, FileServer fl)
            throws RemoteException {
        String localizador = null;
        try {
            localizador = FileSearchImpl.getClientHost() + "/" + nombre;
            this.oremotos.put(localizador, fl);
            System.out.println("LOG: " + FileSearchImpl.class.getName()
                    + ": registra(): " + localizador + ".");
        } catch (ServerNotActiveException ex) {
            /* Cliente desaparecido en combate */
            System.out.println("LOG: " + FileSearchImpl.class.getName()
                    + ": registra(): "
                    + "Intento de registro por cliente no accesible.");
        }

        return localizador;
    }

    @Override
    public FileServer busca(String nombre)
            throws RemoteException {
        System.out.println("LOG: " + FileSearchImpl.class.getName()
                + ": busca(): Accedido " + nombre);
        return oremotos.get(nombre);
    }
}