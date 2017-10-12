	AREA	Unique, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR R0, =2 			;occuranceFlag
	LDR R1, =COUNT		;count	
	LDR R2, =VALUES		; valAddress = values
	LDR R3, [R1]		; count = loadCount()
	LDR R4, [R2]		; currentBaseValue = loadValue(valAddress)
	
while
	CMP R3, #0 			; while (count != 0 && occuranceFlag != 0)
	BEQ endWh			; {
	CMP R0, #0			;
	BEQ endWh			;
	LDR R0, =2			; occuranceFlag = 2
	LDR R5, [R1]		; passThroughCount = loadCount()
	LDR R7, =VALUES		; passThroughValues = values
	
while2
	CMP R5, #0 			; while ( passThroughCount != 0 && occuranceFlag != 0 )
	BEQ endWh2			; {
	CMP R0, #0			; 
	BEQ endWh2			;
	LDR R6, [R7]		; currentInvestigatedVal = loadValue(passThroughValues)
	CMP R4, R6			; if ( currentInvestigatedVal = currentBaseValue )
	BNE endIf1			; 	{
	SUB R0, R0, #1		; 	occuranceFlag--
endIf1					;	 }
			
	ADD R7, R7, #4		; address++
	SUB R5, R5, #1		; passThroughCount--
	B while2			; }

endWh2
	SUB R3, R3, #1 		; count--
	ADD R2, R2, #4		; valAddress ++
	LDR R4, [R2]		; currentBaseValue = loadValue(valAddress)
	B while				; }
	
endWh
						; Result is the value of occuranceFlag which is =0 if values are not unique
						; and is =1 if the values are unique
stop	B	stop


	AREA	TestData, DATA, READWRITE

COUNT	DCD	10
VALUES	DCD	5, 2, 7, 4, 13, 99, 18, 8, 9, 12


	END
