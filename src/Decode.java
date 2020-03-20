//In order for the program to work, you need to type in a phrase backward with symbols between each character. Example is: o5g s0t2e3l This outputs lets go. Good luck and let the secret message be decoded.
import java.util.Scanner;
public class Decode
{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		
		StringBuilder sb = new StringBuilder(str);
	   StringBuilder result = new StringBuilder();

	   for(int i=0; i<str.length(); i++){
	   		if(Character.isLetter(sb.charAt(i)) || sb.charAt(i)==' '){
	result.append(sb.charAt(i));
}
	   }
	   
	   System.out.println(result.reverse().toString());
	}
}