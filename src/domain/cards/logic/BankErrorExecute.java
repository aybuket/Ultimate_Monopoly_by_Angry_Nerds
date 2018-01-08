package domain.cards.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class BankErrorExecute implements CardLogic {

	@Override
	public void activate() {
		Player activePlayer = MonopolyTheGame.getInstance().getActivePlayer();
		activePlayer.incBalance(200);
	}

}
