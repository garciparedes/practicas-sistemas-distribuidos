package servidorHola;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hola extends Remote{
   String sayHello() throws RemoteException ;
}
