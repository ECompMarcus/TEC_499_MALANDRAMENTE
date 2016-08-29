entity testBench_adder_pc is end_;

library IEEE;

use IEEE.STD_LOGIC_1164.all;
use std.textio.all;

architecture BENCH of testBench_adder_pc is 

component adder_pc
	port(operando_1 : in std_logic;
		operando_2 : in std_logic;
		resultado : out std_logic);
endcomponent;

signal i_1 : std_logic;
signal i_2 : std_logic;


begin
	adder_pc1: adder_pc port map(operando_1=>i_1,operando_2=>i_2, res => open);
	
	estimulo: process
	begin 
		wait for 5ns; i_1 <='0'; i_2 <= '0';
		

endmodule
	