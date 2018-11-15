import java.util.ArrayList;

public class Country {
	private int armies;
	private boolean occupied;
	private String countryName;
	private Players player;
	private ArrayList<Country> borderingCountry;

	public Country(String countryName) {
		this.countryName = countryName;
		armies = 0;
		occupied = false;
		System.out.println("Creating country: " + countryName);
	}
	public String getName() {
		return countryName;
	}
//Used to construct country object
	public void addBorders(ArrayList<Country> borderingCountry) {
		this.borderingCountry = borderingCountry;
	}
//Set occupant of country when a player conquers it
	public void setPlayer(Players player) {
		occupied = true;
		this.player = player;
	}
//Return player object to whoever currently occupies the country
	public Players getPlayer() {
		return player;
	}
//Number of armies in the country
	public int getArmies() {
		return armies;
	}
//Set number of armies currently in this country
	public void setNumberOfArmies(int numberOfArmies) {
		armies = numberOfArmies;
	}
//When a player gains armies in a country
	public void incArmies(int numberOfArmies) {
		armies = armies + numberOfArmies;
		System.out.println(player.getName() + " has added " + numberOfArmies + " armies to " + countryName + ".");
	}
//When a player loses armies in a country
	public void decArmies(int numberOfArmies) {
		armies = armies - numberOfArmies;
		System.out.println(player.getName() + " has lost " + numberOfArmies + " army(s) in " + countryName + ".");
	}
// list of bordering countries
	public ArrayList<Country> getBorders(){
		return borderingCountry;
	}
	public boolean occupied() {
		return occupied;
	}
}
