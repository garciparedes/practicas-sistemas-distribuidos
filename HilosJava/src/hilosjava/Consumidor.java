/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hilosjava;

/**
 *
 * @author cllamas
 */
public class Consumidor implements Runnable {
    private final ColaStrings cola ;

    public Consumidor(ColaStrings c) {
        this.cola = c;
    }

    public void run() {
        String linea;

        while (true) {
            linea = cola.pop();
            System.out.println(">> " + linea); // imprime
        }
    }
}
