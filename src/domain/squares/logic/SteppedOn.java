package domain.squares.logic;

import java.util.ArrayList;

import domain.Observable;
import domain.game.MonopolyTheGame;
import domain.squares.Square;
import ui.ButtonsUI;
import ui.Observer;

public abstract class SteppedOn implements Observable, SteppedOnLogic{
	protected ArrayList<Observer> observers = new ArrayList<Observer>();

	private int index;

	public SteppedOn(int i) {
		this.index = i;
		registerObserver(ButtonsUI.getInstance());
	}

	public int getIndex() {
		return index;
	}
	

	@Override
	public void notifyUI() {
			for(Observer obs: observers) {
				Square currentSquare = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(this.getIndex());
				obs.update(new boolean[] {currentSquare.isBuyable(),currentSquare.isPayRentEnabled()}, "buy");
			}
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void unregisterObserver(Observer observer) {
		observers.remove(observer);
		
	}
	
}
