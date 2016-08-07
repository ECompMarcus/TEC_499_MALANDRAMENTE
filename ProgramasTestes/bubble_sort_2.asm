.module bubble_sort_2

.pseg

	main:
	    la  $t0, Array      		; Copia da base do Array para t0
	    addi $t0, $t0, 14    		; Copiando o tamanho maximo do array     
	outterLoop:             		; Usado para determinar quando � feita a itera��o no vetor
	    add $t1, $zero, $zero    	; $t1 guarda uma flag para determinar quando a lista foi ordenada
	    la  $a0, Array     			; modifica $a0 com a base do endere�o do vetor
	innerLoop:                  	; Loop interno que far� a intera��o no vetor e saber� se � necess�rio a troca
	    lw  $t2, 0($a0)         	; modifica $t2 para o elemento atual do vetor
	    lw  $t3, 1($a0)         	; modifica $t3 para o prox elemento do vetor
	    slt $t5, $t2, $t3       	; $t5 recebe o valor 1 caso $t2 < $t3
	    beq $t5, $zero, continue   	; se $t5 = 1, fa�a o swap
	    addi $t1, $zero, 1          ; flag para determinar que temos que checar novamente
	    sw  $t2, 1($a0)         	; guarda o maior valor na posi��o
	    sw  $t3, 0($a0)         	; guarada o menor valor na posi��o
	continue:
	    addi $a0, $a0, 1            ; avan�a para a proxima posi��o do vetor
	    bne  $a0, $t0, innerLoop    ; se $a0 != $t0, va para loop interno
	    bne  $t1, $zero, outterLoop    ; $t1 == 1, � necess�rio mais uma verifica��o , va para loop externo
.dseg

	Array:	.word   14
			.word   12
			.word   13
			.word   5
			.word   9
			.word   11
			.word   3
			.word   6
			.word   7
			.word   10
			.word   2
			.word   4
			.word   8
			.word   1
	
.end
