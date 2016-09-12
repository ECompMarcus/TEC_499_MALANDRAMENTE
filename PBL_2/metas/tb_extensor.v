module tb_extensor;
  
  reg [15:0] in = 15;
  wire [31:0] out;
  extensor_sinal signal_ext(
  .entrada(in),
  .saida(out)
  );
  
  initial begin
    in = 16'b1111111111111110;
  
 
  #100 in = 14;
   #100 in = 24;
   #100 in = 11;
end
  
endmodule
