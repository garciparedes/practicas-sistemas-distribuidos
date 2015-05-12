package rmip2pserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
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
public class FileServerImpl extends UnicastRemoteObject implements FileServer, Serializable {

    public FileServerImpl() throws RemoteException {
        super();
    }

    @Override
    public FileLector abre(String nombre) throws RemoteException , FileNotFoundException {
    	System.out.println("LOG: "+FileServerImpl.class.getName()+
                ": abre(): Abierto "+nombre);
        return new FileLectorImpl(nombre);
    }

	@Override
	public String explora() throws RemoteException, FileNotFoundException {
		File dir = new File(".");
		StringBuilder listado = new StringBuilder();
		for (int x=0;x<dir.list().length;x++)
		    listado.append(dir.list()[x]);
    		listado.append("\n");

		return listado.toString();
	}

}