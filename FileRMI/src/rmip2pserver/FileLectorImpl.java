package rmip2pserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 *
 * @author cllamas
 */
public class FileLectorImpl extends UnicastRemoteObject implements FileLector, Serializable {
    private final BufferedReader br;
    private final String nombre ;

    public FileLectorImpl(String nombre) throws RemoteException, FileNotFoundException {
        super();
        this.nombre = nombre;
        this.br = new BufferedReader(new FileReader(nombre));
    }

    @Override
    public String leeLinea() throws RemoteException, IOException {
    	
    	System.out.println("LOG: "+FileLectorImpl.class.getName()+
                 ": leeLinea(): "+nombre);

        return br.readLine();
    }
}