package domain.game;

import java.util.ArrayList;
import java.util.HashMap;

import domain.Observable;
import domain.bot.Bot;
import domain.squares.Square;
import ui.Observer;
import ui.PlayerUI;

/**
 * This class creates players in order to play the Monopoly game.
 * 
 * @author AngryNerds
 *
 */

public class Player implements Observable {

	private int location;
	private String name;
	private int index;
	private int balance;
	private int moveDirection;
	private boolean jailed = false;
	private ArrayList<Integer> assets = new ArrayList<Integer>();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private HashMap<String, ArrayList<Integer>> ownedDeedColors = new HashMap<String, ArrayList<Integer>>();
	private ArrayList<Integer> cards = new ArrayList<Integer>();
	private int numOfUtils;
	private boolean moving = false;
	private boolean moneyGained;
	private boolean moneyLost;
	private boolean inactive;
	private boolean bankrupt;
	private boolean winner = false;
	private boolean withCard = false;
	private boolean recentlyJailed = false;
	private int jailRollCount = 0;

	// private ArrayList<Integer> chanceCards;
	// private ArrayList<Integer> communityChest;
	// private ArrayList<Integer> travelCVoucher;

	// This part will be dedicated to values that play role in functional
	// squares
	// Squares should check if a player is moving or not, if the player is
	// moving, then
	// steppedOn() function should not be called (except Go Square)

	/**
	 * Constructor
	 * 
	 * @param name
	 *            stands for the name of the player
	 */
	public Player(String name, int index) {
		this.name = name;
		this.index = index;
		balance = 3200;
		assets = new ArrayList<Integer>();
		moveDirection = 1;
		registerObserver(PlayerUI.getInstance());
		registerObserver(Bot.getInstance());
		numOfUtils = 0;
	}

	// gets the number of properties owned for that color group
	/**
	 * To get the number of deeds of given color group
	 * 
	 * @param color
	 *            the color group whose size is wanted
	 * @effects returns an integer that represents the number of properties that
	 *          a player have in that specific color group.
	 */
	public int getOwnedColorSize(String color) {
		/**
		 * @param color
		 *            color type that is being searched
		 * @effects returns an integer that represents the number of properties
		 *          that a player have in that specific color group.
		 */
		if (ownedDeedColors.get(color).isEmpty() || ownedDeedColors.get(color) == null) {
			return 0;
		} else
			return ownedDeedColors.get(color).size();
	}

	// add property to the hashmap
	/**
	 * @param property
	 *            is a square to be added to the assets
	 * @requires player to have a property to be added into the hashmap.
	 * @modifies the hashmap ownedDeedColors so that players ownership status
	 *           can be check if needed.
	 */
	public void addToOwnedDeedColors(Square property) {

		if (ownedDeedColors.get(property.getColorGroup()) == null) {
			ownedDeedColors.put(property.getColorGroup(), new ArrayList<Integer>());
		}
		ownedDeedColors.get(property.getColorGroup()).add(property.getIndex());
	}

	/**
	 * @param amount
	 *            amount of the money
	 * @modifies the balance with a magnitude of the amount variable by
	 *           incrementing.
	 */
	public void incBalance(int amount) {

		balance += amount;
		setMoneyGained(true);
		notifyUI();
		setMoneyGained(false);
	}

	/**
	 * @param amount
	 *            amount of money
	 * @modifies the balance with a magnitude of the amount variable by
	 *           decrementing.
	 */
	public boolean decBalance(int amount) {

		if (balance - amount < 0) {
			return false;
		}
		balance -= amount;
		setMoneyLost(true);
		if (balance <= 0) {
			setBankrupt(true);
		}
		notifyUI();
		setMoneyLost(false);
		return true;
	}

	/**
	 * @param newAsset
	 *            is the index of the asset on the board
	 * @requires player to have an asset to add
	 * @modifies the property list that you have named assets
	 */
	public void addAsset(int newAsset) {

		assets.add(newAsset);
	}

	// getters
	/**
	 * @effects returns name of player
	 */
	public String getName() {

		return name;
	}

	/**
	 * @effects returns number of utilities that a player have
	 */
	public int getNumOfUtils() {

		return numOfUtils;
	}

	/**
	 * @modifies the number of utilities that a player have by incrementing
	 */
	public void incNumOfUtils() {

		this.numOfUtils++;
		;
	}

	/**
	 * @modifies the number of utilities that a player have by decrementing
	 */
	public void decNumOfUtils() {

		this.numOfUtils--;
		;
	}

	/**
	 * @effects returns an arraylist consisting of the properties that a player
	 *          have
	 */
	public ArrayList<Integer> getAssets() {

		return assets;
	}

	/**
	 * @effects returns the amount of money that the player have
	 */
	public int getBalance() {

		return balance;
	}

	/**
	 * @effects returns the square that the player is stepping on
	 */
	public int getLocation() {

		return location;
	}

	/**
	 * @param index
	 *            new location index
	 * @modifies the square that the player is currently stepping on
	 */
	public void setLocation(int index) {

		this.location = index;
		if (!isMoving()) {
			notifyUI();
		}
	}

	/**
	 * 
	 * @effects: returns whether the current player is in jail or not
	 */
	public boolean isJailed() {
		return jailed;
	}

	/**
	 * @param recentlyJailed
	 *            is a boolean
	 * @modifies the player's status of recently being in out out of jail
	 */
	public void setRecentlyJailed(boolean recentlyJailed) {
		this.recentlyJailed = recentlyJailed;
	}

	/**
	 * 
	 * @effects: returns whether the current player is in jail or not
	 */
	public boolean isRecentlyJailed() {
		return recentlyJailed;
	}

	/**
	 * @param b
	 *            is a boolean
	 * @modifies the player's status of being in out out of jail
	 */
	public void setJailed(boolean b) {

		jailed = b;
		if (b) {
			recentlyJailed = b;
		}
		notifyUI();
	}

	/**
	 * @modifies the direction that player is taking while moving across the
	 *           board
	 */
	public void changeDirection() {

		moveDirection *= -1;
	}

	/**
	 * @effects the direction that player uses while mpving across the board
	 */
	public int getDirection() {

		return moveDirection;
	}

	/**
	 * @effects returns whether a player is still moving or not
	 */
	public boolean isMoving() {

		return moving;
	}

	/**
	 * @param moving
	 *            boolean that indicates if the player is moving or not
	 * @modifies that the status of movement of a player
	 */
	public void setMoving(boolean moving) {

		this.moving = moving;
	}

	@Override
	public void notifyUI() {
		for (Observer obs : observers) {
			// the updates are now seperated in order to allow the bot react
			// more than one events happening sequentialls
			// (ex: pay rent)
			// obs.update(new boolean[] {moneyGained, moneyLost, jailed,
			// bankrupt, winner}, "react");

			obs.update(moneyGained, "moneyGained");
			obs.update(moneyLost, "moneyLost");
			obs.update(isJailed(), "jailed");
			obs.update(bankrupt, "bankrupt");
			obs.update(winner, "winner");
			obs.update(new int[] { index, getLocation() }, "move");

		}
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void unregisterObserver(Observer observer) {
		int observerIndex = observers.indexOf(observer);
		observers.remove(observerIndex);
	}

	/**
	 * @param color
	 *            is a color type of properties
	 * @effects returns an arraylist that contains property index list of that
	 *          color
	 */
	public ArrayList<Integer> getOwnedDeedColor(String color) {
		return ownedDeedColors.get(color);
	}

	/**
	 * @effects returns a boolean about whether some amount of money is lost
	 */
	public boolean isMoneyLost() {
		return moneyLost;
	}

	/**
	 * @param moneyLost
	 *            is a boolean flag
	 * @modifies sets a boolean about whether some amount of money is lost
	 */
	public void setMoneyLost(boolean moneyLost) {
		this.moneyLost = moneyLost;
	}

	/**
	 * @effects returns a boolean about whether some amount of money is gained
	 */
	public boolean isMoneyGained() {
		return moneyGained;
	}

	/**
	 * @param moneyGained
	 *            is a boolean flag
	 * @modifies sets a boolean about whether some amount of money is gained
	 */
	public void setMoneyGained(boolean moneyGained) {
		this.moneyGained = moneyGained;
	}

	/**
	 * 
	 * @effects returns a boolean whether players are inactive or not
	 */
	public boolean isInactive() {
		return inactive;
	}

	/**
	 * @param inactive
	 *            a boolean whether players are inactive or not
	 * @modifies sets a boolean whether players are inactive or not
	 */
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	/**
	 * @effects returns true when somebody bankrupts
	 */
	public boolean isBankrupt() {
		return bankrupt;
	}

	/**
	 * @param bankrupt
	 *            a boolean is representing whether somebody bankrupted or not
	 * @modifies the boolean when somebody bankrupts
	 */
	public void setBankrupt(boolean bankrupt) {
		this.bankrupt = bankrupt;
	}

	/**
	 * @effects returns how many times a player rolled the dice in jail
	 */
	public int getJailRollCount() {
		return jailRollCount;
	}

	/**
	 * @param jailRollCount
	 *            a integer which keeps the number of times a player rolled the dice while it is in jail
	 * @modifies the integer for keeping track of number of times the dice has been rolled in order to get out of the jail
	 */
	public void setJailRollCount(int jailRollCount) {
		this.jailRollCount = jailRollCount;
	}

	/**
	 * @effects returns true when somebody wins
	 */
	public boolean isWinner() {
		return winner;
	}

	/**
	 * @param winner
	 *            a boolean is representing whether somebody won or not
	 * @modifies the boolean when somebody wins
	 */
	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public void resetReactables() {
		setMoneyGained(false);
		setMoneyLost(false);
		setBankrupt(false);
		setInactive(false);
		setJailed(false);
		setWinner(false);
	}

	public boolean repOK() {
		boolean locationOK = location < 120 && location >= 0;
		boolean balanceOK = balance >= 0;
		boolean assetsOK = assets != null;
		return locationOK && balanceOK && assetsOK;
	}

	public String toString() {
		String str = "Name: " + getName() + "\n" + "Balance: " + getBalance() + "\n" + "Location: " + getLocation()
				+ "\n" + "Direction: " + getDirection() + "\n" + "Assets: " + getAssets().toString() + "\n";
		return str;
	}

	public void addCard(int index) {
		System.out.println("I got the card " + index);
		cards.add(index);
	}

	public void removeCard(int index2) {
		cards.remove(index2);
	}

	public ArrayList<Integer> getCards() {
		return cards;
	}

	public boolean usedCard() {
		return withCard;
	}

	public void setUsedCard(boolean card) {
		withCard = card;
	}
}
