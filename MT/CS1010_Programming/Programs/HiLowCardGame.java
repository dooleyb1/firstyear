import java.util.Random;
import javax.swing.JOptionPane;

public class HiLowCardGame {

	public static final int CORRECT_GUESSES_TO_WIN = 4;
	public static final int MAX_NUMBER = 13;
	
	public static void main(String[] args) {
		
		JOptionPane.showMessageDialog(null, "Welcome to the High Low Card Game." + "\nYou will be presented with an initial card in terms of it's number "
				+ "2-10, Jack (11), Queen (12), King (13), Ace (14)." + "\nYou will then need to guess whether the next card will be higher or lower."
				+ "\nIf you guess correctly four times in a row you win." + "\nGood luck!.");
		
		boolean playGame = true;
		int numberOfGuesses = 0;
		
		while(numberOfGuesses < CORRECT_GUESSES_TO_WIN && playGame)
		{
			
	    	Random  generator = new Random();
		    int firstCardNumber = generator.nextInt(MAX_NUMBER);
		    firstCardNumber = firstCardNumber + 2;
		    JOptionPane.showMessageDialog(null, "Your new card number is " + firstCardNumber + ".");
	    	
		    Random  generator2 = new Random();
		    int nextCardNumber = generator2.nextInt(MAX_NUMBER);
		    nextCardNumber = nextCardNumber + 2;
		   
	    	Object[] options = { "Higher", "Lower", "Equal" };
			int guess = JOptionPane.showOptionDialog(null, "What will the next card be?", "Card Options",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
			null, options, options[0]);
			
	    	boolean higherGuess = (guess == JOptionPane.YES_OPTION);
			boolean lowerGuess = (guess == JOptionPane.NO_OPTION);
			boolean equalGuess = (guess == JOptionPane.CANCEL_OPTION);
			
			if (higherGuess && nextCardNumber > firstCardNumber)
			{
				numberOfGuesses ++;
				JOptionPane.showMessageDialog(null, "Correct!");
			}
			else if (lowerGuess && nextCardNumber < firstCardNumber)
			{
				numberOfGuesses ++;
				JOptionPane.showMessageDialog(null, "Correct!");
			}
			else if (equalGuess && nextCardNumber == firstCardNumber)
			{
				numberOfGuesses ++;
				JOptionPane.showMessageDialog(null, "Correct!");
			}
			else
			{
				int continueCondition = JOptionPane.showConfirmDialog(null, "Wrong! Would you like to play again?");
				
				if(continueCondition == JOptionPane.YES_OPTION)
				{
					playGame = true;
					numberOfGuesses =0;
				}
				else
				{
					playGame = false;
				}
			}
	    }
		
		if(numberOfGuesses == CORRECT_GUESSES_TO_WIN)
		{
			JOptionPane.showMessageDialog(null, "Congratulations, you have won!");
			numberOfGuesses = 0;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Goodbye! Thank you for playing.");
		}
	}
}
