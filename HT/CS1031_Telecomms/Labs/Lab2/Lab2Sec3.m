coeffecient = 4/pi;
subPlotIndex = 1; 
for i=[1,3,5,10,50,500]                  %iterates through no. of sin waves
    amplitude = 0; 
    xVal = 1;
    for k=1:2:i*2
          amplitude = coeffecient/k;
          subplot (3 ,2 ,subPlotIndex);
          stem(xVal,amplitude);
          xVal = xVal + 1;
          hold on; 
    end
    str = sprintf('Approximation with %i sine functions', i);
    title(str);
    subPlotIndex = subPlotIndex +1;
end;

