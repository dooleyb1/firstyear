	AREA	Val2Dec, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R1, =8393		;quotient = numToDivide
	LDR	R5, =decstr		;stringAddress
	LDR R6, =10			;divider = 10
	ADD SP, SP, #16
	
whLoop
	CMP R1, #0			;while(remainder > 0)
	BEQ stop			;{
	BL divideSub		;  divide(quotient, 10)
	BL val2DecSub		;  val2DecSub(remainder)
	B whLoop			;}

	
stop	B	stop

;divideSub subroutine
;Divides a number x by a divisor y, returning the quotient and the remainder
;
;Parameters: 
;R1 = number to be divided (x)
;R6 = divisor (y)
;
;Returns: 
;R0 = quotient
;R1 = remainder

divideSub
	CMP R6, #0	
	BEQ return			;if divider == 0 don't enter the loop
startDiv
	LDR R0, =0			;quotient = 0
meanWhile		
	CMP	R1, R6			;while(remainder >= divider)
	BLS return		;{
	ADD R0, R0, #1		;	quotient = quotient + 1
	SUB R1, R1, R6		;	remainder = reamainder - y
	B meanWhile			;}
return
	BX LR
	
;val2Dec subroutine
;Takes a value x and converts it to ASCII and stores it at memory address y
;
;Parameters: 
;R1 = value to be stored (x)
;R5 = memory address (y)
;
;Returns: 
;R1 = new remainder	

val2DecSub
	ADD R1, R1, #0x30	;	remainder += 0x30
	STRB R1, [R5, #-1]!	;	pushRemainder to stack
	MOV R1, R0			;	new remainder = quotient
	BX LR		;}

	
	AREA	TestString, DATA, READWRITE

decstr	SPACE	16

	END	