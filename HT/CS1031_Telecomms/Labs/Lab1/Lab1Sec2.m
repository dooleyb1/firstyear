x = -2*pi:0.005:2*pi;    %x co-ordinates
y = sin(x);              %y co-ordinates
a = randn(1, 2514);      %creates array of random numbers for each x val
a = a*0.2;               %multiplies each element by scale factor 0.2

z = y + a;               %adding random noise to sin(x)
plot(x, z, 'blue');      %plots the graph
axis ([-8 8 -2 2]);