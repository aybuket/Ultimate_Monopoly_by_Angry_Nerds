package domain.cards.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class AdvanceIllinoisExecute implements CardLogic {

	@Override
	public void activate() {
		Player activePlayer = MonopolyTheGame.getInstance().getActivePlayer();
		activePlayer.setLocation(24);		
		MonopolyTheGame.getInstance().getBoard().getCurrentSquare(24).steppedOn(24);
	}

}
