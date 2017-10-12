	AREA	SymmDiff, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	
	LDR R1, =ASize
	LDR R1, [R1]				;aCount 
	LDR R2, =AElems	
	LDR R9, =CElems	
	LDR R7, =0					;cSize = 0
	
while1
	CMP R1, #0					;while(aCount != 0)
	BEQ moveBElems 					;{
	
	LDR R3, [R2]				; load aVal
	LDR R8, =BElems				;
	LDR R4, [R8]				; load bVal
	LDR R5, =BSize				;
	LDR R5, [R5]				; load bSize
	
while2 
	CMP R5, #0					;while (bSize != 0)
	BEQ moveAVal					;{
	
	SUB R6, R3, R4				; result = aVal - bVal
	
	CMP R6, #0					; if(result = 0)
	BNE elseDo					; {
	SUB R1, R1, #1				;  aCount--
	ADD R2, R2, #4				;  aAddress++
	B while1					;}
	
elseDo							; else{
	ADD R8, R8, #4				; bAddress++
	LDR R4, [R8]				; load next bVal
	SUB R5, R5, #1				; bCount--
	B while2					; }
	
moveAVal
	STR R3, [R9]				; store aVal
	ADD R9, R9, #4				; cSetAddress++
	ADD R7, R7, #1				; cSize++
	ADD R2, R2, #4				; aAddress++
	SUB R1, R1, #1				; aCount--
	B while1					; }
	
moveBElems
	LDR R1, =CSize
	STR R7, [R1]				; storeCSize
	LDR R2, =BElems				;
	LDR R4, =BSize				;
	LDR R4, [R4]				; load bSize

bWhile1
	CMP R4, #0					;while(bSize!=0)
	BEQ stop					;{
	LDR R3, [R2]				; load bVal
	LDR R5, =AElems				;
	LDR R8, =ASize				;
	LDR R1, =CSize				;
	LDR R10, [R8]				; load aSize
	
bWhile2
	CMP R10, #0					;while(aSize!=0)
	BEQ storeBVal				;{
	
	LDR R6, [R5]				; load aVal
	SUB R11, R6, R3				; result = aVal - bVal
	CMP R11, #0					; if(result=0)
	BNE nextCVal				; {
	ADD R2, R2, #4				;  	bValAddress++
	SUB R4, R4, #1				;   bSize--
	B bWhile1					; }
	
nextCVal
	ADD R5, R5, #4				; aValAddress++
	SUB R10, R10, #1			; aSize--
	B bWhile2
	
storeBVal
	STR R3, [R9]				; store bVal into cSet
	ADD R9, R9, #4				; cSetAddress++
	ADD R7, R7, #1				; cSize++
	STR R7, [R1]				; storeCSize
	ADD R2, R2, #4				; bValAddress++
	SUB R4, R4, #1				; bSize--
	B bWhile1
	
stop	B	stop


	AREA	TestData, DATA, READWRITE

ASize	DCD	4			; Number of elements in A
AElems	DCD 123, 28, 102, 3		; Elements of A

BSize	DCD	2			; Number of elements in B
BElems	DCD 1920, 39		; Elements of B

CSize	DCD	0			; Number of elements in C (SymDifference / !Union)
CElems	SPACE	56			; Elements of C
	

	END
