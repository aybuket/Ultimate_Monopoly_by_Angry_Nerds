package domain.cards.logic;

import java.util.ArrayList;

import domain.Observable;
import domain.board.Board;
import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.Square;
import ui.ButtonsUI;
import ui.Observer;



public class HurricaneCardExecute implements CardLogic, Observable {

	private ArrayList<Observer> observers = new ArrayList<Observer>();

	@Override
	public void activate() {
		registerObserver(ButtonsUI.getInstance());
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();

		if (!ply.isMoving() ){
			notifyUI();
		}
		System.out.println("Interaction: "+interaction.getChosenAction());
		if (!ply.isMoving() && interaction.getChosenAction()!="") {
			
			Board b = MonopolyTheGame.getInstance().getBoard();
			int[] properties = b.getColorGroup(interaction.getChosenAction());
			for(int i: properties) {
				System.out.println(b.getCurrentSquare(i).getName());
				b.getCurrentSquare(i).sellBuilding();
			}


			notifyUI();
		}
	}

	@Override
	public void notifyUI() {
		for(Observer obs: observers) {
			obs.update("","hurricane");
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
