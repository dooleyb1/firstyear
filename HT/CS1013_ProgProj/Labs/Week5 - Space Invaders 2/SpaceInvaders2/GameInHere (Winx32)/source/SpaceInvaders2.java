import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SpaceInvaders2 extends PApplet {

  



public void setup(){
/* create a new alien array */
doubleBulletArray = new Bullet[2];
shootSound = new SoundFile(this, ("shoot.wav"));
explodeSound = new SoundFile(this, ("invaderkilled.wav"));
bulletCatch = new SoundFile(this, ("smb_coin.wav"));
playerDeath = new SoundFile(this, ("explosion.wav"));
alienArray = new Alien[amountOfAliens];
aliensRemaining = amountOfAliens;
doubleBulletsRemaining = 0;
bulletsRemaining = 5;
playerImage = loadImage("player.gif");
backgroundImage = loadImage("background.jpg");
alienCounter = loadImage("alienCounter.gif");
bulletCounter = loadImage("bulletIcon.gif");
doubleBulletImage = loadImage("doubleBulletImage.gif");
bulletBoostImage = loadImage("bulletIcon.gif");
doubleBulletBoosterImage = loadImage("doubleBullet.gif");
thePlayer = new Player(playerImage);
bulletImage = loadImage("bullet.gif");
/* load the images */
alienImage = loadImage("spacer.GIF");
explodeImage = loadImage("exploding.GIF");

frameRate(500);

drawBullet = false;
endGame= false;
doubleBulletFire = false;
startGame= false;
leftPressed= false;
rightPressed= false;
/* initialise the array */
init_aliens(alienArray, alienImage);
}

public void init_aliens (Alien[] alienArray, PImage alienImage){
 /* for each position in the array,  create a new alien at an appropriate
 starting point on the screen */
 int xVal = alienImage.width;
 int yVal = - 2 * alienImage.height - 20;
 
 for(int i=0; i<alienArray.length; i++)
 {
   if(xVal >= (1280 - alienImage.width))
   {
    xVal = alienImage.width;
    yVal -= 2* alienImage.height +10;
   }
   
   alienArray[i] = new Alien(xVal, yVal, alienImage);
   xVal += (2*alienImage.width);
   
 }
}

public void draw(){
  /* clear the screen */
  background(backgroundImage);
  textSize(30);
  /* for each alien in the array - move the alien, then draw the alien */
  
  
  fill(255, 0, 0);
  image(alienCounter, 1000, 665);
  text(aliensRemaining , 1060, 700);
  
  fill(255, 0, 0);
  image(doubleBulletBoosterImage, 1140, 660);
  text(doubleBulletsRemaining , 1180, 700);

  fill(200, 192, 31);
  image(bulletCounter, 1210, 660);
  text(bulletsRemaining , 1240, 700);
  
  if(endGame == true && aliensRemaining > 0)
  {
    fill(255, 0, 0);
    textSize(50);
    text("You Lose!", 550, 350);
  }
  
  if(endGame == true && aliensRemaining == 0)
  {
    fill(255, 0, 0);
    textSize(50);
    text("You Win!", 550, 350);
  }
  
  if(startGame == false && endGame == false)
  {
      fill(255, 0, 0);
      textSize(30);
      text("Press Mouse Key to Shoot", 420, 250);
      text("Press ENTER to Shoot Double Bullets", 350, 290);
      text("Use Arrow Key's to Move", 420, 330);
      text("Kill All the Aliens to Win", 420, 380);
      text("Collect Bullet Drops for more Bullets", 350, 430);
    
      fill(200, 192, 31);
      textSize(50);
      text("Press Space Key to Play", 350, 700);
  }
  
  if(startGame == true && endGame == false)
  {
    if(aliensRemaining == 0)
    {
      endGame = true;
    }
    
    if(drawBullet == true)
    {
      theBullet.draw();
      theBullet.move();
      theBullet.collide(alienArray);
    }
    
    if(doubleBulletFire == true)
    {
      doubleBullet.move();
      doubleBullet.draw();
      doubleBullet.collide(alienArray);
    }
    
    for(int i=0; i<alienArray.length; i++)
    {
      alienArray[i].move();
      alienArray[i].draw();
      thePlayer.draw();
      
      if(alienArray[i].releaseDoubleBullet == true && i%6 == 0)
      {
        alienArray[i].doubleBulletBooster.move();
        alienArray[i].doubleBulletBooster.draw();
        alienArray[i].doubleBulletBooster.collide();
        alienArray[i].collide();
      }
      
      if(alienArray[i].releaseBooster == true && i%4 == 0)
      {
        alienArray[i].booster.move();
        alienArray[i].booster.draw();
        alienArray[i].booster.collide();
        alienArray[i].collide();
      }
    }
    
    if(mousePressed && startGame == true && bulletsRemaining > 0)
    {
      theBullet = new Bullet(bulletImage, thePlayer.xpos, thePlayer.ypos);
      drawBullet = true;
      shootSound.play();
    }
    
    if(leftPressed)
    {
      thePlayer.move(1);
    }
    
    if(rightPressed)
    {
      thePlayer.move(0);
    }
  }
}

public void mouseReleased()
{
  if(bulletsRemaining > 0 && startGame == true)
  {
    bulletsRemaining--;
  }
}

public void keyPressed()
{
  if (key == ' ')
  {
    startGame = !startGame;
  }
  
  if(key == ENTER && doubleBulletsRemaining > 0)
  {
    doubleBulletFire = true; 
    doubleBullet = new DoubleBullet(doubleBulletImage, thePlayer.xpos, thePlayer.ypos);
    shootSound.play();
  }
  
  if (key == CODED) 
  {
    if (keyCode == LEFT) 
    {
      leftPressed = true;
    }
    if (keyCode == RIGHT)
    {
      rightPressed = true;
    }
  }
}

public void keyReleased() {
  if (key == CODED)
  {
    if (keyCode == LEFT) 
    {
      leftPressed = false;
    }
    else if (keyCode == RIGHT)
    {
      rightPressed = false;
    }
  }
}
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
  
 public void move(){
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
 
  
  public void draw(){
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

 public void collide()
 {
   if(y <= thePlayer.ypos+playerImage.height && (y + (alienImage.height/8)) > thePlayer.ypos && x >=thePlayer.xpos && x <= thePlayer.xpos + playerImage.width)
   {
     endGame = true;
   }
 }

 public void explode(){
   explodeSound.play();
   imageToUse = explodeImage;
 }
}
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
 
 public void draw()
 {
   image(bulletImage, bulletXpos, bulletYpos);
 }
 
 public void collide(Alien[] alienArray)
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
 
 public void move()
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
Alien[] alienArray;
Bullet[] doubleBulletArray;
Player thePlayer;
Bullet theBullet;
DoubleBullet doubleBullet;
SoundFile shootSound;
SoundFile explodeSound;
SoundFile bulletCatch;
SoundFile playerDeath;
PImage alienImage;
PImage bulletCounter;
PImage doubleBulletBoosterImage;
PImage playerImage;
PImage bulletImage;
PImage doubleBulletImage;
PImage backgroundImage;
PImage alienCounter;
PImage bulletBoostImage;
PImage explodeImage;
PImage disappearImage;
boolean drawBullet;
boolean drawDoubleBullet;
boolean doubleBulletFire;
boolean endGame;
boolean startGame;
boolean leftPressed;
boolean rightPressed;
int xPos = 1;
int yPos = 50;
int aliensRemaining;
int bulletsRemaining;
int doubleBulletsRemaining;
int amountOfAliens =100;
int SCREEN_X=1280;
int SCREEN_Y=720;
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
 
 public void draw()
 {
   image(spacePlayerImage, xpos, ypos);
 }
 
 public void move(int direction)
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
 
 public void collide(Alien[] alienArray)
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
class bulletBoost{
  
  PImage boostImage;
  int xpos;
  int ypos;
  
  bulletBoost (PImage image, int x, int y){
   
   boostImage = image;
   xpos = x;
   ypos = y;
 }
 
 public void move()
 {
   ypos++;
 }
 
 public void draw()
 {
   image(boostImage, xpos, ypos);
 }
 
 public void collide()
 {
   if(ypos <= thePlayer.ypos+playerImage.height && (ypos + (boostImage.height/8)) > thePlayer.ypos && xpos >=thePlayer.xpos && xpos <= thePlayer.xpos + playerImage.width)
     {
       bulletCatch.play();
       bulletsRemaining++;
       ypos = 720 + boostImage.height;
     }
 }
  
}
class DoubleBullet{
  
  PImage doubleBulImage;
  int xpos;
  int ypos;
  
  DoubleBullet (PImage image, int x, int y){
   
   doubleBulImage = image;
   xpos = x;
   ypos = y;
 }
 
 public void move()
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
 
 public void draw()
 {
   image(doubleBulImage, xpos, ypos);
 }
 
 public void collide(Alien[] alienArray)
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
class DoubleBulletBooster{
  
  PImage doubleImage;
  int xpos;
  int ypos;
  
  DoubleBulletBooster (PImage image, int x, int y){
   
   doubleImage = image;
   xpos = x;
   ypos = y;
 }
 
 public void move()
 {
   ypos++;
 }
 
 public void draw()
 {
   image(doubleImage, xpos, ypos);
 }
 
 public void collide()
 {
   if(ypos <= thePlayer.ypos+playerImage.height && (ypos + (doubleImage.height/8)) > thePlayer.ypos && xpos >=thePlayer.xpos && xpos <= thePlayer.xpos + playerImage.width)
   {
     bulletCatch.play();
     doubleBulletsRemaining++;
     ypos = 720 + doubleImage.height;
   }
 }
 }
 
  public void settings() { 
size(1280, 720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "SpaceInvaders2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
