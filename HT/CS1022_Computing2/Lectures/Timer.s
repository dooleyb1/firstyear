	AREA	Timer, CODE, READONLY
	IMPORT	main
	EXPORT	start

;
; Some useful constants for memory-mapped register addresses
;
VICIntSelect	EQU	0xFFFFF00C
VICIntEnable	EQU	0xFFFFF010
VICVectAddr0	EQU	0xFFFFF100
VICVectPri0	EQU	0xFFFFF200
VICVectAddr	EQU	0xFFFFFF00

PINSEL4		EQU	0xE002C010
FIO2DIR1	EQU	0x3FFFC041
FIO2SET1	EQU	0x3FFFC059
FIO2CLR1	EQU	0x3FFFC05D
FIO2PIN1	EQU	0x3FFFC055

T0TCR		EQU	0xE0004004
T0CTCR		EQU	0xE0004070
T0MR0		EQU	0xE0004018
T0MCR		EQU	0xE0004014
T0PR		EQU	0xE000400C
T0IR		EQU	0xE0004000

start
	;
	; Configure GPIO mode on P2.10
	;

	; Enable P2.10 for GPIO
	LDR	R5, =PINSEL4
	LDR	R6, [R5]	
	BIC	R6, #(0x3 << 20)
	STR	R6, [R5]
	
	; Set P2.10 for output
	LDR	R5, =FIO2DIR1
	LDRB	R6, [R5]
	ORR	R6, #(0x01 << 2)
	STRB	R6, [R5]

	;
	; Configure TIMER0 for 1 second interrupts
	;
	
	; Stop and reset TIMER0 using Timer Control Register
	; Set bit 0 of TCR to 0 to diasble TIMER
	; Set bit 1 of TCR to 1 to reset TIMER
	LDR	R5, =T0TCR
	LDR	R6, =0x2
	STRB	R6, [R5]

	; Clear any previous TIMER0 interrupt by writing 0xFF to the TIMER0
	; Interrupt Register (T0IR)
	LDR	R5, =T0IR
	LDR	R6, =0xFF
	STRB	R6, [R5]

	; Set timer mode using Count Timer Control Register
	; Set bits 0 and 1 of CTCR to 00
	; for timer mode
	LDR	R5, =T0CTCR
	LDR	R6, =0x00
	STRB	R6, [R5]

	; Set match register for 1 sec using Match Register
	; Assuming a 12Mhz clock, set MR to 12,000,000
	LDR	R5, =T0MR0
	LDR	R6, =12000000
	STR	R6, [R5]

	; Interrupt and restart on match using Match Control Register
	; Set bit 0 of MCR to 1 to turn on interrupts
	; Set bit 1 of MCR to 1 to reset counter to 0 after every match
	; Set bit 2 of MCR to 0 to leave the counter enabled after match
	LDR	R5, =T0MCR
	LDR	R6, =0x03
	STRH	R6, [R5]

	; Turn off prescaling using Prescale Register
	; (prescaling is only needed to measure long intervals)
	LDR	R5, =T0PR
	LDR	R6, =0
	STR	R6, [R5]

	;
	; Configure VIC for TIMER0 interrupts
	;

	; Useful VIC vector numbers and masks for following code
	LDR	R3, =4			; vector 4
	LDR	R4, =(1 << 4) 	; bit mask for vector 4
	
	; VICIntSelect - Clear bit 4 of VICIntSelect register to cause
	; channel 4 (TIMER0) to raise IRQs (not FIQs)
	LDR	R5, =VICIntSelect	; addr = VICVectSelect;
	LDR	R6, [R5]		; tmp = Memory.Word(addr);		
	BIC	R6, R6, R4		; Clear bit for Vector 0x04
	STR	R6, [R5]		; Memory.Word(addr) = tmp;
	
	; Set Priority for VIC channel 4 (TIMER0) to lowest (15) by setting
	; VICVectPri4 to 15. Note: VICVectPri4 is the element at index 4 of an
	; array of 4-byte values that starts at VICVectPri0.
	; i.e. VICVectPri4=VICVectPri0+(4*4)
	LDR	R5, =VICVectPri0	; addr = VICVectPri0;
	MOV	R6, #15			; pri = 15;
	STR	R6, [R5, R3, LSL #2]	; Memory.Word(addr + vector * 4); = pri;
	
	; Set Handler routine address for VIC channel 4 (TIMER0) to address of
	; our handler routine (TimerHandler). Note: VICVectAddr4 is the element
	; at index 4 of an array of 4-byte values that starts at VICVectAddr0.
	; i.e. VICVectAddr4=VICVectAddr0+(4*4)
	LDR	R5, =VICVectAddr0	; addr = VICVectAddr0;
	LDR	R6, =TimerHandler	; handler = address of TimerHandler;
	STR	R6, [R5, R3, LSL #2]	; Memory.Word(addr + vector * 4) = handler

	
	; Enable VIC channel 4 (TIMER0) by writing a 1 to bit 4 of VICIntEnable
	LDR	R5, =VICIntEnable	; addr = VICIntEnable;
	STR	R4, [R5]		; enable Timers for vector 0x4

	;
	; Start TIMER0
	;

	; Start TIMER0 using the Timer Control Register
	; Set bit 0 of TCR to enable the timer
	LDR	R5, =T0TCR
	LDR	R6, =0x01
	STRB	R6, [R5]

	;
	; Initialisation is finished. The remainder of the functionality is
	; provided by the interrupt handler.
	;
		
stop	B	stop



;
; Timer interrupt handler
;
TimerHandler
	SUB	LR, LR, #4		; Adjust return address (because the processor
					; sets it 4 bytes after the real return address!!)

	STMFD	sp!, {r0-r12, LR}	; save registers to avoid unintended side effects
	
	; Reset TIMER0 interrupt by writing 0xFF to T0IR
	LDR	R5, =T0IR
	MOV	R6, #0xFF
	STRB	R6, [R5]
	
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

	; Clear source of interrupt by writing 0 to VICVectAddr
	LDR	R5, =VICVectAddr
	MOV	R6, #0		
	STR	R6, [R5]	
	
	; Return
	LDMFD	sp!, {r0-r12, PC}^	; restore register and CPSR	

	END