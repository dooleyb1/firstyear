final int A_FORWARD = 0;
final int A_BACKWARD = 1;
final int A_DOWN = 2;

class Alien {
 /* declare variables for alien position, direction of movement and appearance */
 int xpos;
 int ypos;
 int dx = 1;
 int dy = 0;
 int downCounter = alienImage.height;
 int prevDirection = A_BACKWARD;
 int startYPos = alienImage.height;
 /* Constructor is passed the x and y position where the alien is to
 be created, plus the image to be used to draw the alien */
 
 Alien(int xpos, int ypos, PImage alienImage){
  /* set up the new alien object */ 
 }
  
 void move(){
  /* Move the alien according to the instructions in the exercise */   
  
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
  
  xpos+=dx;
  ypos += dy;
  
  if(xpos == SCREEN_X-alienImage.width)
  {
    dy=1;
    dx=0;
    downCounter--;
  }
  
  if(xpos == 0)
  {
    dy=1;
    dx=0;
    downCounter--;
  }
     
 }
 
  
  void draw(){
    /* Draw the alien using the image() method demonstrated in class */
    image(alienImage, xpos, ypos);    
}



}