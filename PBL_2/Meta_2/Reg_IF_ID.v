// Universidade Estadual de Feira de Santana 
// TEC499 - MI - Sistemas Digitais
//
// Module: Reg_ID_IF

module  Reg_ID_IF( Next_Instruct_IN, Current_instruct_IN, 
						 Next_Instruct_OUT, Current_instruct_OUT, 
						 clock, clear, Read_Write);
	input [31:0] Next_Instruct_IN, Current_instruct_IN;
	output [31:0] Next_Instruct_OUT, Current_instruct_OUT;
	reg [31:0] temp1, temp2;
	reg  [31:0] Next_Instruct_OUT, Current_instruct_OUT;
	input clock, clear, Read_Write;

	initial
		begin
		Next_Instruct_OUT = 32'b0;
		Current_instruct_OUT = 32'b0;
		end
		
	always @ (posedge clock)
		begin
		if (clear)
			begin
				temp1 = 32'b0;
				temp2 = 32'b0; 
				Next_Instruct_OUT = 32'b0;
				Current_instruct_OUT = 32'b0;
			end 
		if (Read_Write ==0 && clear == 0)
			begin
				Next_Instruct_OUT = temp1;
				Current_instruct_OUT = temp2;
			end
		if (Read_Write ==1 && clear == 0)
			begin
				temp1 = Next_Instruct_IN;
				temp2 = Current_instruct_IN;
			end				
		end
endmodule
