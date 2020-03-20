import java.util.Calendar;

public class Days
{
	public static void main(String[] args)
	{
		Calendar cal = Calendar.getInstance();
		int days = cal.get(Calendar.DAY_OF_YEAR);
		System.out.println(days);
	}
}
