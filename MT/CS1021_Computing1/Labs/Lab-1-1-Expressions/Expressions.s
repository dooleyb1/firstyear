	AREA	Expressions, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R1, =5	; x = 5
	LDR	R2, =6	; y = 6
				;solution 1:
				
	LDR R3, =3
	LDR R4, =5
	MUL R3, R1, R3	;3x
	MUL R3, R1, R3	;3x^2
	MUL R4, R1, R4	;5x
	ADD R0, R3, R4	;3x^2+5x
	
				;solution 2:
	LDR R3, =2
	LDR R4, =6
	LDR R5, =3
	MUL R3, R1, R3 	;2x
	MUL R3, R1, R3	;2x^2
	MUL R4, R1, R4	;6x
	MUL R4, R2, R4	;6xy
	MUL R5, R2, R5	;3y
	MUL R5, R2, R5	;3y^2
	ADD R0, R3, R4	;2x^2 + 6xy
	ADD R0, R0, R5	;2x^2 + 6xy + 3y^2
	
				;solution 3:
	LDR R3, =4
	LDR R4, =3
	LDR R5, =8
	MUL R6, R1, R1	;x^2
	MUL R6, R1, R6	;x^3
	MUL R3, R1, R3	;4x
	MUL R3, R1, R3	;4x^2
	MUL R4, R1, R4	;3x
	SUB R0, R6, R3	;x^3 - 4x^2
	ADD R0, R4, R0	;x^3 - 4x^2 + 3x
	ADD R0, R5, R0	;x^3 - 4x^2 + 3x + 8
stop	B	stop

	END	