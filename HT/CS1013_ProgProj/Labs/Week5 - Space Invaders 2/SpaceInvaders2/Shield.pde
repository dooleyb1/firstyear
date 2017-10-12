class Shield{
 
  PImage shieldImage;
  int xpos;
  int ypos;
  int lives;
  
  Shield(PImage image, int x, int y)
  {
    shieldImage = image;
    xpos = x;
    ypos = y;
    lives = 5;
  }
  
  void damageShield()
  {
   switch(lives)
   {
    case 5:
    shieldImage = loadImage("barrier.gif");
    return;
    
    case 4:
    shieldImage = loadImage("barrier2.gif");
    return;
    
    case 3:
    shieldImage = loadImage("barrier3.gif");
    return;
    
    case 2:
    shieldImage = loadImage("barrier4.gif");
    return;
    
    case 1:
    shieldImage = loadImage("barrier5.gif");
    return;
    
    default:
    return;
   }
  }
  
  void draw()
  {
    if(lives >= 1)
    {
     image(shieldImage, xpos, ypos); 
    }
  }
  
}