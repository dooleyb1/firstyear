	AREA	MatMul, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
0	LDR	R0, =matR
	LDR	R1, =matA
	LDR	R2, =matB
	LDR	R3, =4			; row_size
	LDR R4, =0			; i
	LDR R5, =0			; j
	LDR R6, =0			; r
	LDR R7, =0			; k
	LDR R8, =0			;  A[i,k]
	LDR R9, =0			;  B[k,j]
	LDR R10, =0			;  A[i,k] * B[k,j]
	B forLoop1
	
increaseFor1
	ADD R4, R4, #1
	B forLoop1
	
increaseFor2
	ADD R5, R5, #1
	B forLoop2

forLoop1	
	CMP r4, r3					; for(i = 0; i < N; i++)
	BEQ stop
	LDR R5, =0					; j=0

forLoop2
	CMP R5, r3					; for(j = 0; j < N; j++)
	BEQ increaseFor1
	LDR R6, =0					; r=0
	LDR R7, =0					; k=0

forLoop3				
	CMP R7, r3					; for(k = 0; k < N; k++)
	BEQ endLoop3

	LDR R11, =0
	MUL R11, R4, r3				; index = row * row_size
	ADD R11, R11, R7			; index = index + col		
	LDR R8, [R1, R11, LSL #2]	; A[i,k]

	LDR R11, =0					; 
	MUL R11, R7, r3				; index = row * row_size
	ADD R11, R11, R5			; index = index + column
	LDR R9, [R2, R11, LSL #2]	; B[k,j]

	MUL R10, R8, R9				; A[i,k] * B[k,j]
	ADD R6, R6, R10				; r = r + A[i,k] * B[k,j]
	ADD R7, R7, #1
	B forLoop3
	
endLoop3
	LDR R11, =0
	MUL R11, R4, R3
	ADD R11, R11, R5		
	STR R6, [R0, R11, LSL #2]	; Store r in R[i,j]
	B increaseFor2
	
stop	B	stop


	AREA	TestArray, DATA, READWRITE

N	EQU	4

matA	DCD	5,4,3,2
	DCD	3,4,3,4
	DCD	2,3,4,5
	DCD	4,3,4,3

matB	DCD	5,4,3,2
	DCD	3,4,3,4
	DCD	2,3,4,5
	DCD	4,3,4,3

matR	SPACE	64

	END	