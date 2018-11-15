import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TestPlayers {

  @Test
  public void initPlayerGainAmries(){
		Players player = new Players("Georgy", 1000);
		player.gainArmies(10);
    player.displayInfo();
    player.getNumOfArmies();
    player.getName();
	}

  @Test
  public void initPlayerLoseAmries(){
		Players player = new Players("Georgy", 200);
		player.loseArmies(10);
    player.displayInfo();
    player.getNumOfArmies();
    player.getName();
	}

  @Test
  public void nameWithSymbol(){
		Players player = new Players("Georgy-=-=12", 200);
		player.loseArmies(10);
    player.displayInfo();
    player.getNumOfArmies();
    player.getName();
	}

  @Test
  public void negativeArmies(){
		Players player = new Players("Georgy-=-=12", -1);
		player.loseArmies(10);
    player.displayInfo();
    player.getNumOfArmies();
    player.getName();
	}

  @Test
  public void subtractArmies(){
		Players player = new Players("Georgy-=-=12", -1);
		player.loseArmies(-110);
    player.displayInfo();
    player.getNumOfArmies();
    player.getName();
	}

}
