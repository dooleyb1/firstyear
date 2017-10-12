x =-2*pi:0.1:2*pi;          % sets up the array of x-increments
y= cos (x);                 % sine function
y2=0.5*(sin (x));           % cosine function

plot (x,y, 'black');        % plot sine function
hold on;                    % holds onto the previous plot
stem (x,y2 ,'r');           % the command 'r' tells to plot in red color
axis ([-8 8 -1 1])          % extends the axis on both sides