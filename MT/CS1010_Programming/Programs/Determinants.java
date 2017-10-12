import javax.swing.JOptionPane;
import java.util.Scanner;

public class Determinants {

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
		
		int innerMatrix1 = (a22*a33) - (a32*a23);
		int innerMatrix2 = (a21*a33) - (a31*a23);
		int innerMatrix3 = (a21*a32) - (a31*a22);
		
		int outerDet1 = a11*innerMatrix1;
		int outerDet2 = (a12*-1)*(innerMatrix2);
		int outerDet3 = a13*innerMatrix3;
		
		int result = outerDet1 + outerDet2 + outerDet3;
		System.out.println("The determinant of the stated matrix is: " + result + ".");
		
		

	}

}
