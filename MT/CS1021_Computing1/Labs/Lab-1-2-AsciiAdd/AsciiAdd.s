	AREA	AsciiAdd, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R1, ='2'	; R1 = 0x32 (ASCII symbol '2')
	LDR	R2, ='4'	; R2 = 0x34 (ASCII symbol '4')	
	
	LDR R3, ='0'    ; 0x30
	SUB R4, R1, R3	;turn the character 2 into number 2
	SUB R5, R2, R3	;turn the character 4 into number 4
	ADD R0, R4, R5	;add the numbers
	ADD R0, R0, R3	;turn the result back into character
	
stop	B	stop

	END	