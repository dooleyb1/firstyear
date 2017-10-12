import java.util.Scanner;

import javax.swing.JOptionPane;

public class IncrementalStatistics {

	public static void main(String[] args) {
		
		System.out.println("This program computes the average and variance of all numbers entered.");
		
		boolean finished = false;
		int numberCount =1;
		double previousAverage =0;
		double newAverage =0;
		double variance =0;
		
		Scanner input = new Scanner( System.in );
		System.out.println("Enter a number (or type 'exit'):");
		
		if (input.hasNextDouble())
		{
			//Average Computation
			double initialInput = input.nextInt();
			newAverage = initialInput/numberCount;
			
			//Variance Compuation
			double deviationFromAvg = initialInput - newAverage;
			double deviationSquared = deviationFromAvg * deviationFromAvg;
			variance = deviationSquared / numberCount; 
			
			previousAverage = newAverage;
			numberCount++;
			
			System.out.println("So far the average is " +newAverage+ "and the variance is " +variance+ ".");
			
			do
			{
				Scanner input2 = new Scanner( System.in );
				System.out.println("Enter a number (or type 'exit'):");
				
				if (input2.hasNextDouble())
				{
					//New Average Computation
					double numberInput = input2.nextDouble();
					newAverage = ((previousAverage * (numberCount-1)) + numberInput) / numberCount;
					
					//New Variance Computation
					variance = (variance * (numberCount-1)) + ((numberInput - previousAverage) * (numberInput - newAverage));
				    variance = variance / numberCount;
				    
				    previousAverage = newAverage;
				    numberCount++;
				    
				    System.out.println("So far the average is " +newAverage+ " and the variance is " +variance+ ".");
					
				}
				else if (input2.hasNext("exit"))
				{
					finished = true;
					System.out.println("Goodbye.");
				}
			    else
			    {
			    System.out.println("Error, invalid input.");
			    }
		     } 
			 while (!finished);
		}
		else if (input.hasNext("exit"))
		{
			finished = true;
			System.out.println("Goodbye.");
		}
	    else
	    {
	    System.out.println("Error, invalid input.");
	    }
	}
}
