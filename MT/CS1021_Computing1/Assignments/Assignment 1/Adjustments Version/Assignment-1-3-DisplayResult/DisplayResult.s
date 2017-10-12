	AREA	DisplayResult, CODE, READONLY
	IMPORT	main
	IMPORT	getkey
	IMPORT	sendchar
	EXPORT	start
	PRESERVE8

start
	
	LDR R12, =0			; dividend --< outPutDigit
	LDR R11, =0			; mean
    LDR R10, =1 		; powerOf10
	LDR R9, =0			; maxNum
	LDR R8, =0x7FFFFFFF	; minNum
	LDR R7, =0			; sumOfNumbers
	LDR R6, =10			; number10
	LDR R5, =0			; count
	LDR R4, =0			; runningTotal
	

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
	CMP R5, #0 			; if (count == 0 
	BNE increaseCount
	CMP R4, #0
	BNE increaseCount
	CMP R0, #0x0D		;    &&
	BEQ printWordMean	;    key == ENTER endRead
increaseCount			;
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
	BEQ printWordMean	; {
	
meanWhile
	CMP	R12, R5			; 	while(dividend >= count)
	BLT printWordMean	; 	{
	ADD R11, R11, #1	; 		mean = mean + 1
	SUB R12, R12, R5	; 		dividend = dividend - count (divisor)
	B meanWhile			;	}
	
printWordMean
	LDR R0, =0xA		; NewLine
	BL sendchar
	LDR R0, ='M' 		; M	
	BL sendchar 
	LDR R0, ='e'		; e	
	BL sendchar			
	LDR R0, ='a' 		; a
	BL sendchar
	LDR R0, ='n'		; n
	BL sendchar 
	LDR R0, ='='		; =
	BL sendchar 
	
	LDR R12, =0			; clear R12
	CMP R11, #0			; if ( mean == 0 )
	BNE meanPrintStart
	LDR R0, =0x30		;
	BL sendchar			; print 0
	B printWordCount
	
						; // This next section uses an algorithm which detects the
						; // suitable power of 10 containing the first digit
						; // and continues to print the value of all digits 
						; // within the register in decimal form.
						
meanPrintStart		; else{
	LDR R10, =1			; powerOf10 = 1
	LDR R4, =10			; 10xPowerOf10 = 10

meanDigitCalc
	CMP R11, #0			; if (mean = 0)
	BEQ printWordCount	; displayCount
	CMP R11, R10		; if (mean >= powerOf10 
	BLT meanElse		; &&
	CMP R11, R4			; mean < 10xPowerOf10)
	BGE	meanElse		; {

	ADD R12, R12, #1	; outputDigit++
	CMP R11, R10		; if (mean == powerOf10)
	BNE meanContinue		; &&
	CMP R11, #1			;     mean != 1)
	BEQ meanOutputPrint
	BNE zeroFrontPrint
	
meanContinue
	SUB R11, R11, R10	; else ( mean - powerOf10 )
	CMP R11, R10		; if (mean < powerOf10)
	BGE meanDigitCalc	;
	MOV R0, R12			; print outputDigit
	ADD R0, R0, #0x30
	BL sendchar			;
	LDR R12, =0			; clear R12
	B meanPrintStart	;
	
meanOutputPrint
	MOV R0, R12			; print outputDigit
	ADD R0, R0, #0x30
	BL sendchar			;
	B printWordCount	;

meanElse				; else
	MUL R10, R6, R10	; powerOf10New = 10 * powerOf10Old
	MUL R4, R6, R4		; 10xPowerOf10New = 10 * 10xPowerOf10Old
	B meanDigitCalc

zeroFrontPrint	
	MOV R0, R12			; load outputDigit into R0
	ADD R0, R0, #0x30
	BL sendchar

						; // This next section allows digits ending in 0's to be
						; // displayed for example if the mean was 400

zeroDigit
	LDR R10, =1
	LDR R4, =10
	
meanZeroPrint	
	CMP R11, R4			; if ( mean = 10xPowerOf10 )
	BNE meanZeroElse
	LDR R0, =0x30		; load 0 into R0
	BL sendchar			; print 0
	MOV R11, R10		; set newMean = powerOf 10 (divide by 10)
	CMP R11, #1			; if (newMean == 1)
	BEQ printWordCount	; endRead
	B zeroDigit		
	
meanZeroElse
	MUL R10, R6, R10	; powerOf10New = 10 * powerOf10Old
	MUL R4, R6, R4		; 10xPowerOf10New = 10 * 10xPowerOf10Old
	B meanZeroPrint

						; // The algorithm used above is here repeated for 
						; // the statistical measure of count	

printWordCount
	LDR R0, =0x20		; SPACE
	BL sendchar
	LDR R0, =0xA		; NewLine
	BL sendchar
	LDR R0, ='C' 		; C	
	BL sendchar 
	LDR R0, ='o'		; o	
	BL sendchar			
	LDR R0, ='u' 		; u
	BL sendchar
	LDR R0, ='n'		; n
	BL sendchar 
	LDR R0, ='t'		; t
	BL sendchar 
	LDR R0, ='='		; =
	BL sendchar 
	
	LDR R12, =0			; clear R12
	CMP R5, #0			; if ( count == 0 )
	BNE countPrintStart
	LDR R0, =0x30		;
	BL sendchar			; print 0
	B printWordSum
	
	
countPrintStart
	LDR R10, =1			; powerOf10 = 1
	LDR R4, =10			; 10xPowerOf10 = 10

countDigit
	CMP R5, #0			; if (count = 0)
	BEQ printWordSum	; endRead
	CMP R5, R10		    ; if (count >= powerOf10 
	BLT countElse		; &&
	CMP R5, R4			; 	  count < 10xPowerOf10)
	BGE	countElse			; {

	ADD R12, R12, #1	; outputDigit++
	CMP R5, R10			; if (count == powerOf10)
	BNE continueCount		; &&
	CMP R5, #1			;     count != 1)
	BEQ countOutputNum
	BNE countFrontZero
	
continueCount
	SUB R5, R5, R10		; else ( count - powerOf10 )
	CMP R5, R10			; if (count < powerOf10)
	BGE countDigit		;
	MOV R0, R12			; print outputDigit
	ADD R0, R0, #0x30
	BL sendchar			;
	LDR R12, =0			; clear R12
	B countPrintStart	;
	
countOutputNum	
	MOV R0, R12			; print outputDigit
	ADD R0, R0, #0x30
	BL sendchar			;
	B printWordSum		;

countElse				; else
	MUL R10, R6, R10	; powerOf10New = 10 * powerOf10Old
	MUL R4, R6, R4		; 10xPowerOf10New = 10 * 10xPowerOf10Old
	B countDigit

						; // This section determines how many  
						; // zero's to display using the same method

countFrontZero
	MOV R0, R12			; load outputDigit into R0
	ADD R0, R0, #0x30
	BL sendchar

countZeroStart
	LDR R10, =1
	LDR R4, =10
	
countZeroPrint	
	CMP R5, R4			; if ( count = 10xPowerOf10 )
	BNE countZeroElse
	LDR R0, =0x30		; load 0 into R0
	BL sendchar			; print 0
	MOV R5, R10			; set newCount = powerOf 10 (divide by 10)
	CMP R5, #1			; if (newCount == 1)
	BEQ printWordSum	; endRead
	B countZeroStart	
	
countZeroElse
	MUL R10, R6, R10	; powerOf10New = 10 * powerOf10Old
	MUL R4, R6, R4		; 10xPowerOf10New = 10 * 10xPowerOf10Old
	B countZeroPrint
	
	
						; // The algorithm used above is here repeated for 
						; // the statistical measure of sum	
						
printWordSum
	LDR R0, =0xA		; NewLine
	BL sendchar
	LDR R0, ='S' 		; S	
	BL sendchar 
	LDR R0, ='u'		; u	
	BL sendchar			
	LDR R0, ='m' 		; m
	BL sendchar 
	LDR R0, ='='		; =
	BL sendchar 
	
	LDR R12, =0			; clear R12
	CMP R7, #0			; if ( sum == 0 )
	BNE sumPrintStart
	LDR R0, =0x30		;
	BL sendchar			; print 0
	B printWordMin
	
	
sumPrintStart
	LDR R10, =1			; powerOf10 = 1
	LDR R4, =10			; 10xPowerOf10 = 10

sumDigit
	CMP R7, #0			; if (sum = 0)
	BEQ printWordMin	; endRead
	CMP R7, R10			; if (sum>= powerOf10 
	BLT sumElse			; &&
	CMP R7, R4			; 	  sum < 10xPowerOf10)
	BGE	sumElse			; {

	ADD R12, R12, #1	; outputDigit++
	CMP R7, R10			; if (sum == powerOf10)
	BNE sumContinue		; &&
	CMP R7, #1			;     sum != 1)
	BEQ sumOutputNum
	BNE sumFrontZero
	
sumContinue
	SUB R7, R7, R10		; else ( sum - powerOf10 )
	CMP R7, R10			; if ( sum < powerOf10)
	BGE sumDigit		;
	MOV R0, R12			; print outputDigit
	ADD R0, R0, #0x30
	BL sendchar			;
	LDR R12, =0			; clear R12
	B sumPrintStart		;
	
sumOutputNum
	MOV R0, R12			; print outputDigit
	ADD R0, R0, #0x30
	BL sendchar			;
	B printWordMin	;

sumElse				; else
	MUL R10, R6, R10	; powerOf10New = 10 * powerOf10Old
	MUL R4, R6, R4		; 10xPowerOf10New = 10 * 10xPowerOf10Old
	B sumDigit

						; // This section determines how many  
						; // zero's to display using the same method

sumFrontZero	
	MOV R0, R12			; load outputDigit into R0
	ADD R0, R0, #0x30
	BL sendchar

sumZeroStart
	LDR R10, =1
	LDR R4, =10
	
sumZeroPrint
	CMP R7, R4			; if ( sum = 10xPowerOf10 )
	BNE sumZeroElse
	LDR R0, =0x30		; load 0 into R0
	BL sendchar			; print 0
	MOV R7, R10			; set newSum = powerOf 10 (divide by 10)
	CMP R7, #1			; if (newSum == 1)
	BEQ printWordMin	; endRead
	B sumZeroStart		
	
sumZeroElse
	MUL R10, R6, R10	; powerOf10New = 10 * powerOf10Old
	MUL R4, R6, R4		; 10xPowerOf10New = 10 * 10xPowerOf10Old
	B sumZeroPrint

						; // The algorithm used above is here repeated for 
						; // the statistical measure of min	

printWordMin
	LDR R0, =0xA		; NewLine
	BL sendchar
	LDR R0, ='M' 		; M
	BL sendchar 
	LDR R0, ='i'		; i	
	BL sendchar			
	LDR R0, ='n' 		; n
	BL sendchar 
	LDR R0, ='='		; =
	BL sendchar 
	
	LDR R12, =0			; clear R12
	CMP R8, #0			; if ( min == 0 )
	BNE minPrintStart
	LDR R0, =0x30		;
	BL sendchar			; print 0
	B printWordMax
	
	
minPrintStart
	LDR R10, =1			; powerOf10 = 1
	LDR R4, =10			; 10xPowerOf10 = 10

minDigit
	CMP R8, #0			; if (min = 0)
	BEQ printWordMax		; endRead
	CMP R8, R10			; if (min >= powerOf10 
	BLT minElse			; &&
	CMP R8, R4			; 	  min < 10xPowerOf10)
	BGE	minElse			; {

	ADD R12, R12, #1	; outputDigit++
	CMP R8, R10			; if (min == powerOf10)
	BNE minContinue		; &&
	CMP R8, #1			;     min != 1)
	BEQ minOutputNum
	BNE minFrontZero
	
minContinue
	SUB R8, R8, R10		; else ( min - powerOf10 )
	CMP R8, R10			; if (min < powerOf10)
	BGE minDigit		;
	MOV R0, R12			; print outputDigit
	ADD R0, R0, #0x30
	BL sendchar			;
	LDR R12, =0			; clear R12
	B minPrintStart		;
	
minOutputNum	
	MOV R0, R12			; print outputDigit
	ADD R0, R0, #0x30
	BL sendchar			;
	B printWordMax		;

minElse					; else
	MUL R10, R6, R10	; powerOf10New = 10 * powerOf10Old
	MUL R4, R6, R4		; 10xPowerOf10New = 10 * 10xPowerOf10Old
	B minDigit

						; // This section determines how many  
						; // zero's to display using the same method

minFrontZero
	MOV R0, R12			; load outputDigit into R0
	ADD R0, R0, #0x30
	BL sendchar

minZeroStart
	LDR R10, =1
	LDR R4, =10
	
minZeroPrint
	CMP R8, R4			; if ( min = 10xPowerOf10 )
	BNE minZeroElse
	LDR R0, =0x30		; load 0 into R0
	BL sendchar			; print 0
	MOV R8, R10			; set newMin = powerOf 10 (divide by 10)
	CMP R8, #1			; if (newMin == 1)
	BEQ printWordMax	; endRead
	B minZeroStart	
	
minZeroElse
	MUL R10, R6, R10	; powerOf10New = 10 * powerOf10Old
	MUL R4, R6, R4		; 10xPowerOf10New = 10 * 10xPowerOf10Old
	B minZeroPrint
	
						; // The algorithm used above is here repeated for 
						; // the statistical measure of max	
	
printWordMax
	LDR R0, =0xA		; NewLine
	BL sendchar
	LDR R0, ='M' 		; M
	BL sendchar 
	LDR R0, ='a'		; a	
	BL sendchar			
	LDR R0, ='x' 		; x
	BL sendchar 
	LDR R0, ='='		; =
	BL sendchar 
	
	LDR R12, =0			; clear R12
	CMP R9, #0			; if ( max == 0 )
	BNE maxPrintStart
	LDR R0, =0x30		;
	BL sendchar			; print 0
	B endRead
	
	
maxPrintStart
	LDR R10, =1			; powerOf10 = 1
	LDR R4, =10			; 10xPowerOf10 = 10

maxDigit
	CMP R9, #0			; if (max = 0)
	BEQ endRead			; endRead
	CMP R9, R10			; if (max >= powerOf10 
	BLT maxElse			; &&
	CMP R9, R4			; 	  max < 10xPowerOf10)
	BGE	maxElse			; {

	ADD R12, R12, #1	; outputDigit++
	CMP R9, R10			; if (max == powerOf10)
	BNE maxContinue		; &&
	CMP R9, #1			;     max != 1)
	BEQ maxOutputNum
	BNE maxFrontZero
	
maxContinue
	SUB R9, R9, R10		; else ( max - powerOf10 )
	CMP R9, R10			; if (max < powerOf10)
	BGE maxDigit		;
	MOV R0, R12			; print outputDigit
	ADD R0, R0, #0x30
	BL sendchar			;
	LDR R12, =0			; clear R12
	B maxPrintStart	;
	
maxOutputNum
	MOV R0, R12			; print outputDigit
	ADD R0, R0, #0x30
	BL sendchar			;
	B endRead	;

maxElse					; else
	MUL R10, R6, R10	; powerOf10New = 10 * powerOf10Old
	MUL R4, R6, R4		; 10xPowerOf10New = 10 * 10xPowerOf10Old
	B maxDigit

						; // This section determines how many  
						; // zero's to display using the same method

maxFrontZero
	MOV R0, R12			; load outputDigit into R0
	ADD R0, R0, #0x30
	BL sendchar

maxZeroStart
	LDR R10, =1
	LDR R4, =10
	
maxZeroPrint
	CMP R9, R4			; if ( max = 10xPowerOf10 )
	BNE maxZeroElse
	LDR R0, =0x30		; load 0 into R0
	BL sendchar			; print 0
	MOV R9, R10			; set newMax = powerOf 10 (divide by 10)
	CMP R9, #1			; if (newMax == 1)
	BEQ endRead			; endRead
	B maxZeroStart	
	
maxZeroElse
	MUL R10, R6, R10	; powerOf10New = 10 * powerOf10Old
	MUL R4, R6, R4		; 10xPowerOf10New = 10 * 10xPowerOf10Old
	B maxZeroPrint

endRead					; 	endRead
						; }
stop	B	stop

	END	
stop	B	stop

	END	
