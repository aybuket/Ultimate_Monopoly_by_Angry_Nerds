package domain.game;

import java.util.ArrayList;
import java.util.Random;

import domain.Observable;
import ui.DrawUI;
import ui.Observer;
/**
 * This class specifies the dice which are tools that players use to move along the board by rolling them
 * @author AngryNerds
 */
public class Dice implements Observable{
	//singleton
	static Dice instance;

	private ArrayList<Observer> observers = new ArrayList<Observer>();

	/**
	 * Singleton pattern
	 * @effects returns the dice instance
	 */
	public static Dice getInstance(){
		if(instance==null){
			instance= new Dice();
		}
		return instance;
	}

	// values will be between 1 and 6
	private int die1; 
	private int die2; 
	private int speedDie; 
	private Random rand = new Random();
	// constructor
	private Dice() {
		die1 = 0;
		die2 = 0;
		speedDie = 0;
		registerObserver(DrawUI.getInstance());
	}
	// total of regular dice
	/**
	 * @effects returns the sum of the dice's values
	 */
	public int getRegularTotal(){
		notifyUI();
		if(!isBusMove()){
			if(!isMrMonopolyMove()){
				return die1+die2+speedDie;
			}else{
				return die1 + die2;
			}
		}else {
			return die1+die2;
		}	
	}
	// single value to compare with roll once card

	//For testing
	public void setDice(int a, int b){
		this.die1 = a;
		this.die2 = b;
	}
	/**
	 * @effects returns the roll once value
	 */
	public int getRollOnceValue() {
		return die1;
	}
	// assign random numbers between 1-6 to dice
	/**
	 * @modifies changes die1 and die2 values to a number between 1 and 6
	 */
	public void rollDice() {
		die1 = rand.nextInt(6) + 1;
		die2 = rand.nextInt(6) + 1;
		speedDie = rand.nextInt(6) + 1;
	}

	// where must be special moves of speed die? 
	// control methods for now 
	/**
	 * @effects returns if the bus move came
	 */
	public boolean isBusMove() {
		return speedDie == 6;
	}
	/**
	 * @effects returns if the monopoly move came
	 */
	public boolean isMrMonopolyMove() {
		return speedDie == 4 || speedDie == 5;
	}
	// returns the first die
	/**
	 * @effects returns the value of the first die
	 */
	public int getDie1Value() {
		return die1;
	}
	//returns the second die
	/**
	 * @effects returns the value of the second die
	 */
	public int getDie2Value() {
		return die2;
	}
	/**
	 * @effects returns if the both die has the same value
	 */
	public boolean isDouble() {
//				return true;
		return die1==die2;
	}
	@Override
	public void notifyUI() {
		for(Observer obs : observers) {
			obs.update(new int[] {getDie1Value(), getDie2Value(), speedDie}, "rollDice");
//						obs.update(new int[] {2, 2,2}, "rollDice");

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


	public boolean repOK(){
		boolean die1OK= die1<=6 && die1>=1;
		boolean die2OK= die2<=6 && die2>=1;
		boolean speedOK= speedDie<=6 && speedDie>=1;
		return die1OK && die2OK && speedOK;
	}

	public String toString() {
		String str = "Die1: "+getDie1Value()+"\n"
				+"Die2: "+getDie2Value()+"\n"
				+"Speed Die: "+getSpeedDie()+"\n";
		return str;
	}
	private String getSpeedDie() {
		if(speedDie<4) {
			return speedDie+"";
		}else if(speedDie==4 || speedDie==5) {
			return "Mr. Monopoly";
		}else {
			return "Bus Move";
		}
	}
}