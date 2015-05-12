package rmip2pserver;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Interfaz que construye un objeto remoto FileLector para leer archivos de texto.
 * @author cllamas
 */
public interface FileServer extends Remote, Serializable {
    public FileLector abre(String nombre) throws RemoteException, FileNotFoundException;
    
    public String explora() throws RemoteException, FileNotFoundException;
}