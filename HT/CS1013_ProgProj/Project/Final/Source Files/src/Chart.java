public class Chart {
	
	int numberOfBars;
	int graphWidth;
	int graphStartY;
	int graphEndY;
	int graphStartX;
	int graphEndX;
	int highestValue;
	Bar[] bars;
	String yAxis;
	
	Chart(int barCount, String yAxisLabel, double values, String labels)
	{
		numberOfBars = barCount;
		yAxis = yAxisLabel;
		graphStartX = Housing.SCREEN_WIDTH/8;
		graphEndX = Housing.SCREEN_WIDTH - graphStartX;

		graphStartY = Housing.SCREEN_HEIGHT/12;
		graphEndY = Housing.SCREEN_HEIGHT - graphStartY;
		
		graphWidth = (((graphEndX - graphStartX) - (10 * numberOfBars))/numberOfBars)/3;
		
		highestValue = 0;
		bars = new Bar[numberOfBars];
		for(int i = 0; i < bars.length; i++)
		{
			Bar tempBar = new Bar(values, labels);
			bars[i] = tempBar;
			if(bars[i].value > highestValue)
			{
				highestValue = (int)bars[i].value;
			}
		}
	}
	
	Chart(int barCount, String yAxisLabel, double[] values, String[] labels)
	{
		numberOfBars = barCount;
		yAxis = yAxisLabel;
		graphStartX = Housing.SCREEN_WIDTH/8;
		graphEndX = Housing.SCREEN_WIDTH - graphStartX;

		graphStartY = Housing.SCREEN_HEIGHT/12;
		graphEndY = Housing.SCREEN_HEIGHT - graphStartY;
		
		graphWidth = (((graphEndX - graphStartX) - (10 * numberOfBars))/numberOfBars)/3;
		
		highestValue = 0;
		bars = new Bar[numberOfBars];
		for(int i = 0; i < bars.length; i++)
		{
			Bar tempBar = new Bar(values[i], labels[i]);
			bars[i] = tempBar;
			if(bars[i].value > highestValue)
			{
				highestValue = (int)bars[i].value;
			}
		}
	}
}
