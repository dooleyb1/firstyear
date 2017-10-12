[notes,fs] = audioread('exercise notes.wav');

plot(notes);     %plot musical notes
note1 = notes(1:6000);  %seperate note 1 up to x= 6000
N = 10000;             %sample rate
sound(note1);           %play note 1
F = abs(fft(note1, N)); %fft of note 1
newX = -fs/2:fs/N:fs/2-fs/N;   %adjusted x-axis
subplot(3,2,1);
plot(newX, F);          %plot the fft of note1
hold on;

note2 = notes(6001:10001);  %seperate note 2 from x=6000 onwards
sound(note2);          %play note 2
F2 = abs(fft(note2, N));    %fft of note 2
newX2 =  -fs/2:fs/N:fs/2-fs/N;  %adjusted x-axis
subplot(3,2,2);
plot(newX2, F2);    %plot the fft of note2

