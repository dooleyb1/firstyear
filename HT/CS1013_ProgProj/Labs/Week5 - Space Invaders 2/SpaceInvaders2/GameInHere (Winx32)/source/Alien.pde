final int A_FORWARD = 0;
final int A_BACKWARD = 1;
final int A_DOWN = 2;

class Alien {
 /* declare variables for alien position, direction of movement and appearance */
 int x;
 int y;
 int dx;
 int dy;
 int boostX;
 int boostY;
 int explodeCounter;
 int downCounter;
 int boostCounter;
 int doubleBulletCounter;
 int doubleBulletChecker;
 boolean releaseDoubleBullet;
 int boostChecker;
 boolean releaseBooster;
 boolean stop = false;
 int prevDirection;
 PImage imageToUse;
 bulletBoost booster;
 DoubleBulletBooster doubleBulletBooster;

 /* Constructor is passed the x and y position where the alien is to
 be created, plus the image to be used to draw the alien */
 
 Alien(int xpos, int ypos, PImage alienImage){
  /* set up the new alien object */ 
  x = xpos;
  y = ypos;
  dx = 1;
  dy = 0;
  explodeCounter = 0;
  doubleBulletCounter = (int) random(3000, 14000);
  doubleBulletChecker = 0;
  boostCounter = (int) random(2000,14000);
  boostChecker = 0;
  downCounter = 3* alienImage.height;
  prevDirection = A_BACKWARD;
  imageToUse = alienImage;
  releaseDoubleBullet = false;
  releaseBooster = false;
 }
  
 void move(){
  /* Move the alien according to the instructions in the exercise */  
  
  if(stop == false)
  {
  if(doubleBulletChecker == doubleBulletCounter)
  {
    releaseDoubleBullet = true;
    doubleBulletBooster = new DoubleBulletBooster (doubleBulletBoosterImage, x, y);
  }
  if(boostChecker == boostCounter)
  {
    releaseBooster = true;
    booster = new bulletBoost(bulletBoostImage, x, y);
  }
  if(downCounter == 0 && prevDirection == A_BACKWARD)
  {
    dx=-1; 
    dy=0;
    prevDirection = A_FORWARD;
    downCounter = alienImage.height;
  }
  
  else if(downCounter == 0 && prevDirection == A_FORWARD)
  {
    dx=1;
    dy=0;
    prevDirection = A_BACKWARD;
    downCounter = alienImage.height;
  }
  
  x+=dx;
  y+= dy;
  boostChecker++;
  doubleBulletChecker++;
  
  if(x == SCREEN_X-alienImage.width)
  {
    dy=1;
    dx=0;
    downCounter--;
  }
  
  if(x == 0)
  {
    dy=1;
    dx=0;
    downCounter--;
  }
  }  
 }
 
  
  void draw(){
    /* Draw the alien using the image() method demonstrated in class */
    if(imageToUse == explodeImage && explodeCounter < 60)
    {
      explodeCounter ++;
    }
    else if(explodeCounter == 60)
    {
      x = -120;
      dx = 0;
      y = -120;
      dy = 0;
      explodeCounter++;
      aliensRemaining--;
    }
    
    image(imageToUse, x, y);  
}

 void collide()
 {
   if(y <= thePlayer.ypos+playerImage.height && (y + (alienImage.height/8)) > thePlayer.ypos && x >=thePlayer.xpos && x <= thePlayer.xpos + playerImage.width)
   {
     endGame = true;
   }
 }

 void explode(){
   explodeSound.play();
   imageToUse = explodeImage;
 }
}