// Universidade Estadual de Feira de Santana 
// TEC499 - MI - Sistemas Digitais
//
// Module: Mux_2x1

module  Mux_2x1( sel_0, sel_1, sel, mux_out );
	input [31:0] sel_0, sel_1;
	input sel;
	output [31:0] mux_out;
	reg  [31:0] mux_out;

	always @ (sel or sel_0 or sel_1)
		begin
			case(sel) 
			1'b0 : mux_out = sel_0;
			1'b1 : mux_out = sel_1;
			endcase 
		end
endmodule
