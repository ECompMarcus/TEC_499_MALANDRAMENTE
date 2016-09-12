module UC(
			opcode,
			RegDst,
			Branch,
			MemRead,
			MemtoReg,
			ALUOp,
			MemWrite,
			ALUSrc,
			RegWrite,
			Jump
			);
  input [5:0] opcode;
  output wire RegWrite, Branch, ALUSrc, MemRead, MemWrite, Jump;
  output wire [1:0] ALUOp, RegDst, MemtoReg;
  
  parameter [5:0]
    RFORMAT = 6'b000000,
    ADDI = 6'b001000,
    ANDI = 6'b001100,
    LW = 6'b100011,
    SW = 6'b101011,
    BEQ = 6'b000100,
	 BNE = 6'b000101,
    J = 6'b000010;

  wire LOAD = (opcode == LW);
  wire STORE = (opcode == SW);

  assign #10 Jump = (opcode == J);
  assign #10 Branch = (opcode == BEQ || opcode == BNE );
  assign #10 MemRead = LOAD;
  assign #10 MemWrite = STORE;
  assign #10 ALUSrc = (opcode == ADDI || opcode == ANDI || LOAD || STORE);
  assign #10 RegDst[1] = !(opcode == RFORMAT);
  assign #10 RegDst[0] = (opcode == RFORMAT);
  assign #10 MemtoReg[1] = (opcode == J);
  assign #10 MemtoReg[0] = LOAD;
  assign #10 RegWrite = (opcode == RFORMAT || opcode == ADDI || opcode == ANDI || LOAD || opcode == J);
  assign #10 ALUOp[1] = (opcode == RFORMAT || opcode == ANDI);
  assign #10 ALUOp[0] = (opcode == ANDI || opcode == BEQ || opcode == BNE);

endmodule
