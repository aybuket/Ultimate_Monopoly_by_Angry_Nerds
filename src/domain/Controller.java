package domain;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import domain.board.BuildingPriceFactory;
import domain.bot.Bot;
import domain.cards.Card;
import domain.cards.CardFactory;
import domain.game.Auction;
import domain.game.MonopolyTheGame;
import domain.game.Pool;
import domain.squares.Square;
import ui.Observer;

public class Controller implements Observable{

	static Controller instance;
	static MonopolyTheGame gameInstance = MonopolyTheGame.getInstance();
	static Translator translatorInstance = Translator.getInstance();
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private InactivityTask inactivityTask;
	private Timer timer;

	public static Controller getInstance(){

		if(instance==null){
			instance= new Controller();
		}
		return instance;
	}

	private Controller() {
		registerObserver(Bot.getInstance());
		// creates a Timer object and schedules the inactivityTask to 30 seconds
		timer = new Timer();
		inactivityTask = new InactivityTask();
		timer.schedule(inactivityTask, 0, 30000);
	};

	public int[] getDice() {
		return gameInstance.getDice();
	}

	public void rollDice() { 
		gameInstance.rollDice();
		inactivityTask.setInactive(false);
	}

	public void buyDeed() {
		gameInstance.buyDeed(gameInstance.getActivePlayer(), gameInstance.getActivePlayer().getLocation());
		inactivityTask.setInactive(false);
	}

	public void payRent() {
		gameInstance.payRent();
		inactivityTask.setInactive(false);
	}

	public void pass() {
		gameInstance.passTurn();
		inactivityTask.setInactive(false);
	}

	public void drawCard() {
		inactivityTask.setInactive(false);
	}


	public void mortgage(int m) {
		if(isMortgage(m)) {
			gameInstance.getBoard().getCurrentSquare(gameInstance.getActivePlayer().getAssets().get(m)).liftMortgage();
		}else {
			gameInstance.getBoard().getCurrentSquare(gameInstance.getActivePlayer().getAssets().get(m)).mortgage();
		}
		inactivityTask.setInactive(false);
	}

	public void saveGame() {
		translatorInstance.saveGame("test");
		inactivityTask.setInactive(false);
	}
	public void loadGame() {
		translatorInstance.loadGame("test");
		inactivityTask.setInactive(false);
	}
	public void newGame() {
		translatorInstance.saveGame("test");
		inactivityTask.setInactive(false);
	}
	public void move() {
		gameInstance.move();
	}
	public void createPlayers(){
		gameInstance.createNewPlayers();
	}

	public boolean isBuyable() {
		return gameInstance.isBuyable(gameInstance.getActivePlayer().getLocation());
	}

	public int getActivePlayer() {
		return gameInstance.getActivePlayerIndex();
	}

	public int getActivePlayerBalance() {
		return gameInstance.getBalance(gameInstance.getActivePlayer());
	}

	public String getActivePlayerPosition() {
		return  gameInstance.getActivePlayerPosition();
	}

	public String[] getActivePlayerAssets() {
		return gameInstance.getActivePlayerAssets();
	}

	public boolean isPayEnabled() {
		return gameInstance.isPayEnabled(gameInstance.getActivePlayer().getLocation());
	}

	public void buildBuilding(int toBuild){
		gameInstance.buildBuilding(toBuild);
	}

	public int getAssetLevel(int order){
		return gameInstance.getAssetLevel(order);
	}

	public int getCardIndexNumber(int index) {
		return gameInstance.getActivePlayer().getCards().get(index);
	}

	public int getCurrentSquare() {
		return gameInstance.getActivePlayer().getLocation();
	}

	//public String[] getActivePlayerBuildableAssets() {
	//	return gameInstance.getActivePlayerBuildableAssets();
	//}

	public String getCurrentSquareName() {
		return gameInstance.getCurrentSquareName();
	}

	public String getCurrentSquareOwner() {
		return gameInstance.getCurrentSquareOwner();
	}

	public int getSquareLevel() {
		return gameInstance.getSquareLevel();
	}

	public String getSquareType() {
		return gameInstance.getSquareType();
	}
	public boolean isBuildable(int index){
		return gameInstance.isBuildable(index);
	}

	public void useCard(int index, String string) {
		gameInstance.useCard(index, string);
	}

	public void keepCard(int index) {
		gameInstance.keepCard(index);
	}

	public String[] getActivePlayerCards() {
		return gameInstance.getActivePlayerCards();
	}

	@Override
	public void notifyUI() {
		for(Observer obs : observers) {
			obs.update(inactivityTask.isInactive(), "reactInactive");
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

	// A class extending TimeTask class to be used by the timer. It basically has a boolean
	// that keeps the inactivity information. This boolean is set to be false anytime a button 
	// is clicked in the above methods. When the 30 seconds has passed and the player has been inactive,
	// the Bot reacts to this. Otherwise it resets the inactivity boolean to true.
	class InactivityTask extends TimerTask {
		boolean inactive = false;
		@Override
		public void run() {
			if(isInactive()) {
				//System.out.println("hayırdır suskunsun?");
				notifyUI();
				setInactive(false);
			} else {
				setInactive(true);
			}
		}

		public boolean isInactive() {
			return inactive;
		}

		public void setInactive(boolean inactive) {
			this.inactive = inactive;
		}
	}




	public boolean getCurrentSquareIsMortgage() {
		return gameInstance.getCurrentSquareIsMortgage();
	}

	public String[] getPlayersName() {
		return gameInstance.getPlayersName();
	}

	public void bid(int givenBid, int crPly) {
		Auction.getInstance().registerBid(gameInstance.getPlayers()[crPly], givenBid);
	}

	public void auction() {
		Auction.getInstance().finishAuction();
	}

	public void startAuction() {
		int index = getCurrentSquare();
		Auction.getInstance().startAuction(gameInstance.getBoard().getCurrentSquare(index));

	}

	public int getPoolBalance() {
		return Pool.getInstance().getBalance();
	}

	public int getBuildingLevel(int selectedIndex) {
		return gameInstance.getBoard().getCurrentSquare(gameInstance.getActivePlayer().getAssets().get(selectedIndex)).getLevel();
	}

	public boolean isMortgage(int selectedIndex) {
		return gameInstance.getBoard().getCurrentSquare(gameInstance.getActivePlayer().getAssets().get(selectedIndex)).isMortgaged();
	}

	public String[] getSquaresName() {
		String[] names = new String[120];
		for(int i=0; i<120; i++) {
			names[i] = gameInstance.getBoard().getCurrentSquare(i).getName();
		}
		return names;
	}

	public void subway(int index) {
		gameInstance.getBoard().getCurrentSquare(64).getInteraction().setChoosenAction(index+1);
		gameInstance.getBoard().getCurrentSquare(64).steppedOn(64);
		gameInstance.getBoard().getCurrentSquare(index).steppedOn(index);
	}

	public void incomeTax(int i) {
		gameInstance.getBoard().getCurrentSquare(4).getInteraction().setChoosenAction(i);
		gameInstance.getBoard().getCurrentSquare(4).steppedOn(4);
	}

	public String[] getColoredBuiltAsset() {
		return gameInstance.getColoredBuiltAsset();
	}

	public void sell(int sellIndex) {
		Square s = gameInstance.getBoard().getCurrentSquare(gameInstance.getActivePlayer().getAssets().get(sellIndex));
		if(s.getLevel()==0) {
			MonopolyTheGame.getInstance().sellDeed(gameInstance.getActivePlayer().getAssets().get(sellIndex));
		}else {
			s.sellBuilding();
			gameInstance.getActivePlayer().incBalance(BuildingPriceFactory.getPrice(s.getColorGroup()));
		}
	}

	public boolean getCardIsKeepable(int cardIndex) {
		return gameInstance.getCardKeepable(cardIndex);
	}


}