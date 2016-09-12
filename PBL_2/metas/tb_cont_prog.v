module tb_cont_prog;
  
  //--------------------------
	// Input Ports
	//--------------------------
	// < Enter Input Ports  >
	reg						clk;
	reg						rst;
	reg				[2:0]		pc_control;
	reg				[25:0]	jump_address;
	reg				[15:0]	branch_offset;
	reg				[31:0] 	reg_address;
	reg				[31:0] 	pc_in = 0;

	contador_programa pc(
	.clk(clk),
	.rst(rst),
	.pc(open),
	.pc_control(pc_control),
	.jump_address(jump_address),
	.branch_offset(branch_offset),
	.reg_address(reg_address),
	.pc_in(pc_in)
	);
	
	//Initial input values
	initial begin
		clk = 0;
		rst = 0;
		pc_control = 3'b000;
		pc_in =32'b0;
	end

	//Every 100ps, change the clk and modify data_in
	always 
	begin
		#100 clk = !clk;
		pc_in = pc_in +1;
	end
endmodule
