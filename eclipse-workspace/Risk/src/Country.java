import java.util.ArrayList;

public class Country {
	private int armies;
	private boolean hasPlayer;
	private String countryName;
	private Players occupant;
	private ArrayList<Country> adjacent;

	public Country(String countryName) {
		this.countryName = countryName;
		armies = 0;
		hasPlayer = false;
		System.out.println("Creating country: " + countryName);
	}
	public String getName() {
		return countryName;
	}
//Used to construct country object
	public void addAdjacent(ArrayList<Country> adjacent) {
		this.adjacent = adjacent;
	}
//Set occupant of country when a player conquers it		
	public void setOccupant(Players occupant) {
		hasPlayer = true;
		this.occupant = occupant;
	}
//Return player object to whoever currently occupies the country
	public Players getOccupant() {
		return occupant;
	}
//Set number of armies currently in this country
	public void setNumberOfArmies(int numberOfArmies) {
		armies = numberOfArmies;
	}
//When a player gains armies in a country
	public void incArmies(int numberOfArmies) {
		armies = armies + numberOfArmies;
		System.out.println(occupant.getName() + " has added " + numberOfArmies + " armies to " + countryName + ".");
	}
//When a player loses armies in a country
	public void decArmies(int numberOfArmies) {
		armies = armies - numberOfArmies;
		System.out.println(occupant.getName() + " has lost " + numberOfArmies + " armies in " + countryName + ".");
	}
//Number of armies in the country
	public int getArmies() {
		return armies;
	}
// list of adjacent countries
	public ArrayList<Country> getAdjacent(){
		return adjacent;
	}
	public boolean hasPlayer() {
		return hasPlayer;
	}
}
	
