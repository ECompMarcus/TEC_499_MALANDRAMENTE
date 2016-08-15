package br.uefs.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import br.uefs.debuger.Debuger;


public class IR {
	
	private static String[] instrucoes_list = new String[64];
	
	public static void readerInstructions() throws IOException {
		FileInputStream stream = null;
		stream = new FileInputStream("mnemonico.txt");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha = br.readLine();
		
		String type;
		int i=0;
		while (linha != null)
		{
			String[] tokens = linha.trim().split("[\\t - \\s]");
			type = tokens[2];
			
			if (type.equals("R"))
				instrucoes_list[i] = (tokens[0]+","+tokens[1]+","+0+","+tokens[3]+","+tokens[4]);
			else if (type.equals("I"))
				instrucoes_list[i] = (tokens[0]+","+tokens[1]+","+1+","+tokens[3]+","+tokens[4]);
				
			else if (type.equals("J"))
				instrucoes_list[i] = (tokens[0]+","+tokens[1]+","+2+","+tokens[3]+","+tokens[4]);
				
			Debuger.sysPrinfT(instrucoes_list[i]);
			i++;
			linha = br.readLine();
		}
	}

	public static String[] getInstrucao(String aux, int tipo) {
		Debuger.sysPrinfT(aux);
		if(tipo == 0)
		{
			for(int l=0; l<instrucoes_list.length; l++)
			{
				String [] tok = instrucoes_list[l].split(",");
				Debuger.sysPrinfT(tok[3]);
				if(aux.equals(tok[3])) 
				{
					
					return instrucoes_list[l].split(",");
				}
			}
		}
		else
		{
			for(int l=0; l<instrucoes_list.length; l++)
			{
				if(aux.equals(((String [] ) instrucoes_list[l].split(","))[0])) 
				{
					return instrucoes_list[l].split(",");
				}
			}
		}
		return null;
	}
}
