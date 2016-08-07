#	while(expo>1){
#		base = base*base;
#		expo--;
#	}


main:	
	addi $t0,$zero,1
	addi  $s2,,$zero, 3
	add $s0,$zero, $s2 # base
	beq $s0, $zero, Exit
	addi $s1, $zero, 3 # expoente
	add $t1,$zero,$zero
	beq $s1, $t1, Exit1
loop:
	mul $s0, $s0, $s2
	addi $s1, $s1, -1
	bne  $s1, $t0, loop
Exit:
Exit1:
	addi $t1,$zero,1
	sw 	$t1,0($sp)
	addi $sp,$sp,1