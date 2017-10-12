	AREA	Divide, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR R2, =80	;test value for a
	LDR R3, =15	;test value for b
	LDR R0, =0	;set the quotient to 0
	MOV R1, R2	;set the tempValue to a
	
	CMP R3, #0	
	BEQ endwh	;if b == 0 don't enter the loop
	
meanWhile		
	CMP	R1, R3		;while(remainder >= b)
	BLS endwh		;{
	ADD R0, R0, #1	;quotient = quotient + 1
	SUB R1, R1, R3	;remainder = reamainder - b
	B meanWhile			;}
endwh

stop	B	stop

	END	