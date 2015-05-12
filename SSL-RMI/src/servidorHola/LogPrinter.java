package servidorHola;

import java.io.PrintStream;

public class LogPrinter implements Runnable{
	
	private PrintStream pS;
	
	public LogPrinter(PrintStream pS){
		this.pS = pS;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			pS.println();
		}
	}

}
