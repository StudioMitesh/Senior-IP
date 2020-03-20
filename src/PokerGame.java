import java.util.ArrayList;
import java.util.Collections;

public class PokerGame {
	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		
		int players = 4;
		
		
		for (int i = 1; i <= players; i++) {
			Hand hand = new Hand(deck);
			System.out.println("PLAYER " + i + ":");
			hand.showCards();
			hand.showResult();
			System.out.println();
		}
	}
}

class Card {
	short rank, suit;
	String[] suits = {"Hearts", "Spades", "Diamonds", "Clubs"};
	static String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

	public static String getRank(int _rank) {
		return ranks[_rank];
	}

	Card(short suit, short rank) {
		this.rank = rank;
		this.suit = suit;
	}
	
	@Override
	public String toString() {
		return ranks[rank] + " of " + suits[suit];
	}
		public short getRank() {
		return rank;
	}
		public short getSuit() {
		return suit;
	}
}

class Deck {
	/* a deck of 52 cards */
	ArrayList<Card> cards;

	Deck() {
		cards = new ArrayList<>();
		for (short a = 0; a <= 3; a++) {
			for (short b = 0; b <= 12; b++) {
				cards.add(new Card(a,b));
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card drawCard() {
		return cards.remove(cards.size()-1);
	}
}

class Hand {
	/* a hand represents 5 cards */
	Card[] cards;
	int[] value;

	Hand(Deck d) {
		value = new int[6];
		cards = new Card[5];
		for (int x = 0; x < 5; x++) {
			cards[x] = d.drawCard();
		}

		int[] ranks = new int[14];
		int[] orderedRanks = new int[5];
		boolean flush = true, straight = false;
		int sameCards = 1, sameCards2 = 1;
		int largeGroupRank = 0, smallGroupRank = 0;
		int index = 0;
		int topStraightValue = 0;
		
	
		for (int x = 0; x <= 13; x++) {
			ranks[x] = 0;
		}
		for (int x = 0; x <= 4; x++) {
			ranks[cards[x].getRank()]++;
		}
		for (int x = 0; x < 4; x++) {
			if (cards[x].getSuit() != cards[x+1].getSuit())
				flush = false;
		}
		for (int x = 13; x >= 1; x--) {
			if (ranks[x] > sameCards) {
				if (sameCards == 1) {
					largeGroupRank = x;
				}
				else {
					sameCards2 = sameCards;
					smallGroupRank = x; 
				}
				sameCards = ranks[x];
			} 
			else if (ranks[x] > sameCards2) {
				sameCards2 = ranks[x];
				smallGroupRank = x;
			}
		}
		if (ranks[1] == 1) {
			orderedRanks[index] = 14;
			index++;
		}
		for (int x = 13; x >= 2; x--) {
			if (ranks[x] == 1) {
				orderedRanks[index] = x;
				index++;
			}
		}
		for (int x = 1; x <= 9; x++) {
			if (ranks[x] == 1 && ranks[x+1] == 1 && ranks[x+2] == 1 && ranks[x+3] == 1 && ranks[x+4] == 1) {
				straight=true;
				topStraightValue = x + 4;
				break;
			}
		}
		if (ranks[10] == 1 && ranks[11] == 1 && ranks[12] == 1 && ranks[13] == 1 && ranks[1] == 1) {
			straight = true;
			topStraightValue = 14;
		}
		for (int x = 0; x <= 5; x++) {
			value[x] = 0;
		}

		
		if (sameCards == 1) {
			value[0] = 1;
			value[1] = orderedRanks[0];
			value[2] = orderedRanks[1];
			value[3] = orderedRanks[2];
			value[4] = orderedRanks[3];
			value[5] = orderedRanks[4];
		}
		// Pair
		if (sameCards == 2 && sameCards2 == 1) {
			value[0] = 2;
			value[1] = largeGroupRank; //rank of pair
			value[2] = orderedRanks[0];
			value[3] = orderedRanks[1];
			value[4] = orderedRanks[2];
		}
		// Two pair
		if (sameCards == 2 && sameCards2 == 2) {
			value[0] = 3;
			//rank of greater pair
			value[1] = largeGroupRank > smallGroupRank ? largeGroupRank : smallGroupRank;
			value[2] = largeGroupRank < smallGroupRank ? largeGroupRank : smallGroupRank;
			value[3] = orderedRanks[0]; //extra card
		}
		// Three of a kind
		if (sameCards == 3 && sameCards2 != 2) {
			value[0] = 4;
			value[1] = largeGroupRank;
			value[2] = orderedRanks[0];
			value[3] = orderedRanks[1];
		}
		// Straight
		if (straight && !flush) {
			value[0] = 5;
			value[1] = topStraightValue;
		}
		// Flush
		if (flush && !straight) {
			value[0] = 6;
			value[1] = orderedRanks[0];
			value[2] = orderedRanks[1];
			value[3] = orderedRanks[2];
			value[4] = orderedRanks[3];
			value[5] = orderedRanks[4];
		}
		// Full House
		if (sameCards == 3 && sameCards2 == 2) {
			value[0] = 7;
			value[1] = largeGroupRank;
			value[2] = smallGroupRank;
		}
		// Four of a kind 
		if (sameCards == 4) {
			value[0] = 8;
			value[1] = largeGroupRank;
			value[2] = orderedRanks[0];
		}
		// Straight flush
		if (straight && flush) {
			value[0] = 9;
			value[1] = topStraightValue;
		}
	}

	void showResult() {
		String s;
		switch(value[0]) {
			case 1:
				s = "High Card";
				break;
			case 2:
				s = "Pair of " + Card.getRank(value[1]) + "s";
				break;
			case 3:
				s = "Two pair: " + Card.getRank(value[1]) + "s and " + Card.getRank(value[2]) + "s";
				break;
			case 4:
				s = "Three of a kind: " + Card.getRank(value[1]) + "s";
				break;
			case 5:
				s = Card.getRank(value[1]) + " high Straight";
				break;
			case 6:
				s = "Flush";
				break;
			case 7:
				s = "Full House: " + Card.getRank(value[1])+ "s over " + Card.getRank(value[2]) + "s";
				break;
			case 8:
				s = "Four of a kind " + Card.getRank(value[1]) + "s";
				break;
			case 9:
				s = "Straight flush " + Card.getRank(value[1]) + " high";
				break;
			default:
				s = "Invalid hand";
		}
		System.out.println("Hand Result: " + s);
	}

	void showCards() {
		for (int x = 0; x < 5; x++)
			System.out.println(cards[x]);
	}
}
