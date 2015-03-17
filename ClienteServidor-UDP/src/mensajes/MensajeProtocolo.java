package mensajes;

import java.util.*;
import java.io.*;

public class MensajeProtocolo implements Serializable {
	private Primitive primitiva;
	private String mensaje ;
	private int size;
	
	public MensajeProtocolo(Primitive p) throws ExceptionFaltaMensaje{
		/* hemos metido dos assert por la peligrosidad del constructor
		* recordad que para activar los assert en la JVM se usa: java -ae ...
		* proviene de "assert enable" */
		if (p.isCompound()) throw new ExceptionFaltaMensaje();	
		this.primitiva = p;
	}
		
	public MensajeProtocolo(Primitive p, String m) throws ExceptionSobraMensaje{
		if (!p.isCompound()) throw new ExceptionSobraMensaje();	
		this.primitiva = p;
		this.mensaje = m;
	}
	
	public MensajeProtocolo(Primitive p, int size) throws ExceptionSobraMensaje{
		if (!p.isCompound()) throw new ExceptionSobraMensaje();	
		this.primitiva = p;
		this.size = size;
	}
		
	public Primitive getPrimitive() { return this.primitiva; }
	
	public String getMessage() throws ExceptionFaltaMensaje { 
		if (!this.primitiva.isCompound()) throw new ExceptionFaltaMensaje();
		return this.mensaje; 
	}
	
	public String toString() { /* prettyPrinter de la clase */
		String prettyPrint = "["+this.primitiva;
		
		switch (this.primitiva) {
			case HELLO: ;
			case PUSH: ;
			case PULL_OK:
				return "["+this.primitiva+":"+this.mensaje+"]" ;
			case SIZE_OK:
				return "["+this.primitiva+":"+this.size+"]" ;
			default :
				return "["+this.primitiva+"]" ;
		}
	}
}