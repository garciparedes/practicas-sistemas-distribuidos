package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    String sayHello() throws RemoteException;

    void pruebaExcepcion(boolean lanza) 
		throws RemoteException, MyPruebaException;
}