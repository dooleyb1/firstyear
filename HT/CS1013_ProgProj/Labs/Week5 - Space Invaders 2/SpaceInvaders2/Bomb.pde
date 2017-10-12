class Bomb{
  
  PImage bombImage;
  int xpos;
  int ypos;
  
  Bomb(PImage imageToUse, int x, int y)
  {
    bombImage = imageToUse;
    xpos = x;
    ypos = y;
  }
  
  void move()
  {
    ypos++;
  }
  
  void draw()
  {
    image(bombImage, xpos, ypos);
  }
  
  boolean offScreen()
  {
    boolean result = false;
    if(ypos >= 720 - (bombImage.height/2))
    {
       result = true; 
    }
    return result;
  }
  
 void collide()
 {
   if(ypos <= thePlayer.ypos+playerImage.height && (ypos + (bombImage.height/2)) > thePlayer.ypos && xpos >=thePlayer.xpos && xpos <= thePlayer.xpos + playerImage.width)
     {
       bombExplode.play();
       endGame = true;
       ypos = 720 + bombImage.height;
     }
 }
 
 void shieldCollide(Shield[] array)
 {
   for(int i=0; i<array.length; i++)
   {
     if(ypos <= array[i].ypos+array[i].shieldImage.height && (ypos + (bombImage.height/2)) > array[i].ypos && xpos >=array[i].xpos && xpos <= array[i].xpos + array[i].shieldImage.width)
     {
       ypos = 730;
       bombExplode.play();
       array[i].lives--;
       array[i].damageShield();
     }
   }
 }
}