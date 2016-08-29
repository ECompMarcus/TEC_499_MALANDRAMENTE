// Universidade Estadual de Feira de Santana 
// TEC499 - MI - Sistemas Digitais
//
// Module: Reg_EX_MEM

module  Reg_EX_MEM( Next_Instruct_IN, operator2_IN, 
						 Result_ULA_Jump_IN,Result_ULA_Operator_IN,
						 Flag_ULA_IN, 
						 Next_Instruct_OUT, operator2_OUT, 
						 Result_ULA_Jump_OUT,Result_ULA_Operator_OUT,
						 Flag_ULA_OUT,
						 clock, clear, Write);
	
	input 	[31:0] Next_Instruct_IN, operator2_IN, 
						 Result_ULA_Jump_IN,Result_ULA_Operator_IN;
	
	output 	[31:0] Next_Instruct_OUT, operator2_OUT, 
						 Result_ULA_Jump_OUT,Result_ULA_Operator_OUT;
	
	reg 		[31:0] Next_Instruct_OUT, operator2_OUT, 
						 Result_ULA_Jump_OUT,Result_ULA_Operator_OUT;
	
	input 	Flag_ULA_IN;
	output  	Flag_ULA_OUT;
	reg 		Flag_ULA_OUT;
	
	
	input clock, clear, Write;

	initial
		begin		
		Next_Instruct_OUT			= 32'b0;
		operator2_OUT				= 32'b0;
		Result_ULA_Jump_OUT		= 32'b0;
		Result_ULA_Operator_OUT	= 32'b0;
		Flag_ULA_OUT				= 1'b0;
		end
		
	always @ (posedge clock)
		begin
		if (clear)
			begin
				Next_Instruct_OUT			= 32'b0;
				operator2_OUT				= 32'b0;
				Result_ULA_Jump_OUT		= 32'b0;
				Result_ULA_Operator_OUT	= 32'b0;
				Flag_ULA_OUT				= 1'b0;
			end 
		if (Write == 1 && clear == 0)
			begin				
				Next_Instruct_OUT			= Next_Instruct_IN;
				operator2_OUT				= operator2_IN;
				Result_ULA_Jump_OUT		= Result_ULA_Jump_IN;
				Result_ULA_Operator_OUT	= Result_ULA_Operator_IN;
				Flag_ULA_OUT				= Flag_ULA_IN;
			end				
		end
endmodule
