class Bullet{
  
  PImage bulletImage;
  int bulletXpos;
  int bulletYpos;
  int doubleEndChecker; 
  
 Bullet(PImage image, int x, int y){
   
   bulletImage = image;
   bulletXpos = x + (playerImage.width/2);
   bulletYpos = y;
   doubleEndChecker = 0;
 }
 
 void draw()
 {
   image(bulletImage, bulletXpos, bulletYpos);
 }
 
 void collide(Alien[] alienArray)
 {
   for(int i = 0; i<alienArray.length; i++)
   {
     Alien testAlien = alienArray[i];
     if(bulletYpos <= testAlien.y+alienImage.height && (bulletYpos + (bulletImage.height/8)) > testAlien.y && bulletXpos >=testAlien.x && bulletXpos <= testAlien.x + alienImage.width)
     {
       testAlien.explode();
       println("1");
     }
   }
 }
 
 void move()
 {
   if(bulletYpos >= 0-bulletImage.height && bulletYpos < 720 - bulletImage.height/2)
   {
     bulletYpos-=2;
   }
   else
   {
     drawBullet = false;
   }
 }
}