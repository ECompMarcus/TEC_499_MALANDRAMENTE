library verilog;
use verilog.vl_types.all;
entity extensor_sinal is
    port(
        entrada         : in     vl_logic_vector(15 downto 0);
        saida           : out    vl_logic_vector(31 downto 0)
    );
end extensor_sinal;
