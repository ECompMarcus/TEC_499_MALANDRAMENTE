package br.uefs.model;

public class MUX {
	
	private String dado_1; 
	private String dado_2;
	private String op;
	
	
	public MUX() {
		super();
	}


	public String getDado_1() {
		return dado_1;
	}
	public void setDado_1(String dado_1) {
		this.dado_1 = dado_1;
	}
	public String getDado_2() {
		return dado_2;
	}
	public void setDado_2(String dado_2) {
		this.dado_2 = dado_2;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	
	public String result(String OP)
	{
		if(OP.equals("1"))
			return dado_1;
		else
			return dado_2;
	}
	
	
	

}
