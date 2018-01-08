package domain.cards.logic;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class GettingMarriedExecute implements CardLogic {

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		MonopolyTheGame mi = MonopolyTheGame.getInstance();
		Player activePlayer = mi.getActivePlayer();
		for (int i=0; i< mi.getPlayers().length; i++) {
			if (i == mi.getActivePlayerIndex()) {
				activePlayer.incBalance((mi.getPlayers().length -1) * 25);
			}
			else {
				mi.getPlayers()[i].decBalance(25);
			}
		}
	}

}
