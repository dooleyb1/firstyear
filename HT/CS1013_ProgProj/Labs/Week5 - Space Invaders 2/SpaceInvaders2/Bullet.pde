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
       testAlien.stop = true;
       println("1");
     }
   }
 }
 
  void shieldCollide(Shield[] array)
 {
   for(int i=0; i<array.length; i++)
   {
     if(drawBullet == true)
     {
       if(bulletYpos <= array[i].ypos+array[i].shieldImage.height && (bulletYpos + (bulletImage.height/2)) > array[i].ypos && bulletXpos >=array[i].xpos && bulletXpos <= array[i].xpos + array[i].shieldImage.width)
       {
         bulletYpos = -100;
         bulletsRemaining--;
         bombExplode.play();
         array[i].lives--;
         array[i].damageShield();
       }
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