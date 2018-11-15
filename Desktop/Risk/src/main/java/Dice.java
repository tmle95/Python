import java.util.Random;

public class Dice {

  Random random = new Random();

  Dice() {

  }

  public int rollDice(int nNumDice) {
    int totalRoll = 0;

    for(int d = 0; d < nNumDice; d++) {
      totalRoll += random.nextInt(6) + 1;
    }
    return totalRoll;
  }
  
  public int roll() {
	  int roll = (int)(Math.random() * 6) +1;
	  return roll;
  }



}
