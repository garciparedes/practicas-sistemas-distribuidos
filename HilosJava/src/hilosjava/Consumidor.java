/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hilosjava;


public class Consumidor implements Runnable {
    private final ColaStrings cola ;
    private final String id;

    public Consumidor(ColaStrings c, String id) {
        this.cola = c;
        this.id = id;
    }

    public void run() {
        String linea;

        while (true) {
            linea = cola.pop();
            System.out.println(">> " + linea + " " + id ); // imprime
        }
    }
}
