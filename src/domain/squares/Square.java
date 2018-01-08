package domain.squares;

import domain.game.Auctionable;
import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.buildingcontainerlogic.BuildingContainerLogic;
import domain.squares.buildingcontainerlogic.CabCompanyContainer;
import domain.squares.buildingcontainerlogic.PropertyContainerLogic;
import domain.squares.buildingcontainerlogic.RailroadContainer;
import domain.squares.interaction.BasicInteraction;
import domain.squares.interaction.SquareInteraction;
import domain.squares.logic.SteppedOnLogic;
import domain.squares.rentlogic.CommonRentLogic;
import domain.squares.rentlogic.RentLogic;
import domain.squares.rentlogic.UtilityRentLogic;

/**
 * This class contains most of the knowledge about squares including properties,
 * jail, chance, community chest and various others
 * 
 * @author AngryNerds
 */
public class Square implements Auctionable {

	SteppedOnStrategyFactory steppedOnFactory = SteppedOnStrategyFactory.getInstance();
	// Stepped on Logic - A strategy object
	private SteppedOnLogic sOL;

	// Boolean value for Interaction required Squares
	// To be implemented
	private SquareInteraction interaction;

	// For transactions
	private boolean payRentEnabled = false; // GUI button switch
	private int ownerIndex = -1;
	private boolean majorityOwner = false;
	private boolean monopolyOwner = false;

	// For buildings
	// Maybe we can move this part into container logic ?
	private int buildingLevel = 0;
	private BuildingContainerLogic buildings;

	// For Rent-Buy
	private int rent;
	private RentLogic rlogic; // For updating the rent
	private int price;

	// General Information about Square
	private int index;
	private String colorGroup;

	// Name for the Square
	private String name;

	// Mortgage
	private boolean mortgaged = false;

	// Constructor

	/*
	 * public Square(SteppedOnLogic logic, boolean buyenbld, String n) {
	 * this.sOL = logic; this.buyable = buyenable; this.name = name; }
	 */
	// Functional
	/**
	 * Constructor
	 * 
	 * @param string
	 *            is name of square
	 * @param i
	 *            is index of square
	 */
	public Square(String string, int i) {

		name = string;
		ownerIndex = -2;
		index = i;
		switch(index){
		case 79: interaction= new BasicInteraction(); break;
		case 4:  interaction= new BasicInteraction(); break;
		case 30: interaction= new BasicInteraction(); break;
		case 52: interaction= new BasicInteraction(); break;
		case 64: interaction= new BasicInteraction(); break;
		}
		sOL = steppedOnFactory.createSteppedOn(i);
	}

	// Property
	/**
	 * Constructor
	 * 
	 * @param string
	 *            name of square
	 * @param i
	 *            index of square
	 * @param j
	 *            price of square
	 * @param color
	 *            color of square
	 * @param lev0
	 *            default rent amount
	 * @param lev1
	 *            rent with 1 house
	 * @param lev2
	 *            rent with 2 houses
	 * @param lev3
	 *            rent with 3 houses
	 * @param lev4
	 *            rent with 4 houses
	 * @param lev5
	 *            rent with hotel
	 * @param lev6
	 *            rent with skyscraper
	 */
	public Square(String string, int i, int j, String color, int lev0, int lev1, int lev2, int lev3, int lev4, int lev5,
			int lev6) {
		name = string;
		index = i;
		price = j;
		rent = lev0;
		colorGroup = color;
		rlogic = new CommonRentLogic(new int[] { lev0, lev1, lev2, lev3, lev4, lev5, lev6 });
		buildings = new PropertyContainerLogic(i);
		sOL = steppedOnFactory.createSteppedOn(i);
	}

	// Utility, Railroad, CabComp
	/**
	 * Constructor
	 * 
	 * @param string
	 *            name of square
	 * @param i
	 *            index of square
	 * @param j
	 *            price of square
	 */
	public Square(String string, int i, int j) {
		name = string;
		index = i;
		price = j;
		majorityOwner = true;
		sOL = steppedOnFactory.createSteppedOn(i);
		// dont forget adding type check (color group purple for utility, others
		// have container etc.)
		switch (i) {
		case 15:
			buildings = new RailroadContainer(i);
			rlogic = new CommonRentLogic(new int[] { 25, 50 });
			rent = 25;
			break;
		case 35:
			buildings = new RailroadContainer(i);
			rlogic = new CommonRentLogic(new int[] { 25, 50 });
			rent = 25;
			break;
		case 71:
			buildings = new RailroadContainer(i);
			rlogic = new CommonRentLogic(new int[] { 25, 50 });
			rent = 25;
			break;
		case 99:
			buildings = new RailroadContainer(i);
			rlogic = new CommonRentLogic(new int[] { 25, 50 });
			rent = 25;
			break;
		case 70:
			buildings = new CabCompanyContainer(i);
			rlogic = new CommonRentLogic(new int[] { 30, 60 });
			rent = 30;
			break;
		case 86:
			buildings = new CabCompanyContainer(i);
			rlogic = new CommonRentLogic(new int[] { 30, 60 });
			rent = 30;
			break;
		case 98:
			buildings = new CabCompanyContainer(i);
			rlogic = new CommonRentLogic(new int[] { 30, 60 });
			rent = 30;
			break;
		case 114:
			buildings = new CabCompanyContainer(i);
			rlogic = new CommonRentLogic(new int[] { 30, 60 });
			rent = 30;
			break;
		default:
			colorGroup = "purple";
			rlogic = new UtilityRentLogic();
			majorityOwner = false;
		}
	}

	/**
	 * @requires player to land on a square
	 * @modifies the steppedOnLogic type variable by activating
	 * @param index
	 *            index of square
	 */
	public void steppedOn(int index) {

		if (sOL != null)
			sOL.activate(index);
	}

	/**
	 * @effects returns a boolean for a buyable square that indicates whether it
	 *          is owned or not
	 */
	public boolean isOwned() {
		//
		if (ownerIndex == -1)
			return false;
		else
			return true;
	}

	/**
	 * Adds a proper building on the square
	 * 
	 * @modifies the square by adding a building type on it.
	 */
	public void buildBuilding() {
		//
		if (isBuildable()) {
			buildings.build();
			// temporary solution, and sell needs to be added
			buildingLevel++;
		} else {
			System.out.println("Cannot build on this Square, buildEnabled is set to false");
		}
	}

	// Getter - Setters
	/**
	 * @effects returns true if the square is a buyable meaning that can be
	 *          purchased with paying money
	 * 
	 */
	public boolean isBuyable() {
		
		if(ownerIndex == -1){
			return true;
		}else {
			return false;
		}
		
	}

	/**
	 * 
	 * @requires that this square is a buyable one
	 * @effects returns a boolean about whether paying rent is enable for that
	 *          specific square or not
	 */
	public boolean isPayRentEnabled() {
		//
		return payRentEnabled;
	}

	/**
	 * 
	 * @effects returns a boolean shows that whether the given square is
	 *          available for building buildings
	 */
	public boolean isBuildable() {
		return buildings != null && buildings.isEnabler()
				&& ((majorityOwner && buildingLevel < 5) || (monopolyOwner && buildingLevel == 5));
		// return buildings!=null && buildings.isEnabler()&& buildingLevel;
	}

	/**
	 * @param buildable
	 *            the indicator of if the square is buildable or not
	 * @requires that this square is a buyable one
	 * @modifies changes the boolean shows that whether the given square is
	 *           available for building buildings
	 */
	public void setBuildable(boolean buildable) {

		buildings.setEnabler(buildable);
	}

	/**
	 * @effects returns the name of the square
	 */
	public String getName() {
		return name;
	}

	/**
	 * @requires that the square is owned by one of the players
	 * @effects returns the owner player's index
	 */
	public int getOwnerIndex() {

		return ownerIndex;
	}

	/**
	 * @requires for a square to be a buildable one
	 * @effects returns the building level of the square
	 */
	public int getLevel() {
		return buildingLevel;
	}

	/**
	 * @param sOL
	 *            stepped on logic for the square
	 * @modifies sOL by setting it to the given input
	 */
	public void setsOL(SteppedOnLogic sOL) {
		//
		this.sOL = sOL;
	}

//	/**
//	 * @param buyable
//	 *            the indicator of if the square is buyable or not
//	 * @modifies the buyability of a square by changing the boolean value
//	 */
//	public void setBuyable(boolean buyable) {
//
//		this.buyable = buyable;
//	}

	/**
	 * @param payRentEnabled
	 *            the indicator of if the square requires to pay a rent or not
	 * @requires that the property must owned by a player
	 * @modifies the variable to make available to collect rent for a square
	 */
	public void setPayRentEnabled(boolean payRentEnabled) {
		//
		this.payRentEnabled = payRentEnabled;
	}

	/**
	 * @param ownerIndex
	 *            the player index who buys the square
	 * @requires that this square is a buyable one
	 * @modifies the owner index of a property with the players index that
	 *           purchased it
	 */
	public void setOwnerIndex(int ownerIndex) {
		//
		this.ownerIndex = ownerIndex;
	}

	/**
	 * @param buildingLevel
	 *            the level of building on the square
	 * @requires that this square is a buyable one
	 * @modifies the building level of a square
	 */
	public void setBuildingLevel(int buildingLevel) {

		this.buildingLevel = buildingLevel;
	}

	/**
	 * @param name
	 *            the name for the square
	 * @modifies the name by setting it
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * @requires that squares is a property that can have a rent
	 * @effects returns the rent value of this specific square
	 */
	public int getRent() {
		
		  if (buildings != null && monopolyOwner && buildingLevel == 0) {
			return 3 * rent;
		} 
		else if (buildings != null && majorityOwner && buildingLevel == 0) {
			return 2 * rent;
		}
		else {
			return rlogic.updateRent(index);
		}

	}

	/**
	 * 
	 * @effects returns true if player has a majority ownership
	 */
	public boolean isMajorityOwner() {

		return majorityOwner;
	}

	/**
	 * @param majorityOwner
	 *            the indicator of if the owner has a majority ownership or not
	 * @modifies the majority ownership value of a player
	 */
	public void setMajorityOwner(boolean majorityOwner) {

		this.majorityOwner = majorityOwner;
	}

	/**
	 * @effects returns true if the owner of the square has all properties from
	 *          the color group
	 */
	public boolean isMonopolyOwner() {
		//
		return monopolyOwner;
	}

	/**
	 * @param monopolyOwner
	 *            the indicator of if the owner has all from the color group
	 * @modifies the monopoly ownership value of a player
	 */
	public void setMonopolyOwner(boolean monopolyOwner) {

		this.monopolyOwner = monopolyOwner;
	}

	/**
	 * @requires this must be a buyable square
	 * @effects returns the price for the square
	 * 
	 */
	public int getPrice() {

		return price;
	}

	/**
	 * @param price
	 *            the price for the square
	 * @requires this square must have an initial price
	 * @modifies changes the price of the square
	 */
	public void setPrice(int price) {

		this.price = price;
	}

	/**
	 * @effects the index of the square
	 */
	public int getIndex() {

		return index;
	}

	/**
	 * @param index
	 *            the index for the square
	 * @modifies sets the index of the square
	 */
	public void setIndex(int index) {

		this.index = index;
	}

	/**
	 * @effects returns the color group which the square belongs
	 */
	public String getColorGroup() {

		return colorGroup;
	}

	/**
	 * @param colorGroup
	 *            the color group for the square
	 * @modifies changes the color group of the square
	 */
	public void setColorGroup(String colorGroup) {

		this.colorGroup = colorGroup;
	}

	/**
	 * @effects returns interaction type with the UI
	 */
	public SquareInteraction getInteraction() {
		return interaction;
	}

	/**
	 * 
	 * @effects returns the square type
	 */
	public String getSquareType() {
		if (buildings != null) {
			if (buildings.getClass().toString()
					.equals("class domain.squares.buildingcontainerlogic.PropertyContainerLogic")) {
				return "Property";
			} else if (buildings.getClass().toString()
					.equals("class domain.squares.buildingcontainerlogic.RailroadContainer")) {
				return "Railroad";
			} else
				return "CabCompany";

		} else
			return "";
	}

	public void mortgage() {
		if (!mortgaged) {
			if (getLevel() == 0) {
				Player pl = MonopolyTheGame.getInstance().getActivePlayer();
				this.mortgaged = true;
				pl.incBalance((int)Math.ceil(getPrice() / 2));
			}
		}
	}

	public void liftMortgage() {
		Player pl = MonopolyTheGame.getInstance().getActivePlayer();
		if (mortgaged && pl.decBalance((int)Math.ceil(getPrice() / 2) + (int)Math.ceil((getPrice() / 2) / 10))) {
			this.mortgaged = false;
		}else {
			System.out.println("You cannot lift the mortgage");
		}
	}

	public boolean isMortgaged() {
		return mortgaged;
	}

	public boolean repOK() {
		boolean indexOK = index < 120 && index >= 0;
		boolean levelOK;
		if (getSquareType().equals("Property")) {
			levelOK = buildingLevel >= 0 && buildingLevel <= 6;
		} else if (getSquareType().equals("Railroad") || getSquareType().equals("CabCompany")) {
			levelOK = buildingLevel >= 0 && buildingLevel <= 1;
		} else
			levelOK = buildingLevel == 0;
		boolean solOK = sOL != null;
		System.out.println(indexOK + " " + levelOK + " " + solOK);
		return indexOK && levelOK && solOK;
	}

	public String toString() {
		String str;
		if (getSquareType().equals("Property")) {
			str = "Type: Property Square \n" + toStringCommon() + "Color: " + getColorGroup() + "\n"
					+ toStringCommonBuyable();
		} else if (getSquareType().equals("Railroad") || getSquareType().equals("CabCompany")) {
			if (getColorGroup() == "purple") {
				str = "Type: Utility Square \n";
			} else {
				str = "Type: " + getSquareType() + " Square \n";
			}
			str = str + toStringCommon() + toStringCommonBuyable();
		} else {
			str = "Type: Functional Square \n" + toStringCommon();
		}

		return str;
	}

	private String toStringCommon() {
		String str = "Name " + getName() + "\n" + "Location: " + getIndex() + "\n";
		return str;
	}

	private String toStringCommonBuyable() {
		String str = "Buyable: " + isBuyable() + "\n" + "Price: " + getPrice() + "\n" + "Rent: " + getRent() + "\n"
				+ "Owner Index: " + getOwnerIndex() + "\n";
		if (getColorGroup()!=null && !getColorGroup().equals("purple")) {
			str = str + "Buildable: " + isBuildable() + "\n" + "Level: " + getLevel() + "\n";
		}
		return str;
	}

	public void sellBuilding() {
		buildings.sell();
		if(buildingLevel > 0){
			buildingLevel--;
		}
	}
}