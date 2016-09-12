library verilog;
use verilog.vl_types.all;
entity contador_programa is
    port(
        clk             : in     vl_logic;
        rst             : in     vl_logic;
        pc              : out    vl_logic_vector(31 downto 0);
        pc_control      : in     vl_logic_vector(2 downto 0);
        jump_address    : in     vl_logic_vector(25 downto 0);
        branch_offset   : in     vl_logic_vector(15 downto 0);
        reg_address     : in     vl_logic_vector(31 downto 0);
        pc_in           : in     vl_logic_vector(31 downto 0)
    );
end contador_programa;
