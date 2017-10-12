import javax.swing.JOptionPane;

/*
 * Write a program to determine what class (amphibian, reptile, fish, mammal, or bird)
 * a vertebrate is..
 * Class      Blood  Covering     Fins?
 * -----      -----  --------     -----
 * Amphibian  Cold   Moist skin	  No fins
 * Reptile    Cold   Scales       No fins
 * Fish       Cold   Scales       Fins
 * Mammal     Warm   Hair or fur  No fins
 * Bird       Warm   Feathers     No fins
 */
public class Vertebrates {

	public static void main(String[] args) {

		int answer = JOptionPane.showConfirmDialog(null, "Is the animal cold blooded?");
		boolean coldBlooded = (answer == JOptionPane.YES_OPTION);
		if (!coldBlooded)
		{
			answer = JOptionPane.showConfirmDialog(null, "Does the animal have wings and feathers?");
			boolean hasWings = (answer == JOptionPane.YES_OPTION);
			if (hasWings)
				JOptionPane.showMessageDialog(null, "The animal is a bird.");
			else JOptionPane.showMessageDialog(null, "The animal is a mammal.");
		}
		else
		{
			answer = JOptionPane.showConfirmDialog(null, "Does the animal have scales?");
			boolean hasScales = (answer == JOptionPane.YES_OPTION);
			if (hasScales)
			{
				answer = JOptionPane.showConfirmDialog(null, "Does the animal have any fins?");
				boolean hasFins = (answer == JOptionPane.YES_OPTION);
				if (hasFins)
					JOptionPane.showMessageDialog(null, "The animal is a fish.");
				else JOptionPane.showMessageDialog(null, "The animal is a reptile.");
			}
			else JOptionPane.showMessageDialog(null, "The animal is an amphibian.");
		}
	}

}