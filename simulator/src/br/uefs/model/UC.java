package br.uefs.model;

import java.io.IOException;

import br.uefs.debuger.Debuger;

public class UC {
	
	private String nameFile = "teste.txt";
	private String outOperacao ="";
	ULA ula_primary; 	
	
	public UC() throws IOException {
		super();
		DB_REG.readerRegisters();
		IR.readerInstructions();
		ula_primary = new ULA();
		MEMORY.size = 1000;
		
		
		MEMORY.mountMemory(nameFile);
		
		DB_REG.setValeu(DB_REG.getRegisters("$gp"),MEMORY.instruNumber);
		Debuger.sysPrinfT("Local onde o GP: " + (MEMORY.instruNumber) );
		DB_REG.setValeu(DB_REG.getRegisters("$sp"),MEMORY.internalMemorySize);
		Debuger.sysPrinfT("Local onde o SP: " + (MEMORY.internalMemorySize) );
		DB_REG.setValeu(DB_REG.getRegisters("$fp"),MEMORY.instruNumber+1);
		Debuger.sysPrinfT("Local onde o FP: " + (MEMORY.instruNumber) );
	}
	public void loader() throws IOException
	{
		
	}
	public int decoder(String op)
	{
		
		// 		[opcode] [nome] [tipo] [function] [nº operandos]
		String aux = op.substring(0,6);
		Debuger.sysPrinfT(aux);
		String[] operation;
		// TODO FAZER PARA A MULTIPLICAÇÃO 
		if(aux.equals("000000"))
		{
			aux = op.substring(26,32);
			Debuger.sysPrinfT(aux);
			operation = IR.getInstrucao(aux,0);
			
			if( operation[4].equals("3")) 
			{
				
			}
			else if(operation[4].equals("2"))
			{
				
			}
			else
			{
				// TODO registradores 
			}
			
			
		}
		else if ( aux.equals("011100"))
		{
			aux = op.substring(26,32);
			Debuger.sysPrinfT(aux);
			operation = IR.getInstrucao(aux,0);
			
		}
		else
		{
			operation = IR.getInstrucao(aux,1);
			Debuger.sysPrinfT(aux);
		}
			
		if(operation == null)
			Debuger.sysPrinfT("alguma merda rolou");
		else
		switch (1)
		{
		case 1: 
			break;
			default:
				break;
		}
		return 0;
		
	}

}

