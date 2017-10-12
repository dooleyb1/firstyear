/* Declare an Alien */
PImage alienImage;
Alien theAlien;

int xPos = 1;
int yPos = 1;


void setup(){
 alienImage = loadImage("spacer.GIF");
 size(600, 500);
 theAlien = new Alien(xPos, yPos, alienImage);
 frameRate(120);
 
}

void draw(){
  /* clear the screen */
  background(255);
  /* move the alien */
  theAlien.move();
  /* draw the alien */
  theAlien.draw();
  
}