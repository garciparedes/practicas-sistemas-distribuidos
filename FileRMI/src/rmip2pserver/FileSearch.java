package rmip2pserver;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz que referencias a manejadores de archivos entre clientes. Cada
* cliente es libre de crear y registrar manejadores de archivos en su proceso
* local y los registra frente a este servicio.
*
*@author cllamas
*/
public interface FileSearch extends Remote, Serializable {

    /**
     * Registra una referencia FileServer en el índice maestro para que un
     * cliente acceda a los archivos de otro cliente-servidor que disponga
     * de un objeto FileServer.
* Las referencias a FileServer se asocian a claves que se construyen 
* concatenando la IP en forma textual (DDN) del cliente-servidor,
* y el nombreBase proporcionado.
*
* La clave puede colisionar con otra ya existente, de modo que se
* rechaza la última petición.
*
*@return la clave asociada al servidor y que deberá publicarse de algún
*         modo por el cliente-servidor.
*@param nombreBase al que se añade la IP del cliente (DDN) para crear la
*                   clave.
*@param fs con la referencia al objeto remoto del cliente.
*@throws RemoteException 
*/
    public String registra(String nombreBase, FileServer fs)
            throws RemoteException;

    /**
     * Referencia al FileLector registrado por un cliente.
*@param localizador nombre construído por el servidor agregando la IP del cliente
*              con el nombreBase proporcionado por el cliente que registra
*              el objeto (<i>IP-DDN</i>:<i>nombreBase</i>). Si la clave no
*              existe, devuelve null.
*@return la referencia al objeto remoto del cliente.
*@throws RemoteException
*/
    public FileServer busca(String localizador)
            throws RemoteException;
}