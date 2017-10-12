	AREA	AsciiValue, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R4, ='2'	; Load '2','0','3','4' into R4...R1
	LDR	R3, ='0'	;
	LDR	R2, ='3'	;
	LDR	R1, ='4'	;
	
	LDR R5, ='0'
	SUB R6, R4, R5
	SUB R7, R3, R5
	SUB R8, R2, R5
	SUB R9, R1, R5
	LDR R10, =0xA
	MUL R6, R10, R6
	MUL R6, R10, R6
	MUL R6, R10, R6	;multiply the first number by 1000
	MUL R7, R10, R7
	MUL R7, R10, R7	;multiply the second number by 100
	MUL R8, R10, R8	;multiply the third number by 10
	ADD R6, R6, R7	;add the first and the second number
	ADD R8, R8, R9	;add the third and the fourth number
	ADD R0, R6, R8	;add the sums together
stop	B	stop

	END	