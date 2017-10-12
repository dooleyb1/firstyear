import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.io.File;
import java.util.ArrayList;


public class Housing extends PApplet {
	static final int x = 1000;
	static final int y = 700;
		
	String[] rawDataArray;
	String[] rawCountyNamesArray;
	String[] countyStatsArray;
	String[] pointInfoArray;
	String[] countyNamesArray;
	ArrayList<DataPoint> dataPointsArray;
	ArrayList<County> countiesArray;
	PictogramChart thePictogram;
	PImage pictogramImage1;
	PImage pictogramImage2;
	PImage pictogramImage3;
	PImage scanBarImage;
	PImage logoImage;
	PImage helpImage;
	PImage helpX;
	PImage cornerLogo;
	PImage background;
	PImage helpText;
	Chart theChart;
	Chart barChart;
	public double avg;
	public double min;
	public double max;
	public boolean drawHomeScreen = true;
	public boolean drawScanBar = true;
	public boolean click = false;
	public final int SCAN_WIDGET_XPOS = 100;
	public final int SCAN_WIDGET_YPOS = 750;
	PFont aFont;
	PApplet p = this;
	boolean drawGraph = false;
	boolean drawMap = false;
	boolean pictogram = false;
	boolean overTheYears = false;
	boolean stats = false;
	boolean help = false;

	static int SCREEN_HEIGHT = 985;
	static int SCREEN_WIDTH = 699;

	static int averagePrice =5000;
	public int minYear;
	public int maxYear;
	final int EVENT_BUTTON1 = 1; final int EVENT_BUTTON2 = 2; final int EVENT_BUTTON3 = 3;
	final int EVENT_BUTTON4 = 4; final int EVENT_BUTTON5 = 5; final int EVENT_BUTTON6 = 6;
	final int EVENT_BUTTON7 = 7; final int EVENT_BUTTON8 = 8; final int EVENT_BUTTON9 = 9;
	final int EVENT_BUTTON10 = 10; final int EVENT_BUTTON11 = 11; final int EVENT_BUTTON12 = 12;
	final int EVENT_BUTTON13 = 13; final int EVENT_BUTTON14 = 14; final int EVENT_BUTTON15 = 15;
	final int EVENT_BUTTON16 = 16; final int EVENT_BUTTON17 = 17; final int EVENT_BUTTON18 = 18;
	final int EVENT_BUTTON19 = 19; final int EVENT_BUTTON20 = 20; final int EVENT_BUTTON21 = 21;
	final int EVENT_BUTTON22 = 22; final int EVENT_BUTTON23 = 23; final int EVENT_BUTTON24 = 24;
	final int EVENT_BUTTON25 = 25; final int EVENT_BUTTON26 = 26; final int EVENT_BUTTON27 = 27;
	final int EVENT_BUTTON28 = 28; final int EVENT_BUTTON29 = 29; final int EVENT_BUTTON30 = 30;
	final int EVENT_BUTTON31 = 31; final int EVENT_BUTTON32 = 32; final int EVENT_BUTTON33 = 33;
	final int EVENT_BUTTON34 = 34; final int EVENT_BUTTON35 = 35; final int EVENT_BUTTON36 = 36;
	final int EVENT_BUTTON37 = 37; final int EVENT_BUTTON38 = 38; final int EVENT_BUTTON39 = 39;
	final int EVENT_BUTTON40 = 40; final int EVENT_BUTTON41 = 41; final int EVENT_BUTTON42 = 42;
	final int EVENT_BUTTON43 = 43; final int EVENT_BUTTON44 = 44; final int EVENT_BUTTON45 = 45;
	final int EVENT_BUTTON46 = 46; final int EVENT_BUTTON47 = 47; final int EVENT_BUTTON48 = 48;
	final int EVENT_BUTTON49 = 49; final int EVENT_BUTTON50 = 50; final int EVENT_BUTTON51 = 51;
	final int EVENT_BUTTON52 = 52; final int EVENT_BUTTON53 = 53; final int EVENT_BUTTON54 = 54;
	final int EVENT_BUTTON55 = 55; final int EVENT_BUTTON56 = 56; final int EVENT_BUTTON57 = 57;
	final int EVENT_BUTTON58 = 58; final int EVENT_BUTTON59 = 59; final int EVENT_BUTTON60 = 60;
	final int EVENT_BUTTON61 = 61; final int EVENT_BUTTON62 = 62; final int EVENT_BUTTON63 = 63;
	final int EVENT_BUTTON64 = 64; final int EVENT_BUTTON65 = 65; final int EVENT_BUTTON66 = 66;
	final int EVENT_BUTTON67 = 67; final int EVENT_BUTTON68 = 68; final int EVENT_BUTTON69 = 69;
	final int EVENT_BUTTON70 = 70; final int EVENT_BUTTON71 = 71; final int EVENT_BUTTON72 = 72;
	final int EVENT_BUTTON73 = 73; final int EVENT_BUTTON74 = 74; final int EVENT_BUTTON75 = 75;
	final int EVENT_BUTTON76 = 76; final int EVENT_BUTTON77 = 77; final int EVENT_BUTTON78 = 78;
	final int EVENT_BUTTON_BACK = 100;
	final int EVENT_BUTTON_AVERAGE_PRICE_BAR = 1000;
	final int EVENT_BUTTON_YEAR_BAR = 1001;
	final int EVENT_BUTTON_PICTOGRAM = 1002;
	final int EVENT_BUTTON_STATISTICS = 1003;
	final int EVENT_BUTTON_PIE = 1004;

	final int EVENT_BUTTON_HELP = 1006;
	public Widget widgetBack = new Widget(635, 0, 65,35, "  Back", (255), aFont, EVENT_BUTTON_BACK, p);
	public Widget widgetPie = new Widget(435,0,100,35, "  Pie Chart",(255), aFont, EVENT_BUTTON_PIE, p); 
	public Widget widgetAVERAGE_PRICE_BAR = new Widget(0, 0, 100, 35, " Average Price", (255), aFont,
			EVENT_BUTTON_AVERAGE_PRICE_BAR, p);
	public Widget widgetPICTOGRAM = new Widget(100, 0, 100, 35, "    Pictogram", (255), aFont, EVENT_BUTTON_PICTOGRAM,
			p);
	public Widget widgetYEAR_BAR = new Widget(200, 0, 135, 35, " Average over Years", (255), aFont,
			EVENT_BUTTON_YEAR_BAR, p);
	public Widget widgetSTATS = new Widget(335, 0, 100, 35, " Statistics", (255), aFont, EVENT_BUTTON_STATISTICS,p);
	
	final int EVENT_BUTTON1997 = 1997;
	final int EVENT_BUTTON1998 = 1998;
	final int EVENT_BUTTON1999 = 1999;
	final int EVENT_BUTTON2000 = 2000;
	final int EVENT_BUTTON2001 = 2001;
	final int EVENT_BUTTON2002 = 2002;
	final int EVENT_BUTTON2003 = 2003;
	final int EVENT_BUTTON2004 = 2004;
	final int EVENT_BUTTON2005 = 2005;
	final int EVENT_BUTTON2006 = 2006;
	final int EVENT_BUTTON2007 = 2007;
	final int EVENT_BUTTON2008 = 2008;
	final int EVENT_BUTTON2009 = 2009;
	final int EVENT_BUTTON2010 = 2010;
	
	public boolean drawthisGraph = false;
	public boolean pieChart = false;
	final int EVENT_NULL = 0;

	static String countyName = "temp";
	static County currentCounty;
	
	Widget widget1997 = new Widget(70, 40, 30, 20, " 1997", (255), aFont, EVENT_BUTTON1997, p);
 	Widget widget1998 = new Widget(70, 60, 30, 20, " 1998", (255), aFont, EVENT_BUTTON1998, p);
 	Widget widget1999 = new Widget(70, 80, 30, 20, " 1999", (255), aFont, EVENT_BUTTON1999, p);
 	Widget widget2000 = new Widget(70, 100, 30, 20, " 2000", (255), aFont, EVENT_BUTTON2000, p);
 	Widget widget2001 = new Widget(70, 120, 30, 20, " 2001", (255), aFont, EVENT_BUTTON2001, p);
 	Widget widget2002 = new Widget(70, 140, 30, 20, " 2002", (255), aFont, EVENT_BUTTON2002, p);
 	Widget widget2003 = new Widget(70, 160, 30, 20, " 2003", (255), aFont, EVENT_BUTTON2003, p);
 	Widget widget2004 = new Widget(170, 40, 30, 20, " 2004", (255), aFont, EVENT_BUTTON2004, p);
 	Widget widget2005 = new Widget(170, 60, 30, 20, " 2005", (255), aFont, EVENT_BUTTON2005, p);
 	Widget widget2006 = new Widget(170, 80, 30, 20, " 2006", (255), aFont, EVENT_BUTTON2006, p);
 	Widget widget2007 = new Widget(170, 100, 30, 20, " 2007", (255), aFont, EVENT_BUTTON2007, p);
 	Widget widget2008 = new Widget(170, 120, 30, 20, " 2008", (255), aFont, EVENT_BUTTON2008, p);
 	Widget widget2009 = new Widget(170, 140, 30, 20, " 2009", (255), aFont, EVENT_BUTTON2009, p);
 	Widget widget2010 = new Widget(170, 160, 30, 20, " 2010", (255), aFont, EVENT_BUTTON2010, p);


	public static void main(String[] args) {
		PApplet.main("Housing");
	}
	// number of counties in England : 48
	// number of counties in Wales : 22

	int colour = color(0, 0, 0);
	ArrayList<Widget> widgetList;
	public void settings() {
		pictogramImage1 = loadImage("house1.png");
		pictogramImage2 = loadImage("house2.png");
		pictogramImage3 = loadImage("house3.png");
		scanBarImage = loadImage("scan widget.png");
		logoImage = loadImage("logo.png");
		cornerLogo = loadImage("corner logo.png");
		helpImage = loadImage("questionMark.png");
		helpX = loadImage("x image.png");
		size(SCREEN_WIDTH,SCREEN_HEIGHT);
		int index = 0;
		rawDataArray = readData();
		rawCountyNamesArray = readCounties();

		pointInfoArray = new String[11];
		countyNamesArray = new String[47];

		StatsDatapoint.create2DArray();
		dataPointsArray = new ArrayList<DataPoint>();
		countiesArray = new ArrayList<County>();
		County inputCounty = new County();
		DataPoint inputPoint = new DataPoint();
		
		// Separates the rawDataArray into DataPoint Objects containing
		// variables
		while (index < rawDataArray.length)
		{
			pointInfoArray = rawDataArray[index].split(",");
			inputPoint = new DataPoint(pointInfoArray);
			dataPointsArray.add(inputPoint);
			index++;
		}

		// Separates the countyNamesArray into County Objects containing
		// variables
		for (int i = 0; i < rawCountyNamesArray.length; i++) {

			inputCounty = new County(rawCountyNamesArray[i]);
			countiesArray.add(inputCounty);
		}

		// Adds the individual DataPoint Objects to the County Objects
		for (int i = 0; i < dataPointsArray.size(); i++) {
			DataPoint testPoint = dataPointsArray.get(i);
			String countyName = testPoint.county;
			for (County testCounty : countiesArray) {
				if (testCounty.name.equalsIgnoreCase(countyName)) {
					testCounty.addDataPoint(testPoint);
					break;
				}
			}
		}

		for (County county : countiesArray) 
		{
			county.calculateMaxPrice();
			county.calculateMinPrice();
			county.calculateAveragePrice();
			county.updateCounters();
			county.findPropertyTypePercentages();
			if(county.averagePrice != 0)
				System.out.println("Name: " + county.name + "Average Price:" + county.averagePrice + "\n");
			
			else
				System.out.println("Name: " + county.name + "Average Price: N/A\n");
			
		}
	}
	public void setup() {
	
		aFont = loadFont("ArialNarrow-Italic-11.vlw");
		textFont(aFont);
		pictogramImage1 = loadImage("house1.png");
		pictogramImage2 = loadImage("house2.png");
		pictogramImage2 = loadImage("house2.png");
		textAlign(LEFT, CENTER);

		aFont = createFont("Arial", 30);
		noStroke();

		widgetList = new ArrayList<Widget>();
		background = loadImage("home screen.png");
	    helpText = loadImage("help text.png");
		createWidgets();
	}
	
	public void drawPictogramGraph()
	{
		if(pictogram == true)
		{
			double spacing = thePictogram.graphWidth / 4.25;
			int increment = 0;
			fill(40, 48, 57);
			rect((thePictogram.graphStartX - 5), (300), 2, ((SCREEN_HEIGHT/2)) + 110);
			rect((thePictogram.graphStartX - 5), (thePictogram.graphEndY + (SCREEN_HEIGHT / 1000)), SCREEN_WIDTH-170, 2);
			double numberOfPictures1 = 0;
			double numberOfPictures2 = 0;
			double numberOfPictures3 = 0;
			if(thePictogram.indexOfHighestValue == 2)
			{
				numberOfPictures3 = ((thePictogram.graphEndY - (thePictogram.graphStartY + 200)))/thePictogram.IMAGE_HEIGHT;
				
				numberOfPictures1 = (numberOfPictures3) * ((double)thePictogram.values[0]/(double)thePictogram.values[2]);
				numberOfPictures2 = (numberOfPictures3) * ((double)thePictogram.values[1]/(double)thePictogram.values[2]);
				
			}
			else if(thePictogram.indexOfHighestValue == 1)
			{
				numberOfPictures2 = ((thePictogram.graphEndY - (thePictogram.graphStartY + 200)))/thePictogram.IMAGE_HEIGHT;
				numberOfPictures1 = (numberOfPictures2) * ((double)thePictogram.values[0]/(double)thePictogram.values[1]);
				numberOfPictures3 = (numberOfPictures2) * ((double)thePictogram.values[2]/(double)thePictogram.values[1]);
			}
			else if(thePictogram.indexOfHighestValue == 0)
			{
				numberOfPictures1 = ((thePictogram.graphEndY - (thePictogram.graphStartY + 200)))/thePictogram.IMAGE_HEIGHT;
				numberOfPictures2 = (numberOfPictures1) * ((double)thePictogram.values[1]/(double)thePictogram.values[0]);
				numberOfPictures3 = (numberOfPictures1) * ((double)thePictogram.values[2]/(double)thePictogram.values[0]);
			}
			int[] numberOfPictures = {(int)numberOfPictures1, (int)numberOfPictures2, (int)numberOfPictures3};
			PImage imageToUse ;
			
			for(int index = 0; index < numberOfPictures.length; index++)
			{
				switch(index)
				{
				case 0:
					imageToUse = pictogramImage1;
					break;
				case 1:
					imageToUse = pictogramImage2;
					break;
				default:
					imageToUse = pictogramImage3;
					break;
				}
				
				fill(40, 48, 57);
				text(thePictogram.yAxis, (float)(thePictogram.graphStartX - spacing - thePictogram.graphWidth/8 - 20), (float)(thePictogram.graphEndY/2+100));
				text(thePictogram.bars[index].label, (float)(((thePictogram.graphStartX + thePictogram.graphWidth/8 + increment))), (float)(thePictogram.graphEndY + 25));
				int count = 0;
				int stack = 0;
				while(count < numberOfPictures[index])
				{
					image(imageToUse, (float)(((thePictogram.graphStartX + increment + spacing))), 
							(float)(thePictogram.graphEndY - thePictogram.IMAGE_HEIGHT - stack));
					stack += thePictogram.IMAGE_HEIGHT;
					count++;
				}
				increment += thePictogram.graphWidth + spacing;
			}
		}
	}
	public void drawMainScreen()
	{
		if(click == false && drawHomeScreen == false)
		{
			background = loadImage("image altered.png");
			background(background);
			fill(0);
			textFont(aFont);
			textSize(55);
			image(logoImage,380,05);
			image(helpImage, 600, 800);
			if(mouseX<=630 && mouseX>=600 && mouseY<=830 && mouseY>=800 && mousePressed)
			{
				help = true;
			}

			if(mouseX<=568 && mouseX>=553 && mouseY<=845 && mouseY>=830 && mousePressed)
			{
				help = false;
			}
			if (help == true)
			{
				image(helpText, 300, 830);
				textSize(10);
				image(helpX, 553,830);
			}
		}
		
	}
	public void drawWhenPressed()
	{
		if(drawGraph == true)
		{
			background(211, 210, 199);
			textSize(15);
			drawGraph();
		}
		if(pictogram == true)
		{
			background(211, 210, 199);
			textSize(15);
			drawPictogramGraph();
		}
		if(overTheYears == true)
		{
			background(211, 210, 199);
			textSize(15);
			drawOverTheYears();
		}
		if(stats == true)
		{
			background(211, 210, 199);
			textSize(15);
			drawStats();
		}
		if(pieChart == true)
		{
			background(211, 210, 199);
			textSize(15);
			drawPieChart();
		}

		fill(colour);
		image(cornerLogo, 375, 45, 75, 50);
		textFont(aFont);
		text(countyName, 440, 60);
	}
	public void draw() 
	{
		if(drawHomeScreen == true)
		{
			background = loadImage("home screen.png");
			background(background);
			image(scanBarImage, SCAN_WIDGET_XPOS, SCAN_WIDGET_YPOS);
			if(mouseX>=100 && mouseX<=602 &&mouseY>=750 &&mouseY<=832 && mousePressed)
			{
				drawHomeScreen=false;
			}
			
		}
		else if(click==true && drawHomeScreen == false)
		{
			drawWhenPressed();
			textSize(15);
			widgetBack.draw();
			widgetAVERAGE_PRICE_BAR.draw();
			widgetPICTOGRAM.draw();
			widgetYEAR_BAR.draw();
			widgetSTATS.draw();
			widgetPie.draw();
			if(mouseX>635 && mouseX<700 && mouseY>0 && mouseY<35 && mousePressed)
			{
				click = false;
			}
			if(mouseX>435 && mouseX<535 && mouseY>0 && mouseY<35 && mousePressed)
			{
				drawGraph = false;
				pictogram = false;
				drawHomeScreen = false;
				drawScanBar = false;
				overTheYears = false;
				stats = false;
				drawthisGraph = false;
				pieChart = true;
				widgetAVERAGE_PRICE_BAR.setColor(255);
				widgetPie.setColor(150);
				widgetPICTOGRAM.setColor(255);
				widgetYEAR_BAR.setColor(255);
				widgetSTATS.setColor(255);
			}
			if(mouseX>0 && mouseX<100 && mouseY>0 && mouseY<35 && mousePressed)
			{
				drawGraph = true;
				pictogram = false;
				drawHomeScreen = false;
				drawScanBar = false;
				overTheYears = false;
				stats = false;
				drawthisGraph = false;
				pieChart = false;
				widgetPie.setColor(255);
				widgetPICTOGRAM.setColor(255);
				widgetYEAR_BAR.setColor(255);
				widgetSTATS.setColor(255);
				widgetAVERAGE_PRICE_BAR.setColor(150);
			
			}
			if(mouseX>100 && mouseX<200 && mouseY>0 && mouseY<35 && mousePressed)
			{
				drawGraph = false;
				pictogram = true;
				overTheYears = false;
				stats = false;
				drawthisGraph = false;
				pieChart = false;
				drawHomeScreen = false;
				drawScanBar = false;
				widgetPie.setColor(255);
				widgetPICTOGRAM.setColor(150);
				widgetYEAR_BAR.setColor(255);
				widgetSTATS.setColor(255);
				widgetAVERAGE_PRICE_BAR.setColor(255);
			}
			if(mouseX>200 && mouseX<335 && mouseY>0 && mouseY<35 && mousePressed)
			{
				drawGraph = false;
				pictogram = false;
				drawHomeScreen = false;
				drawScanBar = false;
				overTheYears = true;
				drawthisGraph = true;
				stats = false;
				pieChart = false;
				widgetPie.setColor(255);
				widgetPICTOGRAM.setColor(255);
				widgetYEAR_BAR.setColor(150);
				widgetSTATS.setColor(255);
				widgetAVERAGE_PRICE_BAR.setColor(255);
			}
			if(mouseX>335 && mouseX<435 && mouseY>0 && mouseY<35 && mousePressed)
			{
				drawGraph = false;
				pictogram = false;
				overTheYears = false;
				drawthisGraph = false;
				drawHomeScreen = false;
				drawScanBar = false;
				stats = true;
				pieChart = false;
				widgetPie.setColor(255);
				widgetPICTOGRAM.setColor(255);
				widgetYEAR_BAR.setColor(255);
				widgetSTATS.setColor(150);
				widgetAVERAGE_PRICE_BAR.setColor(255);
			}
			if(mouseX>535 && mouseX<635 && mouseY>0 && mouseY<20 && mousePressed)
			{
				drawGraph = false;
				pictogram = false;
				overTheYears = false;
				drawthisGraph = false;
				drawHomeScreen = false;
				drawScanBar = false;
				stats = false;
				pieChart = false;
				widgetPie.setColor(255);
				widgetPICTOGRAM.setColor(255);
				widgetYEAR_BAR.setColor(255);
				widgetSTATS.setColor(255);
				widgetAVERAGE_PRICE_BAR.setColor(255);
			}
		}
		else if(click==false && drawHomeScreen == false)
		{
			drawMainScreen();
			textFont(aFont);
			textSize(10);
			for (int i = 0; i < widgetList.size(); i++) {
				Widget aWidget = (Widget) widgetList.get(i);
				aWidget.draw();
			}
		}
	}
	public void mousePressed()
	{
		checkClick();
	}
	public void drawthisGraph()
	{
		int increment = 0;
		fill(40,48,57);
		rect((barChart.graphStartX - 5), (400), 2, ((SCREEN_HEIGHT/2)) + 10);
		rect((barChart.graphStartX - 5), (barChart.graphEndY + (SCREEN_HEIGHT / 1000)), SCREEN_WIDTH-160, 2);
		textSize(20);
		text(barChart.yAxis, (float)(barChart.graphStartX-20), (float)(barChart.graphEndY/2.9));
		textSize(14);
		//Top Mark
		text(barChart.highestValue, (float)(barChart.graphStartX - 60), 410);
		//3 Quarter Mark
		text((barChart.highestValue/4)*3, (float)(barChart.graphStartX - 60),(float)(410+((theChart.graphEndY-410)/4)));
		//Halfway Mark
		text(barChart.highestValue/2, (float)(barChart.graphStartX - 60), ((410+theChart.graphEndY)/2));
		//Quarter Mark
		text(barChart.highestValue/4, (float)(barChart.graphStartX - 60),(float)(((410+theChart.graphEndY)/2)+((theChart.graphEndY-410)/4)));
		text(("0"), (float)(barChart.graphStartX - 40), (float)(theChart.graphEndY+5));
		
		for(int i = 0; i < barChart.numberOfBars; i++)
		{
			fill(40, 48, 57);
			text(barChart.bars[i].label, (float)(((barChart.graphStartX + increment))), (float)(barChart.graphEndY + 25));
			switch(i)
		    {
		    case 0: 
		    	fill(136, 207, 182);
		    	break;
		    case 1:
		    	fill(255, 214, 205);
		    	break;
		    case 2:
		    	fill(255, 115, 83);
		    	break;
		    case 3:
		    	fill(22, 178, 123);
		    	break;
		    case 4:
		    	fill(178, 47, 17);
		    	break;
		    case 5: 
		    	fill(136, 207, 182);
		    	break;
		    case 6:
		    	fill(255, 214, 205);
		    	break;
		    case 7:
		    	fill(255, 115, 83);
		    	break;
		    case 8:
		    	fill(22, 178, 123);
		    	break;
		    case 9:
		    	fill(136, 207, 182);
		    	break;
		    case 10:
		    	fill(255, 214, 205);
		    	break;
		    case 11:		
		    	fill(22, 178, 123);		
		    	break;		
		    case 12:		
		    	fill(178, 47, 17);		
		    	break;	
		    case 13:
		    	fill(136, 207, 182);
		    	break;
		    	
		    }
			rect((float)barChart.graphStartX + increment, (float)(barChart.graphEndY - barChart.bars[i].height), (float)barChart.graphWidth, (float)barChart.bars[i].height);
			increment = increment + (barChart.graphWidth + 30);
		}
	}
	public void drawGraph()
	{
		fill(40, 48, 57);
		int increment = 50;
		rect((theChart.graphStartX - 5), (300), 2, ((SCREEN_HEIGHT/2)) + 110);
		rect((theChart.graphStartX - 5), (theChart.graphEndY + (SCREEN_HEIGHT / 1000)), SCREEN_WIDTH-170, 2);
		text(theChart.yAxis, (float)(theChart.graphStartX - (theChart.graphWidth + 20)), (float)(theChart.graphEndY/1.5));
		//text(("" + theChart.highestValue), (float)(theChart.graphStartX - theChart.graphWidth/2), (float)(theChart.graphStartY));
		//text(("" + theChart.highestValue/2), (float)(theChart.graphStartX - theChart.graphWidth/2), (float)((theChart.graphStartY + ((theChart.graphStartY - theChart.graphEndY)/2))));
		text(("0"), (float)(theChart.graphStartX - theChart.graphWidth/2), (float)(theChart.graphEndY));
		
		for(int i = 0; i < theChart.numberOfBars; i++)
		{
			fill(40, 48, 57);
			text(theChart.bars[i].label, (float)(((theChart.graphStartX + increment))), (float)(theChart.graphEndY + 25));
			text(("£" + (int)theChart.bars[i].value), (float)(((theChart.graphStartX + increment))), (float)(theChart.graphEndY + 55));
			switch(i)
		    {
		    case 0: 
		    	fill(136, 207, 182);
		    	break;
		    case 1:
		    	fill(255, 214, 205);
		    	break;
		    case 2:
		    	fill(255, 115, 83);
		    	break;
		    case 3:
		    	fill(22, 178, 123);
		    	break;
		    case 4:
		    	fill(178, 47, 17);
		    	break;
		    case 5: 
		    	fill(178, 47, 17);
		    	break;
		    case 6:
		    	fill(255, 214, 205);
		    	break;
		    case 7:
		    	fill(255, 115, 83);
		    	break;
		    case 8:
		    	fill(22, 178, 123);
		    	break;
		    case 9:
		    	fill(136, 207, 182);
		    	break;
		    }
			rect((float)theChart.graphStartX + increment, (float)(theChart.graphEndY - theChart.bars[i].height), (float)theChart.graphWidth, (float)theChart.bars[i].height);
			increment = increment + (theChart.graphWidth + 75);
		}
	}
	public void overYears()
	{
		 	textSize(12);
			widget1997.draw();
			widget1998.draw();
			widget1999.draw();
			widget2000.draw();
			widget2001.draw();
			widget2002.draw();
			widget2003.draw();
			widget2004.draw();
			widget2005.draw();
			widget2006.draw();
			widget2007.draw();
			widget2008.draw();
			widget2009.draw();
			widget2010.draw();
			
			textSize(12);
			fill(40, 48, 57);
			text("Start Year:", 8, 50);
			text("End Year:", 113, 50);
			
			if(mouseY>40 && mouseY<60 && mouseX>70 && mouseX<100 && mousePressed)
			{
				System.out.print("1997");
				minYear = 1997;
				deselectLeftWidgets();
				widget1997.selected = true;
			}
			
			if(mouseY>60 && mouseY<80 && mouseX>70 && mouseX<100 && mousePressed)
			{
				System.out.print("1998");
				minYear = 1998;
				deselectLeftWidgets();
				widget1998.selected = true;
			}
			
			if(mouseY>80 && mouseY<100 && mouseX>70 && mouseX<100 && mousePressed)
			{
				System.out.print("1999");
				minYear = 1999;
				deselectLeftWidgets();
				widget1999.selected = true;
			}
			
			if(mouseY>100 && mouseY<120 && mouseX>70 && mouseX<100 && mousePressed)
			{
				System.out.print("2000");
				minYear = 2000;
				deselectLeftWidgets();
				widget2000.selected = true;
			}
			if(mouseY>120 && mouseY<140 && mouseX>70 && mouseX<100 && mousePressed)
			{
				System.out.print("2001");
				minYear = 2001;
				deselectLeftWidgets();
				widget2001.selected = true;
			}
			if(mouseY>140 && mouseY<160 && mouseX>70 && mouseX<100 && mousePressed)
			{
				System.out.print("2002");
				minYear = 2002;
				deselectLeftWidgets();
				widget2002.selected = true;
			}
			if(mouseY>160 && mouseY<180 && mouseX>70 && mouseX<100 && mousePressed)
			{
				System.out.print("2003");
				minYear = 2003;
				deselectLeftWidgets();
				widget2003.selected = true;
			}
			if(mouseY>40 && mouseY<60 && mouseX>170 && mouseX<200 && mousePressed)
			{
				System.out.print("2004");
				maxYear = 2004;
				deselectRightWidgets();
				widget2004.selected = true;
			}
			if(mouseY>60 && mouseY<80 && mouseX>170 && mouseX<200 && mousePressed)
			{
				System.out.print("2005");
				maxYear = 2005;
				deselectRightWidgets();
				widget2005.selected = true;
			}
			if(mouseY>80 && mouseY<100 && mouseX>170 && mouseX<200 && mousePressed)
			{
				System.out.print("2006");
				maxYear = 2006;
				deselectRightWidgets();
				widget2006.selected = true;
			}
			if(mouseY>100 && mouseY<120 && mouseX>170 && mouseX<200 && mousePressed)
			{
				System.out.print("2007");
				maxYear = 2007;
				deselectRightWidgets();
				widget2007.selected = true;
			}
			if(mouseY>120 && mouseY<140 && mouseX>170 && mouseX<200 && mousePressed)
			{
				System.out.print("2008");
				maxYear = 2008;
				deselectRightWidgets();
				widget2008.selected = true;
			}
			if(mouseY>140 && mouseY<160 && mouseX>170 && mouseX<200 && mousePressed)
			{
				System.out.println("2009");
				maxYear = 2009;
				deselectRightWidgets();
				widget2009.selected = true;
			}
			if(mouseY>160 && mouseY<180 && mouseX>170 && mouseX<230 && mousePressed)
			{
				System.out.println("2010");
				maxYear = 2010;
				deselectRightWidgets();
				widget2010.selected = true;
			}
	}
	public void drawPieChart()
	{
		int[] propertyTypeData = { currentCounty.detachedPercentage, currentCounty.semiDetachedPercentage, currentCounty.terracedPercentage,
				currentCounty.flatsPercentage, currentCounty.otherPercentage};
		fill(40, 48, 57);
		textSize(20);
		text("Property Types Sold in " + countyName, 190, 270);
		pieChart(300, propertyTypeData);
		
	}
	public void pieChart(float diameter, int[] data) {
		  float lastAngle = 0;
		  float yPos = 370;
		  float xPos = 550;
		  for (int i = 0; i < data.length; i++) {
		    
		    fill(40, 48, 57);
		    textSize(15);
			text("Detached", (xPos + 25), 370 + 7);			 
			text("Semi Detached", (xPos + 25), 420 + 7);			    
			text("Terraced", (xPos + 25), 470 + 7);			   
			text("Flats", (xPos + 25), 520 + 7);			    
			text("Other", (xPos + 25), 570 + 7);
			
		    switch(i)
		    {
		    case 0: 
		    	fill(178, 47, 17);
		    	break;
		    case 1:
		    	fill(255, 214, 205);
		    	break;
		    case 2:
		    	fill(255, 115, 83);
		    	break;
		    case 3:
		    	fill(22, 178, 123);
		    	break;
		    case 4:
		    	fill(136, 207, 182);
		    	break;
		    }
		    rect(xPos, yPos, 20, 20);
		    arc(width/2, height/2, diameter, diameter, lastAngle, lastAngle+radians(data[i]));
		    lastAngle += radians(data[i]);
		    yPos += 50;
		  }
	}
	public void drawStats()
	{
		textSize(10);
		text("Statics as of 2015 estimates",535, 95);
		text("Source: Wikipedia",535,105);
		for (int i= 0; i<73;i++)
		{
			String county = StatsDatapoint.StatsArray[i][0];
			if(countyName.equals(county))
			{
				String population = StatsDatapoint.StatsArray[i][1];
				String RankPOP = StatsDatapoint.StatsArray[i][2];
				String area = StatsDatapoint.StatsArray[i][3];
				String RankAREA = StatsDatapoint.StatsArray[i][4];
				String density = StatsDatapoint.StatsArray[i][5];
				textSize(50);
				text("Population : "+population ,100,350);
				text("Rank (Population) : "+RankPOP ,100,450);
				text("Area : "+ area+" km²",100,550);
				text("Rank (Area) : "+RankAREA ,100,650);
				text("Denisty : "+ density+" people/km²",100,750);
				break;
			}
			
		}
	}
	public void drawOverTheYears()
	{
		if(minYear >= 1997 && maxYear >= 1997 && minYear != maxYear)
		{
			barChart = makeBarChart(minYear, maxYear, currentCounty);
			drawthisGraph = true;
			drawthisGraph();
			overYears();
		}
		else
		{
			overYears();
		}
		}
	public void mouseMoved()
	{
		checkMove();
	}
	public static Chart makeBarChart(int startYear, int endYear, County county)
	{
		int difference = (endYear-startYear) +1;
		int currentYear = startYear;
		double[] averagePricesArray = new double[difference];
		String[] yearsAsStringsArray = new String[difference];
		
		for(int i=0;i<difference;i++)
		{
			double averagePriceForX =  county.averagePriceForYear(currentYear);
			averagePricesArray[i] = averagePriceForX;
			yearsAsStringsArray[i] = Integer.toString(currentYear);
			currentYear++;
		}
		
		Chart barChart = new Chart(difference, "Bar Chart of Average Prices from " + Integer.toString(startYear) + " to " + Integer.toString(endYear) + " for " + county.name
				 ,averagePricesArray, yearsAsStringsArray);
		
		return barChart;
	}
	public static String[] readData() {
		File wordFile = new File("data.txt");
		String[] rawDataArray = loadStrings(wordFile);
		return rawDataArray;
	}
	public static String[] readCounties() {
		File wordFile = new File("counties.txt");
		String[] rawCountyDataArray = loadStrings(wordFile);
		return rawCountyDataArray;
	}
	void createWidgets() {
		Widget widget1, widget2, widget3;
		Widget widget4, widget5, widget6;
		Widget widget7, widget8, widget9;
		Widget widget10, widget11;
		Widget widget13, widget14, widget15;
		Widget widget16, widget17, widget18;
		Widget widget19, widget20, widget21;
		Widget widget22, widget23, widget24;
		Widget widget25, widget26, widget27;
		Widget widget28, widget29, widget30;
		Widget widget31, widget32, widget33;
		Widget widget34, widget35, widget36;
		Widget widget37, widget38, widget39;
		Widget widget40, widget41, widget42;
		Widget widget43;
		Widget widget46, widget47, widget48;
		Widget widget49, widget50, widget51;
		Widget widget52, widget53, widget54;
		Widget widget55, widget56, widget57;
		Widget widget58, widget59, widget60;
		Widget widget61, widget62, widget63;
		Widget widget64, widget65, widget66;
		Widget widget67, widget68;
		

		widget1 = new Widget(305, 137, 75, 20, "Northumberland", (255), aFont, EVENT_BUTTON1, p);
		widget2 = new Widget(250, 190, 40, 20, "Cumbria", (255), aFont, EVENT_BUTTON2, p);
		widget3 = new Widget(351, 198, 40, 20, "Durham", (255), aFont, EVENT_BUTTON3, p);

		widget4 = new Widget(270, 235, 60, 20, "Westmorland", (255), aFont, EVENT_BUTTON4, p);
		widget5 = new Widget(365, 257, 68, 20, "North Yorkshire", (255), aFont, EVENT_BUTTON5, p);
		widget6 = new Widget(286, 365, 55, 20, "Lancashire", (255), aFont, EVENT_BUTTON6, p);

		widget7 = new Widget(365, 390, 68, 20, "South Yorkshire", (255), aFont, EVENT_BUTTON7, p);
		widget8 = new Widget(330, 340, 68, 20, "West Yorkshire", (255), aFont, EVENT_BUTTON8, p);
		widget9 = new Widget(434, 327, 65, 20, "East Yorkshire", (255), aFont, EVENT_BUTTON9, p);

		widget10 = new Widget(300, 400, 55, 20, "Manchester", (255), aFont, EVENT_BUTTON10, p);
		widget11 = new Widget(245, 400, 53, 20, "Merseyside", (255), aFont, EVENT_BUTTON11, p);
		
		widget13 = new Widget(278, 439, 45, 20, "Cheshire", (255), aFont, EVENT_BUTTON13, p);
		widget14 = new Widget(370, 435, 35, 20, "Derby", (255), aFont, EVENT_BUTTON14, p);
		widget15 = new Widget(410, 454, 52, 20, "Nottingham", (255), aFont, EVENT_BUTTON15, p);

		widget16 = new Widget(474, 437, 57, 20, "Lincolnshire", (255), aFont, EVENT_BUTTON16, p);
		widget17 = new Widget(325, 504, 57, 20, "Staffordshire", (255), aFont, EVENT_BUTTON17, p);
		widget18 = new Widget(402, 526, 50, 20, "Leicester", (255), aFont, EVENT_BUTTON18, p);

		widget19 = new Widget(578, 519, 35, 20, "Norfolk", (255), aFont, EVENT_BUTTON19, p);
		widget20 = new Widget(587, 589, 35, 20, "Suffolk", (255), aFont, EVENT_BUTTON20, p);
		widget21 = new Widget(529, 588, 30, 20, "Camb.", (255), aFont, EVENT_BUTTON21, p);

		widget22 = new Widget(270, 528, 50, 20, "Shropshire", (255), aFont, EVENT_BUTTON22, p);
		widget23 = new Widget(317, 581, 50, 20, "Worcester", (255), aFont, EVENT_BUTTON23, p);
		widget24 = new Widget(270, 590, 45, 20, "Hereford", (255), aFont, EVENT_BUTTON24, p);

		widget25 = new Widget(375, 590, 45, 18, "Warwick", (255), aFont, EVENT_BUTTON25, p);
		widget26 = new Widget(431, 573, 58, 20, "Northampton", (255), aFont, EVENT_BUTTON26, p);
		widget27 = new Widget(480, 610, 25, 20, "Bedf.", (255), aFont, EVENT_BUTTON27, p);

		widget28 = new Widget(440, 638, 25, 20, "Buck.", (255), aFont, EVENT_BUTTON28, p);
		widget29 = new Widget(310, 636, 70, 20, "Gloucestershire", (255), aFont, EVENT_BUTTON29, p);
		widget30 = new Widget(388, 643, 37, 20, "Oxfords.", (255), aFont, EVENT_BUTTON30, p);

		widget31 = new Widget(398, 713, 42, 20, "Berkshire", (255), aFont, EVENT_BUTTON31, p);
		widget32 = new Widget(490, 700, 40, 20, "London", (255), aFont, EVENT_BUTTON32, p);
		widget33 = new Widget(337, 718, 45, 20, "Wiltshire", (255), aFont, EVENT_BUTTON33, p);

		widget34 = new Widget(554, 651, 27, 20, "Essex", (255), aFont, EVENT_BUTTON34, p);
		widget35 = new Widget(580, 742, 25, 20, "Kent", (255), aFont, EVENT_BUTTON35, p);
		widget36 = new Widget(250, 778, 50, 20, "Somerset", (255), aFont, EVENT_BUTTON36, p);

		widget37 = new Widget(395, 768, 55, 20, "Hampshire", (255), aFont, EVENT_BUTTON37, p);
		widget38 = new Widget(476, 742, 45, 20, "Surrey", (255), aFont, EVENT_BUTTON38, p);
		widget39 = new Widget(464, 784, 55, 20, "East Sussex", (255), aFont, EVENT_BUTTON39, p);

		widget40 = new Widget(522, 783, 55, 20, "West Sussex", (255), aFont, EVENT_BUTTON40, p);
		widget41 = new Widget(289, 815, 45, 20, "Dorset", (255), aFont, EVENT_BUTTON41, p);
		widget42 = new Widget(170, 812, 30, 20, "Devon", (255), aFont, EVENT_BUTTON42, p);

		widget43 = new Widget(92, 855, 45, 20, "Cornwall", (255), aFont, EVENT_BUTTON43, p);
		
		widget46 = new Widget(495, 655, 58, 20, "Hertfordshire", (255), aFont, EVENT_BUTTON46, p);
		widget47 = new Widget(120, 420, 45, 20, "Anglesey", (255), aFont, EVENT_BUTTON47, p);
		widget48 = new Widget(245, 450,30, 20, "Flints", (255), aFont, EVENT_BUTTON48, p);

		widget49 = new Widget(175, 440, 35, 20, "Conwy", (255), aFont, EVENT_BUTTON49, p);
		widget50 = new Widget(200, 460, 40, 20, "Denbigs", (255), aFont, EVENT_BUTTON50, p);
		widget51 = new Widget(270, 480, 45, 20, "Wrexham", (255), aFont, EVENT_BUTTON51, p);

		widget52 = new Widget(150, 460, 50, 20, "Gwynedd", (255), aFont, EVENT_BUTTON52, p);
		widget53 = new Widget(200, 532, 40, 20, "Powys", (255), aFont, EVENT_BUTTON53, p);
		widget54 = new Widget(155, 585, 50, 20, "Ceredigion", (255), aFont, EVENT_BUTTON54, p);

		widget55 = new Widget(85, 630, 55, 20, "Pembroke", (255), aFont, EVENT_BUTTON55, p);
		widget56 = new Widget(150, 635, 58, 20, "Carmarthens", (255), aFont, EVENT_BUTTON56, p);
		widget57 = new Widget(255, 640, 52, 20, "Monmouths", (255), aFont, EVENT_BUTTON57, p);

		widget58 = new Widget(165, 663, 45, 15, "Swansea", (255), aFont, EVENT_BUTTON58, p);
		widget59 = new Widget(190, 675, 30, 15, "Neath", (255), aFont, EVENT_BUTTON59, p);
		widget60 = new Widget(205, 650, 55, 20, "Rhondda Cynon Taff", (255), aFont, EVENT_BUTTON60, p);

		widget61 = new Widget(210, 620, 60, 20, "Merthyr Tydfil", (255), aFont, EVENT_BUTTON61, p);
		widget62 = new Widget(270, 660, 55, 15, "Gwent 62", (255), aFont, EVENT_BUTTON62, p);
		widget63 = new Widget(270, 610, 55, 20, "Caerphilly", (255), aFont, EVENT_BUTTON63, p);

		widget64 = new Widget(230, 665, 55, 20, "Torfaen", (255), aFont, EVENT_BUTTON64, p);
		widget65 = new Widget(200, 688, 55, 15, "Bridgend", (255), aFont, EVENT_BUTTON65, p);
		widget66 = new Widget(265, 675, 55, 15, "Newport", (255), aFont, EVENT_BUTTON66, p);

		widget67 = new Widget(263, 690, 55, 20, "Cardiff", (255), aFont, EVENT_BUTTON67, p);
		widget68 = new Widget(220, 705, 55, 15, "Glamorgan", (255), aFont, EVENT_BUTTON68, p);

		widgetList.add(widget1);
		widgetList.add(widget2);
		widgetList.add(widget3);
		widgetList.add(widget4);
		widgetList.add(widget5);
		widgetList.add(widget6);
		widgetList.add(widget7);
		widgetList.add(widget8);
		widgetList.add(widget9);
		widgetList.add(widget10);
		widgetList.add(widget11);
		widgetList.add(widget13);
		widgetList.add(widget14);
		widgetList.add(widget15);
		widgetList.add(widget16);
		widgetList.add(widget17);
		widgetList.add(widget18);
		widgetList.add(widget19);
		widgetList.add(widget20);
		widgetList.add(widget21);
		widgetList.add(widget22);
		widgetList.add(widget23);
		widgetList.add(widget24);
		widgetList.add(widget25);
		widgetList.add(widget26);
		widgetList.add(widget27);
		widgetList.add(widget28);
		widgetList.add(widget29);
		widgetList.add(widget30);
		widgetList.add(widget31);
		widgetList.add(widget32);
		widgetList.add(widget33);
		widgetList.add(widget34);
		widgetList.add(widget35);
		widgetList.add(widget36);
		widgetList.add(widget37);
		widgetList.add(widget38);
		widgetList.add(widget39);
		widgetList.add(widget40);
		widgetList.add(widget41);
		widgetList.add(widget42);
		widgetList.add(widget43);
		widgetList.add(widget46);
		widgetList.add(widget47);
		widgetList.add(widget48);
		widgetList.add(widget49);
		widgetList.add(widget50);
		widgetList.add(widget51);
		widgetList.add(widget52);
		widgetList.add(widget53);
		widgetList.add(widget54);
		widgetList.add(widget55);
		widgetList.add(widget56);
		widgetList.add(widget57);
		widgetList.add(widget58);
		widgetList.add(widget59);
		widgetList.add(widget60);
		widgetList.add(widget61);
		widgetList.add(widget62);
		widgetList.add(widget63);
		widgetList.add(widget64);
		widgetList.add(widget65);
		widgetList.add(widget66);
		widgetList.add(widget67);
		widgetList.add(widget68);

	}
	public void checkClick()
		{
			int event;
			if(drawMap == true)
			{
				for (int i = 0; i < widgetList.size(); i++) {
					Widget aWidget = (Widget) widgetList.get(i);
					event = aWidget.getEvent(mouseX, mouseY);
					switch (event) {
					case EVENT_BUTTON1:
						colour = color(255);
						
						countyName = "Northumberland";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								//theChart = new Chart(1, "Average Price", county.averagePrice, ("" + county.name));
								drawGraph= true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								//thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						click = true;
						
						break;
					case EVENT_BUTTON2:
						colour = color(20, 0, 255);
						
						countyName = "Cumbria";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON3:
						colour = color(30, 50, 50);
						
						countyName = "Durham";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON4:
						colour = color(125);
						
						countyName = "Westmorland";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						click = true;
						
						break;
					case EVENT_BUTTON5:
						colour = color(50, 90, 255);
						
						countyName = "North Yorkshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						click = true;
						
						break;
					case EVENT_BUTTON6:
						colour = color(60, 0, 50);
						
						countyName = "Lancsashire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						click = true;
						
						break;
	
					case EVENT_BUTTON7:
						colour = color(70, 0, 255);
						
						countyName = "South Yorkshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON8:
						colour = color(180, 0, 255);
						
						countyName = "West Yorkshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON9:
						colour = color(90, 170, 255);
						
						countyName = "East Yorkshire ";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON10:
						colour = color(100, 0, 255);
						
						countyName = "Greater Manchester";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON11:
						colour = color(10, 10, 255);
						
						countyName = "Merseyside";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON12:
						colour = color(120, 0, 255);
						
						countyName = " ";
						break;
	
					case EVENT_BUTTON13:
						colour = color(130, 150, 255);
						
						countyName = "Cheshire west and Chester";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON14:
						colour = color(140, 0, 255);
						
						countyName = "Derbyshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON15:
						colour = color(150, 90, 55);
						
						countyName = "Nottinghamshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON16:
						colour = color(160, 0, 25);
						
						countyName = "Lincolnshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON17:
						colour = color(70, 30, 255);
						
						countyName = "Staffordshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON18:
						colour = color(180, 0, 255);
						
						countyName = "Leicestershire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON19:
						colour = color(190, 70, 255);
						
						countyName = "Norfolk";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON20:
						colour = color(250, 0, 255);
						
						countyName = "Suffolk";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON21:
						colour = color(210, 10, 25);
						
						countyName = "Cambridgeshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON22:
						colour = color(220, 0, 255);
						
						countyName = "Shropshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON23:
						colour = color(230, 50, 55);
						
						countyName = "Worcestershire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON24:
						colour = color(240, 0, 255);
						
						countyName = "Herefordshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON25:
						colour = color(0, 40, 255);
						
						countyName = "Warwickshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON26:
						colour = color(100, 20, 255);
						
						countyName = "Northamptonshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON27:
						colour = color(200, 30, 255);
						
						countyName = "Bedfordshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON28:
						colour = color(0, 40, 255);
						
						countyName = "Buckinghamshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON29:
						colour = color(0, 50, 255);
						
						countyName = "Gloucestershire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON30:
						colour = color(90, 60, 255);
						
						countyName = "Oxfordshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON31:
						colour = color(0, 170, 255);
						
						countyName = "Berkshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON32:
						colour = color(0, 80, 25);
						
						countyName = "Greater London";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON33:
						colour = color(20, 90, 55);
						
						countyName = "Wiltshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON34:
						colour = color(100, 100, 255);
						
						countyName = "Essex";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON35:
						colour = color(30, 110, 55);
						
						countyName = "Kent";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON36:
						colour = color(0, 20, 255);
						
						countyName = "Somerset";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON37:
						colour = color(10, 130, 255);
						
						countyName = "Hampshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON38:
						colour = color(50, 40, 255);
						
						countyName = "Surrey";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON39:
						colour = color(0, 15, 25);
						
						countyName = "East Sussex";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON40:
						colour = color(90, 160, 55);
						
						countyName = "West Sussex";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON41:
						colour = color(0, 170, 255);
						
						countyName = "Dorset";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON42:
						colour = color(100, 180, 55);
						
						countyName = "Devon";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON43:
						colour = color(90, 190, 205);
						
						countyName = "Cornwall";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON44:
						colour = color(0, 200, 255);
						
						countyName = "Isle of White";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								pictogram = true;
								avg = county.averagePrice;
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON45:
						colour = color(90, 20, 255);
						
						countyName = "K";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
	
					case EVENT_BUTTON46:
						colour = color(0, 220, 25);
						
						countyName = "Hertfordshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON47:
						colour = color(100, 230, 55);
						
						countyName = "Isle of Anglesey";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON48:
						colour = color(0, 240, 255);
						
						countyName = "Flintshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON49:
						colour = color(0, 240, 255);
						
						countyName = "Conwy";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON50:
						colour = color(0, 240, 255);
						
						countyName = "Denbighshire";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;
					case EVENT_BUTTON51:
						colour = color(0, 240, 255);
						
						countyName = "Wrexham";
						for (County county : countiesArray) 
						{
							if(county.name.equals(countyName))
							{
								//averagePrice = county.avgPrice;
								currentCounty = county;
								theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
								drawGraph = true;
								thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
								max = county.maxPrice;
								min = county.minPrice;
							}
						}
						
						click = true;
						break;case EVENT_BUTTON52:
							colour = color(0, 240, 255);
							countyName = "Gwynedd";
							for (County county : countiesArray) 
							{
								if(county.name.equals(countyName))
								{
									//averagePrice = county.avgPrice;
									currentCounty = county;
									theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
									drawGraph = true;
									thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
									max = county.maxPrice;
									min = county.minPrice;
								}
							}
							
							click = true;
							break;case EVENT_BUTTON53:
								colour = color(0, 240, 255);
								
								countyName = "Powys";
								for (County county : countiesArray) 
								{
									if(county.name.equals(countyName))
									{
										//averagePrice = county.avgPrice;
										currentCounty = county;
										theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
										drawGraph = true;
										thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
										max = county.maxPrice;
										min = county.minPrice;
									}
								}
								
								click = true;
								break;
							case EVENT_BUTTON54:
								colour = color(0, 240, 255);
								
								countyName = "Cardiganshire";
								for (County county : countiesArray) 
								{
									if(county.name.equals(countyName))
									{
										//averagePrice = county.avgPrice;
										currentCounty = county;
										theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
										drawGraph = true;
										thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
										max = county.maxPrice;
										min = county.minPrice;
									}
								}
								
								click = true;
								break;
							case EVENT_BUTTON55:
								colour = color(0, 240, 255);
								
								countyName = "Pembrokeshire";
								for (County county : countiesArray) 
								{
									if(county.name.equals(countyName))
									{
										//averagePrice = county.avgPrice;
										currentCounty = county;
										theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
										drawGraph = true;
										thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
										max = county.maxPrice;
										min = county.minPrice;
									}
								}
								
								click = true;
								break;
							case EVENT_BUTTON56:
								colour = color(0, 240, 255);
								
								countyName = "Carmarthenshire";
								for (County county : countiesArray) 
								{
									if(county.name.equals(countyName))
									{
										//averagePrice = county.avgPrice;
										currentCounty = county;
										theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
										drawGraph = true;
										thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
										max = county.maxPrice;
										min = county.minPrice;
									}
								}
								
								click = true;
								break;
							case EVENT_BUTTON57:
								colour = color(0, 240, 255);
								
								countyName = "Monmouthshire";
								for (County county : countiesArray) 
								{
									if(county.name.equals(countyName))
									{
										//averagePrice = county.avgPrice;
										currentCounty = county;
										theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
										drawGraph = true;
										thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
										max = county.maxPrice;
										min = county.minPrice;
									}
								}
								
								click = true;
								break;
							case EVENT_BUTTON58:
								colour = color(0, 240, 255);
								
								countyName = "Swansea";
								for (County county : countiesArray) 
								{
									if(county.name.equals(countyName))
									{
										//averagePrice = county.avgPrice;
										currentCounty = county;
										theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
										drawGraph = true;
										thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
										max = county.maxPrice;
										min = county.minPrice;
									}
								}
								
								click = true;
								break;
							case EVENT_BUTTON59:
								colour = color(0, 240, 255);
								
								countyName = "Neath Port Talbot";
								for (County county : countiesArray) 
								{
									if(county.name.equals(countyName))
									{
										//averagePrice = county.avgPrice;
										currentCounty = county;
										theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
										drawGraph = true;
										thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
										max = county.maxPrice;
										min = county.minPrice;
									}
								}
								
								click = true;
								break;
								
							case EVENT_BUTTON60:
								colour = color(0, 240, 255);
								
								countyName = "Rhondda Cynon Taff";
								for (County county : countiesArray) 
								{
									if(county.name.equals(countyName))
									{
										//averagePrice = county.avgPrice;
										currentCounty = county;
										theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
										drawGraph = true;
										thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
										max = county.maxPrice;
										min = county.minPrice;
									}
								}
								
								click = true;
								break;
							case EVENT_BUTTON61:
								colour = color(0, 240, 255);
								
								countyName = "Merthyr Tydfil";
								for (County county : countiesArray) 
								{
									if(county.name.equals(countyName))
									{
										//averagePrice = county.avgPrice;
										currentCounty = county;
										theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
										drawGraph = true;
										thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
										max = county.maxPrice;
										min = county.minPrice;
									}
								}
								
								click = true;
								break;
								case EVENT_BUTTON62:
									colour = color(0, 240, 255);
									
									countyName = "Blaenau Gwent";
									for (County county : countiesArray) 
									{
										if(county.name.equals(countyName))
										{
											//averagePrice = county.avgPrice;
											currentCounty = county;
											theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
											drawGraph = true;
											thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
											max = county.maxPrice;
											min = county.minPrice;
										}
									}
									
									click = true;
									break;
									case EVENT_BUTTON63:
										colour = color(0, 240, 255);
										
										countyName = "Caerphilly";
										for (County county : countiesArray) 
										{
											if(county.name.equals(countyName))
											{
												//averagePrice = county.avgPrice;
												currentCounty = county;
												theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
												drawGraph = true;
												thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
												max = county.maxPrice;
												min = county.minPrice;
											}
										}
										
										click = true;
										break;
									case EVENT_BUTTON64:
										colour = color(0, 240, 255);
										
										countyName = "Torfaen";
										for (County county : countiesArray) 
										{
											if(county.name.equals(countyName))
											{
												//averagePrice = county.avgPrice;
												currentCounty = county;
												theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
												drawGraph = true;
												thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
												max = county.maxPrice;
												min = county.minPrice;
											}
										}
										
										click = true;
										break;
									case EVENT_BUTTON65:
										colour = color(0, 240, 255);
										
										countyName = "Bridgend";
										for (County county : countiesArray) 
										{
											if(county.name.equals(countyName))
											{
												//averagePrice = county.avgPrice;
												currentCounty = county;
												theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
												drawGraph = true;
												thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
												max = county.maxPrice;
												min = county.minPrice;
											}
										}
										
										click = true;
										break;
									case EVENT_BUTTON66:
										colour = color(0, 240, 255);
										
										countyName = "Newport";
										for (County county : countiesArray) 
										{
											if(county.name.equals(countyName))
											{
												
												theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
												drawGraph = true;
												thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
												max = county.maxPrice;
												min = county.minPrice;
											}
										}
										
										click = true;
										break;	
										
									case EVENT_BUTTON67:
										colour = color(0, 240, 255);
										
										countyName = "Cardiff";
										for (County county : countiesArray) 
										{
											if(county.name.equals(countyName))
											{
												
												theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
												drawGraph = true;
												thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
												max = county.maxPrice;
												min = county.minPrice;
											}
										}
										
										click = true;
										break;
									case EVENT_BUTTON68:
										colour = color(0, 240, 255);
										
										countyName = "Vale of Glamorgan";
										for (County county : countiesArray) 
										{
											if(county.name.equals(countyName))
											{
												
												theChart = makeChart(countiesArray.indexOf(county), "Average\nPrice");
												drawGraph = true;
												thePictogram = makePictogram(countiesArray.indexOf(county), "Number\nof\nHouses");
												max = county.maxPrice;
												min = county.minPrice;
											}
										}
										
										click = true;
										break;
					}
				}
			}
			else
			{
				if(mouseX > SCAN_WIDGET_XPOS && mouseX < SCAN_WIDGET_XPOS + 500 && mouseY > SCAN_WIDGET_YPOS && mouseY < SCAN_WIDGET_YPOS + 70)
				{
					drawHomeScreen = false;
					drawScanBar = false;
					drawMap = true;
				}
			}
		}
	public void checkMove()
		{
		if(drawMap == true)
		{
			int event;
			for (int i = 0; i < widgetList.size(); i++) {
				Widget aWidget = (Widget) widgetList.get(i);
				event = aWidget.getEvent(mouseX, mouseY);
				switch (event) {
				case EVENT_BUTTON1:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON2:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON3:
					aWidget.setStroke(color(255));
					break;

				case EVENT_BUTTON4:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON5:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON6:
					aWidget.setStroke(color(255));
					break;

				case EVENT_BUTTON7:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON8:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON9:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON10:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON11:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON12:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON13:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON14:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON15:
					aWidget.setStroke(color(255));
					break;

				case EVENT_BUTTON16:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON17:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON18:
					aWidget.setStroke(color(255));
					break;

				case EVENT_BUTTON19:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON20:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON21:
					aWidget.setStroke(color(255));
					break;

				case EVENT_BUTTON22:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON23:
					aWidget.setStroke(color(255));
					break;
				case EVENT_BUTTON24:
					aWidget.setStroke(color(255));
					break;

				case EVENT_BUTTON25:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON26:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON27:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON28:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON29:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON30:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON31:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON32:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON33:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON34:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON35:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON36:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON37:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON38:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON39:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON40:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON41:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON42:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON43:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON44:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON45:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON46:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON47:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON48:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON49:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON50:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON51:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON52:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON53:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON54:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON55:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON56:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON57:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON58:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON59:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON60:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON61:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON62:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON63:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON64:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON65:
					aWidget.setStroke(color(255));

					break;

				case EVENT_BUTTON66:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON67:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON68:
					aWidget.setStroke(color(255));

					break;
				case EVENT_BUTTON69:
					aWidget.setStroke(color(255));

					break;

				default:
					aWidget.setStroke(color(100));
				}
			}
		}
		}
	public Chart makeChart(int locationInArray, String label)
	{
		County chosenCounty = countiesArray.get(locationInArray);
		County comparisonCounty1;
		County comparisonCounty2;
		if(locationInArray != 0 && locationInArray != countiesArray.size() -1)
		{
			comparisonCounty1 = countiesArray.get(locationInArray-1);
			comparisonCounty2 = countiesArray.get(locationInArray+1);
		}
		else if(locationInArray == 0)
		{
			comparisonCounty1 = countiesArray.get(locationInArray+1);
			comparisonCounty2 = countiesArray.get(locationInArray+2);
		}
		else
		{
			comparisonCounty1 = countiesArray.get(locationInArray-1);
			comparisonCounty2 = countiesArray.get(locationInArray-2);
		}
		double[] values = {(double)chosenCounty.averagePrice, (double)comparisonCounty1.averagePrice, (double)comparisonCounty2.averagePrice};
		String[] labels = {chosenCounty.name, comparisonCounty1.name, comparisonCounty2.name};
		theChart = new Chart(3, label, values, labels);
		return theChart;
	}
	public PictogramChart makePictogram(int locationInArray, String label)
	{
		PictogramChart thePictogram;
		County chosenCounty = countiesArray.get(locationInArray);
		County comparisonCounty1;
		County comparisonCounty2;
		if(locationInArray != 0 && locationInArray != countiesArray.size() -1)
		{
			comparisonCounty1 = countiesArray.get(locationInArray-1);
			comparisonCounty2 = countiesArray.get(locationInArray+1);
		}
		else if(locationInArray == 0)
		{
			comparisonCounty1 = countiesArray.get(locationInArray+1);
			comparisonCounty2 = countiesArray.get(locationInArray+2);
		}
		else
		{
			comparisonCounty1 = countiesArray.get(locationInArray-1);
			comparisonCounty2 = countiesArray.get(locationInArray-2);
		}
		int[] values = {(int)chosenCounty.memberDataPointsArray.size(), 
				(int)comparisonCounty1.memberDataPointsArray.size(), (int)comparisonCounty2.memberDataPointsArray.size()};
		String[] labels = {chosenCounty.name, comparisonCounty1.name, comparisonCounty2.name};
		thePictogram = new PictogramChart(3, label, values, labels);
		return thePictogram;
	}
	public void deselectLeftWidgets()
	{
		widget1997.selected = false;
		widget1998.selected = false;
		widget1999.selected = false;
		widget2000.selected = false;
		widget2001.selected = false;
		widget2002.selected = false;
		widget2003.selected = false;
	}
	public void deselectRightWidgets()
	{
		widget2004.selected = false;
		widget2005.selected = false;
		widget2006.selected = false;
		widget2007.selected = false;
		widget2008.selected = false;
		widget2009.selected = false;
		widget2010.selected = false;
	}
}