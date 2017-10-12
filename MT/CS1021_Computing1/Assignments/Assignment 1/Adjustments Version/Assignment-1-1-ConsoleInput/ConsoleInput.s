	AREA	ConsoleInput, CODE, READONLY
	IMPORT	main
	IMPORT	getkey
	IMPORT	sendchar
	EXPORT	start
	PRESERVE8

start

	LDR R10, =10 		; powerOf10
	LDR R4, =0  		; runningTotal

read
	BL	getkey			; read key from console
	CMP	R0, #0x0D  		; while (key != CR)
	BEQ	endRead			; {
	BL	sendchar		;   echo key back to console

	MUL R4, R10, R4		; running total * powerOf10
	SUB R0, R0, #0x30	; converting key to decimal
	ADD R4, R0, R4		; key * runningTotal	

	B	read			; }
	
endRead

stop	B	stop

	END	