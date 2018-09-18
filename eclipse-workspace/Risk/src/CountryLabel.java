import java.util.Observer;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JLabel;

public class CountryLabel extends JLabel implements Observer {
	private int armies;
	private String countryName;
	private String display;
	private ArrayList<String> stringList;
	private RiskModel model;
	private Country country;
	
	public CountryLabel(RiskModel model, Country country) {
		super();
		this.model = model;
		this.country = country;
		countryName = country.getName();
		setText(countryName);
	}
	public void update(Observable obs, Object obj) {
		display = (String) obj;
		if(display.equals("countryA")) {
			if(country.getOccupant() == null) {
				setText(countryName);
			}
			else {
				setText(countryName + ": " + country.getArmies() + ", " + country.getOccupant().getName());
			}
		}
	}
}
