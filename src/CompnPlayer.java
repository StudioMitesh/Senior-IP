import java.util.Scanner;
import java.util.Random;

public class CompnPlayer {
  public static void main(String... args) {
	Scanner i = new Scanner(System.in);
	
	System.out.println("\nGuess a number between 1 and 6.\nIf it matched the computer's guess, YOU WIN!, else YOU LOSE!\n");
	int nPlayer = i.nextInt();

	if (nPlayer<1 || nPlayer >6){
		System.out.println("Error:\nThis Game will not work!!!\nInput a number between '1' and '6'.");
	}else{
	
	Random r = new Random();
	
	int nComp = 1 + r.nextInt(6);
	
	if(nPlayer == nComp){
		System.out.printf("%s\n%s\t%d\n%s\t%d", "YOU WIN!!!!!!!!!!\n", "Computer's GUESS was: ", nComp, "Player's GUESS was: ", nPlayer);
		System.out.println("\nCONGRATULATIONS!!!\n");
	}else{
		System.out.printf("%s\n%s\t%d\n%s\t%d", "YOU LOSE!!!!!\n", "Computer's GUESS was: ", nComp, "Player GUESS's was: ", nPlayer);
		System.out.println("\n\n\nRUN the game to PLAY AGAIN!");
	}
		}
  }
}