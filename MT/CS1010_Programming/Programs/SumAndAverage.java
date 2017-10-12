import java.util.Scanner;

/*
 * Write a program to determine the total and average of any number of whole numbers
 * input by the user.
 * Ensure that all possible error conditions are handled.
 */
public class SumAndAverage {

	public static void main(String[] args) {
		int total = 0;
		int numberCount = 0;
		boolean finished = false;
		do
		{
			Scanner input = new Scanner( System.in );
			System.out.print("Enter a whole number (or type 'exit'): ");
			if (input.hasNextInt())
			{
				total += input.nextInt();
				numberCount++;
			}
			else if (input.hasNext("exit"))
			{
				finished = true;
			}
			else System.out.println("Not a valid whole number.  Try again.");
		} while (!finished);
		
		if (numberCount > 0)
		{
			double average = ((double) total) / ((double) numberCount);
			System.out.println("Total is " + total + ".  Average is " + average);
		}
		else System.out.println("No numbers entered.");
	}

}