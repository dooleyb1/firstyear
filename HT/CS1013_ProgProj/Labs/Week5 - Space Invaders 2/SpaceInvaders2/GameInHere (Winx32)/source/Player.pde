class Player{
  
  PImage spacePlayerImage;
  int xpos;
  int ypos;
  int leftDirection = 1;
  int rightDirection = 0;
  
 Player (PImage playerImage){
   
   spacePlayerImage = playerImage;
   xpos = (1280-spacePlayerImage.width)/2;
   ypos = 720- (spacePlayerImage.height + 60);
 }
 
 void draw()
 {
   image(spacePlayerImage, xpos, ypos);
 }
 
 void move(int direction)
 {
   if(direction == leftDirection)
   {
     if(xpos > 0)
     {
       xpos-=2;
     }
   }
   else if(direction == rightDirection)
   {
     if(xpos < 1280 - spacePlayerImage.width)
     {
       xpos+=2;
     }
   }
 }
 
 void collide(Alien[] alienArray)
 {
    for(int i = 0; i<alienArray.length; i++)
   {
     Alien testAlien = alienArray[i];
     if(ypos <= testAlien.y+alienImage.height && (ypos + (bulletImage.height/8)) > testAlien.y && xpos >=testAlien.x && xpos <= testAlien.x + alienImage.width)
     {
       explodeSound.play();
       endGame = true;
     }
   }
 }
 
}