	AREA	MotionBlur, CODE, READONLY
	IMPORT	main
	IMPORT	getPicAddr
	IMPORT	putPic
	IMPORT	getPicWidth
	IMPORT	getPicHeight
	EXPORT	start
	PRESERVE8 {TRUE}

start

		BL	getPicAddr	; load the start address of the image in R4
		MOV	R4, R0
		BL	getPicHeight	; load the height of the image (rows) in R5
		MOV	R5, R0
		BL	getPicWidth	; load the width of the image (columns) in R6
		MOV	R6, R0

		LDR R3, =5 		; blurLength
		MOV R0, R4		; imageStartAddress
		BL getEndAddress
		
		LDR R1, =-1		; for(j=0;
forOne	ADD R1, R1, #1	; 
		CMP R1, R5		;	  j<image.Height; j++)
		BGE endLoop		; {
		LDR R2, =0		; 	for(i=0;
forTwo	CMP R2, R6		;	    i<image.Width; i++)
		BGE forOne		;   {
		LDR R8, =0		; 		redTotal = 0
		LDR R9, =0		;		greenTotal = 0
		LDR R10, =0		;		blueTotal = 0
		BL addUpwards	;	   	addUpwards(i,j)
		BL addDownwards	;		addDownwards(i,j)
		BL addCurrent	;		addCurrent(i,j)
		BL getAvg		;       getAvg()
		BL storeAvg		;		storeAvg()
		ADD R4, R4, #4	;		pixelAddress++
		ADD R2, R2, #1	;		i++
		B forTwo		;	}
endLoop					;}
		BL overwriteImage
		BL	putPic		; re-display the updated image

stop	B	stop

;overwriteImage subroutine
;
;Takes the image copy and overwrites the original image
;
;Parameters:
;
;
;Returns;
;
overwriteImage
	STMFD SP!, {R0-R7, LR}
	BL getPicAddr
	MOV R4, R0
	BL getEndAddress
	MUL R1, R5, R6			;total = rows*columns
	LDR R2, =0				;i = 0
loopH
	CMP R2, R1				;while(i<total)
	BGE endLoopH			;{
	LDR R3, [R12]			;	a = imageCopyPixelValue
	STR R3, [R4]			;	store(a)
	ADD R4, R4, #4			;	imageAddress++
	ADD R12, R12, #4		; 	imageCopyAddress++
	ADD R2, R2, #1			; 	i++
	B loopH
endLoopH
	LDMFD SP!, {R0-R7, PC}

;addUpwards subroutine
;
;Adds the Red, Green and Blue components of the pixels diagonally above the pixel at (i,j) and stores it to a running total
;
;Parameters:
;R1= j (rows)
;R2= i (columns)
;R3= blurRadius
;R4= pixelAddress
;R5= height (rows)
;R6= width (columns)
;
;Returns;
;R0 = total

addUpwards
	STMFD SP!, {R1-R7, LR}
	LSR R3, R3, #1	            ; blurRadius = blurLength / 2
	LSL R7, R6, #2				; rowLength = width * 4
	MUL R5, R7, R3				; rowAdjustment = rowLength * blurRadius
	LSL R6, R3, #2				; columnAdjustment = blurRadius * 4
	SUB R1, R1, R3				; i-= blurRadius
	SUB R2, R2, R3				; j-= blurRadius
	SUB R4, R4, R5				; pixelAddress -= columnAdjustment
	SUB R4, R4, R6				; pixelAddress -= rowAdjustment
	
wh1
	CMP R1, #0					;while(i<0
	BLT fixIndex				;		||
	CMP R2, #0					;   		 j<0)
	BGE endWh1					;{
fixIndex
	ADD R1, R1, #1				;	i++
	ADD R2, R2, #1				;   j++
	ADD R4, R4, R7				; 	pixelAddress += rowLength
	ADD R4, R4, #4				;   pixelAddress ++
	SUB R3, R3, #1				;   blurRadius--
	B wh1						;}
endWh1

wh2
	CMP R3, #0					;while(blurRadius!=0)
	BEQ endWh2					;{
	BL addRed					;	addRed()
	BL addGreen					;   addGreen()
	BL addBlue					; 	addBlue()
	ADD R1, R1, #1				;   i++
	ADD R2, R2, #1				;   j++
	SUB R3, R3, #1				;   blurRadius--
	SUB R4, R4, R7				; 	pixelAddress += rowLength
	SUB R4, R4, #4				;   pixelAddress ++
	B wh2
endWh2
	LDMFD SP!, {R1-R7, PC}
	
;addDownwards subroutine
;
;Adds the Red, Green and Blue components of the pixels diagonally below the pixel at (i,j) and stores it to running totals
;
;Parameters:
;R1= j (rows)
;R2= i (columns)
;R3= blurRadius
;R4= pixelAddress
;R5= height (rows)
;R6= width (columns)
;
;Returns;
;R0 = total

addDownwards
	STMFD SP!, {R0-R7, R11-R12, LR}
	MOV R11, R5
	MOV R12, R6
	LSR R3, R3, #1				; blurRadius = blurLength / 2
	LSL R7, R6, #2				; rowLength = width * 4
	MUL R5, R7, R3				; rowAdjustment = rowLength * blurRadius
	LSL R6, R3, #2				; columnAdjustment = blurRadius * 4
	ADD R1, R1, R3				; i+= blurRadius
	ADD R2, R2, R3				; j+= blurRadius
	ADD R4, R4, R5				; pixelAddress += columnAdjustment
	ADD R4, R4, R6				; pixelAddress += rowAdjustment

wh11
	CMP R3, #0					;while(blurRadius!=0)
	BEQ endWh22					;{
	CMP R1, R12					;while(i>end
	BGT fixIndex11				;		||
	CMP R2, R11					;   		 j>end)
	BLS endWh11					;{
fixIndex11
	SUB R1, R1, #1				;	i++
	SUB R2, R2, #1				;   j++
	SUB R4, R4, R7				; 	pixelAddress += rowLength
	SUB R4, R4, #4				;   pixelAddress ++
	SUB R3, R3, #1				;   blurRadius--
	B wh11						;}
endWh11

wh22
	CMP R3, #0					;while(blurRadius!=0)
	BEQ endWh22					;{
	BL addRed					;	addRed()
	BL addGreen					;   addGreen()
	BL addBlue					; 	addBlue()
	SUB R1, R1, #1				;   i--
	SUB R2, R2, #1				;   j--
	SUB R3, R3, #1				;   blurRadius--
	ADD R4, R4, R7				; 	pixelAddress -= rowLength
	ADD R4, R4, #4				;   pixelAddress --
	B wh22
endWh22
	LDMFD SP!, {R0-R7, R11-R12, PC}
		
;addCurrent subroutine
;
;Adds the current RGB components of a pixel at pixelAddress to the running totals
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R8= redTotal
;R9= greenTotal
;R10= blueTotal
addCurrent
	STMFD SP!, {R0-R7, LR}
	BL addRed
	BL addGreen
	BL addBlue
	LDMFD SP!, {R0-R7, PC}

;gevAvg subroutine
;
;Calculates the average of each RGB combined total
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R8= redAverage
;R9= greenAverage
;R10= blueAverage
getAvg
	STMFD SP!, {R0-R7, LR}
	BL avgRed
	BL avgGreen
	BL avgBlue
	LDMFD SP!, {R0-R7, PC}
	
;getEndAddress subroutine
;
;
;Calculates the end address of the image
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R12 = endAddress
getEndAddress
	STMFD SP!, {R0-R7, LR}
	BL getPicAddr
	MOV R4, R0
	MUL R0, R5, R6			;result = rows*columns
	LSL R0, R0, #2			;result *= 4
	ADD R12, R0, R4			;startAddress += result
	LDMFD SP!, {R0-R7, PC}


;avgRed subroutine
;
;Calculates the average of the Red components total stored in R8
;
;Parameters:
;R8= redTotal
;
;Returns;
;R8= redAverage
avgRed
	STMFD SP!, {R0-R7, LR}
	MOV R1, R8				;R1 = redTotal
	MOV R2, R3				;R2 = blurLength
	BL divide				;R1/R2
	MOV R8, R0				;R8 = average
	LDMFD SP!, {R0-R7, PC}

;avgGreen subroutine
;
;Calculates the average of the Green components total stored in R8
;
;Parameters:
;R8= greenTotal
;
;Returns;
;R8= greenAverage
avgGreen
	STMFD SP!, {R0-R7, LR}
	MOV R1, R9				;R1 = greenTotal
	MOV R2, R3				;R2 = blurLength
	BL divide				;R1/R2
	MOV R9, R0				;R8 = average
	LDMFD SP!, {R0-R7, PC}

;avgBlue subroutine
;
;Calculates the average of the Blue components total stored in R8
;
;Parameters:
;R8= blueTotal
;
;Returns;
;R8= blueAverage
avgBlue
	STMFD SP!, {R0-R7, LR}
	MOV R1, R10				;R1 = blueTotal
	MOV R2, R3				;R2 = blurLength
	BL divide				;R1/R2
	MOV R10, R0				;R8 = average
	LDMFD SP!, {R0-R7, PC}
	
	
;storeAvg subroutine
;
;Stores the combined RGB Avg for a given pixel
;
;Parameters:
;R8= redTotal
;R9= greenTotal
;R10= blueTotal
;
;Returns;
;R8= blueAverage
storeAvg
	STMFD SP!, {R0-R7, LR}
	LDR R0, =0x00000000
	LSL R8, R8, #16				;shiftRed << 16
	LSL R9, R9, #8				;shiftGreen << 8
	ORR R0, R8, R0				;setRed
	ORR R0, R9, R0				;setGreen
	ORR R0, R10, R0				;setBlue
	STR R0, [R12], #4			;storeAvg
	LDMFD SP!, {R0-R7, PC}	
	
;divide subroutine
;Divides a number x by a divisor y, returning the quotient and the remainder
;
;Parameters: 
;R1 = number to be divided (x)
;R6 = divisor (y)
;
;Returns: 
;R0 = quotient
;R1 = remainder

divide
	CMP R2, #0	
	BEQ return			;if divider == 0 don't enter the loop
startDiv
	LDR R0, =0			;quotient = 0
meanWhile		
	CMP	R1, R2			;while(remainder >= divider)
	BLS return		;{
	ADD R0, R0, #1		;	quotient = quotient + 1
	SUB R1, R1, R2		;	remainder = reamainder - y
	B meanWhile			;}
return
	BX LR

;addRed subroutine
;
;Adds the Red component of a pixel at pixelAddress to the running total
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R8= redTotal
addRed
	STMFD SP!, {R0-R7, LR}
	LDR R1, =0x00FF0000		;R1 = redMask
	LDR R3, [R4]			;pixel = loadPixelValue(pixelAddress)
	AND R2, R3, R1			;redComp = getRedComponent(pixel)
	LSR R2, R2, #16			;shiftRight(redComp, 24)
	ADD R8, R8, R2			;redTotal += redComp
	LDMFD SP!, {R0-R7, PC}

;addGreen subroutine
;
;Adds the Green component of a pixel at pixelAddress to the running total
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R9= greenTotal
addGreen
	STMFD SP!, {R0-R7, LR}
	LDR R1, =0x0000FF00		;R1 = greenMask
	LDR R0, [R4]			;pixel = loadPixelValue(pixelAddress)
	AND R2, R0, R1			;greenComp = getGreenComponent(pixel)
	LSR R2, R2, #8			;shiftRight(greenComp, 24)
	ADD R9, R9, R2			;redTotal += redComp
	LDMFD SP!, {R0-R7, PC}
	
;addBlue subroutine
;
;Adds the Blue component of a pixel at pixelAddress to the running total
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R10= blueTotal
addBlue
	STMFD SP!, {R0-R7, LR}
	LDR R1, =0x000000FF		;R1 = blueMask
	LDR R0, [R4]			;pixel = loadPixelValue(pixelAddress)
	AND R2, R0, R1			;blueComp = getGreenComponent(pixel)
	ADD R10, R10, R2		;redTotal += redComp
	LDMFD SP!, {R0-R7, PC}

	
	END	
		