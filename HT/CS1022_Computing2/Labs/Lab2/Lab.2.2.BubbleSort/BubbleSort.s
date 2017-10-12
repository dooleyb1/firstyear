	AREA	BubbleSort, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R0, =array	; address of array
	LDR	R5, =arrayN	; address of array size
	LDR	R5, [R5]	; N = load(array size)

whileLoop									;do
											;{
	LDR R6, =0								; swapped = false
	LDR R2, =1								; for(i = 1;
forLoop
	CMP R2, R5								; i < N)
	BEQ endForLoop							; 	{		
	SUB R1, R2, #1							; 		j = i-1
	LDR R9, [R0, R2, LSL #2]				; 		load array[i] into R9
	LDR R10, [R0, R1, LSL #2]				; 		load array[j] into R10
	CMP R10, R9 							;	    if array[j] <= array[i] dont swap
	BLS dontSwap							;
	BL swapSubroutine						; 		else swap array[j] and array[i]
	LDR R6, =1								; 		boolean swapped = true
dontSwap									;
	ADD R2, R2, #1							; 		i++
	B forLoop								;	}
endForLoop								    ;}
	CMP R6, #1								; while(swapped)
	BEQ whileLoop
	B stop

	
stop	B	stop

	
;swapSub subroutine
;Swaps two elements at index i and j in a 1-dimensional array of word-size integers
;
;Parameters:
;R0 = arrayStartAddress
;R1 = i (element index)
;R2 = j	(element index)

;Returns;
;void

swapSubroutine
	STMFD SP!, {R6-R7, LR}
	LDR R6, [R0, R1, LSL #2]	;load(i, i index)
	LDR R7, [R0, R2, LSL #2]	;load(j, j index)
	STR R6, [R0, R2, LSL #2]			;store(i, j index)	
	STR R7, [R0, R1, LSL #2]			;store(j, i index)
	LDMFD SP!, {R6-R7, PC}
	
	AREA	TestArray, DATA, READWRITE

; Array Size
arrayN	DCD	15

; Array Elements
array	DCD	33,17,18,92,49,28,78,75,22,13,19,13,8,44,35

	END	