	AREA	LED, CODE, READONLY
	IMPORT	main
	EXPORT	start

; Addresses of some memory-mapped device registers (for convenience)
PINSEL4		EQU	0xE002C010
FIO2DIR1	EQU	0x3FFFC041
FIO2SET1	EQU	0x3FFFC059
FIO2CLR1	EQU	0x3FFFC05D
FIO2PIN1	EQU	0x3FFFC055

start
	;
	; Configure GPIO mode on P2.10
	;

	; Enable P2.10 for GPIO
	LDR	R5, =PINSEL4		; load address of PINSEL4
	LDR	R6, [R5]		; load current PINSEL4 value
	BIC	R6, #(0x3 << 20)	; set bits 20 and 21 to 00
	STR	R6, [R5]		; store new PINSEL4 value
	
	; Set P2.10 for output
	LDR	R5, =FIO2DIR1		; Set bits 2 and 3 of FIO2DIR1 register
	LDRB	R6, [R5]		; to 01 (clear first then set)
	BIC	R6, #(0x03 << 2)	; REMEMBER - Read-Modify-Write as before
	ORR	R6, #(0x01 << 2)
	STRB	R6, [R5]

	;
	; Enter infinite loop (on...off...on...off...)
	;

repeat					; while (forever) { 

	; Flash the LED (on->off or off->on)

	LDR	R4, =0x04		;   setup bit mask for bit 2 of FIO2 (P2.10)
	LDR	R5, =FIO2PIN1		;
	LDRB	R6, [R5]		;   read FIO2PIN1
	AND	R7, R6, R4		;   only want to test bit 2 – mask other bits
	CMP	R7, #0			;   if (LED off)
	BNE	elseoff			;   {
	ORR	R6, R6, R4		;     set bit 2 (turn LED on)
	B	endif			;   }
elseoff					;   else {
	BIC	R6, R6, R4		;     clear bit 2 (turn LED on)
endif					;   }
	STRB	R6, [R5]		;   write new FIO2PIN1 value
					;

	; Short delay

	LDR	R4, =0x400000		
	;   count = 0x400000
whDelay					;   while (count > 0)
	CMP	R4, #0			;   {
	BEQ	eWhDelay		;
	SUB	R4, R4, #1		;     count = count - 1
	B	whDelay			;   }
eWhDelay				;
	B	repeat			; }


		
stop	B	stop

	END