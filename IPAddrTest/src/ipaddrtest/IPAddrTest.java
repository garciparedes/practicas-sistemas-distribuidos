package ipaddrtest;

import java.io.*;
import java.net.*;

public class IPAddrTest {

	public static void main (final String[] args){
		
		InetAddress ip;
		try {
			ip = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			ip = null;
		}
		System.out.println("IpAddress: " + ip.toString());
		String name = ip.getHostName();
		System.out.println("Host Nombre: " + name);
		name = ip.getCanonicalHostName();
		System.out.println("Host Nombre Completo: " +name );
	}
}
