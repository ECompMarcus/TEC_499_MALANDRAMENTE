module contador_programa
(
	clk,
	rst,
	pc,
	pc_control,
	jump_address,
	branch_offset,
	reg_address,
	pc_in
);

   //--------------------------
	// Input Ports
	//--------------------------
	// < Enter Input Ports  >
	input						clk;
	input						rst;
	input				[2:0]		pc_control;
	input				[25:0]	jump_address;
	input				[15:0]	branch_offset;
	input				[31:0] 	pc_in;
	input				[31:0] 	reg_address;
	
    //--------------------------
    // Output Ports
    //--------------------------
    // < Enter Output Ports  >	
    output 	reg	[31:0] 	pc;
		
	 wire	[31:0]	pc_plus_1;
	 //---------------------------------------------------------------
	// Logica Combinacional 
	//---------------------------------------------------------------
	 assign pc_plus_1 = pc;
	
	//---------------------------------------------------------------
	// Logica Sequencial 
	//---------------------------------------------------------------
    
	 always @(posedge clk or posedge rst)
	begin
		if (rst)
		begin
			pc <= 32'd0;
		end
		else
		begin
			case (pc_control)
				3'b000 : pc <= pc_plus_1;
				3'b001 : pc <= {pc_plus_1[31:28], jump_address, 2'b00};
				3'b010 : pc <= reg_address;
				3'b011 : pc <= pc_plus_1 + { {14{branch_offset[15]}}, branch_offset[15:0],  2'b00  };
				default: pc <= pc_plus_1;
			endcase
		end
	end
	
 endmodule  

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 