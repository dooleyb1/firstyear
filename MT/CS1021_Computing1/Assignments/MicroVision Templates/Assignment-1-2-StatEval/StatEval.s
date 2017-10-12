	AREA	StatEval, CODE, READONLY
	IMPORT	main
	IMPORT	getkey
	IMPORT	sendchar
	EXPORT	start
	PRESERVE8

start
	
	LDR R11, =0			; mean
    LDR R10, =10 		; powerOf10
	LDR R8, =0			; minNum
	LDR R9, =0			; maxNum
	LDR R7, =0			; sum
	LDR R5, =0			; count
	LDR R6, =0			; runningTotal

read
	BL	getkey			; read key from console
	CMP	R0, #0x0D  		; while (key != RETURN)
	BEQ	endRead			; {
	BL	sendchar		; echo key back to console
	CMP R0, #0x20		; if (key = SPACE)
	BEQ count		    ; branch to count
	
	MUL R6, R10, R6		; runningTotal * powerOf10
	SUB R0, R0, #0x30	; converting key to decimal
	ADD R4, R0, R6		; key + runningTotal
	
	CMP R4, R9			; if (R4 > maxNum)
	BLT checkmin		; {
	MOV R9, R4 			; set R4 to maxNum
checkmin			    ; }
	CMP R4, R8			; if (R4 < minNum)
	BGT notMinMax		; {		
	MOV R8, R4			; set R4 to minNum
notMinMax				; }

	ADD R7, R4, R7		; sum
	B	read			; }

count 
	ADD R5, R5, #0x1	; count++
	B read

endRead


stop	B	stop

	END	
