import java.util.ArrayList;

public class playerHand {

	private boolean condition;
	private ArrayList<Card> hand;

	//Create players' hands
	public playerHand() {
		hand = new ArrayList<Card>();
	}

	//Add card to player's Hand
	public void add(Card card) {
		hand.add(card);
	}

	//Removes 3 cards from player's hand for trading in
	public void removeFromHand(int card1, int card2, int card3) {

		if (canTradeCards(card1, card2, card3)) {
			hand.remove(card1);
			hand.remove(card2);
			hand.remove(card3);

			//Possibly add method call to add army to player

		} else {
			System.out.println("You need 3 cards with the same type of army or 1 card with each type of army");
		}
	}

	//Return true if player can trade in cards for armies
	public boolean canTradeCards(int card1, int card2, int card3) {

		condition = false;

		//If player has 3 cards with the same armyType
		if (hand.size() >= 3) {
			if (hand.get(card1).getType().equals(hand.get(card2).getType()) && hand.get(card2).getType().equals(hand.get(card3).getType())) {
				condition = true;

			}

			//If player has 3 cards, each with a different armyType
			 else if (
				!hand.get(card1).getType().equals(hand.get(card2).getType()) && !hand.get(card1).getType().equals(hand.get(card3).getType())
				&& !hand.get(card2).getType().equals(hand.get(card3).getType())) {
				condition = true;
			}
		}
		return condition;
	}

	//Return player's hand
	public ArrayList<Card> getHand() {
		return hand;
	}

}
