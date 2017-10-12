t = -1:0.01:1;                           %x-axis
y = square(2*pi*t);
coEffecient = (4/pi);                    %Fourier co-effecient
subPlotIndex = 1;                        %sub-plot index
for i=[1,3,5,10,50,500]                  %iterates through no. of sin waves
    sum = 0;                                
    for k=1:2:i*2
    sum = sum +((sin(2*pi*k*t))/k);      %sums up the Fourier series to k
    end;
    h = coEffecient * sum;               %4/pi * sum
    subplot (3 ,2 ,subPlotIndex);        %plots Fourier wave vs Square sin
    plot(t,y);
    hold on;
    plot(t,h);
    str = sprintf('Approximation with %i sine functions', i);
    title(str);
    axis ([-1 1 -2 2]);
    subPlotIndex = subPlotIndex +1;      %increases subplot index
end;
