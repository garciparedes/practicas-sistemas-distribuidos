/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hilosjava;
import java.util.*;
/**
 *
 * @author cllamas
 */
public class ColaStrings {

    private List<String> lista = new ArrayList<String>();

    public synchronized void push(final String p) {
        lista.add(p);
        this.notify(); // hace saber que ha llegado un String
    }

    public synchronized String pop() {
        while (lista.size() == 0)
            try { this.wait(); // espera la llegada de un String
            } catch (final InterruptedException e) { }
        return lista.remove(0);
    }
}

