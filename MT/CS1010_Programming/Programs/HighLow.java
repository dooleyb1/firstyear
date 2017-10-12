import java.util.Random;
import java.util.Scanner;

/*
 * Write a program to support the High-Low game:
 * Give a user 5 tries to guess a number between 0 and 20 (inclusive).  For each incorrect
 * guess tell the user if the guess is too big or too small.  At the end tell them how
 * many tries it took them (if they got it right) or  what the number was (if they didn?t
 * guess it correctly).
 */
public class HighLow {

	public static final int GUESSES_ALLOWED = 5;
	public static final int MAX_NUMBER = 20;
	public static final int UNKNOWN_NUMBER = -1;
	
	public static void main(String[] args) {

	    Random  generator = new Random();
	    int numberToGuess = generator.nextInt(MAX_NUMBER+1);
	    int numberGuessed = UNKNOWN_NUMBER;
	    int count;

	    for (count = 0; (count < GUESSES_ALLOWED) &&
	             (numberGuessed != numberToGuess); count++)
	    {
	      Scanner input = new Scanner( System.in );
	      System.out.print("Guess the number (between 0 and 20): ");
	      numberGuessed = input.nextInt();
	      if (numberGuessed < numberToGuess)
	        System.out.println("" + numberGuessed + " is too low.");
	      else if (numberGuessed > numberToGuess)
	        System.out.println("" + numberGuessed +
	                                               " is too high.");
	    }

	    if (numberGuessed == numberToGuess)
	      System.out.println("Well done.  You got it in " + count +
	                                                 " guesses.");
	    else System.out.println("Hard luck.  The number was " + 
	                                       numberToGuess + ".");
	}

}