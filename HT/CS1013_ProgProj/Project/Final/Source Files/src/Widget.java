import processing.core.PFont;
import processing.core.PApplet;

public class  Widget 
{  
	public static void main(String[] args)
	{
		PApplet.main("Housing");
	}
	final  int  EVENT_NULL=0;
    int  x,  y,  width,  height;  
    String  label;  
    int  event;  
    int  widgetColor,  labelColor, strokeColor, red, green, blue;  
    PFont  widgetFont; 
    PApplet parent;
    boolean selected;
    
    Widget(int  x,int  y,  int  width,  int  height,  String  label,  int widgetColor,  PFont  widgetFont,  int  event, PApplet p)
    {  
        this.x=x;  
        this.y=y;  
        this.width  =  width;  
        this.height=  height;  
        this.label=label;  
        this.event=event;  
        this.selected = false;
        this.widgetColor=widgetColor;  
        this.widgetFont=widgetFont; 
        labelColor=  (0); 
        strokeColor = (100);
        this.parent = p;
    } 
    
    void draw()
    {  
    	parent.stroke(strokeColor);
   
    	if(this.selected)
    		parent.fill(40, 48, 57);
    	else
    		parent.fill(widgetColor);
    	
    	parent.rect(x,y,width,height); 
    	
    	if(this.selected)
    		parent.fill(255);
    	else
    		parent.fill(labelColor);
    	parent.text(label,  x,  y+height-10);  
    }  
    int  getEvent(int  mX,  int  mY)
    {  
          if(mX>x  &&  mX  <  x+width  &&  mY  >y  &&  mY  <y+height){  
                return  event;  
          }  
          return  EVENT_NULL;  
    }
    
    public void setColor(int r, int g, int b)
    {
    	this.widgetColor = 555;
    	this.red = r;
    	this.green = g;
    	this.blue = b;
    }
    public void setColor(int color)
    {
    	this.widgetColor=color;
    }
    public void setStroke(int strokeColor)
    {
      this.strokeColor = strokeColor;
    }
}  