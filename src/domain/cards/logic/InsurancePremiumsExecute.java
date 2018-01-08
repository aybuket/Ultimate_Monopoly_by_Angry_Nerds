package domain.cards.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.game.Pool;

public class InsurancePremiumsExecute implements CardLogic {

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		Player activePlayer = MonopolyTheGame.getInstance().getActivePlayer();
		activePlayer.decBalance(50);
		Pool.getInstance().putMoney(50);
	}

}
