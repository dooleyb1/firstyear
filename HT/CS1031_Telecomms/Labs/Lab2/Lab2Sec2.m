
t = -1:0.01:1;                           %x-axis
c = -1:0.25:1;
y = sawtooth(2*pi*(t+0.25),0.5);
coEffecient = (8/(pi^2));                    %Fourier co-effecient
subPlotIndex = 1;                        %sub-plot index
for i=[1,2,3,5,10,50]                  %iterates through no. of sin waves
    sum = 0;                                
    for k=0:1:i-1
    sum = sum + ((-1)^k) * (sin(2*pi*t*((2*k)+1))/((2*k)+1)^2);     %sums up the Fourier series to k
    end;
    h = coEffecient * sum;               %8/pi^2 * sum
    subplot (3 ,2 ,subPlotIndex);        %plots Fourier wave vs Square sin
    plot(t,y);
    hold on;
    plot(t,h);
    str = sprintf('Approximation with %i sine functions', i);
    title(str);
    axis ([-1 1 -1 1]);
    subPlotIndex = subPlotIndex +1;      %increases subplot index
end;
