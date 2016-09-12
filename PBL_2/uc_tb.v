module uc_tb;
  reg [5:0] opcode;
  wire RegWrite, Branch, ALUSrc, MemRead, MemWrite, Jump;
  wire [1:0] ALUOp, RegDst, MemtoReg;

UC control(opcode, RegDst, Branch, MemRead, MemtoReg, ALUOp, MemWrite, ALUSrc, RegWrite, Jump);
 parameter [5:0]
    RFORMAT = 6'd0,
    ADDI = 6'd8,
    ANDI = 6'd12,
    LW = 6'd35,
    SW = 6'd43,
    BEQ = 6'd4,
    BNE = 6'd5,
    J = 6'd3;

  initial begin
    # 100
    # 100 opcode = RFORMAT;
    # 100 opcode = ADDI;
    # 100 opcode = ANDI;
    # 100 opcode = LW;
    # 100 opcode = SW;
    # 100 opcode = BEQ;
    # 100 opcode = BNE;
    # 100 opcode = J;
  end
  endmodule