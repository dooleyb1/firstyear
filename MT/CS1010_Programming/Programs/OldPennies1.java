import java.util.Scanner;
import javax.swing.JOptionPane;

public class OldPennies1 {

    public static final int OLD_TO_NEW_PENNIES_RATE = 67 ;
    public static final int OLD_TO_NEW_SHILLINGS_RATE = 12 * OLD_TO_NEW_PENNIES_RATE;
    public static final int OLD_TO_NEW_POUNDS_RATE = 20 * OLD_TO_NEW_SHILLINGS_RATE;

    public static void main(String[] args) {
   	 
   	  String amountInput = JOptionPane.showInputDialog("Please enter the amount of old English currency you would like to convert. "
   	  		+ "(Pounds, Shillings, Pennies)");
   	 
   	  Scanner inputScanner = new Scanner(amountInput).useDelimiter(", ");
   	 
   	  int oldPounds = inputScanner.nextInt();
   	  int oldShillings = inputScanner.nextInt();
   	  int oldPennies = inputScanner.nextInt();

   	  inputScanner.close();
   	 
   	  //Converting everything to new pennies
   	 
   	  int newPennies = oldPennies * OLD_TO_NEW_PENNIES_RATE;
   	  int newShillingsInPennies = oldShillings * OLD_TO_NEW_SHILLINGS_RATE;
   	  int newPoundsInPennies = oldPounds * OLD_TO_NEW_POUNDS_RATE;
   	  
   	  int totalNewPennies = newPennies + newShillingsInPennies + newPoundsInPennies;
   	  int newPounds = totalNewPennies / 100;
   	  int newPoundsRemainder = totalNewPennies % 100;
   	  
   	  if (newPoundsRemainder > 10)
   		  {
   		   JOptionPane.showMessageDialog(null, "The equivalent amount in todays Sterling is £" + newPounds + "." + newPoundsRemainder + ".");
   		  }
   	  else if (newPoundsRemainder < 10)
   	  {
   	  JOptionPane.showMessageDialog(null, "The equivalent amount in todays Sterling is £" + newPounds + "." + newPoundsRemainder + "0.");
   	  }
    }

}


