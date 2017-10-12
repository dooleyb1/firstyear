/* Declare an array of Aliens */
Alien[] alienArray;
PImage alienImage;

int xPos = 1;
int yPos = 1;

void setup(){
/* create a new alien array */
alienArray = new Alien[10];
/* load the images */
alienImage = loadImage("spacer.GIF");
size(600, 500);
frameRate(500);
/* initialise the array */
init_aliens(alienArray, alienImage);
}

void init_aliens (Alien[] alienArray, PImage alienImage){
 /* for each position in the array,  create a new alien at an appropriate
 starting point on the screen */
 for(int i=0; i<alienArray.length; i++)
 {
   alienArray[i] = new Alien((i*2*alienImage.width), yPos, alienImage);
 }
}

void draw(){
  /* clear the screen */
  background(255);
  /* for each alien in the array - move the alien, then draw the alien */
  for(int i=0; i<alienArray.length; i++)
  {
    alienArray[i].move();
    alienArray[i].draw();
  }

}