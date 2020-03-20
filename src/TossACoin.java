import java.util.Random;

public class TossACoin 
{
	public static void main(String[] args) 
	{
		Coin myCoin = new Coin();

		myCoin.toss();

		System.out.print("Result: " + myCoin.getSideUp());
	}
}

class Coin 
{
	String sideUp;

	public void toss() 
	{
		Random rand = new Random();
		// Get a random value, 0 or 1.
		int value = rand.nextInt(2);

		if (value == 0) 
			this.sideUp = "heads";
		else
			this.sideUp = "tails";
	}
	public String getSideUp() 
	{
		return sideUp;
	}
}
