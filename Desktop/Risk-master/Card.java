public class Card {

    private String armyType;
    private Country country;

    public Card( String type, Country country ) {
		this.armyType = type;
		this.country = country;
    }

	public String getName() {
		return country.getName() + ", " + armyType;
	}

    public String getType() {
		return armyType;
    }

    public Country getCountry() {
		return country;
    }
}
