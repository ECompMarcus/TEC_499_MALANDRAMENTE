.module bubble_sort_2

.pseg

	main:
	    lw  $t0, 0($gp)      		; Copia da base do Array para t0
	    addi $t0, $t0, 4    		; Copiando o tamanho maximo do array     
	outterLoop:             		; Usado para determinar quando � feita a itera��o no vetor
	    add $t1, $zero, $zero    	; $t1 guarda uma flag para determinar quando a lista foi ordenada
	    lw  $a0, 0($gp)     			; modifica $a0 com a base do endere�o do vetor
	innerLoop:                  	; Loop interno que far� a intera��o no vetor e saber� se � necess�rio a troca
	    lw  $t2, 0($a0)         	; modifica $t2 para o elemento atual do vetor
	    lw  $t3, 1($a0)         	; modifica $t3 para o prox elemento do vetor
	    slt $t5, $t2, $t3       	; $t5 recebe o valor 1 caso $t2 < $t3
	    bne $t5, $zero, continue   	; se $t5 == 0, fa�a o swap
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
		
	
.end