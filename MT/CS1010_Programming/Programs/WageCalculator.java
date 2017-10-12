import java.util.Scanner;
import javax.swing.JOptionPane;

public class WageCalculator {

	public static void main(String[] args) {
		
		final double STANDARD_RATE = 9.10;
		final double USC_RATE = 0.0094532755298651;
		
		String weekdays = JOptionPane.showInputDialog("Enter the amount of hours you worked on Monday to Saturday.");
		Scanner weekdayScanner = new Scanner( weekdays );

		double weekdayHours = weekdayScanner.nextDouble();
		
		String sundays = JOptionPane.showInputDialog("Enter the amount of hours you worked on Sunday.");
		Scanner sundayScanner = new Scanner( sundays );
		
		double sundayHours = sundayScanner.nextDouble();
		
		double sundayPremiumHours = (sundayHours / 3);
		
		double totalHours = weekdayHours + sundayHours + sundayPremiumHours;
		double grossPay = totalHours * STANDARD_RATE;
		double totalDeductions = totalHours * USC_RATE;
		double netPay = grossPay - totalDeductions;
		
		JOptionPane.showMessageDialog(null, "Your expected net pay for this week is " + netPay);
	}

}
