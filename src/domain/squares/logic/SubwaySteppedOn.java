package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.Square;
import ui.Observer;

public class SubwaySteppedOn extends SteppedOn{

	public SubwaySteppedOn(int i) {
		super(i);
	}

	@Override
	public void activate(int i) {
		Player ply = MonopolyTheGame.getInstance().getActivePlayer();
		Square currentSquare = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(this.getIndex());
		if (!ply.isMoving()){
			notifyUI();
		}

		if (!ply.isMoving() && currentSquare.getInteraction().getChosenAction()!=0){
			System.out.println(
					"'And if thou gaze long into an abyss, the abyss will also gaze into thee' said the Nietzsche. Wonder what happens if you pass through abyss with 13.5 miles per hour");
			//CommentedOutForTesting TODO
			int ind= currentSquare.getInteraction().getChosenAction();
			ply.setLocation(ind-1); 
			if(ply.getDirection() == -1) {
				ply.changeDirection();
			}
			notifyUI();
			currentSquare.getInteraction().setChoosenAction(0);
		}

	}

	@Override
	public void notifyUI() {
		for(Observer obs: observers) {
			obs.update(true, "subway");
		}
	}

}
