fs=100;                         %sampling frequency [Hz]
frequency=10;                   %frequency of sine wave [Hz]
time=2;                         %how many seconds to consider
x=0:1/fs:time-1/fs;             %points in x axis
y=sin(2*pi*x*frequency);        %your signal
subPlotIndex = 1;

for N=[64, 128,256]                  %iterates through vals of N
    F=fftshift(abs(fft(y,N)));
    newX = -fs/2:fs/N:fs/2-fs/N;
                                
    subplot (3 ,2 ,subPlotIndex);        %plots Fourier wave vs Square sin
    plot(newX, F);
    hold on;
    subPlotIndex = subPlotIndex +1;      %increases subplot index
end;