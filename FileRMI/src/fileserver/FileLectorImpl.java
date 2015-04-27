package fileserver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 *
 * @author cllamas
 */
public class FileLectorImpl extends UnicastRemoteObject implements FileLector {
    private final BufferedReader br;
    public FileLectorImpl(String nombre) throws RemoteException, FileNotFoundException {
        super();
        this.br = new BufferedReader(new FileReader(nombre));
    }

    @Override
    public String leeLinea() throws RemoteException, IOException {
        return br.readLine();
    }
}