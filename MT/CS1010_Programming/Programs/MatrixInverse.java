import javax.swing.JOptionPane;
import java.util.Scanner;

public class MatrixInverse {

	public static void main(String[] args) {
		
		String input = JOptionPane.showInputDialog("Enter the 3x3 matrix. (a11, a12, a13, a21...etc)");
		Scanner inputScanner = new Scanner( input );
		//inputScanner.useDelimiter(", ");
		
		int a11 = inputScanner.nextInt();
		int a12 = inputScanner.nextInt();
		int a13 = inputScanner.nextInt();
		
		int a21 = inputScanner.nextInt();
		int a22 = inputScanner.nextInt();
		int a23 = inputScanner.nextInt();
		
		int a31 = inputScanner.nextInt();
		int a32 = inputScanner.nextInt();
		int a33 = inputScanner.nextInt();
		
		if (a11 != 1)
		{
			a11 = a11/a11;
			a12 = a12/a11;
			a13 = a13/a11;
		}
		
	}
}