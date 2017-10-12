	AREA	Anagram, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
		LDR	R1, =stringA
		LDR	R2, =stringB
		
changeACase
		LDRB R3, [R1]			;testChar = get.first(stringA)
		CMP R3, #0x00			;while(testChar != 0x00)
		BEQ changeBCase			;{
		CMP R3, #'a'				; if(testChar == higherCase)
		BHS nextAChar			; {
		ADD R3, R3, #0x20		;  changeToLowerCase(testChar)
		STRB R3, [R1]			;  storeChar(testChar)
nextAChar
		ADD R1, R1, #1			;  testCharAddress ++
		B changeACase			; }
								; }
		
changeBCase
		LDRB R4, [R2]			;testChar = get.first(stringA)
		CMP R4, #0x00			;while(testChar != 0x00)
		BEQ testStrings			;{
		CMP R4, #'a'				; if(testChar == higherCase)
		BHS nextBChar			; {
		ADD R4, R4, #0x20		;  changeToLowerCase(testChar)
		STRB R4, [R2]			;  storeChar(testChar)
nextBChar
		ADD R2, R2, #1			;  testCharAddress ++
		B changeBCase			; }
								; }

testStrings
		LDR	R1, =stringA
		LDR	R2, =stringB
		
		LDRB R3, [R1]			;load testChar
	
while1	CMP R3, #0x00			;while1 (testChar != 0x00)
		BEQ checkStringB				;{
		LDR R5, =stringB		;stringBAddressCopy
		LDRB R4, [R5]			;compareChar = get.first(stringB)
		
while2	CMP R4, #0x20			;while(compareChar = SPACE)
		BNE continue
		ADD R5,R5, #1			; stringB++
		LDRB R4, [R5]		 	; compareChar = get.next(StringB)
		CMP R4, #0x00			; if (compareChar = 0x00)
		BNE while2				; {
		LDR R0, =1				;	 anagram =true
		B stop					; }
			
continue
while3	CMP R4, #0x00			;while3 (compareChar !=0x00)
		BEQ anFalse				;{
		SUB R7, R3, R4			;	result = testChar - compareChar
		
		CMP R7, #0x00			;	if (result = 0)
		BNE elseB				;	{
		LDR R4, =0x20			;		setCompareChar = SPACE 
		STRB R4, [R5]			;		storeOverwrite
		ADD R1, R1, #1			;		addressA++
		LDRB R3, [R1]			;		testChar = get.next(stringA)
		B while1				;	}
								;	else
								;	{
elseB  	ADD R5,R5, #1			;	stringB++
		LDRB R4, [R5]		 		;compareChar = get.next(StringB)
		B while3				;	}
								;}
checkStringB
		LDR R2, =stringB
stringCheckWh
		LDRB R1, [R2]			;while( bChar != 0x00 && bChar == SPACE Flag)
		CMP R1, #0x00			;{
		BEQ anTrue				;
		CMP R1, #0x20			;
		BNE anFalse				;
		ADD R2, R2, #1			; bChar Address++
		B stringCheckWh			;}

anFalse LDR R0, =0				; anagram = 0
		B stop
		
anTrue  LDR R0, =1				; anagram = 1
	
stop	B	stop



	AREA	TestData, DATA, READWRITE

stringA	DCB	"Best tacos",0
stringB	DCB	"BEST coats",0

	END
