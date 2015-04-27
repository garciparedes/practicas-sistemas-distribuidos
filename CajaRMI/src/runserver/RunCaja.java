package runserver;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import cajaserver.CajaImpl;
import util.Contador;


/**
 * Lanza el servidor de Caja(s) de Contador(es)
 * @author cllamas
 */
public class RunCaja {
	
	private static final int RMI_PORT = 1099;
	
	private static final String CAJA_NAME = "CajaRemota";
	
    public static void main(String[] args) {
        try {
            CajaImpl<Contador> cc = new CajaImpl<Contador>();

            cc.pon(new Contador());
            Registry registro = LocateRegistry.createRegistry(RMI_PORT);

            registro.rebind(CAJA_NAME, cc);
            System.out.println("Objeto remoto enlazado");
        } catch (RemoteException re) {
            re.printStackTrace(System.err);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}