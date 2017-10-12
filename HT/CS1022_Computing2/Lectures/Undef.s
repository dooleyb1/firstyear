	AREA	Undef, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	r4, =0x40000024	; 0x40000024 is mapped to 0x00000024
	LDR	r5, =UndefHandler ; Address of new undefined handler
	STR	r5, [r4]	; Store new undef handler address
	
	;
	; Test our new instruction
	;
	LDR	r4, =3	; test 3^4
	LDR	r5, =4	;
	
	; This is our undefinied unstruction opcode
	DCD	0x77F150F4	; POW r0, r4, r5 (r0 = r4 ^ r5)
	
	; R0 should be 81
		
stop	B	stop	


;
; Undefined exception handler
;
UndefHandler
	STMFD	sp!, {r0-r12, LR}	; save registers

	LDR	r4, [lr, #-4]		; load undefinied instruction
	BIC	r5, r4, #0xFFF0FFFF	; clear all but opcode bits
	TEQ	r5, #0x00010000		; check for undefined opcode 0x1
	BNE	endif1			; if (power instruction) {

	BIC	r5, r4, #0xFFFFFFF0	;  isolate Rm register number
	BIC	r6, r4, #0xFFFF0FFF	;  isolate Rn register number
	MOV	r6, r6, LSR #12		;
	BIC	r7, r4, #0xFFFFF0FF	;  isolate Rd register number
	MOV	r7, r7, LSR #8		;

	LDR	r1, [sp, r5, LSL #2]	;  grab saved Rm off stack
	LDR	r2, [sp, r6, LSL #2]	;  grab saved Rn off stack

	BL	power			;  call pow subroutine

	STR	r0, [sp, r7, LSL #2]	;  save result over saved Rd		
endif1					; }
	LDMFD	sp!, {r0-r12, PC}^	; restore register and CPSR


; power subroutine
; Computes x^y
; paramaters: r0: result (variable)
;             r1: x (value)
;             r2: y (value)
power
	STMFD	sp!, {r1-r2,lr}	; save registers
	
	CMP	r2, #0		; if (y = 0)
	BNE	else2		; {
	MOV	r0, #1		;  result = 1
	B	endif2		; }
else2				; else {
	MOV	r0, r1		;  result = x
	SUBS	r2, r2, #1		;  y = y - 1
	BEQ	endif3		;  if (y != 0) {
do4				;   do {
	MUL	r0, r1, r0		;    result = result * x
	SUBS	r2, r2, #1		;    y = y - 1
	BNE	do4		;   } while (y != 0)
endif3				;  }
endif2				; }	
	LDMFD	sp!, {r1-r2, pc} ; restore registers and return


	END