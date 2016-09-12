module banco(
	clk,
	rst,
	uc_escrita,
	dado_p_escrita,
	endereco_escrita,
	endereco_leitura_1,
	endereco_leitura_2,
	dado_leitura_1,
	dado_leitura_2
);

input uc_escrita;
input dado_p_escrita;
input endereco_escrita;
input clk;
input rst;
input endereco_leitura_1;
input endereco_leitura_2;
output [31:0]dado_leitura_1;
output [31:0]dado_leitura_2;

wire uc_escrita;
wire [31:0] dado_p_escrita;
wire [4:0] endereco_escrita;
wire [4:0] endereco_leitura_1;
wire [4:0] endereco_leitura_2;


reg [31:0] registradores [0:31];
integer i;
initial begin
//LIMPANDO OS REGS
  for (i = 0; i <= 31; i = i + 1)
  begin
    registradores[i] = 0;
  end
end
	
	assign dado_leitura_1 = registradores[endereco_leitura_1];
	assign dado_leitura_2 = registradores[endereco_leitura_2];
	
always @(posedge clk)
begin 
	// sinal da UC   unico endereço que não pode escrever é do GRP -> $zero
  if (uc_escrita == 1 && endereco_escrita != 0) 
  begin
	   registradores[endereco_escrita] <= dado_p_escrita;
  end
	
end

endmodule