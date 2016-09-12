module tb_memoria;
 
 reg control_w;
 reg control_r;
 reg [31:0] adrress;
 wire [31:0] saida;
 reg [31:0] data_write;
 

memoria memory(
.endereco(adrress),
.instrucao(saida),
.dado_escrita(data_write),
.uc_escrita_mem(control_w),
.uc_leitura_mem(control_r)
);

initial begin 
adrress = 0;
data_write = 32'd20;
end
always
begin
  
  #200 control_w = 1; control_r = 0;
  #200 control_w = 0; control_r = 1;
  #200 control_w = 1; control_r = 1;
  #200 control_w = 0; control_r = 0;
  adrress = adrress +1;
end


endmodule
