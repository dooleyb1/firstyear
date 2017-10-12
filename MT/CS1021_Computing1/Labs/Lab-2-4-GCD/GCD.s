	AREA	GCD, CODE, READONLY
	IMPORT	main
	EXPORT	start

start
	LDR R2, =0x00000012	;load a
	LDR R3, =0x0000002A	;load b
	LDR R0, =0			;reset R0
	
while				
	CMP R2, R3		;while(a != b)
	BEQ endwh		;{
	BLO	true		;if(a > b){
	SUB R2, R2, R3	;a = a - b
	B while			;}
true				;else{
	SUB R3, R3, R2	;b = b -a
	B while			;}
endwh				;}
	MOV R0, R2		;set R0 as the result
stop	B	stop

	END	