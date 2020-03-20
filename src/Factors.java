import java.util.ArrayList;

 /* The factors of a number are all those numbers that can divide evenly into the number with no remainder. */
public class Factors
{
	public static void main(String[] args) 
	{
		int number = 128; // a sample number
		
		ArrayList<Integer> factors = new ArrayList<>();
		int factor = 1;
		while(factor <= number) {
			if(number % factor == 0) {
				factors.add(factor);
			}
			factor++;
		}
		
		System.out.println("All factors of " + number + " are: ");
		System.out.println(factors.toString());
	}
}
