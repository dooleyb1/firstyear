
public class PictogramChart{
	int numberOfBars;
	int graphWidth;
	int graphStartY;
	int graphEndY;
	int graphStartX;
	int graphEndX;
	int highestValue;
	int indexOfHighestValue;
	int[] values;
	Pictogram[] bars;
	String yAxis;
	final int IMAGE_HEIGHT = 30;
	
	PictogramChart(int barCount, String yAxisLabel, int[] values, String[] labels)
	{
		this.values = values;
		numberOfBars = barCount;
		yAxis = yAxisLabel;
		graphStartX = Housing.SCREEN_WIDTH/8;
		graphEndX = Housing.SCREEN_WIDTH - graphStartX;

		graphStartY = Housing.SCREEN_HEIGHT/12;
		graphEndY = Housing.SCREEN_HEIGHT - graphStartY;
		
		graphWidth = ((graphEndX - graphStartX) - (10 * numberOfBars))/numberOfBars;
		bars = new Pictogram[numberOfBars];
		highestValue = 0;
		indexOfHighestValue = 0;
		
		for(int i = 0; i < numberOfBars; i++)
		{
			Pictogram tempBar = new Pictogram(values[i], labels[i], IMAGE_HEIGHT, graphEndY, (graphStartY + (IMAGE_HEIGHT * 10)));
			bars[i] = tempBar;
			if(values[i] > highestValue)
			{
				highestValue = values[i];
				indexOfHighestValue = i;
			}
		}
	}
}
