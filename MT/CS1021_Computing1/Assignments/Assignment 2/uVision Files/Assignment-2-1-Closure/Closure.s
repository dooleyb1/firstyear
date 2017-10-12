	AREA	Closure, CODE, READONLY
	IMPORT	main
	EXPORT	start

start

	LDR R1, =ASize				;aCount = ASize
	LDR R1, [R1]				;aCountCopy = [aCount]
	LDR R9, =AElems				;aElemsCopy = copyAddress(aElems)
	LDR R10, =0					;zeroFlag
	LDR R11, =4					;counterMultiplier
	
zeroCheck
	CMP R1, #1					;if(aSize ==1)
	BNE while1					;&&	
	LDR R4, [R9]				;loadAElem
	CMP R4, #0					;if (aElem == 0)
	BEQ closed

while1
	CMP R1, #0					;while(aCount!=0)
	BEQ closed					;{
	LDR R4, [R9]				;get.testChar(aElemsCopy)
	CMP R4, #0					;if (testChar == 0)
	BNE getNextChar				;{
	ADD R9, R9, #4				; testCharAddress++
	B while1
	
getNextChar
	LDR R2, =AElems
	LDR R6, [R2]				;get.aChar(aElems)
	LDR R7, =ASize
	LDR R7, [R7]				;aSizeCopy

while2
	CMP R7, #0					;while(aSizeCopy!=0)
	BEQ notClosed				;{
	CMP R4, #0					; if(testChar == 0)
	BNE additionTest			;{
	ADD R9, R9, #4				; testCharAddress++
	SUB R7, R7, #1				; aSizeCopy --
	B while2
	
additionTest
	ADD R3, R4, R6				; result = testChar + aChar
	
	CMP R3, #0					;if(result != 0  
	BEQ	continue				;		&&
	CMP R7, #0					;	aCount =0)
	BNE continue				;{
	LDR R0, =0					; closed = 0
	B stop						;}

continue
	CMP R3, #0 					;else if(result !=0
	BEQ continue2				;		&&
	CMP R7, #0					;    	aCount != 0)
	BEQ while2					;{
	ADD R2, R2, #4				; address++
	LDR R6, [R2]				; load(aChar)
	SUB R7, R7, #1				; aCountCopy--
	B while2					;}
	
continue2
	STR R10, [R9]				;storeZeroFlag
	SUB R5, R1, R7				;counter = aCount - aCountCopy
	SUB R1, R1, #2				;aCount - 2
	MUL R5, R11, R5				;counter *= 4
	ADD R9, R9, R5				;aAddress + counter
	STR R10, [R2]				;storeZeroFlag()
	LDR R9, =AElems
	B while1					;}
	
closed
	LDR R0, =1					; closed =0
	B stop

notClosed
	LDR R0, =0					;notClosed = 1
	
	
	
	
stop	B	stop


	AREA	TestData, DATA, READWRITE

ASize	DCD	1			; Number of elements in A
AElems	DCD	+1			; Elements of A

	END
