class bulletBoost{
  
  PImage boostImage;
  int xpos;
  int ypos;
  
  bulletBoost (PImage image, int x, int y){
   
   boostImage = image;
   xpos = x;
   ypos = y;
 }
 
 void move()
 {
   ypos++;
 }
 
 void draw()
 {
   image(boostImage, xpos, ypos);
 }
 
 void collide()
 {
   if(ypos <= thePlayer.ypos+playerImage.height && (ypos + (boostImage.height/8)) > thePlayer.ypos && xpos >=thePlayer.xpos && xpos <= thePlayer.xpos + playerImage.width)
     {
       bulletCatch.play();
       bulletsRemaining++;
       ypos = 720 + boostImage.height;
     }
 }
  
}