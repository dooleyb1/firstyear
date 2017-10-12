[signal, fsampling]= audioread('exercise1_piece.wav');
N = fsampling;

sound(signal);

F = fftshift(abs(fft(signal, N)));

newX = -fs/2:fs/N:fs/2-fs/N;

subplot(3, 1, 1);

plot(newX, F);

string = sprintf('Unmodulated signal spectrum');

title(string);

hold on;

carrier_frequency = 30000;

sampling_rate=90000;

amplitude_modulated_signal=ammod(signal, carrier_frequency, sampling_rate);

F2 = fftshift(abs(fft(amplitude_modulated_signal, N)));

subplot(3, 1, 2);

plot(newX, F2);

string2 = sprintf('AM spectrum');

title(string2);

hold on;

frequency_deviation = 10000;

frequency_modulated_signal=fmmod(signal, carrier_frequency,sampling_rate, frequency_deviation);

F3 = fftshift(abs(fft(frequency_modulated_signal, N)));

subplot(3, 1, 3);

plot(newX, F3);

string3 = sprintf('FM spectrum');

title(string3);

hold on;