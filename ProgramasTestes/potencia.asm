.module potencia
.pseg
main:	
	li $t0,1
	li $s2, 3; defina base aqui
	move $s0, $s2; base
	beq $s0, $zero, exit; caso a base seja 0
	addi $s1, $zero, 3; defina o expoente aqui (troque o 3 pelo valor desejado)
loop:
	mul $s0, $s0, $s2; multiplica base*base
	addi $s1, $s1, -1; subtrai expoente
	bne  $s1, $t0, loop; enquanto não for igual
exit:
	syscall			# Exit!