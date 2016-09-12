library verilog;
use verilog.vl_types.all;
entity memoria is
    port(
        endereco        : in     vl_logic_vector(31 downto 0);
        instrucao       : out    vl_logic_vector(31 downto 0);
        dado_escrita    : in     vl_logic_vector(31 downto 0);
        uc_escrita_mem  : in     vl_logic;
        uc_leitura_mem  : in     vl_logic
    );
end memoria;
