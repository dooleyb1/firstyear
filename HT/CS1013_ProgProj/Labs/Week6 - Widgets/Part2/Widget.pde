class Widget {
  
int x, y, width, height;
String label; 
int event;
color widgetColor, labelColor, strokeColor;
PFont widgetFont;
boolean hover;

Widget(int x,int y, int width, int height, String label, color widgetColor, PFont widgetFont, int event)
{
  this.x=x;
  this.y=y; 
  this.width = width; 
  this.height= height;
  this.label=label; 
  this.event=event;
  this.widgetColor=widgetColor;
  this.widgetFont=widgetFont;
  labelColor= color(0);
  strokeColor = color(255);
  hover = false;
}

void draw()
{
  if(hover == true)
  {
    strokeColor = hoverStroke;
  }
  else
  {
    strokeColor = normalStroke;
  }
  fill(widgetColor);
  stroke(strokeColor);
  rect(x,y,width,height);
  fill(labelColor);
  text(label, x+10, (y+height-10));
}

int getEvent(int mX, int mY)
{
  if(mX>x && mX < x+width && mY >y && mY <y+height)
  {
  return event;
  }
  else
  {
  return EVENT_NULL;
  }
}
}