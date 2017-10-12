	AREA	ProperCase, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R1, =testStr

while						;do{
	LDRB R2, [R1]			;testString charachter 1
	CMP R2, #'z'			; if ( char <= 'z' || char >=  'a' )
	BHI outOfIf				; {
	CMP R2, #'a'			;
	BLO outOfIf				;
	SUB R2, R2, #0x20		;convert char to UpperCase
	STRB R2, [R1]			;store char
outOfIf						;}

	ADD R1, R1, #1			;address++
	
while2
	CMP R2, #0x20			;while ( char != SPACE && char != 0x00 )
	BEQ	endWhile2			;{
	CMP R2, #0x00
	BEQ endWhile2
	LDRB R2, [R1]			; loadChar()

	CMP R2, #'Z'			; if ( char <= 'Z' || char >= 'A' )
	BHI outOfIf2			; {
	CMP R2, #'A'			;
	BLO outOfIf2			;
	ADD R2, R2, #0x20		;convert char to UpperCase
	STRB R2, [R1]			;store char
outOfIf2					;}

	ADD R1, R1, #1			;address++
	B while2
endWhile2					;}

	CMP R2, #0x00
	BEQ endRead
	B while
endRead						;} while( char != 0x00)
stop	B	stop



	AREA	TestData, DATA, READWRITE

testStr	DCB	"my name is brandon",0

	END
