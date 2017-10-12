	AREA	Flags2, CODE, READONLY
	IMPORT	main
	EXPORT	start

start

	LDR R1, =0x10000001
	LDR R2, =2_0001
	ADDS R0, R1, R2

stop	B	stop

	END	