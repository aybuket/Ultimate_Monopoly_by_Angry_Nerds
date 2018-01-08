package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.Square;
import ui.Observer;

public class IncomeTaxSteppedOn extends SteppedOn{

	public IncomeTaxSteppedOn(int index) {
		super(index);
	}

	@Override
	public void activate(int i) {
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();
		Square currentSquare = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(ply.getLocation());

		if (!ply.isMoving() ){
			notifyUI();
		}		

		if (!ply.isMoving() && currentSquare.getInteraction().getChosenAction()!=0) {
			if(currentSquare.getInteraction().getChosenAction()==1) {
				Player pl= MonopolyTheGame.getInstance().getActivePlayer();
				pl.decBalance(pl.getBalance()/10);
			}
			if(currentSquare.getInteraction().getChosenAction()==2) {
				Player pl= MonopolyTheGame.getInstance().getActivePlayer();
				pl.decBalance(100);
			}
			System.out.println("But we built double highways with those... Wait, is that a shoebox?");
			notifyUI();
		}
	}
	
	@Override
	public void notifyUI() {
		for(Observer obs: observers) {
			obs.update(true,"income");
		}
	}

}
