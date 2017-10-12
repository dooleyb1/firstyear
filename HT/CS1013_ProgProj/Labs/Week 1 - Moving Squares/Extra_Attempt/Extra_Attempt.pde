int i=0;

int xPosition1 = -150;
int yPosition1 = 250;

int xPosition2 = 650;
int yPosition2 = 250;

int squareWidth = 50;
int squareHeight = 50;


void setup(){
  
  size(600, 700);
  noStroke(); 
  fill(255, 19, 123);

}

void yPosChange1(){
  
  if(xPosition1 > 0 && xPosition1 <100)
  {
    yPosition1++;
  }
  
   if(xPosition1 > 100 && xPosition1 <200)
  {
    yPosition1--;
  }
  
   if(xPosition1 > 200 && xPosition1 <300)
  {
    yPosition1++;
  }
  
  if(xPosition1 > 300 && xPosition1 <400)
  {
    yPosition1--;
  }
  
  if(xPosition1 > 400 && xPosition1 <500)
  {
    yPosition1++;
  }
  
  if(xPosition1 > 500 && xPosition1 <600)
  {
    yPosition1--;
  }
}

void yPosChange2(){
  
  if(xPosition2 > 0 && xPosition2 <100)
  {
    yPosition2--;
  }
  
   if(xPosition2 > 100 && xPosition2 <200)
  {
    yPosition2++;
  }
  
   if(xPosition2 > 200 && xPosition2 <300)
  {
    yPosition2--;
  }
  
  if(xPosition2 > 300 && xPosition2 <400)
  {
    yPosition2++;
  }
  
  if(xPosition2 > 400 && xPosition2 <500)
  {
    yPosition2--;
  }
  
  if(xPosition2 > 500 && xPosition2 <600)
  {
    yPosition2++;
  }
}

void colorChange(){
  
  if(xPosition1%100 == 0){
    fill(random(255), random(255), random(255));
  }
}
  
void draw(){
 
  frameRate(200);
  background(255);

  yPosChange1();
  yPosChange2();
  colorChange();
  
  //Creates first rectangle
  rect(xPosition1, yPosition1, squareWidth, squareHeight);
  
  //Creates second rectangle for wrap-around
  if(xPosition1>=450){
   rect(xPosition1-600, yPosition1, squareWidth, squareHeight);
  }
  
  //Resets xPosition when rectangle reaches border
  if(xPosition1++>=599){
    xPosition1= 0;
    yPosition1 = 275;
  }
  
  //Creates second rectangle
  rect(xPosition2, yPosition2, squareWidth, squareHeight);
 
  
  //Creates second rectangle for wrap-around
  if(xPosition2<=0){
   rect(xPosition2+600, yPosition2, squareWidth, squareHeight);
  
  }
  
  //Resets xPosition2 when rectangle reaches border
  if(xPosition2--<=-50){
    xPosition2 = 550;
    yPosition2 = 275;
    
  }

}