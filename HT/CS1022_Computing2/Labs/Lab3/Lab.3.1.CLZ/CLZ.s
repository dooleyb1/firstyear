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
	LDR	r4, =3	; test 3
	
	; This is our undefinied unstruction opcode
	DCD	0x77F120F4	; CLZ R0, R4
	
	; R0 should be 30
		
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
	BIC	r7, r4, #0xFFFFF0FF	;  isolate Rd register number
	MOV	r7, r7, LSR #8		;

	LDR	r1, [sp, r5, LSL #2]	;  grab saved Rm off stack
	BL	CLZsub			;  call pow subroutine

	STR	r0, [sp, r7, LSL #2]	;  save result over saved Rd		
endif1					; }
	LDMFD	sp!, {r0-r12, PC}^	; restore register and CPSR


; clearLeadingZeros subroutine
; Counts the leading zeros of a value x
; paramaters: r0: result (variable)
;             r1: x (value)
;             
CLZsub
	STMFD	sp!, {r1-r4,lr}	; save registers
	
	MOV R0, #0				;count = 0
	MOV R4, #0x80000000		;front bit mask
	AND R2, R4, R1			;xFrontBit = getFrontBit(x)
	LSR R2, R2, #31			;xFrontBit >> 31
	
	CMP R2, #0				;if(xFrontBit == 0)
	BNE endLoop				;{
	
while
	AND R2, R4, R1			;xFrontBit = getFrontBit(x)
	LSR R2, R2, #31			;shiftBitRight >> 31
	CMP R2, #1				;if(bit != 1)
	BEQ endLoop				;{
	ADD R0, R0, #1			; count++
	LSL R1, R1, #1			; x << #1
	B while
	
	
endLoop
	LDMFD	sp!, {r1-r4, pc} ; restore registers and return


	END