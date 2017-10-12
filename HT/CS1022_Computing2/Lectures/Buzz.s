	AREA	Buzz, CODE, READONLY
	IMPORT	main
	EXPORT	start

;
; Memory-mapped device registers
;
PINSEL1		EQU	0xE002C004
DACR		EQU	0xE006C000
;
;notes - these are the (ARM periferal clock speed divided by 2 for high and low component) divided by frequencies of piano notes
;moving up an octave is a logical shift left (*2) of the note (still need to converti it to a time by 12MHz / (NOTE))
;https://en.wikipedia.org/wiki/Piano_key_frequencies
;NAME			TIME	MAX_WAVES_PER_SECOND/NOTE FREQ =
A			EQU 218182 ;(12MHz/2)/27.5		A0
AS			EQU 205938 ;6MHz/29.135	A#0
B			EQU 194382 ;6MHz/30.867	B0
C			EQU 183468 ;6MHz/32.7032	C1
CS			EQU 173171 ;6MHz/34.6478	C#1
D			EQU 163452 ;6MHz/36.7081	D1
DS			EQU 154278 ;6MHz/38.8909	D#1
E			EQU 145619 ;6MHz/41.2034	E1
F			EQU 137446 ;6MHz/43.6535	F1
FS			EQU 129732 ;6MHz/46.2493	F#1
G			EQU 122450 ;6MHz/48.9994	G
GS			EQU 115578 ;6MHz/51.9131	G#1
C4			EQU	22934	;6MHz / (261.626Hz * 2)

;
; A value between 0 and 1023 representing the volume
;
volume 		EQU 	1023


start
	
	;
	; Configure DAC	(Digital to Audio Converter)
	;

	; Configure pin P0.26 for AOUT (DAC analog out)
	LDR	R5, =PINSEL1
	LDR	R6, [R5]
	BIC	R6, R6, #(0x03 << 20)
	ORR	R6, R6, #(0x02 << 20)
	STR	R6, [R5]

	; DAC is always on so no further configuration required


	;
	; Infinite loop
	;
repeat					; while (forever) { 
	;
	; Change analog output to cause square wave signal
	; If signal is currently high, send it low. If its low, send it high
	;
	; Load the current DAC output value
	LDR	R5, =DACR
	LDR	R6, [R5]
	
	; Mask out all but bits 15...6
	LDR	R7, =0x0000FFC0
	AND	R6, R6, R7
	
	CMP	R6, #0			; if (DACR == 0)
	BNE	high			; {
	LDR	R6, =(volume << 6)	;  DACR = volume << 6
	B	endif			; }
high					; else {
	LDR	R6, =0			;  DACR = 0
endif					; }
	STR	R6, [R5]		; store new DACR
	
	; Short delay
	LDR	R4, =C4		;  square wave delay
	;LSR	R4, R4, #3		;MOVE UP N OCTAVES (APPROX)
whDelay					;   while (count > 0)
	CMP	R4, #0			;   {
	BEQ	eWhDelay		;
	SUB	R4, R4, #1		;     count = count - 1
	B	whDelay			;   }
eWhDelay				;
	B	repeat			; }
		
stop	B	stop




	

	END