PFont stdFont;
final int EVENT_BUTTON1=1;
final int EVENT_BUTTON2=2;
final int EVENT_BUTTON3=3;
final int EVENT_NULL=0;
final int widgetWidth = 100;
final int widgetHeight = 40;
final color red = color(255, 0, 0);
final color green = color(0, 255, 0);
final color blue = color(0, 0, 255);
color squareColor;
color normalStroke= color(0);
color hoverStroke = color(255);
Widget widget1, widget2, widget3;

void setup()
{
  stdFont=loadFont("Arial-Black-18.vlw");
  textFont(stdFont);
  squareColor = color(255, 255, 255);
  widget1=new Widget(100, 100, widgetWidth, widgetHeight, "Red!", red, stdFont, EVENT_BUTTON1);
  widget2=new Widget(100, 200, widgetWidth, widgetHeight, "Green!", green, stdFont, EVENT_BUTTON2);
  widget3=new Widget(100, 300, widgetWidth, widgetHeight, "Blue!", blue, stdFont, EVENT_BUTTON3);
  size(600, 800);
}

void draw()
{
  widget1.draw();
  widget2.draw();
  widget3.draw();
  stroke(normalStroke);
  fill(squareColor);
  rect(300, 500, 100, 100);
}

void mouseMoved()
{
  
  if(mouseX>=widget3.x && mouseX <= widget3.x+widgetWidth && mouseY >=widget3.y && mouseY <=widget3.y+widgetHeight)
  {
    widget3.hover = true;
  }
  else if(mouseX>=widget2.x && mouseX <= widget2.x+widgetWidth && mouseY >=widget2.y && mouseY <=widget2.y+widgetHeight)
  {
    widget2.hover = true;
  }
  else if(mouseX>=widget1.x && mouseX <= widget1.x+widgetWidth && mouseY >=widget1.y && mouseY <=widget1.y+widgetHeight)
  {
    widget1.hover = true;
  }
  
  else
  {
    widget1.hover = false;
    widget2.hover = false;
    widget3.hover = false;
  }
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