class DoubleBullet{
  
  PImage doubleBulImage;
  int xpos;
  int ypos;
  
  DoubleBullet (PImage image, int x, int y){
   
   doubleBulImage = image;
   xpos = x;
   ypos = y;
 }
 
 void move()
 {
   if(ypos >= 0-doubleBulImage.height && ypos < 720 - doubleBulImage.height/2)
   {
     ypos-=2;
   }
   else
   {
     doubleBulletsRemaining--;
     doubleBulletFire = false;
     ypos = 720 + doubleBulImage.height;
   }
 }
 
 void draw()
 {
   image(doubleBulImage, xpos, ypos);
 }
 
 void collide(Alien[] alienArray)
 {
   for(int i = 0; i<alienArray.length; i++)
   {
     Alien testAlien = alienArray[i];
     if(ypos <= testAlien.y+alienImage.height && (ypos + (doubleBulImage.height)) > testAlien.y && xpos >=testAlien.x && xpos <= testAlien.x + alienImage.width)
     {
       testAlien.explode();
     }
   }
 }
  
}