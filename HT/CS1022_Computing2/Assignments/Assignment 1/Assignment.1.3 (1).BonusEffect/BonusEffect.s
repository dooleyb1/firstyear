	AREA	BonusEffect, CODE, READONLY
	IMPORT	main
	IMPORT	getPicAddr
	IMPORT	putPic
	IMPORT	getPicWidth
	IMPORT	getPicHeight
	EXPORT	start

start

		BL	getPicAddr	; load the start address of the image in R4
		MOV	R4, R0
		BL	getPicHeight	; load the height of the image (rows) in R5
		MOV	R5, R0
		BL	getPicWidth	; load the width of the image (columns) in R6
		MOV	R6, R0
		
		LDR R3, =20	; threshold = 120

		LDR R1, =-1		; for(j=0;
forOne	ADD R1, R1, #1	; 
		CMP R1, R5		;	  j<image.Height; j++)
		BGE endLoop		; {
		LDR R2, =0		; 	for(i=0;
forTwo	CMP R2, R6		;	    i<image.Width; i++)
		BGE forOne		;   {
		BL getRed		;		redVal = red(i,j)
		BL getRedRight	;		redValRight = red(i+1,j)
		SUB R7, R9, R10	;		redDifference = redVal - redRight
		CMP R7, R3		;		if(redDifference < threshold
		BLT endIfOne	;			endLoop
		BL getGreen		;		greenVal = green(i,j)
		BL getGreenRight;		greenValRight = green(i+1,j)
		SUB R7, R9, R10	;		greenDifference = greenVal - greenRight
		CMP R7, R3		;		if(greenDifference < threshold
		BLT endIfOne	;			endLoop
		BL getBlue		;		blueVal = blue(i,j)
		BL getBlueRight	;		blueValRight = blue(i+1,j)
		SUB R7, R9, R10	;		blueDifference = blueVal - blueRight
		CMP R7, R3		;		if(blueDifference < threshold
		BLT endIfOne	;			endLoop
						;		else()
		B edgeFoundSet	;		setPixelWhite
endIfOne				;		}

		BL getRed		;		redVal = red(i,j)
		BL getRedBelow	;		redValRight = red(i,j+1)
		SUB R7, R9, R10	;		redDifference = redVal - redRight
		CMP R7, R3		;		if(redDifference < threshold
		BLT edgeNotFoundSet;			endLoop
		BL getGreen		;		greenVal = green(i,j)
		BL getGreenBelow;		greenValRight = green(i,j+1)
		SUB R7, R9, R10	;		greenDifference = greenVal - greenRight
		CMP R7, R3		;		if(greenDifference < threshold
		BLT edgeNotFoundSet	;			endLoop
		BL getBlue		;		blueVal = blue(i,j)
		BL getBlueBelow	;		blueValRight = blue(i,j+1)
		SUB R7, R9, R10	;		blueDifference = blueVal - blueRight
		CMP R7, R3		;		if(blueDifference < threshold
		BLT edgeNotFoundSet	;			endLoop

		
edgeFoundSet
		BL setEdgeWhite
		ADD R4, R4, #4
		ADD R2, R2, #1
		B forTwo
		
edgeNotFoundSet
		BL setEdgeBlack
		ADD R2, R2, #1
		ADD R4, R4, #4
		B forTwo		;	}
endLoop					;}

	BL	putPic		; re-display the updated image

stop	B	stop

;getRed Subroutine
;
;Gets and returns the Red Component value of a pixel at pixelAddress
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R8= redComponent
getRed
	STMFD SP!, {R0-R6, LR}
	LDR R1, =0x00FF0000		;R1 = redMask
	LDR R3, [R4]			;pixel = loadPixelValue(pixelAddress)
	AND R2, R3, R1			;redComp = getRedComponent(pixel)
	LSR R9, R2, #16			;shiftRight(redComp, 24)
	LDMFD SP!, {R0-R6, PC}

;getRedRight Subroutine
;
;Gets and returns the Red Component value of a pixel at pixelAddress+1
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R9= redRightComponent
getRedRight
	STMFD SP!, {R0-R6, LR}
	LDR R1, =0x00FF0000		;R1 = redMask
	LDR R3, [R4, #4]		;pixel = loadPixelValue(pixelAddress+4)
	AND R2, R3, R1			;redComp = getRedComponent(pixel)
	LSR R10, R2, #16		;shiftRight(redComp, 24)
	LDMFD SP!, {R0-R6, PC}

;getGreen Subroutine
;
;Gets and returns the Green Component value of a pixel at pixelAddress
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R9= greenComponent
getGreen
	STMFD SP!, {R0-R6, LR}
	LDR R1, =0x00FF0000		;R1 = greenMask
	LDR R3, [R4]			;pixel = loadPixelValue(pixelAddress)
	AND R2, R3, R1			;redComp = getGreenComponent(pixel)
	LSR R9, R2, #8			;shiftRight(greenComp, 24)
	LDMFD SP!, {R0-R6, PC}

;getGreenRight Subroutine
;
;Gets and returns the green Component value of a pixel at pixelAddress+1
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R8= greenRightComponent
getGreenRight
	STMFD SP!, {R0-R6, LR}
	LDR R1, =0x0000FF00		;R1 = greenMask
	LDR R3, [R4, #4]		;pixel = loadPixelValue(pixelAddress+4)
	AND R2, R3, R1			;greenComp = getGreenComponent(pixel)
	LSR R10, R2, #8			;shiftRight(greenComp, 8)
	LDMFD SP!, {R0-R6, PC}

;getBlue Subroutine
;
;Gets and returns the Blue Component value of a pixel at pixelAddress
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R9= blueComponent
getBlue
	STMFD SP!, {R0-R6, LR}
	LDR R1, =0x0000FF		;R1 = blueMask
	LDR R3, [R4]			;pixel = loadPixelValue(pixelAddress)
	AND R9, R3, R0			;blueComp = getBlueComponent(pixel)
	LDMFD SP!, {R0-R6, PC}

;getBlueRight Subroutine
;
;Gets and returns the BlueComponent value of a pixel at pixelAddress+1
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R8= blueRightComponent
getBlueRight
	STMFD SP!, {R0-R6, LR}
	LDR R1, =0x0000FF		;R1 = blueMask
	LDR R3, [R4, #4]		;pixel = loadPixelValue(pixelAddress+4)
	AND R10, R3, R1			;blueComp = getBlueComponent(pixel)
	LDMFD SP!, {R0-R6, PC}

;getRedBelow Subroutine
;
;Gets and returns the Red Component value of a pixel at pixelAddress (i, j+1)
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R9= redBelowComponent
getRedBelow
	STMFD SP!, {R0-R6, LR}
	LDR R1, =0x00FF0000		;R1 = redMask
	LSL R6, R6, #2			;adjustment = columns*4
	ADD R4, R4, R6
	LDR R3, [R4]		    ;pixel = loadPixelValue(pixelAddress+adjustment)
	AND R2, R3, R1			;redComp = getRedComponent(pixel)
	LSR R10, R2, #16		;shiftRight(redComp, 24)
	LDMFD SP!, {R0-R6, PC}
	
;getGreenBelow Subroutine
;
;Gets and returns the Green Component value of a pixel at pixelAddress (i, j+1)
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R9= greenBelowComponent
getGreenBelow
	STMFD SP!, {R0-R6, LR}
	LDR R1, =0x0000FF00		;R1 = greenMask
	LSL R6, R6, #2			;adjustment = columns*4
	LDR R3, [R4, R6]		;pixel = loadPixelValue(pixelAddress+adjustment)
	AND R2, R3, R1			;greenComp = getGreenComponent(pixel)
	LSR R10, R2, #8		;shiftRight(redComp, 24)
	LDMFD SP!, {R0-R6, PC}
	
;getBlueBelow Subroutine
;
;Gets and returns the Blue Component value of a pixel at pixelAddress (i, j+1)
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R9= blueBelowComponent
getBlueBelow
	STMFD SP!, {R0-R6, LR}
	LDR R1, =0x00000000		;R1 = blueMask
	LSL R6, R6, #2			;adjustment = columns*4
	LDR R3, [R4, R6]		;pixel = loadPixelValue(pixelAddress+adjustment)
	AND R10, R3, R1			;blueComp = getRedComponent(pixel)
	LDMFD SP!, {R0-R6, PC}

;setEdgeWhite Subroutine
;
;Sets the pixel value at pixelAddress to white
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R9= blueBelowComponent
setEdgeWhite
	STMFD SP!, {R0-R6, LR}
	LDR R1, =0x00FFFFFF		;R1 = whiteValue
	STR R1, [R4]			;setWhite()
	LDMFD SP!, {R0-R6, PC}
	
;setEdgeBlack Subroutine
;
;Sets the pixel value at pixelAddress to black
;
;Parameters:
;R4= pixelAddress
;
;Returns;
;R9= blueBelowComponent
setEdgeBlack
	STMFD SP!, {R0-R6, LR}
	LDR R1, =0x00000000		;R1 = blackValue
	STR R1, [R4]			;setBlack()
	LDMFD SP!, {R0-R6, PC}

	END	