import java.util.ArrayList;
import java.util.Collections;

//Create a deck with 42 cards
//Ignores wild cards and secret mission cards
public class Deck {

	private String input;
	private String name;
	private String[] typesArray;
	private ArrayList<Card> deck;
	private ArrayList<Country> countries;
	private Card drawCard;


// 42 cards
// Each card includeds armyType and unique country
// 14 cards of each army type (Infantry, Cavalry, Artillery)
//  1 of each country

	public Deck (ArrayList<Country> countries) {

		//armyTypes of cards
		typesArray = new String[]{ "Infantry", "Cavalry", "Artillery" };

		deck = new ArrayList<Card>();

    //Fill deck with cards
    //14 of each army type, unique country on each card
		for (int i=0; i<countries.size(); i++) {
			deck.add(new Card(typesArray[i/14], countries.get(i)));
			System.out.println("Added new card to deck: " + deck.get(i).getName());
		}

    //Shuffle deck when all cards are added
		Collections.shuffle(deck);
	}

	//Remove card from deck and add to hand
	public Card drawCard() {

		drawCard = deck.get(0);
		deck.remove(0);

		return drawCard;
	}

	//Add cards to the deck
	public void add(Card card) {
		deck.add(card);
	}

	//Shuffle deck
	public void shuffle() {
		Collections.shuffle(deck);
	}
}
