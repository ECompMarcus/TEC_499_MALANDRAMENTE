// Universidade Estadual de Feira de Santana 
// TEC499 - MI - Sistemas Digitais
// Lab 3, 2016.1
//
// Module: ALU.v
// Desc:   32-bit ALU for the MIPS150 Processor
// Inputs: 
// 	A: 32-bit value
// 	B: 32-bit value
// 	ALUop: Selects the ALU's operation 
// 						
// Outputs:
// 	ALU_Out: The chosen function mapped to A and B.

`include "Opcode.vh"
`include "ALUop.vh"

module ALU( operand1,operand2, ALUop, ALU_Out, overFlow, compare);

input [31:0] operand1, operand2;
input [4:0] ALUop;
output reg [31:0] ALU_Out;
output reg compare, overFlow;
reg [31:0]result;

always@(*) begin
	case(ALUop)
		
		`ALU_ADD: 	ALU_Out = $signed(operand1) + $signed(operand2);                          
		`ALU_ADDU: 	ALU_Out = operand1 + operand2;
		`ALU_SUB: 	ALU_Out = operand1 - operand2;
		`ALU_SUBU: 	ALU_Out = operand1 + operand2;
		`ALU_AND:	ALU_Out = operand1 & operand2;
		`ALU_OR:   	ALU_Out = operand1 | operand2;
		`ALU_NOR: 	ALU_Out = ~operand1 & ~operand2; //~(A | B) DÃƒÅ¡VIDA!
		`ALU_XOR:	ALU_Out = operand1 ^ operand2;
		`ALU_SLT: 	//SLTI
					begin
						ALU_Out = 32'b0;
						compare = ($signed(operand1) < $signed(operand2))?1'b1:1'b0;
					end
		`ALU_SLTU: //SLTIU
					begin
						ALU_Out = 32'b0;
						compare = operand1<operand2?1'b1:1'b0;
					end
		`ALU_SLL:	ALU_Out = operand2 << operand1;
		`ALU_SRL:	ALU_Out = operand2 >> operand1;
		`ALU_SRA:	ALU_Out = $signed(operand2) >>> $signed(operand1);
		//`ALU_SLA:	ALU_Out = $signed(operand2) << $signed(operand1);
		`ALU_LUI: 	ALU_Out = {operand2[15:0], 16'b0};
		`ALU_BEQ: 
					begin
						ALU_Out = 32'b0;
						compare = (operand1 - operand2)==0?1'b1:1'b0;
					end
		`ALU_BNE: 
					begin
						ALU_Out = 32'b0;
						compare = (operand1 - operand2)==0?1'b0:1'b1;
					end
		
		`ALU_BLEZ: 
					begin
						ALU_Out = 32'b0;
						compare = operand1 <= 0?1'b1:1'b0;
					end
		`ALU_BGTZ: 
					begin
						ALU_Out = 32'b0;
						compare = operand1 > 0?1'b1:1'b0;
					end
		`ALU_BLTZ: 
					begin
						ALU_Out = 32'b0;
						compare = operand1 < 0?1'b1:1'b0;
					end
		`ALU_BGEZ: 
					begin
						ALU_Out = 32'b0;
						compare = operand1 >= 0?1'b1:1'b0;
					end			
		`ALU_CLZ: 
					begin
						integer i;
						result = 32'b0;
						for (i=0 ;i<32; i = i+1)
							begin 
								if (operand1[i] ^ 1 )//NUMEROS DE ZEROS
								result = result + 32'b1;																						
							end
						ALU_Out <= result; 						
					end			
		`ALU_CLO: 
					begin
						integer i;
						result = 32'b0;
						for (i=0 ;i<32; i = i+1)
							begin 
								if (operand1[i] & 1 )//NUMEROS DE UNS
								result = result + 32'b1;																						
							end
						ALU_Out <= result; 						
					end			
		default:  
		begin
		ALU_Out = 32'b0;		
		end
	endcase
end
endmodule
