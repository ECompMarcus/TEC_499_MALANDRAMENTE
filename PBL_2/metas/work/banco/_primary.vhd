library verilog;
use verilog.vl_types.all;
entity banco is
    port(
        clk             : in     vl_logic;
        rst             : in     vl_logic;
        uc_escrita      : in     vl_logic;
        dado_p_escrita  : in     vl_logic_vector(31 downto 0);
        endereco_escrita: in     vl_logic_vector(4 downto 0);
        endereco_leitura_1: in     vl_logic_vector(4 downto 0);
        endereco_leitura_2: in     vl_logic_vector(4 downto 0);
        dado_leitura_1  : out    vl_logic_vector(31 downto 0);
        dado_leitura_2  : out    vl_logic_vector(31 downto 0)
    );
end banco;
