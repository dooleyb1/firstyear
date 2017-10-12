
public class Pictogram{
	double value;
	String label;
	double height;
	double pictureHeight;
	int numberOfPictures;
	Pictogram(int value, String label, int pictureHeight, int end, int start) {
		this.value = value;
		this.label = (label + " (" + value + ")");
		this.height = value;
		this.pictureHeight = pictureHeight;
		this.numberOfPictures = (int)(height/pictureHeight);
		if(numberOfPictures * 30 > (end - start))
		{
			this.numberOfPictures = ((end - start)/pictureHeight);
		}
	}
}
