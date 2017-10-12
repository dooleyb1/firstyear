PFont stdFont;
final int EVENT_BUTTON1=1;
final int EVENT_BUTTON2=2;
final int EVENT_NULL=0;
final int widgetWidth = 100;
final int widgetHeight = 40;
final color red = color(255, 0, 0);
final color green = color(0, 255, 0);
final color blue = color(0, 0, 255);
Screen screen1, screen2, currentScreen;

void setup()
{
  stdFont=loadFont("Arial-Black-18.vlw");
  screen1 = new Screen(color(55, 200, 130));
  screen2 = new Screen(color(100, 10, 240));
  currentScreen = screen1;
  textFont(stdFont);
  size(600, 800);
}

void draw()
{
  currentScreen.draw();
}

void mousePressed(){
  int event;
  
  for(int i = 0; i<currentScreen.widgetList.size(); i++)
  {
    Widget aWidget = (Widget) currentScreen.widgetList.get(i);
    event = aWidget.getEvent(mouseX,mouseY);
    
    switch(event) 
    {
      case EVENT_BUTTON2:
      println("button 2!");
      break;
      case EVENT_BUTTON1:
      if(currentScreen == screen1)
      {
        currentScreen = screen2;
      }
      else
      {
         currentScreen = screen1; 
      }
      break;
    }
  }
}