fs = 1000; %sample rate
time = 2; 
x = (0:1/fs:time-1/fs); %x-axis
S = load('array.mat');
%N = 1024;
F = fftshift(abs(fft(y,N)));
newX = -fs/2:fs/N:fs/2-fs/N;
plot(newX, F);