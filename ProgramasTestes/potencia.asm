#	while(expo>1){
#		base = base*base;
#		expo--;
#	}


main:	
	li $t0,1
	li $s2, 3
	move $s0, $s2 # base
	beq $s0, 0, Exit
	addi $s1, $zero, 3 # expoente
loop:
	mul $s0, $s0, $s2
	addi $s1, $s1, -1
	bne  $s1, $t0, loop
Exit:
	syscall			# Exit!