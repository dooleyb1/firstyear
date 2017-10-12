[signal, fsampling]= audioread('exercise2_piece.wav');

N = fsampling;

newX = -fs/2:fs/N:fs/2-fs/N;

carrier_frequency = 30000;

sampling_rate=90000;

y = ammod(signal,carrier_frequency,N);

y = y + 0.1 * randn();

y = amdemod(y, carrier_frequency, sampling_rate);


