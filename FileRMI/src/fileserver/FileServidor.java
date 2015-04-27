package fileserver;

import java.io.FileNotFoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Interfaz que construye un objeto remoto FileLector para leer archivos de texto.
 * @author cllamas
 */
public interface FileServidor extends Remote {
    public FileLector abre(String nombre) throws RemoteException, FileNotFoundException;
}