.module calculo_raiz_exata
	
	.pseg

	addi $s0, $zero, 16 	;raiz
	addi $s2, $zero, 0 	;contador
	addi $t0, $zero, 0 	;resultado parcial

	
	Loop: 

	beq $s0, $zero, end
	addi $t7,$zero,2		;$t7 = 2
	mul $t0, $s2, $t7 	;$t0 = indice * $t7
	addi $t0, $t0, 1	; $t0 +=1
	sub $s0, $s0, $t0 	;raiz - indice
	
	addi $s2, $s2, 1 	;adiciona 1 ao contador
	
	j Loop
	
	end:
		add $s3, $zero,$s2
.end

