import java.util.Scanner;
import javax.swing.JOptionPane; 

public class StandardDev {

	public static void main(String[] args) {
		
		String numInputs = JOptionPane.showInputDialog("Please enter three numbers seperated by a space.");
		Scanner inputScanner = new Scanner(numInputs);
		
		double numOne = inputScanner.nextDouble();
		double numTwo = inputScanner.nextDouble();
		double numThree = inputScanner.nextDouble();
		
		inputScanner.close();
		
		double average = (numOne + numTwo + numThree) / 3.0;
		double topLine = (numOne - average) * (numOne - average) * (numTwo - average) * (numTwo - average) * (numThree - average) * (numThree - average);
		double innerTotal = topLine / 3.0;
		double standardDev = Math.sqrt(innerTotal);
		
		JOptionPane.showMessageDialog(null, "The standard deviation of the numbers " + numOne + "," + numTwo + "," + numThree + " is " + standardDev +".");
		
		
		

	}

}
