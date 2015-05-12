package rmip2pclient;

import java.rmi.Naming;

import rmip2pserver.FileSearch;
import rmip2pserver.FileServer;
import rmip2pserver.FileServerImpl;

/**
 * Cliente de prueba de FileServer
 * @author cllamas
 */
public class RMIP2PClientServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        

        try {

            FileSearch fSearch = (FileSearch) Naming.lookup("rmi://localhost:1099/MasterIndex");
            
            FileServer fServer = new FileServerImpl();
            
            String clave = fSearch.registra("SergioGarciaPrado", fServer);
            System.out.println("Servidor local registrado con nombre: "+clave);
            
         } catch (Exception e) {
            System.err.println("<Cliente: Excepcion: "+e);
            e.printStackTrace();
         }
    }
}