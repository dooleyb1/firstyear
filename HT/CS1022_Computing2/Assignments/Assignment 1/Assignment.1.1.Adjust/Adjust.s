	AREA	Adjust, CODE, READONLY
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
		BL	getPicHeight	; R5 = image.Height
		MOV	R5, R0
		BL	getPicWidth	; R6 = image.Width
		MOV	R6, R0

		LDR R7, =10	; brightnessVal = b
		LDR R8, =6		; contrastVal = c
		BL getEndAddress
		
		LDR R1, =-1		; for(j=0;
forOne	ADD R1, R1, #1	; 
		CMP R1, R5		;	  j<image.Height; j++)
		BGE endLoop		; {
		LDR R2, =0		; 	for(i=0;
forTwo	CMP R2, R6		;	    i<image.Width; i++)
		BGE forOne		;   {
		CMP R1, #0
		BNE checkNext
		CMP R2, #0
		BNE checkNext
		BL adjustPixel	;	    adjustPixel(i,j)
		ADD R4, R4, #4	;		pixelAddress++
		ADD R2, R2, #1	;		i++
		B forTwo
checkNext
		BL checkNextPixel
		ADD R4, R4, #4	;		pixelAddress++
		ADD R2, R2, #1	;		i++
		B forTwo		;	}
endLoop					;}

		BL overwriteImage
		BL	putPic		; re-display the updated image

stop	B	stop

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


;checkNextPixel Subroutine
;
;Compares currentPixel to nextPixel, if they are the same then the adjustment value of nextPixel is set to adjustmentValue of currentPixel 
;
;Parameters:
;R4= pixelAddress
;R7= brightnessVal (b)
;R8= contrastVal (c)
;
;Returns;
;

checkNextPixel
	STMFD SP!, {R0-R4, R7-R8, LR}
	LDR R0, [R4] 		;load(currentPixel)
	LDR R1, [R4, #4]	;load(nextPixel)
	CMP R0, R1			;if(currentPixel == nextPixel)
	BNE notSame			;{
	STR R10, [R12] ,#4  ; nextPixelAdjustmentVal =
	B endSub
notSame					;else
	BL adjustPixel		;adjustPixel()
endSub
	LDMFD SP!, {R0-R4, R7-R8, PC}


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

;adjustPixel subroutine
;
;Adjusts the brightness and contrast of pixel at image[i][j] based on the value of the 
;adjustment parameters b and c
;
;Parameters:
;R4= pixelAddress
;R7= brightnessVal (b)
;R8= contrastVal (c)
;
;Returns;
;

adjustPixel
	STMFD SP!, {R0-R4, R7-R8, LR}
	BL adjustRed			;adjustRed(pixel)
	BL adjustGreen			;adjustGreen(pixel)
	BL adjustBlue			;adjustBlue(pixel)
	STR R10, [R12], #4			;storeVal()
	LDMFD SP!, {R0-R4, R7-R8, PC}
	
;adjustRed subroutine
;
;Adjusts the red component of a pixel based on the adjustment values b and c
;
;Parameters:
;R0 = pixelVal
;R7= brightnessVal (b)
;R8= contrastVal (c)
;
;Returns;
;

adjustRed
	STMFD SP!, {R0-R2, LR}
	LDR R1, =0x00FF0000		;R1 = redMask
	LDR R0, [R4]			;pixel = loadPixelValue(pixelAddress)
	AND R2, R0, R1			;redComp = getRedComponent(pixel)
	LSR R2, R2, #16			;shiftRight(redComp, 24)
	MUL R2, R8, R2			;redComp *= contrastVal
	LSR R2, R2, #4			;redComp/16
	ADD R2, R2, R8			;redComp+=B
	
	CMP R2, #255			;if(recComp > 255)
	BLS checkZero			;{
	LDR R2, =255			;	redComp = 255
							;}
checkZero
	CMP R2, #0				;if(redComp < 0)
	BHS endSubRout			;{
	LDR R2, =0				;	redComp = 0
endSubRout					;}
	LSL R2, R2, #16			;shiftLeft(redComp, 24)
	ORR R0, R2, R0			;redComp = setRedComponent(pixel)
	STR R0, [R4]			;
	LDMFD SP!, {R0-R2, PC}

;adjustGreen subroutine
;
;Adjusts the green component of a pixel based on the adjustment values b and c
;
;Parameters:
;R0 = pixelVal
;R7= brightnessVal (b)
;R8= contrastVal (c)
;
;Returns;
;

adjustGreen
	STMFD SP!, {R0-R2, LR}
	LDR R1, =0x0000FF00		;R1 = greenMask
	LDR R0, [R4]			;pixel = loadPixelValue(pixelAddress)
	AND R2, R0, R1			;greenComp = getGreenComponent(pixel)
	LSR R2, R2, #8			;shiftRight(greenComp, 24)
	MUL R2, R8, R2			;greenComp *= contrastVal
	LSR R2, R2, #4			;greenComp/16
	ADD R2, R2, R8			;greenComp+=B
	
	CMP R2, #255			;if(greenComp > 255)
	BLS checkZero2			;{
	LDR R2, =255			;	greenComp = 255
							;}
checkZero2
	CMP R2, #0				;if(greenComp < 0)
	BHS endSubRout2			;{
	LDR R2, =0				;	greenComp = 0
endSubRout2					;}
	LSL R2, R2, #8			;shiftLeft(greenComp, 24)
	ORR R0, R2, R0			;greenComp = setGreenComponent(pixel)
	STR R0, [R4]			;
	LDMFD SP!, {R0-R2, PC}	
	
;adjustBlue subroutine
;
;Adjusts the blue component of a pixel based on the adjustment values b and c
;
;Parameters:
;R0 = pixelVal
;R7= brightnessVal (b)
;R8= contrastVal (c)
;
;Returns;
;

adjustBlue
	STMFD SP!, {R0-R2, LR}
	LDR R1, =0x000000FF		;R1 = blueMask
	LDR R0, [R4]			;pixel = loadPixelValue(pixelAddress)
	AND R2, R2, R1			;blueComp = getGreenComponent(pixel)
	MUL R2, R8, R2			;blueComp *= contrastVal
	LSR R2, R2, #4			;blueComp/16
	ADD R2, R2, R8			;blueComp+=B
	
	CMP R2, #255			;if(blueComp > 255)
	BLS checkZero3			;{
	LDR R2, =255			;	blueComp = 255
							;}
checkZero3
	CMP R2, #0				;if(blueComp < 0)
	BHS endSubRout3			;{
	LDR R2, =0				;	blueComp = 0
endSubRout3					;}
	ORR R0, R2, R0			;blueComp = setBlueComponent(pixel)
	MOV R10, R0			;
	LDMFD SP!, {R0-R2, PC}	
	
	END	