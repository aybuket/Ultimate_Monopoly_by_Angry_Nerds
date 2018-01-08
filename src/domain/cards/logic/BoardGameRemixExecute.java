package domain.cards.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;
import domain.game.Pool;

public class BoardGameRemixExecute implements CardLogic {

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		Player activePlayer = MonopolyTheGame.getInstance().getActivePlayer();
		
		activePlayer.incBalance(10);
		
	}

}
