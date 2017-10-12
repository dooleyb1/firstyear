	AREA	Shift64, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R0, =0x13131313			; leastSignificant32
	LDR	R1, =0x13131313			; mostSignificant32 
	LDR	R2, =-2					; shiftCount

	CMP R2, #0					;if(shiftCount < 0)
	BLT leftShift				;	leftShift();
	BGT rightShift				;else if(shiftCount > 0)
								;   rightShit()
	BEQ stop					
	
rightShift						;rightShift(){
	CMP R2, #0					;while(shiftCount > 0)
	BLE stop					;{
	MOV R0, R0, LSR #1			;  leastSignificant32 >> 1
	MOVS R1, R1, LSR #1			;  mostSignificant32 >> 1, check carry
	BCC skip1					;  if(carrySet){
	ADD R0, R0, #0x80000000		;    leastSignificant32 += #0x80000000
skip1							;  }
	SUB R2, R2, #1				;  shiftCount--
	B	rightShift				;}
	
leftShift						;rightShift(){
	CMP R2, #0					;while(shiftCount < 0)
	BGE stop					;{
	MOV R1, R1, LSL #1			;  mostSignificant32 >> 1
	MOVS R0, R0, LSL #1			;  leastSignificant32 >> 1, check carry
	BCC skip2					;  if(carrySet){
	ADD R1, R1, #0x00000001		;    mostSignificant32 += #0x80000000
skip2							;  }
	ADD R2, R2, #1				;  shiftCount++
	B	leftShift				;}
	
stop	B	stop


	END
		