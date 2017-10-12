import java.util.Scanner;
import javax.swing.JOptionPane;

public class PowersOf2 {

	public static void main(String[] args) {
		
		String input = JOptionPane.showInputDialog("Enter base:");
		Scanner scanner = new Scanner( input );
		double base = scanner.nextDouble();
		input = JOptionPane.showInputDialog("Enter exponent:");
		scanner = new Scanner( input );
		double exponent = scanner.nextDouble();
		
		try
		{
			if (exponent > 0)
			{
				double result = 1;
				
				for (int i=0; (i < exponent); i++)
				{
					result *= base;
				}
				if (result >= Double.MAX_VALUE)
				{
					System.out.println("ERROR, Result is too large.");
				}
				else
				{
					System.out.print("The result is: " + result);	
				}
			}
			else 
			{
				System.out.println("ERROR, Invalid exonent number");
			}
		}
		catch (NullPointerException exception)
		{
		}
		catch (java.util.NoSuchElementException exception)
		{
		}
  }
} 
