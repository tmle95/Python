import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestCard {

  @Test
  public void unValidCountry() {
    Card card = new Card("Infantry", "YESJapan");
    card.getType();
    card.getCountry();
  }

  @Test
  public void unValidArmyCountry() {
    Card card = new Card("NONONOInfantry", "Japan");
    card.getType();
    card.getCountry();
  }

  @Test
  public void symbolCountry() {
    Card card = new Card("Infantry", "-=-=-=-Japan");
    card.getType();
    card.getCountry();
  }

  @Test
  public void symbolArmy() {
    Card card = new Card("Infantry -=@#5", "Japan");
    card.getType();
    card.getCountry();
  }
}
