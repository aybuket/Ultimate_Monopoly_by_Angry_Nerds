package domain.cards.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.game.Pool;

public class ExcellentAccountingExecute implements CardLogic {

	@Override
	public void activate() {
		//keepable bu ona dikkat
		Player activePlayer = MonopolyTheGame.getInstance().getActivePlayer();
		activePlayer.setLocation(54);
		activePlayer.incBalance(Pool.getInstance().takeAllMoney());
		
	}

}
