import java.util.Scanner;
import javax.swing.JOptionPane;

public class BMIJoptionPane {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String weightInput = JOptionPane.showInputDialog("What is your weight in Kilograms?");
		Scanner weightScanner = new Scanner (weightInput);
		double weight = weightScanner.nextDouble();
		String heightInput = JOptionPane.showInputDialog("What is your height in metres?");
		Scanner heightScanner = new Scanner (heightInput);
		double height = heightScanner.nextDouble();
		double heightsquared = height * height;
		double BMI = weight / heightsquared;
		JOptionPane.showMessageDialog(null, "Your Body Mass Index (BMI) is " + BMI);
		

	}

}
