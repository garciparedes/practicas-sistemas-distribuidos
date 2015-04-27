package cajaclientrmi;

import cajaserver.Caja;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import util.Contador;

/**
 * Introduce en Caja un elemento Contador con valor 1.
 * @author cllamas
 */
public class CajaClientRMIset {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Contador c = new Contador();
        c.inc();
        System.out.println("Valor del nuevo contador: "+c.lee());
        try {
            Caja caja = (Caja) Naming.lookup("rmi://localhost:1099/CajaRemota");
            caja.pon(c);
            
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

}