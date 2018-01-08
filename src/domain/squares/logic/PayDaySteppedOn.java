package domain.squares.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class PayDaySteppedOn extends SteppedOn {

	public PayDaySteppedOn(int i) {
		super(i);
	}

	@Override
	public void activate(int i) {

		Player currentPlayer = MonopolyTheGame.getInstance().getActivePlayer();

		if (!currentPlayer.isRecentlyJailed()) {
			int[] dices = MonopolyTheGame.getInstance().getDice();
			int total = dices[0] + dices[1];

			if (currentPlayer.usedCard()) {
				currentPlayer.incBalance(400);
				currentPlayer.setUsedCard(false);
			} else if ((total % 2) == 1) {
				currentPlayer.incBalance(300);
			} else if ((total % 2) == 0) {
				currentPlayer.incBalance(400);
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
