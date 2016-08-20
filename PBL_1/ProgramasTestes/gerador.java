.module calculo_primo
	
	.pseg
	
	addi $a0, $zero, 1
	addi $s0, $a0, 10 							;numero limite + 1 (para comparacao de menor ou igual)
	addi $t0, $zero, 1 							;contador
	
	slt $t1, $t0, s0 							 ; s0<=1?
	bqe $t1, $zero, Fim 						;se NÃO for menor fim do programa
	
	addi $a1, $zero, 2
	
		1     2  < 11
	slt $t1, $a1, $s0 							;o numero limite < 2?
		 1     1
	bne $t1, $a0, Verifica 							;se nao for, pula
		
	sw $a2, 0($sp)									;
			
	addi $sp, $zero, 1
	j Fim 										;fim do programa ele eh o unico primo
	
	Verifica: 
		slt $t1, $t0, s0 					 ; s0<=1?
		bqe $t1, $zero, Fim 						;se NÃO for menor fim do programa
		div $s2, $s0, $t0 							;divide o numero limite pelo contador (x/i)
		mfhi $a1 									;pega o resto
		beq	$a1, $zero, Conta 						;se o resto for zero eh primo
		j Verifica
	
	Conta: 	
		addi $t0, $t0, 1 					;add 1 no contador
		sw $a2, 0($sp)						;
		addi $sp, $zero, 1
		j Verifica 								;volta pra ver os outros numeros
	
	Fim: 	
			
.end
