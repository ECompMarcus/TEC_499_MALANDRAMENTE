////////////////////////////////////////////////////////
// RS-232 RX and TX module
// (c) fpga4fun.com & KNJN LLC - 2003 to 2016

// The RS-232 settings are fixed
// TX: 8-bit data, 2 stop, no-parity
// RX: 8-bit data, 1 stop, no-parity (the receiver can accept more stop bits of course)

//`define SIMULATION   // in this mode, TX outputs one bit per clock cycle
                       // and RX receives one bit per clock cycle (for fast simulations)

////////////////////////////////////////////////////////

////////////////////////////////////////////////////////

////////////////////////////////////////////////////////
// dummy module used to be able to raise an assertion in Verilog
module RS232(clk, rx, tx);

input clk;
input rx;
output tx;

 
wire clk3;

//testado com MAX II (EPM240T100C5)
//rodando a 57600 com divisor de frequencia
//rodando a  230400 sem divisor de frequencia
frquency_divider_by2 divide( .clk(clk) ,.clk3(clk3) );
//////////////////////////////////////////////////////



async_transmitter transmissor(
	.clk(clk3),
	.TxD_start(enviar),
	.TxD_data(data),
	.TxD(tx),
	.TxD_busy()
);

wire [7:0] data;
wire enviar;


async_receiver receptor(
	.clk(clk3),
	.RxD(rx),
	.RxD_data_ready(enviar),
	.RxD_data(data),  // data received, valid only (for one clock cycle) when RxD_data_ready is asserted

	// We also detect if a gap occurs in the received stream of characters
	// That can be useful if multiple characters are sent in burst
	//  so that multiple characters can be treated as a "packet"
	.RxD_idle(),  // asserted when no data has been received for a while
	.RxD_endofpacket()  // asserted for one clock cycle when a packet has been detected (i.e. RxD_idle is going high)
);
endmodule

////////////////////////////////////////////////////////
module BaudTickGen(
	input clk, enable,
	output tick  // generate a tick at the specified baud rate * oversampling
);
parameter ClkFrequency = 25000000;
parameter Baud = 115200;
parameter Oversampling = 1;

function integer log2(input integer v); begin log2=0; while(v>>log2) log2=log2+1; end endfunction
localparam AccWidth = log2(ClkFrequency/Baud)+8;  // +/- 2% max timing error over a byte
reg [AccWidth:0] Acc = 0;
localparam ShiftLimiter = log2(Baud*Oversampling >> (31-AccWidth));  // this makes sure Inc calculation doesn't overflow
localparam Inc = ((Baud*Oversampling << (AccWidth-ShiftLimiter))+(ClkFrequency>>(ShiftLimiter+1)))/(ClkFrequency>>ShiftLimiter);
always @(posedge clk) if(enable) Acc <= Acc[AccWidth-1:0] + Inc[AccWidth:0]; else Acc <= Inc[AccWidth:0];
assign tick = Acc[AccWidth];
endmodule


////////////////////////////////////////////////////////
