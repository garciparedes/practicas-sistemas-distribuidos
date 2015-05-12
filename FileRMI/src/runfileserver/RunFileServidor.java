package runfileserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmip2pserver.FileSearch;
import rmip2pserver.FileSearchImpl;

/**
 * Lanza el objeto remoto ObjetoFileServidor de tipo FileServidor.
 * @author cllamas
 */
public class RunFileServidor {
    public static void main(String[] args) {
        try {
        FileSearch fs = new FileSearchImpl();

        Registry registro = LocateRegistry.createRegistry(1099);

        registro.rebind("MasterIndex", fs);
        System.out.println("Objeto remoto 'MasterIndex' enlazado");
        } catch (RemoteException re) {
            re.printStackTrace(System.err);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}