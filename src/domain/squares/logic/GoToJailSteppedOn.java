package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.squares.Square;

public class GoToJailSteppedOn extends SteppedOn {

	public GoToJailSteppedOn(int index) {
		super(index);
	}

	@Override
	public void activate(int i) {

		Player activePlayer = MonopolyTheGame.getInstance().getActivePlayer();
		if (!activePlayer.isMoving()) {
			activePlayer.setLocation(10);
			activePlayer.setJailed(true);
			Square currentSquare = MonopolyTheGame.getInstance().getBoard().getCurrentSquare(activePlayer.getLocation());
			currentSquare.steppedOn(activePlayer.getLocation());
			System.out.println("Well not very lucky today, are you ?");
			notifyUI();
		}
	}

}
