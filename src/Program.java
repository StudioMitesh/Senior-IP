import java.util.*;
class Player{
  Random rand=new Random();
  int attackPower= (rand.nextInt(5)+15);
   public int health=100;
   
	public Player(){
			System.out.println("player created");
		} 
		
		public int attack(Player attacked){
		  attacked.health-=attackPower;
		  return attacked.health;
		}
		
}


public class Program
{
	public static void main(String[] args) {
	
		Player one = new Player();
		Player two = new Player();
		
		System.out.println("player1 health: "+one.health);
		System.out.println("computer health: "+two.health);
		
   while((one.health>0)&&(two.health>0)){  
	 if(one.health>0){
	  
	  two.health=one.attack(two);
	  }
	  if(two.health>0){
		  one.health=two.attack(one);
	  }
	  if((one.health>=0)||(two.health>=0)){  
	  
	  System.out.println("computer health: "+two.health);
	  System.out.println("player1 health: "+one.health);
	  }
	  if(one.health<=0){
		  System.out.println("computer wins");
	  }
	  if(two.health<=0){
		  System.out.println("player1 wins");
	  }
	  
	  }
	}
}