import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.lang.StringBuilder;
import java.util.Arrays;
import java.util.Collections;

/*import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;*/

import twitter4j.TwitterException;

public class Main {

  public static void main(String[] args) throws TwitterException, IOException {
	  
	  /*ApiContextInitializer.init();
      TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
      TelegramBot telegramBot = new TelegramBot();
      try {
          telegramBotsApi.registerBot(telegramBot);
      } catch (TelegramApiException e) {
          e.printStackTrace();
      }
      telegramBot.sendMessage("Hi, let' start the game");
      */
      
	  String countryFile = "Countries.txt";
	  String continentFile = "Continents.txt";
	  String borderingCountryFile  = "BorderingCountries.txt";
	  String fileLine;
	  String fileInput;
	  BufferedReader reader;
	  StringBuilder stringBuilder;
	  String[] Countries;
	  String[] Continents;
	  String[] borderingCountries;
	  RiskBoard Board;
	  boolean createdBoard;

    PostTwitter postTwitter = new PostTwitter();


	  createdBoard = false;
	  Board = new RiskBoard();

	  try{
  		reader = new BufferedReader(new FileReader(countryFile));
  		stringBuilder = new StringBuilder();
  		while((fileLine = reader.readLine()) != null) {
  			stringBuilder.append(fileLine);
		   }
  		fileInput = stringBuilder.toString();
  		Countries = fileInput.split("\t");
  		System.out.println(Arrays.toString(Countries) + "\n");

  		reader = new BufferedReader(new FileReader(borderingCountryFile));
  		stringBuilder = new StringBuilder();
  		while((fileLine = reader.readLine()) != null){
  			stringBuilder.append(fileLine);
  		}
  		fileInput = stringBuilder.toString();
  		borderingCountries = fileInput.split("\t");
  		System.out.println(Arrays.toString(borderingCountries));

  		reader = new BufferedReader(new FileReader(continentFile));
  		stringBuilder = new StringBuilder();
  		while((fileLine = reader.readLine()) != null) {
  			stringBuilder.append(fileLine);
  		}
  		fileInput = stringBuilder.toString();
  		Continents = fileInput.split("\t");
  		System.out.println(Arrays.toString(Continents) + "\n");

		  createdBoard = Board.SetBoard(Countries, Continents, borderingCountries);
		}catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(IOException e) {
			System.out.println(e.getMessage());
	}

    String sColorOption[] = {"Red", "Green", "Blue", "Purple", "Orange", "Yellow", "Teal"};
    String sWinner[] = {"0", "0", "0", "0", "0", "0"};
    int nNumPlayers = 0;
    int nColor = 0;
    int nArmies = 0;

    String sUserInput;
    int nUserInput;

    Players[] players;
    Dice dice = new Dice();

    boolean bGameRunning = true;
    Scanner sc;

    Deck deck = new Deck();
    deck.shuffle();
    if(deck.shuffle()){
      System.out.println("The deck has been shuffled.");
    }

    System.out.println("=============================================================");
    System.out.println("============ Welcome to Risk Board Game !!! =================");
    System.out.println("=============================================================");

    // Keep running until user pick a valid number of players
    while(bGameRunning) {
      if(nNumPlayers >= 2 && nNumPlayers <= 6) {
        bGameRunning = false;
      } else {
        System.out.println("Please select how many players (2-6) are playing...");

        sc = new Scanner(System.in);
        nNumPlayers = sc.nextInt();
      }
    }

    System.out.println("=============================================================");
    System.out.println("============== You have select " + nNumPlayers  + " Players. ===================");
    System.out.println("=============================================================");

    players = new Players[nNumPlayers];

    // Give each player the amount of infantry
    if(nNumPlayers == 2)  // Not sure what to do for 2 players
      nArmies = 1;
    if(nNumPlayers == 3)
      nArmies = 35;
    if(nNumPlayers == 4)
      nArmies = 30;
    if(nNumPlayers == 5)
      nArmies = 25;
    if(nNumPlayers == 6)
      nArmies = 20;



    for(int p = 0; p < nNumPlayers; p++) {
      String sName = "Player " + (p+1);

      System.out.println("Please name "+ sName + "...");
      sc = new Scanner(System.in);
      sName = sc.nextLine();

      bGameRunning = true;
      /* PLAYER DOES NOT NEED COLOR
      do{
        if(nColor >= 1 && nColor <= sColorOption.length && sColorOption[nColor-1] != null) {
          bGameRunning = false;
        } else {
          System.out.println("Please select the color for player " + sName + "...");
          for(int c = 0; c < sColorOption.length; c++) {
            if(sColorOption[c] != null)
              System.out.println((c+1) + ". " + sColorOption[c]);
          }
          sc = new Scanner(System.in);
          nColor = sc.nextInt();
        }
      }while(bGameRunning);
      */

      players[p] = new Players(sName, nArmies);
      //sColorOption[nColor-1] = null;

      System.out.println();
      System.out.println("=============================================================\n\n");
    }

    for(int p = 0; p < nNumPlayers; p++) {
      System.out.println("=============================================================");
      System.out.println("Player " + (p+1) + " is name: " + players[p].getName());
      // System.out.println("Player " + (p+1) + " is color: " + players[p].getColor());
    }
    // Let the player imbrace with their name
    System.out.println("Press Enter key to continue...");
    try
    {
        System.in.read();
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }
    System.out.println("=============================================================\n\n");

    // Setup number 2 - whoever rolls the highest number gets to choose any territory
    System.out.println("===================== Whoever rolls the dice gets to pick first territory ========================================\n");
    int nPlayerTurn = 0;
    bGameRunning = true;
    while(bGameRunning == true) {
      int nWinner = 0;

      for(int p = 0; p < nNumPlayers; p++) {
        if(sWinner[p] != null) {
          System.out.println("=============================================================");
          players[p].setDice(dice.rollDice(1));
          System.out.println("Player " + (p+1) + " roll a: " + players[p].getDice());
          System.out.println("=============================================================");

          if(players[nPlayerTurn].getDice() < players[p].getDice()) {
            sWinner[nPlayerTurn] = null;
            nPlayerTurn = p;
          }else if(players[nPlayerTurn].getDice() > players[p].getDice()) {
            sWinner[p] = null;
          }

        }
      }

      // Check if there is a winner
      for(int c = 0; c < nNumPlayers; c++) {
        if(sWinner[c] != null)
            nWinner++;
      }

      // This will check if ONE player has the highest number
      if(nWinner == 1) {
        System.out.println("\n=============================================================");
        System.out.println("Player " + (nPlayerTurn+1) + " gets to pick territory first... ");
        System.out.println("=============================================================");
        bGameRunning = false;
      } else {
        System.out.println("\n\n There are 2 or more players that have the same number. Must roll again.");
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {
          System.out.println(e.getMessage());
        }
      }
    }

    // Set up - Claim territories
    int nCountriesClaim = Board.returnCountries().size();
    boolean bValidName = false;
    while(nCountriesClaim >= 40) {
      System.out.println("Player " + (nPlayerTurn+1) + ": " + players[nPlayerTurn].getName() + " which country would you like to claim?");
      System.out.println("1. List all the countries...");

      bValidName = false;
      sc = new Scanner(System.in);
      sUserInput = sc.nextLine();

      if(sUserInput.equals("1"))
      {
        for(int i = 0; i < Board.returnVacancy().size(); i++) {
            System.out.println(Board.returnVacancy().get(i).getName());
        }
      }
      else
      {

        // Check if the country has been taken
        for(int i = 0; i < Board.returnVacancy().size(); i++)
        {
          if(sUserInput.equals(Board.returnVacancy().get(i).getName()))
            bValidName = true; // Country has not been claim

        }
        // If user has input a valid country and it is not occupy by any players. Then let the player take control of that country
        if(bValidName)
        {
          Board.setPlayer(sUserInput, players[nPlayerTurn]);
          players[nPlayerTurn].gainCountry(Board.returnNameOfCountry(sUserInput));
          Board.setNumOfArmies(sUserInput, 1); // Add 1 troops to country
          players[nPlayerTurn].loseArmies(1);

          // Player take turn
          // *** USE THIS TO ROTATE PLAYER TURNS ***
          if(nPlayerTurn < nNumPlayers-1)
            nPlayerTurn++;
          else
            nPlayerTurn = 0;
        }
        else
        {
          // Player has input something that is incorrect. Don't exit the game, just continue looping...
          System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSomething went wrong...");
          System.out.println("1. Country has been claim already...");
          System.out.println("2. Incorrect input (Press 1 to list country and type the exact way...)\n");
        }
      }
      nCountriesClaim = Board.returnVacancy().size(); // Check if all the countries are claimed
    }

    // Set remaining troops that players have
    bGameRunning = true;
    while(bGameRunning)
    {
      // All user have place their troops
      bGameRunning = false;
      for(int p = 0; p < nNumPlayers; p++) {
        if(players[p].getNumOfArmies() > 0) {
          bGameRunning = true;
        }
      }

      if(players[nPlayerTurn].getNumOfArmies() > 0)
      {
        bValidName = false;
        System.out.println("Player " + (nPlayerTurn+1) + ": " + players[nPlayerTurn].getName() + ", which country would you like to add troop to?");
        sc = new Scanner(System.in);
        sUserInput = sc.nextLine();

        // Check if the country belongs to that player
        for(int i = 0; i < players[nPlayerTurn].countriesPlayerHas().size(); i++)
        {
          if(sUserInput.equals(players[nPlayerTurn].countriesPlayerHas().get(i).getName()))
            bValidName = true;
        }

        if(bValidName)
        {
          players[nPlayerTurn].loseArmies(0); // Display how many troop this player has left
          System.out.println("How many troops would you like to add to " + sUserInput + "?");
          sc = new Scanner(System.in);
          nUserInput = sc.nextInt();

          if(nUserInput <= players[nPlayerTurn].getNumOfArmies() && nUserInput >= 0)
          {
            // Add troop to country
            for(int i = 0; i < players[nPlayerTurn].countriesPlayerHas().size(); i++)
            {
              if(sUserInput.equals(players[nPlayerTurn].countriesPlayerHas().get(i).getName()))
              {
                players[nPlayerTurn].countriesPlayerHas().get(i).incArmies(nUserInput);
                players[nPlayerTurn].loseArmies(nUserInput);
              }
            }

            // Player take turn
            if(nPlayerTurn < nNumPlayers-1)
              nPlayerTurn++;
            else
              nPlayerTurn = 0;
          }
        }
        else
        {
          System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSomething went wrong...");
          System.out.println("1. Country does NOT belong to this player...");
          System.out.println("2. Incorrect input (Press 1 to list country your country and type the exact way...)\n");
        }
      }
      else
      {
        // Player take turn
        if(nPlayerTurn < nNumPlayers-1)
          nPlayerTurn++;
        else
          nPlayerTurn = 0;
      }
    }



    // Game Starts
    boolean bPlayerTurn = false;
    bGameRunning = true;
    int nTurn = 0;
    int nNumberOfTurns = 0;
    while(bGameRunning) {
      bPlayerTurn = true;
      // Check if any player has conquers all the country
      
      // Twitter Message
      nNumberOfTurns++;
      try{
        postTwitter.TweetOnTwitter(postMessage(1, nNumberOfTurns, players, nNumPlayers, postTwitter));
      }catch (TwitterException e) {
        System.out.println("Did not post to twitter.");
      }

      switch(nPlayerTurn) {
        case 0:
        	
          while(bPlayerTurn)
          {
        	purchaseCredits(players[0]);
        	purchaseCards(players[0], Board);
        	purchaseUndo(players[0]);
        	transferCredits(players[0], Board, nNumPlayers, players);
        	attackTerritory(players[0], Board, dice, players);
            System.out.println(players[nPlayerTurn].getName() + ", what would you like to do?");
            System.out.println("Press \'1\' to fortify");
            System.out.println("Press \'-1\' to end turn");
            System.out.println("What would you like to do?");
            sc = new Scanner(System.in);
            sUserInput = sc.nextLine();
            // Getting and placing new armies.
            // Attacking.
            //attackTerritory(players[nPlayerTurn], Board, dice);
            // Fortifying your position.
            if(sUserInput.equals("1"))
              fortifyArmy(players[nPlayerTurn], Board);
            if(sUserInput.equals("-1"))
              bPlayerTurn = false;
          }
   
        break;

        case 1:
        while(bPlayerTurn)
        {
          System.out.println(players[nPlayerTurn].getName() + ", what would you like to do?");
          System.out.println("Press \'1\' to fortify");
          System.out.println("Press \'-1\' to end turn");
          System.out.println("What would you like to do?");
          sc = new Scanner(System.in);
          sUserInput = sc.nextLine();
          // Getting and placing new armies.
          // Attacking.
          //attackTerritory(players[nPlayerTurn], Board, dice);
          // Fortifying your position.
          if(sUserInput.equals("1"))
            fortifyArmy(players[nPlayerTurn], Board);
          
          if(sUserInput.equals("-1"))
            bPlayerTurn = false;
        }
        //purchaseCredits(players[1]);
        //purchaseCards(players[1], Board);
        //transferCredits(players[1], Board, nNumPlayers, players);
        //attackTerritory(players[1], Board, dice, players);
        break;

        case 2:
        while(bPlayerTurn)
        {
          System.out.println(players[nPlayerTurn].getName() + ", what would you like to do?");
          System.out.println("Press \'1\' to fortify");
          System.out.println("Press \'-1\' to end turn");
          System.out.println("What would you like to do?");
          sc = new Scanner(System.in);
          sUserInput = sc.nextLine();
          // Getting and placing new armies.
          // Attacking.
          //attackTerritory(players[nPlayerTurn], Board, dice);
          // Fortifying your position.
          if(sUserInput.equals("1"))
            fortifyArmy(players[nPlayerTurn], Board);
          if(sUserInput.equals("-1"))
            bPlayerTurn = false;
        }
        attackTerritory(players[2], Board, dice, players);
        break;

        case 3:
        while(bPlayerTurn)
        {
          System.out.println(players[nPlayerTurn].getName() + ", what would you like to do?");
          System.out.println("Press \'1\' to fortify");
          System.out.println("Press \'-1\' to end turn");
          System.out.println("What would you like to do?");
          sc = new Scanner(System.in);
          sUserInput = sc.nextLine();
          // Getting and placing new armies.
          // Attacking.
          //attackTerritory(players[nPlayerTurn], Board, dice);
          // Fortifying your position.
          if(sUserInput.equals("1"))
            fortifyArmy(players[nPlayerTurn], Board);
          if(sUserInput.equals("-1"))
            bPlayerTurn = false;
        }
        attackTerritory(players[3], Board, dice, players);
        break;

        case 4:
        while(bPlayerTurn)
        {
          System.out.println(players[nPlayerTurn].getName() + ", what would you like to do?");
          System.out.println("Press \'1\' to fortify");
          System.out.println("Press \'-1\' to end turn");
          System.out.println("What would you like to do?");
          sc = new Scanner(System.in);
          sUserInput = sc.nextLine();
          // Getting and placing new armies.
          // Attacking.
          //attackTerritory(players[nPlayerTurn], Board, dice);
          // Fortifying your position.
          if(sUserInput.equals("1"))
            fortifyArmy(players[nPlayerTurn], Board);
          if(sUserInput.equals("-1"))
            bPlayerTurn = false;
        }
        attackTerritory(players[4], Board, dice, players);
        break;

        case 5:
        while(bPlayerTurn)
        {
          System.out.println(players[nPlayerTurn].getName() + ", what would you like to do?");
          System.out.println("Press \'1\' to fortify");
          System.out.println("Press \'-1\' to end turn");
          System.out.println("What would you like to do?");
          sc = new Scanner(System.in);
          sUserInput = sc.nextLine();
          // Getting and placing new armies.
          // Attacking.
          //attackTerritory(players[nPlayerTurn], Board, dice);
          // Fortifying your position.
          if(sUserInput.equals("1"))
            fortifyArmy(players[nPlayerTurn], Board);
          if(sUserInput.equals("-1"))
            bPlayerTurn = false;
        }

      attackTerritory(players[5], Board, dice, players);
      break;

      default:
      // Getting and placing new armies.
      // Attacking.
      attackTerritory(players[5], Board, dice, players);
      // Fortifying your position.
      break;
    }
      
    // Twitter Message
      try{
        postTwitter.TweetOnTwitter(postMessage(2, nNumberOfTurns, players, nNumPlayers, postTwitter));
      }catch (TwitterException e) {
        System.out.println("Did not post to twitter.");
      }
    
    
      // Player take turn
      if(nPlayerTurn < nNumPlayers-1)
        nPlayerTurn++;
      else
        nPlayerTurn = 0;
   
  }
}

public static void purchaseCredits(Players player) {
	boolean purchasing = true;
	Scanner sc;
	String sUserInput;
	int nUserInput;
	System.out.println("\nWelcome to CTC Credits Store!");
	while(purchasing) {
		System.out.println("\nPlayer : " + player.getName() + " would you like to purchase credits? (y/n)");
		sc = new Scanner(System.in);
		sUserInput = sc.nextLine();
		if(sUserInput.equals("y")) {
			System.out.println("How many would you like to buy? Price rate: $1 = 1 credit");
			sc = new Scanner(System.in);
			//checks to make sure input is an integer
			do {
				while(!sc.hasNextInt()) {
					System.out.println("Transaction error: That is not a valid number");
					System.out.println("Please enter the number of credits you would like to buy");
					sc.next();
				}
				nUserInput = sc.nextInt();
			}while(nUserInput <= 0);
				System.out.println("\nPurchase successful!" + "\n");
				player.gainCredits(nUserInput);
		}
		if(sUserInput.equals("n")) {
			System.out.println("\nExiting CTC Credits Store...");
			return;
		}
	}
}
public static void purchaseCards(Players player, RiskBoard Board) {
	boolean purchasing = true;
	Scanner sc;
	String countryInput;
	String armyInput;
	Card pCard;
	boolean validName = false;
	boolean purchased = false;
	String sUserInput;
	int infantryCost = 1;
	int cavalryCost = 5;
	int artilleryCost = 10;
	System.out.println("\nWelcome to CTC Cards Store!");
	while(purchasing) {
		System.out.println("\nPlayer : " + player.getName() + " would you like to purchase a territory card? (y/n) ");
		System.out.println("1. List available credits ");
		sc = new Scanner(System.in);
		sUserInput = sc.nextLine();
		if(sUserInput.equals("1")) {
			System.out.println("Total available credits: " + player.getNumOfCredits());
		}
		if(sUserInput.equals("y")) {
			//Do you have to own the country to purchase a card for?
			
			System.out.println("\nWhich country would you like this territory card for?");
			sc = new Scanner(System.in);
			countryInput = sc.nextLine();
			//Checks if input is a valid country in the board
	        for(int i = 0; i < Board.returnCountries().size(); i++) {
	            if(countryInput.equals(Board.returnCountries().get(i).getName())){
	            	validName = true;
	            }
	        }
			if(!validName) {
				System.out.println("\nNot a valid country");
			}
			while(validName) {
				System.out.println("\nWould you like Infantry($1), Cavalry($5), or Artillery($10) army type for this territory card?");
				sc = new Scanner(System.in);
				armyInput = sc.nextLine();
				//checks if player has enough credits to buy for each army type
				if(armyInput.equals("Infantry") || armyInput.equals("Cavalry") || armyInput.equals("Artillery")) {
					if(armyInput.equals("Infantry")) {
						if(player.getNumOfCredits() < infantryCost) {
							System.out.println("\nTransaction error: You do not have enough credits to purchase this");
							break;
						}
					}
					if(armyInput.equals("Cavalry")) {
						if(player.getNumOfCredits() < cavalryCost) {
							System.out.println("\nTransaction error: You do not have enough credits to purchase this");
							break;
						}
					}
					if(armyInput.equals("Artillery")) {
						if(player.getNumOfCredits() < artilleryCost) {
							System.out.println("\nTransaction error: You do not have enough credits to purchase this");
							break;
						}
					}
					//Creates a new card if player has enough credits
					pCard = new Card(armyInput, countryInput);
					player.getPlayersHand().add(pCard);
					purchased = true;
				}
				else {
					System.out.println("\nNot a valid army type");
				}
				if(purchased) {
					validName = false;
					System.out.println("\nPurchase successful!");
					for(int i = 0 ;i < player.getPlayersHand().size(); i ++) {
						System.out.println("\nPlayer: " + player.getName() + " has purchased a territory card for " + player.getPlayersHand().get(i).getCountry() + ", type: " + player.getPlayersHand().get(i).getType());
					}
					if(armyInput.equals("Infantry")){
							player.loseCredits(1);
					}
					else if(armyInput.equals("Cavalry")) {
						player.loseCredits(5);
					}
					else if(armyInput.equals("Artillery")) {
						player.loseCredits(10);
					}
					else
						player.loseCredits(0);
				}
			}
		}
		if(sUserInput.equals("n")) {
			System.out.println("\nExiting CTC Cards Store........");
			return;
		}
	}
}

public static void purchaseUndo(Players player) {
	boolean purchasing = true;
	Scanner sc;
	int nUserInput;
	String sUserInput;
	while(purchasing) {
		System.out.println("\nPlayer : " + player.getName() + " would you like to purchase any Undos? (y/n)");
		sc = new Scanner(System.in);
		sUserInput = sc.nextLine();
		
		if(sUserInput.equals("y")) {
			System.out.println("How many would you like to buy? Price rate: 1 Undo = 20 credit");
			sc = new Scanner(System.in);
			do {
				while(!sc.hasNextInt()) {
					System.out.println("Transaction error: That is not a valid number");
					System.out.println("Please enter the number of Undos you would like to buy");
					sc.next();
				}
				nUserInput = sc.nextInt();
			}while(nUserInput <= 0);
				System.out.println("\nPurchasing Undo(s).........");
				//Checks if player has enough credits 
				if(player.getNumOfCredits() < (nUserInput*20)) {
					System.out.println("\nTransaction error: You do not have enough credits to buy " + nUserInput + " Undo(s)");
					continue;
				}
				System.out.println("Purchase successful!");
				player.gainUndo(nUserInput);				
		}
		
		if(sUserInput.equals("n")) {
			return;
		}
	}
}

public static void transferCredits(Players player, RiskBoard Board, int nNumPlayers, Players[] players) {
	boolean transfer = true;
	boolean canTransfer = false;
	Scanner sc;
	String sUserInput;
	String nameInput;
	int nCreditsInput;
	while(transfer) {
		System.out.println("\nPlayer: " + player.getName() + " would you like to transfer any of your credits to another player? (y/n)");
		sc = new Scanner(System.in);
		sUserInput = sc.nextLine();
		if(sUserInput.equals("y")) {
			System.out.println("Who would you like to transfer credits to?");
			System.out.println("1. List of players");
			sc = new Scanner(System.in);
			nameInput = sc.nextLine();
			if(nameInput.equals("1")) {
				for(int i = 0; i < nNumPlayers ; i++) {
					System.out.println(players[i].getName());
				}
			}
			//Checks if the input was the same as the current player
			if(nameInput.equals(player.getName())) {
				System.out.println("\nWhy would you transfer to yourself......");
				continue;
			}
			for(int i = 0; i < nNumPlayers; i++) {
				if(nameInput.equals(players[i].getName())) {
					System.out.println("\nHow many credits would you like to transfer to " + players[i].getName());
					sc = new Scanner(System.in);
					do {
						while(!sc.hasNextInt()) {
							System.out.println("\nTransfer error: Not a valid number input");
							System.out.println("Please enter a valid number of credits you would like to transfer");
							sc.next();
						}
						nCreditsInput = sc.nextInt();
					}while(nCreditsInput <= 0);
					System.out.println("\nTransferring credits..........");
						//Checks if player has enough credits to transfer
						if(nCreditsInput > player.getNumOfCredits()) {
							System.out.println("\nYou do not have enough credits to transfer");
							continue;
						}
						System.out.println("\nPlayer: " + player.getName() + " has transferred " + nCreditsInput + " credits to Player: " + players[i].getName());
						player.loseCredits(nCreditsInput);
						players[i].gainCredits(nCreditsInput);
					}
				}
		}
		if(sUserInput.equals("n")) {
			return;
		}
	}
}
public static void attackTerritory(Players player, RiskBoard Board, Dice dice, Players[] players) {
	boolean attacking = true;
    boolean validBorderCountry = false;
    boolean isOpponent = false;
    boolean validAtkRoll = false;
    boolean validDefRoll = false;
	boolean bValidName = false;
    String atkCountryInput = "";
    String defCountryInput = "";
    Country atkCountry;
    Country defCountry;
    String a,b,c;
    int atkArmyLoss = 0;
    int defArmyLoss = 0;
    int lowDice = 0;
    int nAtkDice = 0;
    int nDefDice = 0;
    Integer [] nAtkRolls;
    Integer [] nDefRolls;
    Scanner sc;
    int nUserInput;
    Subject subject = new Subject();

    System.out.println("\nPlayer: " + player.getName() + " will now begin their Attack phase.");
    while(attacking) {
    	System.out.println("\nPlayer: " + player.getName() + ", From which of your countries would you like to attack from?");
    	System.out.println("1. list countries you currently occupy");
    	sc = new Scanner(System.in);
    	atkCountryInput = sc.nextLine();
    	if(atkCountryInput.equals("1")) {
    		//Displays list of countries that belongs to player
    		for(int i = 0;i < player.countriesPlayerHas().size();i++) {
    			System.out.println(player.countriesPlayerHas().get(i).getName());
    		}
    	}
    	
    	for(int i = 0;i < player.countriesPlayerHas().size();i++) {
    		//Checks if country belongs to player
    		if(atkCountryInput.equals(player.countriesPlayerHas().get(i).getName())) {
    				bValidName = true;
    		}
    	}

    	if(!bValidName) {
    		System.out.println("\nYou do not own this country, or press 1 to list your countries");
    	}

		while(bValidName) {
    		System.out.println("\nWhich nearby country would you like to attack from " + atkCountryInput);
    		System.out.println("1. List countries nearby: " + atkCountryInput);
    		sc = new Scanner(System.in);
			defCountryInput = sc.nextLine();
    		if(defCountryInput.equals("1")) {
    			//Displays list of countries adjacent to country player is attacking from
    			for(int j = 0 ; j < Board.returnBorders(atkCountryInput).size(); j++) {
    				System.out.println(Board.returnBorders(atkCountryInput).get(j).getName());
    			}
    		}
    		//If countries are adjacent to each other, returns true
    		validBorderCountry = Board.verifyBorder(atkCountryInput, defCountryInput);
    		if(validBorderCountry) {
    			 for(int i = 0; i < player.countriesPlayerHas().size();i++) {
    				 //checks whether the adjacent country also belongs to the player or not
 			    	if(defCountryInput.equals(player.countriesPlayerHas().get(i).getName()) || defCountryInput.equals(atkCountryInput)) {
 			    		System.out.println("\nYou can not attack your own country");
 			    		break;
 			    	}
 			    	else {
 			    		isOpponent = true;
 			    	}
    			 }
    		}

	    	if(!validBorderCountry) {
	    		System.out.println("\nThis country is not bordering " + atkCountryInput + " or check spelling of country");
	    	}
	    	
	    	while(isOpponent) {
          System.out.println("\nCommencing Attack!");
          subject.setState(1);
	    		System.out.println("Player: " + player.getName() + "'s armies from " + atkCountryInput + " will be attacking Player: " + Board.returnPlayer(defCountryInput).getName() + "'s armies in " + defCountryInput);
	    		System.out.println("\n" + player.getName() + ", How many dices would you like to roll to attack?");
    			sc = new Scanner(System.in);
    			do {
    				while(!sc.hasNextInt()) {
    					System.out.println("\nNot a valid number to roll");
    					System.out.println("Please enter a number of dices you want to roll...");
    					sc.next();
    				}
    				nUserInput = sc.nextInt();
    			}while(nUserInput <= 0);
    				System.out.println("\nPlayer: " + player.getName() + " will be rolling " + nUserInput + " dices");
    			//Checks if input is less than or equal to number of armies in country
    			if(nUserInput >= 1 && nUserInput <= Board.returnNumOfArmies(atkCountryInput)) {
    						nAtkDice = nUserInput;
    						validAtkRoll = true;
    			}
    			else {
    				System.out.println("\nYou do not have that many army in this country");
    			}
	    		while(validAtkRoll) {
	    			System.out.println("\n" + Board.returnPlayer(defCountryInput).getName() + " , how many dices would you like to roll to defend?");
	    			sc = new Scanner(System.in);
	    			do {
	    				while(!sc.hasNextInt()) {
	    					System.out.println("\nNot a valid number to roll");
	    					System.out.println("Please enter a number of dices you want to roll...");
	    					sc.next();
	    				}
	    				nUserInput = sc.nextInt();
	    			}while(nUserInput <= 0);
	    				System.out.println("\nPlayer: " + Board.returnPlayer(defCountryInput).getName() + " will be rolling " + nUserInput + " dices");
	    				if(nUserInput >= 1 && nUserInput <= Board.returnNumOfArmies(defCountryInput)) {
	    					nDefDice = nUserInput;
	    					validDefRoll = true;
	    				}
	    			else {
	    				System.out.println("\nYou do not have that many army in this Country");
	    			}
	    			if(validDefRoll) {
	    				//Sets number of dice duels to the number of rolls of the player with the least
	    				if(Integer.compare(nAtkDice, nDefDice) == 1) {
	    					lowDice = nDefDice;
	    				}
	    				else if(Integer.compare(nAtkDice, nDefDice) == -1) {
	    					lowDice = nAtkDice;
	    				}
	    				else if(Integer.compare(nAtkDice,  nDefDice) == 0) {
	    					lowDice = nAtkDice;
	    				}
	    				nAtkRolls = new Integer[nAtkDice];
	    				for(int i = 0; i < nAtkRolls.length; i++) {
	    					nAtkRolls[i] = dice.roll();
	    					//System.out.println(nAtkRolls[i]);
	    				}
	    				Arrays.sort(nAtkRolls, Collections.reverseOrder());
	    				nDefRolls = new Integer[nDefDice];
	    				for(int i = 0; i < nDefRolls.length; i++) {
	    					nDefRolls[i] = dice.roll();
	    					//System.out.println(nDefRolls[i]);
	    				}
	    				Arrays.sort(nDefRolls,Collections.reverseOrder());
	    				for(int i = 0; i < lowDice ; i++) {
	    					System.out.println("\nNext Highest Pair Roll: ");
		    				if(nAtkRolls[i] > nDefRolls[i]) {
		    					defArmyLoss++;
		    					System.out.println(player.getName() + " rolled " + nAtkRolls[i]);
		    					System.out.println(Board.returnPlayer(defCountryInput).getName() + " rolled " + nDefRolls[i]);
		    				}
		    				else if(nAtkRolls[i] < nDefRolls[i]) {
		    					atkArmyLoss++;
		    					System.out.println(player.getName() + " rolled " + nAtkRolls[i]);
		    					System.out.println(Board.returnPlayer(defCountryInput).getName() + " rolled " + nDefRolls[i]);
		    				}
		    				else if(nAtkRolls[i] == nDefRolls[i]) {
	    						System.out.println(player.getName() + " rolled " + nAtkRolls[i]);
		    					System.out.println(Board.returnPlayer(defCountryInput).getName() + " rolled " + nDefRolls[i]);
		    					System.out.println("Dice roll tie!");
		    					if(nAtkRolls[i+1] > nDefRolls[i+1]) {
			    					System.out.println("\nNext Highest Pair Roll: ");
		    						defArmyLoss++;
			    					System.out.println(player.getName() + " rolled " + nAtkRolls[i]);
			    					System.out.println(Board.returnPlayer(defCountryInput).getName() + " rolled " + nDefRolls[i]);    					
		    					}
		    					else if(nAtkRolls[i+1] < nDefRolls[i+1]) {
		    						System.out.println("\nNext Highest Pair Roll: ");
			    					atkArmyLoss++;
			    					System.out.println(player.getName() + " rolled " + nAtkRolls[i]);
			    					System.out.println(Board.returnPlayer(defCountryInput).getName() + " rolled " + nDefRolls[i]);
			    				}
		    				}
	    				}
	    				System.out.println("\nBattle Report: ");
	    				Board.returnNameOfCountry(atkCountryInput).decArmies(atkArmyLoss);
	    				Board.returnNameOfCountry(defCountryInput).decArmies(defArmyLoss);
	    				
	    				if(Board.returnNameOfCountry(defCountryInput).getArmies() < 1) {
	    					System.out.println("\n\n\n\n\n\n\n\n\nGame Announcement: Player: " + player.getName() + " has eliminated all of Player:" + Board.returnPlayer(defCountryInput).getName() + "'s armies in " + Board.returnNameOfCountry(defCountryInput).getName() + " and now has posession of the country!");
	    					Board.returnPlayer(defCountryInput).lostCountry(defCountryInput);
	    					player.gainCountry(Board.returnNameOfCountry(defCountryInput));
	    					
	    					if(Board.returnPlayer(defCountryInput).countriesPlayerHas().size() == 0) {
	    						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\nGame Announcement: Player: " + Board.returnPlayer(defCountryInput).getName() + " has lost all of their territories and will be removed from the game!");
	    					}
	    					
	    				}
	    				
	    				return;

	    			}
	    		}
	    	}	
		}
    }
}
		


  public static void fortifyArmy(Players player, RiskBoard Board) {
    boolean bFortify = true;
    String sUserInputCountry;
    String sUserInputAdjacent;
    Scanner sc;
    while(bFortify) {
      int nUserInput;
      boolean bValidName = false;
      System.out.println(player.getName() + ", which country would you like to fortify?");
      sc = new Scanner(System.in);
      sUserInputCountry = sc.nextLine();

      if(sUserInputCountry.equals("1"))
      {
        for(int i = 0; i < Board.returnVacancy().size(); i++) {
            System.out.println(Board.returnVacancy().get(i).getName());
        }
      }
      if(sUserInputCountry.equals("-1"))
      {
        bFortify = false;
      }

      // Check if the country belongs to that player
      for(int i = 0; i < player.countriesPlayerHas().size(); i++)
      {
        if(sUserInputCountry.equals(player.countriesPlayerHas().get(i).getName()))
          bValidName = true;
      }

      if(!bValidName)
      {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSomething went wrong...");
        System.out.println("1. Country does NOT belong to this player...");
        System.out.println("2. Incorrect input (Press 1 to list country your country and type the exact way...)\n");
        System.out.println("3. Type \'-1\' to exit...\n");
      }

      while(bValidName)
      {
        boolean bValidAdjacentName = false;
        bFortify = false;
        System.out.println("Which adjacent of " + sUserInputCountry + " do you want to fortify?");
        sc = new Scanner(System.in);
        sUserInputAdjacent = sc.nextLine();

        if(sUserInputAdjacent.equals("1"))
        {
          for(int i = 0; i < Board.returnBorders(sUserInputCountry).size(); i++) {
              System.out.println(Board.returnBorders(sUserInputCountry).get(i).getName());
          }
        }
        if(sUserInputCountry.equals("-1"))
        {
          bValidName = false;
        }

        // Check if the country is adjacent to each other
        bValidAdjacentName = Board.verifyBorder(sUserInputCountry, sUserInputAdjacent);

        if(!bValidAdjacentName)
        {
          System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSomething went wrong...");
          System.out.println("1. Country does NOT belong to this player...");
          System.out.println("2. Incorrect input (Press 1 to list country that is adjacent to that country...)\n");
          System.out.println("3. Type \'-1\' to exit...\n");
        }

        while(bValidAdjacentName) {
          bValidName = false;

          System.out.println("How many troops would you like to add " + sUserInputCountry + " to " + sUserInputAdjacent + "?");
          sc = new Scanner(System.in);
          nUserInput = sc.nextInt();

          // Display how many armies does the country have
          if(sUserInputCountry.equals("-1"))
          {
            for(int i = 0; i < player.countriesPlayerHas().size(); i++)
            {
              if(sUserInputCountry.equals(player.countriesPlayerHas().get(i).getName()))
              {
                player.countriesPlayerHas().get(i).decArmies(0);
              }
              if(sUserInputAdjacent.equals(player.countriesPlayerHas().get(i).getName()))
              {
                player.countriesPlayerHas().get(i).decArmies(0);
              }
            }
          }
          if(sUserInputCountry.equals("-1"))
          {
            bValidAdjacentName = false;
          }

          if(nUserInput <= (Board.returnNumOfArmies(sUserInputCountry)-1) && nUserInput >= 0)
          {
            bValidAdjacentName = false;
            // Add troop to country
            for(int i = 0; i < player.countriesPlayerHas().size(); i++)
            {
              if(sUserInputCountry.equals(player.countriesPlayerHas().get(i).getName()))
              {
                player.countriesPlayerHas().get(i).decArmies(nUserInput);
              }
              if(sUserInputAdjacent.equals(player.countriesPlayerHas().get(i).getName()))
              {
                player.countriesPlayerHas().get(i).incArmies(nUserInput);
              }
            }
          }
          else
          {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nSomething went wrong...");
            System.out.println("1. You have entered more than the country army limit...");
            System.out.println("2. Incorrect input (Press -1 to list country army number...)\n");
            System.out.println("3. Type \'-1\' to exit...\n");
          }
        }
      }
    }
  }

  public static String postMessage(int beginTurn, int nNumberOfTurns, Players[] player, int nNumPlayers, PostTwitter postTwitter) {
    int territories = 0;
    String message = "";
    if(beginTurn == 1) {
      message += ("Beginning of turn " + nNumberOfTurns + "\n");
    } else {
      message += ("End of turn " + nNumberOfTurns + "\n");
    }

    for(int i = 0; i < nNumPlayers; i++) {
      territories = player[i].countriesPlayerHas().size();
      message += (player[i].getName() + " has " + territories + " countries.\n");
    }

    return message;
  }
}
