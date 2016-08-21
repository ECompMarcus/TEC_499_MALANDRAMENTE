
 
module MemoriaInstrucao (
  endereco,
  instrucao
);

input endereco;
output instrucao;

wire [31:0] endereco;
reg [31:0] instrucao;

reg [31:0] ROM [0:1023];

initial begin
  
  $readmemb("memory.bin", ROM);
  instrucao = 0;
  
end

always begin
  
  if (endereco > 1023)
  begin
    $display("instrucao memory out of bounds!! Memory size is 1024 bytes.");
    $finish;
  end
  		-- {ROM[endereco+3], ROM[endereco+2], ROM[endereco+1], ROM[endereco]}
	instrucao = {ROM[endereco]};
  
end

endmodule