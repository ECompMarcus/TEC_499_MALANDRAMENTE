// Universidade Estadual de Feira de Santana 
// TEC499 - MI - Sistemas Digitais
//
// Module: Reg_ID_EX

module  Reg_ID_EX( Next_Instruct_IN, Operator1_IN, operator2_IN, 
						 Signal_Extended31_6_IN,Signal_Extended31_15_IN,
						 Function_ULA_IN, 
						 Next_Instruct_OUT, Operator1_OUT,operator2_OUT,  
						 Signal_Extended31_6_OUT, Signal_Extended31_15_OUT,
						 Function_ULA_OUT,
						 clock, clear, Write);
	
	input 	[31:0] Next_Instruct_IN, Operator1_IN, operator2_IN, 
						 Signal_Extended31_6_IN,Signal_Extended31_15_IN;
	
	output 	[31:0] Next_Instruct_OUT, Operator1_OUT,operator2_OUT,  
						 Signal_Extended31_6_OUT, Signal_Extended31_15_OUT;
	
	reg 		[31:0] Next_Instruct_OUT, Operator1_OUT,operator2_OUT,  
						 Signal_Extended31_6_OUT, Signal_Extended31_15_OUT;
	
	input 	[5:0]	 Function_ULA_IN;
	output  	[5:0]	 Function_ULA_OUT;
	reg 		[5:0]	 Function_ULA_OUT;
	
	
	input clock, clear, Write;

	initial
		begin
		Next_Instruct_OUT 			= 32'b0;
		Operator1_OUT 					= 32'b0;
		operator2_OUT 					= 32'b0;  
		Signal_Extended31_6_OUT 	= 32'b0;
		Signal_Extended31_15_OUT	= 32'b0;
		Function_ULA_OUT				= 5'b0;
		end
		
	always @ (posedge clock)
		begin
		if (clear)
			begin
				Next_Instruct_OUT 			= 32'b0;
				Operator1_OUT 					= 32'b0;
				operator2_OUT 					= 32'b0;  
				Signal_Extended31_6_OUT 	= 32'b0;
				Signal_Extended31_15_OUT	= 32'b0;
				Function_ULA_OUT				= 5'b0;
			end 
		if (Write ==1 && clear == 0)
			begin
				Next_Instruct_OUT 			= Next_Instruct_IN;
				Operator1_OUT 					= Operator1_IN;
				operator2_OUT 					= operator2_IN;  
				Signal_Extended31_6_OUT 	= Signal_Extended31_6_IN;
				Signal_Extended31_15_OUT	= Signal_Extended31_15_IN;
				Function_ULA_OUT				= Function_ULA_IN;
			end				
		end
endmodule
