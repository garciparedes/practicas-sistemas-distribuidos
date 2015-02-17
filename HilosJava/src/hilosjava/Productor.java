/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hilosjava;
import java.io.*;

/**
 *
 * @author cllamas
 */
public class Productor implements Runnable {
    private final ColaStrings cola ;

    public Productor(ColaStrings c) {
        this.cola = c;
    }

    public void run() {
        final Reader r1 = new InputStreamReader(System.in);
        final BufferedReader teclado = new BufferedReader(r1);

        String linea;
        try {
            while ((linea = teclado.readLine()) != null) { // lee teclado
                cola.push(linea);
            }
        } catch (final IOException x) { x.printStackTrace(System.err); }
    }
}