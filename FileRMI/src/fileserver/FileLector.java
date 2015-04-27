package fileserver;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz para leer secuencialmente las líneas de un archivo de texto.
 * @author cllamas
 */
public interface FileLector extends Remote {
    /**
     * Utiliza BufferedReader.readline() para leer líneas del archivo.
     * @return String con la línea actual del archivo.
     * @throws ...
     * @throws IOException del mismo modo que BufferedReader->readLine()
     */
    String leeLinea() throws RemoteException, IOException;
}