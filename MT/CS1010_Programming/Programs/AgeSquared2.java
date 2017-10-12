import java.util.*;

public class AgeSquared2 {

	public static void main(String[] args) {
		
		Calendar maxBirthYear = Calendar.getInstance();
		
		final int CURRENT_YEAR = maxBirthYear.get(Calendar.YEAR);
		final int MAX_AGE = 123;
		final int MIN_AGE = 0;
		
		int age=0;
		for (age =1; age<=MAX_AGE; age++)
		{
			int ageSquared = age*age;
			int birthYear = ageSquared - age;
			int currentAge = CURRENT_YEAR - birthYear;
			
			if (currentAge<=MAX_AGE && currentAge>=MIN_AGE)
			{
				System.out.println("If you are " +age+ " in the year " +ageSquared+" then your age squared is also "
						+ageSquared+ ".");
			}
			else if (currentAge>=MAX_AGE && currentAge<=MIN_AGE)
			{
				System.out.println("It is not possible for anyone who is alive today to be living during a year that is also "
						+ "the square of their age.");
				
			}
		}

	}

}
