class Ball {
  
float x; 
float y;
float dx; 
float dy;
float timeIncrement= 1.001;
float radius;
color ballColor = color(200, 100, 50);

Ball(){
  
x = 500;
y = 500;

//These are the speed values of the ball
dx = random(5, 10);
dy = random(5, 10);

radius=30;

}

void move(){

//This allows the speed to increase over time
dx*= timeIncrement;
dy*= timeIncrement;

//This changes the x and y position of the ball based on the dx dy values
x = x+dx; 
y = y+dy;

//This decreases playerLives when ball goes below the screen and calls reset()
if(y >= SCREENY)
{
  thePlayer.playerLives--;
  reset();
}

//This decreases computerLives when ball goes above the screen and calls reset()
if(y <= 0)
{
  computerPlayer.computerLives--;
  reset();
}
}

void reset()
{
   gameStart = false;
   x = 150;
   y = 110;
   dx = 0;
   dy = 0;
}

void draw(){
  
fill(ballColor);
ellipse(int(x), int(y), radius, radius);

}

void collide(Player tp){
  
//Checks collision with left wall
if(x<= 0)
{
  dx= dx * -1;
}

//Checks collision with right wall
if(x+radius>=SCREENX) 
{
  dx= dx * -1;
}

//This resets ball if it goes off the top of the screen
if(y<0)
{
  reset();
  tp.computerLives--;
}

//This resets ball if it goes off the bottom of the screen
if(y>SCREENY)
{
  reset();
  tp.playerLives--;
}

//Checks if ball collides with bottom paddle
if(y+radius >= tp.ypos && (y-radius)<tp.ypos && x >=tp.xpos && x <= tp.xpos + PADDLEWIDTH)
{
  dy= dy * -1;
  dx= dx * tp.directionChecker;
}
}

void computerCollide(Player compPlayer)
{
  //Checks if ball collides with top paddle
  if((y-radius) <= compPlayer.ypos+PADDLEHEIGHT-5 && (y+radius) > compPlayer.ypos && x>=compPlayer.xpos && x <= compPlayer.xpos + PADDLEWIDTH)
{ 
  dy = dy * -1;
}
}

}