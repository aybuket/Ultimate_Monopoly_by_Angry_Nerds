package domain.cards.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class GetOutOfJailFreeExecute implements CardLogic {

	@Override
	public void activate() {
		Player pl = MonopolyTheGame.getInstance().getActivePlayer();
		pl.setJailed(false);
	}

}
