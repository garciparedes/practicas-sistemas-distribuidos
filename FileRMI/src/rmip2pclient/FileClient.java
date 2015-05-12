package rmip2pclient;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.rmi.Naming;

import rmip2pserver.FileLector;
import rmip2pserver.FileSearch;
import rmip2pserver.FileServer;

/**
*
* @author cllamas
*/
public class FileClient {

   /**
    * @param args the command line arguments
*/
   public static void main(String[] args) {
       String localizador;
       
       /*
       if (args.length <1) {
          System.out.println("Introduce como argumento el localizador de FileServer!!");
          return;
       } else
          localizador = args[0];
       */
       localizador = "157.88.125.23/SergioGarciaPrado";
       

       String nombreArchivo = "Hola.txt";
       FileServer fServer = null;
       try {
    	   FileSearch fSearch = (FileSearch)
                   Naming.lookup("rmi://localhost:1099/MasterIndex");
    	   
           fServer = (FileServer) fSearch.busca(localizador);
       } catch (Exception e) {
           System.err.println("Cliente: Excepcion: " + e);
           e.printStackTrace();
       }
       if (null != fServer) {
           try {
        	   System.out.println(fServer.explora());
               FileLector fLector = (FileLector) fServer.abre(nombreArchivo);
               String linea;

               try {
                   while (null != (linea = fLector.leeLinea())) {
                       System.out.println(nombreArchivo + ": " + linea);
                   }
               } catch (EOFException ioe) {
                   System.err.println("Archivo finalizado con EOFException");
               } catch (Exception ex) {
                   ex.printStackTrace(System.err);
               }
           } catch (FileNotFoundException fnfe) {
               System.err.println("El archivo " + nombreArchivo + " no existe.");
           } catch (Exception ex) {
               ex.printStackTrace(System.err);
           }
       } else {
           System.err.println("El Servidor " + localizador + " no está registrado :(");
       }

   }
}