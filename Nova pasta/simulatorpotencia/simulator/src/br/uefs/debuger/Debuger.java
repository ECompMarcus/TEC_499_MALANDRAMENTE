package br.uefs.debuger;

public class Debuger {
	
	public static boolean enable=true;
	public static void sysPrinfT(String x){
		if(enable)
		System.err.println(x);
	}
}
