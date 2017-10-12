class DoubleBulletBooster{
  
  PImage doubleImage;
  int xpos;
  int ypos;
  
  DoubleBulletBooster (PImage image, int x, int y){
   
   doubleImage = image;
   xpos = x;
   ypos = y;
 }
 
 void move()
 {
   ypos++;
 }
 
 void draw()
 {
   image(doubleImage, xpos, ypos);
 }
 
 void collide()
 {
   if(ypos <= thePlayer.ypos+playerImage.height && (ypos + (doubleImage.height/8)) > thePlayer.ypos && xpos >=thePlayer.xpos && xpos <= thePlayer.xpos + playerImage.width)
   {
     bulletCatch.play();
     doubleBulletsRemaining++;
     ypos = 720 + doubleImage.height;
   }
 }
 }
 