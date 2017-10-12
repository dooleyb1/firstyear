class Screen{
  
ArrayList widgetList;
color backgroundColor;
int screenX = 600;
int screenY = 800;
Widget widget1, widget2;

Screen(color inputColor)
{
 backgroundColor = inputColor;
 widgetList = new ArrayList();
 widget1=new Widget(100, 100, widgetWidth, widgetHeight, "Forward", red, stdFont, EVENT_BUTTON1);
 widget2=new Widget(100, 200, widgetWidth+40, widgetHeight, "Other Button", green, stdFont, EVENT_BUTTON2);
 widgetList = new ArrayList();
 addWidget(widget1, widgetList);
 addWidget(widget2, widgetList);
}

void draw()
{
  background(backgroundColor);
  for(int i = 0; i<widgetList.size(); i++)
  { 
    Widget aWidget = (Widget) widgetList.get(i);
    aWidget.draw();
  }
}

void addWidget(Widget widgetToAdd, ArrayList widgetList)
{
  widgetList.add(widgetToAdd);
}


}