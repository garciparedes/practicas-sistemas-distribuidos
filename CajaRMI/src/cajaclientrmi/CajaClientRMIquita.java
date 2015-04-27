package cajaclientrmi;

import cajaserver.Caja;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import util.Contador;

/**
 * Quita el elemento de la caja.
 * @author cllamas
 */
public class CajaClientRMIquita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Contador c = null;
        try {
            Caja caja = (Caja) Naming.lookup("rmi://localhost:1099/CajaRemota");

            c = (Contador) caja.lee();
            if (null != c) {
                System.out.println("valor de la caja: "+c.lee());
                caja.quita();
                System.out.println("Caja vaciada.");
            } else
                System.out.println("La caja estaba vacía.");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

}