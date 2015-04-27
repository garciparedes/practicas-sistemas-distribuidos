package holaServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;


public interface HolaRegistro extends Remote{
    Map<String,String> lista() throws RemoteException;
}