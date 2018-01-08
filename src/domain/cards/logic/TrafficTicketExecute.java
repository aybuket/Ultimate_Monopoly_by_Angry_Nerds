package domain.cards.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.game.Pool;

public class TrafficTicketExecute implements CardLogic {

	@Override
	public void activate() {
		Player activePlayer = MonopolyTheGame.getInstance().getActivePlayer();
		activePlayer.decBalance(15);
		Pool.getInstance().putMoney(15);		
	}

}
