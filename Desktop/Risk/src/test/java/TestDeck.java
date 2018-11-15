import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.beans.Transient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.io.File;

//Create a deck with 42 cards
//Ignores wild cards and secret mission cards
public class TestDeck {

	  @Test
    public void addInvalidArmyType() {
    Deck deck = new Deck();
     Card invalidCard = new Card("InvalidInfantry", "Alberta");
     deck.add(invalidCard);
    }

    @Test
    public void addInvalidCountry() {
        Deck deck = new Deck();
        Card invalidCard = new Card("Infantry", "InvalidAlberta");
        deck.add(invalidCard);
    }

    @Test
    public void addEmptyCard() {
        Deck deck = new Deck();
        Card invalidCard = new Card("", "");
        deck.add(invalidCard);

    }

    @Test
    public void addNullCard() {
        Deck deck = new Deck();
        Card nullCard = new Card(null, null);
        deck.add(nullCard);

    }

    @Test
    public void testShuffle() {
        Deck deck = new Deck();
        deck.shuffle();

    }

}
