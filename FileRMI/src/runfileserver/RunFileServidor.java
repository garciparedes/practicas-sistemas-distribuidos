package runfileserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import fileserver.FileServidor;
import fileserver.FileServidorImpl;

/**
 * Lanza el objeto remoto ObjetoFileServidor de tipo FileServidor.
 * @author cllamas
 */
public class RunFileServidor {
    public static void main(String[] args) {
        try {
        FileServidor cc = new FileServidorImpl();

        Registry registro = LocateRegistry.createRegistry(1099);

        registro.rebind("ObjetoFileServidor", cc);
        System.out.println("Objeto remoto 'ObjetoFileServidor' enlazado");
        } catch (RemoteException re) {
            re.printStackTrace(System.err);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}