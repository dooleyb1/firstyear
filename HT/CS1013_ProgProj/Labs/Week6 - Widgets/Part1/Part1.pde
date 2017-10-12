PFont stdFont;
final int EVENT_BUTTON1=1;
final int EVENT_BUTTON2=2;
final int EVENT_BUTTON3=3;
final int EVENT_NULL=0;
final color red = color(255, 0, 0);
final color green = color(0, 255, 0);
final color blue = color(0, 0, 255);
color squareColor;
Widget widget1, widget2, widget3;

void setup()
{
  stdFont=loadFont("Arial-Black-18.vlw");
  textFont(stdFont);
  squareColor = color(255, 255, 255);
  widget1=new Widget(100, 100, 100, 40, "Red!", red, stdFont, EVENT_BUTTON1);
  widget2=new Widget(100, 200, 100, 40, "Green!", green, stdFont, EVENT_BUTTON2);
  widget3=new Widget(100, 300, 100, 40, "Blue!", blue, stdFont, EVENT_BUTTON3);
  size(600, 800);
}

void draw()
{
  widget1.draw();
  widget2.draw();
  widget3.draw();
  fill(squareColor);
  rect(300, 500, 100, 100);
}

void mousePressed()
{
  int event;
  event = widget1.getEvent(mouseX,mouseY);
  
  if(event== EVENT_BUTTON1)
  {
    println("Red button pressed");
    squareColor = red;
  }
  else 
  {
    event = widget2.getEvent(mouseX,mouseY);
    if(event==EVENT_BUTTON2)
    {
      println("Green button pressed");
      squareColor = green;
    }
    else
    {
      println("Blue button pressed");
      squareColor = blue;
    }
  }
}