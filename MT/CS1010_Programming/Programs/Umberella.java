import javax.swing.JOptionPane;

public class Umberella {

	public static void main(String[] args) {
		
		int answer = JOptionPane.showConfirmDialog (null, "Is it raining?", 
				null, JOptionPane.YES_NO_OPTION);
		
		boolean raining = (answer == JOptionPane.YES_OPTION);
		
		if (raining)
		{
			JOptionPane.showMessageDialog(null, "Bring an umberella with you and put it up as soon"
					+ " as you leave the house.");
		}
		else
		{
			int potentialRain = JOptionPane.showConfirmDialog(null, "Does it look like it might rain?", 
					null, JOptionPane.YES_NO_OPTION);
			
			boolean possibleRain = (potentialRain == JOptionPane.YES_OPTION);
			
			if (possibleRain)
			{
				JOptionPane.showMessageDialog(null, "You should probably bring an umberella with you "
						+ "just to be safe, but there is no need to put it up just yet.");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "There doesn't seem to be any need to bring an"
						+ " umberella with you. Enjoy your day!");
			}
		}
	}

}
