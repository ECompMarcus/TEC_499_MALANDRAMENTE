module frquency_divider_by2 ( clk ,clk3 );
output clk3 ;
reg clk2, clk3 ;
input clk ;
wire clk ;
initial clk2 = 0;
initial clk3 = 0;
 always @ (posedge (clk)) begin
   clk2 <= ~clk2;
 end
 always @ (posedge (clk2)) begin
  clk3 <= ~clk3;
 end
endmodule