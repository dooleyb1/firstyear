import java.util.Scanner;

import javax.swing.JOptionPane;

public class MaxMinTutorial {

	public static void main(String[] args) {
		
		int maxNum = 0;
		int minNum = 0;
		
		
		String question = JOptionPane.showInputDialog("Enter a list of numbers e.g 21, 48, 270, 775, 1, 2");
		Scanner input = new Scanner(question);
		input.useDelimiter(", ");
		
		/*
		Scanner input = new Scanner (System.in);
		System.out.print("Enter a list of numbers on one line seperated by spaces.");
		*/
		
	 while(input.hasNextInt())
	 {
		int temp = input.nextInt();
		
		if (temp >= maxNum)
		{
			maxNum = temp;
		}
		else if (temp <= minNum)
		{
			minNum = temp;
		}
	 }
	 
	 System.out.println("The maximum number in the list is " +maxNum+ " and the minimum number is " +minNum+ ".");
	 
	}

}
