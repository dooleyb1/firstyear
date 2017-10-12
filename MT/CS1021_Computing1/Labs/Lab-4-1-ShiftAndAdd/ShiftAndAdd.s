	AREA	ShiftAndAdd, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR R0, =0				;result
	LDR	R1, =9				;value
	LDR	R2, =10				;multiplier
	LDR R3, =0x80000000 	;valueOfHighestPower
	LDR R4, = 31			;powerCount
	
while
	CMP R2, #0				;while(multiplier!=0)
	BEQ endWhile			;{
	
while2						;
	CMP R2, R3				;while(multiplier <= valueOfHighestPower)
	BHS endWh2				;{
	MOV R3, R3, LSR #1		; valueOfHighestPower >> #1
	SUB R4, R4, #1			; powerCount--
	B while2				;}

endWh2
	ADD R0, R0, R1, LSL R4  ;result = result + (value << #1)
	SUB R2, R2, R3			;multipler -= valueOfHighestPower
	B while

endWhile

stop	B	stop


	END	
	