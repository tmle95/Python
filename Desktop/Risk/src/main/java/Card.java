public class Card {

    private String armyType;
    private String country;
    
    public Card( String type, String country ) {
		this.armyType = type;
		this.country = country;
    }
    
    public String getType() {
		return armyType;
    }

    public String getCountry() {
		return country;
    }
}
