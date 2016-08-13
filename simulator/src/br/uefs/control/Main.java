package br.uefs.control;

import java.io.IOException;
import br.uefs.debuger.Debuger;
import br.uefs.model.*;

public class Main {

	public static void main(String[] args) {
		try {
			UC controlUnit = new UC();
			int y = controlUnit.decoder("00000010000100001000000000100000");
			System.err.println(y+" decoder");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String [] t = new String[2];
		t[0] ="1";
		t[1] = "0";
		int i =1; int k =3;
		i = i<<2;
		
		System.err.println(k&i);
//		for(int i=0; i<DB_REG.registerSize(); i++)
//		{
//			Debuger.sysPrinfT(DB_REG.getRegisters(i)+"\t"+DB_REG.getRegistersValue(i) );
//		}
	}

}
