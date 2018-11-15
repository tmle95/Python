import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;

public class RiskBoard {
	public RiskBoard() {}
	private String[] Continents;
	private String[] borderingCountries;
	private boolean generated;
	private boolean Bordering;
	private HashMap<String, Country> mapOfCountries;
	private HashMap<String, Continent> mapOfContinents;
	private ArrayList<Country> listOfCountries;
	private ArrayList<Country> listOfBorderingCountries;
	private ArrayList<Country> listOfLocalCountries;
	private ArrayList<Country> vacancy;

	public boolean SetBoard(String[] countryInputs, String[] continentInputs, String[] borderingCountriesInputs ) {
		generated = false;

		mapOfCountries = new HashMap<String, Country>();
		for(int i = 0; i < countryInputs.length; i++) {
			mapOfCountries.put(countryInputs[i], new Country(countryInputs[i]));
		}

		listOfCountries = new ArrayList<Country>(mapOfCountries.values());

		for(int i = 0; i < borderingCountriesInputs.length; i++) {
			borderingCountries = borderingCountriesInputs[i].split(",");
			listOfBorderingCountries = new ArrayList<Country>();
			for(int j = 1; j < borderingCountries.length; j++) {
				System.out.println(borderingCountries[0] + "'s list of borders has added: " + borderingCountries[j]);
				listOfBorderingCountries.add(mapOfCountries.get(borderingCountries[j]));
			}
			mapOfCountries.get(borderingCountries[0]).addBorders(listOfBorderingCountries);
		}

		mapOfContinents = new HashMap<String, Continent>();
		for(int i = 0; i < continentInputs.length; i++) {
			Continents = continentInputs[i].split(",");
			listOfLocalCountries = new ArrayList<Country>();
			for(int j = 2; j < Continents.length; j++) {
				System.out.println(Continents[0] + "'s list of Countries has added: " + Continents[j]);
				listOfLocalCountries.add(mapOfCountries.get(Continents[j]));
			}
			mapOfContinents.put(Continents[0], new Continent(Continents[0], Integer.parseInt(Continents[1]), listOfLocalCountries));
		}


		generated = true;
		return generated;
	}
	public ArrayList<Country> returnCountries(){
		return listOfCountries;
	}
	public Country returnNameOfCountry(String nameOfCountry) {
		return mapOfCountries.get(nameOfCountry);
	}
	public ArrayList<Country> returnBorders(String nameOfCountry){
		return mapOfCountries.get(nameOfCountry).getBorders();
	}
	public boolean verifyBorder(String firstCountry, String secondCountry) {
		if(mapOfCountries.get(firstCountry).getBorders().contains(mapOfCountries.get(secondCountry))) {
			Bordering = true;
		}
		else {
			Bordering = false;
		}
		return Bordering;
	}
	public ArrayList<Continent> returnContinents(){
		return new ArrayList<Continent>(mapOfContinents.values());
	}
	public Continent returnNameOfContinent(String nameOfContinent) {
		return mapOfContinents.get(nameOfContinent);
	}
	public ArrayList<Country> returnLocalCountries(String nameOfContinent){
		return mapOfContinents.get(nameOfContinent).getLocalCountries();
	}
	public int returnExtraArmies(String nameOfContinent) {
		return mapOfContinents.get(nameOfContinent).getExtraArmies();
	}
	public void setPlayer(String nameOfCountry, Players player) {
		mapOfCountries.get(nameOfCountry).setPlayer(player);
	}
	public Players returnPlayer(String nameOfCountry) {
		return mapOfCountries.get(nameOfCountry).getPlayer();
	}
	public void setNumOfArmies(String nameOfCountry, int numOfArmies) {
		mapOfCountries.get(nameOfCountry).setNumberOfArmies(numOfArmies);
	}
	public int returnNumOfArmies(String nameOfCountry) {
		return mapOfCountries.get(nameOfCountry).getArmies();
	}

	public ArrayList<Country> returnVacancy(){

		vacancy = new ArrayList<Country>();
		for(int i = 0; i < listOfCountries.size(); i++) {
			if(listOfCountries.get(i).occupied() == false) {
				vacancy.add(listOfCountries.get(i));
			}
		}
		return vacancy;

	}
}
