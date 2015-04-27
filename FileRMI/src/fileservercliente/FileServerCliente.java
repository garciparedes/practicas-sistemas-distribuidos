package fileservercliente;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.rmi.Naming;

import fileserver.FileLector;
import fileserver.FileServidor;

/**
 * Cliente de prueba de FileServer
 * @author cllamas
 */
public class FileServerCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nombreArchivo = "Hola.txt";
        FileLector fl;
        FileServidor fs;
        String linea;

        try {
            fs = (FileServidor) Naming.lookup("rmi://localhost:1099/ObjetoFileServidor");

            try {
                fl = fs.abre(nombreArchivo);
                try {
                    while (null != (linea = fl.leeLinea())) {
                        System.out.println(nombreArchivo+ ": "+linea);
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
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
}