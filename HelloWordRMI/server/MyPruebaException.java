package server;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MyPruebaException extends Exception {

    public MyPruebaException(String msg) {
        super(msg);
    }

}