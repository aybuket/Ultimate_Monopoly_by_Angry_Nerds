package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class BonusSteppedOn extends SteppedOn {

	public BonusSteppedOn(int index) {
		super(index);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void activate(int i) {

		Player currentPlayer = MonopolyTheGame.getInstance().getActivePlayer();
		if (!currentPlayer.isRecentlyJailed()) {
			if (currentPlayer.isMoving()) {
				currentPlayer.incBalance(250);
			} else {
				currentPlayer.incBalance(300);
			}
			System.out.println("You my friend, in for a treat");
		} else {
			currentPlayer.setRecentlyJailed(false);
		}
		if(!currentPlayer.isMoving()) {
			notifyUI();
		}

	}

}
