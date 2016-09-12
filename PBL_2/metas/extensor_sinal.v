module extensor_sinal
(
  entrada,	//16-bit entranda
	saida			//32-bit extende para 32
);

   input 	[15:0]	entrada;
   output 	[31:0] 	saida; 
  	
	 assign saida = {{16{entrada[15]}},entrada[15:0]};
	
 endmodule  

