package domain.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import domain.board.Board;
import domain.bot.Bot;
import domain.cards.Card;
import domain.cards.CardFactory;
import domain.squares.Square;

/**
 * This class contains methods to be used in our game such as building, paying
 * moving,rolling dice, updating some domain objects and values of the players
 * and squares
 * 
 * @author AngryNerds
 */
public class MonopolyTheGame {

	static MonopolyTheGame gameInstance;
	private static Board gameBoard;
	// private ArrayList<Observer> observers = new ArrayList<Observer>();
	private Player players[] = new Player[4];
	private int activePlayerIndex = 0;
	private Dice dice = Dice.getInstance();
	private static Pool pool = Pool.getInstance();
	public int doubleHistory = 0;
	private Random rand = new Random();
	private boolean secondTime = true;

	// we store the number of deeds for each color here so that we can determine
	// rent comparing them with player values

	/**
	 * Singleton pattern
	 * 
	 * @effects the game instance or creates it does not exist
	 */
	public static MonopolyTheGame getInstance() {
		if (gameInstance == null) {
			gameInstance = new MonopolyTheGame();
			gameBoard = Board.getInstance();
		}
		return gameInstance;
	}

	// private constructor
	private MonopolyTheGame() {
		Bot b = Bot.getInstance();
		b.start();
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		System.out.println(threadSet);
		// registerObserver(PlayerUI.getInstance());
	};

	// called on new game
	/**
	 * @modifies activePlayerIndex to first player, fills the players array with
	 *           the new players
	 */
	public void createNewPlayers() {
		activePlayerIndex = 0;
		for (int player = 0; player < players.length; player++) {
			players[player] = new Player(player + "", player);
		}
	}

	// called on load game
	/**
	 * @param name
	 *            names of players
	 * @param indices
	 *            locations of the players
	 * @param directions
	 *            moving directions of the players
	 * @param balances
	 *            balances that players' have
	 * @param assets
	 *            assets that players' have
	 * @modifies sets the numPlayers, fills the players array with the Player
	 *           Objects which has given locations, balances and assets
	 */
	public void createLoadedPlayers(String[] name, int[] indices, int[] directions, boolean[] jailed, int[] balances,
			ArrayList<Integer>[] assets, ArrayList<Integer>[] assetLevels) {
		gameBoard = Board.getInstance();
		int numPlayers = balances.length;
		for (int player = 0; player < numPlayers; player++) {
			players[player] = new Player(name[player], player);
			players[player].setLocation(indices[player]);
			setActivePlayerIndex(player);
			int count = 0;
			// notifyUI();
			if (directions[player] == -1)
				players[player].changeDirection();
			for (Integer asset : assets[player]) {
				buyDeed(players[player], asset);
			}
			for (Integer asset : assets[player]) {
				for (int lev = 0; lev < assetLevels[player].get(count); lev++) {
					gameBoard.getCurrentSquare(asset).buildBuilding();
				}
				count++;
				players[player].incBalance(balances[player] - players[player].getBalance());
			}
			if (jailed[player]) {
				players[player].setJailed(true);
				players[player].setLocation(10);
			}
		}
	}

	// return the active player object
	/**
	 * @effects returns the active player object
	 */
	public Player getActivePlayer() {
		return players[getActivePlayerIndex()];
	}

	// return the active players index
	/**
	 * @effects returns active player index
	 */
	public int getActivePlayerIndex() {
		return activePlayerIndex;
	}

	/**
	 * @param activePlayerIndex
	 *            is the new index will be assigned to the current player
	 * @effects the current player's index
	 */
	public void setActivePlayerIndex(int activePlayerIndex) {
		this.activePlayerIndex = activePlayerIndex;
	}

	// updates
	/**
	 * @param property
	 *            the to add the colored deeds
	 * @param pl
	 *            the player that buys the property
	 * @requires a purchase operation of a colored property
	 * @effects the ArrayList by adding a new color for a player + and set the
	 *          property a buildable one and changes the ownership status if
	 *          conditions are satisfied
	 */
	public void updateDeedColors(Square property, Player pl) {
		pl.addToOwnedDeedColors(property);
		if (pl.getOwnedColorSize(property.getColorGroup()) == gameBoard.getColorGroupSize(property.getColorGroup())) {
			// property.setMonopolyOwner(true); // this is previous version
			property.setBuildable(true);
			for (Integer prop : pl.getOwnedDeedColor(property.getColorGroup())) {
				gameBoard.getCurrentSquare(prop).setMajorityOwner(true);
				gameBoard.getCurrentSquare(prop).setMonopolyOwner(true);
			}
			// ownedDeedColors.get(p.getColorGroup()).add(p.getIndex());
		} else if (pl.getOwnedColorSize(
				property.getColorGroup()) == gameBoard.getColorGroupSize(property.getColorGroup()) - 1) {
			// property.setMajorityOwner(true);
			for (Integer prop : pl.getOwnedDeedColor(property.getColorGroup())) {
				gameBoard.getCurrentSquare(prop).setMajorityOwner(true);
				gameBoard.getCurrentSquare(prop).setBuildable(true);
			}

		}
	}

	// this function is called when the buy deed button is pressed in the UI
	/**
	 * @param p
	 *            is a player
	 * @param squareIndex
	 *            is the index of a square
	 */
	public void buyDeed(Player p, int squareIndex) {
		// if the deed is a property, then update its majority and monopoly
		// flags if needed. update the color group hashmap of player
		// bu nasil kod, bence bozuk.null.equals exception vercek
		Square d = gameBoard.getCurrentSquare(squareIndex);
		if (p.decBalance(d.getPrice())) {
			if (gameBoard.getCurrentSquare(squareIndex).getColorGroup() != null) {
				if (gameBoard.getCurrentSquare(squareIndex).getColorGroup().equals("purple")) {
					p.incNumOfUtils();
				} else {
					updateDeedColors(gameBoard.getCurrentSquare(squareIndex), p);
				}
			}
			p.addAsset(squareIndex);
			d.setOwnerIndex(activePlayerIndex);
			System.out.println(p.getName() + " bought " + p.getAssets());
		}
	}

	// for Translator to access necessary game data
	/**
	 * @effects returns the number of players
	 */
	public int getNumberOfPlayers() {
		return players.length;
	}

	// passes the turn, if players gets double, same player takes another turn

	/**
	 * @modifies changes activePlayerIndex to index of the active player in the
	 *           new turn
	 */
	public void passTurn() {
		int activIndex = Arrays.asList(players).indexOf(getActivePlayer());

		int index = getActivePlayer().getLocation();
		int index2 = getActivePlayer().getLocation();

		if (dice.isBusMove() && secondTime) {	
			Board brd = getBoard();
			while (true) {
				if(brd.getCurrentSquare(index).getName().equals("Chance") || brd.getCurrentSquare(index).getName().equals("Community Chest")) break;
				System.out.println(index);
				index = (index+1)%120;
				if(index == index2){
					index = index2;
					break;
				}
			}
			secondTime = false;
			getActivePlayer().setLocation(index);
			//			gameBoard.getCurrentSquare(index).steppedOn(index);
			System.out.println(index);
			gameBoard.delegateMove(getActivePlayerIndex(), gameBoard.getCurrentSquare(getActivePlayer().getLocation()));


		}else if (dice.isMrMonopolyMove() && secondTime){
			Board brd = getBoard();
			while (brd.getCurrentSquare(index).getOwnerIndex() != -1) {
				index = (index+1)%120;
				System.out.println(index);
				if(index == index2){
					index = index2;
					break;	
				}
			}
			secondTime = false;
			getActivePlayer().setLocation(index);
			//			gameBoard.getCurrentSquare(index).steppedOn(index);
			System.out.println(index);
			gameBoard.delegateMove(getActivePlayerIndex(), gameBoard.getCurrentSquare(getActivePlayer().getLocation()));

		}else {
			if (!dice.isDouble() || (dice.isDouble() && getActivePlayer().isRecentlyJailed())) {
				activIndex = (activePlayerIndex + 1) % 4;
				secondTime = true;
				doubleHistory = 0;
			} else {
				doubleHistory++;
				//				System.out.println("ATTENTION " + getActivePlayer().isJailed());
			}
		}
		Player playerOfInterest = players[activIndex];
		boolean allMortgaged = true;

		for (int i = 0; i < playerOfInterest.getAssets().size(); i++) {
			Square sq = getBoard().getCurrentSquare(playerOfInterest.getAssets().get(i));
			if (!sq.isMortgaged()) {
				allMortgaged = false;
			}
		}

		if (playerOfInterest.getBalance() <= 0 && allMortgaged) {
			playerOfInterest.setBankrupt(true);
		}
		
		int bankruptCounter = 0;
		for(int i = 0; i<players.length; i++) {
			if(players[i].isBankrupt()) bankruptCounter++;
		}
		if(!playerOfInterest.isBankrupt() && bankruptCounter == 3)
			playerOfInterest.setWinner(true);

		activePlayerIndex = activIndex;
		//		System.out.println(activePlayerIndex);
	}

	public Player[] getPlayers() {
		return players;

	}

	// changes the dice values
	/**
	 * @modifies changes the dice's value
	 */
	public void rollDice() {
		dice.rollDice();
	}

	// getting the dice values
	/**
	 * @effects returns an int[] that is representing dice values
	 */
	public int[] getDice() {
		int[] diceValues = { dice.getDie1Value(), dice.getDie2Value() };
		return diceValues;
	}

	/**
	 * @requires it must be the players turn and players must not be at jail
	 * @effects adds dice total to players current location in the direction
	 *          that player is taking
	 */
	public void move() {
		Player p = getActivePlayer();
		int diceTotal = dice.getRegularTotal();

		if (!p.isJailed()) {
			if (doubleHistory == 2 && dice.isDouble()) {
				doubleHistory = 0;
				p.setJailed(true);
				p.setLocation(10);
				getBoard().getCurrentSquare(10).steppedOn(10);
			} else {
				p.setMoving(true);
				for (int i = 0; i < diceTotal - 1; i++) {
					moveOnce(p);
				}
				p.setMoving(false);
				moveOnce(p);
			}
		} else {
			if (dice.isDouble()) {
				p.setJailed(false);
				p.setMoving(true);
				for (int i = 0; i < diceTotal - 1; i++) {
					moveOnce(p);
				}
				p.setMoving(false);
				moveOnce(p);
			} else if (p.getJailRollCount() == 3) {
				p.decBalance(50);
				p.setJailed(false);
				p.setMoving(true);
				for (int i = 0; i < diceTotal - 1; i++) {
					moveOnce(p);
				}
				p.setMoving(false);
				moveOnce(p);
			} else {
				int count = p.getJailRollCount();
				count++;
				p.setJailRollCount(count);
			}
		}

		// PlayerUI.move(getActivePlayerIndex(),p.getLocation());
	}

	private void moveOnce(Player player) {
		int direct = player.getDirection();
		int oldLocation = player.getLocation();
		int newLocation = oldLocation + direct;

		if (0 <= oldLocation && oldLocation < 40) {
			if (newLocation < 0) {
				newLocation = (40 + newLocation) % 40;
			} else {
				newLocation = newLocation % 40;
			}
		} else if (39 < oldLocation && oldLocation <= 63) {
			if (newLocation == 39) {
				newLocation = 63;
			} else if (newLocation == 64) {
				newLocation = 40;
			}
		} else if (63 < oldLocation && oldLocation <= 120) {
			if (newLocation == 63) {
				newLocation = 119;
			} else if (newLocation == 120) {
				newLocation = 64;
			}
		}

		player.setLocation(newLocation);
		gameBoard.delegateMove(getActivePlayerIndex(), gameBoard.getCurrentSquare(player.getLocation()));
	}

	// to be able to get the information to use in UI
	/**
	 * @param index
	 *            is index of a square
	 * @effects returns a boolean whether a square is buyable
	 */
	public boolean isBuyable(int index) {
		return gameBoard.getCurrentSquare(index).isBuyable();
	}

	/**
	 * @param index
	 *            the location of the sqaure that is asked if it requires paying
	 *            rent
	 * @effects returns whether requires rent or not
	 */
	public boolean isPayEnabled(int index) {
		return gameBoard.getCurrentSquare(index).isPayRentEnabled();
	}

	/**
	 * @param activePlayer
	 *            is representing the current player
	 * @effects returns the balance of the active player
	 */
	public int getBalance(Player activePlayer) {
		return activePlayer.getBalance();
	}

	// to be able to get the information to use in UI
	/**
	 * @effects returns the string that contains active player's location on the
	 *          board
	 */
	public String getActivePlayerPosition() {
		return gameBoard.getCurrentSquare(players[activePlayerIndex].getLocation()).getName();
	}

	// to be able to get the information to use in UI
	/**
	 * @effects returns a string[] that contains active player's assets
	 */
	public String[] getActivePlayerAssets() {
		ArrayList<Integer> assetAL = players[activePlayerIndex].getAssets();
		int size = assetAL.size();
		String[] assets = new String[size];
		for (int i = 0; i < size; i++) {
			assets[i] = gameBoard.getCurrentSquare(assetAL.get(i)).getName();
		}
		return assets;
	}

	/**
	 * @requires player should land on a owned square but not owned by him/her
	 *           and have enough money
	 */
	public void payRent() {
		Player p1 = getActivePlayer();
		if (!gameBoard.getCurrentSquare(p1.getLocation()).isMortgaged()) {
			Player p2 = players[gameBoard.getCurrentSquare(p1.getLocation()).getOwnerIndex()];
			int rent = gameBoard.getCurrentSquare(p1.getLocation()).getRent();
			p1.decBalance(rent);
			p2.incBalance(rent);
		}
	}

	/**
	 * @requires it must be a buildable square
	 * @effects returns a boolean whether building is available for a property
	 */
	public boolean canBuild() {
		ArrayList<Integer> assets = players[activePlayerIndex].getAssets();
		for (int i = 0; i < assets.size(); i++) {
			if (gameBoard.getCurrentSquare(assets.get(i)).isBuildable()) {
				return true;
			}
		}
		return false;
	}

	/*
	 * public String[] getActivePlayerBuildableAssets() { ArrayList<Integer>
	 * assetAL = players[activePlayerIndex].getAssets(); ArrayList<String>
	 * buildableAssets = new ArrayList<String>(); for(int i=0; i<assetAL.size();
	 * i++) { Square current = gameBoard.getCurrentSquare(assetAL.get(i));
	 * if(current.isBuildable()) { buildableAssets.add(current.getName()); } }
	 * String[] assets = new String[buildableAssets.size()]; for(int i=0;
	 * i<buildableAssets.size(); i++) { assets[i] = buildableAssets.get(i); }
	 * return assets; }
	 */
	/**
	 * @param toBuild
	 *            the location of the square that is build on
	 */
	public void buildBuilding(int toBuild) {
		gameBoard.getCurrentSquare(getActivePlayer().getAssets().get(toBuild)).buildBuilding();
	}

	/**
	 * @param order
	 *            the type of building that is builded on square. 1 2 3 4 are
	 *            for the house numbers and 5 6 for hotel and skyscraper
	 * @effects returns the integer that represents the building level for the
	 *          property
	 */
	public int getAssetLevel(int order) {
		return gameBoard.getCurrentSquare(getActivePlayer().getAssets().get(order)).getLevel();
	}

	/**
	 * @effects returns a boolean for the player whether he/she has any assets
	 */
	public boolean hasAsset() {
		return !players[activePlayerIndex].getAssets().isEmpty();
	}

	/**
	 * @effects returns a string that is the name of the square
	 */
	public String getCurrentSquareName() {
		return gameBoard.getCurrentSquare(players[activePlayerIndex].getLocation()).getName();
	}

	/**
	 * @effects returns a string that is the name of owner of the specific
	 *          square
	 */
	public String getCurrentSquareOwner() {
		return "Player " + gameBoard.getCurrentSquare(players[activePlayerIndex].getLocation()).getOwnerIndex();

	}

	/**
	 * @effects returns an int that is the level of square
	 */
	public int getSquareLevel() {
		return gameBoard.getCurrentSquare(players[activePlayerIndex].getLocation()).getLevel();
	}

	/**
	 * @effects returns a string that is the type of the square
	 */
	public String getSquareType() {
		return gameBoard.getCurrentSquare(getActivePlayer().getLocation()).getSquareType();
	}
	//
	// @Override
	// public void notifyUI() {
	// }
	//
	// @Override
	// public void registerObserver(Observer observer) {
	// observers.add(observer);
	// }
	//
	// @Override
	// public void unregisterObserver(Observer observer) {
	// // @modifies the observers list and remove one
	// int observerIndex = observers.indexOf(observer);
	// observers.remove(observerIndex);
	// }

	/**
	 * @effects returns a new pool, a place for collecting money in the middle
	 *          of the board
	 */
	public Pool getPool() {
		return pool;
	}

	/**
	 * @param index
	 *            is index of a square
	 * @effects returns a boolean of true if the square is a buildable one
	 */
	public boolean isBuildable(int index) {
		return gameBoard.getCurrentSquare(getActivePlayer().getAssets().get(index)).isBuildable();
	}

	public Board getBoard() {
		return gameBoard;
	}

	// For Testing
	public void setDiceForTest(int a, int b) {
		dice.setDice(a, b);
	}

	// TO-DO: Move player which checks requiresAction for squares

	public boolean diceRepOK() {
		return dice.repOK();
	}

	public boolean repOK() {
		boolean activePlayerOK = activePlayerIndex >= 0 && activePlayerIndex < players.length;
		for (int i = 0; i < players.length; i++) {
			if (!players[i].repOK()) {
				return false;
			}
		}
		System.out.println(activePlayerOK + " " + pool.repOK() + " " + dice.repOK() + " " + gameBoard.repOK());
		return activePlayerOK && pool.repOK() && dice.repOK() && gameBoard.repOK();
	}

	public String toString() {
		String str = "Players: \n";
		for (Player pl : players) {
			str = str + pl.toString();
		}
		str = str + "Active Player: " + players[activePlayerIndex].getName() + "\n";
		str = str + "Dice: \n" + dice.toString();
		str = str + "Pool: \n" + pool.toString();
		return str;
	}

	public void useCard(int index, String color) {
		if (index == -1) {
			index = getActivePlayer().getCards().size() - 1;
		}
		int cardIndex = getActivePlayer().getCards().get(index);
		Card c = CardFactory.generateCard(cardIndex);
		if (cardIndex == 7) {
			c.setColor(color);
		}
		c.execute();
		getActivePlayer().removeCard(index);
	}

	public void keepCard(int index) {
		getActivePlayer().addCard(index);
	}

	public String[] getActivePlayerCards() {
		ArrayList<Integer> cardsAL = players[activePlayerIndex].getCards();
		if (cardsAL.size() == 0) {
			return new String[0];
		}
		int size = cardsAL.size();
		String[] cards = new String[size];
		System.out.println(cardsAL);
		for (int i = 0; i < size; i++) {
			if(cardsAL.get(i)!= -1) {
				cards[i] = CardFactory.generateCard(cardsAL.get(i)).getName();
			}
		}
		return cards;
	}

	public boolean getCurrentSquareIsMortgage() {
		return gameBoard.getCurrentSquare(getActivePlayer().getLocation()).isMortgaged();
	}

	public String[] getPlayersName() {
		String[] names = new String[players.length];
		for (int i = 0; i < players.length; i++) {
			names[i] = players[i].getName();
		}
		return names;
	}

	public String[] getColoredBuiltAsset() {
		ArrayList<String> sq = new ArrayList<String>();
		for (int i = 0; i < 120; i++) {
			Square crSq = gameBoard.getCurrentSquare(i);
			if (crSq.getLevel() > 0) {
				if (!sq.contains(crSq.getColorGroup())) {
					sq.add(crSq.getColorGroup());
				}
			}
		}
		String[] colors = new String[sq.size()];
		for (int i = 0; i < sq.size(); i++) {
			colors[i] = sq.get(i);
		}
		return colors;
	}

	public boolean getCardKeepable(int cardIndex) {
		return CardFactory.generateCard(cardIndex).isKeepable();
	}

	public void sellDeed(int index) {
		Square d = gameBoard.getCurrentSquare(index);
		Player p = getActivePlayer();
		p.incBalance((int) Math.ceil(d.getPrice() / 2));
		d.setOwnerIndex(-1);
		p.getAssets().remove(p.getAssets().indexOf(d.getIndex()));
	}

	public void rollOnce() {
		int rollOnceCard = rand.nextInt(6) + 1;
		System.out.println("Roll once card is picked as: " + rollOnceCard);
		System.out.println("Die value is: " + dice.getDie1Value());
		if(dice.getDie1Value() == rollOnceCard) {
			System.out.println("The card and die are matching, player earns 100$");
			getActivePlayer().incBalance(100);
		} else {
			System.out.println("The card and die are not matching, unfortunately you did not gain any money");
		}
	}

}
