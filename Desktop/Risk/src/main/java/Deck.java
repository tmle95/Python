import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.File;

//Create a deck with 42 cards
//Ignores wild cards and secret mission cards
public class Deck {

	private String[] typesArray;
	private ArrayList<Card> deck;
	//private ArrayList<Country> countries;
	public String[] countries = {"Alaska","Alberta","Central America","Eastern United States",
								"Greenland","Northwest Territory","Ontario","Quebec","Western United States",
								"Argentina","Brazil","Venezuela","Great Britain","Iceland","Northern Europe",
								"Scandinavia","Southern Europe","Ukraine","Western Europe","Congo","East Africa",
								"Egypt","Madagascar","North Africa","South Africa","Afghanistan","China","India",
								"Irkutsk","Japan","Kamchatka","Middle East","Mongolia","Siam","Siberia","Ural",
								"Yakutsk","Eastern Australia","Indonesia","LotR","New Guinea","Western Australia"};
	private Card drawCard;


// 42 cards
// Each card includeds armyType and unique country
// 14 cards of each army type (Infantry, Cavalry, Artillery)
//  1 of each country

	public Deck () {

		//armyTypes of cards
		typesArray = new String[]{ "Infantry", "Cavalry", "Artillery" };

		deck = new ArrayList<Card>();

    //Fill deck with cards
    //14 of each army type, unique country on each card
		for (int j=0; j<countries.length; j++) {
			deck.add(new Card(typesArray[j/14], countries[j]));
			//System.out.println("Added new card to deck: " + deck.get(i).getName());
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
	public boolean shuffle() {
		Collections.shuffle(deck);
		return true;
	}

}
