.module potencia

	main:	
		addi $t0,$zero,1
		addi  $s2,,$zero, 6 #; base
		add $s0,$zero, $s2 
		beq $s0, $zero, Exit
		addi $s1, $zero, 3 #; expoente
		add $t1,$zero,$zero
		beq $s1, $t1, Exit1
	loop:
		mul $s0, $s0, $s2
		addi $s1, $s1, -1
		bne  $s1, $t0, loop
		j end
	Exit:
		addi $s0,$zero,-1
		j end
	Exit1:
		addi $s0,$zero,1
	end:

.end