// Universidade Estadual de Feira de Santana 
// TEC499 - MI - Sistemas Digitais
//
// Module: Reg_IF_ID

module  Reg_IF_ID( Next_Instruct_IN, Current_instruct_IN, 
						 Next_Instruct_OUT, Current_instruct_OUT, 
						 clock, clear, Write);
	input 	[31:0] Next_Instruct_IN, Current_instruct_IN;
	output 	[31:0] Next_Instruct_OUT, Current_instruct_OUT;
	
	reg  		[31:0] Next_Instruct_OUT, Current_instruct_OUT;
	input clock, clear, Write;

	initial
		begin
		Next_Instruct_OUT = 32'b0;
		Current_instruct_OUT = 32'b0;
		end
		
	always @ (posedge clock)
		begin
		if (clear)
			begin				
				Next_Instruct_OUT = 32'b0;
				Current_instruct_OUT = 32'b0;
			end 
		if (Write ==1 && clear == 0)
			begin
				Next_Instruct_OUT = Next_Instruct_IN;
				Current_instruct_OUT = Current_instruct_IN;
			end				
		end
endmodule
