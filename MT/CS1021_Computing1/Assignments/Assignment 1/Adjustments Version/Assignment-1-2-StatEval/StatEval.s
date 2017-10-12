	AREA	StatEval, CODE, READONLY
	IMPORT	main
	IMPORT	getkey
	IMPORT	sendchar
	EXPORT	start
	PRESERVE8

start
	
	LDR R12, =0			; dividend
	LDR R11, =0			; mean
    LDR R10, =1 		; powerOf10
	LDR R9, =0			; maxNum
	LDR R8, =0xFFFFFFF2	; minNum
	LDR R7, =0			; sumOfNumbers
	LDR R6, =10			; number10
	LDR R5, =0			; count
	LDR R4, =0			; runningTotal --> range
	

positiveRead
	BL	getkey			; read key from console
	CMP	R0, #0x0D  		; while (key != RETURN)
	BEQ	positiveInput	; {
	CMP R0, #0x20		; && while (key != SPACE)
	BEQ positiveInput	; 
	BL	sendchar		; echo key back to console

	MUL R4, R6, R4		; runningTotal * 10
	SUB R0, R0, #0x30	; converting key to decimal
	ADD R4, R0, R4		; key + runningTotal
	B	positiveRead	; }
		

positiveInput
	CMP R0, #0x0D
	BNE posContinue
	CMP R4, #0x00
	BNE posContinue
	
						; // Prints error message to console 
						; // if there is no input from user
	
	LDR R0, ='E'
	BL sendchar
	LDR R0, ='r'
	BL sendchar
	LDR R0, ='r'
	BL sendchar
	LDR R0, ='o'
	BL sendchar
	LDR R0, ='r'
	BL sendchar
	LDR R0, =','
	BL sendchar
	LDR R0, =' '
	BL sendchar
	LDR R0, ='I'
	BL sendchar
	LDR R0, ='n'
	BL sendchar
	LDR R0, ='v'
	BL sendchar
	LDR R0, ='a'
	BL sendchar
	LDR R0, ='l'
	BL sendchar
	LDR R0, ='i'
	BL sendchar
	LDR R0, ='d'
	BL sendchar
	LDR R0, =' '
	BL sendchar
	LDR R0, ='I'
	BL sendchar
	LDR R0, ='n'
	BL sendchar
	LDR R0, ='p'
	BL sendchar
	LDR R0, ='u'
	BL sendchar
	LDR R0, ='t'
	BL sendchar
	LDR R0, ='.'
	BL sendchar
	B endRead
	
posContinue
	BL	sendchar		; echo SPACE/ENTER key back to console
	ADD R5, R5, #0x1	; count++
	ADD R7, R4, R7		; sum = sum + runningTotal

minMaxCheck
	CMP R4, R9			; if (R4 >= maxNum)
	BLT checkmin		; {
	MOV R9, R4 			; 	set maxNum to R4
	
checkmin			    
	CMP R4, R8			; else if (R4 <= minNum)
	BHI endMinMax		; {		
	MOV R8, R4			; 	set minNum to R4
						; }
endMinMax	
	LDR R4, =0;			; reset R4 =0
	CMP	R0, #0x0D  		; if (key = RETURN)
	BEQ	meanCompute		; { meanCompute }
						; else
	B 	positiveRead	; (read again)
	
meanCompute	
	MOV R12, R7			; dividend == sumOfNumbers
	CMP R5, #0			; if ( count != 0)
	BEQ rangeCalc		 ; {
	
meanWhile
	CMP	R12, R5			; 	while(dividend >= count)
	BLT rangeCalc		; 	{
	ADD R11, R11, #1	; 		mean = mean + 1
	SUB R12, R12, R5	; 		dividend = dividend - count (divisor)
	B meanWhile			;	}

rangeCalc
	SUB R4, R9, R8		;	Range = Max - Min				
						; }
endRead					; 	endRead
						; }
stop	B	stop

	END	
stop	B	stop

	END	
