// Universidade Estadual de Feira de Santana 
// TEC499 - MI - Sistemas Digitais
//
// Module: Reg_MEM_WR

module  Reg_MEM_WR( Result_ULA_Operator_IN, Data_Memory_IN, 
						 Result_ULA_Operator_OUT, Data_Memory_OUT,
						 clock, clear, Write);
	
	input 	[31:0] Result_ULA_Operator_IN, Data_Memory_IN;
	
	output 	[31:0] Result_ULA_Operator_OUT, Data_Memory_OUT;
	reg 		[31:0] Result_ULA_Operator_OUT, Data_Memory_OUT;
	
	input clock, clear, Write;

	initial
		begin		
		Data_Memory_OUT			= 32'b0;
		Result_ULA_Operator_OUT	= 32'b0;
		end
		
	always @ (posedge clock)
		begin
		if (clear)
			begin
				Data_Memory_OUT			= 32'b0;
				Result_ULA_Operator_OUT	= 32'b0;
			end 
		if (Write == 1 && clear == 0)
			begin				
				Data_Memory_OUT			= Data_Memory_IN;
				Result_ULA_Operator_OUT	= Result_ULA_Operator_IN;
			end				
		end
endmodule
