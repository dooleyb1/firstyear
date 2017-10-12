  
import processing.sound.*;


void setup(){
/* create a new alien array */
doubleBulletArray = new Bullet[2];
shootSound = new SoundFile(this, ("shoot.wav"));
explodeSound = new SoundFile(this, ("invaderkilled.wav"));
bombExplode = new SoundFile(this, ("bombExplode.wav"));
bulletCatch = new SoundFile(this, ("smb_coin.wav"));
playerDeath = new SoundFile(this, ("explosion.wav"));
alienArray = new Alien[amountOfAliens];
shieldArray = new Shield[3];
aliensRemaining = amountOfAliens;
doubleBulletsRemaining = 0;
bulletsRemaining = 50;
theBombImage = loadImage("bombImage.gif");
playerImage = loadImage("player.gif");
shieldImage = loadImage("barrier.gif");
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
size(1280, 720);
frameRate(500);

drawBullet = false;
endGame= false;
doubleBulletFire = false;
startGame= false;
leftPressed= false;
rightPressed= false;
/* initialise the array */
init_aliens(alienArray, alienImage);
init_shields(shieldArray, shieldImage);
}


void init_aliens (Alien[] alienArray, PImage alienImage){
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

void init_shields(Shield[] array, PImage image)
{
  int x =190;
  int y = 510;
  
  for(int i =0; i<array.length; i++)
  {
    array[i] = new Shield(image, x, y);
    x += (image.width*2);
  }
}

void draw(){
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
    text("Game Over!", 530, 350);
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
      theBullet.move();
      theBullet.draw();
      theBullet.collide(alienArray);
      theBullet.shieldCollide(shieldArray);
    }
    
    if(doubleBulletFire == true)
    {
      doubleBullet.move();
      doubleBullet.draw();
      doubleBullet.collide(alienArray);
      doubleBullet.shieldCollide(shieldArray);
    }
    
    for(int i=0; i<shieldArray.length; i++)
    {
      if(shieldArray[i].lives > 0)
      {
        shieldArray[i].draw();
      }
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
      
      if(alienArray[i].releaseBooster == true && i%6 == 0)
      {
        alienArray[i].booster.move();
        alienArray[i].booster.draw();
        alienArray[i].booster.collide();
        alienArray[i].collide();
      }
      
      if(alienArray[i].releaseBomb == true && i%6 == 0)
      {
         alienArray[i].bomb.move();
         alienArray[i].bomb.draw();
         alienArray[i].bomb.collide();
         alienArray[i].bomb.shieldCollide(shieldArray);
         alienArray[i].collide();
        }
      }
    }
    
    if(mousePressed && startGame == true && endGame == false && bulletsRemaining > 0)
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

void mouseReleased()
{
  if(bulletsRemaining > 0 && startGame == true)
  {
    bulletsRemaining--;
  }
}

void keyPressed()
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

void keyReleased() {
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