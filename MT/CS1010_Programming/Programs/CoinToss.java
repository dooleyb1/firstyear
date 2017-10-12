import java.util.Random;

public class CoinToss {
	
	public static final int MAX_TOSSES = 9999;
	
	public static void main(String[] args) {
		
		int tossNumber = 0;
		int numberOfHeads = 0;
		int numberOfTails = 0;
		String lastFlipResult = null;
		
		for (int tossNumber1 = 0; (tossNumber1 < MAX_TOSSES); tossNumber1 ++ )
		{
			Random generator = new Random (2);
			int randomNumber = generator.nextInt(2);
			
			if (randomNumber == 1)
			{
				numberOfHeads ++ ;
			}
			else
			{
				numberOfTails ++ ;
			}
		}
		
		Random generator = new Random (2);
		int lastFlip = generator.nextInt(2);
		
		if (lastFlip == 1)
		{
			numberOfHeads ++;
			lastFlipResult = "heads";
		}
		else
		{
			numberOfTails ++;
			lastFlipResult = "tails";
		}
		
		System.out.print("Over 10,000 tosses there was " + numberOfHeads + " heads and " + numberOfTails +"tails. The result of the"
				+ "last flip was " + lastFlipResult +".");

	}

}
