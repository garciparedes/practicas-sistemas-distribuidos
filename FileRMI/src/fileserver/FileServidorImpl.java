package fileserver;

import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Implementación de FileServidor
 * @author cllamas
 */
public class FileServidorImpl extends UnicastRemoteObject implements FileServidor {

    public FileServidorImpl() throws RemoteException {
        super();
    }

    @Override
    public FileLector abre(String nombre) throws RemoteException , FileNotFoundException {
        return new FileLectorImpl(nombre);
    }

}