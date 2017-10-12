	AREA	ArrayMove, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR	R0, =array
	LDR	R1, =N	
	LDR	R2, =2			; move from index
	LDR	R3, =6			; move to index
	LDR R5, =4			; index offset

	LDR R4, [R0, #0]	;val = array[index]
	MUL R5, R2, R5		;index offset *= move from index * 4
	LDR R4, [R0, R5]	;val = array[index]
	MOV R9, R4			;valToSwapIndex = val
	
	CMP R2, R3			;if(fromIndex < toIndex)
	BLT whLoopLeftShift
	
whLoopRightShift
	CMP R2, R3			;while(fromIndex >= toIndex)
	BLS moveVal
	LDR R0, =array
	SUB R5, R5, #4
	LDR R4, [R0, R5]	;val = array[index]

	MOV R6, R4			;tempReg = val
	
	ADD R0, R0, R5		;address = address + offset
	ADD R0, R0, #4		;
	
	STR R6, [R0]		;storeVal
	SUB R2, R2, #1		;fromIndex--
	B whLoopRightShift
	
whLoopLeftShift
	CMP R2, R3			;while(fromIndex <= toIndex)
	BHS moveVal
	LDR R0, =array
	ADD R5, R5, #4		;offset++
	LDR R4, [R0, R5]	;val = array[index]

	MOV R6, R4			;tempReg = val
	
	ADD R0, R0, R5		;address = address - offset
	SUB R0, R0, #4		;address -= 4
	
	STR R6, [R0]		;storeVal
	ADD R2, R2, #1		;fromIndex++
	B whLoopLeftShift
	
moveVal
	LDR R0, = array
	LDR R5, =4			;offset = 4
	MUL R5, R3, R5		;offset = offset * indexToMoveTo
	ADD R0, R0, R5		;index + offset
	STR R9, [R0]		;store valToMove
	
stop	B	stop


	AREA	TestArray, DATA, READWRITE

N	equ	9
array	DCD	7,2,5,9,1,3,2,3,4

	END	