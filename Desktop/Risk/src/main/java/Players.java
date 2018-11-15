import java.util.ArrayList;
import java.util.HashMap;

public class Players {

  private String sName;
  //private String sColor;
  private int nDice;
  private int nArmies;
  private int nCredits;
  private int nUndos;
  private int numOfCredits;
  private int numOfArmies;
  private HashMap<String, Country> hasCountry;
  private HashMap<String, Continent> hasContinent;
  private playerHand hand;
  private int numOfUndos;

  public Players(String name, int nArmy) {
	  this.sName = name;
	  //this.sColor = color;
	  this.nDice = 0;
	  this.numOfUndos = 0;
	  this.numOfArmies = nArmy;
	  this.numOfCredits = 0;
	  hasCountry = new HashMap<String, Country>();
	  hasContinent = new HashMap<String, Continent>();
	  hand = new playerHand();
  }

  public String getName() {
    return this.sName;
  }

  //public String getColor() {
  //  return this.sColor;
  //}

  public int getNumOfArmies() {
	  return numOfArmies;
  }
  
  public int getNumOfCredits() {
	  return numOfCredits;
  }

  public void setDice(int dice) {
    this.nDice = dice;
  }

  public int getDice() {
    return this.nDice;
  }

  public void displayInfo() {
    System.out.println("=============================================================");
    System.out.println("Name: " + this.sName);
    //System.out.println("Color: " + this.sColor);
    System.out.println("=============================================================");
  }

  public void gainCountry(Country nameOfCountry) {
	  System.out.println(nameOfCountry.getName() + " has been acquired by " + sName + ".");
	  hasCountry.put(nameOfCountry.getName(), nameOfCountry);
  }
  
  public void gainCountry(ArrayList<Country> listOfCountries) {
	  for(int i = 0; i < listOfCountries.size(); i++) {
		  hasCountry.put(listOfCountries.get(i).getName(), listOfCountries.get(i));
	  }
  }
  
  public void lostCountry(String nameOfCountry) {
	  System.out.println(nameOfCountry + " is no longer occupied by " + sName + ".");
	  hasCountry.remove(nameOfCountry);
  }

  public void gainContinent(Continent nameOfContinent) {
	  System.out.println(nameOfContinent.getName() + " is now controlled by " + sName + ", and after every turn will receive " + nameOfContinent.getExtraArmies() + " bonus armies.");
	  hasContinent.put(nameOfContinent.getName(), nameOfContinent);
  }

  public void lostContinent(String nameOfContinent) {
	  hasContinent.remove(nameOfContinent);
  }

  public ArrayList<Country> countriesPlayerHas(){
	  return new ArrayList<Country>(hasCountry.values());
  }
  
  public void gainArmies(int nArmies) {
	  numOfArmies = numOfArmies + nArmies;
	  System.out.println(sName + " has gained " + nArmies + " armies and now has " + numOfArmies + " total armies.");
  }

  public void loseArmies(int nArmies) {
	  numOfArmies = numOfArmies - nArmies;
	  System.out.println(sName + "'s remaining number of armies: " + numOfArmies);
  }
  
  public void gainCredits(int nCredits) {
	  numOfCredits = numOfCredits + nCredits;
	  System.out.println(sName + " has gained " + nCredits + " credits, and now has a total of " + numOfCredits + " credits.");
  }
  
  public void loseCredits(int nCredits) {
	  numOfCredits = numOfCredits - nCredits;
	  System.out.println(sName + "'s remaining available credits: " + numOfCredits);
  }
  
  public void gainRiskCard(Card rCard) {
	  hand.add(rCard);
  }
  
  public void loseCards(int[] cardsTurnedIn) {
	  hand.removeFromHand(cardsTurnedIn[0], cardsTurnedIn[1], cardsTurnedIn[2]);
  }
  
  public ArrayList<Card> getPlayersHand(){
	  return hand.getHand();
  }
  
  public void gainUndo(int nUndos) {
	  numOfUndos = numOfUndos + nUndos;
	  System.out.println(sName + " has gained " + nUndos + " number of undos, and now has a total of " + numOfUndos + " undos.");
  }
  
  public void loseUndo(int nUndos) {
	  numOfUndos = numOfUndos - nUndos;
	  System.out.println(sName + "'s remaining available undos : " + numOfUndos);
  }
}
