import java.util.Scanner;
import javax.swing.JOptionPane;

public class VertabrateClass {

	public static void main(String[] args) {
		
	int blood = JOptionPane.showConfirmDialog (null, "Does the animal have cold blood?");
	boolean coldBlood = (blood == JOptionPane.YES_OPTION);
	boolean warmBlood = (blood == JOptionPane.NO_OPTION);
		
	if (coldBlood)
		{
			int fins = JOptionPane.showConfirmDialog(null, "Does the animal have fins?");
			boolean hasFins = (fins == JOptionPane.YES_OPTION);
			boolean noFins = (fins == JOptionPane.NO_OPTION);
			
			if (hasFins) 
			{
				JOptionPane.showMessageDialog(null, "The vertabrate is a fish.");
			}
			else if (noFins)
			{
				int scales = JOptionPane.showConfirmDialog(null, "Does the animal have scales?");
				boolean hasScales = (scales == JOptionPane.YES_OPTION);
				boolean noScales = (scales == JOptionPane.NO_OPTION);
				
				if (hasScales)
				{
					JOptionPane.showMessageDialog(null, "The vertabrate is a reptile.");
				}
				else if (noScales)
				{
					JOptionPane.showMessageDialog(null, "The vertabrate is an amphibian.");
				}
			}
		}
	else if (warmBlood)
		{
			int feathers = JOptionPane.showConfirmDialog(null, "Does this animal have feathers?");
			boolean hasFeathers = (feathers == JOptionPane.YES_OPTION);
			boolean noFeathers = (feathers == JOptionPane.NO_OPTION);
			
			if (hasFeathers)
			{
				JOptionPane.showMessageDialog(null, "The vertabrate is a bird.");
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "The vertabrate is a mammal.");
			}
		}
	else
		{
			JOptionPane.showMessageDialog(null, "ERROR! Can not determine class of vertabrate.");
		}
	}
}
