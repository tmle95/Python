import java.util.ArrayList;

public class Continent {
	private String continentName;
	private int extraArmies;
	private ArrayList<Country> countries;
	public Continent(String continentName, int extraArmies, ArrayList<Country> localCountries) {
		this.continentName = continentName;
		this.extraArmies = extraArmies;
		countries = localCountries;
		System.out.println("Continent created: " + continentName + "\n" + "Extra armies included: " + extraArmies);
	}
	public String getName() {
		return continentName;
	}
//Return number of extra armies to player controlling this continent
	public int getExtraArmies() {
		return extraArmies;
	}
// list of countries on this continent
	public ArrayList<Country> getLocalCountries(){
		return countries;
	}
}
